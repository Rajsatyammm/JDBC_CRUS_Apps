package com.satyam.dao;

import com.satyam.dto.Student;

public interface IStudentDao {
	String save(Student student);
	Student findById(Integer id);
	String updateById(Student student);
	String deleteById(Integer id);
}
