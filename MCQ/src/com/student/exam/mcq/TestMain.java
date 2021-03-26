package com.student.exam.mcq;

import java.sql.SQLException;
import java.util.Scanner;
//This class is designed by Pushkar
public class TestMain {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {
		int option=0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter 1 for the Test.");			//provides multiple options to the user
		System.out.println("Enter 2 for Results.");;
		System.out.println("Enter 3 for Leaderboard.");
		option = scanner.nextInt();						//get user choice input
		DataFetching dataFetching = new DataFetching();	
		Exam exam = new Exam();							
		Result result = new Result();
		
		while(option>3) {
			System.out.println("Please enter valid input...");
			option = scanner.nextInt();
		}	
		if (option == 1) {								//selecting functions according to user choice
			System.out.println("Welcome to exam portal");
			dataFetching.getExamData();					// Fetching exam data from database
			exam.getStudentInformation();				//get user input as student information
			exam.test();								//test method called
		} else if (option == 2) {		//if option 2 is selected result function invoked
			result.ShowResult();						//show student details using id
		} else if (option == 3) {						//if option 3 is selected leaderboard function invoked
			result.getLeaderboard();					//show leaderboard
		}
		}
	}

