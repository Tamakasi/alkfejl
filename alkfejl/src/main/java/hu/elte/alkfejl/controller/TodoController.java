package hu.elte.alkfejl.controller;

import hu.elte.alkfejl.annotation.Role;
import hu.elte.alkfejl.entity.Comment;
import hu.elte.alkfejl.entity.Todo;
import hu.elte.alkfejl.entity.User;
import hu.elte.alkfejl.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import hu.elte.alkfejl.repository.TaskRepository;

@Controller
@RequestMapping("/todo")
public class TodoController {
    
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TaskRepository commentRepository;
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @GetMapping("/list")
    public String list(Model model) {
        Todo newTodo = new Todo();
        Iterable<Todo> list = todoRepository.findAll();
        model.addAttribute("todos", list);
        model.addAttribute("newTodo", newTodo);
        return "todolist";
    }
    
    @Role({User.Role.ADMIN})
    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo newTodo) {
        todoRepository.save(newTodo);
        return "redirect:/todo/list";
    }
    
    @PostMapping("/addcomment")
    public String addComment(@RequestBody Comment newComment) {
        //commentRepository.save(newComment);
        return "redirect:/todo/list";
    }
}
