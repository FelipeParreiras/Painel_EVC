package com.evcvistoria.gestao.users.services;

import com.evcvistoria.gestao.roles.models.RoleEntity;
import com.evcvistoria.gestao.roles.models.enums.Role;
import com.evcvistoria.gestao.roles.repositorys.RoleRepository;
import com.evcvistoria.gestao.users.models.User;
import com.evcvistoria.gestao.users.models.dto.CreateUserRequestDTO;
import com.evcvistoria.gestao.users.models.dto.UserResponseDTO;
import com.evcvistoria.gestao.users.repositorys.UsersRepository;
import com.evcvistoria.gestao.users.services.interfaces.IUsersService;
import com.evcvistoria.gestao.utils.Validators;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserResponseDTO CreateUser(CreateUserRequestDTO createUserRequestDTO) {

        String username = createUserRequestDTO.username().toLowerCase();
        String email = createUserRequestDTO.email();
        String password = createUserRequestDTO.password();

        if (usersRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username já cadastrado!!");
        }
        if (usersRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("E-mail já cadastrado!!");
        }
        if (!Validators.isEmailValid(email)) {
            throw new IllegalArgumentException("E-mail inválido");
        }
        if (!Validators.isPasswordValid(password)) {
            throw new IllegalArgumentException("Senha inválida");
        }
        User user = new User(username, email, encoder.encode(password));
        Set<RoleEntity> roleEntities = createUserRequestDTO.roles().stream()
                .map(role -> roleRepository.findByName(role)
                        .orElseThrow(() -> new IllegalStateException("Role não cadastrada no banco: " + role)))
                .collect(Collectors.toSet());

        roleEntities.forEach(user::addRole);
        User saved = usersRepository.save(user);

        return toResponse(saved);
    }


    @Override
    public Page<UserResponseDTO> listUsers(Pageable pageable) {
        return usersRepository.findAll(pageable).map(this::toResponse);
    }


    @Override
    public UserResponseDTO findByUsername(String username) {
        User user = usersRepository.findByUsername(username.toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return toResponse(user);
    }

    private UserResponseDTO toResponse(User user) {
        Set<Role> roles = user.getRoles().stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toSet());

        return new UserResponseDTO(
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.isEnabled(),
                roles
        );
    }

}
