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

//
//https://www.youtube.com/watch?v=5GsdaZWDcdY&feature=youtu.be
package com.banker;

import java.io.*;
import java.util.*;
import com.accounts.*;
import com.customers.*;
import com.transactions.*;
import com.utilities.*;

/** public class
 * 
 * 
 * @author Ian Holtson<br>Jeremiah McKinney<br>Theral Jessop<br>
 * Apr 20, 2015<br>
 * Banker.java<br>
 *
 */
public class Banker {
	
	/** main method
	 * @param args
	 */
	public static void main(String[] args) {
		ActualBanker banker = new ActualBanker();
		banker.doBanker();
	}
}

/** actual banker<br><br>
 * 
 * this is the class that will actually do the work for the client<br>
 * instantiate the accounts, customers, transactions, utilities<br><br>
 * 
 * and establish the number of menu items using menuItems field<br><br>
 * 
 * display the menu and get the users response<br>
 * gets user response for the 'display menu and get input' method<br>
 * 
 */
class ActualBanker {
	// holds the account information
	ArrayList<Account> accounts = new ArrayList<Account>();
	// eom accounts that have problems while processing
	// does not remove them from the accounts list but adds
	// them so they can be processed later
	ArrayList<Account> eomErrors = new ArrayList<Account>(); 
	// holds the customer information
	ArrayList<Customer> customers = new ArrayList<Customer>();
	// holds the transactions that only affect the change in balance
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	// utility class for holding miscellaneous methods
	BankUtilities bu = new BankUtilities();
	public String[] filename = new String[9];
	public String[] backname = new String[9];
	public String[] paths = new String[2];
	public int bakup;
	// this holds the number of menu items and allows for customization to occur
	static int menuItems;
	// change bankName to whatever the user prefers
	static String bankName = "";//"   Welcome To Holtson, McKinney and Jessop LLC";
	
	/** doBanker method<br><br>
	 * 
	 * this method is the heart of the client menu<br>
	 * 
	 * 
	 */
	public void doBanker() {
		// load filename and paths
		// and if the path doesn't exist, create it
		loadFileName();
		
		// load data
		loadConfigData();
		loadCustomerObjectData();
		loadAccountObjectData();
		loadTransactionObjectData();
		
		// menu
		
		boolean finished = false;

		while (finished == false)
		{
			// Menu Display and Get user input
			int inputInt = 0;
			while (inputInt == 0)
			{
				inputInt = displayMenuAndGetInput();

				// if the input is out of range
				if ((inputInt < 1) || (inputInt > menuItems))
				{
					System.out.println("\nThe input is out of range. Please enter 1 - " + menuItems + "!");
					System.out.println();
					inputInt = 0;
				}
			} //end while

			// switch to correspondence function
			switch (inputInt)
			{
				case 1:
					// 1.  Create a Customer
					System.out.print(((createCustomer(0))? "\nCustomer Successfully Created!!\n" : "\nCustomer was NOT created!!\n"));
					break;
				case 2:
					// 2.  Create a Checking Account (0)
					createAccounts(0);
					break;
				case 3:
					// 3.  Create a Gold Account (2)
					createAccounts(2);
					break;
				case 4:
					// 4.  Create a Regular Account (1)
					createAccounts(1);
					break;
				case 5:
					// 5.  Make a Deposit (0)
					callDepositWithdrawal(0);
					break;
				case 6:
					// 6.  Make a Withdraw (1)
					callDepositWithdrawal(1);
					break;
				case 7:
					// 7.  Display Customer Information
					// loop back to menu if no customers
					displayCustomer();
					break;
				case 8:
					// 8.  Display Account Information
					displayAccount();
					break;
				case 9:
					// 9.  Generate Transaction Report
					displayTransaction();
					break;
				case 10:
					// 10.  Remove an Account
					removeAccount();
					break;
				case 11:
					// 11.  Remove a Customer
					removeCustomer();
					break;
				case 12:
					// 12.  Apply End of Month Updates
					eomCalculations();
					break;
				case 13:
					// 13.  Process EOM Errors
					eomErrorTransactions();
					break;
				case 14:
					// 14.  Display Bank Statistics
					generateStatistics();
					break;
				case 15:
					// 15.  exit
					saveConfigData();
					saveCustomerObjectData();
					saveAccountObjectData();
					saveTransactionObjectData();
					
					System.out.print("\n*********************************************************\n"
							         + "      Thank you for using " + bankName + "\n"
							         + "*********************************************************\n");
					finished = true;
					break;
				default:
					System.out.println("Invalid Input!");
					System.out.println("");
					break;
			} // end switch
		} // end while
	}
	
	/** display menu and get input<br>
	 * 
	 * @return the menu number the user enters
	 */
	public static int displayMenuAndGetInput()
	{
		int inputInt = 0 ;
		@SuppressWarnings("resource")
		Scanner input = new Scanner( System.in );
//		if (bankName.isEmpty()) {
//			String getBankName = "";
//			System.out.print("\n\nWhat is the name of your bank? ");
//			getBankName = input.nextLine();
//			bankName = getBankName;
//		}
		// define the menu options
		String[] dispMenu = new String[15];
		dispMenu[0] =  "Create a Customer ";
		dispMenu[1] =  "Create a Checking Account ";
		dispMenu[2] =  "Create a Gold Account ";
		dispMenu[3] =  "Create a Regular Account ";
		dispMenu[4] =  "Make a Deposit ";
		dispMenu[5] =  "Make a Withdraw ";
		dispMenu[6] =  "Display Customer Information";
		dispMenu[7] =  "Display Account Information ";
		dispMenu[8] =  "Display Transaction Report ";
		dispMenu[9] =  "Remove an Account ";
		dispMenu[10] = "Remove a Customer ";
		dispMenu[11] = "Apply End of Month Updates ";
		dispMenu[12] = "Process EOM Errors ";
		dispMenu[13] = "Display Bank Statistics ";
		dispMenu[14] = "Exit ";
		// set the number of menu options
		menuItems = dispMenu.length;
		System.out.println();
		System.out.println("   Welcome To " + bankName);
		System.out.println("=================================================");
		// generate the menu
		for (int x = 0; x < dispMenu.length; x++) {
			System.out.println(((x < 9)? " " + (x + 1) + ".   " + dispMenu[x] : (x + 1) + ".   " + dispMenu[x]));
		}
		System.out.println("");

		// Get the input from the user
		System.out.print("Please input your choice (1 - " + (dispMenu.length) + "): ");

		//Try-catch for Scanner input sections (remember to set inputInt = 0)
		boolean validateInput = true;
		while (validateInput) {
			try  {
				inputInt = input.nextInt();
				validateInput = false;
			} catch (InputMismatchException e) {
				System.out.print("\nInvalid Input: Please enter Integer Value\n");
				// catch any stray tokens
				input.nextLine();
			}
		}
		//end try catch
		return inputInt;
	}

