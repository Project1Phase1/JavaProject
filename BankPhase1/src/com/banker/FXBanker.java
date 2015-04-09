/**
 * @mentor Professor Dr. Awny Alnusair<br><br>
 * @college Indiana University Kokomo<br><br><br>
 * @course INFO-211 Informatics II<br><br>
 * 
 * 
 * @authors Ian Holtson<br>Jeremiah McKinney<br>Theral Jessop<br>
 * Apr 6, 2015<br>
 * Banker.java<br>
 *
 */
package com.banker;
import java.util.*;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.accounts.*;
import com.customers.*;
import com.transactions.*;
import com.utilities.*;

/** client application<br>
 * displays a menu and all 
 * 
 * 
 * 
 * @mentor Professor Dr. Awny Alnusair<br>
 * @college Indiana University Kokomo<br><br>
 * @course INFO-211 Informatics II<br>
 * 
 * 
 * @authors Ian Holtson, Jeremiah McKinney, Theral Jessop<br>
 * Apr 8, 2015<br>
 * FXBanker.java<br>
 *
 */
@SuppressWarnings("unused")
public class FXBanker extends Application {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
	    Scene scene = new Scene(getPane(), 280, 350);
	    primaryStage.setTitle("Unrealistic Bank Application"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
		
		
		
		/*
		ActualFXBanker banker = new ActualFXBanker();
		banker.doBanker();
		*/
	}
	
