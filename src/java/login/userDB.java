/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
public class userDB {
    public static void insert(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(user);
            trans.commit();
            
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
            
        } finally{
            em.close();
        
        }
    
    }
    
    public static User selectUser(String name){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT u from User u where u.name = :name";
        
        TypedQuery<User> q = em.createQuery(query, User.class);
        q.setParameter("name", name);
        try {
            User user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<User> getUserList(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT u from User u";
        
        TypedQuery<User> q = em.createQuery(query, User.class);
        
        try {
            List<User> users = q.getResultList();
            return users;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