	/** display customer<br>br>
	 * 
	 * loop through the customer with the accounts in a nested loop<br>
	 * to find all the accounts that a customer has and their overall balance<br>
	 * and present it to the user in a formated manner<br>
	 * 
	 */
	public void displayCustomer(){
		if (customers.isEmpty()) {
			System.out.print("\nNo customers to display!\n\n");
			return;
		}

		int counter = 0;
		double totalAccounts = 0.0;
		// set up the header
		System.out.print("\n\n=======================================================================================================================\n");
		System.out.print("                                                       Customers " + customers.size() + "\n");
		System.out.print("                                                 -------------------\n\n");
		System.out.printf("%12s %-55s %s %s %s %s", "", "                      Customer Information", "|", "# of Accounts", "|", " Total  Balance\n");
		// get customer information
		for (Customer c: customers) {
			for (Account a: accounts) {
				if (a.getCustomer() == c) {
					counter++;
					totalAccounts += a.getAccountBalance();
				}
			}
			// display customer information and reset totals for next customer
			System.out.printf("%12s %-55s %s %7d %10s $%12.2f \n", "",  c.toString(), "|", counter, "|  ", totalAccounts);
			totalAccounts = 0.0;
			counter = 0;
		}
		System.out.print("\n=======================================================================================================================\n\n");
	}
	
	/** display account information<br><br>
	 * 
	 * loop through accounts and generate a list<br>
	 * grouped by type (Checking, Regular, Gold)<br>
	 * in a easily readable format<br><br>
	 * 
	 * also display the number of accounts next to the main heading<br>
	 * 
	 * 
	 */
	public void displayAccount() {
		// check to see if there is anything to display
		if (accounts.isEmpty()) {
			System.out.print("\nNo accounts to display!\n\n");
			return;
		}
		System.out.print("\n====================================================================================================================================================\n");
		System.out.print("                                                                        Accounts "+ accounts.size() + "\n");
		System.out.print("                                                             ------------------------------\n");
		int chkCounter = 1, regCounter = 1, gldCounter = 1;
		for (Account a: accounts) {
			if (a instanceof Checking) {
				// display the header only once
				if (chkCounter == 1) {
					System.out.println();
					System.out.printf("%12s %-55s %-15s %12s %s %s %15s\n", " ", "Customer Information","Account Number", "Balance", "# of Transactions", "Transaction Fee", "Total Fee");
					chkCounter++;
				}
				// display the account information for Checking
				System.out.print(a.toString());
			}
		}
		for (Account a: accounts) {
			if (a instanceof Regular) {
				// display the header only once
				if (regCounter == 1) {
					System.out.println();
					System.out.printf("%12s %-55s %-15s %12s %s %s %17s\n", " ", "Customer Information", "Account Number", "Balance", "     Inerest Rate", "  Fixed Charge", "Total Interest");
					regCounter++;
				}
				// display the account information for Regular
				System.out.print(a.toString());
			}
		}
		for (Account a: accounts) {
			if (a instanceof Gold) {
				// display the header only once
				if (gldCounter == 1) {
					System.out.println();
					System.out.printf("%12s %-55s %-15s %12s %s %s\n", " ", "Customer Information", "Account Number", "Balance", "     Interest Rate", "Interest Amount");
					gldCounter++;
				}
				// display the account information for Gold
				System.out.print(a.toString());
			}
		}
		System.out.print("\n====================================================================================================================================================\n");
	}