	  protected BorderPane getPane() {

		  //Declare some UI Controls (Labels, Buttons, Textfields, etc.)
		 // Label lblName = new Label("Enter your name");
		 // Label lblNumber = new Label("Enter a number");

		  
		  Button btnOne =    new Button(" 1. Create Regular account");
		  Button btnTwo =    new Button(" 2. Create Gold account");
		  Button btnThree =  new Button(" 3. Create Checking account");
		  Button btnFour =   new Button(" 4. Deposit money into a choosen account");
		  Button btnFive =   new Button(" 5. Withdraw money from a choosen account");
		  Button btnSix =    new Button(" 6. Display account information");
		  Button btnSeven =  new Button(" 7. Remove a a choosen account");
		  Button btnEight =  new Button(" 8. EOM Calculations");
		  Button btnNine =   new Button(" 9. Generate Statistics");
		  Button btnTen =    new Button("10. Generate Transaction report");
		  Button btnEleven = new Button("11. Exit");
		  
		  
		  /*
		  Button btnName = new Button("Display Name");
		  Button btnNumber = new Button("Display Number");
		  Button btnClear = new Button("Clear");
		  Button btnExit = new Button("Exit");
		  btnExit.setStyle("-fx-font: 18 arial; -fx-base: #ff2222;");
			*/
		  Font sansbold12 = Font.font("SansSerif", FontWeight.BOLD, 13);

		  //TextField txtName = new TextField();
		  //TextField txtNumber = new TextField();
		  //TextArea taOutput = new TextArea();
		  //taOutput.setPrefRowCount(5);
		  //taOutput.setPrefColumnCount(35);
		  //taOutput.setFont(sansbold12);

		  //Create some Panes:
		  /*
		  //FlowPane adds nodes row by row horizontally or col by col vertically
		  FlowPane topPane = new FlowPane();
		  //Sets the top, right, bottom, and left padding around the region's content
		  topPane.setPadding(new Insets(11,12,13,14));
		  //Set the amount of horizontal space between each node
		  topPane.setHgap(5);
		  //Set the amount of vertical space between each node
		  topPane.setVgap(5);
		 // topPane.getChildren().addAll(lblName, txtName, lblNumber, txtNumber);
		  topPane.setAlignment(Pos.CENTER_LEFT);

		  FlowPane centerPane = new FlowPane();
		  //centerPane.setPadding(new Insets(11,0,5,0));
		  centerPane.setHgap(5);
		  centerPane.setVgap(5);
		  centerPane.getChildren().add(taOutput);
		  centerPane.setAlignment(Pos.CENTER);
*/
		  //HBpx pane lays out its children in a single horizontal row
		  //HBox bottomPane = new HBox(4);
		  VBox centerPane = new VBox(5);
		  //                           top, right, bottom, left
		  centerPane.setPadding(new Insets(10, 10, 10, 10));
		  centerPane.getChildren().addAll(btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnTen, btnEleven);
		  centerPane.setAlignment(Pos.TOP_LEFT);
		  //bottomPane.setPadding(new Insets(15,15,15,15));
		  //bottomPane.getChildren().addAll(btnName, btnNumber, btnClear, btnExit);
		  //bottomPane.setAlignment(Pos.CENTER);

		  //The following uses a BorderPane: Where nodes are placed in five regions:
		  //top, bottom, left, right, center
		  BorderPane mainPane = new BorderPane();
		  //mainPane.setTop(topPane);
		  mainPane.setCenter(centerPane);
		  //mainPane.setBottom(bottomPane);
		  mainPane.setStyle("-fx-border-color: red");

		  /*
		  // handle events go here
		  
		  //Handling button click events:
		  //Buttons have an onAction Property which sets a handler for handling button actions:
		  btnName.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
					String name = txtName.getText();
					taOutput.setText("The name you entered is: " + name);
			    }
			});

		  btnNumber.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
					try{
						double num = Double.parseDouble(txtNumber.getText());
						taOutput.appendText("\nSquare root of your number is: " + Math.sqrt(num));
				}
				catch(NumberFormatException a){
						JOptionPane.showMessageDialog(null, "You must enter a number", "Wrong Input", JOptionPane.INFORMATION_MESSAGE);
				}
			    }
			});


		  btnClear.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
				  	taOutput.clear();
			    }
			});
		  
		  //You can also use the new Java 8 Lambda expression feature as follows:
		  //A lambda expression can be viewed as an anonymous class with precise syntax
		  */
		  btnOne.setOnAction(e -> {
			  ActualFXBanker banker = new ActualFXBanker();
			  banker.createRegularAccount();
		  });
		  
		  btnEleven.setOnAction(e -> {//code for processing event e
				  	JOptionPane.showMessageDialog(null, "Exiting!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
					System.exit( 0 ); 
					//class System is part of the Java.lang package thats imported automatically for every java program.
					//exit: is a method that terminate any application that has graphical user interface components
			});
		  
		  
		  return mainPane;
	  }
	  
	  protected void cust(Stage customerStage) {
		  Scene customer = new Scene(getCustPane(), 650, 250);
		    customerStage.setTitle("Create Customer"); // Set the stage title
		    customerStage.setScene(customer); // Place the scene in the stage
		    customerStage.show(); // Display the stage
		  
	  }
	  
	  protected void regular(Stage regularAccount) {
		  Scene regular = new Scene(getRegularPane(), 650, 350);
		  regularAccount.setTitle("Create Regular Account");
		  regularAccount.setScene(regular);
		  regularAccount.show();
		 
	  }
	  
	  protected BorderPane getCustPane(){
		  
		  
			  Label lblID = new Label("Enter customer ID");
			  Label lblName = new Label("Enter customers name");
			  
			  TextField txtID = new TextField();
			  TextField txtName = new TextField();
			  
			  Button btnAdd = new Button("Add");
			  HBox topPane = new HBox(5);
			  topPane.setPadding(new Insets(15,15,15,15));  
			  topPane.getChildren().addAll(lblID, txtID, lblName, txtName);
			  topPane.setAlignment(Pos.BOTTOM_LEFT);
			  HBox bottomPane = new HBox(5);
			  bottomPane.setPadding(new Insets(15,15,15,15));;
			  bottomPane.getChildren().addAll(btnAdd);
			  
			  //bottomPane.setPadding(new Insets(15,15,15,15));
			  //bottomPane.getChildren().addAll(btnName, btnNumber, btnClear, btnExit);
			  //bottomPane.setAlignment(Pos.CENTER);

			  BorderPane custMainPane = new BorderPane();
			  custMainPane.setTop(topPane);
			  //mainPane.setCenter(centerPane);
			  custMainPane.setBottom(bottomPane);
			  custMainPane.setStyle("-fx-border-color: red");
			  
			  btnAdd.setOnAction(e -> {
				  ActualFXBanker banker = new ActualFXBanker();
				  banker.createCustomer(txtID.getText(), txtName.getText());
			  });

		  return custMainPane;
	  }
	  
	  protected BorderPane getRegularPane(){
		  
		  
		  BorderPane regMainPane = new BorderPane();
		  
		  return regMainPane;
	  }
	  

}


class ActualFXBanker {
	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Account> reject = new ArrayList<Account>(); // eom accounts that had problems while processing
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	BankUtilities bu = new BankUtilities();
	
