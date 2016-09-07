package com.RESTAPISEPTSEVENTH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class GetAllRestAPI {

	/**
	 * Sample class that makes API request to GET all employees in a database
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

		//Displays all employees in database
		GetALL();
	}

	protected static void GetALL() {
		try {

			
			// creates a new URL out of the endpoint
			URL localSite = new URL(endpoint);
			HttpURLConnection connection = (HttpURLConnection) localSite.openConnection();
			connection.setRequestMethod("GET");

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

				String[] split = read.split(",");
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

	
	
	
	
	
	
	
	
	


