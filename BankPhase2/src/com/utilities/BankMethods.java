package com.utilities;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import com.accounts.Account;
import com.accounts.Checking;
import com.accounts.Gold;
import com.accounts.Regular;
import com.customers.Customer;
import com.transactions.Transaction;

// http://docs.oracle.com/javase/tutorial/essential/io/index.html

public class BankMethods {
	public String[] filename = new String[9]; // contains the path and file names of data files
	public String[] backname = new String[9]; // contains the path and file name of the backup data files
	public String[] paths = new String[2];
	public String[] txtFillColor = new String[7];
	public Font[] arialFont = new Font[4];
	public String bankName; // bank name that will be stored in the config dat file
	public int bakup; // a backup token that will keep track of the backup number so backups will never over write each other
	public String autoAcctNum;
	// the general bank containers
	public ArrayList<Customer> customers = new ArrayList<Customer>();
	public ArrayList<Account> accounts = new ArrayList<Account>();
	public ArrayList<Account> rejects = new ArrayList<Account>();
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	// instance of the BankUtilities class
	public BankUtilities bu = new BankUtilities();
	

	public void loadFont() {
		arialFont[0] = Font.font("Arial", 14);
		arialFont[1] = Font.font("Arial", 18);
		arialFont[2] = Font.font("Arial", FontWeight.BOLD, 18);
		arialFont[3] = Font.font("Arial", 25);
	}
	
	public void loadColors() {
		txtFillColor[0] = "-fx-text-fill: blue;";
		txtFillColor[1] = "-fx-text-fill: red;";
		txtFillColor[2] = "-fx-text-fill: black;";
		txtFillColor[3] = "-fx-text-fill: white;";
		txtFillColor[4] = "-fx-text-fill: green;";
		txtFillColor[5] = "-fx-base: blue;";
		txtFillColor[6] = "-fx-base: red;";
	}
	/** load file names<br><br>
	 * this also loads:<br>
	 * the backup<br>
	 * the paths<br>
	 * the fonts<br>
	 * and the colors<br>
	 * 
	 *  0 - config<br>
	 *  1 - customer<br>
	 *  2 - checking<br>
	 *  3 - regular<br>
	 *  4 - gold<br>
	 *  5 - transactions<br>
	 *  8 - accounts<br>
	 * 
	 */
	public void loadFileName() {
		loadPaths();
		loadBackup();
		loadFont();
		loadColors();
		filename[0] = paths[0] + "/config.dat"; // individually saved
		filename[1] = paths[0] + "/customers.dat"; // individually saved
		filename[2] = paths[0] + "/checking.dat"; // individually saved
		filename[3] = paths[0] + "/regular.dat"; // individually saved
		filename[4] = paths[0] + "/gold.dat"; // individually saved
		filename[5] = paths[0] + "/transactions.dat"; // individually saved
		filename[6] = paths[0] + "/oaccounts.dat"; // saved as an object
		filename[7] = paths[0] + "/ocustomers.dat"; // saved as an object
		filename[8] = paths[0] + "/otransactions.dat"; // saved as an object
	}
	
	/** load backup<br><br>
	 * 
	 * this loads the filename that will be used to store backup copies of the files<br>
	 * the extension will be added when the file is initially created<br><br>
	 * 
	 * example: config0.dat and if that is used then config1.dat<br>
	 * 
	 */
	public void loadBackup() {
		loadPaths();
		backname[0] = paths[1] + "/config"; // individually saved
		backname[1] = paths[1] + "/customers"; // individually saved
		backname[2] = paths[1] + "/checking"; // individually saved
		backname[3] = paths[1] + "/regular"; // individually saved
		backname[4] = paths[1] + "/gold"; // individually saved
		backname[5] = paths[1] + "/transactions"; // individually saved
		backname[6] = paths[1] + "/oaccounts"; // saved as an object
		backname[7] = paths[1] + "/ocustomers"; // saved as an object
		backname[8] = paths[1] + "/otransactions"; // saved as an object
	}
	
