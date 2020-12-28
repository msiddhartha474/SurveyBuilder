package com.sbDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sbEntity.Answer;
import com.sbEntity.Question;

public class AnswerDAO implements AnswerDAOInterface{

	public int saveAnswer(Answer a) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
	    EntityManager em = emf.createEntityManager();
	    try {
	    EntityTransaction t = em.getTransaction();
	    t.begin();

	    em.merge(a);
	    i = 1;   
	    t.commit(); 
	 
	    }catch(Exception e) {
	    	i = 0;
	    }finally {
	    	em.close();
	    }
	    return i;
	}

	public List<Answer> getAnswersByQid(Question q) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        List<Answer> ql = null;
        try {
        Query q0 = em.createQuery("from com.sbEntity.Answer a where queId =: qid");
        q0.setParameter("qid", q.getQid());
        
        ql = q0.getResultList();
        
        }catch(Exception e) {
        	ql = null;
        	
        }finally {
        	em.close();
        }
        return ql;
	}
	
}
