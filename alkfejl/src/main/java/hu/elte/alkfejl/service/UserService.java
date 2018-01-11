/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.service;

import hu.elte.alkfejl.entity.User;
import hu.elte.alkfejl.annotation.Role;
import hu.elte.alkfejl.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@Data
@RequestMapping("api/us/")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private User user;

    @ResponseBody
    @PostMapping(value="/login")
    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            return this.user = userRepository.findByName(user.getName()).get();
        }
        throw new UserNotValidException();
    }
    
    @ResponseBody
    @PostMapping(value="/logout")
    @Role({User.Role.USER, User.Role.ADMIN})
    public boolean logout() throws UserNotValidException {
        this.user = null;
        return this.user == null;
    }

    public ResponseEntity register(String name, String password, String email) {
        User newUser = new User(name, password, email);
        newUser.setRole(User.Role.USER);
        if(!userRepository.findByName(name).isPresent()) {
            this.user = userRepository.save(newUser);
            return ResponseEntity.ok(this.user);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        
    }

    public boolean isValid(User user) {
        return userRepository.findByNameAndPassword(user.getName(), user.getPassword()).isPresent();
    }

    @ResponseBody
    @GetMapping(value="/log")
    public ResponseEntity isLoggedIn() {
        boolean r = this.user != null;
        return ResponseEntity.ok(r);
    }

    private static class UserNotValidException extends Exception {

        public UserNotValidException() {
            super("Not valid User");
        }
    }
}