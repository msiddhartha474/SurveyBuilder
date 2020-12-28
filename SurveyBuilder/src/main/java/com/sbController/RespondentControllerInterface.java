package com.sbController;

import com.sbEntity.Respondent;

public interface RespondentControllerInterface {

	void viewCurrentProfile(Respondent cur_resp);

	void editCurrentProfile(Respondent cur_resp);

	void viewAvailableServeys(Respondent cur_resp);

	void fillServey(Respondent cur_resp);

}
