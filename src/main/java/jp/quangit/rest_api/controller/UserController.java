package jp.quangit.rest_api.controller;

import jp.quangit.rest_api.domain.User;
import jp.quangit.rest_api.repository.UserRepository;
import jp.quangit.rest_api.service.UserService;
import jp.quangit.rest_api.service.error.IdInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //get all users
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> fetchAllUser(){
        List<User> users = new ArrayList<>();
        users = this.userService.fetchAllUser() != null? this.userService.fetchAllUser(): users ;
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    //get user by id
    @GetMapping("/api/users/{id}")
    public  ResponseEntity<User> fetchUserById(@PathVariable("id") long id){
        User user = this.userService.fetchUserById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(user);
    }

    //create new user
    @PostMapping("/api/users/create")
    public ResponseEntity<User> createNewUser1(@RequestBody User user){
       User newUser = this.userService.handleSaveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    //update user
    @PutMapping("/api/users")
    public  ResponseEntity<User> updateUser(@RequestBody User user){
        User existUser = this.userService.fetchUserById(user.getId());
        if(existUser != null){
            existUser.setName(user.getName());
            existUser.setEmail(user.getEmail());
            existUser.setPassword(user.getPassword());
        }
        this.userService.handleSaveUser(existUser);
        return  ResponseEntity.ok(existUser);
    }

    //delete user by id
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id)
    throws IdInvalidException
    {
        if(id >= 1000) {throw new IdInvalidException("id lon the");}
        this.userService.handleDeleteUser(id);
//    return  ResponseEntity.status(HttpStatus.OK).body("deleted!");
        return ResponseEntity.ok("deleted");
    }
}
