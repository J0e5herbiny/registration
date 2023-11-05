package com.joe.project.controller;

import com.joe.project.dto.UserDto;
import com.joe.project.service.UserSecurityService;
import com.joe.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserJsonController {

    private final UserService userService;

    @Autowired
    public UserJsonController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers(){
        return userService.readAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable("userId") Long id){
        return userService.readUserById(id);
    }

//    @GetMapping("/{userName}")
//    public UserDto getUserByName(@PathVariable("userName") String userName){
//        return userService.readUserByUserName(userName);
//    }

    @PostMapping
    public void postUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
    }

    @PutMapping("/{userId}")
    public void putUser(@PathVariable("userId") Long id,
                        @RequestBody UserDto userDto){
        userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long id){
        userService.deleteUser(id);
    }

}
