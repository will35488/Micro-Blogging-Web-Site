/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */

@Entity
public class Follow implements Serializable{
    
    
    private Long followId;
    private User user;
    private User followedUser;    
    
    
    public Follow(){
        
    
    
    }
    
    public Follow(User user, User followedUser){
        this.user = user;
        this.followedUser = followedUser;
        
    
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getFollowId() {
        return followId;
    }
    
    @ManyToOne
    public User getUser() {
        return user;
    }
    
    @ManyToOne
    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }
    
    }
