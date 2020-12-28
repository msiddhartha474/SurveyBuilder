package com.sbDao;

import java.util.List;

import com.sbEntity.Respondent;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

public interface RespondentDAOInterface {
	public int createRespondentProfile(Respondent r);
	public Respondent viewCurrentProfileDAO(Respondent cr);
	public int editCurrentProfileDAO(Respondent cr);
	public Respondent respondentAuthenticationDAO(Respondent cu);
	public List<Respondent> viewAllRespondent();
	public List<Survey> viewAvailableServeysDAO(Respondent cur_resp);
}
