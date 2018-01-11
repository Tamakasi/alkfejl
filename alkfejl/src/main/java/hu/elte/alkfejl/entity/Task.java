/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Cacheable(false)
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {
   
    
    public Task(Task t) {
        this.state = "created";
        this.description = t.getDescription();
        this.priority = t.getPriority();
    }
    
    public Task(String description) {
        this.state = "created";
        this.description = description;
    }
    
    public Task(String description, int priority) {
        this.state = "created";
        this.description = description;
        this.priority = priority;
    }
    
    public Task(String description, int priority, Timestamp deadline) {
        this.state = "created";
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
    }
    //java.sql.Timestamp, de lehet változtatni kell még
    @Column(nullable = false)
    private Timestamp deadline;
    
    @Column(nullable = false)
    private String state;
    
    @Column
    private String description;
    
    @Column(nullable = false)
    private int priority = 5;
    
    @Column(nullable = false)
    private boolean deleted = false;
}
