package com.seaz.proyectospringboot.controller;

import com.seaz.proyectospringboot.entity.User;
import com.seaz.proyectospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    //find a user

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserId(@PathVariable(value = "id") Long userId){
        Optional<User> user = userService.findById(userId);
        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    //Update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user,@PathVariable(value = "id") Long userId){
        Optional<User> Dbuser = userService.findById(userId);
        if(!Dbuser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Dbuser.get().setName(user.getName());
        Dbuser.get().setLastname(user.getLastname());
        Dbuser.get().setEmail(user.getEmail());
        Dbuser.get().setEnable(user.getEnable());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(Dbuser.get()));
    }
    //Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId){

        if(!userService.findById(userId).isPresent()){
            return ResponseEntity.notFound().build();
        }
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }
    //read all user
    @GetMapping
    public List<User> readAll () {
        List<User> users = StreamSupport
                .stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }
}
