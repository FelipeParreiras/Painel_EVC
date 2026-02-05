package com.evcvistoria.gestao.users.models.dto;

import com.evcvistoria.gestao.roles.models.enums.Role;

import java.time.Instant;
import java.util.Set;

public record UserResponseDTO(
        String username,
        String email,
        Instant createdAt,
        boolean enabled,
        Set<Role> roles
) {
}
