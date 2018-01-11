/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.controller;

import form.UserBody;
import hu.elte.alkfejl.annotation.Role;
import hu.elte.alkfejl.entity.Folder;
import hu.elte.alkfejl.entity.User;
import hu.elte.alkfejl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import hu.elte.alkfejl.repository.FolderRepository;
import hu.elte.alkfejl.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private UserService userService;
    
    
    @GetMapping(value="/gettt")
    @ResponseBody
    @Role({User.Role.USER, User.Role.ADMIN})
    public ResponseEntity getUserthing(
        ) {
            
            List<Folder> folders = userService.getUser().getFolders();
            for(Folder f : folders) {
                if(!f.isDeleted()) folders.remove(f);
            }
            
            return ResponseEntity.ok(folders);

    }
    
    @RequestMapping(value="/edit",  method = RequestMethod.POST)
    @ResponseBody
    @Role({User.Role.USER, User.Role.ADMIN})
    public ResponseEntity editAccount(
        @RequestBody UserBody payload
        ) {
            User user = null;
            String name = payload.getName();
            String email = payload.getEmail();
            String password = payload.getPassword();
            
            Optional<User> optuser = userRepository.findById(this.userService.getUser().getId());
            if(optuser.isPresent()) {
                user = optuser.get();
            }
            if(user != null) {
                if(!name.equals(user.getName())) user.setName(name);
                if(!password.equals(user.getPassword())) user.setPassword(password);
                if(!email.equals(user.getEmail())) user.setEmail(email);

                User endUser = userRepository.save(user);
                return ResponseEntity.ok(endUser);
            } else {
                return null;
            }
    }
    
    @ResponseBody
    @RequestMapping(value="/register",  method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody UserBody payload){
        String name = payload.getName();
        String email = payload.getEmail();
        String password = payload.getPassword();

        return userService.register(name, password, email);
    }
    
}

 
