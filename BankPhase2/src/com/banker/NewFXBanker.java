package com.banker;

import javax.swing.JOptionPane;

import com.utilities.BankMethods;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	static String bankName = "";
	Stage chkAddStage = new Stage();
	
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
      MenuItem filesNew = new MenuItem("Ceate New Data System");
      MenuItem filesLoad = new MenuItem("Load All Data");
      MenuItem filesSave = new MenuItem("Save All Data");
      MenuItem filesBackup = new MenuItem("Make Backup Copy");
      MenuItem eraseFiles = new MenuItem("Erase All Data");
      files.getItems().addAll(filesNew, filesLoad, filesSave, new SeparatorMenuItem(), filesBackup, new SeparatorMenuItem(), eraseFiles);
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
     quit.getItems().add(new MenuItem("Exit"));
     menuBar.getMenus().add(quit);
     quit.setOnAction(new EventHandler<ActionEvent>() {
    	 public void handle(ActionEvent e) {
    		 System.exit(0);
    	 }
     });
     
     // create new customer
      accountCustomer.setOnAction(e -> {
    	  chkAddStage.hide();
    	  Scene myScene = new Scene(banker.getCustPane(), 375, 200, Color.BEIGE);
    	  chkAddStage.setTitle("Customer Information");
    	  chkAddStage.setScene(myScene);
    	  chkAddStage.show();
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
    	 }
     });
     menuBar.prefWidthProperty().bind(menuWidthProperty);

      return menuBar;
   }

   /**
    * Start of JavaFX application demonstrating menu support.
    * 
    * @param stage Primary stage.
    */
   @Override
   public void start(Stage stage)
   {
	   String getBankName = "Testing 1, 2, 3!!";
	   //getBankName = JOptionPane.showInputDialog("What is the name of your bank?");
	   bankName = "Welcome to ".concat(getBankName);
      stage.setTitle(bankName);
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
