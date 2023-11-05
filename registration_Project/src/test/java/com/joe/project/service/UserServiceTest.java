package com.joe.project.service;

//import org.junit.Test;

import com.joe.project.entity.User;
import com.joe.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userServiceTest;

    @BeforeEach
    void setUp() {
         MockitoAnnotations.initMocks(this);
        userServiceTest= new UserService(userRepository);
    }

    @Test
//    @Disabled
    public void createUser() {
        // Given
        User user= new User(
                "userName",
                "password",
                "fName",
                "lName",
                "email@email"
        );

        // When
        userServiceTest.createUser(user.userDto());

        // Then
        ArgumentCaptor<User> userArgumentCaptor= ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User expectedUserValue = userArgumentCaptor.getValue();
        assertThat(expectedUserValue).isEqualTo(user);
    }

    @Test
    public void readAllUsers() {
        //when
        userServiceTest.readAllUsers();
        //then
        verify(userRepository).findAll();
    }

    @Test
    @Disabled
    public void readUserById() {
    }

    @Test
    @Disabled
    public void readUserByUserName() {
    }

    @Test
    @Disabled
    public void updateUser() {
    }

    @Test
    @Disabled
    public void deleteUser() {
    }
}