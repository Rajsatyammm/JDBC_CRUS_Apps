package com.satyam.controller;

import com.satyam.dto.Student;

public interface IStudentController {
	String save(Student student);
	Student findById(Integer id);
	String updateById(Student student);
	String deleteById(Integer id);
}
