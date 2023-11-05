package com.joe.project.service;

import com.joe.project.dto.UserDto;
import com.joe.project.entity.User;
import com.joe.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void createUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getRoles()
                );
        userRepository.save(user);
    }

    public List<UserDto> readAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for ( User user : users ) {
//            System.out.println(user.userDto());
            userDtos.add(user.userDto());

        }
        return userDtos;
    }


    public UserDto readUserById(Long id){
        return userRepository.findById(id).get().userDto();
    }

    public UserDto readUserByUserName(String userName){
        return userRepository.findByUserName(userName).userDto();
    }

//    @Transactional
    public void updateUser(Long id, UserDto userDto){
        User user = userRepository.findById(id).
                orElseThrow(() -> new IllegalStateException("User with ID : "+id+", is not Found !"));

//        System.out.println(user.getUserName()+" not updated to "+userDto.getUsername());
        user.setUserName(userDto.getUsername());
//        System.out.println(" updated ! -> "+user.getUserName());

        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
//        user.setRoles(userDto.getRoles());
        userRepository.save(user);

    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }


}
