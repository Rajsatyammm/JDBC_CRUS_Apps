package com.satyam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.satyam.dto.Student;
import com.satyam.factory.StudentServiceFactory;
import com.satyam.service.IStudentService;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	@SuppressWarnings("unused")
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		IStudentService stdService = null; 
		RequestDispatcher rd = null;
		Student student = null;
		
		if(uri.endsWith("addForm")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			
			System.out.println(name);
			System.out.println(email);
			System.out.println(city);
			System.out.println(country);
			
			stdService = StudentServiceFactory.getStudentService();
			student = new Student();
			student.setName(name);
			student.setEmail(email);
			student.setCity(city);
			student.setCountry(country);
			
			String status = stdService.save(student);
			System.out.println(status);
			
			if("success".equals(status)) 
				rd = request.getRequestDispatcher("../success.html");
			else 
				rd = request.getRequestDispatcher("../failure.html");	
				rd.forward(request, response);
		}
		
		if(uri.endsWith("readForm")) {
			String readId = request.getParameter("readId");
			stdService = StudentServiceFactory.getStudentService();
			student = stdService.findById(Integer.parseInt(readId));
			String status = (student == null) ? "failure" : "success";
			System.out.println(student);
			
			if("success".equals(status)) {
				rd = request.getRequestDispatcher("../success.html");
			}
			else 
				rd = request.getRequestDispatcher("../failure.html");	
				rd.forward(request, response);
		}
		
		if(uri.endsWith("deleteForm")) {
			String deleteId = request.getParameter("deleteId");
			stdService = StudentServiceFactory.getStudentService();
			String status = stdService.deleteById(Integer.parseInt(deleteId));
			
			if("success".equals(status)) {
				rd = request.getRequestDispatcher("../success.html");
			}
			else 
				rd = request.getRequestDispatcher("../failure.html");	
				rd.forward(request, response);
		}
		
		if(uri.endsWith("updateForm")) {
			String updateId = request.getParameter("updateId");
			stdService = StudentServiceFactory.getStudentService();
			student = stdService.findById(Integer.parseInt(updateId));
			
			if(student != null) {
				
				//create a form
				rd = request.getRequestDispatcher("../updateForm.html");
				request.setAttribute("updateId", updateId);
				request.setAttribute("student", student);
				rd.forward(request, response);
			}
			else {
				rd = request.getRequestDispatcher("../failure.html");	
				rd.forward(request, response);
			}
		}
		
		if(uri.endsWith("newUpdateForm")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			
			int updateId = (int) request.getAttribute("updateId");
			Student oldStudent = (Student) request.getAttribute("student");
			
			stdService = StudentServiceFactory.getStudentService();
			student = new Student();
			
			student.setId(updateId);
			
			if(name != null || name != "") 
				student.setName(name);
			else
				student.setName(oldStudent.getName());
			
			if(email != null || email != "") 
				student.setEmail(email);
			else
				student.setEmail(oldStudent.getEmail());
			
			if(city != null || city != "") 
				student.setCity(city);
			else
				student.setCity(oldStudent.getCity());
			
			if(country != null || country != "") 
				student.setCountry(country);
			else
				student.setCountry(oldStudent.getCountry());
			
			String status = stdService.updateById(student);
			if("success".equals(status)) {
				rd = request.getRequestDispatcher("../success.html");
			}
			else 
				rd = request.getRequestDispatcher("../failure.html");	
				rd.forward(request, response);
			
		}
	
		
	}

}
