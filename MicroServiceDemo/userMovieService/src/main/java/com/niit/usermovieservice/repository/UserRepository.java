package com.niit.usermovieservice.repository;
import com.niit.usermovieservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
