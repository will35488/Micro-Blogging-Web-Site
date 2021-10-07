
package login;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */

@Entity
public class User implements Serializable{
    
    
    private Long userId;
    private String name;
    private String passWord;
    
    public User(){
        //userId="";
        name="";
        passWord="";
    
    
    }
    
    public User(String name,String password){
        //userId="";
        this.name=name;
        this.passWord=password;
    
    
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getUserId (){
        return userId;
    
    }

    public String getPassWord() {
        return passWord;
    }

    public String getName() {
        return name;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
}
