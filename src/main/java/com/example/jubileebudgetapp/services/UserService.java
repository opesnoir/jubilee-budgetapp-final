package com.example.jubileebudgetapp.services;


import com.example.jubileebudgetapp.dtos.UserDto;
import com.example.jubileebudgetapp.models.User;
import com.example.jubileebudgetapp.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public UserDto convertUserToDto(User user){
        UserDto userDto = new UserDto();

        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.email = user.getEmail();
        userDto.enabled = user.isEnabled();
        userDto.apikey = user.getApikey();
        userDto.authorities = user.getAuthorities();

        return userDto;
    }

    public void updateUserFromDto(String username, UserDto updatedUserDto){
        if (!userRepository.existsById(username)){
            throw new UsernameNotFoundException(username);
        }

        User user = userRepository.findById(username).get();
        if (updatedUserDto.getPassword() != null) {
            user.setPassword(updatedUserDto.getPassword());
        }
        if (updatedUserDto.getEmail() != null) {
            user.setEmail(updatedUserDto.getEmail());
        }
        userRepository.save(user);
    }

}
