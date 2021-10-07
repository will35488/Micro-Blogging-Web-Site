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
public class Chirp implements Serializable{
    
    
    private Long chirpId;
    private User user;
    private String text;    
    private Date chirpDate;
    private byte[] image;
    private String filename;
    
    public Chirp(){
        //userId="";
        //text="";
        //chirpDate="";
    
    
    }
    
    public Chirp(User user, String text){
        this.user = user;
        this.text = text;
        chirpDate= new java.util.Date();
        filename = "blank";
    
    }
    
    public Chirp(User user, String text, byte[] image, String filename){
        this.user = user;
        this.text = text;
        chirpDate= new java.util.Date();
        this.image=image;
        this.filename = filename;
    }
    
    public Chirp(User user, String text, String filename){
        this.user = user;
        this.text = text;
        chirpDate= new java.util.Date();
        
        this.filename = filename;
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getChirpId() {
        return chirpId;
    }
    
    @ManyToOne
    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date getChirpDate() {
        return chirpDate;
    }

    public void setChirpId(Long chirpId) {
        this.chirpId = chirpId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setChirpDate(Date chirpDate) {
        this.chirpDate = chirpDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    
    
    
    
    }
    
    

