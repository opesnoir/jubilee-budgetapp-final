package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.UserDto;
import com.example.jubileebudgetapp.models.Authority;
import com.example.jubileebudgetapp.models.User;
import com.example.jubileebudgetapp.repositories.UserRepository;
import com.example.jubileebudgetapp.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers(){
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(convertUserToDto(user));
        }
        return collection;
    }

    public UserDto getUser(String username){
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return convertUserToDto(user);
    }

    public UserDto createUser(UserDto userDto){
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        userDto.setEnabled(true);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User newUser = userRepository.save(convertUserDtoToUser(userDto));

        return convertUserToDto(newUser);
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }

    public UserDto updateUser(String username, UserDto updatedUserDto){
        User existingUser = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        updateUserFromDto(existingUser, updatedUserDto);
        User updatedUser = userRepository.save(existingUser);

        return convertUserToDto(updatedUser);
    }

    // authority methods
    public Set<Authority> getAuthorities(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        UserDto userDto = convertUserToDto(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Authority authorityToRemove = user.getAuthorities().stream()
                .filter((a) -> a.getAuthority().equalsIgnoreCase(authority))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Authority not found: " + authority));

        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    //helper methods
    public User convertUserDtoToUser(UserDto userDto){
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());

        return user;
    }

    public static UserDto convertUserToDto(User user){
        UserDto userDto = new UserDto();

        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.email = user.getEmail();
        userDto.enabled = user.isEnabled();
        userDto.apikey = user.getApikey();
        userDto.authorities = user.getAuthorities();

        return userDto;
    }

    public void updateUserFromDto(User existingUser, UserDto updatedUserDto){
        if(updatedUserDto.getPassword() != null){
            existingUser.setPassword(passwordEncoder.encode(updatedUserDto.getPassword()));
        }
        if (updatedUserDto.getEmail() != null) {
            existingUser.setEmail(updatedUserDto.getEmail());
        }
    }

}
