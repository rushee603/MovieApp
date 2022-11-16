package com.niit.usermovieservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    private String email;
    private String userName;
    @Transient
    private String password;
    private Long phoneNo;
    private List<Movie> favMovieList;

}


