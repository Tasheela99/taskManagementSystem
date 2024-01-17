package com.system.task.management.system.service;

import com.system.task.management.system.dto.UserDTO;
import com.system.task.management.system.dto.requestdto.RequestUserSaveDto;
import com.system.task.management.system.dto.responsedto.ResponseUserDataDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void initializeUser() throws IOException;
    ResponseUserDataDTO getAllUserData(String token);
    void createUser(RequestUserSaveDto userDTO);
    boolean verifyUser(String type, String token);

    List<UserDTO> getAllUsers();
}
