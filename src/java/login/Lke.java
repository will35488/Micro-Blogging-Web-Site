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
public class Lke implements Serializable{
    
    
    private Long likeId;
    private User user;
    private Chirp chirp;    
    
    
    public Lke(){
        
    
    
    }
    
    public Lke(User user, Chirp chirp){
        this.user = user;
        this.chirp = chirp;
        
    
    }
    
    
    
    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getLikeId() {
        return likeId;
    }
    
    @ManyToOne
    public User getUser() {
        return user;
    }
    
    @ManyToOne
    public Chirp getChirp() {
        return chirp;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChirp(Chirp chirp) {
        this.chirp = chirp;
    }
    
    
    

    
    
    
    
    }
