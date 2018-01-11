/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.controller;

import form.FolderAndTask;
import form.FolderBody;
import hu.elte.alkfejl.annotation.Role;
import hu.elte.alkfejl.entity.Folder;
import hu.elte.alkfejl.entity.Task;
import hu.elte.alkfejl.entity.Team;
import hu.elte.alkfejl.entity.User;
import hu.elte.alkfejl.repository.FolderRepository;
import hu.elte.alkfejl.repository.TeamRepository;
import hu.elte.alkfejl.repository.UserRepository;
import hu.elte.alkfejl.service.UserService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/folder/")
public class FolderController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping(value="/add",  method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody FolderBody fb) throws ParseException{
        Folder f = new Folder(fb.getName(), fb.getDescription(), fb.getColor());
        User u = this.userRepository.findOne(this.userService.getUser().getId());
        
        u.getFolders().add(f);
        this.userRepository.save(u);
        
        //Folder nf = folderRepository.save(f);
        //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA"+nf);
        
        
        return ResponseEntity.ok(u);
        
    }
    
    @PostMapping(value="/delete")
    @ResponseBody
    @Role({User.Role.USER, User.Role.ADMIN})
    public ResponseEntity delete(
        @RequestParam(  value = "folder", required = true )Long folderId
    ) {
        User user = null;
        user = userRepository.findById(userService.getUser().getId()).get();

        Folder folder = null;
        Optional<Folder> optFolder = folderRepository.findById(folderId);
        if(optFolder.isPresent()) {
            folder = optFolder.get();
        }

        if( user != null && folder != null) {
            folder.setDeleted(true);
            folderRepository.save(folder);
            return ResponseEntity.ok(folder);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(value="/update")
    @ResponseBody
    @Role({User.Role.USER, User.Role.ADMIN})
    public ResponseEntity update(
        @RequestParam(  value = "folder", required = true )Long folderId,
        @RequestParam(  value = "name", required = false )String name,
        @RequestParam(  value = "description", required = false )String description,
        @RequestParam(  value = "color", required = false )String color
    ) {
        
        Folder folder = null;
        Optional<Folder> optFolder = folderRepository.findById(folderId);
        if(optFolder.isPresent()) {
            folder = optFolder.get();
        }
        
        if(folder != null) {
            if(userService.getUser().getFolders().contains(folder) ||
                    folderInTeam(userService.getUser(), folder)) {
                
                if(name != null) folder.setName(name);
                if(description != null) folder.setDescription(description);
                if(color != null) folder.setColor(color);
                
                Folder f = folderRepository.save(folder);
                
                return ResponseEntity.ok(folder);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    private boolean folderInTeam(User user, Folder folder) {
        boolean found = false;
        for(Team team : user.getTeams()) {
            if(team.getFolders().contains(folder)) found = true;
        }
        
        return found;
    }
    
    @RequestMapping(value="/list")
    @ResponseBody
    @Role({User.Role.USER, User.Role.ADMIN})
    public ResponseEntity list() {
        List<Folder> folders = userService.getUser().getFolders();
        List<Folder> notDeletedFolders = new LinkedList<Folder>();
        for(Folder f : folders) {
            if(!f.isDeleted()) {
                notDeletedFolders.add(f);
                System.out.println(f.getId());
            }
        }

        return ResponseEntity.ok(notDeletedFolders);
    }


}
