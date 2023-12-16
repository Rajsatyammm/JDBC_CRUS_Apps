package com.satyam.service;

import com.satyam.dto.Student;

public interface IStudentService {
	String save(Student student);
	Student findById(Integer id);
	String updateById(Student student);
	String deleteById(Integer id);
}
