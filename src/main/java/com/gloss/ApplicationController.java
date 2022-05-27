package com.gloss;

import com.gloss.model.User;
import com.gloss.model.UserDto;
import com.gloss.service.EmailAddressService;
import com.gloss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApplicationController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailAddressService emailAddressService;

    @PostMapping("/users")
    public UserDto addUser(@RequestBody UserDto userDto){
        return asDto(
                userService.addUser(
                        userDto.getFirstName(), userDto.getLastName(),
                        userDto.getUsername(), userDto.getPassword()));

    }

    @GetMapping("/users")
    public List<UserDto> getUsers(@RequestParam(value = "sortBy", required = false) String sortBy){
        return userService.getUsers(sortBy).stream().map(this::asDto).collect(Collectors.toList());
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable("id") Integer id,
                              @RequestBody UserDto userDto){
        return userService
                .updateUser(id,userDto.getUsername(),userDto.getPassword(),
                        userDto.getFirstName(),userDto.getLastName())
                .map(this::asDto)
                .orElseThrow();
    }

    @DeleteMapping("/customers/{id}")
    public void deleteUser(@PathVariable("id") Integer id) { userService.deleteUser(id); }

    public UserDto asDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }



}
