package com.banker;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.accounts.Account;
import com.customers.Customer;
import com.transactions.Transaction;
import com.utilities.BankUtilities;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Example of creating menus in JavaFX.
 * 
 * @author Dustin
 */
public class NewFXBanker extends Application
{
	ActualBanker banker = new ActualBanker();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Account> rejects = new ArrayList<Account>();
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	BankUtilities bu = new BankUtilities();

   /**
    * Build menu bar with included menus for this demonstration.
    * 
    * @param menuWidthProperty Width to be bound to menu bar width.
    * @return Menu Bar with menus included.
    */
   private MenuBar buildMenuBarWithMenus(final ReadOnlyDoubleProperty menuWidthProperty) {
      MenuBar menuBar = new MenuBar();

      // Prepare left-most 'File' drop-down menu
      Menu fileMenu = new Menu("New");
      MenuItem fileCustomer = new MenuItem("Customers");
      MenuItem fileChecking = new MenuItem("Checking");
      MenuItem fileGold = new MenuItem("Gold");
      MenuItem fileRegular = new MenuItem("Regular");
      //SubMenuItem subM = new SubMenuItem("This is it");
      fileMenu.getItems().addAll(fileCustomer, fileChecking, fileGold, fileRegular);
       menuBar.getMenus().add(fileMenu);

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
    
//     Menu helpMenu = new Menu("Help");
//      final MenuItem searchMenuItem = new MenuItem("Search");
//      searchMenuItem.setDisable(true);
//      helpMenu.getItems().add(searchMenuItem);
//      final MenuItem onlineManualMenuItem = new MenuItem("Online Manual");
//      onlineManualMenuItem.setVisible(false);
//      helpMenu.getItems().add(onlineManualMenuItem);
//      helpMenu.getItems().add(new SeparatorMenuItem());
//      final MenuItem aboutMenuItem = new MenuItem("About");
//      aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
//    	  @Override public void handle(ActionEvent e) {
//    		  out.println("You clicked on About!");
//                               }
//                            });
 
      
      //helpMenu.getItems().add(aboutMenuItem);
      // menuBar.getMenus().add(transactions);
      
     //menuBar.getMenus().add(helpMenu);
     Menu quit = new Menu("Quit");
     quit.getItems().add(new MenuItem("Exit"));
     menuBar.getMenus().add(quit);
     quit.setOnAction(new EventHandler<ActionEvent>() {
    	 public void handle(ActionEvent e) {
    		 System.exit(1);
    	 }
     });
     
     fileCustomer.setOnAction(new EventHandler<ActionEvent>() {
    	 public void handle(ActionEvent e) {
    		 customers.add(bu.createCustomer(1));
    		 //JOptionPane.showMessageDialog(null, "Customer Created");
    	 }
     });
      // bind width of menu bar to width of associated stage
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
      stage.setTitle("Holtson, McKinney, and Jessop Banker, LLC");
      final Group rootGroup = new Group();
      final Scene scene = new Scene(rootGroup, 800, 400, Color.BEIGE);
      final MenuBar menuBar = buildMenuBarWithMenus(stage.widthProperty());
      rootGroup.getChildren().add(menuBar);
      stage.setScene(scene);
      stage.show();
      
      
   }

    
   /**
    * Main executable function for running examples.
    * 
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(String[] arguments)
   {
      Application.launch(arguments);
   }
}