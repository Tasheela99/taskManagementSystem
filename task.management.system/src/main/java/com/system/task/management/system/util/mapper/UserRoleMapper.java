package com.system.task.management.system.util.mapper;

import com.system.task.management.system.dto.UserRoleDTO;
import com.system.task.management.system.dto.responsedto.ResponseUserRoleDTO;
import com.system.task.management.system.entity.UserRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDTO toUserRoleDto(UserRole userRole);

    UserRole toUserRole(UserRoleDTO userRoleDTO);

    List<ResponseUserRoleDTO> toUserRoleList(List<UserRole> all);

}
