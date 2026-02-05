package com.evcvistoria.gestao.users.controllers;

import com.evcvistoria.gestao.roles.models.enums.Role;
import com.evcvistoria.gestao.users.models.User;
import com.evcvistoria.gestao.users.models.dto.CreateUserRequestDTO;
import com.evcvistoria.gestao.users.models.dto.UserResponseDTO;
import com.evcvistoria.gestao.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/create-user")
    public UserResponseDTO CreateUser(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam Set<Role> roles) {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO(username, email, password, roles);
        return usersService.CreateUser(createUserRequestDTO);
    }

    @GetMapping("/list-all")
    public Page<UserResponseDTO> listUsers(Pageable pageable) {
        return usersService.listUsers(pageable);
    }

    @GetMapping("/findbyusername/{username}")
    public UserResponseDTO FindByUsername(@PathVariable String username) {
        return usersService.findByUsername(username);
    }

}
