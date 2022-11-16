package com.niit.jwt.userauthentication.repository;

import com.niit.jwt.userauthentication.exception.UserNotFoundException;
import com.niit.jwt.userauthentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

     User findByUserName(String userName);
     User findByUserNameAndPassword(String userName,String password);
     User findByEmail(String email);

}