	/** display transactions<br><br>
	 * 
	 * list the transactions in a easily readable format<br>
	 * all deposits will be positive<br>
	 * all withdrawals will be negative<br><br>
	 * 
	 * all transactions that increase to the balance will be positive<br>
	 * and all transaction that decrease the balance will be negative<br>
	 * 
	 */
	public void displayTransaction() {
		// check to see if there is anything to display
		if (transactions.isEmpty()) {
			System.out.print("\nNo transactions to display!\n\n");
			return;
		}
		double totalAmnt = 0.0;
		// generate the header
		System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.print("                                                                     Transactions " + transactions.size() + "\n");
		System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		System.out.printf("%-20s %-35s %-15s %-20s %-45s %-20s \n",  "Transaction Number" , "Transaction Date", "Customer ID"  , "Account Number"  , "Transaction Description", "Transaction Amount");
		// display the transactions
		for (Transaction t: transactions) {
			System.out.print(t.toString());
			totalAmnt += t.getAmount();
		}
		// report the total
		System.out.printf("%158s\n", "================");
		System.out.printf("%144s $%12.2f \n","  ", totalAmnt);
		System.out.print("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
	}
	
	/** create customer<br><br>
	 * 
	 * get customer id and name from user then create customer<br>
	 * add it to the customer ArrayList (I call it a table)<br><br>
	 * 
	 * @param condition allows for using JOptionPane as the input for<br>
	 * customer id and name
	 * 
	 * 
	 */
	public boolean createCustomer(int condition) {
		// declare usable variables
		String customerID = "", customerName = "";
		// set the debug vale
		boolean debug = false;
		int chkSum = -1;
		if (customers.isEmpty()) {
			chkSum = 0;
		} else {
			chkSum = customers.size();
		}
		if (debug) {
			System.out.println(chkSum);
		}
		// if using the text based menu
		if (condition == 0) {
			// hide the warning input not closed
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("\nEnter customer ID\nOr enter 0 to generate one ");
			//begin try-catch
			boolean validateInput=true, isFound = false;
			while (validateInput) {
				isFound = false;
				try  {
					customerID = input.nextLine();
					if (customerID.equals("0")) {
						customerID = bu.generateUniqueAcctNumber();
						System.out.print("\n" + customerID + "\n");
					}
					for (Customer c: customers) {
						if (c.getCustomerID().equals(customerID)) {
							System.out.print("\nCustomer ID already Exists!\nPlease enter new Customer ID: ");
							isFound = true;
							break;
						}
					}
					if (!isFound) {
						validateInput = false;
					}
				} catch (InputMismatchException e) {
					System.out.print("\nInvalid Input: Please enter String Value");
				}
			}
			//end try-catch
			System.out.print("\nEnter customer Name ");
			//begin try-catch
			boolean inputValidate=true;
			while (inputValidate) {
				try  {
					customerName = input.nextLine();
					inputValidate=false;
				} catch (InputMismatchException e) {
					System.out.print("\nInvalid Input: Please enter Integer Value");
				}
			}
			//end try-catch
		}
		Customer customer = new Customer(customerID, customerName);
		customers.add(customer);
		// determine whether customer was added or not
		if (chkSum < customers.size()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/** get customer<br><br>
	 * 
	 * determine if there are any customers and if there are not<br>
	 * ask the user if they want to create one now or not<br>
	 * if not, it will return to the menu<br><br>
	 * 
	 * the typeAcct is for displaying to the user what type<br>
	 * account being created contextually<br><br>
	 * 
	 * display the customers in a menu style and allow the<br>
	 * user to add a customer if the customer is not listed<br>
	 * 
	 * @param typeAcct the type of account being created (Checking, Regular, Gold)
	 * @return the appropriate customer selected
	 */
	public Customer getCustomer(String typeAcct) {
		// clear warning of input not closed and declare scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// clear skip unused warning and set up token capture variable
		// declare customer variable
		Customer customer;
		// are the customers empty
		while (customers.isEmpty()) {
			// notify user and get response
			System.out.print("\nNo customers available to complete task!\n");
			System.out.print("\nAdd customer? (y/n) ");
			String getAnswer = "";
			//begin try-catch
			boolean validateInput=true;
			while (validateInput) {
				try  {
					getAnswer = input.nextLine();
					validateInput=false;
				} catch (InputMismatchException e) {
					System.out.print("\nInvalid Input: Please enter String Value\n");
				}
			}
			//end try-catch
			// determine if the user wants to add customer now
			if (getAnswer.equalsIgnoreCase("y")) {
				// create user
				System.out.print(((createCustomer(0))? "\nCustomer Successfully Created!!\n" : "\nCustomer was not created!!\n"));
			} else {
				// notify user of choice and re-display the menu
				System.out.print("\nTerminationg create " + typeAcct + " account!\n");
				return null;
			}
		}
		// show available customers to choose from
		System.out.println("\nChoose the customer for this " + typeAcct + " account");
		System.out.println("===========================================================================");
		int counter = 0;
		// start the menu
		System.out.print("0.) Create New Customer\n");
		System.out.print("    -------------------\n");
		// list the available customers starting with 1 as the first one
		for (Customer c: customers) {
			counter++;
			System.out.print(counter + ".) " + c.toString() + "\n");
		}
		// get response from user
		System.out.print("\nChoose which customer to create "+ typeAcct + " account for (0 - " + counter + ") ");
		// set the element to a negative value
		int whichCustomer = -1;
		// validate user input
		while (whichCustomer == -1) {
			//begin try-catch
			boolean validateInput=true;
			while (validateInput) {
				try  {
					whichCustomer = input.nextInt();
					validateInput=false;
				} catch (InputMismatchException e) {
					System.out.print("\nInvalid Input: Please enter Integer Value\n");
					input.nextLine();
				}
			}
			//end try-catch
			// is the user entry within the appropriate range
			if (whichCustomer < 0 || whichCustomer > counter) {
				System.out.print("\nInvalid entry. Please try again!!!\n");
				// reset element value
				whichCustomer = -1;
			}
		}
		if (whichCustomer == 0) {
			// call the create new customer method
			System.out.print(((createCustomer(0))? "\nCustomer Successfully Created!!\n\n" : "\nCustomer was not created!!\n\n"));
			// set customer variable to last entered customer
			customer = customers.get(customers.size()-1);
			whichCustomer = customers.size();
		} else {
			// select the users choice of customer
			customer = customers.get((whichCustomer - 1));
		}
		return customer;
	}
	
	/** display account<br><br>
	 * 
	 * used to display a list of available account numbers<br>
	 * so the user can enter the proper one<br>
	 * 
	 * @param message an appropriate message to display to the user<br>
	 * as the header
	 */
	public void displayAccount(String message) {
		// present account information to user and get response
		System.out.print(message);
		System.out.print("\n=============================================================\n");
		System.out.print("Available accounts\n-----------------------------------------\n");
		// list available accounts in a menu style
		for (Account a: accounts) {
			System.out.print("     " + a.getAccountNumber() + "\n");
		}
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
	
	/** create customer transaction<br>
	 * this add to the transaction table the removal of a customer<br>
	 * the description will include the cutomer id and name<br>
	 * 
	 * 
	 * @param description the description of the transaction
	 */
	public void createCustomerTransaction(String customerID, String description) {
		transactions.add(new Transaction(new java.util.Date(), customerID, description, bu.generateUniqueTransNumber()));
	}
	
	/** create account<br><br>
	 * 
	 * 0 is Checking<br>
	 * 1 is Regular<br>
	 * 2 is Gold<br><br>
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
	 * @param threeAcct is the account type: 0 is Checking, 1 is Regular, 2 is Gold
	 */
	public void createAccounts(int threeAcct) {
		// declare the type of account string
		String typeAccount = "", acctPre = "";
		// which type of account is being created
		switch (threeAcct) {
			case 0:
				typeAccount = "Checking";
				acctPre = "CA";
				break;
			case 1:
				typeAccount = "Regular";
				acctPre = "RA";
				break;
			case 2:
				typeAccount = "Gold";
				acctPre = "GA";
				break;
		}
		// declare variables
		Customer customer;
		customer = getCustomer(typeAccount);
		// if the customer was not found return
		if (customer==null) {
			return;
		}
		// get account information from user (Account number, balance)
		String accountNumber = "", message = "", errMessage = "";
		message = "\nEnter 0 to generate " + typeAccount + " account number\nOr enter the " + typeAccount + "account number: ";
		errMessage = "\nInvalid Input: Please enter Account Number\n";
		boolean isOk = true, validateInput = true;
		while (validateInput) {
			isOk = true;
			accountNumber = getAccountNumber(message, errMessage);
			if (accountNumber.equals("0")) {
				accountNumber = acctPre + bu.generateUniqueAccountNumber();
				System.out.print("\n" + accountNumber + "\n");
			}
			for (Account a: accounts) {
				if (a.getAccountNumber().equals(accountNumber)) {
					message = "\nAccount already exists!\nPlease enter " + typeAccount + "account number: ";
					isOk = false;
					break;
				}
			}
			if (isOk) {
				validateInput = false;
			}
		}
		double accountBalance = 0.0;
		message = "\nEnter Account Balance: ";
		errMessage = "\n**************************************************\n"
			+          " Invalid Amount! Can ONLY enter positive values!!\n"
			+          "**************************************************\n"
			+          "\nPlease re-enter Account balance: ";
		accountBalance = getDoubleAmount(message, errMessage);
		// add the appropriate account
		
		switch (threeAcct) {
			case 0:
				accounts.add(new Checking(accountNumber, accountBalance, customer));
				break;
			case 1:
				accounts.add(new Regular(accountNumber, accountBalance, customer));
				break;
			case 2:
				accounts.add(new Gold(accountNumber, accountBalance, customer));
				break;
		}
		// add transaction to transaction tracker
		createTransaction(customer.getCustomerID(), accountNumber, "Opening "+ typeAccount + " Account", accountBalance);
		// notify user of account creation
		System.out.println("\n" + typeAccount + " account successfully created!\n");
	}
	
	/** create make deposit or withdrawal<br><br>
	 * 
	 * 0 is Deposit<br>
	 * 1 is Withdrawal<br><br>
	 * 
	 * check to see if there are any customers<br>
	 * if there are no customers it will terminate<br><br>
	 * 
	 * ask for the account number and allow the user to <br>
	 * select 0 to get a list<br><br>
	 * 
	 * ask the user for the amount<br>
	 * user will be notified of success or failure<br><br>
	 * 
	 * determine whether it is the checking, gold or regular account<br>
	 * and add it the description: example: "Checking Deposit"
	 * add a transaction to the transaction tracker<br><br>
	 * 
	 * @param dw 0 is Deposit, 1 is Withdrawal
	 */	
	public void callDepositWithdrawal(int dw) {
		// declare description string
		String description = "", message = "", errMessage = "", tranType = "";
		switch (dw) {
			case 0:
				tranType = "Deposit";
				break;
			case 1:
				tranType = "Withdrawal";
				break;
		}
		if (accounts.isEmpty()) {
			System.out.println("\n\nThere are no accounts to add a " + tranType + " to!!!!!\n\nTerminating make " + tranType + "!!\n\n\n\n");
			return;
		}
		// check to see if there any accounts
		
		// get users choice
		String getAccount = "";
		message = "\nEnter the Account Number or 0 to display accounts: ";
		errMessage = "\n************************************\n"
			+ "          Invalid entry!"
			+ "\n************************************\n"
			+ "Please re-enter the Account Number: ";
		boolean wantDisplay = true;
		while (wantDisplay) {
			getAccount = getAccountNumber(message, errMessage);
			if (getAccount.equals("0")) {
				displayAccount("                           Make " + tranType);
			} else {
				wantDisplay = false;
			}
		}
		// declare account variable
		Account account = accounts.get(0);
		// determine which type of account the account number
		// belongs to and set it to account variable
		// and set the portable string
		boolean isFound = false, validateInput=true;
		while (validateInput) {
			for (Account a: accounts) {
				if (a.getAccountNumber().equals(getAccount)) {
					account = a;
					isFound = true;
				}
			}
			if (!isFound) {
				//System.out.print(errMessage);
				getAccount = getAccountNumber(errMessage, "");
			} else {
				validateInput=false;
			}
		}
		// determine which type of account the account number
		// belongs to and set it to account variable
		// and set the portable string
		if (account instanceof Checking) {
			description = "Checking " + tranType;
		} else if (account instanceof Gold) {
			description = "Gold " + tranType;
		} else {
			description = "Regular " + tranType;
		}
		// get deposit amount
		message = "\n\nEnter the amount of the " + tranType + " ";
		errMessage = "\n**************************************************\n"
			+          " Invalid Amount! Can ONLY enter positive values!!\n"
			+          "**************************************************\n"
			+          "\nPlease re-enter amount of the " + tranType + ": ";
		double amount = 0.0;
		amount = getDoubleAmount(message, errMessage);
		switch (dw) {
			case 0:
				// deposit
				// add deposit and notify user
				System.out.print(((account.makeDeposit(amount))? "\n       ******\nDeposit Successful!\n       ******\n" : "\n       ******\nDeposit Unsuccessful!\n       ******\n"));
				break;
			case 1:
				double oldAmount = amount;
				amount = account.makeWithdrawal(amount);
				if ((oldAmount != amount) && amount != -1.0) {
					description = description + " - Available Balance";
				}
				// if the returning value is -1 then the amount sent to withdraw
				// was a negative number
				// if the amount in the account is below the amount requested
				// then it will return the amount that is in the account
				// it will not allow the account to go below 0.00
				if (amount == -1.0) {
					System.out.print("\n\n******************************\nWithdrawal was UNSUCCESSFUL!!\n******************************\n\n");
					return;
				} else {
					System.out.print("\n           ******\nWithdrawal Successful!\n           ******\n");
				}
		}
		// add transaction in tracker
		createTransaction(account.getCustomer().getCustomerID(), account.getAccountNumber(), description, amount);
	}

	/** get double amount<br><br>
	 * 
	 * get the amount from the user<br>
	 * 
	 * @param message the message to display to the user
	 * @param errMessage the error message to display to the user
	 * @return the amount
	 */
	public double getDoubleAmount(String message, String errMessage) {
		//begin try-catch
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		double amount = 0.0;
		boolean validate=true;
		System.out.print(message);
		while (validate) {
			try  {
				amount = input.nextDouble(); 
				if (amount >= 0.00) {
					validate=false;
				}
			} catch (InputMismatchException e) {
				System.out.print("\n                   *******************\n"
						+ "Fatal Error: Invalid input! Was expecting a decimal value!\n"
						+ "                   *******************\n");
				if (input.hasNext()) {
					input.next();
				}
			}
			// if user entered a negative number display error message
			if (validate) {
				System.out.print(errMessage);
			}
		}
		//end try catch
		return amount;
	}
	
	/** get account number<br><br>
	 * 
	 * 
	 * 
	 * @param message to display to user
	 * @param errMessage to display to user
	 * @return account number
	 */
	public String getAccountNumber(String message, String errMessage) {
		// try catch
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String getAccount = "";
		// display message to user
		System.out.print(message);
		boolean validateInput = true;
		while (validateInput) {
			try  {
				getAccount = input.nextLine();
				validateInput=false;
			} catch (NoSuchElementException | IllegalStateException e) {
				System.out.print("\n           *************\n"
						+ "Fatal Error: Element or State!"
						+ "\n           *************\n");
			}
			// display error message
			if (validateInput) {
				System.out.print(errMessage);
			}
		}
		//end try catch
		return getAccount;
	}
	
	/** remove account<br><br>
	 * 
	 * check to see if there are any accounts to remove<br>
	 * and if there are then display the account number in a menu<br>
	 * style allowing the user to choose the one to remove<br><br>
	 * 
	 * create a transaction in the tracker with a description and the current balance<br><br>
	 * 
	 * notify user of the success or failure
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
		int counter = 0;
		System.out.print("Available accounts\n-----------------------------------------\n");
		// list available accounts in a menu style
		for (Account a: accounts) {
			counter++;
			System.out.print(counter + ".) " + a.getAccountNumber() + "\n");
		}
		// get users choice
		System.out.print("\nChoose which account to remove: (1 - " + counter + ") ");
		int whichAccount = 0;
		//begin try-catch
		boolean validateInput=true;
		while (validateInput) {
			try  {
				whichAccount = input.nextInt();
				validateInput=false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input: Please enter Integer Value");
			}
		}
		//end try catch
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
	
	/** remove customer<br><br>
	 * 
	 * check to see if there are any customers to remove<br>
	 * if there are it will display the customers in a menu<br>
	 * style and allow the user to select the one to remove<br><br>
	 * 
	 * Add a transaction into the tracker listing the customer id and name<br><br>
	 * 
	 * then notify the user of the success<br>
	 * 
	 */
	public void removeCustomer() {
		// check to see if there any customers
		if (customers.isEmpty()) {
			System.out.println("There are no customers to remove!\nTerminating remove customer!\n");
			return;
		}
		boolean isActive = false;
		for (int y = 0; y < customers.size(); y++) {
			for (int x = 0; x < accounts.size(); x++) {
				if (customers.get(y).equals(accounts.get(x).getCustomer())) {
					isActive = true;
				}
			}
			if (isActive) {
				customers.get(y).setActive(true);
			} else {
				customers.get(y).setActive(false);
			}
			isActive = false;
		}
		// clear input not closed warning and declare scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// present customer information to user and get response
		System.out.print("                         Remove Customer");
		System.out.print("\n=============================================================\n");
		int counter = 0;
		System.out.print("Available customers\n-----------------------------------------\n");
		System.out.print("0.) To Exit!\n-------------------------\n");
		// list available customers in a menu style
		ArrayList<Integer> element = new ArrayList<Integer>();
		for (int x = 0; x < customers.size(); x++) {
			if(!(customers.get(x).getActive())) {
				counter++;
				element.add(x);
				System.out.print(counter + ".) " + customers.get(x).toString() + "\n");
			}
		}
		if (counter == 0) {
			System.out.print("\nThere are no customers available to remove!\n\nTerminating Customer Removal!\n");
			return;
		}
		// get users choice
		int whichAccount = -1;
		// validate users choice
		while (whichAccount < 0 || whichAccount > counter) {
			System.out.print("\nChoose which customer to remove: (0 - " + counter + ") ");
			//begin try-catch
			boolean validateInput=true;
			while (validateInput) {
				try  {
					whichAccount = input.nextInt();
					validateInput=false;
				} catch (InputMismatchException e) {
					System.out.print("Invalid Input: Please enter Integer Value");
				}
			}
			
			//end try catch
		}
		if (whichAccount == 0) {
			System.out.print("\nTerminating Remove Customer!\n");
			return;
		}
		// lower choice to array type value (starts at 0)
		whichAccount -= 1;
		Customer customer;
		customer = customers.get(element.get(whichAccount));
		// set description to customer ID and customer name along with "Removed"
		String description = customer.getCustomerID() + " " + customer.getCustomerName() + " Removed";
		// remove customer
		customers.remove(customer);
		// add customer removal to transactions
		createCustomerTransaction(customer.getCustomerID(), description);
		// Notify user that customer was removed
		System.out.print("\n" + description + "!\n");

	}
	
	/** end of month calculations<br><br>
	 * 
	 * process accounts and calculate interest for accounts that<br>
	 * have the ability to generate interest only if the balance is positive<br><br>
	 * 
	 * apply transaction fees to the balance of Checking accounts only<br>
	 * if there is enough funds to do so<br>
	 * Will not allow the balance to go into a negative state<br>
	 * if there are not enough funds, the account will be added to a<br>
	 * error table and processed later allow the user to<br>
	 * add funds<br><br>
	 * 
	 * also keep track of the number of account and how many errors<br>
	 * and display a formated report at the end of the run<br>
	 * 
	 * 
	 * 
	 * 
	 */
	public void eomCalculations() {
		
		String description = "";
		if (accounts.isEmpty()) {
			System.out.print("\n\nThere are no accounts to process!\n\nTerminating EOM Calculations!\n\n");
			return;
		}
		// declare counting variables
		int counter = 0;
		int goodCounter = 0;
		int badCounter = 0;
		int errorCounter = 0;
		int checkCounter = 0;
		int goldCounter = 0;
		int regCounter = 0;
		int checkErrorCounter = 0;
		int goldErrorCounter = 0;
		int regErrorCounter = 0;
		// set up account types variables
		Checking chk;
		Gold gold;
		Regular reg;
		// loop through accounts and prcess each one
		for (Account a: accounts) {
			counter++;
			// checking accounts
			if (a instanceof Checking) {
				checkCounter++;
				chk = (Checking) a;
				// determine if transactions are zero and increment bad counter and loop back
				if (chk.getNumberOfTransactions() == 0) {
					badCounter++;
					continue;
				}
				// check to see if the amount of the fees don't match the number of transaction times the fees (remember the first two are fee)
				if (((chk.getNumberOfTransactions() - 2) * chk.getCheckingTransactionFee()) != chk.getCheckingTransactionFeeAmount()) {
					// there is an error
					errorCounter++;
					checkErrorCounter++;
					description = "EOM Checking Fees Don't Match";
					// display message to user
					displayDescription(description);
					// add transaction to tracker
					createTransaction(chk.getCustomer().getCustomerID(), chk.getAccountNumber(), description, 0.0);
					// add account to error table
					eomErrors.add(chk);
				} else {
				 // calculated fees and fee amount are equal
					// now check to see if there are enough available funds to process
					if (chk.getAccountBalance() < chk.getCheckingTransactionFeeAmount()) {
						// there are not enough funds to process
						errorCounter++;
						checkErrorCounter++;
						description = "EOM Checking - Insufficient Funds";
						// add account to error table
						eomErrors.add(chk);
						// display message to user
						displayDescription(description);
						// add transaction to tracker
						createTransaction(chk.getCustomer().getCustomerID(), chk.getAccountNumber(), description, 0.0);
					} else {
						// there are enough funds to process
						goodCounter++;
						description = "EOM Checking - Transaction Fees";
						// update account balance
						chk.setAccountBalance(chk.getAccountBalance() - chk.getCheckingTransactionFeeAmount());
						// display message to user
						displayDescription(description);
						// add transaction to tracker
						createTransaction(chk.getCustomer().getCustomerID(), chk.getAccountNumber(), description, (chk.getCheckingTransactionFeeAmount() * -1));
						// reset transaction number and fee amount
						chk.setNumberOfTransactions(0);
						chk.setCheckingTransactionFeeAmount(0.0);
					}
				}
			} else if (a instanceof Gold) {
				goldCounter++;
				gold = (Gold) a;
				if(gold.getAccountBalance() <= 0.0) {
					// the account has no funds to calculate interest
					badCounter++;
					continue;
				} else {
					// account is able to add interest calculations
					goodCounter++;
					description = "EOM Gold - Interest Calculation";
					// display message to user
					displayDescription(description);
					// calculate interest
					double interest = calculateInterest(gold, (gold.getGoldInterestRate() / 100.0));
					// apply interest to account
					gold.setAccountBalance(gold.getAccountBalance() + interest);
					// add transaction to tracker
					createTransaction(gold.getCustomer().getCustomerID(), gold.getAccountNumber(), description, interest);
					// add interest to interest field
					gold.setGoldInterestAmount(gold.getGoldInterestAmount() + interest);
				}
			} else {
				regCounter++;
				reg = (Regular) a;
				if (reg.getAccountBalance() <= 0.0) {
					// the account has no funds to calculate interest
					badCounter++;
					continue;
				} else {
					// account is able to add interest calculations
					if (reg.getAccountBalance() >= reg.getRegularFixedCharge()) {
						goodCounter++;
						description = "EOM Regular - Interest Calculation";
						// calculate interest
						double interest = calculateInterest(reg, (reg.getRegularInterestRate() / 100.0));
						// display message to user
						displayDescription(description);
						// apply interest to account
						reg.setAccountBalance(reg.getAccountBalance() + (interest - reg.getRegularFixedCharge()));
						// add transaction to tracker
						createTransaction(reg.getCustomer().getCustomerID(), reg.getAccountNumber(), description, interest);
						// add interest to interest field
						reg.setRegularInterestAmount(reg.getRegularInterestAmount() + interest);
					} else {
						// there is not enough funds available to calculate interest and apply the fixed Charge
						regErrorCounter++;
						errorCounter++;
						description = "EOM Regular - Insufficient funds to apply fixed cost";
						// display message to user
						displayDescription(description);
					}
				}
			}
		}
		// display report
		// all accounts
		System.out.print("\nThere were " + counter + " accounts processed!\n");
		System.out.print("\nThere were " + errorCounter + " errors accounted for!\n");
		System.out.print("\nThere were " + goodCounter + " accounts processed successfully!\n");
		// show those with zero balance
		System.out.print("\nThere were " + badCounter + " accounts processed unsuccessfully! (zero balance or no transactions)\n\n");
		// out of all the accounts
		System.out.print("\nOf the " + counter + " accounts processed, " + checkCounter + " were Checking accounts!\n");
		System.out.print("\nOf the " + counter + " accounts processed, " + goldCounter + " were Gold accounts!\n");
		System.out.print("\nOf the " + counter + " accounts processed, " + regCounter + " were Regular accounts!\n\n");
		// of the accounts how many of those were errors
		System.out.print("\nOf the " + checkCounter + " Checking accounts processed, there were " + checkErrorCounter + " errors\n");
		System.out.print("\nOf the " + goldCounter + " Gold accounts processed, there were " + goldErrorCounter + " errors\n");
		System.out.print("\nOf the " + regCounter + " Regular accounts processed, there were " + regErrorCounter + " errors\n\n");
		
		System.out.print("\n");
		
	}

	
	/** display description<br><br>
	 * 	
	 * @param description the message to display to the user
	 */
	public void displayDescription(String description) {
		System.out.print("\n\n"
		+ "*******************************************\n"
		+ description + "\n"
		+ "*******************************************\n\n");
	}
	
	/** calculate interest<br><br>
	 * 
	 * @param account the account to calculate the interest for
	 * @param rate the rate in decimal form
	 * 
	 * @return the amount of the interest
	 */
	public double calculateInterest(Account account, double rate) {
		// calculate interest for 1 year compounded monthly7
		// I = P x (1 + r/n)^(n x t)
		// P = accountBalance : Principle
		// r = rate : interest rate (in decimal)
		// t = year : number of years, months, days, etc in this case it is years
		// n = numTimes : how often : months, quarters, days etc. in this case it is one month
		double years = 1.0;
		double numTimes = 1.0/12.0;
		double interest = ((account.getAccountBalance() * (Math.pow((1.0 + (rate / numTimes)), (numTimes * years)))) - account.getAccountBalance());
		return interest;
	}
	/** generate statistics<br><br>
	 * 
	 * loop through the accounts and get:<br>
	 * largest amount<br>
	 * account with largest balance<br>
	 * amount of all the accounts, number of zero balance accounts,<br>
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
		if (accounts.isEmpty()) {
			System.out.println("\n\nThere are no accounts available to run statistics for!!!\n\n\n");
			return;
		}
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
		String largestAccountNumber = "not available", largestRegularAccountNumber = "not available", largestCheckingAccountNumber = "not available", largestGoldAccountNumber = "not available";
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
			avgAccounts = (sumAccounts / numAccounts);
		}
		if (numGoldAccounts == 0) {
			avgGoldAccounts = 0.00;
		} else {
			avgGoldAccounts = (sumGoldAccounts / numGoldAccounts);
		}
		if (numRegularAccounts == 0) {
			avgRegularAccounts = 0.00;
		} else {
			avgRegularAccounts = (sumRegularAccounts / numRegularAccounts);
		}
		if (numCheckingAccounts == 0) {
			avgCheckingAccounts = 0.00;
		} else {
			avgCheckingAccounts = (sumCheckingAccounts / numCheckingAccounts);
		}
//------------------------------------display results-----------------------------------
		// (char) 68 < Statistics > 68 (char)
		System.out.print("==================================================================================================================================================\n");
		System.out.print("\n                                                                  Statistics\n");
		// 16 < Statistics > 16
		System.out.print("                                                  ------------------------------------------");
		System.out.printf("\n %-15s %18s %20s %20s %20s %23s %20s \n", "Type Account","Number of Accounts", "Total Balance", "w/Zero Balance", "Average Balance", "Account Number of", "Largest Balance");
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
		displayStatistics("Gold", numGoldAccounts, sumGoldAccounts, numGoldZeroAccounts, avgGoldAccounts, largestGoldAccountNumber, largestGoldAccount);
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
		displayStatistics("Regular", numRegularAccounts, sumRegularAccounts, numRegularZeroAccounts, avgRegularAccounts, largestRegularAccountNumber, largestRegularAccount);
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
		displayStatistics("Checking", numCheckingAccounts, sumCheckingAccounts, numCheckingZeroAccounts, avgCheckingAccounts, largestCheckingAccountNumber, largestCheckingAccount);
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
		displayStatistics("All Accounts", numAccounts, sumAccounts, numZeroAccounts, avgAccounts, largestAccountNumber, largestAccount);
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
		System.out.print("\n\n==================================================================================================================================================\n");
		
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
	public void displayStatistics(String acctType, int numAccts, double sumAccts, int numZero, double avgAccts, String acctNumber, double largeBal) {
		System.out.printf("%-15s %7s %-4d %13s $%12.2f %11s %-4d %10s $%12.2f %3s %19s %6s $%12.2f \n", acctType, "", numAccts, "", sumAccts, "", numZero, "", avgAccts, "", acctNumber, "", largeBal);
	}
	
	/** EOM Error Transactions<br><br>
	 *  process transactions that were added to the eomError array list<br>
	 *  because they were not able to be processed.<br>
	 *  Example: insufficient funds to post a EOM transaction (Transaction fees)<br>
	 *  
	 */
	public void eomErrorTransactions() {
		if (eomErrors.isEmpty()) {
			System.out.print("\nThe correction table has nothing to process. Terminating Fix Bad Accounts!!!\n\n");
			return;
		}
		// process the reject arraylist which holds the accounts that were not able to process
		// because there was an error in the EOM: insufficient funds, fees don't match
		Account account;
		// keep track of the elements of the eomErrors that are processed
		ArrayList<Integer> element = new ArrayList<Integer>();
		boolean isOk = false;
		// loop through the eomErrors and accounts and process the eomErrors one by one
		for (int x = 0; x < eomErrors.size(); x++) {
			for (int y = 0; y < accounts.size(); y++) {
				if (eomErrors.get(x).equals(accounts.get(y))) {
					account = accounts.get(y);
					isOk = processEOMError(account);
					if (isOk) {
						element.add(x);
					}
					break;
				}
			}
		}
		// now remove all the eomErrors that were processed
		if (!element.isEmpty()) {
			for (Integer i: element) {
				eomErrors.remove(i);
			}
		}
	}
	
	/** process EOM errors<br><br>
	 * 
	 * @param account the account that had the error
	 * @return true if it was processed<br><br>and false if it was not
	 * 
	 */
	public boolean processEOMError(Account account) {
		String procInput = "", message = "", errMessage = "";
		double procAmount = 0.0;
		boolean procYN = true;
		// make sure the account is a checking account
		if (account instanceof Checking) {
			Checking chk = (Checking) account;
			System.out.printf("\n%s $%12.2f %s $%12.2f \n", "Avalable Balance: ", chk.getAccountBalance(), " Amount of Fees ", chk.getCheckingTransactionFeeAmount());
			System.out.printf("\n %s $%12.2f %s \n", "Account needs: ", (chk.getCheckingTransactionFeeAmount() - chk.getAccountBalance()), " to process.");
			message = "\nProcess now? (y/n) ";
			errMessage = "\n************************************\n"
					+ "          Invalid entry!"
					+ "\n************************************\n"
					+ "Please re-enter (y/n): ";
			// loop as long as the user did not enter y or n
			while (procYN) {
				procInput = getAccountNumber(message, errMessage);
				if (procInput.equalsIgnoreCase("y")) {
					procYN = false;
				} else if (procInput.equalsIgnoreCase("n")) {
					procYN = false;
				} else {
					System.out.print(errMessage);
				}
			}
			// start the processing as long as the user entered y
			if (procInput.equalsIgnoreCase("y")) {
				errMessage = "\n************************************\n"
					+ "          Invalid entry!"
					+ "\n************************************\n"
					+ "Please re-enter amount: ";
				boolean addUntil = true;
				// loop as long as there needs to be more funds added to account
				while (addUntil) {
					
					message = "Enter the amount to add to the account:\nMinimum is " + (chk.getCheckingTransactionFeeAmount() - chk.getAccountBalance()) + " ";
					procAmount = getDoubleAmount(message, errMessage);
					// with amount entered will there be enough to process the fees
					if (procAmount >= (chk.getCheckingTransactionFeeAmount() - chk.getAccountBalance())) {
						chk.setAccountBalance(chk.getAccountBalance()+procAmount);
						createTransaction(chk.getCustomer().getCustomerID(), chk.getAccountNumber(), "New EOM Checking - Correction", procAmount);
						// after adding the amount to the account will there be enough to process the fees
						if ((chk.getAccountBalance() - chk.getCheckingTransactionFeeAmount()) >= 0.0){
							chk.setAccountBalance(chk.getAccountBalance() - chk.getCheckingTransactionFeeAmount());
							createTransaction(chk.getCustomer().getCustomerID(), chk.getAccountNumber(), "New EOM Checking - Process Fees", (chk.getCheckingTransactionFeeAmount() * -1));
							chk.setNumberOfTransactions(0);
							chk.setCheckingTransactionFeeAmount(0.0);
							addUntil = false;
							return true;
						}
					}
				}
			} else {
				// user entered n
				return false;
			}
		}
		// account was not a checking account
		return false;
	}
	public void loadFileName() {
		loadPaths();
		loadBackup();
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
			System.out.print("Security setting for creating folders invalid! Unable to create Paths!");
		}
	}
	
	// ***************************************************************************** object file system *****************************************************************************
	  
		  // ***************************************************************** load *****************************************************************
		  
	  // *************************** config ***************************
	  
	  public void loadConfigData() {
		  File file = new File(filename[0]);
		  if (!(file.exists())) {
			  bankName = getAccountNumber("Please Enter the Bank Name: ", "");
			  bakup = 0;
			  return;
		  }
		  try (DataInputStream input = new DataInputStream(new FileInputStream(filename[0]));){
			  while (true) {
				  bankName = input.readUTF();
				  bakup = input.readInt();
			  }
		  } catch (EOFException e) {
			  e.getStackTrace();
			  return;
		  } catch (FileNotFoundException e) {
			  e.getStackTrace();
			  return;
		  } catch (IOException e) {
			  System.out.print("Configuration File Read Error");
			  e.getStackTrace();
		  }
	  }

	  
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
					  System.out.print("\nFile " + filename[7] + " not found!\n");
					  e.getStackTrace();
				  } catch (IOException e) {
					  System.out.print("\nCustomer Read Error\n");
					  e.getStackTrace();
				  } catch (ClassNotFoundException e) {
					  System.out.print("\nUnable to create object\n");
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
					  System.out.print("\nFile " + filename[6] + " not found!\n");
					  e.getStackTrace();
				  } catch (IOException e) {
					  System.out.print("\nAccount Read Error\n");
					  e.getStackTrace();
				  } catch (ClassNotFoundException e) {
					  System.out.print("\nUnable to create object\n");
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
					  System.out.print("\nFile " + filename[8] + " not found!\n");
					  e.getStackTrace();
				  } catch (IOException e) {
					  System.out.print("\nTransaction Read Error\n");
					  e.getStackTrace();
				  } catch (ClassNotFoundException e) {
					  System.out.print("\nUnable to create object\n");
					  e.getStackTrace();
				  }
			  } else {
				  return;
			  }
		  }
		  
