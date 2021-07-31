package com.nnk.springboot.dto;

import lombok.*;
import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/*
  Lombok:
    @FieldDefaults(level=AccessLevel.PRIVATE) : passe tous les champs en private ;
    @NoArgsConstructor : génère le constructeur sans argument et public ;
    @AllArgsConstructor : génère le constructeur avec tous les arguments et public (pour l'exemple) ;
    @Getter : génère tous les getters sur les champs ;
    @Setter : génère tous les setters sur les champs ;
    @EqualsAndHashCode(of=...) : génère equals et hashCode (et d'autres méthodes) sur les champs donnés ;
    @ToString(of=...) : génère toString sur les champs donnés.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp ="[a-zA-Z\\+\\-\\+]{2,125}",
             message = "the name must contain at least 2 characters")
    private String username;


    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp ="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,125}",
            message = "The password must be at least 8 characters long including: " +
                             "1 uppercase letter, 1 number, 1 symbol (@ # $% ^ & + =)")
    private String password;


    @NotBlank(message = "FullName is mandatory")
    @Pattern(regexp ="[a-zA-Z\\+\\-\\+]{2,125}",
            message = "the name must contain at least 2 characters")
    private String fullname;


    @NotBlank(message = "Role is mandatory")
    private String role;

    //---------------------------------------------------------------------------------------------------------------------------------------

    public UserDTO(String username, String password, String fullname, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------
}
