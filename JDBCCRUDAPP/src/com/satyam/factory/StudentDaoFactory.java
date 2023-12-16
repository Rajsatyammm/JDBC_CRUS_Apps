package com.satyam.factory;

import com.satyam.dao.IStudentDao;
import com.satyam.dao.StudentDaoImpl;

public class StudentDaoFactory {

	private static IStudentDao studentDao;

	private StudentDaoFactory() {
	}

	public static IStudentDao getStudentDao() {
		if (studentDao == null)
			studentDao = new StudentDaoImpl();
		return studentDao;
	}

}
