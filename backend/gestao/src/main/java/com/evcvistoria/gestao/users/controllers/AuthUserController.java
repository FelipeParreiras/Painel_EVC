package com.evcvistoria.gestao.users.controllers;

import com.evcvistoria.gestao.users.models.dto.UserLoginRequestDTO;
import com.evcvistoria.gestao.users.models.dto.UserLoginResponseDTO;
import com.evcvistoria.gestao.users.services.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authusers")
public class AuthUserController {

    private final AuthUserService authUserService;

    @PostMapping("/login")
    public UserLoginResponseDTO UserLogin(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        return authUserService.LoginUser(userLoginRequestDTO);
    }

}
