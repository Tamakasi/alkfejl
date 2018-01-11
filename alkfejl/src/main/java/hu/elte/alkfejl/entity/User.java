package hu.elte.alkfejl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.elte.alkfejl.annotation.Role;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
public class User extends BaseEntity {
    
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = Role.USER;
        this.folders.add(new Folder("green"));
    }
    
    @Column(unique=true, nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String password;
    
    @Column(unique=true, nullable = false)
    private String email;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,
                targetEntity = Folder.class, 
               cascade = CascadeType.ALL)
    private List<Folder> folders = new ArrayList<Folder>();
    
    @JsonIgnore
    @ManyToMany(targetEntity = Team.class,
                cascade = CascadeType.ALL)
    private List<Team> teams = new ArrayList<Team>(); 
    
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public enum Role {
        GUEST, USER, ADMIN
    }
    
    @Override
    public String toString() {
        String result = String.format(
                "User[id=%d, name='%s', password = '%s', email = '%s']%n",
                this.getId(), name, password, email);
        if (folders != null) {
            for(Folder folder : folders) {
                result += String.format(
                        "Folder[id=%d, name='%s']%n",
                        folder.getId(), folder.getName());
            }
        }
        if (teams != null) {
            for(Team team : teams) {
                result += String.format(
                        "Team[id=%d, name='%s']%n",
                        team.getId(), team.getName());
            }
        }

        return result;
    }
    
}
