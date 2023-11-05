package com.joe.project.repository;

import com.joe.project.entity.User;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//import static org.junit.Assert.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryTest;


    @After
    public void tearDown() throws Exception {
        userRepositoryTest.deleteAll();
    }

    @Test
    public void checkIfFindByUserName() {

        //Given
        User user= new User(
                "userName",
                "password",
                "fName",
                "lName",
                "email@email"
        );
        userRepositoryTest.save(user);
        //when
        User byUserName = userRepositoryTest.findByUserName(user.getUserName());
        //then
        assertThat(byUserName).isNotNull();

    }


    @Test
    public void checkIfFindByUserNamedoesNotExist() {

        //Given
        String name= "name";
        //when
        User byUserName = userRepositoryTest.findByUserName(name);
        //then
        assertThat(byUserName).isNull();

    }

}