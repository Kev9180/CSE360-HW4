/*	Kevin Johnston
 *  1218407034
 *  CSE360, Carter, Tuesday 1:30pm - 2:45pm
 *  HW4
 */ 

package application;
	
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {	
	//Stage and Scene
	private Stage window;
	private Scene mainScene;
	
	//Buttons
	private Button patientIntakeBtn;
	private Button ctScanTechViewBtn;
	private Button patientViewBtn;
	private Button receptionistSaveBtn;
	private Button ctScanTechSaveBtn;
	private Button backBtn;
	private Button getResultsBtn;
	
	//Labels
	private Label mainViewLabel;
	private Label patientIntakeLabel;
	private Label firstNameLabel;
	private Label lastNameLabel;
	private Label emailLabel;
	private Label phoneNumLabel;
	private Label healthHistoryLabel;
	private Label insuranceIDLabel;
	private Label patientIDLabel;
	private Label totalCACLabel;
	private Label vesselLevelLabel;
	private Label lmLabel;
	private Label ladLabel;
	private Label lcxLabel;
	private Label rcaLabel;
	private Label pdaLabel;
	private Label helloPatientLabel;
	
	//TextFields
	private TextField firstNameTF;
	private TextField lastNameTF;
	private TextField emailTF;
	private TextField phoneNumTF;
	private TextField healthHistoryTF;
	private TextField insuranceIdTF;
	private TextField patientIdTF;
	private TextField totalCACTF;
	private TextField lmTF;
	private TextField ladTF;
	private TextField lcxTF;
	private TextField rcaTF;
	private TextField pdaTF;
	
	//VBoxes
	private VBox mainViewLayout;
	private VBox techLayout;
	private VBox patientIdLayout;
	private VBox patientViewLayout;
	
	//HBoxes
	private HBox helloPatientBox;
	private HBox patientIdBox;
	private HBox totalCACBox;
	private HBox vesselLabelBox;
	private HBox lmBox;
	private HBox ladBox;
	private HBox lcxBox;
	private HBox rcaBox;
	private HBox pdaBox;
	private HBox buttonBox;
	
	//Regions
	Region patientIdSpacer;
	Region totalCACSpacer;
	Region spacerLM;
	Region spacerLAD;
	Region spacerLCX;
	Region spacerRCA;
	Region spacerPDA;
	Region spacerBtn;
	
	//Define the style to be used for the buttons
	String buttonStyle = "-fx-background-color: #0099FF; -fx-text-fill: black; -fx-font-size: 12px; -fx-padding: 10px 20px;";
	String backButtonStyle = "-fx-background-color: #bcbcbc; -fx-text-fill: black; -fx-font-size: 12px; -fx-padding: 10px 20px;";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//root = new BorderPane();
			window = primaryStage;
			window.setTitle("CSE360 HW4 - Kevin Johnston 1218407034");
			
			//Instantiate the buttons
			patientIntakeBtn = new Button("Patient Intake");
			ctScanTechViewBtn = new Button("CT Scan Tech View");
			patientViewBtn = new Button("Patient View");
			
			//Instantiate the labels
			mainViewLabel = new Label("Welcome to Heart Health Imaging and Recording System!");			
			
			//Set up the layout of the main view
			mainViewLayout = new VBox(20);
			mainViewLayout.setAlignment(Pos.CENTER);
			mainViewLabel.setFont(new Font("Arial", 14));
			
			//Set button width
			patientIntakeBtn.setPrefWidth(150);
			ctScanTechViewBtn.setPrefWidth(150);
			patientViewBtn.setPrefWidth(150);
			
			//Assign handlers to buttons
			patientIntakeBtn.setOnAction(e -> showPatientIntakeView());
			ctScanTechViewBtn.setOnAction(e -> showTechnicianView());
			patientViewBtn.setOnAction(e -> showPatientIdEntryView());
			
			//Update the button style
			patientIntakeBtn.setStyle(buttonStyle);
			ctScanTechViewBtn.setStyle(buttonStyle);
			patientViewBtn.setStyle(buttonStyle);
			
			//Update the position of the mainViewLabel
			VBox.setMargin(mainViewLabel, new Insets(20, 0, 50, 0));
			
			//Add the elements to the VBox
			mainViewLayout.getChildren().addAll(mainViewLabel, patientIntakeBtn, ctScanTechViewBtn, patientViewBtn);
			
			//Show the window
			mainScene = new Scene(mainViewLayout,500,300);
			window.setScene(mainScene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method to show the receiptionist view which is where patient intake happens
	private void showPatientIntakeView() {
		//Use a GridPane to display the UI elements.
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		//Instantiate the buttons
		receptionistSaveBtn = new Button("Save");
		backBtn = new Button("Back");
		
		//Instantiate the labels
		patientIntakeLabel = new Label("Patient Intake Form");
		firstNameLabel = new Label("First Name:");
		lastNameLabel = new Label("Last Name:");
		emailLabel = new Label("Email:");
		phoneNumLabel = new Label("Phone Number:");
		healthHistoryLabel= new Label("Health History:");
		insuranceIDLabel = new Label("Insurance ID:");
		
		//Instantiate the textfields
		firstNameTF = new TextField();
		lastNameTF = new TextField();
		emailTF = new TextField();
		phoneNumTF = new TextField();
		healthHistoryTF = new TextField();
		insuranceIdTF = new TextField();
		
		//Update the button styles
		receptionistSaveBtn.setStyle(buttonStyle);
		backBtn.setStyle(backButtonStyle);
		receptionistSaveBtn.setPrefWidth(80);
		backBtn.setPrefWidth(80);
		
		//Set up the button handler for the save button
		receptionistSaveBtn.setOnAction(e -> {
			//Verify that no text fields are blank
			if (!firstNameTF.getText().trim().isEmpty() && !lastNameTF.getText().trim().isEmpty() &&
				!emailTF.getText().trim().isEmpty() && !phoneNumTF.getText().trim().isEmpty() &&
				!healthHistoryTF.getText().trim().isEmpty() && !insuranceIdTF.getText().trim().isEmpty()) {
				
				//Create a new patient
				Patient newPatient = new Patient(
					firstNameTF.getText(),
					lastNameTF.getText(),
					emailTF.getText(),
					phoneNumTF.getText(),
					healthHistoryTF.getText(),
					insuranceIdTF.getText()
				);
				
				//Write the patient info to the patient info file
				newPatient.savePatientInfo();
				
				//Print patient info to console
				System.out.println("Patient created!");
				newPatient.printPatientInfo();
				
				//Go back to the main view
				goBackToMainView();
			} 
			
			//If a text field is blank, display an alert
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Incomplete Form!");
				alert.setHeaderText(null);
				alert.setContentText("Please fill in all fields before saving.");
				alert.showAndWait();
			}
		});
		
		//Set up the button handler for the back button
		backBtn.setOnAction(e -> goBackToMainView());
		
		
		//Set width for the text fields
		firstNameTF.setPrefWidth(250);
		
		//Update the label styling
		patientIntakeLabel.setFont(new Font("Arial", 14));
		
		//Set the position of the patient intake label
		GridPane.setHalignment(patientIntakeLabel, HPos.CENTER);
		GridPane.setConstraints(patientIntakeLabel, 0, 0, 3, 1);
		GridPane.setMargin(patientIntakeLabel, new Insets(0, 0, 30, 80));
		
		//Add the items to the grid
		grid.add(patientIntakeLabel, 0, 0, 2, 1);
		grid.add(firstNameLabel, 0, 1);
		grid.add(firstNameTF, 1, 1);
		grid.add(lastNameLabel, 0, 2);
		grid.add(lastNameTF, 1, 2);
		grid.add(emailLabel, 0, 3);
		grid.add(emailTF, 1, 3);
		grid.add(phoneNumLabel, 0, 4);
		grid.add(phoneNumTF, 1, 4);
		grid.add(healthHistoryLabel, 0, 5);
		grid.add(healthHistoryTF, 1, 5);
		grid.add(insuranceIDLabel, 0, 6);
		grid.add(insuranceIdTF, 1, 6);
		grid.add(backBtn, 0, 9);
		grid.add(receptionistSaveBtn, 2, 9);
		
		//Show the window
		Scene patientIntakeScene = new Scene(grid, 600, 380);
		window.setScene(patientIntakeScene);
	}
	
	//Method to show the Technician View in which a CT Scan Tech will enter a patient's CT scan results
	private void showTechnicianView() {
		//Use a VBox to display the UI elements
		techLayout = new VBox(10);
		techLayout.setAlignment(Pos.TOP_CENTER);
		techLayout.setPadding(new Insets(25, 50, 25, 50));
		
		//Instantiate the labels
		patientIDLabel = new Label("Patient ID:");
		totalCACLabel = new Label("The total Agatston CAC score");
		vesselLevelLabel = new Label("Vessel level Agatston CAC score");
		lmLabel = new Label("LM:");
		ladLabel = new Label("LAD:");
		lcxLabel = new Label("LCX");
		rcaLabel = new Label("RCA");
		pdaLabel = new Label("PDA:");
		
		double labelWidth = 40;
		
		//Define the label widths
		lmLabel.setMinWidth(labelWidth);
		ladLabel.setMinWidth(labelWidth);
		lcxLabel.setMinWidth(labelWidth);
		rcaLabel.setMinWidth(labelWidth);
		pdaLabel.setMinWidth(labelWidth);
		
		//Instantiate the textfields
		patientIdTF = new TextField();
		totalCACTF = new TextField();
		lmTF = new TextField();
		ladTF = new TextField();
		lcxTF = new TextField();
		rcaTF = new TextField();
		pdaTF = new TextField();
		
		//Instantiate the button and update it's style and width
		ctScanTechSaveBtn = new Button("Save");
		ctScanTechSaveBtn.setStyle(buttonStyle);
		ctScanTechSaveBtn.setPrefWidth(80);
		
		//Instantiate the back button and update it's style and width
		backBtn = new Button("Back");
		backBtn.setStyle(backButtonStyle);
		backBtn.setPrefWidth(80);
		
		//Set up the button handler
		ctScanTechSaveBtn.setOnAction(e -> {
			//Check if any of the textfields are empty
			if (!patientIdTF.getText().trim().isEmpty() && !totalCACTF.getText().trim().isEmpty() &&
				!lmTF.getText().trim().isEmpty() && !ladTF.getText().trim().isEmpty() &&
				!lcxTF.getText().trim().isEmpty() && !rcaTF.getText().trim().isEmpty() &&
				!pdaTF.getText().trim().isEmpty()) {
				
				Patient patient = Patient.getPatientByID(patientIdTF.getText().trim());
				
				//If the patient was found in the system, update their results
				if (patient != null) {
					patient.updatePatientScanResults(
						Integer.parseInt(totalCACTF.getText().trim()),
						Integer.parseInt(lmTF.getText().trim()),
						Integer.parseInt(ladTF.getText().trim()),
						Integer.parseInt(lcxTF.getText().trim()),
						Integer.parseInt(rcaTF.getText().trim()),
						Integer.parseInt(pdaTF.getText().trim())		
					);
					
					//Write the patients CT Scan Results to a file and go back to the main view
					patient.savePatientScanInfo();
					goBackToMainView();
				} 
				
				//If the patient was NOT found in the system, alert the CT scan tech
				else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Incorrect Patient ID!");
					alert.setHeaderText(null);
					alert.setContentText("No patient found with ID " + patientIdTF.getText().trim());
					alert.showAndWait();
				}
			}

			//If any fields are empty, alert CT Scan Tech
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Incomplete Form!");
				alert.setHeaderText(null);
				alert.setContentText("Please fill in all fields before saving.");
				alert.showAndWait();
			}
		});
		
		//Set up the action for the back button
		backBtn.setOnAction(e -> goBackToMainView());
		
		//Set the widths for the textfields
		patientIdTF.setPrefWidth(250);
		totalCACTF.setPrefWidth(250);
		lmTF.setPrefWidth(150);
		ladTF.setPrefWidth(150);
		lcxTF.setPrefWidth(150);
		rcaTF.setPrefWidth(150);
		pdaTF.setPrefWidth(150);
		
		//Define an offset amount for the hboxes
		double boxOffset = 20.0;
		
		//Patient ID
		patientIdBox = new HBox(10);
		patientIdSpacer = new Region();
		HBox.setHgrow(patientIdSpacer, Priority.ALWAYS);
		patientIdBox.getChildren().addAll(patientIDLabel, patientIdSpacer, patientIdTF);
		
		//Total Agatston CAC
		totalCACBox = new HBox(10);
		totalCACSpacer = new Region();
		HBox.setHgrow(totalCACSpacer, Priority.ALWAYS);
		totalCACBox.getChildren().addAll(totalCACLabel, totalCACSpacer, totalCACTF);
		
		//Vessel label
		vesselLabelBox = new HBox(10);
		vesselLabelBox.setAlignment(Pos.CENTER_LEFT);
		vesselLabelBox.getChildren().add(vesselLevelLabel);
		
		//LM label
		lmBox = new HBox(10);
		spacerLM = new Region();
		HBox.setMargin(spacerLM, new Insets(0, 0, 0, boxOffset));
		lmBox.getChildren().addAll(lmLabel, spacerLM, lmTF);
		
		//LAD label
		ladBox = new HBox(10);
		spacerLAD = new Region();
		HBox.setMargin(spacerLAD, new Insets(0, 0, 0, boxOffset));
		ladBox.getChildren().addAll(ladLabel, spacerLAD, ladTF);
		
		//LCX label
		lcxBox = new HBox(10);
		spacerLCX = new Region();
		HBox.setMargin(spacerLCX, new Insets(0, 0, 0, boxOffset));
		lcxBox.getChildren().addAll(lcxLabel, spacerLCX, lcxTF);
		
		//RCA label
		rcaBox = new HBox(10);
		spacerRCA = new Region();
		HBox.setMargin(spacerRCA, new Insets(0, 0, 0, boxOffset));
		rcaBox.getChildren().addAll(rcaLabel, spacerRCA, rcaTF);
		
		//PDA label
		pdaBox = new HBox(10);
		spacerPDA = new Region();
		HBox.setMargin(spacerPDA, new Insets(0, 0, 0, boxOffset));
		pdaBox.getChildren().addAll(pdaLabel, spacerPDA, pdaTF);
		
		//Back button and Save button
		buttonBox = new HBox();
		spacerBtn = new Region();
		HBox.setMargin(spacerBtn, new Insets(0, 0, 0, 300));
		buttonBox.getChildren().addAll(backBtn, spacerBtn, ctScanTechSaveBtn);
		
		//Add all the boxes to the VBox
		techLayout.getChildren().addAll(patientIdBox, totalCACBox, vesselLabelBox, lmBox, ladBox, lcxBox, rcaBox, pdaBox, buttonBox);
		
		//Show the window
		Scene technicianView = new Scene(techLayout, 600, 380);
		window.setScene(technicianView);
	}
	
	//Screen that will allow the patient to the enter an ID to display scan results for
	private void showPatientIdEntryView() {
		patientIdLayout = new VBox(20);
		patientIdLayout.setAlignment(Pos.CENTER);
		patientIdLayout.setPadding(new Insets(25, 50, 25, 50));
		
		//Instantiate the patient id label
		patientIDLabel = new Label("Patient ID:");
		
		//Instantiate the patient id textfield and set its pref width
		patientIdTF = new TextField();
		patientIdTF.setPrefWidth(200);
		
		//Instantiate the get results button and update it's style and width
		getResultsBtn = new Button("Get Results");
		getResultsBtn.setStyle(buttonStyle);
		getResultsBtn.setPrefWidth(120);
		
		//Instantiate the back button and update it's style and width
		backBtn = new Button("Back");
		backBtn.setStyle(backButtonStyle);
		backBtn.setPrefWidth(80);
		
		//Set up the action for the get results btn
		getResultsBtn.setOnAction(e -> {
			//If the text field is not empty
			if (!patientIdTF.getText().trim().isEmpty()) {
				Patient patient = Patient.getPatientByID(patientIdTF.getText().trim());
				
				//If a patient was found with that ID, go to the PatientView screen
				if (patient != null) {
					showPatientView(patient);
				} 
				
				//If no patient was found, alert the user
				else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Incorrect Patient ID!");
					alert.setHeaderText(null);
					alert.setContentText("No patient found with ID " + patientIdTF.getText().trim());
					alert.showAndWait();
				}
			} 
			
			//If the text field is empty, alert the user
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Missing Patient ID");
				alert.setHeaderText(null);
				alert.setContentText("Please enter your patient ID.");
				alert.showAndWait();
			}
		});
		
		//Set up the action for the back button
		backBtn.setOnAction(e -> goBackToMainView());
		
		//Hbox to hold the buttons
		buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(backBtn, getResultsBtn);
		
		//Add all the items to the VBox
		patientIdLayout.getChildren().addAll(patientIDLabel, patientIdTF, buttonBox);
		
		//Show the scene
		Scene patientIdScene = new Scene(patientIdLayout, 300, 200);
		window.setScene(patientIdScene);
	}
	
	//Method that will show a specific patient's vessel levels and total Agatston CAC score
	private void showPatientView(Patient patient) {
		//Use a VBox to display the UI elements
		patientViewLayout = new VBox(10);
		patientViewLayout.setAlignment(Pos.TOP_CENTER);
		patientViewLayout.setPadding(new Insets(25, 50, 25, 50));
		
		//Instantiate the labels
		helloPatientLabel = new Label();
		totalCACLabel = new Label("The total Agatston CAC score");
		lmLabel = new Label("LM:");
		ladLabel = new Label("LAD:");
		lcxLabel = new Label("LCX");
		rcaLabel = new Label("RCA");
		pdaLabel = new Label("PDA:");
		
		double labelWidth = 40;
		
		//Define the label widths
		lmLabel.setMinWidth(labelWidth);
		ladLabel.setMinWidth(labelWidth);
		lcxLabel.setMinWidth(labelWidth);
		rcaLabel.setMinWidth(labelWidth);
		pdaLabel.setMinWidth(labelWidth);
		
		//Instantiate the TextFields
		totalCACTF = new TextField();
		lmTF = new TextField();
		ladTF = new TextField();
		lcxTF = new TextField();
		rcaTF = new TextField();
		pdaTF = new TextField();
		
		//Set the pref widths for the TextFields
		totalCACTF.setPrefWidth(250);
		lmTF.setPrefWidth(150);
		ladTF.setPrefWidth(150);
		lcxTF.setPrefWidth(150);
		rcaTF.setPrefWidth(150);
		pdaTF.setPrefWidth(150);
		
		//Instantiate the back button and update it's style and width
		backBtn = new Button("Back");
		backBtn.setStyle(backButtonStyle);
		backBtn.setPrefWidth(80);
		backBtn.setOnAction(e -> goBackToMainView());
		
		//Extract the patient's name from their _PatientInfo file
		String patientName = Patient.getPatientNameByID(patient.getPatientID());
		
		//Update the text value of the helloPatientLabel
		helloPatientLabel.setText("Hello " + patientName + "!");
		
		//Extract the patient's vessel levels from their CTResults file
		String patientLevels = Patient.getPatientLevelsByID(patient.getPatientID());
		String[] levelsArray = patientLevels.split(",", 0);
		
		//Update the textfields with their appropriate values
		totalCACTF.setText(levelsArray[0]);
		lmTF.setText(levelsArray[1]);
		ladTF.setText(levelsArray[2]);
		lcxTF.setText(levelsArray[3]);
		rcaTF.setText(levelsArray[4]);
		pdaTF.setText(levelsArray[5]);
		
		//Update the textfields to be non-editable
		totalCACTF.setEditable(false);
		lmTF.setEditable(false);
		ladTF.setEditable(false);
		lcxTF.setEditable(false);
		rcaTF.setEditable(false);
		pdaTF.setEditable(false);
		
		//Patient Greeting
		helloPatientBox = new HBox(10);
		helloPatientBox.setAlignment(Pos.CENTER);
		helloPatientBox.getChildren().add(helloPatientLabel);
		
		//Total Agatston CAC
		totalCACBox = new HBox(10);
		totalCACSpacer = new Region();
		HBox.setHgrow(totalCACSpacer, Priority.ALWAYS);
		totalCACBox.getChildren().addAll(totalCACLabel, totalCACSpacer, totalCACTF);
		
		double boxOffset = 20.0;
		
		//LM label
		lmBox = new HBox(10);
		spacerLM = new Region();
		HBox.setMargin(spacerLM, new Insets(0, 0, 0, boxOffset));
		lmBox.getChildren().addAll(lmLabel, spacerLM, lmTF);
		
		//LAD label
		ladBox = new HBox(10);
		spacerLAD = new Region();
		HBox.setMargin(spacerLAD, new Insets(0, 0, 0, boxOffset));
		ladBox.getChildren().addAll(ladLabel, spacerLAD, ladTF);
		
		//LCX label
		lcxBox = new HBox(10);
		spacerLCX = new Region();
		HBox.setMargin(spacerLCX, new Insets(0, 0, 0, boxOffset));
		lcxBox.getChildren().addAll(lcxLabel, spacerLCX, lcxTF);
		
		//RCA label
		rcaBox = new HBox(10);
		spacerRCA = new Region();
		HBox.setMargin(spacerRCA, new Insets(0, 0, 0, boxOffset));
		rcaBox.getChildren().addAll(rcaLabel, spacerRCA, rcaTF);
		
		//PDA label
		pdaBox = new HBox(10);
		spacerPDA = new Region();
		HBox.setMargin(spacerPDA, new Insets(0, 0, 0, boxOffset));
		pdaBox.getChildren().addAll(pdaLabel, spacerPDA, pdaTF);
		
		//Button
		buttonBox = new HBox();
		spacerBtn = new Region();
		HBox.setMargin(spacerBtn, new Insets(0, 0, 0, 300));
		buttonBox.getChildren().addAll(spacerBtn, backBtn);
		
		//Add all the items to the VBox
		patientViewLayout.getChildren().addAll(helloPatientBox, totalCACBox, lmBox, ladBox, lcxBox, rcaBox, pdaBox, buttonBox);
		
		//Show the window
		Scene patientView = new Scene(patientViewLayout, 600, 380);
		window.setScene(patientView);
	}
	
	//Method that will take the user back to the main view
	private void goBackToMainView() {
		window.setScene(mainScene);
	}
	
	//Main
	public static void main(String[] args) {
		launch(args);
	}
}
