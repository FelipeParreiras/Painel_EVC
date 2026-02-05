package com.evcvistoria.gestao.users.models.dto;

import com.evcvistoria.gestao.roles.models.enums.Role;

import java.util.Set;

public record CreateUserRequestDTO(
        String username,
        String email,
        String password,
        Set<Role> roles
) {
}
