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
public class LikeDB {
    public static void insert(Lke like){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(like);
            trans.commit();
            
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
            
        } finally{
            em.close();
        
        }
    
    }
    
    public static void unlike(Lke like){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(like));
            trans.commit();
            
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
            
        } finally{
            em.close();
        
    }
    
    
    
    }
    
    
    public static List<Lke> selectLikesByChirpId(Long chirpid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT l from Lke l where l.chirp.chirpId = :chirpid";
        
        TypedQuery<Lke> q = em.createQuery(query, Lke.class);
        q.setParameter("chirpid", chirpid);
        List<Lke> likes;
        try {
            likes = q.getResultList();
            return likes;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
    public static int getLikeCountForChirp(Long chirpid){
            List<Lke> likelist = selectLikesByChirpId(chirpid);
            int count = likelist.size();
            return count;
    
    }
    
    public static List<Lke> selectLike(User user, Chirp chirp){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT l from Lke l where l.chirp.chirpId = :chirpid and l.user.userId = :userid";
        TypedQuery<Lke> q = em.createQuery(query, Lke.class);
        q.setParameter("chirpid", chirp.getChirpId());
        q.setParameter("userid", user.getUserId());
        List<Lke> likes;
        try {
            likes = q.getResultList();
            return likes;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    
    
    }
    
        public static Lke selectSingleLike(User user, Chirp chirp){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT l from Lke l where l.chirp.chirpId = :chirpid and l.user.userId = :userid";
        TypedQuery<Lke> q = em.createQuery(query, Lke.class);
        q.setParameter("chirpid", chirp.getChirpId());
        q.setParameter("userid", user.getUserId());
        Lke like;
        try {
            like = q.getSingleResult();
            return like;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    
    
    }
    
    public static Boolean checkLikeable(User user, Chirp chirp){
        List<Lke> likes = selectLike(user, chirp);
        if (likes ==null) {
            return true;
        } 
        else if (likes.isEmpty()) {
            return true;
        }
        return false;
    
    
    }
}
