package com.student.exam.mcq;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

//Databases is designed by vikas
//This class is design by Vikas
public class DataFetching extends GetConnection {
	java.sql.PreparedStatement statement; // declaring objects globally
	ResultSet rs;

	public HashMap<String, Integer> getExamData() throws SQLException { // method which returns Hashmap object
		connection = super.setConnection(); // get connection reference using superclass method
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>(); // defines hashmap with genetics String and integer
		try {
			statement = connection.prepareStatement("select * from user.questionbank");// Prepare statement to fetch
																						// data from database
			ResultSet rs = statement.executeQuery(); // execute the prepared statement
			while (rs.next()) { // iterating result set
				hashMap.put(rs.getString(2), rs.getInt(3)); // putting values to hashmap as resultset iterate
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			statement.close(); // statement is closed
		}
		return hashMap; // return a hashmap object contains questions and right answer
	}

	public void getStudentInformation() {
		Scanner scanner = new Scanner(System.in); // scanner class instantiation
		//System.out.println("Enter your ID :"); // get input from user as student Id
		//int studentId = scanner.nextInt();
		System.out.println("Enter your Name :"); // get input from user as student Name
		String studentName = scanner.next();
		try {
			statement = connection.prepareStatement("insert into user.studentdata(name,marks,grade) values(?,?,?);");
			statement.setString(1, studentName);
			statement.setInt(2 ,0);
			statement.setString(3, null);
			statement.execute();
			//statement.close();
			statement = connection.prepareStatement("select max(id) from user.studentdata");
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
			System.out.println("Your Id is"+rs.getInt(1));
		} }catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
