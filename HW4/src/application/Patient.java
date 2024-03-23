/*	Kevin Johnston
 *  1218407034
 *  CSE360, Carter, Tuesday 1:30pm - 2:45pm
 *  HW4
 */ 

package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Patient {
	//HashMap that will hold a list of all registered patients
	private static Map<String, Patient> patientRegistry = new HashMap<>();
	
	//Patient information
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String healthHistory;
	private String insuranceID;
	private String patientID;
	private int totalCAC;
	private int lmLevel;
	private int ladLevel;
	private int lcxLevel;
	private int rcaLevel;
	private int pdaLevel;
	
	//Constructor for a Patient
	public Patient(String firstName, String lastName, String email, String phoneNumber, String healthHistory, String insuranceID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.healthHistory = healthHistory;
		this.insuranceID = insuranceID;
		this.patientID = generatePatientID();
		patientRegistry.put(this.patientID, this);
	}
	
	//Method to create a unique ID for a patient
	private String generatePatientID() {
		Random random = new Random();
		int id;
		String uniqueID;
		
		//Check the patientRegistry map to ensure that a unique ID is created
		do {
			id = 10000 + random.nextInt(90000);
			uniqueID = String.valueOf(id);
		} while (patientRegistry.containsKey(uniqueID));
		
		return uniqueID;
	}
	
	//Method to get a Patient by their ID
	public static Patient getPatientByID(String id) {
		return patientRegistry.get(id);
	}
	
	//Method to check if a patientID exists
	public static boolean patientIdExists(String id) {
		return patientRegistry.containsKey(id);
	}
	
	//Method to print patient info to console
	public void printPatientInfo() {
		System.out.println("Patient ID: " + this.patientID);
		System.out.println("First Name: " + this.firstName);
		System.out.println("Last Name: " + this.lastName);
		System.out.println("Email: " + this.email);
		System.out.println("Phone Number: " + this.phoneNumber);
		System.out.println("Health History: " + this.healthHistory);
		System.out.println("Insurance ID: " + this.insuranceID);
		System.out.println();
	}
	
	//Method to save info to a patient's file
	public void savePatientInfo() {
		String filename = patientID + "_PatientInfo.txt";
		
		//Instantiate a BuferedWriter and FileWriter for file writing operations
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			writer.write("Patient ID: " + this.patientID + "\n");
			writer.write("First Name: " + this.firstName + "\n");
			writer.write("Last Name: " + this.lastName + "\n");
			writer.write("Email: " + this.email + "\n");
			writer.write("Phone Number: " + this.phoneNumber + "\n");
			writer.write("Health History: " + this.healthHistory + "\n");
			writer.write("Insurance ID: " + this.insuranceID + "\n");
			
			File file = new File(filename);
			System.out.println("File saved to: " + file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Method to save ct scan info to a patient's file
	public void savePatientScanInfo() {
		String filename = patientID + "CTResults.txt";
		
		//Instantiate a BufferedWriter and FileWriter for file writing operations
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			writer.write("Patient ID: " + this.patientID + "\n");
			writer.write("The total Agatston CAC score: " + this.totalCAC + "\n");
			writer.write("Vessel level Agatston CAC Score\n");
			writer.write("LM: " + this.lmLevel + "\n");
			writer.write("LAD: " + this.ladLevel + "\n");
			writer.write("LCX: " + this.lcxLevel + "\n");
			writer.write("RCA: " + this.rcaLevel + "\n");
			writer.write("PDA: " + this.pdaLevel + "\n");
			
			File file = new File(filename);
			System.out.println("File saved to: " + file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Method that takes a patient ID, looks up their patient info file, and extracts their full name
	public static String getPatientNameByID(String patientID) {
		String filename = patientID + "_PatientInfo.txt";
		String firstName = "";
		String lastName = "";
		
		//Instantiate a BufferedReader and FileReader for file reading operations
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			//Read from the file and try to match/extract the info
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("First Name:")) {
					firstName = line.substring(line.indexOf(":") + 2);
				} else if (line.startsWith("Last Name:")) {
					lastName = line.substring(line.indexOf(":") + 2);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		//Return the concatenated first and last name
		return firstName + " " + lastName;
	}
	
	//Method that takes a patient ID, looks up their scan results file, and extracts their levels
	public static String getPatientLevelsByID(String patientID) {
		String filename = patientID + "CTResults.txt";
		String totalCAC = "";
		String lm = "";
		String lad = "";
		String lcx = "";
		String rca = "";
		String pda = "";
		
		//Instantiate a BufferedReader and FileReader for file reading operations
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			//Read from the file and try to match/extract the info
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("The total Agatston CAC score:")) {
					totalCAC = line.substring(line.indexOf(":") + 2);
				} else if (line.startsWith("LM:")) {
					lm = line.substring(line.indexOf(":") + 2);
				} else if (line.startsWith("LAD:")) {
					lad = line.substring(line.indexOf(":") + 2);
				} else if (line.startsWith("LCX:")) {
					lcx = line.substring(line.indexOf(":") + 2);
				} else if (line.startsWith("RCA:")) {
					rca = line.substring(line.indexOf(":") + 2);
				} else if (line.startsWith("PDA:")) {
					pda = line.substring(line.indexOf(":") + 2);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		//Return the concatenated information with comma delimiters
		return totalCAC + "," + lm + "," + lad + "," + lcx + "," + rca + "," + pda;
	}
	
	//Method to save the patients ct scan results
	public void updatePatientScanResults(int totalCAC, int lm, int lad, int lcx, int rca, int pda) {
		this.totalCAC = totalCAC;
		this.lmLevel = lm;
		this.ladLevel = lad;
		this.lcxLevel = lcx;
		this.rcaLevel = rca;
		this.pdaLevel = pda;
	}
	
	//Method to return the patient's name
	public String getPatientID() {
		return patientID;
	}
}
