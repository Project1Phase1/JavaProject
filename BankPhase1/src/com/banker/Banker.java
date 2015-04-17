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

/* -------------------Tasks that need done ---------------------------
 * Tasks that need to be finished before submission
 * -------------------------------------------------------------------
 * 
 * 1. Correct the formating on the Statistics method <FINISHED - Theral>
 * 2. Add try / catch to all input areas. Wherever a scanner gets input from user <Ian is working on this>
 * 
 * 
 * ------------Ongoing tasks---------------
 * Finish testing the entire application from start to finish
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
	ArrayList<Account> accounts = new ArrayList<Account>();
	// eom accounts that had problems while processing
	// does not remove them from the accounts list but adds
	// them so thay can be processed later
	ArrayList<Account> eomErrors = new ArrayList<Account>(); 
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	BankUtilities bu = new BankUtilities();
	static int menuItems;
	
	/** doBanker method<br><br>
	 * 
	 * this method is the heart of the client menu<br>
	 * 
	 * 
	 */
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
					createCustomer(0);
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
					callDeposit();
					break;
				case 6:
					// 6.  Make a Withdraw
					callwithdrawal();
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
					// 12.  Process EOM Errors
					rejectTransactions();
					break;
				case 13:
					// 13.  Apply End of Month Updates
					eomCalculations(accounts);
					break;
				case 14:
					// 14.  Display Bank Statistics
					generateStatistics();
					break;
				case 15:
					// 15.  exit
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
		int inputInt = 0 ;

		// Menu Display
		String[] dispMenu = new String[15];
		dispMenu[0] =  "Create a Customer ";
		dispMenu[1] =  "Create a Checking Account ";
		dispMenu[2] =  "Create a Gold Account ";
		dispMenu[3] =  "Create a Regular Account ";
		dispMenu[4] =  "Make a Deposit ";
		dispMenu[5] =  "Make a Withdraw ";
		dispMenu[6] =  "Display Customer Information";
		dispMenu[7] =  "Display Account Information ";
		dispMenu[8] =  "Generate Transaction Report ";
		dispMenu[9] =  "Remove an Account ";
		dispMenu[10] = "Remove a Customer ";
		dispMenu[11] = "Apply End of Month Updates ";
		dispMenu[12] = "Process EOM Errors ";
		dispMenu[13] = "Display Bank Statistics ";
		dispMenu[14] = "Exit ";
		menuItems = dispMenu.length;
		System.out.println();
		System.out.println("     Welcome To Holtson, McKinney and Jessop LLC");
		System.out.println("================================================");
		for (int x = 0; x < dispMenu.length; x++) {
			System.out.println(((x < 9)? " " + (x + 1) + ".   " + dispMenu[x] : (x + 1) + ".   " + dispMenu[x]));
		}
		System.out.println("");

		// Get the input from the user
		System.out.print("Please input your choice (1 - " + (dispMenu.length) + "): ");

		@SuppressWarnings("resource")
		Scanner input = new Scanner( System.in );
		//Try-catch for Scanner input sections (remember to set inputInt = 0)
		boolean validateInput=true;
		while (validateInput) {
			try  {
				inputInt = input.nextInt();
				validateInput=false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input: Please enter Integer Value");
			}
		}
		//end try catch
		return inputInt;
	}

	
	public void displayCustomer(){
		if (customers.isEmpty()) {
			System.out.print("\nNo customers to display!\n\n");
			return;
		}

		int counter = 0;
		double totalAccounts = 0.0;
		System.out.print("\n\n=======================================================================================================================\n");
		System.out.print("                                                       Customers " + customers.size() + "\n");
		System.out.print("                                                 -------------------\n\n");
		System.out.printf("%12s %-55s %s %s %s %s", "", "                      Customer Information", "|", "# of Accounts", "|", " Total  Balance\n");
		for (Customer c: customers) {
			for (Account a: accounts) {
				if (a.getCustomer() == c) {
					counter++;
					totalAccounts += a.getAccountBalance();
				}
			}
			totalAccounts = (((totalAccounts % 1) > 0.5)? (((Math.ceil((((totalAccounts)*100))))/100)) :  (((Math.floor((((totalAccounts)*100))))/100)));
			System.out.printf("%12s %-55s %s %7d %10s %12.2f %s", "",  c.toString(), "|", counter, "|  $", totalAccounts, "\n");
			totalAccounts = 0.0;
			counter = 0;
		}
		System.out.print("\n======================================================================================================================\n\n");
	}
	
	
	public void displayAccount() {
		if (accounts.isEmpty()) {
			System.out.print("\nNo accounts to display!\n\n");
			return;
		}
		System.out.print("\n==================================================================================================================================\n");
		System.out.print("                                                                  Accounts\n");
		System.out.print("                                                    ------------------------------\n");
		//System.out.printf("%12s %-55s %12s %s %s %14s %s", "        ", "            Customer Information", "Balance", "# of Transactions", "Transaction Fee", "Total Fee","\n");  
		int chkCounter = 1, regCounter = 1, gldCounter = 1;
		for (Account a: accounts) {
			if (a instanceof Checking) {
				if (chkCounter == 1) {
					System.out.println();
					System.out.printf("%12s %-55s %12s %s %s %14s\n", "        ", "            Customer Information", "Balance", "# of Transactions", "Transaction Fee", "Total Fee");
					chkCounter++;
				}
			}
			if (a instanceof Regular) {
				if (regCounter == 1) {
					System.out.println();
					System.out.printf("%12s %-55s %12s %s %s %17s\n", "        ", "            Customer Information", "Balance", "     Inerest Rate", "  Fixed Charge", "Total Interest");
					regCounter++;
				}
			}
			if (a instanceof Gold) {
				if (gldCounter == 1) {
					System.out.println();
					System.out.printf("%12s %-55s %12s %s %s\n", "        ", "            Customer Information", "Balance", "     Interest Rate", "Interest Amount");
					gldCounter++;
				}
			}
			a.toString();
		}
	}

	public void displayTransaction() {
		if (transactions.isEmpty()) {
			System.out.print("\nNo transactions to display!\n\n");
			return;
		}
		double totalAmnt = 0.0;
		System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.print("                                                                     Transactions\n");
		System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		System.out.printf("%-20s %-35s %-15s %-15s %-45s %-20s \n",  "Transaction Number" , "Transaction Date", "Customer ID"  , "Account Number"  , "Transaction Description", "Transaction Amount");
		for (Transaction t: transactions) {
			t.toString();
			totalAmnt += t.getAmount();
		}
		System.out.printf("%153s\n", "================");
		System.out.printf("%139s $%12.2f \n","  ", totalAmnt);
		System.out.print("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
	}
	/** create customer<br><br>
	 * 
	 * get customer id and name from user then create customer<br>
	 * add it to the customer ArrayList<br>
	 * 
	 */
	public void createCustomer(int condition) {
		String customerID = "", customerName = "";
		if (condition == 0) {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("Enter customer ID ");
			//begin try-catch
			boolean validateInput=true;
			while (validateInput) {
				try  {
					customerID = input.nextLine();
					validateInput=false;
				} catch (InputMismatchException e) {
					System.out.print("Invalid Input: Please enter String Value");
				}
			}
			//end try-catch
			System.out.print("Enter customer Name ");
			//begin try-catch
			boolean inputValidate=true;
			while (inputValidate) {
				try  {
					customerName = input.nextLine();
					inputValidate=false;
				} catch (InputMismatchException e) {
					System.out.print("Invalid Input: Please enter Integer Value");
				}
			}
			//end try-catch
		} else if (condition == 1) {
			customerID = JOptionPane.showInputDialog("Enter customer ID");
			customerName = JOptionPane.showInputDialog("Enter customer Name");
		}
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
			String getAnswer = "";
			//begin try-catch
			boolean validateInput=true;
			while (validateInput) {
				try  {
					getAnswer = input.nextLine();
					validateInput=false;
				} catch (InputMismatchException e) {
					System.out.print("Invalid Input: Please enter String Value");
				}
			}
			//end try-catch
			// determine if the user wants to add customer now
			if (getAnswer.equalsIgnoreCase("y")) {
				// create user
				createCustomer(0);
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
			//begin try-catch
			boolean validateInput=true;
			while (validateInput) {
				try  {
					whichCustomer = input.nextInt();
					validateInput=false;
				} catch (InputMismatchException e) {
					System.out.print("Invalid Input: Please enter Integer Value");
				}
			}
			//end try-catch
			if (whichCustomer < 0 || whichCustomer > counter) {
				System.out.print("Invalid entry. Please try again!!!");
				whichCustomer = -1;
			}
		}
		if (whichCustomer == 0) {
			// create new customer
			createCustomer(0);
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
		String accountNumber = "";
		//begin try-catch
		boolean validateInput=true;
		while (validateInput) {
			try  {
				accountNumber = input.nextLine();
				validateInput=false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input: Please enter String Value");
			}
		}
		//end try catch
		System.out.print("Enter account Balance: ");
		double accountBalance = 0.0;
		//begin try-catch
		boolean validate=true;
		while (validate) {
			try  {
				accountBalance = input.nextDouble();
				validate=false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input: Please enter Double Value");
			}
		}
		//end try catch
		
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
		if (customer==null) {
			return;
		}		
		// get account information (Account number, account balance)
		System.out.print("Enter " + typeAccount + " account number: ");
		String accountNumber = "";
		//begin try-catch
		boolean validateInput=true;
		while (validateInput) {
			try  {
				accountNumber = input.nextLine();
				validateInput=false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input: Please enter String Value");
			}
		}
		//end try-catch
		System.out.print("Enter account Balance: ");
		double accountBalance = 0.0;
		//begin try-catch
		boolean validate=true;
		while (validate) {
			try  {
				accountBalance = input.nextDouble();
				validate=false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input: Please enter Double Value");
			}
		}
		//end try-catch
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
		if (customer==null) {
			return;
		}
		// get account information (Account number, account balance)
		System.out.print("Enter " + typeAccount + " account number: ");
		String accountNumber = "";
		//begin try-catch
		boolean validateInput=true;
		while (validateInput) {
			try  {
				accountNumber = input.nextLine();
				validateInput=false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input: Please enter String Value");
			}
		}
		//end try catch
		System.out.print("Enter account Balance: ");
		boolean isOk = false;
		double accountBalance = 0.0;
		while (!isOk) {
			try {
				accountBalance = input.nextDouble();
				isOk = true;
			} catch (InputMismatchException e) {
				JOptionPane.showMessageDialog(null, "Invalid entry detected! Please enter double amount!");
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
	public void callDeposit() {
		// declare description string
		String description = "";
		// check to see if there any accounts
		if (accounts.isEmpty()) {
			System.out.println("\n\nThere are no accounts to add a deposit to!!!!!\n\nTerminating make deposit!!!!\n\n\n\n");
			return;
		}
		// clear input not closed warning and declare scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// present account information to user and get response
		System.out.print("                           Make Deposit");
		System.out.print("\n=============================================================\n");
		int counter = 0;
		System.out.print("Available accounts\n-----------------------------------------\n");
		// list available accounts in a menu style
		for (Account a: accounts) {
			counter++;
			System.out.print(counter + ".) " + a.getAccountNumber() + "\n");
		}
		// get users choice
		boolean validateInput = true;
		int whichAccount = 0;
		//begin try-catch
			while (whichAccount < 1 || whichAccount > counter) {
				validateInput=true;
				while (validateInput) {
					try  {
						// validate users choice
						System.out.print("\nChoose which account will receive the deposit: (1 - " + counter + ") ");
						whichAccount = input.nextInt();
						validateInput=false;
					} catch (InputMismatchException e) {
						System.out.print("Invalid Input: Please enter Integer Value");
					}
				}
				//end try catch
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
		double amount = 0.0;
		//begin try-catch
		boolean validate=true;
		while (validate) {
			try  {
				amount = input.nextDouble();
				validate=false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input: Please enter Double Value");
			}
		}
		//end try catch
		// add deposit and notify user
		System.out.print(((account.makeDeposit(amount))? "Deposit successful" : "Deposit unsuccessful"));
		// create transaction in tracker
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
	public void callwithdrawal() {
		boolean debug = false;
		// declare description string
		String description = "";
		// check to see if there any accounts
		if (accounts.isEmpty()) {
			System.out.println("\n\nThere are no accounts to make a withdrawal from!!!!\n\nTerminating make withdrawal!!!!\n\n\n\n");
			return;
		}
		// clear input not closed warning and declare scanner input
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// present account information to user and get response
		System.out.print("                           Make Withdrawal");
		System.out.print("\n=============================================================\n");
		int counter = 0;
		System.out.print("Available accounts\n-----------------------------------------\n");
		// list available accounts in a menu style
		for (Account a: accounts) {
			counter++;
			System.out.print(counter + ".) " + a.getAccountNumber() + "\n");
		}
		// get users choice
		System.out.print("\nChoose which account to withdraw from: (1 - " + counter + ") ");
		int whichAccount = 0;
		//begin try-catch
		// validate users choice
		while (whichAccount < 1 || whichAccount > counter) {
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
		// lower choice to array type value (starts at 0)
		whichAccount -= 1;
		// declare account variable
		Account account;
		// determine which type of account the account number
		// belongs to and set it to account variable
		// and set the portable string
		
		if (accounts.get(whichAccount) instanceof Checking) {
			if (debug) {
				System.out.println("Checking: " + "    " + accounts.get(whichAccount).getAccountNumber() + "   " + accounts.get(whichAccount).getAccountBalance());
			}
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
		double amount = 0.0;
		//begin try-catch
		boolean validate=true;
		while (validate) {
			try  {
				amount = input.nextDouble();
				validate=false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input: Please enter Double Value");
			}
		}
		//end try catch
		// create transaction in tracker
		createTransaction(account.getCustomer().getCustomerID(), account.getAccountNumber(), description, (amount*-1));
		// add deposit and notify user
		System.out.print(((account.makeWithdrawal(amount))? "Withdrawal successful" : "Withdrawal unsuccessful"));
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
		int counter = 0;
		System.out.print("Available customers\n-----------------------------------------\n");
		// list available customers in a menu style
		for (Customer c: customers) {
			counter++;
			System.out.print(counter + ".) " + c.toString() + "\n");
		}
		// get users choice
		System.out.print("\nChoose which customer to remove: (1 - " + counter + ") ");
		int whichAccount = 0;
		// validate users choice
		while (whichAccount < 1 || whichAccount > counter) {
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

	public void removeReject(Account rejectRemove) {
		eomErrors.remove(rejectRemove);
	}
	
	
	/** end of month (eom) calculations<br><br>
	 * 
	 * this will allow for processing not only the accounts<br>
	 * but <strong>also</strong> the rejects from running the eom<br>
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
			System.out.println("\n\nThere are no accounts to process!!!\n\nTerminating EOM calculations!!!\n\n\n\n");
			return;
		}
		int counter = 0;
		int goodCounter = 0;
		int errorCounter = 0;
		int checkCounter = 0;
		int goldCounter = 0;
		int regCounter = 0;
		// calculate interest for 1 year compounded monthly7
		// I = P x (1 + r/n)^(n x t)
		// P = accountBalance : Principle
		// r = rate : interest rate (in decimal)
		// t = year : number of years, months, days, etc in this case it is years
		// n = numTimes : how often : months, quarters, days etc. in this case it is one month

		// loop through the accounts
		for (Account a: process) {
			// determine which type of account the account number
			// belongs to and set it to account variable
			// and set the description
			
			
			// is it a checking account
			if (a instanceof Checking) {
				counter++;
				checkCounter++;
				Checking chk = (Checking)a ;
				description = "EOM Checking Account";
				// validate the amount of the fees before posting
				if (((chk.getNumberOfTransactions() - 2) * chk.getCheckingTransactionFee()) == chk.getCheckingTransactionFeeAmount()) {
					if (chk.getAccountBalance() < chk.getCheckingTransactionFeeAmount()) {
						errorCounter++;
						description = "EOM Checking - Insufficent Funds to Process";
						System.out.print("\n" + description + "\n");
						createTransaction(chk.getCustomer().getCustomerID(), chk.getAccountNumber(), description, (chk.getCheckingTransactionFeeAmount() * -1));
						eomErrors.add(chk);
					} else {
						goodCounter++;
						chk.setAccountBalance(chk.getAccountBalance() - chk.getCheckingTransactionFeeAmount());
						chk.setCheckingTransactionFeeAmount(0.0);
						createTransaction(chk.getCustomer().getCustomerID(), chk.getAccountNumber(), description, (chk.getCheckingTransactionFeeAmount() * -1));
						
					}
				} else {
					// if this is reached there is a critical error
					errorCounter++;
					// put the troubled account into a reject holder until it can be properly processed
					description = "EOM Checking - Transaction Fees No Match";
					System.out.print("\n" + description + "\n");
					createTransaction(chk.getCustomer().getCustomerID(), chk.getAccountNumber(), description, (chk.getCheckingTransactionFeeAmount() * -1));
					eomErrors.add(chk);
					continue;
				}
			}
			// is it a gold account
			if (a instanceof Gold) {
				counter++;
				goldCounter++;
				Gold gold = (Gold)a;
				description = "EOM Gold Account";
				if (gold.getAccountBalance() <= 0) {
					errorCounter++;
					// put the troubled account into the reject folder
					description = "EOM Gold - Insufficient Funds to process";
					System.out.print("\n" + description + "\n");
					createTransaction(gold.getCustomer().getCustomerID(), gold.getAccountNumber(), description, 0.0);					
					eomErrors.add(gold);
					continue;
				}
				// calculate the interest
				goodCounter++;
				double rate = gold.getGoldInterestRate() / 100;
				double years = 1;
				double numTimes = 1/12.0;
				double interest = ((Math.floor(((gold.getAccountBalance() * Math.pow(1 + (rate / numTimes), (numTimes * years))) - gold.getAccountBalance())*100.0))/100.0);
				// add interest to balance
				gold.setAccountBalance(gold.getAccountBalance() + interest);
				// add transaction to transaction tracker
				createTransaction(gold.getCustomer().getCustomerID(), gold.getAccountNumber(), description, interest);
			}
			// is it a regular account 
			if (a instanceof Regular){
				counter++;
				regCounter++;
				// process regular accounts
				Regular reg = (Regular)a;
				description = "EOM Regular Account";
				
				if (reg.getAccountBalance() <= 0) {
					System.out.println("Unable to calculate interest due to a lack of funds!\n");
					// unable to process this account so add it to the  reject pile
					// until the balance has enough to process
					errorCounter++;
					description = "EOM Regular - Insufficent Funds to process";
					createTransaction(reg.getCustomer().getCustomerID(), reg.getAccountNumber(), description, 0.0);
					eomErrors.add(reg);
					continue;
				}
				goodCounter++;
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
		System.out.print("\nThere were " + counter + " accounts processed!\n");
		System.out.print("\nThere were " + errorCounter + " errors accounted for!\n");
		System.out.print("\nThere were " + goodCounter + " non-errors accounted for!\n");
		System.out.print("\nOf the " + counter + " accounts processed, " + checkCounter + " were Checking accounts!\n");
		System.out.print("\nOf the " + counter + " accounts processed, " + goldCounter + " were Gold accounts!\n");
		System.out.print("\nOf the " + counter + " accounts processed, " + regCounter + " were Regular accounts!\n");
		System.out.print("\n");
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
		int numAccounts = 0, numRegularAccounts = 0, numCheckingAccounts = 0, numGoldAccounts = 0;
		double sumAccounts = 0.0, sumRegularAccounts = 0.0, sumCheckingAccounts = 0.0, sumGoldAccounts = 0.0;
		int numZeroAccounts = 0, numRegularZeroAccounts = 0, numCheckingZeroAccounts = 0, numGoldZeroAccounts = 0;
		double avgAccounts = 0.0, avgRegularAccounts = 0.0, avgCheckingAccounts = 0.0, avgGoldAccounts = 0.0;
		double largestAccount = 0.0, largestRegularAccount = 0.0, largestCheckingAccount = 0.0, largestGoldAccount = 0.0;
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
		displayStatistics("All Accounts", numAccounts, sumAccounts, numZeroAccounts, avgAccounts, largestAccountNumber, largestAccount);
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
		displayStatistics("Gold", numGoldAccounts, sumGoldAccounts, numGoldZeroAccounts, avgGoldAccounts, largestGoldAccountNumber, largestGoldAccount);
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
		displayStatistics("Regular", numRegularAccounts, sumRegularAccounts, numRegularZeroAccounts, avgRegularAccounts, largestRegularAccountNumber, largestRegularAccount);
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
		displayStatistics("Checking", numCheckingAccounts, sumCheckingAccounts, numCheckingZeroAccounts, avgCheckingAccounts, largestCheckingAccountNumber, largestCheckingAccount);
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
	
	/** reject Transactions
	 *  process transactions that were added to the reject array list
	 *  because they were not able to be processed.
	 *  Example: insufficient funds to post a EOM transaction (Transaction fees)
	 *  
	 */
	public void rejectTransactions() {
		if (eomErrors.isEmpty()) {
			System.out.print("\nThe correction table has nothing to process. Terminating Fix Bad Accounts!!!\n\n");
			return;
		}
		// process the reject arraylist which holds the accounts that were not able to process
		// because there was an error in the EOM: insufficient funds, fees don't match
		Account account;
		for (int x = 0; x < eomErrors.size(); x++) {
			for (int y = 0; y < accounts.size(); y++) {
				if (eomErrors.get(x).equals(accounts.get(y))){
					account = eomErrors.get(x);
					processReject(account);
					return;
				}
			}
		}
	}
	
	/** process reject
	 * this is the engine that will actually do the processing
	 * 
	 * @param account the reject account that will be processed
	 */
	public void processReject(Account account) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String procInput = "", description = "";
		double procAmount = 0.0;
		boolean debug = true, isOk = true;
		for (int t = 0; t < transactions.size(); t++) {
			if (account.getAccountNumber() == transactions.get(t).getAccountNumber()) {
				if (account.getCustomer().getCustomerID() == transactions.get(t).getCustomerID()) {
					if (transactions.get(t).getDescription().contains("Insufficent") && transactions.get(t).getDescription().contains("EOM")) {
						if (debug) {
							System.out.print(transactions.get(1).getCustomerID()+ " " + transactions.get(1).getAccountNumber() + " " + transactions.get(1).getDescription() + " $" + transactions.get(1).getAmount() + "\n");
						}
						Transaction transaction = transactions.get(t);
						System.out.printf("\n %20s %20s %20s \n", "Account Number", "Customer ID", "Balance");
						System.out.printf("%20s %20s %7s $%12.2f \n", account.getAccountNumber(), account.getCustomer().getCustomerID(), "", account.getAccountBalance());
						System.out.printf("\n $%12.2f %s \n", Math.abs(transaction.getAmount()), " is the amount that needs to be taken from the account balance");
						System.out.print("process transaction? (y/n");
						procInput = input.nextLine();
						if (procInput.equalsIgnoreCase("y")) {
							System.out.print("Transaction amt " + (Math.abs(transaction.getAmount())) + " : Account amt " + account.getAccountBalance() + " :: Increase amount by ");
							while (isOk) {
								try {
									procAmount = input.nextDouble();
									isOk = false;
								} catch (InputMismatchException e) {
									System.out.println("Invalid entry! Please re-enter your amount!");
								}
							}
							account.makeDeposit(procAmount);
							description = transaction.getDescription() + "Correction deposit";
							createTransaction(account.getCustomer().getCustomerID(), account.getAccountNumber(), description, (procAmount));
							description = transaction.getDescription() + "Correction withdraw";
							account.makeWithdrawal((Math.abs(transaction.getAmount())));
							createTransaction(account.getCustomer().getCustomerID(), account.getAccountNumber(), description, (Math.abs(transaction.getAmount())));
							System.out.print("Successfully Updated!!!");
							break;
						}
						
						
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
		eomErrors.remove(account);
		System.out.print("Updated correction table");
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
