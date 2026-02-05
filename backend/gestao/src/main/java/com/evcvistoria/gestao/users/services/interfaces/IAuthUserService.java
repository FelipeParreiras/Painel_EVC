package com.evcvistoria.gestao.users.services.interfaces;

import com.evcvistoria.gestao.users.models.dto.UserLoginRequestDTO;
import com.evcvistoria.gestao.users.models.dto.UserLoginResponseDTO;

public interface IAuthUserService {

    UserLoginResponseDTO LoginUser (UserLoginRequestDTO userLoginRequestDTO);
}
