package com.surveybuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;

import com.sbController.RespondentController;
import com.sbDao.AdminDAO;
import com.sbDao.AdminDAOInterface;
import com.sbDao.QuestionDAO;
import com.sbDao.QuestionDAOInterface;
import com.sbDao.RespondentDAO;
import com.sbDao.RespondentDAOInterface;
import com.sbDao.SurveyDAO;
import com.sbDao.SurveyDAOInterface;
import com.sbDao.SurveyorDAO;
import com.sbDao.SurveyorDAOInterface;
import com.sbEntity.Admin;
import com.sbEntity.Question;
import com.sbEntity.Respondent;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;
import com.sbView.AdminView;
import com.sbView.RespondentView;
import com.sbView.SurveyorView;
	
public class App 
{

	private static Logger logger = Logger.getLogger(RespondentController.class);
	
    public static void main( String[] args ) throws Exception
    {
    	QuestionDAO qdi = new QuestionDAO();
    	SurveyDAOInterface sdi = new SurveyDAO();
    	SurveyorDAOInterface sudi = new SurveyorDAO();
    	RespondentDAOInterface rdi = new RespondentDAO();
    	AdminDAOInterface addi = new AdminDAO();
    	
    	SurveyorView sv = new SurveyorView();
    	RespondentView rv = new RespondentView(); 
    	AdminView av = new AdminView();
    	
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
	    	
	    int choice = 0;
	    do {
	    	try {
	    		System.out.println("\n********************Welcome to the Online Survey Builder Application***********************");
	    		System.out.println("press 1 to Register");
				System.out.println("press 2 to login");
				System.out.println("press 3 to Exit");
				
				System.out.println("\nenter your choice");
				String str = br.readLine();
				
				choice = Integer.parseInt(str);
				
				switch(choice) {
				case 1:{
					System.out.println("press 1 to Register as Surveyor");
					System.out.println("press 2 to Register as Respondent");
					System.out.println("press 3 to Register as Admin");
					System.out.println("press 4 to Exit");
					
					System.out.println("\nenter your choice");
					int ch = Integer.parseInt(br.readLine());
					
					
					switch(ch) {
					case 1:
						
					{
						Surveyor s = new Surveyor();
						
						System.out.println("Enter your Name");
						s.setName(br.readLine());
						
						System.out.println("Enter your Email ID");
						s.setEmailId(br.readLine());
						
						System.out.println("Enter your Password");
						String pass = br.readLine();
						
						System.out.println("Confirm your Password");
						String pass2 = br.readLine();
						
						if(pass.equals(pass2))
							s.setPassword(pass);
						else {
							System.out.println("Password did not match");
							break;
						}
						
						System.out.println(s.getName()+" "+s.getEmailId()+" "+s.getPassword());
						if(sudi.createSurveyorProfile(s) == 1) {
							System.out.println("Profile created successful...");
						}
						else {
							System.out.println("Profile is not created");
						}
					}
						break;
					case 2:
					{
						Respondent r = new Respondent();
						
						System.out.println("Enter your Name");
						r.setName(br.readLine());
						
						System.out.println("Enter your Email ID");
						r.setEmailId(br.readLine());
						
						System.out.println("Enter your Password");
						String pass = br.readLine();
						
						System.out.println("Confirm your Password");
						String pass2 = br.readLine();
						
						if(pass.equals(pass2))
							r.setPassword(pass);
						else {
							System.out.println("Password did not match");
							break;
						}
						
						if(rdi.createRespondentProfile(r) == 1) {
							System.out.println("Profile created successful...");
						}
						else {
							System.out.println("Profile is not created");
						}
					}
						break;
					case 3:
					{
						Admin a = new Admin();
						
						System.out.println("Enter your Name");
						a.setName(br.readLine());
						
						System.out.println("Enter your Email ID");
						a.setEmailId(br.readLine());
						
						System.out.println("Enter your Password");
						String pass = br.readLine();
						
						System.out.println("Confirm your Password");
						String pass2 = br.readLine();
						
						if(pass.equals(pass2))
							a.setPassword(pass);
						else {
							System.out.println("Password did not match");
							break;
						}
						
						if(addi.createAdminProfile(a) == 1) {
							System.out.println("Profile created successful...");
						}
						else {
							System.out.println("Profile is not created");
						}
					}
						break;
					case 4:
						break;
					}
					
					
				}
					break;
				case 2:
				{
					System.out.println("press 1 to Log In as Surveyor");
					System.out.println("press 2 to Log In as Respondent");
					System.out.println("press 3 to Log In as Admin");
					System.out.println("press 4 to Exit");
					
					System.out.println("\nenter your choice");
					int ch = Integer.parseInt(br.readLine());
					
					
					switch(ch) {
					case 1:
					{
						Surveyor s = new Surveyor();
						
						System.out.println("Enter your Email ID");
						s.setEmailId(br.readLine());
						
						System.out.println("Enter your Password");
							s.setPassword(br.readLine());
						
						Surveyor cur_surveyor = sudi.surveyorAuthenticationDAO(s);
						if(cur_surveyor != null) {
							sv.SurveyorMain(cur_surveyor);
						}else {
							System.out.println("Invalid Credintials");
						}
						
					}
						break;
					case 2:
					{
						Respondent s = new Respondent();
					
						System.out.println("Enter your Email ID");
						s.setEmailId(br.readLine());
						
						System.out.println("Enter your Password");
							s.setPassword(br.readLine());
						
						Respondent cur_resp = rdi.respondentAuthenticationDAO(s);
						if(cur_resp != null) {
							rv.RespondentMain(cur_resp);
						}else {
							System.out.println("Invalid Credintials");
						}
					}
						break;
					case 3:
					{
						Admin a = new Admin();
					
						System.out.println("Enter your Email ID");
						a.setEmailId(br.readLine());
						
						System.out.println("Enter your Password");
						a.setPassword(br.readLine());
						
						Admin cur_adm = addi.adminAuthenticationDAO(a);
						if(cur_adm != null) {
							av.AdminMain(cur_adm);
						}else {
							System.out.println("Invalid Credintials");
						}
					}
						break;
					case 4:
						break;
					}
					
				}
					break;
				case 3:
					break;
				default:
					logger.error("Invalid Choice");
					System.out.println("Invalid Choice");
				}
	    	}catch(Exception e) {
	        		logger.error("Invalid Input");
	        		System.out.println("Invalid Input");
	        }
	    		
	    		
	    }while(choice != 3);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
        //Surveyor::createSurveyorProfile---------------------------------------------------->
    	
    	/*Question que = new Question();
    	Surveyor s = new Surveyor();
    	
    	s.setEmailId("Sid@gmail.com");
    	s.setName("sid");
    	s.setPassword("1234");
    	sudi.createSurveyorProfile(s);*/
    	

    	
    	
        
        //Question::createQuestion---------------------------------------------------->
    	
    	/*Question que = new Question();
	     que.setQuestion("which one is your favourite car");
	     que.setOption1("BMW");
	     que.setOption2("Audi");
	     que.setOption3("Mercedes");
	     que.setOption4("None of the above");
	     
	     qdi.createQuestion(que); */
        
        
    	
    	
    	
    	//Survey::createSurvey-------------------------------------------------------->
    	
    	/*SurveyDAOInterface sd = new SurveyDAO();
        Survey s = new Survey();
        s.setTitle("Survey 2");
        s.setDueDate("10/12/2020");
        s.setFeedback(3);
	    sd.createSurvey(s);*/
	    
	    
    	
    	
        
        //Question::viewQuestion-------------------------------------------------------->
    	/* Question que = new Question();
	    que.setQid(1);
	    
	    Question q = qdi.viewQuestion(que);
	    System.out.println(q.toString());*/
    	
    	
    	
    	
    	
    	
    	//Survey::viewSurvey------------------------------------------------------------>
    	/*Survey s = new Survey();
	    s.setSId(3);
	    
	    Survey sout = sd.viewSurvey(s);
	    System.out.println(sout.toString());*/
       
                
        
    	
    	
    	
        //Question::viewAllQuestion------------------------------------------------------>
    	/* List<Question> ql = qdi.viewAllQuestions();
        for (Question qs : ql) {
            System.out.println(qs.toString());
        }*/
    	
    	
    	
    	
    	
    	 //Survey::viewAllSurveys------------------------------------------------------>
    	
    	/*List<Survey> sl = sdi.viewAllSurveys();
        for (Survey s : sl) {
            System.out.println(s.toString());
        }*/
        
        
        
        
        
        //Question::deleteQuestion----------------------------------------------------->
        
       /* Question que = new Question();
	    que.setQid(1);
	    
	    int i = qdi.deleteQuestion(que);
	    System.out.println(i);*/
        
        
        
        
        
        
        
      //Survey::deleteServey----------------------------------------------------->
        
    	/*Survey s = new Survey();
	    s.setSId(1);
	    
	    int i = sdi.deleteSurvey(s);
	    System.out.println(i);
        */
        
        
        
        
        
        
        //Question::searchQuestion----------------------------------------------->
        
        /*Question que = new Question();
	    que.setQid(1);
	    
	    int i = qdi.searchQuestion(que);
	    System.out.println(i);*/
        
        
        
        
        
        
        //Survey::searchSurvey----------------------------------------------->
        
    	/*Survey s = new Survey();
	    s.setSId(6);
	    
	    int i = sdi.searchSurvey(s);
	    System.out.println(i);
        */
        
        
        
        
        
        //Question::editQuestion--------------------------------------------->
        
        /*Question que = new Question();
	    que.setQid(1);
	    
	    int i = qdi.editQuestion(que);
	    System.out.println(i);*/
    	
        
        
        
        
        
        //Survey::editSurvey------------------------------------------------->
    	/*Survey s = new Survey();
	    s.setSId(6);
    	
    	int i = sdi.editSurvey(s);
	    System.out.println(i);*/
    	
    	
        
        
    	
    	
    	
    	//Question::getQuestionsBySid---------------------------------------->
    	/*Survey su = new Survey();
    	su.setSId(1);
    	
     	List<Question> sl = qdi.getQuestionsBySid(su);
         for (Question s : sl) {
             System.out.println(s.toString());
         }*/
        
    }
}
