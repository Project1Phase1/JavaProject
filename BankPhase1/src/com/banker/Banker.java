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
import javax.swing.*;
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
	static int menuItems;
	
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
					createCustomer();
					break;
				case 2:
					// 2.  Create a Checking Account
					createCheckingAccount();
					break;
				case 3:
					// 3.  Create a Gold Account
					createGoldAccount();
					break;
				case 4:
					// 4.  Create a Regular Account
					createRegularAccount();
					break;
				case 5:
					// 5.  Make a Deposit
					makeDeposit();
					break;
				case 6:
					// 6.  Make a Withdraw
					makewithdrawal();
					break;
				case 7:
					// 7.  Display Customer Information
					if (customers.size() == 0) {
						break;
					}
					int counter = 0;
					double totalAccounts = 0.0;
					System.out.print("\n\n===========================================================\n");
					System.out.print("                         Customers\n");
					System.out.print("                       -------------\n\n");
					for (Customer c: customers) {
						for (Account a: accounts) {
							if (a.getCustomer() == c) {
								counter++;
								totalAccounts += a.getAccountBalance();
							}
						}
						totalAccounts = (((totalAccounts % 1) > 0.5)? (((Math.ceil((((totalAccounts)*100))))/100)) :  (((Math.floor((((totalAccounts)*100))))/100)));
						System.out.printf("%s %d %s %12.2f %s", c.toString() + "\t\t# Accounts ", counter, "\t\tTotal Balance $", totalAccounts, "\n");
						totalAccounts = 0.0;
						counter = 0;
					}
					System.out.print("\n===========================================================\n\n");
					break;
				case 8:
					// 8.  Display Account Information
					for (Account a: accounts) {
						System.out.print(a.toString());
					}
					break;
				case 9:
					// 9.  Remove an Account
					removeAccount();
					break;
				case 10:
					// 10.  Remove a Customer
					removeCustomer();
					break;
				case 11:
					// 11.  Apply End of Month Updates
					eomCalculations(accounts);
					break;
				case 12:
					// 12.  Display Bank Statistics
					generateStatistics();
					break;
				case 13:
					// 13.  Generate Transaction Report
					for (Transaction t: transactions) {
						t.toString();
					}
					break;
				case 14:
					// 14.  exit
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
		String[] dispMenu = new String[14];
		dispMenu[0] = "Create a Customer ";
		dispMenu[1] = "Create a Checking Account ";
		dispMenu[2] = "Create a Gold Account ";
		dispMenu[3] = "Create a Regular Account ";
		dispMenu[4] = "Make a Deposit ";
		dispMenu[5] = "Make a Withdraw ";
		dispMenu[6] = "Display Customer Information";
		dispMenu[7] = "Display Account Information ";
		dispMenu[8] = "Remove an Account ";
		dispMenu[9] = "Remove a Customer ";
		dispMenu[10] = "Apply End of Month Updates ";
		dispMenu[11] = "Display Bank Statistics ";
		dispMenu[12] = "Generate Transaction Report "; 
		dispMenu[13] = "Exit ";
		menuItems = dispMenu.length;
		System.out.println();
		System.out.println("     Welcome To Your Banking Center ");
		System.out.println("     ==============================");
		for (int x = 0; x < dispMenu.length; x++) {
			System.out.println(((x < 9)? " " + (x + 1) + ".   " + dispMenu[x] : (x + 1) + ".   " + dispMenu[x]));
		}
		System.out.println("");

		// Get the input from the user
		System.out.print("Please input your choice (1 - " + (dispMenu.length) + "): ");

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
	
	public Customer getCustomer(String type) {
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
				createCustomer();
			} else {
				// notify user of choice and re-display the menu
				System.out.print("\nTerminationg create " + type + " account!\n");
				return null;
			}
		}
		// show available customers to choose from
		System.out.println("\nChoose the customer for this " + type + " account");
		System.out.println("===========================================================================");
		int counter = 0;
		System.out.print("0.) Create New Customer\n");
		System.out.print("    -------------------\n");
		for (Customer c: customers) {
			counter++;
			System.out.print(counter + ".) " + c.toString() + "\n");
		}
		// get response from user
		System.out.print("Choose which customer to create "+ type + " account for (0 - " + counter + ") ");
		int whichCustomer = -1;
		// validate user input
		while (whichCustomer == -1) {
			whichCustomer = input.nextInt();
			if (whichCustomer < 0 || whichCustomer > counter) {
				System.out.print("Invalid entry. Please try again!!!");
				whichCustomer = -1;
			}
		}
		if (whichCustomer == 0) {
			// create new customer
			createCustomer();
			customer = customers.get(customers.size()-1);
			whichCustomer = customers.size();
		} else {
			// select the users choice of customer
			customer = customers.get((whichCustomer - 1));
		}
		return customer;

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
	
	public void createCustomerTransaction(String description) {
		transactions.add(new Transaction(new java.util.Date(), description, bu.generateUniqueTransNumber()));
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
		// declare the type of account string
		String typeAccount = "Checking";
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		@SuppressWarnings("unused")
		String skip = "";
		Customer customer;
		customer = getCustomer(typeAccount);
		if (customer==null) {
			return;
		}
		// get account information from user (Account number, balance)
		System.out.print("Enter " + typeAccount + " account number: ");
		String accountNumber = input.nextLine();
		System.out.print("Ener account Balance: ");
		double accountBalance = 0.0;
		accountBalance = input.nextDouble();
		// take care of extra tokens
			// add the account
		accounts.add(new Checking(accountNumber, accountBalance, customer));
		// add transaction to transaction tracker
		createTransaction(customer.getCustomerID(), accountNumber, "Opening "+ typeAccount + " Account", accountBalance);
		// notify user of account creation
		System.out.println("\n" + typeAccount + " account successfully created!\n");
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
		String typeAccount = "Gold";
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		@SuppressWarnings("unused")
		String skip = "";
		Customer customer;
		customer = getCustomer(typeAccount);
		// get account information (Account number, account balance)
		System.out.print("Enter " + typeAccount + " account number: ");
		String accountNumber = input.nextLine();
		System.out.print("Ener account Balance: ");
		double accountBalance = 0.0;
		accountBalance = input.nextDouble();
		// add account
		accounts.add(new Gold(accountNumber, accountBalance, customer));
		// create a record of the transaction
		createTransaction(customer.getCustomerID(), accountNumber, "Opening " + typeAccount + " Account", accountBalance);
		// notify user of success
		System.out.println("\n" + typeAccount + " account successfully created!\n");
	
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
		String typeAccount = "Regular";
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		@SuppressWarnings("unused")
		String skip = "";
		Customer customer;
		customer = getCustomer(typeAccount);
		// get account information (Account number, account balance)
		System.out.print("Enter " + typeAccount + " account number: ");
		String accountNumber = input.nextLine();
		System.out.print("Ener account Balance: ");
		boolean isOk = false;
		double accountBalance = 0.0;
		while (!isOk) {
			try {
				accountBalance = input.nextDouble();
				isOk = true;
			} catch (InputMismatchException e) {
				JOptionPane.showMessageDialog(null, "Invalid entry detected! Please try again!");
			}
		}
		// create account
		accounts.add(new Regular(accountNumber, accountBalance, customer));
		// add transaction to transaction tracker
		createTransaction(customer.getCustomerID(), accountNumber, "Opening Regular Account", accountBalance);
		// notify user of success
		System.out.println("\n" + typeAccount + " account successfully created!\n");
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
	
	public void removeCustomer() {
		// check to see if there any customers
		if (customers.isEmpty()) {
			System.out.println("There are no customers to remove!\nTerminating remove customer!\n");
			return;
		}
		// clear input not closed warning and declare scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// present customer information to user and get response
		System.out.print("                         Remove Customer");
		System.out.print("\n=============================================================\n");
		int counter = 1;
		System.out.print("Available customers\n-----------------------------------------\n");
		// list available customers in a menu style
		for (Customer c: customers) {
			System.out.print(counter + ".) " + c.toString() + "\n");
			counter++;
		}
		// get users choice
		System.out.print("\nChoose which customer to remove: (1 - " + counter + ")");
		int whichAccount = 0;
		// validate users choice
		while (whichAccount < 1 || whichAccount > counter) {
			whichAccount = input.nextInt();
		}
		// lower choice to array type value (starts at 0)
		whichAccount -= 1;
		Customer customer;
		customer = customers.get(whichAccount);
		// set description to customer ID and customer name along with "Removed"
		String description = customer.getCustomerID() + " " + customer.getCustomerName() + " Removed";
		// remove customer
		customers.remove(customer);
		// add customer removal to transactions
		createCustomerTransaction(description);
		// Notify user that customer was removed
		System.out.print("\nSuccessfully removed customer!\n");

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
		int numAccounts = 0, numRegularAccounts = 0, numCheckingAccounts = 0, numGoldAccounts = 0;
		double sumAccounts = 0.0, sumRegularAccounts = 0.0, sumCheckingAccounts = 0.0, sumGoldAccounts = 0.0;
		int numZeroAccounts = 0, numRegularZeroAccounts = 0, numCheckingZeroAccounts = 0, numGoldZeroAccounts = 0;
		double avgAccounts = 0.0, avgRegularAccounts = 0.0, avgCheckingAccounts = 0.0, avgGoldAccounts = 0.0;
		double largestAccount = 0, largestRegularAccount = 0, largestCheckingAccount = 0, largestGoldAccount = 0;
		int indexLargest = 0, indexLargestRegular = 0, indexLargestChecking = 0, indexLargestGold = 0;
		numAccounts = accounts.size();
		for (int x = 0; x < accounts.size(); x++) {
//------------------------------------Total---------------------------			
			sumAccounts += accounts.get(x).getAccountBalance();
			if (accounts.get(x).getAccountBalance() == 0.0) {
				numZeroAccounts++;
			}
			if (accounts.get(x).getAccountBalance() > largestAccount) {
				largestAccount = accounts.get(x).getAccountBalance();
				indexLargest = x;
			}
//-----------------------------------Gold-----------------------------
			if(accounts.get(x) instanceof Gold) {
				if (accounts.get(x).getAccountBalance() == 0.0) {
					numGoldZeroAccounts++;
				}
				if (accounts.get(x).getAccountBalance() > largestGoldAccount) {
					largestGoldAccount = accounts.get(x).getAccountBalance();
					indexLargestGold = x;
				}
			}
//----------------------------------Regular-----------------------------
			if (accounts.get(x) instanceof Regular) {
				if (accounts.get(x).getAccountBalance() == 0.0) {
					numRegularZeroAccounts++;
				}
				if (accounts.get(x).getAccountBalance() > largestRegularAccount) {
					largestRegularAccount = accounts.get(x).getAccountBalance();
					indexLargestRegular = x;
				}
			}
//--------------------------------Checking-----------------------------
			if (accounts.get(x) instanceof Checking) {
				if (accounts.get(x).getAccountBalance() == 0.0) {
					numCheckingZeroAccounts++;
				}
				if (accounts.get(x).getAccountBalance() > largestCheckingAccount) {
					largestCheckingAccount = accounts.get(x).getAccountBalance();
					indexLargestChecking = x;
				}
			}
			
		}
		avgAccounts = (Math.floor(((sumAccounts / numAccounts) * 100.0))) / 100.0;
		avgGoldAccounts = (Math.floor(((sumGoldAccounts / numGoldAccounts) * 100.0))) / 100.0;
		avgRegularAccounts = (Math.floor(((sumRegularAccounts / numRegularAccounts) * 100.0))) / 100.0;
		avgCheckingAccounts = (Math.floor(((sumCheckingAccounts / numCheckingAccounts) * 100.0))) / 100.0;
		System.out.print("\n                            Statistics\n");
		System.out.print("===============================================================\n");
		System.out.print("                    -----------------------\n");
		System.out.print("                     Total All Accounts\n");
		System.out.print("                    -----------------------\n");
		System.out.print("\nNumber of accounts: " + numAccounts);
		System.out.print("\n\nTotal assets of all accounts: $" + sumAccounts);
		System.out.print("\n\nNumber of accounts with zero balance: "+ numZeroAccounts);
		System.out.print("\n\nAverage balance of the accounts: $" + avgAccounts);
		System.out.print("\n\nAccount with the largest balance: " + accounts.get(indexLargest).getAccountNumber() + " $" + accounts.get(indexLargest).getAccountBalance());
		System.out.print("\n\n");
		System.out.print("                   -----------------------\n");
		System.out.print("                    Total Gold Accounts\n");
		System.out.print("                   -----------------------\n");
		System.out.print("\nNumber of accounts: " + numGoldAccounts);
		System.out.print("\n\nTotal assets of all accounts: $" + sumGoldAccounts);
		System.out.print("\n\nNumber of accounts with zero balance: "+ numGoldZeroAccounts);
		System.out.print("\n\nAverage balance of the accounts: $" + avgGoldAccounts);
		System.out.print("\n\nAccount with the largest balance: " + accounts.get(indexLargestGold).getAccountNumber() + " $" + accounts.get(indexLargestGold).getAccountBalance());
		System.out.print("\n\n");
		System.out.print("                  ---------------------------\n");
		System.out.print("                    Total Regular Accounts\n");
		System.out.print("                  ---------------------------\n");
		System.out.print("\nNumber of accounts: " + numRegularAccounts);
		System.out.print("\n\nTotal assets of all accounts: $" + sumRegularAccounts);
		System.out.print("\n\nNumber of accounts with zero balance: "+ numRegularZeroAccounts);
		System.out.print("\n\nAverage balance of the accounts: $" + avgRegularAccounts);
		System.out.print("\n\nAccount with the largest balance: " + accounts.get(indexLargestRegular).getAccountNumber() + " $" + accounts.get(indexLargestRegular).getAccountBalance());
		System.out.print("\n\n");
		System.out.print("                  -----------------------------\n");
		System.out.print("                    Total Checking Accounts\n");
		System.out.print("                  -----------------------------\n");
		System.out.print("\nNumber of accounts: " + numCheckingAccounts);
		System.out.print("\n\nTotal assets of all accounts: $" + sumCheckingAccounts);
		System.out.print("\n\nNumber of accounts with zero balance: "+ numCheckingZeroAccounts);
		System.out.print("\n\nAverage balance of the accounts: $" + avgCheckingAccounts);
		System.out.print("\n\nAccount with the largest balance: " + accounts.get(indexLargestChecking).getAccountNumber() + " $" + accounts.get(indexLargestChecking).getAccountBalance());
		
		
		System.out.print("\n\n===============================================================\n");
		
	}
}
