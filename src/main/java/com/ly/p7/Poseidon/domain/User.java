package com.ly.p7.Poseidon.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Username is mandatory")
    @Length(min = 1, message = "trop court")
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp ="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",
                message = "The password must be at least 8 characters long including: 1 uppercase letter, 1 number, 1 symbol (@ # $% ^ & + =)")
    private String password;
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
