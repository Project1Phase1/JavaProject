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

import com.accounts.*;
import com.customers.*;
import com.transactions.*;
import com.utilities.*;
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
public class Banker {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActualBanker banker = new ActualBanker();
		banker.doBanker();
	}
	

}


class ActualBanker {
	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Account> reject = new ArrayList<Account>(); // eom accounts that had problems while processing
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	BankUtilities bu = new BankUtilities();
	
	public void doBanker() {
		
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
				if ((inputInt < 1) || (inputInt > 10))
				{
					System.out.println("\nThe input is out of range. Please enter 1-10!");
					System.out.println();
					inputInt = 0;
				}
			} //end while

			// switch to correspondence function
			switch (inputInt)
			{
				case 1:
					// 1.  Create a Checking Account
					createCheckingAccount();
					break;
				case 2:
					// 2.  Create a Gold Account
					createGoldAccount();
					break;
				case 3:
					// 3.  Create a Regular Account
					createRegularAccount();
					break;
				case 4:
					// 4.  Make a Deposit
					makeDeposit();
					break;
				case 5:
					// 5.  Make a Withdraw
					makewithdrawal();
					break;
				case 6:
					// 6.  Display Account Information
					for (Account a: accounts) {
						System.out.print(a.toString());
					}
					break;
				case 7:
					// 7.  Remove an Account
					removeAccount();
					break;
				case 8:
					// 8.  Apply End of Month Updates
					eomCalculations(accounts);
					break;
				case 9:
					// 9.  Display Bank Statistics
					generateStatistics();
					break;
				case 10:
					// 10.  Generate Transaction Report
					for (Transaction t: transactions) {
						System.out.print(t.toString());
					}
					break;
				case 11:
					// exit
					finished = true;
					break;
				default:
					System.out.println("Invalid Input!");
					System.out.println("");
					break;
			} // end switch
		} // end while
		
		//eomCalculations(reject);
		
	}
	
	public static int displayMenuAndGetInput()
	{
		int inputInt ;

		// Menu Display
		System.out.println("");
		System.out.println("     Welcome To Your Banking Center ");
		System.out.println("     ==============================");
		System.out.println(" 1.  Create a Checking Account ");
		System.out.println(" 2.  Create a Gold Account ");
		System.out.println(" 3.  Create a Regular Account");
		System.out.println(" 4.  Make a Deposit ");
		System.out.println(" 5.  Make a Withdraw ");
		System.out.println(" 6.  Display Account Information ");
		System.out.println(" 7.  Remove an Account ");
		System.out.println(" 8.  Apply End of Month Updates ");
		System.out.println(" 9.  Display Bank Statistics ");
		System.out.println("10.  Generate Transaction Report ");
		System.out.println("11.  Exit ");
		System.out.println("");

		// Get the input from the user
		System.out.print("Please input your choice (1 - 11): ");

		@SuppressWarnings("resource")
		Scanner input = new Scanner( System.in );

		inputInt = input.nextInt();

		return inputInt;
	}

	
	/** create customer<br><br>
	 * 
	 * get customer id and name from user then create customer<br>
	 * add it to the customer ArrayList<br>
	 * 
	 */
	public void createCustomer() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter customer ID ");
		String customerID = input.nextLine();
		System.out.print("Enter customer Name ");
		String customerName = input.nextLine();
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
				createCustomer();
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
			counter++;
			System.out.print(counter + ". " + c.toString() + "\n");
		}
		// get the account number to use from the user
		System.out.print("Choose which customer to create " + type + " account for (1 - " + counter + ") ");
		int whichCustomer = 0;
		// validate user input
		while (whichCustomer == 0) {
			whichCustomer = input.nextInt();
			if (whichCustomer < 1 || whichCustomer > counter) {
				System.out.print("Invalid entry. Please try again!!!");
				whichCustomer = 0;
			}
		}
		// check to see if there is another token and take care of it
		// select the customer the user chose
		customer = customers.get((whichCustomer - 1));
		// get account information from user (Account number, balance)
		System.out.print("Enter account number for this " + type + " account: ");
		if (input.hasNext()) {
			skip = input.nextLine();
		}
		String accountNumber = input.nextLine();
		System.out.print("Ener account Balance: ");
		double accountBalance = 0.0;
		accountBalance = input.nextDouble();
		// take care of extra tokens
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
			System.out.print("Add customer? (y/n)");
			String getAnswer = input.nextLine();
			// determine if the user wants to add customers now
			if (getAnswer.equalsIgnoreCase("y")) {
				// create customer
				createCustomer();
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
			counter++;
			System.out.print(counter + ". " + c.toString() + "\n");
		}
		// get users choice
		System.out.print("Choose which customer to create "+ type + " account for (1 - " + counter + ") ");
		int whichCustomer = 0;
		// validate user input
		while (whichCustomer == 0) {
			whichCustomer = input.nextInt();
			if (whichCustomer < 1 || whichCustomer > counter) {
				System.out.print("Invalid entry. Please try again!!!");
				whichCustomer = 0;
			}
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
			System.out.print("Add customer? (y/n)");
			String getAnswer = input.nextLine();
			// determine if the user wants to add customer now
			if (getAnswer.equalsIgnoreCase("y")) {
				// create user
				createCustomer();
			} else {
				// notify user of choice and re-display the menu
				System.out.print("\nTerminationg create " + type + " account!\n");
				return;
			}
		}
		// show available customers to choose from
		System.out.println("\nChoose the customer for this " + type + " account");
		System.out.println("===========================================================================");
		int counter = 0;
		for (Customer c: customers) {
			counter++;
			System.out.print(counter + ". " + c.toString() + "\n");
		}
		// get response from user
		System.out.print("Choose which customer to create "+ type + " account for (1 - " + counter + ") ");
		int whichCustomer = 0;
		// validate user input
		while (whichCustomer == 0) {
			whichCustomer = input.nextInt();
			if (whichCustomer < 1 || whichCustomer > counter) {
				System.out.print("Invalid entry. Please try again!!!");
				whichCustomer = 0;
			}
		}
		// capture any floating tokens
		if (input.hasNext()) {
			skip = input.nextLine();
		}
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
		System.out.print(((account.makeDeposit(amount))? "Deposit successful" : "Deposit unsuccessful"));
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
		System.out.print(((account.makeDeposit(amount))? "Withdrawal successful" : "Withdrawal unsuccessful"));
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
