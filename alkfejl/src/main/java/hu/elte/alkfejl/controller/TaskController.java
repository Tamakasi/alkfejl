/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.controller;

import form.FolderAndTask;
import form.UserBody;
import form.completeBody;
import hu.elte.alkfejl.entity.Folder;
import hu.elte.alkfejl.entity.Task;
import hu.elte.alkfejl.entity.User;
import hu.elte.alkfejl.repository.FolderRepository;
import hu.elte.alkfejl.repository.TaskRepository;
import hu.elte.alkfejl.repository.TeamRepository;
import hu.elte.alkfejl.repository.UserRepository;
import hu.elte.alkfejl.service.UserService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/task/")
public class TaskController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping(value="/add",  method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody FolderAndTask fnt) throws ParseException{
        Timestamp ts = timestampConvert(fnt.getDeadline());
        Task t = new Task(fnt.getDescription(), fnt.getPriority(), ts);
        
        Folder f = folderRepository.findOne(fnt.getFolder().getId());
        f.addTask(t);
        folderRepository.save(f);
        
        Task newTask = taskRepository.save(t);
        return ResponseEntity.ok(this.taskRepository.findOne(newTask.getId()));
        
    }
    
    private Timestamp timestampConvert(String d) throws ParseException {
        System.out.println(d);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date parsedDate = dateFormat.parse(d);
        System.out.println(parsedDate);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        System.out.println(timestamp);
        
        return timestamp;
    }
    
    @ResponseBody
    @RequestMapping(value="/list",  method = RequestMethod.GET)
    public ResponseEntity tasks(){
        
        List<Folder> folders = userService.getUser().getFolders();
        List<Folder> notDeletedFolders = new LinkedList<Folder>();
        for(Folder f : folders) {
            if(!f.isDeleted()) {
                notDeletedFolders.add(f);
                System.out.println(f.getId());
            }
        }
        List<Task> tasks = new LinkedList<Task>();        
        for(Folder f: notDeletedFolders) {
            tasks.addAll(f.getTasks());
        }
        
        return ResponseEntity.ok(tasks); 
    }
    
    @ResponseBody
    @RequestMapping(value="/listbyfolder",  method = RequestMethod.GET)
    public ResponseEntity tasks(@RequestParam(value="id") Long id){
        Folder f = folderRepository.findOne(id);
        List<Task> tasks = f.getTasks();
        for(User u : this.userRepository.findAll()) {
            if(u.getFolders().contains(f)) {
                if(u.getId() == this.userService.getUser().getId()) {
                    return ResponseEntity.ok(tasks);
                }
            }
        }
                
         return ResponseEntity.badRequest().body("Not authorized");
    }
    
    
    @ResponseBody
    @RequestMapping(value="/complete",  method = RequestMethod.POST)
    public ResponseEntity completeTask(@RequestBody completeBody cb){
        Task t = this.taskRepository.findOne(Long.parseLong(cb.getId()+""));
        t.setState(cb.getMessage());
        Task nt = taskRepository.save(t);
        
        return ResponseEntity.ok(nt); 
    }
    
    
    
    
}

