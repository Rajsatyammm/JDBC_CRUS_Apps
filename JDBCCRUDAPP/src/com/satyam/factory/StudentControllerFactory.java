package com.satyam.factory;

import com.satyam.controller.IStudentController;
import com.satyam.controller.StudentControllerImpl;

public class StudentControllerFactory {

	private static IStudentController studentController = null;

	private StudentControllerFactory() {
	}

	public static IStudentController getStudentController() {
		if (studentController == null)
			studentController = new StudentControllerImpl();
		return studentController;
	}
}
