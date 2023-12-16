package com.satyam.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.satyam.dto.Student;
import com.satyam.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {

	Connection connection = null;
	String status = null;
	Student student = null;

	@Override
	public String save(Student student) {
		String sqlInsertQuery = "insert into student(`name`, `email`, `city`, `country`) values(?,?,?,?);";
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, student.getName());
				pstmt.setString(2, student.getEmail());
				pstmt.setString(3, student.getCity());
				pstmt.setString(4, student.getCountry());
			}

			int rowAffected = pstmt.executeUpdate();

			if (rowAffected == 1)
				status = "success";

			else
				status = "failure";

		} catch (SQLException | IOException e) {
			status = "failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student findById(Integer id) {
		String sqlFindQuery = "select id, name, email, city, country from student where id = ?";
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlFindQuery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, id);
			}

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				student = new Student();
				student.setId(resultSet.getInt(1));
				student.setName(resultSet.getString(2));
				student.setEmail(resultSet.getString(3));
				student.setCity(resultSet.getString(4));
				student.setCountry(resultSet.getString(5));
			}

		} catch (SQLException | IOException e) {
			student = null;
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateById(Student stud) {
		String sqlUpdateQuery = "update student set name=?, email=?, city=?, country=? where id = ?";
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, stud.getName());
				pstmt.setString(2, stud.getEmail());
				pstmt.setString(3, stud.getCity());
				pstmt.setString(4, stud.getCountry());
				pstmt.setInt(5, stud.getId());
			}

			int rowAffected = pstmt.executeUpdate();

			if (rowAffected == 1)
				status = "success";
			else
				status = "failure";

		} catch (SQLException | IOException e) {
			status = "failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String deleteById(Integer id) {
		String sqlFindQuery = "delete from student where id = ?";
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtil.getJdbcConnection();

			student = findById(id);
			if (student != null) {
				if (connection != null) {
					pstmt = connection.prepareStatement(sqlFindQuery);
				}
				if (pstmt != null) {
					pstmt.setInt(1, id);
				}

				int updatedRow = pstmt.executeUpdate();

				if (updatedRow == 1)
					status = "success";

				else
					status = "failure";
			}
			else 
				status = "not available";

		} catch (SQLException | IOException e) {
			status = "failure";
			e.printStackTrace();
		}
		return status;
	}

}
