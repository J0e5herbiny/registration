package com.joe.project.service;

import com.joe.project.crm.CrmUser;
//import com.joe.project.dao.RoleRepository;
//import com.joe.project.dao.UserRepository;
import com.joe.project.dto.UserDto;
import com.joe.project.entity.Role;
import com.joe.project.entity.User;
import com.joe.project.repository.RoleRepository;
import com.joe.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserDto readUserDtoByUserName(String userName){
        User user = userRepository.findByUserName(userName);

        return user.userDto();
    }

    public User readUserByUserName(String userName){
        User user = userRepository.findByUserName(userName);

        return user;
    }

    @Transactional
    public void create(CrmUser crmUser){
        User user = new User();

        user.setUserName(crmUser.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(crmUser.getPassword()));
        user.setFirstName(crmUser.getFirstName());
        user.setLastName(crmUser.getLastName());
        user.setEmail(crmUser.getEmail());
        // Default role is EMPLOYEE
        user.setRoles(Arrays.asList(roleRepository.findRoleByName("ROLE_EMPLOYEE")));

        userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuth(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password !");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                mapRolesToAuth(user.getRoles()));
    }

}
