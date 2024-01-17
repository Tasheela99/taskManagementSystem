package com.system.task.management.system.util.mapper;

import com.system.task.management.system.dto.UserDTO;
import com.system.task.management.system.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDto(User user);
    User toUser(UserDTO userDTO);

}
