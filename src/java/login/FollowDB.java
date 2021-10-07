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
public class FollowDB {
    public static void insert(Follow follow){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(follow);
            trans.commit();
            
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
            
        } finally{
            em.close();
        
        }
    
    }
    
    public static void unfollow(Follow follow){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(follow));
            trans.commit();
            
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
            
        } finally{
            em.close();
        
        }
    
    
    
    }
    
    public static List<Follow> selectFollowsByUserId(Long userid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT f from Follow f where f.user.userId = :userid";
        
        TypedQuery<Follow> q = em.createQuery(query, Follow.class);
        q.setParameter("userid", userid);
        List<Follow> follows;
        try {
            follows = q.getResultList();
            return follows;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Follow> selectFollow(User user, User followeduser){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT f from Follow f where f.user.userId = :userid and f.followedUser.userId = :followeduserid";
    
        TypedQuery<Follow> q = em.createQuery(query, Follow.class);
        q.setParameter("userid", user.getUserId());
        q.setParameter("followeduserid", followeduser.getUserId());
        List<Follow> follows;
        try {
            follows = q.getResultList();
            return follows;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    
    
    }
    
    public static Boolean checkFollowable(User user, User followeduser){
        List<Follow> follows = selectFollow(user, followeduser);
        if (user.getUserId().equals(followeduser.getUserId())) {
            return false;
        }
        
        if (follows ==null) {
            return true;
        } 
        else if (follows.isEmpty()) {
            return true;
        }
        return false;
        
    
    }
    
    public static Follow selectSingleFollow(User user, User followeduser){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT f from Follow f where f.user.userId = :userid and f.followedUser.userId = :followeduserid";
    
        TypedQuery<Follow> q = em.createQuery(query, Follow.class);
        q.setParameter("userid", user.getUserId());
        q.setParameter("followeduserid", followeduser.getUserId());
        Follow follow;
        try {
            follow = q.getSingleResult();
            return follow;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    
    
    }
}