	/** load paths<br><br>
	 * 
	 * this loads the paths into an array that will used to create the folders<br>
	 * for the first time and also this will add to the file names so the text<br>
	 * files will be stored in sub folders rather in the main folder with the source code<br>
	 * 
	 */
	public void loadPaths() {
		paths[0] = "data";
		paths[1] = "backup";
		try {
			File myPath;
			for (int x = 0; x < paths.length; x++) {
				myPath = new File(paths[x]);
				if (myPath.isDirectory()) {
					continue;
				} else {
					myPath.mkdir();
				}
			}
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(null, "Security setting for creating folders invalid! Unable to create Paths!", "Error Paths", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
  	/** get customer pane<br><br>
   	 * 
   	 * declare the scene for making the customer screen for adding<br>
   	 * new customers<br><br>
   	 * 
   	 * 0 - create Customer<br>
   	 * 1 - lookup Customer<br>
   	 * 
   	 * 
   	 * @return the boarder pane that will generate the stage and scene
   	 */
	  public BorderPane getCustPane(){
		  
		  // set up the labels
		  Label lblID = new Label("Customer ID");
		  lblID.setFont(arialFont[1]);
		  
		  Label lblName = new Label("Customers Name");
		  lblName.setFont(arialFont[1]);
		  
		  Label lblMessage = new Label("");
		  lblMessage.setFont(arialFont[1]);
		  
		  // create the text boxes and adjust their font and font size and establish the tool tips that
		  // will be used for each individual box
		  TextField txtID = new TextField();
		  txtID.setFont(arialFont[1]);
		  txtID.setStyle(txtFillColor[0]);
		  txtID.setPromptText("Customer ID");
		  txtID.setTooltip(new Tooltip("Type the number 0 and press Enter to automaticly generate Customer ID"));
		  
		  TextField txtName = new TextField();
		  txtName.setFont(arialFont[1]);
		  txtName.setStyle(txtFillColor[0]);
		  txtName.setPromptText("Customer Name");
		  txtName.setTooltip(new Tooltip("Enter the Customer's First and Last name"));
		  
		  // declare a font that will be used for the buttons
		  
		  // generate buttons and tool tips
		  Button btnAdd = new Button("Add");
		  btnAdd.setFont(arialFont[2]);
		  btnAdd.setStyle(txtFillColor[5]);
		  btnAdd.setTooltip(new Tooltip("Add entered customer into the bank system"));
		  
		  Button btnExit = new Button("Exit");
		  btnExit.setFont(arialFont[2]);
		  btnExit.setStyle(txtFillColor[6]);
		  btnExit.setTooltip(new Tooltip("Exit!\nPressing this will not save the currently entered information!"));
		  
		  // put the ID label and text box together vertically
		  VBox idPane = new VBox(2);
		  idPane.setAlignment(Pos.CENTER);
		  idPane.getChildren().addAll(lblID, txtID);
		 
		  // put the Name label and text box together vertically
		  VBox namePane = new VBox(2);
		  namePane.setAlignment(Pos.CENTER);
		  namePane.getChildren().addAll(lblName, txtName);
		  
		  // put in a label that will be used as a message center for the user
		  HBox messagePane = new HBox(1);
		  messagePane.getChildren().add(lblMessage);
		  messagePane.setAlignment(Pos.CENTER);
		  
		  // add the ID and Name panes horizontally to the txtPane which
		  // will be put in the top pane of the window
		  HBox txtPane = new HBox(2);
		  txtPane.setPadding(new Insets(30,30,30,30));  
		  txtPane.getChildren().addAll(idPane, namePane);
		  txtPane.setAlignment(Pos.TOP_CENTER);
		  
		  // add the buttons horizontally to the bottom pane
		  HBox bottomPane = new HBox(2);
		  bottomPane.setPadding(new Insets(15,15,15,15));;
		  bottomPane.getChildren().addAll(btnAdd, btnExit);
		  bottomPane.setAlignment(Pos.CENTER);
		  
		  // generate the Panes and set the border color to blue
		  BorderPane custMainPane = new BorderPane();
		  custMainPane.setTop(txtPane);
		  custMainPane.setCenter(messagePane);
		  custMainPane.setBottom(bottomPane);
		  custMainPane.setStyle("-fx-border-color: blue");

		  // when the user presses any key inside the ID box
		  txtID.setOnKeyPressed(e -> {
			  lblMessage.setText("");
		  });
		  
		  // when the user presses the Enter key in the ID box
		  txtID.setOnAction(e -> {
			  if (txtID.getText().equals("0")) {
				  txtID.setText(bu.generateUniqueAcctNumber());
			  } else {
				  boolean isOk = true;
				  for (Customer c: customers) {
					  if (c.getCustomerID().equals(txtID.getText())) {
						  isOk = false;
					  }
				  }
				  if (isOk) {
					  txtName.requestFocus();
				  } else {
					  lblMessage.setText("Customer ID already Exists!\nPlease choose a different Customer ID");
					  lblMessage.setStyle(txtFillColor[1]);
				  }
			  }
		  });
		  
		  // when the user presses any key in the Name box
		  txtName.setOnKeyTyped(e -> {
				  lblMessage.setText(" ");
				  lblMessage.setStyle(txtFillColor[2]);
		  });
		  
		  // when the user presses Enter key in the Name box
		  txtName.setOnAction(e -> {
			  btnAdd.requestFocus();
		  });
		  
		  // when the user presses any key on the Add button
		  btnAdd.setOnKeyPressed(e -> {
			  boolean cc = false;
			  Stage myStage = (Stage) txtID.getScene().getWindow();
			  cc = createCustomer(txtID, txtName, lblMessage);
			  if (!cc) {
				  myStage.requestFocus();
				  txtID.requestFocus();
			  } else {
					  txtID.setText("");
					  txtName.setText("");
				  myStage.requestFocus();
				  txtID.requestFocus();
			  }
		  });
		  
		  // when the user clicks the Add button
		  btnAdd.setOnAction(e -> {
			  boolean cc = false;
			  Stage myStage = (Stage) txtID.getScene().getWindow();
			  cc = createCustomer(txtID, txtName, lblMessage);
			  if (!cc) {
				  myStage.requestFocus();
				  txtID.requestFocus();
			  } else {
				  txtID.setText("");
				  txtName.setText("");
				  myStage.requestFocus();
				  txtID.requestFocus();
			  }
		  });
		  
		  // when the user clicks on the Exit button
		  btnExit.setOnAction(e -> {
			  btnExit.getScene().getWindow().hide();
			  });
		  // this returns the pane to the calling Scene
	  return custMainPane;
	  }

	  /** create Customer<br><br>
	   * 
	   * a method that will perform the creation of the Customer<br>
	   * this will allow for multiple areas to create the Customer<br>
	   * and easier to maintain and debug the code<br>
	   * 
	   * @param txtID the TextField for CustomerID
	   * @param txtName the TextField for CustomerName
	   * @param message the message that will be displayed to the user on the form
	   * @return whether it was successful (true or false)
	   */
	  public boolean createCustomer(TextField txtID, TextField txtName, Label lblMessage) {
		  // what is the current size of the customer table (array list)
		  // this will be used to determine if the customer was added successfully
		  int element = customers.size();
		  
		  // did the user enter any information in the ID box
		  if(txtID.getText().isEmpty()) {
			  lblMessage.setText("Please Enter Customer ID");
			  lblMessage.setFont(arialFont[1]);
			  lblMessage.setStyle(txtFillColor[1]);
		  } else if (txtName.getText().isEmpty()) { // did the user enter any information in the Name box
			  lblMessage.setText("Please Enter Customer Name");
			  lblMessage.setFont(arialFont[1]);
			  lblMessage.setStyle(txtFillColor[1]);
		  } else { // since there were no errors that I detected, add the customer to the table
			  Customer customer = new Customer(txtID.getText(), txtName.getText());
			  customers.add(customer);
			  
			  // check to see if the customer was added to the table and return true if yes and false if not
			  if (element < customers.size()) {
				  lblMessage.setText("Customer Created successfully!");
				  lblMessage.setFont(arialFont[1]);
				  lblMessage.setStyle(txtFillColor[0]);
				  return true;
			  } else { // notify the user that the customer was not added
				  if (element == customers.size()) {
					  lblMessage.setText("Customer Creation Unsessful!!");
					  lblMessage.setFont(arialFont[1]);
					  lblMessage.setStyle(txtFillColor[1]);
				  }
			  }
		  }
		  return false;
	  }
	  
	  /** look up customer and if it was found return it or return null<br><br>
	   * 
	   * currently not used but I expect this to be added later
	   * 
	   * @param txtID
	   * @param txtName
	   * @return
	   */
	  public Customer lookupCustomer(TextField txtID, TextField txtName) {
		  Customer customer = customers.get(0);
		  boolean isFound = false;
		  for (int x = 0; x < customers.size(); x++) {
			  if (customers.get(x).getCustomerID().equals(txtID) && customers.get(x).getCustomerName().equals(txtName)) {
				  customer = customers.get(x);
				  isFound = true;
			  }
		  }
		  if (isFound) {
			  return customer;
		  }
		  
		  return null;
	  }
	  
	  /** add Account pane<br><br>
	   * 
	   * this paints the scene for the account pane<br>
	   * and generates the code for adding a new Account<br><br>
	   * 
	   * 0 - Checking<br>
	   * 1 - Regular<br>
	   * 2 - Gold<br>
	   * 
	   * @param acctNum this determines which account to create: Checking, Regular, or Gold
	   * @return the boarder pane that will generate the form
	   */
	public BorderPane addAccountPane(int acctNum) {
		  String acctType = "";
		  
		  switch (acctNum) {
			case 0: // checking
				acctType = "Checking Account";
				autoAcctNum = "CA" + bu.generateUniqueAccountNumber();
				break;
			case 1:// regular
				acctType = "Regular Account";
				autoAcctNum = "RA" + bu.generateUniqueAccountNumber();
				break;
			case 2: // gold
				acctType = "Gold Account";
				autoAcctNum = "GA" + bu.generateUniqueAccountNumber();
				break;
			default:
				//autoAcctNum = "";
		  }
		  // declare buttons and set their styles
		  Button btnAdd = new Button("Add");
		  btnAdd.setFont(arialFont[1]);
		  btnAdd.setStyle(txtFillColor[5]);
		  btnAdd.setTooltip(new Tooltip("Add this Account into the banking system"));
		  
		  Button btnExit = new Button("Exit");
		  btnExit.setFont(arialFont[1]);
		  btnExit.setStyle(txtFillColor[6]);
		  btnExit.setTooltip(new Tooltip("Exit! \nPressing this will not save the currently entered information!"));

		  TextArea listCust = new TextArea();
		  listCust.setPrefRowCount(5);
		  listCust.setPrefColumnCount(35);
		  listCust.setEditable(false);
		  listCust.setTooltip(new Tooltip("Highlight the Customer ID you want and it will be put in the Customer ID box!"));
		  listCust.setFont(arialFont[1]);
		  
		 FlowPane leftPane = new FlowPane();
		 leftPane.setAlignment(Pos.CENTER);
		 leftPane.setHgap(5);
		 leftPane.setVgap(5);
		 leftPane.getChildren().addAll(listCust);
		  
		  // set up the account type heading
		  Label lbltxtDisplayBoxAccounts = new Label(acctType);  // heading
		  lbltxtDisplayBoxAccounts.setFont(arialFont[3]);
		  lbltxtDisplayBoxAccounts.setStyle(txtFillColor[0]);
		  // set up the error display message area
		  Label lbltxtDisplayBoxMessage = new Label(" "); // clear error reporting
		  
		  // declare the heading and labels
		  Label lblAccountNumber = new Label("Account Number");
		  lblAccountNumber.setFont(arialFont[1]);
		  lblAccountNumber.setStyle(txtFillColor[0]);
		  
		  Label lblCustomerID = new Label("Customer ID");
		  lblCustomerID.setFont(arialFont[1]);
		  lblCustomerID.setStyle(txtFillColor[0]);
		  
		  Label lblOpeningBalance = new Label("Opening Balance");
		  lblOpeningBalance.setFont(arialFont[1]);
		  lblOpeningBalance.setStyle(txtFillColor[0]);
		  
		  // declare the text boxes and tool tips
		  TextField txtAccountNumber = new TextField();
		  txtAccountNumber.setFont(arialFont[1]);
		  txtAccountNumber.setStyle(txtFillColor[0]);
		  txtAccountNumber.setPromptText("Hover for Instructions");
		  txtAccountNumber.setTooltip(new Tooltip("Type the number 0 and press Enter to generate automatic Account Number"));
		  
		  TextField txtCustomerID = new TextField();
		  txtCustomerID.setFont(arialFont[1]);
		  txtCustomerID.setStyle(txtFillColor[0]);
		  txtCustomerID.setPromptText("Customer ID");
		  txtCustomerID.setTooltip(new Tooltip("Enter Customer ID or type the number 0 to show a list of available customers"));
		  
		  TextField txtOpeningBalance = new TextField();
		  txtOpeningBalance.setFont(arialFont[1]);
		  txtOpeningBalance.setStyle(txtFillColor[0]);
		  txtOpeningBalance.setPromptText("Example: 789.74");
		  txtOpeningBalance.setTooltip(new Tooltip("Opening Balance of this Account, Example: 854.34"));
		  
		  // put account number label and text box together in a vertical box
		  VBox accountPane = new VBox(2);
		  accountPane.getChildren().addAll(lblAccountNumber, txtAccountNumber);
		  accountPane.setAlignment(Pos.CENTER);
		  
		  // put the customer id label and text box together in a vertical box
		  VBox customerPane = new VBox(2);
		  customerPane.getChildren().addAll(lblCustomerID,txtCustomerID);
		  customerPane.setAlignment(Pos.CENTER);
		 
		  // put the opening balance label and text box in a vertical box
		  VBox balancePane = new VBox(2);
		  balancePane.getChildren().addAll(lblOpeningBalance,txtOpeningBalance);
		  balancePane.setAlignment(Pos.CENTER);
		 
		  // put the display message label in a horizontal box
		  HBox accountDisplayPane = new HBox(1);
		  accountDisplayPane.setPadding(new Insets(20,15,25,15));
		  accountDisplayPane.getChildren().addAll(lbltxtDisplayBoxAccounts);
		  accountDisplayPane.setAlignment(Pos.CENTER);
		  
		  // add the customer, account, and opeening balance panes into an horizontal box
		  HBox topPane = new HBox(3);
		  topPane.setPadding(new Insets(15,15,15,15));
		  topPane.getChildren().addAll(customerPane,accountPane,balancePane);
		  topPane.setAlignment(Pos.CENTER);
		  
		  // add the account type display label and top pane into a vertical box
		  VBox finaTopPane = new VBox(2);
		  finaTopPane.getChildren().addAll(accountDisplayPane, topPane);
		  finaTopPane.setAlignment(Pos.CENTER);
		  
		  // add the label display box to a horizontal box
		  VBox centerPane = new VBox(2);
		  centerPane.setPadding(new Insets(55,15,55,15));
		  centerPane.getChildren().addAll(lbltxtDisplayBoxMessage, leftPane);
		  centerPane.setAlignment(Pos.CENTER);
		  
		  // add the buttons to the bottom pane
		  HBox bottomPane = new HBox(2);
		  bottomPane.setPadding(new Insets(15,15,15,15));
		  bottomPane.getChildren().addAll(btnAdd, btnExit);
		  bottomPane.setAlignment(Pos.CENTER);
		  
		  // generate the actual panes that will create the form
		  // and return it to the scene
		  BorderPane mainPane = new BorderPane();
		  mainPane.setTop(finaTopPane);
		  mainPane.setCenter(centerPane);
		  mainPane.setBottom(bottomPane);
		  mainPane.setStyle("-fx-border-color: blue");
		  
		  // if any key is pressed in the account number box, clear the message display label
		  txtAccountNumber.setOnKeyTyped(e ->{
			  lbltxtDisplayBoxMessage.setText(" ");
	     });
		  
		  // if the enter key is pressed in the account number move to the next box
		  txtAccountNumber.setOnAction(e -> {
			  if (txtAccountNumber.getText().equals("0")) {
				  txtAccountNumber.setText(autoAcctNum);
				  //txtAccountNumber.setDisable(true);
			  } else {
				  boolean isOk = true;
				  for (Account a: accounts) {
					  if (a.getAccountNumber().equals(txtAccountNumber.getText())) {
						  isOk = false;
					  }
				  }
				  if (isOk) {
					  txtOpeningBalance.requestFocus();
				  } else {
					  lbltxtDisplayBoxMessage.setText("Account Number already used! Please choose a differeent Account Number!");
					  lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
				  }
			  }
		  });
		  
		  listCust.setOnMouseClicked(e -> {
			  txtCustomerID.setText(listCust.getSelectedText().trim());
			  txtCustomerID.requestFocus();
		  });
		  
		  // if the enter key is pressed in the customerID move to the next box
		  txtCustomerID.setOnAction(e -> {
				 if (txtCustomerID.getText().equals("0")) {
					 listCust.clear();
					 listCust.setText("");
					 for (Customer c: customers) {
						  listCust.appendText(c.toString() + "\n");
					  }
					 listCust.setFont(arialFont[1]);
					 listCust.setStyle(txtFillColor[0]);
				  } else {
					  boolean isOk = false;
					  for (Customer c: customers) {
						  if (c.getCustomerID().equals(txtCustomerID.getText())) {
							  isOk = true;
						  }
					  }
					 if (isOk) {
						 txtAccountNumber.requestFocus();
					 } else {
						 lbltxtDisplayBoxMessage.setText("Account Number was NOT found! Please re-enter Account Number!");
						 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
					 }
				  }
		  });
		  
	     
		  // if any key is pressed in the customer id box, clear the message display label
	     txtCustomerID.setOnKeyTyped(e -> {
	    	 lbltxtDisplayBoxMessage.setText(" ");
	     });
	     
	     // if any key is pressed in the opening balance box, clear the message display label
	     txtOpeningBalance.setOnKeyPressed(e -> {
	    	 lbltxtDisplayBoxMessage.setText(" ");
	     });
	     
	     // if the enter key is pressed in the opening balance box, validate the input before continuing
	     txtOpeningBalance.setOnAction(e -> {
	    	double amount = -1;
	    	try {
		    	amount = Double.parseDouble(txtOpeningBalance.getText());
		    	btnAdd.requestFocus();
		    	if (amount < 0.0) {
    				 lbltxtDisplayBoxMessage.setText("Negative Opening Balances are not allow!\nPlease enter a positive Opening Balance!");
    				 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
    				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
    				 Stage myStage = (Stage) txtOpeningBalance.getScene().getWindow();
    				 myStage.requestFocus();
	    			 txtOpeningBalance.requestFocus();
		    	}
    		 } catch (NumberFormatException h) {
    			 lbltxtDisplayBoxMessage.setText("I was expecting a Account Opening Balance, Please re-enter");
    			 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
    			 txtOpeningBalance.requestFocus();
    		 } catch (InputMismatchException  g) {
    			 lbltxtDisplayBoxMessage.setText("You must enter the Opening Balance to the Account");
    			 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
    			 txtOpeningBalance.requestFocus();
    		 } catch (NoSuchElementException f) {
    			 lbltxtDisplayBoxMessage.setText("Out of Range, Please re-enter the Opening Balance Ammount!");
    			 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
    			 txtOpeningBalance.requestFocus();
    		 }
	     });
	     
	     // if the user pressed enter on the add button, call the create account method
	     btnAdd.setOnKeyPressed(e -> {
	    	 btnAdd.getOnKeyTyped();
	    	 boolean isAcctOk = false;
	    	 isAcctOk = createAccount( txtAccountNumber, txtCustomerID, txtOpeningBalance, lbltxtDisplayBoxMessage, acctNum);
	    	 if (isAcctOk) {
	    		 txtAccountNumber.setDisable(false);
	    		 txtCustomerID.setText("");
	    		 txtAccountNumber.setText("");
	    		 txtOpeningBalance.setText("");
	    	 }
	     });

	     // if the user clicks the add button, call the create account method
	     btnAdd.setOnAction(e -> {
	    	 boolean isAcctOk = false;
	    	 isAcctOk = createAccount( txtAccountNumber, txtCustomerID, txtOpeningBalance, lbltxtDisplayBoxMessage, acctNum);
	    	 if (isAcctOk) {
	    		 txtAccountNumber.setDisable(false);
	    		 txtCustomerID.setText("");
	    		 txtAccountNumber.setText("");
	    		 txtOpeningBalance.setText("");
	    		 listCust.clear();
	    	 }
	     });
	     
	     // if the user presses enter on the exit button, close the window
	     btnExit.setOnKeyTyped(e -> {
	    	 btnExit.getScene().getWindow().hide();
	    });		     

	     // if the user clicks the exit button close the window
	     btnExit.setOnAction(e -> {
	    	 btnExit.getScene().getWindow().hide();
	    });		     
		return mainPane;
	  }
	  
// ********************************************************************** processTransaction pane **********************************************************************
	  
	  	/** process transaction pane<br><br>
	  	 * 
	  	 * this will paint the window for making deposits and withdrawals<br>
	  	 * the following types are allowed<br><br>
	  	 * 
	  	 * 0 - deposit<br>
	  	 * 1 - withdrawal<br>
	  	 * 
	  	 * @param tranType this will tell the window which type is calling
	  	 * @return it will return the format to the stage that is calling
	  	 */
		public BorderPane processTransactionsPane(int tranType){
			String strTitle = "";

			switch (tranType) {
			case 0:
				strTitle = "Make Deposit";
				break;
			case 1:
				strTitle = "Make Withdrawal";
				break;
			}
			Label lblPageTitle = new Label(strTitle);
			lblPageTitle.setFont(arialFont[3]);
			lblPageTitle.setStyle(txtFillColor[0]);
			
			Label lblAccountNum = new Label("Account #");
			lblAccountNum.setFont(arialFont[1]);
			lblAccountNum.setStyle(txtFillColor[0]);
			
			Label lblAmount = new Label("Amount");
			lblAmount.setFont(arialFont[1]);
			lblAmount.setStyle(txtFillColor[0]);
			
			Label lblSuccess = new Label("");
			lblSuccess.setFont(arialFont[1]);
			lblSuccess.setStyle(txtFillColor[1]);
			
			TextField txtAccountNum = new TextField();
			txtAccountNum.setFont(arialFont[1]);
			txtAccountNum.setStyle(txtFillColor[0]);
			txtAccountNum.setTooltip(new Tooltip("Type 0 and press Enter to look up Account Numbers"));
			txtAccountNum.setPromptText("Bank Account Number");
			
			TextField txtAmount = new TextField();
			txtAmount.setFont(arialFont[1]);
			txtAmount.setStyle(txtFillColor[0]);
			txtAmount.setTooltip(new Tooltip("Enter the Opening Balance"));
			txtAmount.setPromptText("Example: 853.46");
			
			TextArea lstAccount = new TextArea();
			lstAccount.setPrefRowCount(5);
			lstAccount.setEditable(false);
			lstAccount.setFont(arialFont[1]);
			lstAccount.setStyle(txtFillColor[0]);
			
			Button btnAdd = new Button("Add");
			btnAdd.setFont(arialFont[2]);
			btnAdd.setStyle(txtFillColor[5] + txtFillColor[3]);
			btnAdd.setDisable(true);
			
			Button btnExit = new Button("Exit");
			btnExit.setFont(arialFont[2]);
			btnExit.setStyle(txtFillColor[6] + txtFillColor[3]);
			
			HBox topPane = new HBox(4);
			topPane.setPadding(new Insets(35, 5, 35,5));
			topPane.getChildren().add(lblPageTitle);
			topPane.setAlignment(Pos.TOP_CENTER);
			
			VBox accountPane = new VBox(5);
			accountPane.getChildren().addAll(lblAccountNum, txtAccountNum);
			accountPane.setAlignment(Pos.CENTER);
			
			VBox amountPane = new VBox(5);
			amountPane.getChildren().addAll(lblAmount, txtAmount);
			amountPane.setAlignment(Pos.CENTER);
			
			HBox groupPane = new HBox(5);
			groupPane.getChildren().addAll(accountPane, amountPane);
			groupPane.setAlignment(Pos.CENTER);
			
			VBox listPane = new VBox(5);
			listPane.getChildren().add(lstAccount);
			listPane.setMaxWidth(325);
			listPane.setAlignment(Pos.CENTER);
			
			VBox successPane = new VBox(5);
			successPane.setPadding(new Insets(35,5,35,5));
			successPane.getChildren().add(lblSuccess);
			successPane.setAlignment(Pos.CENTER);
			
			
			VBox centerPane = new VBox(5);
			centerPane.getChildren().addAll(groupPane, successPane, listPane);
			centerPane.setAlignment(Pos.CENTER);
			
			HBox bottomPane = new HBox(5);
			bottomPane.setPadding(new Insets(15,5,15,5));
			bottomPane.getChildren().addAll(btnAdd, btnExit);
			bottomPane.setAlignment(Pos.BOTTOM_CENTER);
			
		 
			 BorderPane mainPane = new BorderPane();
			 mainPane.setTop(topPane);
			 mainPane.setCenter(centerPane);
			 mainPane.setBottom(bottomPane);
		     mainPane.setStyle("-fx-border-color: blue");
		
		     txtAccountNum.setOnAction(e -> {
		    	 if (txtAccountNum.getText().equals("0")) {
		    		 for (Account a: accounts) {
		    			 lstAccount.appendText(a.getAccountNumber() + "\n");
		    		 }
		    	 } else if (txtAccountNum.getText().isEmpty()) {
		    		 lblSuccess.setText("You must enter an Account Number!");
		    		 lblSuccess.setStyle(txtFillColor[1]);
		    		 txtAccountNum.requestFocus();
		    	 } else {
			    	 boolean isFound = false;
			    	 for (Account a: accounts) {
			    		 if (a.getAccountNumber().equals(txtAccountNum.getText())) {
					    	 isFound = true;
			    			 break;
			    		 }
			    	 }
			    	 if (isFound) {
			    		 lblSuccess.setText("Account Number Found!");
			    		 btnAdd.setDisable(false);
			    		 lblSuccess.setStyle(txtFillColor[0]);
					     txtAmount.requestFocus();
			    	 } else {
		    			 lblSuccess.setText("Account Number was not found! Please try again!");
		    			 lblSuccess.setStyle(txtFillColor[1]);
		    			 txtAccountNum.requestFocus();
			    	}
		    	 }
		     });
		     
		     txtAccountNum.setOnKeyPressed(e -> {
		    	 lblSuccess.setText(" ");
		     });
		     
		     txtAmount.setOnAction(e -> {
		    	 if (txtAmount.getText().isEmpty()) {
		    		 lblSuccess.setText("You must enter an Amount!");
		    		 lblSuccess.setStyle(txtFillColor[1]);
		    		 txtAmount.requestFocus();
		    		 btnAdd.setDisable(true);
		    	 } else {
		    		 btnAdd.setDisable(false);
		    		 btnAdd.requestFocus();
		    	 }
		     });
		     
		     txtAmount.setOnKeyPressed(e -> {
		    	 lblSuccess.setText("");
		     });
		     
		     btnAdd.setOnAction(e -> {
		    	 Account account = accounts.get(0);
		    	 String desc = "";
		    	 double amount = getAmount(txtAmount), oldAmount = 0.0;
		    	 if (txtAccountNum.getText().isEmpty()) {
		    		 lblSuccess.setText("You must enter an Account Number!");
		    		 lblSuccess.setFont(arialFont[1]);
		    		 lblSuccess.setStyle(txtFillColor[1]);
		    		 txtAccountNum.requestFocus();
		    		 
		    	 } else if (txtAmount.getText().isEmpty()) {
		    		 txtAmount.setText("0.00");
		    		 
		    	 } else if (getAmount(txtAmount) < 0.0) {
		    		 lblSuccess.setText("Amount cannot be a negative number! Please re-enter!");
		    		 lblSuccess.setFont(arialFont[1]);
		    		 lblSuccess.setStyle(txtFillColor[1]);
		    		 txtAmount.requestFocus();
		    	 } else {
		    		 if (tranType == 0) { // deposit
			    		 boolean isOk = false;
				    	 for (Account a: accounts) {
				    		 if (a.getAccountNumber().equals(txtAccountNum.getText())) {
				    			 account = a;
				    			 isOk = a.makeDeposit(amount);
				    			 break;
				    		 }
				    	 }
				    	 if (account instanceof Checking) {
			    			 desc = "Checking Account Deposit";
			    		 } else if (account instanceof Regular) {
			    			 desc = "Regular Account Deposit";
			    		 } else if (account instanceof Gold) {
			    			 desc = "Gold Account Deposit";
			    		 }
				    	 
				    	 if (isOk) {
				    		 createTransaction(account.getCustomer().getCustomerID(), txtAccountNum.getText(), desc, amount);
				    		 lblSuccess.setText("Successfully Deposited the funds!");
				    		 lblSuccess.setStyle(txtFillColor[0]);
				    		 txtAccountNum.setText("");
				    		 txtAmount.setText("");
				    		 txtAccountNum.requestFocus();
				    	 } else {
				    		 lblSuccess.setText("Deposit was NOT successFul!");
				    		 lblSuccess.setStyle(txtFillColor[1]);
				    		 txtAccountNum.requestFocus();
				    	 }
			    	 } else if (tranType == 1) { // withdrawal
			    		 JOptionPane.showMessageDialog(null, "This is a Withdrawal");
			    		 oldAmount = amount;
			    		 for (Account a: accounts) {
			    			 if (a.getAccountNumber().equals(txtAccountNum.getText())) {
			    				 account = a;
			    				 amount = account.makeWithdrawal(amount);
			    			 }
			    		 }
			    		 if (account instanceof Checking) {
			    			 desc = "Checking Account Withdrawal";
			    		 } else if (account instanceof Regular) {
			    			 desc = "Regular Account Withdrawal";
			    		 } else if (account instanceof Gold) {
			    			 desc = "Gold Account Withdrawal";
			    		 }
	    				 if ((oldAmount != amount) || amount != -1.0) {
	    					 desc += " - Available Balance";
	    				 }
	    				 if (amount == -1.0) {
	    					 lblSuccess.setText("*********** Withdrawal was UNSUCCESSFUL! ***********");
	    					 lblSuccess.setStyle(txtFillColor[1]);
	    				 } else {
	    					 createTransaction(account.getCustomer().getCustomerID(), txtAccountNum.getText(), desc, (amount * -1.0));
	    					 lblSuccess.setText(desc + " - SUCCESSFUL!");
	    					 lblSuccess.setStyle(txtFillColor[0]);
	    				 }
			    	 }
		    	 }
		     });
		     btnExit.setOnAction(e -> {
		    	 btnExit.getParent().getScene().getWindow().hide();
		     });
		     
			  lstAccount.setOnMouseClicked(e -> {
				  txtAccountNum.setText(lstAccount.getSelectedText().trim());
				  txtAccountNum.requestFocus();
			  });
		     return mainPane;
		}
		
		
		public BorderPane removeScreen(int remNum){
			String txtTitle = "", txtLabel = "";
			switch (remNum) {
			case 0:
				txtTitle = "Remove Customer";
				txtLabel = "Customer #";
				break;
			case 1:
				txtTitle = "Remove Account";
				txtLabel = "Account #";
				break;
			}
			Label lblTitle = new Label(txtTitle);
			lblTitle.setFont(arialFont[3]);
			lblTitle.setStyle(txtFillColor[0]);
			
			Label lblCustomerNum = new Label(txtLabel);
			lblCustomerNum.setFont(arialFont[1]);
			lblCustomerNum.setStyle(txtFillColor[0]);
			
			Label lblMessage = new Label(" ");
			lblMessage.setFont(arialFont[1]);
			lblMessage.setStyle(txtFillColor[1]);
			
			TextField txtCustomerNum = new TextField();
			txtCustomerNum.setFont(arialFont[1]);
			txtCustomerNum.setStyle(txtFillColor[0]);
			txtCustomerNum.setMaxWidth(200);
			
			TextArea txtOutput = new TextArea();
			txtOutput.setPrefRowCount(1);
			txtOutput.setPrefColumnCount(35);
			txtOutput.setFont(arialFont[1]);
			txtOutput.setStyle(txtFillColor[1]);
			txtOutput.setMaxWidth(600);
			
			Button btnRemove = new Button("Remove");
			btnRemove.setFont(arialFont[2]);
			btnRemove.setStyle(txtFillColor[5]);
			
			Button btnExit = new Button("Exit");
			btnExit.setPadding(new Insets(5, 35, 5, 35));
			btnExit.setFont(arialFont[2]);
			btnExit.setStyle(txtFillColor[6]);
			
			VBox errorPane = new VBox(8);
			errorPane.getChildren().add(lblMessage);
			errorPane.setAlignment(Pos.CENTER);
			
			
			VBox titlePane = new VBox(8);
			titlePane.setPadding(new Insets(15, 15, 25, 15));
			titlePane.getChildren().add(lblTitle);
			titlePane.setAlignment(Pos.CENTER);
			
			VBox custNumPane = new VBox(8);
			custNumPane.getChildren().addAll(lblCustomerNum, txtCustomerNum);
			custNumPane.setPadding(new Insets(25, 15, 25, 15));
			custNumPane.setAlignment(Pos.CENTER);
			
			VBox outputPane = new VBox(8);
			outputPane.getChildren().add(txtOutput);
			outputPane.setAlignment(Pos.CENTER);
			
			VBox topPane = new VBox(8);
			topPane.getChildren().addAll(titlePane, custNumPane);
			topPane.setAlignment(Pos.CENTER);
			
			HBox buttonPane = new HBox(8);
			buttonPane.setPadding(new Insets(25, 15, 25, 15));
			buttonPane.getChildren().addAll(btnRemove, btnExit);
			buttonPane.setAlignment(Pos.CENTER);
			
			VBox bottomPane = new VBox(8);
			bottomPane.getChildren().addAll(errorPane, buttonPane);
			bottomPane.setAlignment(Pos.CENTER);
			
			BorderPane mainPane = new BorderPane();
			mainPane.setTop(topPane);
			mainPane.setCenter(outputPane);
			mainPane.setBottom(bottomPane);
			
			txtCustomerNum.setOnAction(e -> {
				if (txtCustomerNum.getText().isEmpty()) {
					lblMessage.setText(((remNum == 0)? "You must enter a Customer Number" : "You must enter an Account Number"));
					lblMessage.setFont(arialFont[1]);
					lblMessage.setStyle(txtFillColor[1]);
				} else if (remNum == 0) { // remove customer
					boolean isFound = false;
					Customer customer = customers.get(0);
					for (Customer c: customers) {
						if (c.getCustomerID().equals(txtCustomerNum.getText())) {
							if (!c.getActive()) {
								customer = c;
								isFound = true;
							}
							break;
						}
					}
					if (isFound) {
						txtOutput.clear();
						txtOutput.appendText(customer.getCustomerID() + " " + customer.getCustomerName() + ((customer.getActive())? " Active":" Inactive"));
						btnRemove.requestFocus();
					} else {
						lblMessage.setText("Customer Number was either not found or was not able to remove it!");
						lblMessage.setStyle(txtFillColor[1]);
						txtCustomerNum.requestFocus();
					}
				} else if (remNum == 1) { // remove account
					boolean isFound = false;
					Account account = accounts.get(0);
					for (Account a: accounts) {
						if (a.getAccountNumber().equals(txtCustomerNum.getText())) {
							isFound = true;
							account = a;
							break;
						}
					}
					if (isFound) {
						txtOutput.clear();
						txtOutput.setText(account.getAccountNumber() + " " + account.getCustomer().toString());
					} else {
						lblMessage.setText("Account Number was not Found");
						lblMessage.setStyle(txtFillColor[1]);
						txtCustomerNum.requestFocus();
					}
				}
			});
			
			txtCustomerNum.setOnKeyPressed(e -> {
				lblMessage.setText("");
			});
			
			btnRemove.setOnAction(e -> {
				
			});
			btnExit.setOnAction(e -> {
				btnExit.getParent().getScene().getWindow().hide();
			});
			
			return mainPane;
		}
		
		/** get amount<br><br>
		 * 
		 * this will parse the text field amount and return the number<br>
		 * 
		 * @param txtAmount the TextField of the amount
		 * @return the amount
		 */
		public double getAmount(TextField txtAmount) {
			double amount = 0.0;
			try {
				amount = Double.parseDouble(txtAmount.getText());
				if (amount < 0.0) {
					amount = Math.abs(amount);
				}
			} catch (NumberFormatException e) {
				amount = -1.0;
			} catch (NullPointerException e) {
				amount = -1.0;
			} catch (Exception e) {
				amount = -1.0;
			}
			return amount;
		}
		/** create transactions
		 * 
		 * @param customerID String
		 * @param accountNumber String
		 * @param description String
		 * @param amount double
		 */
		public void createTransaction(String customerID, String accountNumber, String description, double amount) {
			transactions.add(new Transaction(new java.util.Date(), customerID, accountNumber, description, amount, bu.generateUniqueTransNumber()));
		}
		

	  /** create Account<br><br>
	   * 
	   * using the acctNum will determine whether it is for Checking, Regular or Gold<br><br>
	   * 
	   * 0 - Checking<br>
	   * 1 - Regular<br>
	   * 2 - Gold<br>
	   * 
	   * 
	   * 
	   * @param txtAccountNumber the account number from the form
	   * @param txtCustomerID the customer id from the form
	   * @param txtOpeningBalance the opening balance from the form
	   * @param lbltxtDisplayBoxMessage the display box on the form
	   * @param acctNum the number that will determine Checking, Regular, or Gold
	   * @return whether it was successful or not (true or false)
	   */
	  public boolean createAccount( TextField txtAccountNumber, TextField txtCustomerID, TextField txtOpeningBalance, Label lbltxtDisplayBoxMessage, int acctNum) {
		  // message box font size
		  //String txtMessage = "18";
		  // the beginning of the style string
		  //String acctStyleFirst = "-fx-font: ";
		  // the last pert of the style string before the color
		  //String acctStyleLast =" arial; -fx-text-fill: ";
		  // the blue color
		 // String acctStyleBlue = "blue;";
		  // the red color
		 // String acctStyleRed = "red;";
		  // the black color
		  //String acctStyleBlack = "black;";

    	 // check to see if the text boxes are empty and notify user
    	 if (txtAccountNumber.getText().isEmpty()) {
    		 lbltxtDisplayBoxMessage.setText("You must enter an Accouont Number!");
    		 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
    		 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
    		 txtAccountNumber.requestFocus();
    	 } else if (txtCustomerID.getText().isEmpty()) {
    		 lbltxtDisplayBoxMessage.setText("You must enter a Customer ID!");
    		 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
    		 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
    		 txtCustomerID.requestFocus();
    	 } else if (txtOpeningBalance.getText().isEmpty()) {
    		 lbltxtDisplayBoxMessage.setText("You must enter the Opening Balance!");
    		 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
    		 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
    		 txtOpeningBalance.requestFocus();
    	 } else {
    		 int element = accounts.size();
    		 double amount = -1.0;
    		 boolean isOk = false;
	    		 try {
	    			 amount = Double.parseDouble(txtOpeningBalance.getText());
	    			 if (amount < 0.0) {
	    				 lbltxtDisplayBoxMessage.setText("Negative Opening Balances are not allow!\n\nPlease enter a positive Opening Balance!");
	    				 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
	    				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
	    				 txtOpeningBalance.requestFocus();
	    			
	    			 } else {
			    		 // JOptionPane.showMessageDialog(null, amount);
			    		 isOk = true;
	    			 }
	    		 } catch (NumberFormatException h) {
	    			 lbltxtDisplayBoxMessage.setText("I was expecting a Account Opening Balance, Please re-enter");
	    			 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
    				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
	    			 txtOpeningBalance.requestFocus();
	    		
	    		 } catch (InputMismatchException  g) {
	    			 lbltxtDisplayBoxMessage.setText("You must enter the Opening Balance to the Account");
	    			 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
    				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
	    			 txtOpeningBalance.requestFocus();
	    			
	    		 } catch (NoSuchElementException f) {
    				 lbltxtDisplayBoxMessage.setText("Out of Range, Please re-enter the Opening Balance Ammount!");
    				 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
    				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
	    		 }
    		 if (isOk) {
    			 String desc = "";
    			 Customer customer = customers.get(0);
    			 for (int x = 0; x < customers.size(); x++) {
 	    			 if (customers.get(x).getCustomerID().equals(txtCustomerID.getText())) {
 	    				 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
	    				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[2]);
	    				 customer = customers.get(x);
	    				 switch (acctNum) {
	    				 case 0: // checking
	    					 desc = "Checking Opening Balance";
	    					 accounts.add(new Checking(txtAccountNumber.getText(), amount, customer));
	    					 createTransaction(customer.getCustomerID(), txtAccountNumber.getText(), desc, amount);
	    					 break;
	    				 case 1: // regular
	    					 desc = "Regular Opening Balance";
	    					 accounts.add(new Regular(txtAccountNumber.getText(), amount, customer));
	    					 createTransaction(customer.getCustomerID(), txtAccountNumber.getText(), desc, amount);
	    					 break;
	    				 case 2: // gold
	    					 desc = "Gold Opening Balance";
	    					 accounts.add(new Gold(txtAccountNumber.getText(), amount, customer));
	    					 createTransaction(customer.getCustomerID(), txtAccountNumber.getText(), desc, amount);
	    					 break;
	    				 }
	    			 } else {
	    				 lbltxtDisplayBoxMessage.setText("Customer not fount!\n\nPlease re-enter Customer ID");
	    				 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
	    				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
	    				 txtCustomerID.requestFocus();
	    			 }
    			 }
    			 // notify the user
 	    		 if (element < accounts.size()) {
    	 			 lbltxtDisplayBoxMessage.setText("Account successfully Added!");
    	 			 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
    				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[0]);
    				 return true;
	    		 } else {
	    			 lbltxtDisplayBoxMessage.setText("Adding Checking Account Unsuccessful!");
	    			 lbltxtDisplayBoxMessage.setFont(arialFont[1]);
    				 lbltxtDisplayBoxMessage.setStyle(txtFillColor[1]);
	    		 }
    		 } else {
    			 txtOpeningBalance.requestFocus();
    			 
    		 }
    	 }
		  return false;
	  }
// ************************************************************* file system *************************************************************
	  
	  // **************************************** load ****************************************
	  
	  // *************************** config ***************************
	  
	  public void loadConfigData() {
		  File file = new File(filename[0]);
		  if (!(file.exists())) {
			  bankName = JOptionPane.showInputDialog("Please Enter the Bank Name");
			  bakup = 0;
			  return;
		  }
		  try (DataInputStream input = new DataInputStream(new FileInputStream(filename[0]));){
			  while (true) {
				  bankName = input.readUTF();
				  bakup = input.readInt();
			  }
		  } catch (EOFException e) {
			  //JOptionPane.showMessageDialog(null, "Configuration Data Loaded successfully!", "Config Load Data", JOptionPane.INFORMATION_MESSAGE);
			  e.getStackTrace();
			  return;
		  } catch (FileNotFoundException e) {
			  //JOptionPane.showMessageDialog(null, "Configuration file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
			  return;
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Configuration File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
		  }
	  }
	  
	  // *************************** customer ***************************
	  
	  public void loadCustomerData() {
		  if (customers.isEmpty()) {
			  String custID = "", custName = "";
			  @SuppressWarnings("unused")
			int counter = 0;
			  boolean active = false;
			  try (DataInputStream input = new DataInputStream(new FileInputStream(filename[1]));){
				  while (true) {
					  custID = input.readUTF();
					  custName = input.readUTF();
					  active = input.readBoolean();
					  customers.add(new Customer(custID, custName, active));
					  counter++;
				  }
			  } catch (EOFException e) {
				 // JOptionPane.showMessageDialog(null, counter + " Customer(s) Data Loaded successfully!", "Customer Load Data", JOptionPane.INFORMATION_MESSAGE);
				  e.getStackTrace();
				 return;
			  } catch (FileNotFoundException e) {
				  //JOptionPane.showMessageDialog(null, "Customer file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Customer File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  }
		  } else {
			  JOptionPane.showMessageDialog(null, "Customers have already been loaded!", "Load Customer Error", JOptionPane.INFORMATION_MESSAGE);
			  
		  }
	  }
	  
	  // *************************** checking ***************************
	  
	  public void loadCheckingData() {
		  if (customers.isEmpty()) {
			  JOptionPane.showMessageDialog(null, "There are no Customers! Terminating Load Checking!", "Error Load Checking", JOptionPane.ERROR_MESSAGE);
			  return;
		  }
		  boolean isOk = true;
		  for (Account a: accounts) {
			  if (a instanceof Checking) {
				  isOk = false;
			  }
		  }
		  if (isOk) {
			  String custID = "", custName = "", acctNum = "";
			  double acctBal = 0.0, acctFee = 0.0, acctFeeAmount = 0.0;
			  @SuppressWarnings("unused")
			int counter = 0;
			  try (DataInputStream input = new DataInputStream(new FileInputStream(filename[2]));) {
				  while (true) {
					  custID = input.readUTF();
					  custName = input.readUTF();
					  acctNum = input.readUTF();
					  acctBal = input.readDouble();
					  acctFee = input.readDouble();
					  acctFeeAmount = input.readDouble();
					  Customer customer = customers.get(0);
					  for (Customer c: customers) {
						  if (c.getCustomerID().equals(custID) && c.getCustomerName().equals(custName)) {
							  customer = c;
						  }
					  }
					  accounts.add(new Checking(acctNum, acctBal, customer, acctFee, acctFeeAmount));
					  counter++;
				  }
			  } catch (EOFException e) {
				  //JOptionPane.showMessageDialog(null, counter + " Checking Account Data Loaded successfully!", "Checking Load Data", JOptionPane.INFORMATION_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (FileNotFoundException e) {
				  //JOptionPane.showMessageDialog(null, "Account file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Account File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  }
		  } else {
			  JOptionPane.showMessageDialog(null, "Checking Accounts have already been loaded!", "Load Accounts Error", JOptionPane.INFORMATION_MESSAGE);
		  }
	  }
	  
	  // *************************** regular ***************************
	  
	  public void loadRegularData() {
		  if (customers.isEmpty()) {
			  JOptionPane.showMessageDialog(null, "There are no Customers! Terminating Load Regular!", "Error Load Regular", JOptionPane.ERROR_MESSAGE);
			  return;
		  }
		  boolean isOk = true;
		  for (Account a: accounts) {
			  if (a instanceof Regular) {
				  isOk = false;
			  }
		  }
		  if (isOk) {
			  String custID = "", custName = "", acctNum = "";
			  double acctBal = 0.0, acctIntRate = 0.0, acctIntAmount = 0.0, acctFixed = 0.0;
			  @SuppressWarnings("unused")
			int counter = 0;
			  try (DataInputStream input = new DataInputStream(new FileInputStream(filename[3]));) {
				  while (true) {
					  custID = input.readUTF();
					  custName = input.readUTF();
					  acctNum = input.readUTF();
					  acctBal = input.readDouble();
					  acctIntRate = input.readDouble();
					  acctIntAmount = input.readDouble();
					  acctFixed = input.readDouble();
					  Customer customer = customers.get(0);
					  for (Customer c: customers) {
						  if (c.getCustomerID().equals(custID) && c.getCustomerName().equals(custName)) {
							  customer = c;
						  }
					  }
					  accounts.add(new Regular(acctNum, acctBal, customer, acctIntRate, acctIntAmount, acctFixed));
					  counter++;
				  }
			  } catch (EOFException e) {
				  //JOptionPane.showMessageDialog(null, counter + " Regular Account Data Loaded successfully!", "Regular Load Data", JOptionPane.INFORMATION_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (FileNotFoundException e) {
				  //JOptionPane.showMessageDialog(null, "Regular file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Regular File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  }
		  } else {
			  JOptionPane.showMessageDialog(null, "Regular Accounts have already been loaded!", "Load Accounts Error", JOptionPane.INFORMATION_MESSAGE);
		  }
	  }
	  
	  // *************************** gold ***************************
	  
	  public void loadGoldData() {
		  if (customers.isEmpty()) {
			  JOptionPane.showMessageDialog(null, "There are no Customers! Terminating Load Gold!", "Error Load Gold", JOptionPane.ERROR_MESSAGE);
			  return;
		  }
		  boolean isOk = true;
		  for (Account a: accounts) {
			  if (a instanceof Gold) {
				  isOk = false;
			  }
		  }
		  if (isOk) {
			  String custID = "", custName = "", acctNum = "";
			  double acctBal = 0.0, acctIntRate = 0.0, acctIntAmount = 0.0;
			  @SuppressWarnings("unused")
			int counter = 0;
			  try (DataInputStream input = new DataInputStream(new FileInputStream(filename[4]));) {
				  while (true) {
					  custID = input.readUTF();
					  custName = input.readUTF();
					  acctNum = input.readUTF();
					  acctBal = input.readDouble();
					  acctIntAmount = input.readDouble();
					  acctIntRate = input.readDouble();
					  Customer customer = customers.get(0);
					  for (Customer c: customers) {
						  if (c.getCustomerID().equals(custID) && c.getCustomerName().equals(custName)) {
							  customer = c;
						  }
					  }
					  accounts.add(new Gold(acctNum, acctBal, customer, acctIntAmount, acctIntRate));
					  counter++;
				  }
			  } catch (EOFException e) {
				  //JOptionPane.showMessageDialog(null, counter + " Gold Account Data Loaded successfully!", "Gold Load Data", JOptionPane.INFORMATION_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (FileNotFoundException e) {
				  //JOptionPane.showMessageDialog(null, "Gold file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Gold File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  }
		  } else {
			  JOptionPane.showMessageDialog(null, "Gold Accounts have already been loaded!", "Load Accounts Error", JOptionPane.INFORMATION_MESSAGE);
		  }
	  }

	  // *************************** transactions ***************************

	  public void loadTransactionData() {
		  if (transactions.isEmpty()) {
			  String custID = "", acctNum = "", desc = "";
			  double amount = 0.0;
			  long tranID = 0;
			  Date date = new Date();
			  SimpleDateFormat strDate = new SimpleDateFormat();
			  try (DataInputStream input = new DataInputStream(new FileInputStream(filename[5]));) {
				  while (true) {
					  custID = input.readUTF();
					  acctNum = input.readUTF();
					  desc = input.readUTF();
					  amount = input.readDouble();
					  tranID = input.readLong();
					  date = strDate.parse(input.readUTF());
					  transactions.add(new Transaction(date, custID, acctNum, desc, amount, tranID));
				  }
			  } catch (EOFException e) {
				  //JOptionPane.showMessageDialog(null, counter + " Gold Account Data Loaded successfully!", "Gold Load Data", JOptionPane.INFORMATION_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (FileNotFoundException e) {
				  //JOptionPane.showMessageDialog(null, "Gold file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Transaction File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  } catch (ParseException e) {
				  e.getStackTrace();
			  }
		  } else {
			  JOptionPane.showMessageDialog(null, "Transactions have already been loaded! Terminating Load Transactions!", "Load Transacitons", JOptionPane.INFORMATION_MESSAGE);
		  }
	  }
	  
	  
// **************************************** save ****************************************
	  
	  // *************************** config ***************************

	  public void saveConfigData() {
		  try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename[0], false));) {
			  output.writeUTF(bankName);
			  output.writeInt(bakup);
			  
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Config File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
		  }
		  // JOptionPane.showMessageDialog(null, "Config Data Saved successfully!", "Config Save Data", JOptionPane.INFORMATION_MESSAGE);
	  }
	  
	  
	  // *************************** customer ***************************
	  
	  public void saveCustomerData() {
		  int counter = 0;
		  try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename[1], ((counter==0)?false:true)));) {
			  for (Customer c: customers) {
				  output.writeUTF(c.getCustomerID());
				  output.writeUTF(c.getCustomerName());
				  output.writeBoolean(c.getActive());
				  counter++;
			  }
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Customer File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
		  }
		  JOptionPane.showMessageDialog(null, counter + " Customer Data Saved successfully!", "Customer Save Data", JOptionPane.INFORMATION_MESSAGE);
		  
	  }
	  
	  // *************************** checking ***************************
	  
	  public void saveCheckingData() {
		  int counter = 0;
		  try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename[2], ((counter==0)?false:true)));) {
			  for (Account a: accounts) {
				  if (a instanceof Checking) {
					  output.writeUTF(a.getCustomer().getCustomerID());
					  output.writeUTF(a.getCustomer().getCustomerName());
					  output.writeUTF(a.getAccountNumber());
					  output.writeDouble(a.getAccountBalance());
					  output.writeDouble(((Checking) a).getCheckingTransactionFee());
					  output.writeDouble(((Checking) a).getCheckingTransactionFeeAmount());
					  counter++;
				  }
			  }
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Checking File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
		  }
		  JOptionPane.showMessageDialog(null, counter + " Checking Data Saved successfully!", "Checking Save Data", JOptionPane.INFORMATION_MESSAGE);
		  
	  }
	  
	  // *************************** regular ***************************
	  
	  public void saveRegularData() {
		  int counter = 0;
		  try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename[3], ((counter==0)?false:true)));) {
			  for (Account a: accounts) {
				  if (a instanceof Regular) {
					  output.writeUTF(a.getCustomer().getCustomerID());
					  output.writeUTF(a.getCustomer().getCustomerName());
					  output.writeDouble(a.getAccountBalance());
					  output.writeDouble(((Regular) a).getRegularInterestRate());
					  output.writeDouble(((Regular) a).getRegularInterestAmount());
					  output.writeDouble(((Regular) a).getRegularFixedCharge());
					  counter++;
				  }
			  }
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Regular File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
		  }
		  JOptionPane.showMessageDialog(null, counter + " Regular Data Saved successfully!", "Regular Save Data", JOptionPane.INFORMATION_MESSAGE);
	  }
	
	  // *************************** gold ***************************

	  public void saveGoldData() {
		  int counter = 0;
		  try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename[4], ((counter==0)?false:true)));) {
			  for (Account a: accounts) {
				  if (a instanceof Gold) {
					  output.writeUTF(a.getCustomer().getCustomerID());
					  output.writeUTF(a.getCustomer().getCustomerName());
					  output.writeUTF(a.getAccountNumber());
					  output.writeDouble(a.getAccountBalance());
					  output.writeDouble(((Gold) a).getGoldInterestAmount());
					  output.writeDouble(((Gold) a).getGoldInterestRate());
					  counter++;
				  }
			  }
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Gold File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
			  
		  }
		  JOptionPane.showMessageDialog(null, counter + " Gold Data Saved successfully!", "Gold Save Data", JOptionPane.INFORMATION_MESSAGE);
	  }
	
	  
	  // *************************** transactions ***************************

	  
	  public void saveTransactionData() {
		  if (transactions.isEmpty()) {
			  JOptionPane.showMessageDialog(null, "There are no Transacitons to save! Terminating Save Transactions!", "No Transactions", JOptionPane.ERROR_MESSAGE);
			  return;
		  } else {
			 try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename[5]));) {
				 for (Transaction t: transactions) {
					 output.writeUTF(t.getCustomerID());
					 output.writeUTF(t.getAccountNumber());
					 output.writeUTF(t.getDescription());
					 output.writeDouble(t.getAmount());
					 output.writeLong(t.getTransactionNumber());
					 output.writeUTF(t.getCreateDate().toString());
				 }
			  } catch (FileNotFoundException e) {
				  //JOptionPane.showMessageDialog(null, "Gold file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
				  return;
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Transaction File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  }
		  }
	  }

	  
	  