		  // ***************************************************************** save *****************************************************************

		  // *************************** config ***************************

		  public void saveConfigData() {
			  try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename[0], false));) {
				  output.writeUTF(bankName);
				  output.writeInt(bakup);
				  
			  } catch (IOException e) {
				  System.out.print("\nConfig File Write Error\n");
				  e.getStackTrace();
			  }
		  }
		  
		  
			// ************************************** customer **************************************

		  public void saveCustomerObjectData() {
			  try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename[7], false));) {
				  output.writeObject(customers);
				  
			  } catch (FileNotFoundException e) {
				  System.out.print("\nFile " + filename[7] + " not found!\n");
				  e.getStackTrace();
				  
			  } catch (IOException e) {
				  System.out.print("\nCustomer Write Error\n");
				  e.getStackTrace();

			  }
		  }
		  
			// ************************************** accounts **************************************

		  public void saveAccountObjectData() {
			  try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename[6], false));) {
				  output.writeObject(accounts);
			  } catch (FileNotFoundException e) {
				  System.out.print("\nFile " + filename[6] + " not found!\n");
				  e.getStackTrace();
			  } catch (IOException e) {
				  System.out.print("\nAccount Read Error\n");
				  e.getStackTrace();
			  }
		  }
		  
			// ************************************** transactions **************************************

		  public void saveTransactionObjectData() {
			  try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename[8], false));) {
				  output.writeObject(transactions);
			  } catch (FileNotFoundException e) {
				  System.out.print("\nFile " + filename[7] + " not found!\n");
				  e.getStackTrace();
			  } catch (IOException e) {
				  System.out.print("\nTransaction Read Error\n");
				  e.getStackTrace();
			  }
		  }

	
} // end of ActualBanker class


