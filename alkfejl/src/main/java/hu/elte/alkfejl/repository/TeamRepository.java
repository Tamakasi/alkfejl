/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.repository;

import hu.elte.alkfejl.entity.Folder;
import hu.elte.alkfejl.entity.Team;
import hu.elte.alkfejl.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>{
    
    Optional<Team> findById(Long id);
    
    //List<Team> listByUser(User user, Team team);
    
    //List<User> listMembers(User user, Team team);
    //List<Group> listGroups(User user, Team team);
    
    //void create(User user, String name, String description);
    
    //void update(User user, String name, String description);
    
    void delete(Team team);
    
    //void invite(User inviter, Team team, User invited);
    
    //void join(User user, Team team);

    //public ResponseEntity findAllByUser(User user);
    
    
    
}
