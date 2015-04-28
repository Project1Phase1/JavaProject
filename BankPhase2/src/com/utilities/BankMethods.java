package com.utilities;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	public String[] filename = new String[18]; // contains the path and file names of files and backups
	public String bankName; // bank name that will be stored in the config dat file
	public int bakup; // a backup token that will keep track of the backup number so backups will never over write each other
	// the general bank containers
	public ArrayList<Customer> customers = new ArrayList<Customer>();
	public ArrayList<Account> accounts = new ArrayList<Account>();
	public ArrayList<Account> rejects = new ArrayList<Account>();
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	// instance of the BankUtilities class
	public BankUtilities bu = new BankUtilities();
	
	/** load file names
	 *  0 - config.dat
	 *  1 - customer.dat
	 *  2 - checking.dat
	 *  3 - regular.dat
	 *  4 - gold.dat
	 *  5 - transaction.dat
	 *  6 - backup/config
	 *  7 - backup/customers
	 *  8 - backup/checking
	 *  9 - backup/regular
	 * 10 - backup/gold
	 * 11 - backup/transactions
	 * 12 - object/accounts.dat
	 * 13 - object/customers.dat
	 * 14 - object/transactions.dat
	 * 15 - backup/customers
	 * 16 - backup/accounts
	 * 17 - backup/transactions
	 * 
	 */
	public void loadFileName() {
		filename[0]  = "data/config/config.dat";
		filename[1]  = "data/customers/customers.dat";
		filename[2]  = "data/accounts/checking.dat";
		filename[3]  = "data/accounts/regular.dat";
		filename[4]  = "data/accounts/gold.dat";
		filename[5]  = "data/transactions/transactions.dat";
		filename[6]  = "backup/config/config";
		filename[7]  = "backup/customers/customers";
		filename[8]  = "backup/accounts/checking";
		filename[9]  = "backup/accounts/regular";
		filename[10] = "backup/accounts/gold";
		filename[11] = "backup/transactions/transactions";
		filename[12] = "data/objects/accounts.dat";
		filename[13] = "data/objects/customers.dat";
		filename[14] = "data/objects/transactions.dat";
		filename[15] = "backup/objects/accounts";
		filename[16] = "backup/objects/customers";
		filename[17] = "backup/objects/transactions";
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
		  
		  Label lblID = new Label("Customer ID");
		  Label lblName = new Label("Customers Name");
		  Label lblMessage = new Label("");
		  
		  TextField txtID = new TextField();
		  txtID.setStyle("-fx-font: 18 arial; -fx-text-fill: blue;");
		  TextField txtName = new TextField();
		  txtName.setStyle("-fx-font: 18 arial; -fx-text-fill: blue;");
		  Font times = Font.font("Times New Roman", FontWeight.BOLD, 16);

		  Button btnAdd = new Button("Add");
		  Button btnExit = new Button("Exit");
		  btnAdd.setFont(times);
		  btnExit.setFont(times);
		  
		  // set the color to the buttons
		  btnAdd.setStyle("-fx-font: 18 arial; -fx-base: blue;");
		  btnExit.setStyle("-fx-font: 18 arial; -fx-base: red;");

		  VBox idPane = new VBox(2);
		  idPane.setAlignment(Pos.CENTER);
		 idPane.getChildren().addAll(lblID, txtID);
		 
		  VBox namePane = new VBox(2);
		  namePane.setAlignment(Pos.CENTER);
		  namePane.getChildren().addAll(lblName, txtName);
		  
		  HBox messagePane = new HBox(1);
		  messagePane.setAlignment(Pos.CENTER);
		  messagePane.getChildren().add(lblMessage);
		  
		  HBox txtPane = new HBox(2);
		  txtPane.setPadding(new Insets(30,30,30,30));  
		  txtPane.getChildren().addAll(idPane, namePane);
		  txtPane.setAlignment(Pos.TOP_CENTER);
		  lblID.setFont(times);
		  lblName.setFont(times);
		  
		  HBox bottomPane = new HBox(2);
		  bottomPane.setPadding(new Insets(15,15,15,15));;
		  bottomPane.getChildren().addAll(btnAdd, btnExit);
		  bottomPane.setAlignment(Pos.CENTER);
		  
		  BorderPane custMainPane = new BorderPane();
		  custMainPane.setTop(txtPane);
		  custMainPane.setCenter(messagePane);
		  custMainPane.setBottom(bottomPane);
		  custMainPane.setStyle("-fx-border-color: blue");

		  txtID.setOnKeyTyped(e -> {
			  if (txtID.getText().equals("0")) {
				  // need to work on this and make it either pop up another window showing all the available customerID numbers
				  // or in a combo box on this form
				  lblMessage.setText("You entered a 0");
				  lblMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: black;");
			  } else {
				  lblMessage.setText(" ");
				  lblMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: black;");
			  }
		  });
		  
		  txtName.setOnKeyTyped(e -> {
				  lblMessage.setText(" ");
				  lblMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: black;");
		  });
		  
		  txtID.setOnAction(e -> {
			  if (txtID.getText().equals("0")) {
				  // need to work on this and make it either pop up another window showing all the available customerID numbers
				  // or in a combo box on this form
				  lblMessage.setText("You entered a 0");
 				 lblMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: black;");
			  } else {
			  txtName.requestFocus();
			  }
		  });
		  
		  txtName.setOnAction(e -> {
			  btnAdd.requestFocus();
		  });
		  
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
		  
		  btnExit.setOnAction(e -> {
			  btnExit.getScene().getWindow().hide();
			  });
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
		  int element = customers.size();
		  //JOptionPane.showMessageDialog(null, element);
		  if(txtID.getText().isEmpty()) {
			  lblMessage.setText("Please Enter Customer ID");
			  lblMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: red;");
			  //JOptionPane.showMessageDialog(null,  );
		  } else if (txtName.getText().isEmpty()) {
			  lblMessage.setText("Please Enter Customer Name");
			  lblMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: red;");
		  } else {
			  Customer customer = new Customer(txtID.getText(), txtName.getText());
			  customers.add(customer);
			  if (element < customers.size()) {
				  lblMessage.setText("Customer Created successfully!");
				  lblMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: blue;");
				  return true;
			  } else {
				  if (element == customers.size()) {
					  lblMessage.setText("Customer Creation Unsessful!!");
					  lblMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: red;");
				  }
			  }
		  }
		  return false;
	  }
	  
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
		  // heading font size
		  String heading = "25";
		  // message box font size
		  String txtMessage = "18";
		  // label font size
		  String txtLabel = "14";
		  // the beginning of the style string
		  String acctStyleFirst = "-fx-font: ";
		  // the last part of the style string before the color
		  String acctStyleLast =" arial; -fx-text-fill: ";
		  // the blue color
		  String acctStyleBlue = "blue;";
		  // the red color
		  String acctStyleRed = "red;";
		  // all buttons styles not including the color
		  String acctButtonStyle = "-fx-font: 18 arial; -fx-base: ";
		  String acctType = "";
		switch (acctNum) {
			case 0: // checking
				acctType = "Checking Account";
				break;
			case 1:// regular
				acctType = "Regular Account";
				break;
			case 2: // gold
				acctType = "Gold Account";
				break;
		}
		  // declare buttons and set their styles
		  Button btnAdd = new Button("Add");
		  btnAdd.setStyle(acctButtonStyle + "blue;");
		  Button btnExit = new Button("Exit");
		  btnExit.setStyle(acctButtonStyle + "red;");
		  Font sansbold12 = Font.font("arial", FontWeight.BOLD, 13);

		  TextArea listCust = new TextArea();
		  listCust.setPrefRowCount(5);
		  listCust.setPrefColumnCount(35);
		  listCust.setFont(sansbold12);
		  listCust.setEditable(false);
		  
		 Label lblLoadCustomers = new Label("Type 0 in Customer ID to display Customers in display box below!");
		 lblLoadCustomers.setStyle(acctStyleFirst + "14" + acctStyleLast + "green;");
		 
		 FlowPane leftPane = new FlowPane();
		 leftPane.setAlignment(Pos.CENTER);
		 leftPane.setHgap(5);
		 leftPane.setVgap(5);
		 leftPane.getChildren().addAll(lblLoadCustomers, listCust);
		  
		  // set up the account type heading
		  Label lbltxtDisplayBoxAccounts = new Label(acctType);  // heading
		  lbltxtDisplayBoxAccounts.setStyle(acctStyleFirst + heading  + acctStyleLast + acctStyleBlue);
		  // set up the error display message area
		  Label lbltxtDisplayBoxMessage = new Label(" "); // error reporting
		  
		  // declare the heading and labels
		  Label lblAccountNumber = new Label("Account Number");
		  lblAccountNumber.setStyle(acctStyleFirst + txtLabel + acctStyleLast + acctStyleBlue);
		  Label lblCustomerID = new Label("Customer ID");
		  lblCustomerID.setStyle(acctStyleFirst + txtLabel + acctStyleLast + acctStyleBlue);
		  Label lblOpeningBalance = new Label("Opening Balance");
		  lblOpeningBalance.setStyle(acctStyleFirst + txtLabel + acctStyleLast + acctStyleBlue);
		  
		  // declare the text boxes
		  TextField txtAccountNumber = new TextField();
		  txtAccountNumber.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleBlue);
		  TextField txtCustomerID = new TextField();
		  txtCustomerID.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleBlue);
		  TextField txtOpeningBalance = new TextField();
		  txtOpeningBalance.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleBlue);
		  
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
		  
		  // if the enter key is pressed in the account number move to the next box
		  txtAccountNumber.setOnAction(e -> {
			  txtOpeningBalance.requestFocus();
		  });
		  
		  // if the enter key is pressed in the customerID move to the next box
		  txtCustomerID.setOnAction(e -> {
				 if (txtCustomerID.getText().equals("0")) {
					 listCust.clear();
					 listCust.setText("");
					 for (Customer c: customers) {
						  listCust.appendText(c.toString() + "\n");
					  }
				  }
				 listCust.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleBlue);
			  txtAccountNumber.requestFocus();
		  });
		  
		  // if any key is pressed in the account number box, clear the message display label
		  txtAccountNumber.setOnKeyTyped(e ->{
			  lbltxtDisplayBoxMessage.setText(" ");
	     });
	     
		  // if any key is pressed in the customer id box, clear the message display label
	     txtCustomerID.setOnKeyTyped(e -> {
			  if (txtCustomerID.getText().equals("0")) {
				  listCust.clear();
				  listCust.setText("");
				  listCust.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleBlue);
				  for (Customer c: customers) {
					  listCust.appendText(c.toString() + "\n");
				  }
			  }
	    	 lbltxtDisplayBoxMessage.setText(" ");
	     });
	     
	     // if any key is pressed in the opening balance box, clear the message display label
	     txtOpeningBalance.setOnKeyTyped(e -> {
	    	 lbltxtDisplayBoxMessage.setText(" ");
	     });
	     
	     // if the enter key is pressed in the opening balance box, validate the input before continuing
	     txtOpeningBalance.setOnAction(e -> {
	    	double amount = -1;
	    	try {
		    	amount = Double.parseDouble(txtOpeningBalance.getText());
		    	if (amount < 0.0) {
    				 lbltxtDisplayBoxMessage.setText("Negative Opening Balances are not allow!\n\nPlease enter a positive Opening Balance!");
    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    				 Stage myStage = (Stage) txtOpeningBalance.getScene().getWindow();
    				 myStage.requestFocus();
	    			 txtOpeningBalance.requestFocus();
		    	}
    		 } catch (NumberFormatException h) {
    			 lbltxtDisplayBoxMessage.setText("I was expecting a Account Opening Balance, Please re-enter");
				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    			 txtOpeningBalance.requestFocus();
    		 } catch (InputMismatchException  g) {
    			 lbltxtDisplayBoxMessage.setText("You must enter the Opening Balance to the Account");
				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    			 txtOpeningBalance.requestFocus();
    		 } catch (NoSuchElementException f) {
    			 lbltxtDisplayBoxMessage.setText("Out of Range, Please re-enter the Opening Balance Ammount!");
				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    			 txtOpeningBalance.requestFocus();
    		 }
	     });
	     
	     // if the user pressed enter on the add button, call the create account method
	     btnAdd.setOnKeyTyped(e -> {
	    	 btnAdd.getOnKeyTyped();
	    	 boolean isAcctOk = false;
	    	 isAcctOk = createAccount( txtAccountNumber, txtCustomerID, txtOpeningBalance, lbltxtDisplayBoxMessage, acctNum);
	    	 if (isAcctOk) {
	    		 
	    	 }
	     });

	     // if the user clicks the add button, call the create account method
	     btnAdd.setOnAction(e -> {
	    	 boolean isAcctOk = false;
	    	 isAcctOk = createAccount( txtAccountNumber, txtCustomerID, txtOpeningBalance, lbltxtDisplayBoxMessage, acctNum);
	    	 if (isAcctOk) {
	    		 
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
		  String txtMessage = "18";
		  // the beginning of the style string
		  String acctStyleFirst = "-fx-font: ";
		  // the last pert of the style string before the color
		  String acctStyleLast =" arial; -fx-text-fill: ";
		  // the blue color
		  String acctStyleBlue = "blue;";
		  // the red color
		  String acctStyleRed = "red;";
		  // the black color
		  String acctStyleBlack = "black;";

    	 // check to see if the text boxes are empty and notify user
    	 if (txtAccountNumber.getText().isEmpty()) {
    		 lbltxtDisplayBoxMessage.setText("You must enter an Accouont Number!");
    		 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    		 txtAccountNumber.requestFocus();
    	 } else if (txtCustomerID.getText().isEmpty()) {
    		 lbltxtDisplayBoxMessage.setText("You must enter a Customer ID!");
    		 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    		 txtCustomerID.requestFocus();
    	 } else if (txtOpeningBalance.getText().isEmpty()) {
    		 lbltxtDisplayBoxMessage.setText("You must the Opening Balance!");
    		 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    		 txtOpeningBalance.requestFocus();
    	 } else {
    		 int element = accounts.size();
    		 double amount = -1.0;
    		 boolean isOk = false;
	    		 try {
	    			 amount = Double.parseDouble(txtOpeningBalance.getText());
	    			 if (amount < 0.0) {
	    				 lbltxtDisplayBoxMessage.setText("Negative Opening Balances are not allow!\n\nPlease enter a positive Opening Balance!");
	    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
	    				 txtOpeningBalance.requestFocus();
	    			
	    			 } else {
			    		 // JOptionPane.showMessageDialog(null, amount);
			    		 isOk = true;
	    			 }
	    		 } catch (NumberFormatException h) {
	    			 lbltxtDisplayBoxMessage.setText("I was expecting a Account Opening Balance, Please re-enter");
    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
	    			 txtOpeningBalance.requestFocus();
	    		
	    		 } catch (InputMismatchException  g) {
	    			 lbltxtDisplayBoxMessage.setText("You must enter the Opening Balance to the Account");
    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
	    			 txtOpeningBalance.requestFocus();
	    			
	    		 } catch (NoSuchElementException f) {
    				 lbltxtDisplayBoxMessage.setText("Out of Range, Please re-enter the Opening Balance Ammount!");
    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
	    		 }
    		 if (isOk) {
    			 String desc = "";
    			 Customer customer = customers.get(0);
    			 for (int x = 0; x < customers.size(); x++) {
 	    			 if (customers.get(x).getCustomerID().equals(txtCustomerID.getText())) {
	    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleBlack);
	    				 customer = customers.get(x);
	    				 switch (acctNum) {
	    				 case 0: // checking
	    					 desc = "Checking Opening Balance";
	    					 accounts.add(new Checking(txtAccountNumber.getText(), amount, customer));
	    					 transactions.add(new Transaction(bu.generateDate(), customer.getCustomerID(), txtAccountNumber.getText(), desc, amount, bu.generateUniqueTransNumber()));
	    					 break;
	    				 case 1: // regular
	    					 desc = "Regular Opening Balance";
	    					 accounts.add(new Regular(txtAccountNumber.getText(), amount, customer));
	    					 transactions.add(new Transaction(bu.generateDate(), customer.getCustomerID(), txtAccountNumber.getText(), desc, amount, bu.generateUniqueTransNumber()));
	    					 break;
	    				 case 2: // gold
	    					 desc = "Gold Opening Balance";
	    					 accounts.add(new Gold(txtAccountNumber.getText(), amount, customer));
	    					 transactions.add(new Transaction(bu.generateDate(), customer.getCustomerID(), txtAccountNumber.getText(), desc, amount, bu.generateUniqueTransNumber()));
	    					 break;
	    				 }
	    			 } else {
	    				 lbltxtDisplayBoxMessage.setText("Customer not fount!\n\nPlease re-enter Customer ID");
	    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
	    				 txtCustomerID.requestFocus();
	    			 }
    			 }
    			 // notify the user
 	    		 if (element < accounts.size()) {
    	 			 lbltxtDisplayBoxMessage.setText("Account successfully Added!");
    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleBlue);
    				 return true;
	    		 } else {
	    			 lbltxtDisplayBoxMessage.setText("Adding Checking Account Unsuccessful!");
    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
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
			  return;
		  } catch (FileNotFoundException e1) {
			  //JOptionPane.showMessageDialog(null, "Configuration file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
			  return;
		  } catch (IOException e1) {
			  JOptionPane.showMessageDialog(null, "Configuration File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
			  
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
				 return;
			  } catch (FileNotFoundException e1) {
				  //JOptionPane.showMessageDialog(null, "Customer file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  return;
			  } catch (IOException e1) {
				  JOptionPane.showMessageDialog(null, "Customer File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  
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
				  return;
			  } catch (FileNotFoundException e1) {
				  //JOptionPane.showMessageDialog(null, "Account file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  return;
			  } catch (IOException e1) {
				  JOptionPane.showMessageDialog(null, "Account File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  
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
				  return;
			  } catch (FileNotFoundException e1) {
				  //JOptionPane.showMessageDialog(null, "Regular file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  return;
			  } catch (IOException e1) {
				  JOptionPane.showMessageDialog(null, "Regular File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  
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
				  return;
			  } catch (FileNotFoundException e1) {
				  //JOptionPane.showMessageDialog(null, "Gold file " + filename[0] + " does not exist!", "File Error", JOptionPane.ERROR_MESSAGE);
				  return;
			  } catch (IOException e1) {
				  JOptionPane.showMessageDialog(null, "Gold File Read Error", "File Read Error", JOptionPane.ERROR_MESSAGE);
				  
			  }
		  } else {
			  JOptionPane.showMessageDialog(null, "Gold Accounts have already been loaded!", "Load Accounts Error", JOptionPane.INFORMATION_MESSAGE);
		  }
	  }

	  
// **************************************** save ****************************************
	  
	  // *************************** config ***************************

	  public void saveConfigData() {
		  try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename[0], false));) {
			  output.writeUTF(bankName);
			  output.writeInt(bakup);
			  
		  } catch (IOException e1) {
			  JOptionPane.showMessageDialog(null, "Config File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  
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
		  } catch (IOException e1) {
			  JOptionPane.showMessageDialog(null, "Customer File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  
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
		  } catch (IOException e1) {
			  JOptionPane.showMessageDialog(null, "Checking File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  
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
		  } catch (IOException e1) {
			  JOptionPane.showMessageDialog(null, "Regular File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  
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
		  } catch (IOException e1) {
			  JOptionPane.showMessageDialog(null, "Gold File Write Error", "File Write Error", JOptionPane.ERROR_MESSAGE);
			  
		  }
		  JOptionPane.showMessageDialog(null, counter + " Gold Data Saved successfully!", "Gold Save Data", JOptionPane.INFORMATION_MESSAGE);
	  }
	
	  
// ***************************************************************************** object file system *****************************************************************************
	  
	  // ***************************************************************** load *****************************************************************
	  
	  // ************************************** customer **************************************
	@SuppressWarnings("unchecked")
	public void loadCustomerObjectData() {
		File info = new File(filename[13]);
		if (info.exists()) {
			  try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename[13]));) {
				  customers = (ArrayList<Customer>) input.readObject();
				  
			  } catch (EOFException e) {
				  return;
				  
			  } catch (FileNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "File " + filename[13] + " not found!", "Customer Read Error", JOptionPane.ERROR_MESSAGE);
				  
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Customer Read Error", "Read Error", JOptionPane.ERROR_MESSAGE);
				  
			  } catch (ClassNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "Unable to create object", "Error Reading Customer", JOptionPane.ERROR);
				  
			  }
		}
	}
	  
	
	// ************************************** account **************************************
		
	  @SuppressWarnings("unchecked")
	public void loadAccountObjectData() {
		  File info = new File(filename[12]);
		  if (info.exists()) {
			  try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename[12]));) {
				  accounts = (ArrayList<Account>) input.readObject();
				  
			  } catch (FileNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "File " + filename[13] + " not found!", "Account Read Error", JOptionPane.ERROR_MESSAGE);
				  
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Account Read Error", "Read Error", JOptionPane.ERROR_MESSAGE);
				  
			  } catch (ClassNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "Unable to create object", "Error Reading Account", JOptionPane.ERROR);
				  
			  }
		  }
	  }
	  
	// ************************************** transactions **************************************
	  
	  @SuppressWarnings("unchecked")
	  public void loadTransactionObjectData() {
		  File info = new File(filename[14]);
		  if (info.exists()) {  
			  try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename[14]));) {
				  transactions = (ArrayList<Transaction>) input.readObject();
				  
			  } catch (FileNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "File " + filename[13] + " not found!", "Transaction Read Error", JOptionPane.ERROR_MESSAGE);
				  
			  } catch (IOException e) {
				  JOptionPane.showMessageDialog(null, "Transaction Read Error", "Read Error", JOptionPane.ERROR_MESSAGE);
				  
			  } catch (ClassNotFoundException e) {
				  JOptionPane.showMessageDialog(null, "Unable to create object", "Error Reading Transaction", JOptionPane.ERROR);
				  
			  }
		  } else {
			  return;
		  }
	  }
	  
	  // ***************************************************************** save *****************************************************************

		// ************************************** customer **************************************

	  public void saveCustomerObjectData() {
		  try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename[13], true));) {
			  output.writeObject(customers);
			  
		  } catch (FileNotFoundException e) {
			  JOptionPane.showMessageDialog(null, "File " + filename[13] + " not found!", "Customer Save Error", JOptionPane.ERROR_MESSAGE);
			  
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Customer Read Error", "Save Error", JOptionPane.ERROR_MESSAGE);

		  }
	  }
	  
		// ************************************** customer **************************************

	  public void saveAccountObjectData() {
		  try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename[12], true));) {
			  output.writeObject(accounts);
		  } catch (FileNotFoundException e) {
			  JOptionPane.showMessageDialog(null, "File " + filename[13] + " not found!", "Account Save Error", JOptionPane.ERROR_MESSAGE);
			  
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Account Read Error", "Save Error", JOptionPane.ERROR_MESSAGE);

		  }
	  }
	  
		// ************************************** customer **************************************

	  public void saveTransactionObjectData() {
		  try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename[14], true));) {
			  output.writeObject(transactions);
		  } catch (FileNotFoundException e) {
			  JOptionPane.showMessageDialog(null, "File " + filename[13] + " not found!", "Transaction Save Error", JOptionPane.ERROR_MESSAGE);
			  
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "Transaction Read Error", "Save Error", JOptionPane.ERROR_MESSAGE);

		  }
	  }
	  
	  // *********************************** create new System Data *****************************************
	  // ************* this will delete the files so be careful ****************************
	  
	  public void createNewSystemData() {
		  try {
			  File startOver;
			  startOver = new File(filename[0]);
			  startOver.delete();
			  for (int x = 12; x < 15; x++) {
				  startOver = new File(filename[x]);
				  startOver.delete();
			  }
			  JOptionPane.showMessageDialog(null, "New File System Ready For Use!", "New Files", JOptionPane.INFORMATION_MESSAGE);
			  bankName = "";
			  loadConfigData();
		  } catch (NullPointerException e) {
			  
		  } catch (SecurityException e) {
			  
		  }
	  }
}
