package com.satyam.dao;

import java.sql.Connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satyam.dto.Student;
import com.satyam.util.HibernateUtil;

public class StudentDaoImpl implements IStudentDao {

	Connection connection = null;
	String status = "failure";
	Student student = null;
	Session session = null;
	Transaction transaction = null;

	@Override
	public String save(Student stud) {
		session = HibernateUtil.getSession();
		try {
			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				stud.setId(1);
				session.save(stud);
				status = "success";
				transaction.commit();
				System.out.println("object saved to db");
			}

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return status;

	}

	@Override
	public Student findById(Integer id) {
		session = HibernateUtil.getSession();

		try {
			if (session != null)
				student = session.get(Student.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateById(Student stud) {
		session = HibernateUtil.getSession();

		try {
			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null)
				student = session.get(Student.class, stud.getId());

			if (student != null) {
				student.setName(stud.getName());
				student.setCity(stud.getCity());
				student.setCountry(stud.getCountry());
				session.update(student);
				transaction.commit();
				status = "success";

			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String deleteById(Integer id) {
		session = HibernateUtil.getSession();

		try {
			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null)
				student = session.get(Student.class, id);

			if (student != null) {
				session.delete(student);
				status = "success";
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return status;
	}

}
