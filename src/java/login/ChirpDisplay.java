/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class ChirpDisplay implements Comparable<ChirpDisplay>{
    private String text;
    private String name;
    private Date date;
    private int likecount;
    
    private Long chirpid;
    private Boolean likeable;
    private Boolean followable;
    private String filename;
    
    public ChirpDisplay(){
    
    
    }
    
    public ChirpDisplay(String name, String text, Date date, int likecount, Long chirpid, Boolean likeable, Boolean followable, String filename){
        this.name = name;
        this.text = text;
        this.date = date;
        this.likecount = likecount;
        
        this.chirpid = chirpid;
        this.likeable = likeable;
        this.followable = followable;
        this.filename = filename;
    
    }
    
    
    
    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }
    
    public static List<ChirpDisplay> getDisplayListForFollowed(User user){
        Long userid = user.getUserId();
        List<ChirpDisplay> displaylist= new ArrayList<>();
        List<Follow> followlist = FollowDB.selectFollowsByUserId(userid);
        
        if (!followlist.isEmpty()) {
            
        
            for (Follow follow : followlist) {
                Long followedId = follow.getFollowedUser().getUserId();
                List<Chirp> chirplist = ChirpDB.selectByUserId(followedId);
                for (Chirp chirp : chirplist) {
                    ChirpDisplay newChirpDisplay = new ChirpDisplay(chirp.getUser().getName(), chirp.getText(), chirp.getChirpDate(), LikeDB.getLikeCountForChirp(chirp.getChirpId()),chirp.getChirpId(),LikeDB.checkLikeable(user, chirp),FollowDB.checkFollowable(user, chirp.getUser()),chirp.getFilename());
                    displaylist.add(newChirpDisplay);
            }
            }
        }
        Collections.sort(displaylist,Collections.reverseOrder());
        
        //displaylist.sort(Comparable);
        return displaylist;
    }
    
    public static List<ChirpDisplay> getDisplayListForUserView(Long userid, User currentuser){
        
        List<ChirpDisplay> displaylist= new ArrayList<>();
        
        
        
            
            List<Chirp> chirplist = ChirpDB.selectByUserId(userid);
            for (Chirp chirp : chirplist) {
                ChirpDisplay newChirpDisplay = new ChirpDisplay(chirp.getUser().getName(), chirp.getText(), chirp.getChirpDate(), LikeDB.getLikeCountForChirp(chirp.getChirpId()),chirp.getChirpId(),LikeDB.checkLikeable(currentuser, chirp),FollowDB.checkFollowable(currentuser, chirp.getUser()),chirp.getFilename());
                displaylist.add(newChirpDisplay);
        }
        Collections.sort(displaylist,Collections.reverseOrder());
        return displaylist;
    }

    public Long getChirpid() {
        return chirpid;
    }

    public Boolean getLikeable() {
        return likeable;
    }

    public Boolean getFollowable() {
        return followable;
    }

    public void setChirpid(Long chirpid) {
        this.chirpid = chirpid;
    }

    public void setLikeable(Boolean likeable) {
        this.likeable = likeable;
    }

    public void setFollowable(Boolean followable) {
        this.followable = followable;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    @Override
    public int compareTo(ChirpDisplay otherChirpDisplay){
        if (this.date.compareTo(otherChirpDisplay.getDate())==1) {
            return 1;
        }
        if (this.date.compareTo(otherChirpDisplay.getDate())==-1) {
            return -1;
        }
        
            return 0;
        
    }
    
}
