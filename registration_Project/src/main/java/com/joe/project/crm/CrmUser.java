package com.joe.project.crm;

import com.joe.project.dto.UserDto;
import com.joe.project.entity.User;
import com.joe.project.validation.EmailValidation;
import com.joe.project.validation.FieldMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({ @FieldMatch(first = "password", second = "matchingPassword", message = "The passwords must be matched") })
public class CrmUser {

    @NotNull(message = "Is required")
    @Size(min = 1, message = "Is required")
    private String userName;

    @NotNull(message = "Is required")
    @Size(min = 1, message = "Is required")
    private String password;

    @NotNull(message = "Is required")
    @Size(min = 1, message = "Is required")
    private String matchingPassword;

    @NotNull(message = "Is required")
    @Size(min = 1, message = "Is required")
    private String firstName;

    @NotNull(message = "Is required")
    @Size(min = 1, message = "Is required")
    private String lastName;

    @NotNull(message = "Is required")
    @Size(min = 1, message = "Is required")
    @EmailValidation
    private String email;

    public CrmUser() {
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto userDto(){
        User user = new User(
                userName,
                password,
                firstName,
                lastName,
                email
        );
        return new UserDto(
                user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

}
