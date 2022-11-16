package com.niit.jwt.userauthentication.model;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {
    @Id
    private String userName;
    private String password;
    private String email;
}
