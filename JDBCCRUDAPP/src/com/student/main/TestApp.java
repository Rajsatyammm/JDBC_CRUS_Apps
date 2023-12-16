package com.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.satyam.controller.IStudentController;
import com.satyam.dto.Student;
import com.satyam.factory.StudentControllerFactory;

public class TestApp {

	public static IStudentController stdController;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean loop = true;
		int option;

		do {
			System.out.println("1. Create");
			System.out.println("2. Read");
			System.out.println("3. Update");
			System.out.println("4. Delete");
			System.out.println("5. Exit");

			option = Integer.parseInt(br.readLine());

			if (option == 1) {
				System.out.print("Enter Name :: ");
				String name = br.readLine();

				System.out.print("Enter City :: ");
				String city = br.readLine();

				System.out.print("Enter Email :: ");
				String email = br.readLine();

				System.out.print("Enter Country :: ");
				String country = br.readLine();

				Student student = new Student();
				student.setName(name);
				student.setCity(city);
				student.setEmail(email);
				student.setCountry(country);

				stdController = StudentControllerFactory.getStudentController();
				String status = stdController.save(student);

				if ("success".equals(status)) {
					System.out.println("Successfully Inserted to DB");
				} else if ("failure".equals(status)) {
					System.out.println("Not Inserted to DB");
				} else {
					System.out.println("Error occured");
				}

			} else if (option == 2) {

				System.out.println("Enter id : ");
				int id = Integer.parseInt(br.readLine());
				stdController = StudentControllerFactory.getStudentController();
				Student student = stdController.findById(id);
				if (student != null)
					System.out.println(student);
				else
					System.out.println("No record Found by id :: " + id);

			} else if (option == 3) {

				System.out.println("Enter id : ");
				int id = Integer.parseInt(br.readLine());
				stdController = StudentControllerFactory.getStudentController();
				Student student = stdController.findById(id);
				if (student != null) {
					Student newStudent = new Student();
					newStudent.setId(id);
					
					System.out.print("[ Old Name is :: " + student.getName() + "] ");
					String newName = br.readLine();
					if (newName == null || newName.equals(""))
						newStudent.setName(student.getName());
					else
						newStudent.setName(newName);

					System.out.print("[ Old Email is :: " + student.getEmail() + "] ");
					String newEmail = br.readLine();
					if (newEmail == null || newEmail.equals(""))
						newStudent.setEmail(student.getEmail());
					else
						newStudent.setEmail(newEmail);

					System.out.print("[ Old City is :: " + student.getCity() + "] ");
					String newCity = br.readLine();
					if (newCity == null || newCity.equals(""))
						newStudent.setCity(student.getCity());
					else
						newStudent.setCity(newCity);

					System.out.print("[ Old Country is :: " + student.getCountry() + "] ");
					String newCountry = br.readLine();
					if (newCountry == null || newCountry.equals(""))
						newStudent.setCountry(student.getCountry());
					else
						newStudent.setCountry(newCountry);

					String status = stdController.updateById(newStudent);
					if ("success".equals(status)) {
						System.out.println("Successfully Updated to DB");
					} else if ("failure".equals(status)) {
						System.out.println("Not Updated to DB");
					} else {
						System.out.println("Error occured");
					}
					
				} else
					System.out.println("No record Found by id :: " + id);

			} else if (option == 4) {

				System.out.println("Enter id : ");
				int id = Integer.parseInt(br.readLine());
				stdController = StudentControllerFactory.getStudentController();
				String status = stdController.deleteById(id);
				if ("success".equals(status)) {
					System.out.println("Successfully Deleted from DB");
				} else if ("failure".equals(status)) {
					System.out.println("Not Deleted from DB");
				} else {
					System.out.println("Error occured");
				}

			} else {
				loop = false;
			}

		} while (loop);

	}

}
