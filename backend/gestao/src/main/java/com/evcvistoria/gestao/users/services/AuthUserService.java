package com.evcvistoria.gestao.users.services;

import com.evcvistoria.gestao.users.models.User;
import com.evcvistoria.gestao.users.models.dto.UserLoginRequestDTO;
import com.evcvistoria.gestao.users.models.dto.UserLoginResponseDTO;
import com.evcvistoria.gestao.users.repositorys.UsersRepository;
import com.evcvistoria.gestao.security.JwtService;
import com.evcvistoria.gestao.users.services.interfaces.IAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthUserService implements IAuthUserService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final JwtService jwtService;

    @Override
    public UserLoginResponseDTO LoginUser(UserLoginRequestDTO dto) {

        String identificator = dto.identificator();
        String rawPassword = dto.password();

        User user = usersRepository.findByUsername(identificator.toLowerCase())
                .or(() -> usersRepository.findByEmail(identificator))
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // compara senha digitada (raw) com hash salvo no banco
        boolean ok = encoder.matches(rawPassword, user.getPassword());
        if (!ok) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(
                user.getId().toString(),
                Map.of(
                        "username", user.getUsername(),
                        "email", user.getEmail()
                        // "role", user.getRole()  // se tiver
                )
        );

        return new UserLoginResponseDTO(token);
    }
}
