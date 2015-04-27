package com.utilities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

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


public class BankMethods {
	public ArrayList<Customer> customers = new ArrayList<Customer>();
	public ArrayList<Account> accounts = new ArrayList<Account>();
	public ArrayList<Account> rejects = new ArrayList<Account>();
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	public BankUtilities bu = new BankUtilities();

	
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
    			 Customer customer = customers.get(0);
    			 for (int x = 0; x < customers.size(); x++) {
 	    			 if (customers.get(x).getCustomerID().equals(txtCustomerID.getText())) {
	    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleBlack);
	    				 customer = customers.get(x);
	    				 switch (acctNum) {
	    				 case 0: // checking
	    					 accounts.add(new Checking(txtAccountNumber.getText(), amount, customer));
	    					 break;
	    				 case 1: // regular
	    					 accounts.add(new Regular(txtAccountNumber.getText(), amount, customer));
	    					 break;
	    				 case 2: // gold
	    					 accounts.add(new Gold(txtAccountNumber.getText(), amount, customer));
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

	
}
