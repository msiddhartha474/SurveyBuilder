package com.sbController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sbDao.AnswerDAO;
import com.sbDao.AnswerDAOInterface;
import com.sbDao.QuestionDAO;
import com.sbDao.QuestionDAOInterface;
import com.sbDao.RespondentDAO;
import com.sbDao.RespondentDAOInterface;
import com.sbDao.SurveyDAO;
import com.sbDao.SurveyDAOInterface;
import com.sbEntity.Answer;
import com.sbEntity.Question;
import com.sbEntity.Respondent;
import com.sbEntity.Survey;


public class RespondentController implements RespondentControllerInterface{
	
	private RespondentDAOInterface rdi = new RespondentDAO();
	private SurveyDAOInterface sdi = new SurveyDAO();
	private QuestionDAOInterface qdi = new QuestionDAO();
	private AnswerDAOInterface adi = new AnswerDAO();
	
	private static Logger logger = Logger.getLogger(RespondentController.class);
	 
	
	public void viewCurrentProfile(Respondent cur_resp) {
		Respondent user = rdi.viewCurrentProfileDAO(cur_resp);
		if(user != null) {
			logger.info(user.toString());
			System.out.println(user.toString());
		}else {
			logger.error("No profile found");
			System.out.println("No profile found");
		}
	}

	public void editCurrentProfile(Respondent cur_resp){
		Respondent updated_user = cur_resp;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("press 1 to edit Name\n"+
			"press 2 to edit Email ID\n"+
			"press 3 to edit Password\n"+
			"press 4 to Back\n");
			
			int ch = Integer.parseInt(br.readLine());
				
			switch(ch) {
			case 1:
				System.out.println("enter new Name");
				updated_user.setName(br.readLine());
				break;
			case 2:
				System.out.println("enter new Email");
				updated_user.setEmailId(br.readLine());
	
				break;
			case 3:
				System.out.println("enter new Password");
				updated_user.setPassword(br.readLine());
		        
				break;
			case 4:
				break;
				
			default:
				System.out.println("Invalid choice");
			}
		
			
			if(rdi.editCurrentProfileDAO(updated_user) == 1) {
				logger.info("Details Updated Successfully");
				System.out.println("Details Updated Successfully");
			}else {
				logger.error("Details did not updated");
				System.out.println("Details Updated Successfully");
			}
		}
		catch(Exception e) {
			logger.error("Invalid Input");
			System.out.println("Invalid Input");
		}
	}

	public void viewAvailableServeys(Respondent cur_resp) {
		List<Survey> availables =  (ArrayList<Survey>) rdi.viewAvailableServeysDAO(cur_resp);
		if(availables != null) {
			for(Survey s : availables) {
				logger.info(s.getsId()+" : "+s.getTitle());
				System.out.println(s.getsId()+" : "+s.getTitle());
			}
		}
		else{
			logger.error("No serveys found....");
			System.out.println("No serveys found....");
		}
		
	}

	public void fillServey(Respondent cur_resp){
		Survey s = new Survey();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("enter Survey ID to fill the survey");
			s.setSId(Long.parseLong(br.readLine()));
			
			Survey cur_survey = sdi.viewSurvey(s);
			List<Question> qs = qdi.getQuestionsBySid(cur_survey);
			
			System.out.println("***********************Survey Form***************************");
			System.out.println("Survey ID: "+cur_survey.getSId());
			System.out.println("Due Date: "+cur_survey.getDueDate());
			System.out.println("Status: "+cur_survey.getStatus());
			System.out.println("Title : "+cur_survey.getTitle());
			for(Question q : qs) {
				System.out.println(q.getQid()+". "+q.getQuestion());
				System.out.println("1) "+q.getOption1());
				System.out.println("2) "+q.getOption2());
				System.out.println("3) "+q.getOption3());
				System.out.println("4) "+q.getOption4()+"\n");
				
				Answer ans = new Answer();
				System.out.print("Enter your responce: ");
				ans.setAns(br.readLine());
				ans.setQue(q);
				adi.saveAnswer(ans);
				
				System.out.println("\n");
		
			}
			
			System.out.print("Enter your over all Rating for this Survey (1.0 to 5.0) ");
			float fb = Float.parseFloat(br.readLine());
			cur_survey.setFeedback((cur_survey.getFeedback() + fb) / 2);
			
			if(sdi.editSurvey(cur_survey) == 1) {
				logger.info("Thank you for feedback your responce is recorded :)");
				System.out.println("Thank you for feedback your responce is recorded :)");
			}else {
				logger.error("can't save feedback :(");
				System.out.println("can't save feedback :(");
			}
		}catch(Exception e) {
			logger.error("Invalid Input");
			System.out.println("Invalid Input");
		}
	}

}
