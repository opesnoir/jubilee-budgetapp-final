package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.AccountDto;
import com.example.jubileebudgetapp.dtos.UserDto;
import com.example.jubileebudgetapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username){
        UserDto userDto = userService.getUser(username);
        if (userDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(UserDto userDto, AccountDto accountDto){
        UserDto createdUserDto = userService.createUser(userDto, accountDto);
        userService.addAuthority(createdUserDto.getUsername(), "ROLE_USER");

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(createdUserDto.getUsername())
                .toUriString());

        return ResponseEntity.created(uri).body(createdUserDto);
    }

}
