package com.student.exam.mcq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//This class is designed by Piyush
public class Exam extends DataFetching {
	int marks;
	String grades;
	int studentId;
	String studentName;

	
	public void test() {
		try {
			HashMap<String, Integer> hashMap; // declaring hashmap object
			hashMap = super.getExamData(); // Get hashmap object from getExamData method from DataFetiching class
			List<String> keys = new ArrayList<String>(hashMap.keySet()); // Creates new array list contains keys from
																			// hashmap
			Collections.shuffle(keys); // Shuffle the sequence of the elements
			System.out.println("Note: Please enter 1 - 4 numbers for answer "); // print instruction to students
			System.out.println("Invalid option selection has no marks ");
			System.out.println("Best Of luck...");
			int questionNumber = 1; // sequential question numbers for shuffled questions
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			for (String string : keys) { // iterating arraylist
				System.out.println("\n \n" + questionNumber + ". " + string);// Print question number and question with
																				// options
				System.out.println("Enter your Answer : "); // get user input as the selected answer
				int userAnswer = scanner.nextInt();
				while (userAnswer > 4) { // Validation for option selection
					System.out.println("Invalid option choosen...\nEnter valid input from 1-4:");
					userAnswer = scanner.nextInt();
				}

				if ((hashMap.get(string)).equals(userAnswer)) { // comparing user selected answers with right answers
					marks++; // incrementing marks if condition satisfied
				}
				questionNumber++; // incrementing question number
			}
			System.out.println("\n\nMarks Scored = " + marks); // display student marks

			saveResult(studentId, studentName, marks, gradeCalculation(marks)); // save result in database

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String gradeCalculation(int marks) { // Calculates grades according to marks of student
		if (marks >= 8) {
			grades = "A";
		} else if (marks == 7 || marks == 6) {
			grades = "B";
		} else if (marks == 5) {
			grades = "C";
		} else {
			grades = "D";
		}

		return grades;
	}

	public void saveResult(int id, String name, int marks, String grades) throws SQLException {
		connection = GetConnection.setConnection();
		statement = connection.prepareStatement("insert into user.studentdata(id,name,marks,grade) values(?,?,?,?);"); // preparing
																														// into
																														// databse
		statement.setInt(1, id);
		statement.setString(2, name);
		statement.setInt(3, marks);
		statement.setString(4, grades);
		statement.executeUpdate(); // execute the querry
	}
}
