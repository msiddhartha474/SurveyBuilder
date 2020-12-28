package com.sbController;

import com.sbEntity.Surveyor;

public interface SurveyorControllerInterface {
	public void viewCurrentProfile(Surveyor cu);
	public void editCurrentProfile(Surveyor cu);
	public void createSurvey(Surveyor cu);
	public void editSurvey(Surveyor cu);
	public void distributeSurvey(Surveyor cu);
	public void stopDistributedSurvey(Surveyor cu);
	public void reviewSurvey(Surveyor cu);
	public void viewAllSurvey(Surveyor cu);
}
