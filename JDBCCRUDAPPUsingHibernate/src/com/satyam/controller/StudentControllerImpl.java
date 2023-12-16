package com.satyam.controller;

import com.satyam.dto.Student;
import com.satyam.factory.StudentServiceFactory;
import com.satyam.service.IStudentService;

public class StudentControllerImpl implements IStudentController {

	IStudentService stdService;
	
	@Override
	public String save(Student student) {
		stdService = StudentServiceFactory.getStudentService();
		return stdService.save(student);
	}

	@Override
	public Student findById(Integer id) {
		stdService = StudentServiceFactory.getStudentService();
		return stdService.findById(id);
	}

	@Override
	public String updateById(Student student) {
		stdService = StudentServiceFactory.getStudentService();
		return stdService.updateById(student);
	}

	@Override
	public String deleteById(Integer id) {
		stdService = StudentServiceFactory.getStudentService();
		return stdService.deleteById(id);
	}

}
