package com.sbDao;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sbEntity.Question;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

public class SurveyDAO implements SurveyDAOInterface{

	public int createSurvey(Survey s) {	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		int i = 0;
		try {
			
		    EntityTransaction t = em.getTransaction();
		    t.begin();
		    em.merge(s);
		    i = 1;
		    t.commit();
		}catch(Exception e) {
			e.printStackTrace();
			i = 0;
		}finally {
			
			em.close();
		}
	    return i;
	}
	
	

	public Survey viewSurvey(Survey s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        Survey su = null;
        try {
        	
        	su = em.find(Survey.class, s.getSId());
        	
        }catch(Exception e) {
        	
        	su = null;
        }finally {
        	
        	em.close();
        }  
        return su;
	}

	
	
	public int editSurvey(Survey s){
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
	    EntityManager em = emf.createEntityManager();
	    try {
	        EntityTransaction t = em.getTransaction();
	        t.begin();
	        
	        Query q =  em.createQuery("update com.sbEntity.Survey s set s.title =: tit, s.dueDate =: due, s.feedback =: feed where s.sId =: SId");
	        q.setParameter("tit", s.getTitle());
	        q.setParameter("due", s.getDueDate());
	        q.setParameter("feed", s.getFeedback());
	       
	        q.setParameter("SId", s.getSId());
	        
	        q.executeUpdate();
	        i = 1;
	        t.commit();
	    }catch(Exception e ) {
	    	
	    	i = 0;
	    }finally {
	    	
	    	em.close();
	    }
	    
        return i;
	}

	public int deleteSurvey(Survey s) {
		int i = 0;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em1 = emf.createEntityManager();
	    EntityManager em = emf.createEntityManager();
		
		try {
			EntityTransaction t1 = em1.getTransaction();
			  
			t1.begin();       
			Query query1 = em1.createQuery("delete from com.sbEntity.Question q where SurveyId =: sid " );
			i += query1.setParameter("sid", s.getSId()).executeUpdate();
			t1.commit();
			
			
		    EntityTransaction t = em.getTransaction();
		               
		    t.begin(); 
		    Query query = em.createQuery("delete from com.sbEntity.Survey s where s.sId =: sid  " );
		    i = query.setParameter("sid", s.getSId()).executeUpdate();
		    t.commit();
		     
		}catch(Exception e) {
			
			i = 0;
			System.out.println("Something went wrong....");
		}finally {
			
			em.close();
			em1.close();
		}
	    return i;
	}

	public int searchSurvey(Survey s) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        try {
	        Survey su = em.find(Survey.class, s.getSId());
	        
	        if(su != null)
	        	i = 1;
	        
        }catch(Exception e) {
        	i = 0;
        }finally {
        	em.close();
        }
        
        return i;
	}

	public List<Survey> viewAllSurveys() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        List<Survey> su = null;
        try {
        	
	        Query q = em.createQuery("from com.sbEntity.Survey s");
	        su = q.getResultList();
        }catch(Exception e) {
        	
        	su = null;
        }finally {
        	
        	em.close();
        }
        
        return su;
	}
	
	
	public List<Question> getQuestionsBySid(Survey s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        List<Question> ql = null;
        try {
	        Query q = em.createQuery("from com.sbEntity.Question que where SurveyId =: sid");
	        q.setParameter("sid", s.getSId());
	        ql = q.getResultList();
        }catch(Exception e) {
        	
        	ql = null;
        }finally {
        	
        	em.close();
        }
        return ql;
	}
	
	
	public List<Survey> getSurveysBySurveyorId(Surveyor s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        List<Survey> ql = null;
        try {
	        Query q = em.createQuery("from com.sbEntity.Survey s where SurveyorId =: sid");
	        q.setParameter("sid", s.getSurveyorId());
	        ql = q.getResultList();
	        
        }catch(Exception e) {
        	
        	ql = null;
        }finally {
        	
        	em.close();
        }
        return ql;
	}



	public int distributeSurvey(Survey s) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
	    EntityManager em = emf.createEntityManager();
	    try {
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        Query q =  em.createQuery("update com.sbEntity.Survey s set s.status =: state where s.sId =: SId");
        q.setParameter("state", "Active");
        q.setParameter("SId", s.getSId());
        
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
	
	public int stopDistributedSurvey(Survey s) {
		int i = 0;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
		    EntityManager em = emf.createEntityManager();
		    try {
	        EntityTransaction t = em.getTransaction();
	        t.begin();
	        
	        Query q =  em.createQuery("update com.sbEntity.Survey s set s.status =: state where s.sId =: SId");
	        q.setParameter("state", "Passive");
	        q.setParameter("SId", s.getSId());
	        
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