// ***************************************************************************** object file system *****************************************************************************
	  
	  // ***************************************************************** load *****************************************************************
	  
	  // ************************************** customer **************************************
	  
	@SuppressWarnings("unchecked")
	public void loadCustomerObjectData() {
		File info = new File(filename[7]);
		if (info.exists()) {
			  try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename[7]));) {
				  customers = (ArrayList<Customer>) input.readObject();
				  
			  } catch (EOFException e) {
				  e.getStackTrace();
				  return;
				  
			  } catch (FileNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "File " + filename[7] + " not found!", "Customer Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Customer Read Error", "Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  } catch (ClassNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "Unable to create object", "Error Reading Customer", JOptionPane.ERROR);
				  e.getStackTrace();
			  }
		}
	}
	  
	
	// ************************************** account **************************************
		
	  @SuppressWarnings("unchecked")
	public void loadAccountObjectData() {
		  File info = new File(filename[6]);
		  if (info.exists()) {
			  try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename[6]));) {
				  accounts = (ArrayList<Account>) input.readObject();
				  
			  } catch (FileNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "File " + filename[6] + " not found!", "Account Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Account Read Error", "Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  } catch (ClassNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "Unable to create object", "Error Reading Account", JOptionPane.ERROR);
				  e.getStackTrace();
			  }
		  }
	  }
	  
	// ************************************** transactions **************************************
	  
	  @SuppressWarnings("unchecked")
	  public void loadTransactionObjectData() {
		  File info = new File(filename[8]);
		  if (info.exists()) {  
			  try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename[8]));) {
				  transactions = (ArrayList<Transaction>) input.readObject();
				  
			  } catch (FileNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "File " + filename[8] + " not found!", "Transaction Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Transaction Read Error", "Read Error", JOptionPane.ERROR_MESSAGE);
				  e.getStackTrace();
			  } catch (ClassNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "Unable to create object", "Error Reading Transaction", JOptionPane.ERROR);
				  e.getStackTrace();
			  }
		  } else {
			  return;
		  }
	  }
	  
	  // ***************************************************************** save *****************************************************************

		// ************************************** customer **************************************

	  public void saveCustomerObjectData() {
		  try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename[7], false));) {
			  output.writeObject(customers);
			  
		  } catch (FileNotFoundException e) {
			  JOptionPane.showMessageDialog(null, "File " + filename[7] + " not found!", "Customer Save Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
			  
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Customer Read Error", "Save Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();

		  }
	  }
	  
		// ************************************** accounts **************************************

	  public void saveAccountObjectData() {
		  try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename[6], false));) {
			  output.writeObject(accounts);
		  } catch (FileNotFoundException e) {
			  JOptionPane.showMessageDialog(null, "File " + filename[6] + " not found!", "Account Save Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Account Read Error", "Save Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
		  }
	  }
	  
		// ************************************** transactions **************************************

	  public void saveTransactionObjectData() {
		  try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename[8], false));) {
			  output.writeObject(transactions);
		  } catch (FileNotFoundException e) {
			  JOptionPane.showMessageDialog(null, "File " + filename[7] + " not found!", "Transaction Save Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Transaction Read Error", "Save Error", JOptionPane.ERROR_MESSAGE);
			  e.getStackTrace();
		  }
	  }
	  
	  // *********************************** create new System Data *****************************************
	  
	  // ************* this will delete the files so be careful ****************************
	  
	  public void createNewSystemData() {
		  boolean isOk = false, debug = true;
		  try {
			  File startOver;
			  if (debug) {
				 int x = 0; // data only
				 startOver = new File(paths[x+1]);
				 String[] files = startOver.list();
				 // make sure it is working properly. Then remove the if and its contents
				 if (files.length > 0) {
					 String msg = "";
					 for (String f: files) {
						 msg += f + "\n";
					 }
					 JOptionPane.showMessageDialog(null, msg, "Folder Contents", JOptionPane.INFORMATION_MESSAGE);
				 }
			  }
			 for (int y = 0; y < filename.length; y++) {
				 startOver = new File(filename[y]);
				 if (startOver.exists()) {
					 isOk = copyDatToBakup(filename[y], backname[y] + bakup + ".dat");
				 }
			 }
			 if (isOk) {
				 bakup++;
				 for (int y = 0; y < filename.length; y++) {
					 startOver = new File(filename[y]);
					 if (startOver.exists()) {
						 startOver.delete();
					 }
				 }
				 bankName = JOptionPane.showInputDialog("Please Enter the Bank Name");
				 
				 saveConfigData();
				 loadConfigData();
				 JOptionPane.showMessageDialog(null, "New file System READY for use!", "New Files", JOptionPane.INFORMATION_MESSAGE);
			 } else {
				 JOptionPane.showMessageDialog(null, "Error copying files! New System is NOT Ready for use!", "New Files", JOptionPane.ERROR_MESSAGE);
			 }
		  } catch (NullPointerException e) {
			  e.getStackTrace();
			  
		  } catch (SecurityException e) {
			  e.getStackTrace();
		  }
	  }
	  
	  public boolean copyDatToBakup(String fromFile, String toFile) {
		  if (fromFile.isEmpty()) {
			  return false;
		  }
		  if (toFile.isEmpty()) {
			  return false;
		  }
		  File sourceFile = new File(fromFile);
		  File targetFile = new File(toFile);
		  if (!sourceFile.exists()) {
			  JOptionPane.showMessageDialog(null, sourceFile + " does not exist!", "File Not Found", JOptionPane.ERROR_MESSAGE);
			  return false;
		  }
		  
		  if (targetFile.exists()) {
			  JOptionPane.showMessageDialog(null, targetFile + " already exists!", "File already Exists", JOptionPane.ERROR_MESSAGE);
			  return false;
		  }
		  
		  try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile));
				  BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile));) {
			  int r;
			  while((r = input.read()) != -1) {
				  output.write((byte)r);
			  }
			  return true;
		  } catch (IOException e) {
			e.getStackTrace();
		  }
		  return false;
	  }
	  
	  public BorderPane generateReport(int acctNum) {
		  Label lblReports = new Label();
		  lblReports.setFont(arialFont[1]);
		  lblReports.setStyle(txtFillColor[0]);
		  
		  switch (acctNum) {
		  case 0:
			  lblReports.setText("Customer Reports");
			  break;
		  case 1:
			  lblReports.setText("Account Reports");
			  break;
		  case 2:
			  lblReports.setText("Transaction Reports");
			  break;
		  case 3:
			  lblReports.setText("Statistics Report");
			  break;
		  }
		  Button btnExit = new Button("Exit");
		  btnExit.setFont(arialFont[2]);
		  btnExit.setStyle(txtFillColor[6] + txtFillColor[3]);
		  
		  TextArea taOutput = new TextArea();
		  taOutput.setPrefRowCount(15);
		  switch (acctNum) {
		  case 0:
			  taOutput.appendText("Customer ID\t Name\t\t\t\t\t\t\t Status\n");
			  for (Customer c: customers) {
				  taOutput.appendText(c.toString() + "\n");
			  }
			  break;
		  case 1:
			  int counter = 0;
			  for (Account a: accounts) {
				  if (a instanceof Checking) {
					  if (counter == 0) {
						  taOutput.appendText(String.format("\n\t\t\t\t\t %s \t\t\t\t\t\t %s \t %s \t\t %s \t\t %s \t\t %s \n", "Customer Information","Account Number", "Balance", "# of Transactions", "Transaction Fee", "Total Fee"));
						  counter++;
					  }
					  taOutput.appendText(a.toString());
				  }
			  }
			  counter = 0;
			  for (Account a: accounts) {
				  if (a instanceof Regular) {
					  if (counter == 0) {
						  taOutput.appendText(String.format("\n\t\t\t\t\t %s \t\t\t\t\t\t %s \t %s \t\t %s \t\t %s \t\t %s \n", "Customer Information", "Account Number", "Balance", "Inerest Rate", "Fixed Charge", "Total Interest"));
						  counter++;
					  }
					  taOutput.appendText(a.toString());
				  }
			  }
			  counter = 0;
			  for (Account a: accounts) {
				  if (a instanceof Gold) {
					  if (counter == 0) {
						  taOutput.appendText(String.format("\n\t\t\t\t\t  %s \t\t\t\t\t\t %s \t %s \t %s %s\n", "Customer Information", "Account Number", "Balance", "Interest Rate", "Interest Amount"));
						  counter++;
					  }
					  taOutput.appendText(a.toString());
				  }
			  }
			  break;
		  case 2:
			  taOutput.setFont(arialFont[0]);
			  for (Transaction t: transactions) {
				  taOutput.appendText(t.toString());
			  }
			  break;
		  case 3:
			  taOutput.setFont(arialFont[0]);
			  generateStatistics(taOutput);
			  break;
		  }
			  if (acctNum == 0) {
				  taOutput.setPrefColumnCount(30);
			  } else if (acctNum == 1 || acctNum == 2) {
				  taOutput.setPrefColumnCount(85);
			  } else if (acctNum == 3) {
				  taOutput.setPrefColumnCount(75);
			  }
			  taOutput.setEditable(false);
			  
			  HBox displayPane = new HBox();
			  displayPane.setPadding(new Insets(25,12,12,12));
			  displayPane.getChildren().addAll(lblReports);
			  displayPane.setAlignment(Pos.CENTER);
			  
			  FlowPane centerPane = new FlowPane();
			  centerPane.setHgap(5);
			  centerPane.setVgap(5);
			  centerPane.getChildren().addAll(taOutput);
			  centerPane.setAlignment(Pos.CENTER);
			  
			  HBox bottomPane = new HBox(1);
			  bottomPane.setPadding(new Insets(0,0,25,0));
			  bottomPane.getChildren().addAll(btnExit);
			  bottomPane.setAlignment(Pos.CENTER);
			  
			  BorderPane mainPane = new BorderPane();
			  mainPane.setTop(displayPane);
			  mainPane.setCenter(centerPane);
			  mainPane.setBottom(bottomPane);
			  mainPane.setStyle("-fx-border-color: blue");
			  
			  btnExit.setOnAction(e -> {
				 btnExit.getScene().getWindow().hide();
			});
		   
			  return mainPane;
	  }
	  
	  public void generateStatistics(TextArea taOutput) {
			// number of accounts
			int numAccounts = 0, numRegularAccounts = 0, numCheckingAccounts = 0, numGoldAccounts = 0;
			// sum accounts
			double sumAccounts = 0.0, sumRegularAccounts = 0.0, sumCheckingAccounts = 0.0, sumGoldAccounts = 0.0;
			// number of zero balances
			int numZeroAccounts = 0, numRegularZeroAccounts = 0, numCheckingZeroAccounts = 0, numGoldZeroAccounts = 0;
			// average balance
			double avgAccounts = 0.0, avgRegularAccounts = 0.0, avgCheckingAccounts = 0.0, avgGoldAccounts = 0.0;
			// largest account balance
			double largestAccount = 0.0, largestRegularAccount = 0.0, largestCheckingAccount = 0.0, largestGoldAccount = 0.0;
			// largest account balance account number
			String largestAccountNumber = "not available                ", largestRegularAccountNumber = "not available                ", largestCheckingAccountNumber = "not available                ", largestGoldAccountNumber = "not available                ";
			numAccounts = accounts.size();
			for (int x = 0; x < accounts.size(); x++) {
	//------------------------------------Total---------------------------			
				sumAccounts += accounts.get(x).getAccountBalance();
				if (accounts.get(x).getAccountBalance() == 0.0) {
					numZeroAccounts++;
				}
				if (accounts.get(x).getAccountBalance() > largestAccount) {
					largestAccount = accounts.get(x).getAccountBalance();
					largestAccountNumber = accounts.get(x).getAccountNumber();
				}
	//-----------------------------------Gold-----------------------------
				if(accounts.get(x) instanceof Gold) {
					numGoldAccounts++;
					sumGoldAccounts += accounts.get(x).getAccountBalance();
					if (accounts.get(x).getAccountBalance() == 0.0) {
						numGoldZeroAccounts++;
					}
					if (accounts.get(x).getAccountBalance() > largestGoldAccount) {
						largestGoldAccount = accounts.get(x).getAccountBalance();
						largestGoldAccountNumber = accounts.get(x).getAccountNumber();
					}
				}
	//----------------------------------Regular-----------------------------
				if (accounts.get(x) instanceof Regular) {
					numRegularAccounts++;
					sumRegularAccounts += accounts.get(x).getAccountBalance();
					if (accounts.get(x).getAccountBalance() == 0.0) {
						numRegularZeroAccounts++;
					}
					if (accounts.get(x).getAccountBalance() > largestRegularAccount) {
						largestRegularAccount = accounts.get(x).getAccountBalance();
						largestRegularAccountNumber = accounts.get(x).getAccountNumber();
					}
				}
	//--------------------------------Checking-----------------------------
				if (accounts.get(x) instanceof Checking) {
					numCheckingAccounts++;
					sumCheckingAccounts += accounts.get(x).getAccountBalance();
					if (accounts.get(x).getAccountBalance() == 0.0) {
						numCheckingZeroAccounts++;
					}
					if (accounts.get(x).getAccountBalance() > largestCheckingAccount) {
						largestCheckingAccount = accounts.get(x).getAccountBalance();
						largestCheckingAccountNumber = accounts.get(x).getAccountNumber();
					}
				}
				
			}
	//-------------------------------calculate averages----------------------------		
			if (numAccounts == 0) {
				avgAccounts = 0.00;
			} else {
				avgAccounts = calcAverages(sumAccounts, numAccounts);
			}
			if (numGoldAccounts == 0) {
				avgGoldAccounts = 0.00;
			} else {
				avgGoldAccounts = calcAverages(sumGoldAccounts, numGoldAccounts);
			}
			if (numRegularAccounts == 0) {
				avgRegularAccounts = 0.00;
			} else {
				avgRegularAccounts = calcAverages(sumRegularAccounts, numRegularAccounts);
			}
			if (numCheckingAccounts == 0) {
				avgCheckingAccounts = 0.00;
			} else {
				avgCheckingAccounts = calcAverages(sumCheckingAccounts, numCheckingAccounts);
			}
	//------------------------------------display results-----------------------------------
			// (char) 68 < Statistics > 68 (char)
			taOutput.appendText(String.format("\n %s \t %s \t %s \t %s \t %s \t %s \t %s \n", "Type Account","Number of Accounts", "Total Balance", "w/Zero Balance", "Average Balance", "Account Number of", "Largest Balance"));
			taOutput.appendText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			taOutput.appendText(displayStatistics("Gold        ", numGoldAccounts, sumGoldAccounts, numGoldZeroAccounts, avgGoldAccounts, largestGoldAccountNumber, largestGoldAccount));
			taOutput.appendText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			taOutput.appendText(displayStatistics("Regular     ", numRegularAccounts, sumRegularAccounts, numRegularZeroAccounts, avgRegularAccounts, largestRegularAccountNumber, largestRegularAccount));
			taOutput.appendText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			taOutput.appendText(displayStatistics("Checking    ", numCheckingAccounts, sumCheckingAccounts, numCheckingZeroAccounts, avgCheckingAccounts, largestCheckingAccountNumber, largestCheckingAccount));
			taOutput.appendText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			taOutput.appendText(displayStatistics("All Accounts", numAccounts, sumAccounts, numZeroAccounts, avgAccounts, largestAccountNumber, largestAccount));
			taOutput.appendText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			//taOutput.setFont(new Font("Arial", 8));
			
		}
		
		/** display statistics
		 * this makes changing the printed listing a lot easier than
		 * having to change 4 different version of the same thing
		 * 
		 * @param acctType the account type (Checking, Gold, Regular, All)
		 * @param numAccts the total number of accounts per account type
		 * @param sumAccts the sum of the balance of the accounts per account type
		 * @param numZero the number of accounts with a balance of zero balance per account type
		 * @param avgAccts the average balance of all the accounts per account type
		 * @param acctNumber the account number of the account with the largest balance per account type
		 * @param largeBal the largest balance of the accounts per account type
		 * 
		 */
		public String displayStatistics(String acctType, int numAccts, double sumAccts, int numZero, double avgAccts, String acctNumber, double largeBal) {
			return String.format("%s \t\t\t\t %-4d \t\t\t $%12.2f \t\t\t %3d \t\t\t $%12.2f \t\t %-20s  $%12.2f \n", acctType, numAccts, sumAccts, numZero, avgAccts, acctNumber, largeBal);
		}

		public double calcAverages(double num1, double num2) {
			double avgNum = 0.0;
			try {
				avgNum = num1 / num2;
			} catch (ArithmeticException e) {
				JOptionPane.showMessageDialog(null, "Invalid Arithimatic Operation", "Math Error", JOptionPane.ERROR);
				e.getStackTrace();
			}
			return avgNum;
		}
}
