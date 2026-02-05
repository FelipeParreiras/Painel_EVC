package com.evcvistoria.gestao.roles.repositorys;

import com.evcvistoria.gestao.roles.models.RoleEntity;
import com.evcvistoria.gestao.roles.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(Role name);
}