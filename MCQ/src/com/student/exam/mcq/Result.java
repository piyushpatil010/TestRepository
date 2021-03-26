package com.student.exam.mcq;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

//This class is designed by Piyush
public class Result extends GetConnection {
	ResultSet resultset;

	@SuppressWarnings("resource")
	public void ShowResult() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Your ID : ");
		int studentId = scanner.nextInt();
		; // get student id
		connection = GetConnection.setConnection();
		statement = connection.prepareStatement("select * from user.studentdata "); // preparing statement for data
																					// fetching
		ResultSet resultset = statement.executeQuery(); // executes statements
		LinkedList<Integer> linkedList = new LinkedList<>();
		while (resultset.next()) {
			linkedList.add(resultset.getInt(1));
		}
		if (linkedList.contains(studentId)) {
			statement = connection.prepareStatement("select * from user.studentdata where id=?"); // preparing statement
																									// for data fetching
			statement.setInt(1, studentId);
			resultset = statement.executeQuery(); // display student data
			while (resultset.next()) {
				System.out.println("Student id = " + resultset.getInt(1));
				System.out.println("Student Name = " + resultset.getString(2));
				System.out.println("Marks Obtained = " + resultset.getInt(3));
				System.out.println("Class/Grade = " + resultset.getString(4));
				if (resultset.getInt(3) < 5) {
					System.out.println("Fail");
				}
			}
		} else {
			while(! linkedList.contains(studentId)) {
			System.out.println("Invalid input....\nSelect coreect choice");
			System.out.println("Enter Your ID");
			studentId = scanner.nextInt();
			}
		}
	}

	public void getLeaderboard() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		connection = GetConnection.setConnection();
		statement = connection.prepareStatement("select * from user.studentdata order by marks desc"); // fetch data in
																										// descending
																										// order of
																										// marks
		ResultSet rs = statement.executeQuery();
		int rank = 1; // Rank of the students
		while (rs.next()) { // displaying multiple student data order by rank
			System.out.println("Rank -->" + rank);
			System.out.println("Student id = " + rs.getInt(1));
			System.out.println("Student Name = " + rs.getString(2));
			System.out.println("Marks Obtained = " + rs.getInt(3));
			System.out.println("Class/Grade = " + rs.getString(4) + "\n");
			rank++;
		}
	}

}
