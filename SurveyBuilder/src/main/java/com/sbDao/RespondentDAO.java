package com.sbDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sbEntity.Respondent;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

public class RespondentDAO implements RespondentDAOInterface{
	
	public int createRespondentProfile(Respondent r) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		try {	 
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(r);
				
			i = 1;
			t.commit();
		}catch(Exception e) {
			
			i = 0;
		}finally {
			
			em.close();
		}
		return i;
	}

	public Respondent viewCurrentProfileDAO(Respondent cr) {
				
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		Respondent respondent = null;
		try {  
			
			respondent = em.find(Respondent.class, cr.getRespondentId());
		
		}catch(Exception e) {
			
			respondent = null;
		}finally {
			
			em.close();
		}
		return respondent;
				
	}

					
	public int editCurrentProfileDAO(Respondent cr) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
		EntityManager em = emf.createEntityManager();
		int i = 0;
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			        
			Query q =  em.createQuery("update com.sbEntity.Respondent s set s.name =: nm, s.emailId =: eid, s.password =: pass where s.RespondentId =: rId");
			q.setParameter("nm", cr.getName());
			q.setParameter("eid", cr.getEmailId());
			q.setParameter("pass", cr.getPassword());
			q.setParameter("rId", cr.getRespondentId());
			        
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

	
	public List<Respondent> viewAllRespondent() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		List<Respondent> rl = null;
		try {
			
			Query q = em.createQuery("from com.sbEntity.Respondent r");
			rl = q.getResultList();
		}catch(Exception e) {
			
			rl = null;
		}finally {
			
			em.close();
		}       
		return rl;
	}
					
	
	public Respondent respondentAuthenticationDAO(Respondent cu) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		Respondent r = null;
		try {
			
			Query q = em.createQuery("from com.sbEntity.Respondent r where emailId =: semail and password =: spass");
			q.setParameter("semail", cu.getEmailId());
			q.setParameter("spass", cu.getPassword());
			r = (Respondent) q.getSingleResult();
		}catch(Exception e){
			
			r = null;
		}finally {
			
			em.close();
		}        
		return r;
	}

			
			
	public List<Survey> viewAvailableServeysDAO(Respondent cur_resp) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		List<Survey> rl;
		try {
			Query q = em.createQuery("from com.sbEntity.Survey s where s.status =: s");
			q.setParameter("s", "Active");
			rl = q.getResultList();
		}catch(Exception e) {
			rl = null;
		}finally {
			em.close();
		}
		return rl;
	}
				
}
