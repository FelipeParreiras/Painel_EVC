package com.evcvistoria.gestao.users.services.interfaces;

import com.evcvistoria.gestao.users.models.User;
import com.evcvistoria.gestao.users.models.dto.CreateUserRequestDTO;
import com.evcvistoria.gestao.users.models.dto.UserResponseDTO;
import org.springframework.data.domain.*;

public interface IUsersService {
    UserResponseDTO CreateUser (CreateUserRequestDTO createUserRequestDTO);
    Page<UserResponseDTO> listUsers (Pageable pageable);
    UserResponseDTO findByUsername(String username);
}
