package com.system.task.management.system.service;

import com.system.task.management.system.dto.responsedto.ResponseUserRoleDTO;
import com.system.task.management.system.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface UserRoleService {
    public void initializeUserRoles();

    List<ResponseUserRoleDTO> findAllUserRole();

    CommonResponseDTO deleteUserRole(String userRoleId);
}
