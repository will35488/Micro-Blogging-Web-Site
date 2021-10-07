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
public class ChirpDB {
    public static void insert(Chirp chirp){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(chirp);
            trans.commit();
            
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
            
        } finally{
            em.close();
        
        }
    
    }
    
    public static List<Chirp> selectByUserId(Long userId){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT c from Chirp c where c.user.userId = :userId";
        
        TypedQuery<Chirp> q = em.createQuery(query, Chirp.class);
        q.setParameter("userId", userId);
        List<Chirp> chirps;
        try {
            chirps = q.getResultList();
            return chirps;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Chirp> selectByName(String name){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT c from Chirp c where c.user.name = :name";
        
        TypedQuery<Chirp> q = em.createQuery(query, Chirp.class);
        q.setParameter("name", name);
        List<Chirp> chirps;
        try {
            chirps = q.getResultList();
            return chirps;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Chirp> selectById(Long chirpid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT c from Chirp c where c.chirpId = :chirpid";
        TypedQuery<Chirp> q = em.createQuery(query, Chirp.class);
        q.setParameter("chirpid", chirpid);
        
        List<Chirp> chirps;
        try {
            chirps = q.getResultList();
            return chirps;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    
    }
    
    
    public static Chirp getChirpById(Long chirpid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        
        
        Chirp chirp;
        try {
            chirp = em.find(Chirp.class, chirpid);
            return chirp;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    
    }
}
