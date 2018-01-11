package hu.elte.alkfejl.repository;

import hu.elte.alkfejl.entity.Folder;
import hu.elte.alkfejl.entity.Task;
import hu.elte.alkfejl.entity.User;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    
    //List<Task> list();
    
    Optional<Task> findById(Long id);
    
    Task save(Task t);
    
    //Iterable<Task> findAllByUser(User user);
    
    void delete(Task task);
    
    //void edit(User user, Task task, Timestamp deadline, String description, int priority);
    
    //void complete(User user, Task task);
    
    //void pause(User user, Task task);
    
    /**
     * mozgatni taskok a többi task között?
     * bullshit, majd kitaláljuk
     * pl:  -2: 2 taskkal előrébb,
     *      1: 1 taskkal hátrébb
     *      0: helyben marad
     */
    //void move(User user, Task task, int direction);
    
    //elhalaszt
    //void stall(User user, Task task);
}
