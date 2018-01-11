/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Team extends BaseEntity {
    
    public Team(String name, String description) {
        this.name = name;
        this.description = description;
        folders.add(new Folder("green"));
    }
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String description;
    
    @JsonIgnore
    @OneToMany(targetEntity = Folder.class, 
               cascade = CascadeType.ALL)
    private List<Folder> folders = new ArrayList<Folder>();
    
    @Column(nullable = false)
    private boolean deleted = false;
}
