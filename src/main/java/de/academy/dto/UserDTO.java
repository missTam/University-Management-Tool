package de.academy.dto;

import de.academy.validation.EmailPattern;
import de.academy.validation.FieldMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// compare 1 pair of fields:
@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword")
})
public class UserDTO {

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstname;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastname;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @EmailPattern
    private String email;

    public UserDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
