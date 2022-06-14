package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int id;
    private String lastname;
    private String firstname;
    private String username;
    private String password;

    public User(String lastname, String firstname, String username, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
        this.password = password;
    }
}
