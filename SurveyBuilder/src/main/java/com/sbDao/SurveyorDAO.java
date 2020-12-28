package com.sbDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sbEntity.Question;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

public class SurveyorDAO implements SurveyorDAOInterface{
	
	public int createSurveyorProfile(Surveyor s) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
	    EntityManager em = emf.createEntityManager();
	 
		try {
		    EntityTransaction t = em.getTransaction();
		    t.begin();
		    em.persist(s);
		    i = 1;
		    t.commit();
		    
		}catch(Exception e) {
			
			i = 0;
		}finally {
			
			em.close();
		}
		return i;
	}

	public Surveyor viewCurrentProfileDAO(Surveyor cu) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        Surveyor surveyor = null;
        try {
        	
        	surveyor = em.find(Surveyor.class, cu.getSurveyorId());
        }catch(Exception e) {
        	
        	surveyor = null;
        }finally {
        	
        	em.close();
        }
  
        return surveyor;
		
	}
	
	public Surveyor surveyorAuthenticationDAO(Surveyor cu) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        Surveyor s = null;
        try {
	        Query q = em.createQuery("from com.sbEntity.Surveyor s where emailId =: semail and password =: spass");
	        q.setParameter("semail", cu.getEmailId());
	        q.setParameter("spass", cu.getPassword());
	        s = (Surveyor) q.getSingleResult();
        }catch(Exception e) {
        	
        	s = null;
        }finally {
        	
        	em.close();
        }
        
        return s;
		
	}

	
	
	public int editCurrentProfileDAO(Surveyor cu) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
	    EntityManager em = emf.createEntityManager();
	    int i = 0;
	    
	    try {
	        EntityTransaction t = em.getTransaction();
	        t.begin();
	        
	        Query q =  em.createQuery("update com.sbEntity.Surveyor s set s.name =: nm, s.emailId =: eid, s.password =: pass where s.SurveyorId =: SuId");
	        q.setParameter("nm", cu.getName());
	        q.setParameter("eid", cu.getEmailId());
	        q.setParameter("pass", cu.getPassword());
	        q.setParameter("SuId", cu.getSurveyorId());
	        
	        q.executeUpdate();
	        i = 1;
	        t.commit();
	        
	    }catch(Exception e) {
	    	
	    	i = 0;
	    }finally {
	    	
	    	em.close();
	    }
	    return i;
	}


}