/*
 * This information is to try and stay as consistent as possible
 * in the testing and the effectiveness of the entries.
 * 
 * I don't really think there will be very much deviation from the ones listed
 * but if you know of other ways to enter and still be effective in the testing
 * then please feel free to add them here.
 * 
 * 
 * 
 * 										CUSTOMER INFORMATION
 * CUSTOMERS:
 * 	LK4344 Luke Kyle
 * 	MV5613 Mary Valinski
 * 	OX1121 Oienta Xau
 * 	VC1314 Victoria Chow
 * 	PT8621 Paul Thomas
 * 
 * 
 * ACCOUNTS:
 * Gold---
 * 	GA43245643		4600.00		Oienta Xau
 * 	GA15436453		1200.00		Mary Valinski
 * 	GA44369945		   0.00		Victoria Chow
 *  GA65898813		  -5.00		Luke Kyle
 * 
 * Regular---
 * 	RA11436453		300.00		Luke Kyle
 *  RA43146345		275.43		Mary Valinski
 *  RA34331436		527.68		Oienta Xau
 *  RA94964394		  0.00		Victoria Chow
 *  RA22487883		-23.36		Paul Thomas
 *  
 * Checking---
 *  CA55451345		 25.00		Oienta Xau
 *  CA93464345		145.16		Luke Kyle
 *  CA99474135		987.89		Mary Valinski
 *  CA16439459		  0.00		Victoria Chow
 *  CA54983214	   -895.32		Paul Thomas


this will produce a checking fee of 48.00
and when the withdrawals are applied
the balance will be below the available limit
making it possible to test the EOM and
the process the correction table
Deposits
	CA55451345		200.00		Oienta Xau
	CA55451345		125.00		Oienta Xau
	CA55451345		100.00		Oienta Xau
	CA55451345		200.00		Oienta Xau
	CA55451345		175.00		Oienta Xau
	CA55451345		350.18		Oienta Xau
	CA55451345		 23.00		Oienta Xau
	CA55451345		600.00		Oienta Xau
	CA55451345		125.00		Oienta Xau
	CA55451345		 16.49		Oienta Xau
	CA55451345		 42.37		Oienta Xau
	
Withdrawals
	CA55451345		200.00		Oienta Xau
	CA55451345		325.00		Oienta Xau
	CA55451345		 12.32		Oienta Xau
	CA55451345		900.17		Oienta Xau
	CA55451345		395.00		Oienta Xau
	CA55451345		100.00		Oienta Xau
	CA55451345		 25.00		Oienta Xau
	CA55451345		200.00		Oienta Xau

Below will allow for calculating interest that
will also allow for testing of the EOM and
interest calculations
Deposits
	GA44369945		350.12		Victoria Chow
	GA44369945		251.96		Victoria Chow
	GA44369945		 26.49		Victoria Chow
	GA44369945		999.21		Victoria Chow
	RA11436453	   1248.92		Luke Kyle
	RA11436453		245.63		Luke Kyle
	RA11436453		983.47		Luke Kyle

Withdrawals
	GA44369945		125.31		Victoria Chow
	RA11436453		361.67		Luke Kyle
	
 */
