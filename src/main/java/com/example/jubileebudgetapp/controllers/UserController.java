package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.AccountDto;
import com.example.jubileebudgetapp.dtos.UserDto;
import com.example.jubileebudgetapp.services.UserService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> userDtos = userService.getUsers();
        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username){
        UserDto userDto = userService.getUser(username);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUserDto = userService.createUser(userDto);
        userService.addAuthority(createdUserDto.getUsername(), "ROLE_USER");

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(createdUserDto.getUsername())
                .toUriString());

        return ResponseEntity.created(uri).body(createdUserDto);
    }

    @RequestMapping(value = "/{username}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto updatedUserDto){
        UserDto updatedUser = userService.updateUser(username, updatedUserDto);
        return ResponseEntity.ok(updatedUser);
    }

}
