package com.ly.p7.Poseidon.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Username is mandatory")
    private String username;


    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp ="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",
            message = "The password must be at least 8 characters long including: " +
                             "1 uppercase letter, 1 number, 1 symbol (@ # $% ^ & + =)")
    private String password;


    @NotBlank(message = "FullName is mandatory")
    private String fullname;


    @NotBlank(message = "Role is mandatory")
    private String role;

    //---------------------------------------------------------------------------------------------------------------------------------------
}
