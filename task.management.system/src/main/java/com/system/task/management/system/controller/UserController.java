package com.system.task.management.system.controller;
import com.system.task.management.system.dto.UserDTO;
import com.system.task.management.system.dto.requestdto.RequestUserSaveDto;
import com.system.task.management.system.service.UserService;
import com.system.task.management.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<StandardResponse> signUp(@RequestBody RequestUserSaveDto userDTO) throws IOException {
        userService.createUser(userDTO);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "User Saved",
                        userDTO
                ), HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/verify", params = {"type"})
    public ResponseEntity<StandardResponse> verifyUser(
            @RequestParam String type,
            @RequestHeader("Authorization") String token
    ) {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "User State",
                        userService.verifyUser(type, token)
                ), HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<StandardResponse> getAllUsers(){
        List<UserDTO> userDTOList = userService.getAllUsers();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Users",
                        userDTOList
                ),
                HttpStatus.OK
        );
    }

}
