package com.evcvistoria.gestao.configs;

import com.evcvistoria.gestao.roles.models.RoleEntity;
import com.evcvistoria.gestao.roles.models.enums.Role;
import com.evcvistoria.gestao.roles.repositorys.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        Arrays.stream(Role.values()).forEach(r -> {
            roleRepository.findByName(r).orElseGet(() -> roleRepository.save(new RoleEntity(r)));
        });
    }
}