	public void doBanker() {
		
		// menu
		//eomCalculations(reject);
		
	}
	
	/** create customer<br><br>
	 * 
	 * get customer id and name from user then create customer<br>
	 * add it to the customer ArrayList<br>
	 * 
	 * @param customerID the customer id (String)
	 * @param customerName the customer name (String)
	 * 
	 */
	public void createCustomer(String customerID, String customerName) {
		Customer customer = new Customer(customerID, customerName);
		customers.add(customer);
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
	
	/** create checking account<br><br>
	 * 
	 * check to see if there are any customers to add before trying to add an account<br>
	 * if there are no customers then it will ask to create them<br><br>
	 * 
	 * if user does not want to create them right now it will terminate the process<br>
	 * and return to the menu<br><br>
	 * 
	 * allow the user to select a customer form a list<br>
	 * once selected it will then ask for the account information<br>
	 * (account number, balance)<br><br>
	 * 
	 * create account from user input and add the account to the available accounts<br>
	 * and add a transaction to the transaction tracker with "Opening Checking Account" as the <br>
	 * description<br>
	 * 
	 */
	public void createCheckingAccount() {
		// declare a portable string
		String type = "Checking";
		// clear the input not closed warning and create a scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// clear the skip not used warning and create a skip value for remaining tokens
		@SuppressWarnings("unused")
		String skip;
		// declare a customer variable to check against available customers
		Customer customer;
		// are there any customers available to choose from?
		while (customers.isEmpty()) {
			// no customer so ask if user wants to add one now
			System.out.print("No customers available to complete task!\n");
			System.out.print("Add customer? (y/n) ");
			String getAnswer = input.nextLine();
			// if user wants to add one now then get it
			if (getAnswer.equalsIgnoreCase("y")) {
				// get customer to use for creating account
				//createCustomer();
			} else {
				// user does not want to add one now. Terminate and return to the menu
				System.out.print("\nTerminationg create " + type + " account!\n");
				return;
			}
		}
		// display the available customers to choose from
		System.out.println("\nChoose the customer for this " + type + " Account");
		System.out.println("===========================================================================");
		int counter = 0;
		for (Customer c: customers) {
			System.out.print(counter + ". " + c.toString() + "\n");
			counter++;
		}
		// get the account number to use from the user
		System.out.print("Choose which customer to create " + type + " account for (1 - " + counter + ") ");
		int whichCustomer = input.nextInt();
		// validate user input
		while (whichCustomer < 1 && whichCustomer > counter) {
			whichCustomer = input.nextInt();
		}
		// check to see if there is another token and take care of it
		if (input.hasNext()) {
			skip = input.nextLine();
		}
		// select the customer the user chose
		customer = customers.get((whichCustomer - 1));
		// get account information from user (Account number, balance)
		System.out.print("Enter account number for this " + type + " account: ");
		String accountNumber = input.nextLine();
		System.out.print("Ener account Balance: ");
		double accountBalance = input.nextDouble();
		// take care of extra tokens
		if (input.hasNext()) {
			skip = input.nextLine();
		}
		// add the account
		accounts.add(new Checking(accountNumber, accountBalance, customer));
		// add transaction to transaction tracker
		createTransaction(customers.get((whichCustomer - 1)).getCustomerID(), accountNumber, "Opening Checking Account", accountBalance);
		// notify user of account creation
		System.out.println("\n" + type + " account successfully created!\n");
	}

	/** create gold account<br><br>
	 * 
	 * check to see if there are any customers to add before trying to add an account<br>
	 * if there are no customers then it will ask to create them<br><br>
	 * 
	 * if user does not want to create them right now it will terminate the process<br>
	 * and return to the menu<br><br>
	 * 
	 * allow the user to select a customer form a list<br>
	 * once selected it will then ask for the account information<br>
	 * (account number, balance)<br><br>
	 * 
	 * create account from user input and add the account to the available accounts<br>
	 * and add a transaction to the transaction tracker with "Opening Gold Account" as the <br>
	 * description<br>
	 * 
	 */
	public void createGoldAccount() {
		// declare a portable string
		String type = "Gold";
		// clear the input not closed warning and create a scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// clear the skip unused warning and set up a extra token capture
		@SuppressWarnings("unused")
		String skip;
		// declare customer
		Customer customer;
		// check to see if there are any customers available
		while (customers.isEmpty()) {
			// warn user and get response
			System.out.print("No customers available to complete task!\n");
			System.out.print("Add customer? (y/n) ");
			String getAnswer = input.nextLine();
			// determine if the user wants to add customers now
			if (getAnswer.equalsIgnoreCase("y")) {
				// create customer
				//createCustomer();
			} else {
				// notify user of choice
				System.out.print("\nTerminationg create " + type + " account!\n");
				return;
			}
		}
		// display available customers 
		System.out.println("\nChoose the customer for this " + type + " account");
		System.out.println("===========================================================================");
		int counter = 0;
		for (Customer c: customers) {
			System.out.print(counter + ". " + c.toString() + "\n");
			counter++;
		}
		// get users choice
		System.out.print("Choose which customer to create "+ type + " account for (1 - " + counter + ") ");
		int whichCustomer = input.nextInt();
		// validate users input
		while (whichCustomer < 1 && whichCustomer > counter) {
			whichCustomer = input.nextInt();
		}
		// determine if there are extra tokens and take care of them
		if (input.hasNext()) {
			skip = input.nextLine();
		}
		// get customer selected for account creation
		customer = customers.get((whichCustomer - 1));
		// get account information (Account number, account balance)
		System.out.print("Enter account number for this  account: ");
		String accountNumber = input.nextLine();
		System.out.print("Ener account Balance: ");
		double accountBalance = input.nextDouble();
		// capture floating tokens
		if (input.hasNext()) {
			skip = input.nextLine();
		}
		// add account
		accounts.add(new Gold(accountNumber, accountBalance, customer));
		// create a record of the transaction
		createTransaction(customers.get((whichCustomer - 1)).getCustomerID(), accountNumber, "Opening Gold Account", accountBalance);
		// notify user of success
		System.out.println("\n" + type + " account successfully created!\n");
	
	}

	/** create regular account<br><br>
	 * 
	 * check to see if there are any customers to add before trying to add an account<br>
	 * if there are no customers then it will ask to create them<br><br>
	 * 
	 * if user does not want to create them right now it will terminate the process<br>
	 * and return to the menu<br><br>
	 * 
	 * allow the user to select a customer form a list<br>
	 * once selected it will then ask for the account information<br>
	 * (account number, balance)<br><br>
	 * 
	 * create account from user input and add the account to the available accounts<br>
	 * and add a transaction to the transaction tracker with "Opening Regular Account" as the <br>
	 * description<br>
	 * 
	 */
	public void createRegularAccount() {
		// declare a portable string
		String type = "Regular";
		// clear warning of input not closed and declare scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// clear skip unused warning and set up token capture variable
		@SuppressWarnings("unused")
		String skip;
		// declare customer variable
		Customer customer;
		// are the customers empty
		while (customers.isEmpty()) {
			// notify user and get response
			System.out.print("No customers available to complete task!\n");
			System.out.print("Add customer? (y/n) ");
			String getAnswer = input.nextLine();
			// determine if the user wants to add customer now
			if (getAnswer.equalsIgnoreCase("y")) {
				// create user
				//createCustomer();
			} else {
				// notify user of choice and re-display the menu
				System.out.print("\nTerminating create " + type + " account!\n");
				return;
			}
		}
		// show available customers to choose from
		System.out.println("\nChoose the customer for this " + type + " account");
		System.out.println("===========================================================================");
		int counter = 0;
		for (Customer c: customers) {
			counter++;
			System.out.print(counter + ") " + c.toString() + "\n");
		}
		// get response from user
		System.out.print("Choose which customer to create "+ type + " account for (1 - " + counter + ") ");
		int whichCustomer = 0;
		// validate users input
		while ((whichCustomer < 1) && (whichCustomer > counter)) {
			whichCustomer = input.nextInt();
		}
		// capture any floating tokens
		if (input.hasNext()) {
			skip = input.nextLine();
		}
		System.out.println("Check whichCustomer value: " + whichCustomer);
		skip = input.nextLine();
		// select the users choice of customer
		customer = customers.get((whichCustomer - 1));
		// get account information (Account number, account balance)
		System.out.print("Enter account number for this  account: ");
		String accountNumber = input.nextLine();
		System.out.print("Ener account Balance: ");
		double accountBalance = input.nextDouble();
		// suppress any extra tokens
		if (input.hasNext()) {
			skip = input.nextLine();
		}
		// create account
		accounts.add(new Gold(accountNumber, accountBalance, customer));
		// add transaction to transaction tracker
		createTransaction(customers.get((whichCustomer - 1)).getCustomerID(), accountNumber, "Opening Regular Account", accountBalance);
		// notify user of success
		System.out.println("\n" + type + " account successfully created!\n");
	}
	
	/** create make deposit<br><br>
	 * 
	 * check to see if there are any customers to make deposits to<br>
	 * if there are no customers it will terminate<br><br>
	 * 
	 * displays a list of account numbers to choose from<br>
	 * ask the user for the amount of the deposit<br>
	 * user will be notified of success or failure<br><br>
	 * 
	 * determine whether it is the checking, gold or regular account<br>
	 * and add it the description: example: "Checking Deposit"
	 * add a transaction to the transaction tracker<br><br>
	 * 
	 */	
	public void makeDeposit() {
		// declare description string
		String description = "";
		// check to see if there any accounts
		if (accounts.isEmpty()) {
			System.out.println("There are no accounts to add a deposit to!\nTerminating make deposit!\n");
			return;
		}
		// clear input not closed warning and declare scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// present account information to user and get response
		System.out.print("                           Make Deposit");
		System.out.print("\n=============================================================\n");
		int counter = 1;
		System.out.print("Available accounts\n-----------------------------------------\n");
		// list available accounts in a menu style
		for (Account a: accounts) {
			System.out.print(counter + ".) " + a.getAccountNumber() + "\n");
			counter++;
		}
		// get users choice
		System.out.print("\nChoose which account will receive the deposit: (1 - " + counter + ")");
		int whichAccount = input.nextInt();
		// validate users choice
		while (whichAccount < 1 || whichAccount > counter) {
			whichAccount = input.nextInt();
		}
		// lower choice to array type value (starts at 0)
		whichAccount -= 1;
		// declare account variable
		Account account;
		// determine which type of account the account number
		// belongs to and set it to account variable
		// and set the portable string
		if (accounts.get(whichAccount) instanceof Checking) {
			account = (Checking) accounts.get(whichAccount);
			description = "Checking Deposit";
		} else if (accounts.get(whichAccount) instanceof Gold) {
			account = (Gold) accounts.get(whichAccount);
			description = "Gold Deposit";
		} else {
			account = (Regular) accounts.get(whichAccount);
			description = "Regular Deposit";
		}
		// get deposit amount
		System.out.print("Enter the amount of the deposit ");
		double amount = input.nextDouble();
		// create transaction in tracker
		// add deposit and notify user
		System.out.print(account.makeDeposit(amount));
		createTransaction(account.getCustomer().getCustomerID(), account.getAccountNumber(), description, amount);
	}

	
	/** create make withdrawal<br><br>
	 * 
	 * check to see if there are any customers to make withdrawal from<br>
	 * if there are no customers it will terminate<br><br>
	 * 
	 * displays a list of account numbers to choose from<br>
	 * ask the user for the amount of the withdrawal<br>
	 * user will be notified of success or failure<br><br>
	 * 
	 * determine whether it is the checking, gold or regular account<br>
	 * and add it the description: example: "Checking Withdrawal"
	 * add a transaction to the transaction tracker<br><br>
	 * 
	 */	
	public void makewithdrawal() {
		// declare description string
		String description = "";
		// check to see if there any accounts
		if (accounts.isEmpty()) {
			System.out.println("There are no accounts to make a withdrawal from!\nTerminating make withdrawal!\n");
			return;
		}
		// clear input not closed warning and declare scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// present account information to user and get response
		System.out.print("                           Make Withdrawal");
		System.out.print("\n=============================================================\n");
		int counter = 1;
		System.out.print("Available accounts\n-----------------------------------------\n");
		// list available accounts in a menu style
		for (Account a: accounts) {
			System.out.print(counter + ".) " + a.getAccountNumber() + "\n");
			counter++;
		}
		// get users choice
		System.out.print("\nChoose which account to withdraw from: (1 - " + counter + ")");
		int whichAccount = input.nextInt();
		// validate users choice
		while (whichAccount < 1 || whichAccount > counter) {
			whichAccount = input.nextInt();
		}
		// lower choice to array type value (starts at 0)
		whichAccount -= 1;
		// declare account variable
		Account account;
		// determine which type of account the account number
		// belongs to and set it to account variable
		// and set the portable string
		if (accounts.get(whichAccount) instanceof Checking) {
			account = (Checking) accounts.get(whichAccount);
			description = "Checking Withdrawal";
		} else if (accounts.get(whichAccount) instanceof Gold) {
			account = (Gold) accounts.get(whichAccount);
			description = "Gold Withdrawal";
		} else {
			account = (Regular) accounts.get(whichAccount);
			description = "Regular Withdrawal";
		}
		// get deposit amount
		System.out.print("Enter the amount of the withdrawal ");
		double amount = input.nextDouble();
		// create transaction in tracker
		createTransaction(account.getCustomer().getCustomerID(), account.getAccountNumber(), description, amount);
		// add deposit and notify user
		System.out.print(account.makeDeposit(amount));
	}

	/** remove account<br><br>
	 * 
	 * check to see if there are any accounts to remove<br>
	 * and if there are then display the account number in a menu<br>
	 * style allowing the user to choose the one to remove<br><br>
	 * 
	 * create a transaction in the tracker with a description and the current balance<br><br>
	 * 
	 * notify user of the success
	 * 
	 * 
	 * 
	 */
	public void removeAccount() {
		// declare description string
		String description = "";
		// check to see if there any accounts
		if (accounts.isEmpty()) {
			System.out.println("There are no accounts to remove!\nTerminating remove account!\n");
			return;
		}
		// clear input not closed warning and declare scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// present account information to user and get response
		System.out.print("                         Remove Account");
		System.out.print("\n=============================================================\n");
		int counter = 1;
		System.out.print("Available accounts\n-----------------------------------------\n");
		// list available accounts in a menu style
		for (Account a: accounts) {
			System.out.print(counter + ".) " + a.getAccountNumber() + "\n");
			counter++;
		}
		// get users choice
		System.out.print("\nChoose which account to remove: (1 - " + counter + ")");
		int whichAccount = input.nextInt();
		// validate users choice
		while (whichAccount < 1 || whichAccount > counter) {
			whichAccount = input.nextInt();
		}
		// lower choice to array type value (starts at 0)
		whichAccount -= 1;
		// set the description string according to which type of account
		Account account;
		if (accounts.get(whichAccount) instanceof Checking) {
			account = (Checking) accounts.get(whichAccount);
			description = "Remove Checking Account";
		} else if (accounts.get(whichAccount) instanceof Gold) {
			account = (Gold) accounts.get(whichAccount);
			description = "Remove Gold Account";
		} else {
			account = (Regular) accounts.get(whichAccount);
			description = "Remove Regular Account";
		}
		// get deposit amount
		double amount = account.getAccountBalance();
		// create transaction in tracker
		createTransaction(account.getCustomer().getCustomerID(), account.getAccountNumber(), description, amount);
		// remove account
		accounts.remove(account);
		// Notify user that account was removed
		System.out.print("\nSuccessfully removed account!\n");
	}

	/** end of month (eom) calculations<br><br>
	 * 
	 * this will allow for processing not only the accounts<br>
	 * but also the rejects from running the eom<br>
	 * 
	 * 
	 * @param process the ArrayList
	 */
	public void eomCalculations(ArrayList<Account> process) {
		// declare variables
		String description;
		// declare description string
		// check to see if there are any accounts 
		if (accounts.isEmpty()) {
			System.out.println("There are no accounts to process!\nTerminating EOM calculations!\n");
			return;
		}
		// loop through the accounts
		for (Account a: process) {
			
			// determine which type of account the account number
			// belongs to and set it to account variable
			// and set the description
			// is it a checking account
			if (a instanceof Checking) {
				Checking chk = (Checking)a ;
				description = "EOM Checking Account";
				// validate the amount of the fees before posting
				if (((chk.getNumberOfTransactions() - 2) * chk.getCheckingTransactionFee()) == chk.getCheckingTransactionFeeAmount()) {
					chk.setAccountBalance(chk.getAccountBalance() - chk.getCheckingTransactionFeeAmount());
					createTransaction(chk.getCustomer().getCustomerID(), chk.getAccountNumber(), description, chk.getCheckingTransactionFeeAmount());
				} else {
					// if this is reached there is a critical error
					System.out.print("\nCRITICAL ERROR! Transaction Fees do not match!\n\n");
					reject.add(chk); // put the troubled account into a reject holder until it can be properly processed
					continue;
				}
				// is the account a gold account
			} else if (a instanceof Gold) {
				Gold gold = (Gold)a;
				description = "EOM Gold Account";
				if (gold.getAccountBalance() <= 0) {
					System.out.println("Unable to calculate interest due to a lack of funds!\n");
					continue;
				}
				// calculate interest for 1 year compounded monthly7
				// I = P x (1 + r/n)^(n x t)
				// P = accountBalance : Principle
				// r = rate : interest rate (in decimal)
				// t = year : number of years, months, days, etc in this case it is years
				// n = numTimes : how often : months, quarters, days etc. in this case it is one month
				
				double rate = gold.getGoldInterestRate() / 100;
				double years = 1;
				double numTimes = 1/12.0;
				double interest = ((Math.floor(((gold.getAccountBalance() * Math.pow(1 + (rate / numTimes), (numTimes * years))) - gold.getAccountBalance())*100.0))/100.0);
				// add interest to balance
				gold.setAccountBalance(gold.getAccountBalance() + interest);
				// add transaction to transaction tracker
				createTransaction(gold.getCustomer().getCustomerID(), gold.getAccountNumber(), description, interest);
				// it can only be a regular account 
			} else {
				// process regular accounts
				Regular reg = (Regular)a;
				description = "EOM Regular Account";
				if (reg.getAccountBalance() <= 0) {
					System.out.println("Unable to calculate interest due to a lack of funds!\n");
					continue;
				}
				// calculate interest for 1 year compounded monthly7
				// I = P x (1 + r/n)^(n x t)
				// P = accountBalance : Principle
				// r = rate : interest rate (in decimal)
				// t = year : number of years, months, days, etc in this case it is years
				// n = numTimes : how often : months, quarters, days etc. in this case it is one month
				
				double rate = reg.getRegularInterestRate() / 100;
				double years = 1;
				double numTimes = 1/12.0;
				double interest = ((Math.floor(((reg.getAccountBalance() * Math.pow(1 + (rate / numTimes), (numTimes * years))) - reg.getAccountBalance())*100.0))/100.0);
				// apply interest to balance
				reg.setAccountBalance(reg.getAccountBalance() + interest);
				// add to tracker
				createTransaction(reg.getCustomer().getCustomerID(), reg.getAccountNumber(), description, interest);
			}
		}
	}
	
	/** generate statistics<br><br>
	 * 
	 * loop through the accounts and get the largest amount, account with<br>
	 * largest balance, amount of all the accounts, number of zero balance accounts,<br>
	 * average balance of the accounts<br><br>
	 * 
	 * Display them in a nice manner
	 * 
	 * 
	 */
	public void generateStatistics() {
		// need:
		// total number of accounts
		// sum of all accounts
		// number of zero balance accounts
		// average balance of the accounts
		// largest balance accounts
		int numAccounts = 0;
		double sumAccounts = 0.0;
		int numZeroAccounts = 0;
		double avgAccounts = 0.0;
		double largestAccount = 0;
		int indexLargest = 0;
		numAccounts = accounts.size();
		for (int x = 0; x <= accounts.size(); x++) {
			sumAccounts += accounts.get(x).getAccountBalance();
			if (accounts.get(x).getAccountBalance() == 0.0) {
				numZeroAccounts++;
			}
			if (accounts.get(x).getAccountBalance() > largestAccount) {
				largestAccount = accounts.get(x).getAccountBalance();
				indexLargest = x;
			}
		}
		avgAccounts = (Math.floor(((sumAccounts / numAccounts)*100.0)))/100.0;
		System.out.print("\n                            Statistics\n");
		System.out.print("===============================================================\n");
		System.out.print("\nNumber of accounts: " + numAccounts);
		System.out.print("\n\nTotal assets of all accounts: $" + sumAccounts);
		System.out.print("\n\nNumber of accounts with zero balance: "+ numZeroAccounts);
		System.out.print("\n\nAverage balance of the accounts: $" + avgAccounts);
		System.out.print("\n\nAccount with the largest balance: " + accounts.get(indexLargest).getAccountNumber() + " $" + accounts.get(indexLargest).getAccountBalance());
		System.out.print("\n\n===============================================================\n");
		
	}
}
