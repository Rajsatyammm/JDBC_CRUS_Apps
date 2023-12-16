package com.satyam.factory;

import com.satyam.service.IStudentService;
import com.satyam.service.StudentServiceImpl;

public class StudentServiceFactory {

	private static IStudentService studentService = null;

	private StudentServiceFactory() {
	}

	public static IStudentService getStudentService() {
		if (studentService == null)
			studentService = new StudentServiceImpl();
		return studentService;
	}
}
