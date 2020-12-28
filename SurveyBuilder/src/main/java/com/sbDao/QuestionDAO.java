package com.sbDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


import com.sbEntity.Question;
import com.sbEntity.Survey;

public class QuestionDAO implements QuestionDAOInterface{

	public int createQuestion(Question q) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
	    EntityManager em = emf.createEntityManager();
	    int i = 0;
		try {
			
		    EntityTransaction t = em.getTransaction();
		    t.begin();
	
		    em.merge(q);
		    i = 1;
		    t.commit();
		         
		}catch(Exception e) {
			
			i = 0;
		}finally {
			
			em.close();
		}
		return i;
	}
	
	

	public Question viewQuestion(Question q) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        Question que = null;
        try {
        	
        	que = em.find(Question.class, q.getQid());
        }catch(Exception e) {
        	
        	que = null;
        }finally{
        	
        	em.close();
        }
        
        return que;
	}

	
	
	public int editQuestion(Question q) {
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
		EntityManager em = emf.createEntityManager();
		int i = 0;
		try {
		    EntityTransaction t = em.getTransaction();
	        t.begin();
			        
	        Query q0 =  em.createQuery("update com.sbEntity.Question q set q.question =: quest, q.option1 =: opt1, q.option2 =: opt2, q.option3 =: opt3, q.option4 =: opt4 where q.Qid =: QId");
		    q0.setParameter("quest", q.getQuestion());
		    q0.setParameter("opt1", q.getOption1());
		    q0.setParameter("opt2", q.getOption2());
		    q0.setParameter("opt3", q.getOption3());
		    q0.setParameter("opt4", q.getOption4());
		    q0.setParameter("QId", q.getQid());
			        
		    i = q0.executeUpdate();
			        
		    t.commit();
		}catch(Exception e) {
			i = 0;
		}finally {
			em.close();
		}
		return i;
	}
	
	

	public int deleteQuestion(Question q) {
		int i = 0;
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
	     EntityManager em = emf.createEntityManager();
	     try {  
		     EntityTransaction t = em.getTransaction();
		                 
		     t.begin();
		       
		     Query query = em.createQuery("delete from com.sbEntity.Question q where q.Qid =: QId");
		     i = query.setParameter("QId", q.getQid()).executeUpdate();
		     
		     t.commit();
		     
	     }catch(Exception e) {
	    	 i = 0;
	     }finally {
	    	 em.close();
	     }
	     return i;
	}

	
	
	public int searchQuestion(Question q) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        try {
	        Question que = em.find(Question.class, q.getQid());
	        
	        if(que != null) {
	        	i = 1;
	        }
	        
        }catch(Exception e) {
        	i = 0;
        }finally {
        	 em.close();
        }
        return i;
	}

	
	
	public List<Question> viewAllQuestions() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        List<Question> ql = null;
	    try {
	        Query q = em.createQuery("from com.sbEntity.Question que");
	        ql = q.getResultList();
        }catch(Exception e) {
        	ql = null;
        }finally {
        	em.close();
        }
        return ql;
	}



	public List<Question> getQuestionsBySid(Survey s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        List<Question> ql;
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

}
