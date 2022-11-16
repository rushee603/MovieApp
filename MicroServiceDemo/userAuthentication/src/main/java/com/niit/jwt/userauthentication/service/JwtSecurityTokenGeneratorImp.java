package com.niit.jwt.userauthentication.service;

import com.niit.jwt.userauthentication.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGeneratorImp implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken=null;
        jwtToken= Jwts.builder().setSubject(user.getUserName()).setIssuedAt(new Date())
                  .signWith(SignatureAlgorithm.HS256,"secretKey")
                  .compact();

        Map<String,String > map=new HashMap<>();
        map.put("token",jwtToken);
        map.put("message","User Successfully LoggedIn.......");
        map.put("userName", user.getUserName()) ;
        map.put("email",user.getEmail()) ;
        map.put("password",user.getPassword()) ;
        return map;

    }
}
