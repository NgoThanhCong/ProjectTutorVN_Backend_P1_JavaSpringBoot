package com.greenwich.tutorvn.controller;

import com.greenwich.tutorvn.model.ResponseObject;
import com.greenwich.tutorvn.model.User;
import com.greenwich.tutorvn.repository.RoleRepository;
import com.greenwich.tutorvn.repository.UserRepository;
import com.greenwich.tutorvn.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController  // *khai bao anotation restcontroller de tao ra 1 class Rest API
@RequestMapping(path  = "/api/user")  // *tao ra URL cos dang /api/
@CrossOrigin(origins = "http://localhost:3000,http://localhost:3002", maxAge = 3600)
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;



    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
//    @Autowired
//    JwtUtils jwtUtils;

    @Autowired
    JwtUtils jwtProvider;

    @GetMapping("/changePassWord")
    public ResponseEntity<?> changePassword(@RequestParam String oldPassword,
                                            @RequestParam String newPassword,
                                            @RequestHeader("Authorization") String token)
    {
        System.out.println("Token-Full::"+ token);
        Optional<User> optionalUser = null;
        String jwt = token.split(" ")[1];
        System.out.println("Token:"+ jwt);
        if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
            String username = jwtProvider.getUserNameFromJwtToken(jwt);
            System.out.println("username:"+ username);
            optionalUser = userRepository.findByUserName(username);
        }
        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            System.out.println("User:"+ user.getPassWord());
            System.out.println("Encode:"+ encoder.encode(oldPassword));

            if(encoder.encode(oldPassword).equals(user.getPassWord())){
                System.out.println("Oke Done");

            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), oldPassword));
            if(authentication.isAuthenticated()){
                user.setPassWord(encoder.encode(newPassword));
                User returnUser =   userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Change password successful", returnUser));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject(201,"Old password is incorrect", ""));
            }
        }else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(201,"User update not found by ID", ""));
        }
    }
}
