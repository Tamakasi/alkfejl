/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Folder extends BaseEntity {
    
    public Folder(String color) {
        this.name = "default";
        this.description = "default";
        this.color=color;
    }
    
    public Folder(String name, String description, String color) {
        this.name = name;
        this.description = description;
        this.color=color;
    }
    
    public void addTask(Task t) {
        this.tasks.add(t);
    }
    
        
    @Column(nullable = false)
    private String name;
    
    @Column()
    private String description = "";
    
    @Column(nullable = false)
    private String color = "green";
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,
               targetEntity = Task.class, 
               cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<Task>();
    
    @Column(nullable = false)
    private boolean deleted = false;
    
}

