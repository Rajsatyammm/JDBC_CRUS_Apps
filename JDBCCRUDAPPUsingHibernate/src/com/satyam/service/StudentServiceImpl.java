package com.satyam.service;

import com.satyam.dao.IStudentDao;
import com.satyam.dto.Student;
import com.satyam.factory.StudentDaoFactory;

public class StudentServiceImpl implements IStudentService {
	
	IStudentDao studentDao;
	
	@Override
	public String save(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.save(student);
	}

	@Override
	public Student findById(Integer id) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.findById(id);
	}

	@Override
	public String updateById(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.updateById(student);
	}

	@Override
	public String deleteById(Integer id) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.deleteById(id);
	}

}
