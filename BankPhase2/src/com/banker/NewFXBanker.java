package com.banker;

import java.io.File;

import javax.swing.JOptionPane;

import com.accounts.Account;
import com.accounts.Checking;
import com.accounts.Gold;
import com.accounts.Regular;
import com.utilities.BankMethods;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @mentor Professor Dr. Awny Alnusair<br><br>
 * @college Indiana University Kokomo<br><br><br>
 * @course INFO-211 Informatics II<br><br>
 * 
 * 
 * @authors Ian Holtson<br>Jeremiah McKinney<br>Theral Jessop<br>
 * Apr 6, 2015<br>
 * FXBanker.java<br>
 *
 */
public class NewFXBanker extends Application {
	BankMethods banker = new BankMethods();
	
	Stage chkAddStage = new Stage();
	public static int cust = 0; // if customers have been loaded disable load customer
	public static int checkAcct = 0; // if checking accounts have been loaded disable load checking
	public static int regAcct = 0; // if regular accounts have been loaded disable load regular
	public static int goldAcct = 0; // if gold accounts have been loaded disable load gold
	public static int trans = 0; // if transactions have been loaded disable load transactions
	
   /**
    * Build menu bar with included menus for this demonstration.
    * 
    * @param menuWidthProperty Width to be bound to menu bar width.
    * @return Menu Bar with menus included.
    */
   private MenuBar buildMenuBarWithMenus(final ReadOnlyDoubleProperty menuWidthProperty) {
      MenuBar menuBar = new MenuBar();

      // Prepare left-most 'File' drop-down menu
      Menu files = new Menu("Files");
      MenuItem[] fileNames = new MenuItem[13];
      fileNames[0] = new MenuItem("Create New Data System");
      fileNames[1] = new MenuItem("Load All Data");
      fileNames[2] = new MenuItem("Save All Data");
      fileNames[3] = new MenuItem("Save Config Data");
      fileNames[4] = new MenuItem("Save Customer Data");
      fileNames[5] = new MenuItem("Save Account Data");
      fileNames[6] = new MenuItem("Save Transaction Data");
      fileNames[7] = new MenuItem("Load Config Data");
      fileNames[8] = new MenuItem("Load Customer Data");
      fileNames[9] = new MenuItem("Load Account Data");
      fileNames[10] = new MenuItem("Load Transaction Data");
      fileNames[11] = new MenuItem("Make Backup Copy");
      fileNames[12] = new MenuItem("Erase All Data");
      files.getItems().addAll(fileNames[0], fileNames[1], fileNames[2], new SeparatorMenuItem(), 
    		  fileNames[3], fileNames[4], fileNames[5], fileNames[6], new SeparatorMenuItem(), 
    		  fileNames[7], fileNames[8], fileNames[9], fileNames[10], new SeparatorMenuItem(),
    		  fileNames[11], new SeparatorMenuItem(), fileNames[12]);
      
      
    menuBar.getMenus().add(files);
      
      Menu accountMenu = new Menu("New");
      MenuItem accountCustomer = new MenuItem("Customers");
      MenuItem accountChecking = new MenuItem("Checking");
      MenuItem accountGold = new MenuItem("Gold");
      MenuItem accountRegular = new MenuItem("Regular");
      //SubMenuItem subM = new SubMenuItem("This is it");
      accountMenu.getItems().addAll(accountCustomer, new SeparatorMenuItem(), accountChecking, accountGold, accountRegular);
       menuBar.getMenus().add(accountMenu);

      // Prepare 'Examples' drop-down menu
      Menu reports = new Menu("Reports");
      MenuItem rptCustomer = new MenuItem("Customers");
      MenuItem rptAccounts = new MenuItem("Accounts");
      MenuItem rptStatistics = new MenuItem("Statistics");
      
      reports.getItems().addAll(rptCustomer, rptAccounts, rptStatistics);
      menuBar.getMenus().add(reports);

      // Prepare 'Help' drop-down menu
     Menu transactions = new Menu("Transactions");
     MenuItem tranDeposit = new MenuItem("Deposit");
     MenuItem tranWithdraw = new MenuItem("Withdrawal");
     MenuItem tranEOM = new MenuItem("End of Month");
     transactions.getItems().addAll(tranDeposit, tranWithdraw, new SeparatorMenuItem(), tranEOM);
     menuBar.getMenus().add(transactions);

     Menu remove = new Menu("Remove");
     MenuItem removeCustomer = new MenuItem("Remove Customer");
     MenuItem removeAccount = new MenuItem("Remove Account");
     remove.getItems().addAll(removeCustomer, removeAccount);
     menuBar.getMenus().add(remove);

     Menu quit = new Menu("Quit");
     quit.getItems().add(new MenuItem("Exit and Save"));
     menuBar.getMenus().add(quit);
     quit.setOnAction(e -> {
    	 banker.saveConfigData();
    	 banker.saveCustomerData();
    	 banker.saveCheckingData();
    	 banker.saveRegularData();
    	 banker.saveGoldData();
    	 // banker.saveTransactionData();
    	 System.exit(0);
     });
     
// ************************************************************ File System ************************************************************
// *************************** save config data ***************************
     fileNames[3].setOnAction(e -> {
    	banker.saveConfigData();
     });
// *************************** load config data ***************************
     fileNames[7].setOnAction(e -> {
     	 banker.loadConfigData();
     	 fileNames[7].setDisable(true);
     });
     
// *************************** save customer data ***************************
     fileNames[4].setOnAction(e -> {
    	 checkFileStatus(fileNames);
     });
     
// *************************** load customer data ***************************
     fileNames[8].setOnAction(e -> {
    	 banker.loadCustomerData();
    	 fileNames[8].setDisable(true);
      });
    
// *************************** save account data ***************************
     
     fileNames[5].setOnAction(e -> {
    	 banker.saveCheckingData();
    	 banker.saveRegularData();
    	 banker.saveGoldData();
     });
  
// *************************** load account data ***************************
     fileNames[9].setOnAction(e -> {
    	 banker.loadCheckingData();
    	 banker.loadRegularData();
    	 banker.loadGoldData();
    	 fileNames[9].setDisable(true);
    	 
     });
     
     files.setOnMenuValidation(e -> {
    	 fileNames[0].setDisable(true);
    	 fileNames[1].setDisable(true);
    	 fileNames[2].setDisable(true);
    	 fileNames[11].setDisable(true);
    	 fileNames[12].setDisable(true);
    	 fileNames[6].setDisable(true);
    	 fileNames[10].setDisable(true);
     });
     
     
// *************************** save transaction data ***************************     
     fileNames[6].setOnAction(e -> {
    	 for (int x = 0; x < banker.transactions.size(); x++) {
    		// banker.saveTransactionData(banker.transactions.get(x), ((x==0)?false:true));
    	 }
    	 JOptionPane.showMessageDialog(null, "Saved " + banker.transactions.size() + " Transactions!", "Transaction Save Data", JOptionPane.INFORMATION_MESSAGE);
    	// checkFileStatus(fileNames);
     });
     
  // *************************** load transaction data ***************************     
     fileNames[10].setOnAction(e -> {
    	 //banker.loadTransactionData();
    	 checkFileStatus(fileNames);
     });
// ************************************************************ create new customer ************************************************************
      accountCustomer.setOnAction(e -> {
    	  chkAddStage.hide();
    	  Scene myScene = new Scene(banker.getCustPane(), 375, 200, Color.BEIGE);
    	  chkAddStage.setTitle("Customer Information");
    	  chkAddStage.setScene(myScene);
    	  chkAddStage.show();
     	// checkFileStatus(fileNames);   	  
     });
     
     accountChecking.setOnAction(e -> {
    	 chkAddStage.hide();
    	 if (banker.customers.isEmpty()) {
    		 JOptionPane.showMessageDialog(null, "There are no customers available to select!\n\nUnable to add Checking Account!!");
    	 } else {
 		    Scene scene = new Scene(banker.addAccountPane(0), 800, 525);
		    chkAddStage.setTitle("Add Account"); // Set the stage title
		    chkAddStage.setScene(scene); // Place the scene in the stage
   		    chkAddStage.show(); // Display the stage
   		    //checkFileStatus(fileNames);
    	 }
      });
     
     accountRegular.setOnAction(e -> {
    	 chkAddStage.hide();
    	 if (banker.customers.isEmpty()) {
    		 JOptionPane.showMessageDialog(null, "There are no customers available to select!\n\nUnable to add Regular Account!!");
    	 } else {
  		    //Stage chkAddStage = new Stage();
		    Scene scene = new Scene(banker.addAccountPane(1), 800, 525);
		    chkAddStage.setTitle("Add Account"); // Set the stage title
		    chkAddStage.setScene(scene); // Place the scene in the stage
   		    chkAddStage.show(); // Display the stage
   		    //checkFileStatus(fileNames);
  	 }
     });
     
     accountGold.setOnAction(e -> {
    	 chkAddStage.hide();
    	 if (banker.customers.isEmpty()) {
    		 JOptionPane.showMessageDialog(null, "There are no customers available to select!\n\nUnable to add Gold Account!!");
    	 } else {
  		    //Stage chkAddStage = new Stage();
		    Scene scene = new Scene(banker.addAccountPane(2), 800, 525);
		    chkAddStage.setTitle("Add Account"); // Set the stage title
		    chkAddStage.setScene(scene); // Place the scene in the stage
   		    chkAddStage.show(); // Display the stage
   		    //checkFileStatus(fileNames);
    	 }
     });
       
     menuBar.prefWidthProperty().bind(menuWidthProperty);

      return menuBar;
   }

   
   public void checkFileStatus(MenuItem[] filesSave ) {
	   boolean isChecking = false, isRegular = false, isGold = false;
	   if (!(banker.accounts.isEmpty())) {
		   for (Account a: banker.accounts) {
	 			 if (a instanceof Checking) {
	 				 isChecking = true;
	 			 }
	 			 if (a instanceof Regular) {
	 				 isRegular = true;
	 			 }
	 			 if (a instanceof Gold) {
	 				 isGold = true;
	 			 }
	 		}
	   }
   	 // set all to disabled
	 for (int d = 0; d < filesSave.length; d++) {
		 filesSave[d].setDisable(true);           
	 }
	 File file;
     for (int x = 0; x < banker.filename.length; x++) {
     	 // System.out.print(x + ") " + banker.filename[x] + "\n");
         file = new File(banker.filename[x]);
          // only if the file exists change the settings
         if (file.exists()) {
        	 if (x == 0) { // config
	        	 filesSave[3].setDisable(false);
	        	 filesSave[9].setDisable(false);
        	 }
        	 // customer
        	 if (x == 1) {
 	         	 if (banker.customers.isEmpty()) {
	         		 filesSave[4].setDisable(true);   // save customer data
	         		 filesSave[10].setDisable(false);  // load customer data
	         	 } else {
	         		 filesSave[4].setDisable(false);  // save customer data
	         		 filesSave[10].setDisable(false); // load customer data
	          	 }
        	 }
        	 // checking
        	 if (x == 2) {
	         	 if (isChecking) {
	         		 filesSave[5].setDisable(false);  // save checking data
	         		 filesSave[11].setDisable(false); // load checking data
	         	 } else {
	         		 filesSave[5].setDisable(true);   // save checking data
	         		 filesSave[11].setDisable(false);  // load checking data
	         	 }
        	 }
        	 // regular
        	 if (x == 3) {
	         	 if (isRegular) {
	         		 filesSave[6].setDisable(false);  // save regular data
	         		 filesSave[12].setDisable(false); // load regular data
	         	 } else {
	          		filesSave[6].setDisable(true);    // save regular data
	          		filesSave[12].setDisable(false);   // load regular data
	          	 }
        	 }
        	 // gold
        	 if (x == 4) {
	          	 if (isGold) {
	         		 filesSave[7].setDisable(false);  // save gold data
	         		 filesSave[13].setDisable(false); // load gold data
	         	 } else {
	         		 filesSave[7].setDisable(true);   // save gold data
	         		 filesSave[13].setDisable(false);  // load gold data
	         	 }
        	 }
        	 // transactions
        	 if (x == 5) {
	         	 if (banker.transactions.isEmpty()) {
	          		 filesSave[8].setDisable(true);   // save transaction data
	          		 filesSave[14].setDisable(false);  // load transaction data
	         	 } else {
	         		 filesSave[8].setDisable(false);  // save transaction data
	          		 filesSave[14].setDisable(false); // load transaction data
	         	 }
          	 }
         } else {
        	 if (x == 1) {
	        	 if (banker.customers.isEmpty()) {
	         		 filesSave[4].setDisable(true);   // save customer data
	         		 filesSave[10].setDisable(true);  // load customer data
	        	 } else {
	         		 filesSave[4].setDisable(false);   // save customer data
	         		 filesSave[10].setDisable(true);   // load customer data
	        	 }
        	 }
        	 if (x == 4) {
        		 if (isGold) {
	         		 filesSave[7].setDisable(false);  // save gold data
	         		 filesSave[13].setDisable(true);  // load gold data
	        	 } else {
	         		 filesSave[7].setDisable(true);  // save gold data
	         		 filesSave[13].setDisable(true); // load gold data
	        	 }
        	 }
        	 if (x == 3) {
	        	 if (isRegular) {
	         		 filesSave[6].setDisable(false);  // save regular data
	         		 filesSave[12].setDisable(true);  // load regular data
	        	 } else {
	         		 filesSave[6].setDisable(true);  // save regular data
	         		 filesSave[12].setDisable(true); // load regular data
	        	 }
        	 }
        	 if (x == 2) {
	        	 if (isChecking) {
	         		 filesSave[5].setDisable(false);  // save checking data
	         		 filesSave[11].setDisable(true); // load checking data
	        	 } else {
	         		 filesSave[5].setDisable(true);  // save checking data
	         		 filesSave[11].setDisable(true); // load checking data
	        	 }
        	 }
        	 if (x == 5) {
	        	 if (banker.accounts.isEmpty()) {
	          		 filesSave[8].setDisable(true);   // save transaction data
	          		 filesSave[14].setDisable(true);  // load transaction data
	        	 } else {
	          		 filesSave[8].setDisable(false);   // save transaction data
	          		 filesSave[14].setDisable(true);  // load transaction data
	        	 }
        	 }
         }
       }
   }
   
   
   /**
    * Start of Bank menu System
    * 
    * @param stage Primary stage
    */
   @Override
   public void start(Stage stage) {
	  banker.loadFileName();
	  banker.loadConfigData();
	  banker.loadCustomerData();
	  banker.loadCheckingData();
	  banker.loadRegularData();
	  banker.loadGoldData();
	  // banker.loadTransactions();
	  stage.setTitle(banker.bankName);
      final Group rootGroup = new Group();
      final Scene scene = new Scene(rootGroup, 400, 25, Color.BEIGE);
      final MenuBar menuBar = buildMenuBarWithMenus(stage.widthProperty());
      rootGroup.getChildren().add(menuBar);
      stage.setScene(scene);
      stage.show();
      
      
   }

    
   /**
    * Main executable function for running menus
    * 
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(String[] arguments) {
	   
      Application.launch(arguments);
   }
   	
 }
