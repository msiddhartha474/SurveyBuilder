package com.sbDao;

import com.sbEntity.Surveyor;

public interface SurveyorDAOInterface {
	
		public int createSurveyorProfile(Surveyor cu);
		public Surveyor viewCurrentProfileDAO(Surveyor cu);
		public int editCurrentProfileDAO(Surveyor cu);
		public Surveyor surveyorAuthenticationDAO(Surveyor cu);
}
