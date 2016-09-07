package com.RESTAPISEPTSEVENTH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class DeleteEmployeeRestAPI {

	
	/**
	 * Sample class that makes API request to delete an employee from the database
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

			
			//Display all employees in database
			GetAllRestAPI.GetALL();
			
			//Ask user which record they want to delete
			System.out.println("Please enter in the id of the record you wish to delete:");
			String userSelection = sc.nextLine();
			
			// creates a new URL out of the endpoint
			URL localSite = new URL(endpoint + "/" + userSelection);
			HttpURLConnection connection = (HttpURLConnection) localSite.openConnection();
			connection.setRequestMethod("DELETE");

			// if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			//Display all remaining employees in database after the deletion
			GetAllRestAPI.GetALL();


			// close connection to API
			connection.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	


	
}
