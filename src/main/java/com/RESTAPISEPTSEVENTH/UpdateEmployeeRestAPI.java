package com.RESTAPISEPTSEVENTH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class UpdateEmployeeRestAPI {

	/**
	 * Sample class that makes API request to update an employee in the database
	 *
	 * @author Chris Vaughan
	 * @since 2016-09-07
	 */

	/**
	 * The URL of the API we want to connect to.
	 */
	protected static String endpoint = "http://localhost:1337/employee";

	/**
	 * The character set to use when encoding URL parameters.
	 */
	protected static String charset = "UTF-8";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		
		
		

		try {
			
			//Extracted method used to display all records in database
			GetAllRestAPI.GetALL();
			
			//User identifies the record to be updated
			System.out.println("Enter in the id number of the record you wish to update?");
			String userSelection = sc.nextLine();

			// Request user to re-enter the employee first name
			System.out.println("Please re-enter in the employee's first name?");
			String firstName = sc.nextLine();

			// Request user to re-enter the employee last name
			System.out.println("Please re-enter in the employee's last name?");
			String lastName = sc.nextLine();

			// Request user to re-enter the employee email
			System.out.println("Please re-enter in the employee's email?");
			String email = sc.nextLine();

			// Request user to re-enter the employee home phone
			System.out
					.println("Please re-enter in the employee's home phone? (***Must be in this format: XXX-XXX-XXXX***)");
			String homePhone = sc.nextLine();

			// Request user to re-enter the employee cell phone
			System.out
					.println("Please re-enter in the employee's cell phone? (***Must be in this format: XXX-XXX-XXXX***)");
			String cellPhone = sc.nextLine();

			// Request user to re-enter the employee password
			System.out.println(
					"Please re-enter in the employee's password (***Alphanumeric only, 8 charaters total, with at least 1 capital letter and 1 number***)?");
			String password = sc.nextLine();

			// Request user to re-enter the employee status
			System.out.println(
					"Please re-enter in the employee's status (***1 for active employee, 0 for inactive employee***)?");
			String active = sc.nextLine();

			// creates the url parameters as a string encoding them with the
			// defined charset
			String queryString = String.format(
					"firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(firstName, charset), URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset), URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset), URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset));

			// creates a new URL out of the endpoint and querystring
			URL localSite = new URL(endpoint + "/" + userSelection + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) localSite.openConnection();
			connection.setRequestMethod("POST");

			// if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			// read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String read;

			// loop of buffer line by the line until it return null, meaning
			// there are no more lines
			while ((read = br.readLine()) != null) {

				// print out each line to the screen and splitting the commas to
				// ensure it reads each line
				read.split(",");
				System.out.println(read);
			}

			// close connection to API
			connection.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
