package com.banker;


import java.util.ArrayList;
import java.util.Scanner;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
public class NewFXBanker extends Application
{
	NewActualBanker banker = new NewActualBanker();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Account> rejects = new ArrayList<Account>();
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	BankUtilities bu = new BankUtilities();
	static String bankName = "";

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
    		 System.exit(0);
    	 }
     });
     
     fileCustomer.setOnAction(new EventHandler<ActionEvent>() {
    	 public void handle(ActionEvent e) {
    		 createCustomer();
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
	   String getBankName = "";
	   getBankName = JOptionPane.showInputDialog("What is the name of your bank?");
	   bankName = "Welcome to ".concat(getBankName);
      stage.setTitle(bankName);
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
   public static void main(String[] arguments) {
	   
      Application.launch(arguments);
   }
   
	public void createCustomer() {
		
		//while ()
			Scene myScene = new Scene(getCustPane(), 280,175, Color.BEIGE);
			Stage test = new Stage();
			test.setTitle("Customer Information");
			test.setScene(myScene);
			test.show();
//			test.get
//			test.hide();
			//test.setOnC
	}

	  protected BorderPane getCustPane(){
		  
		  
		  Label lblID = new Label("Customer ID");
		  Label lblName = new Label("Customers Name");
		  
		  TextField txtID = new TextField();
		  TextField txtName = new TextField();
		  Font times = Font.font("Times New Roman", FontWeight.BOLD, 16);

		  Button btnAdd = new Button("Add");
		  Button btnExit = new Button("Exit");
		  btnAdd.setFont(times);
		  btnExit.setFont(times);
		  
		  HBox lblPane = new HBox(15);
		  lblPane.setPadding(new Insets(15,15,15,15));  
		  lblPane.getChildren().addAll(lblID, txtID, lblName, txtName);
		  lblPane.setAlignment(Pos.TOP_CENTER);
		  lblID.setFont(times);
		  lblName.setFont(times);
		  
		  HBox txtPane = new HBox(5);
		  txtPane.setPadding(new Insets(15,15,15,15));
		  txtPane.getChildren().addAll(txtID, txtName);
		  txtPane.setAlignment(Pos.BOTTOM_CENTER);
		  
		  HBox bottomPane = new HBox(2);
		  bottomPane.setPadding(new Insets(15,15,15,15));;
		  bottomPane.getChildren().addAll(btnAdd, btnExit);
		  bottomPane.setAlignment(Pos.CENTER);
		  

		  BorderPane custMainPane = new BorderPane();
		  custMainPane.setTop(lblPane);
		  custMainPane.setCenter(txtPane);
		  custMainPane.setBottom(bottomPane);
		  custMainPane.setStyle("-fx-border-color: red");
		  
		  btnAdd.setOnAction(e -> {
			  if(txtID.getText().isEmpty()) {
				  JOptionPane.showMessageDialog(null,  "Please Enter Customer ID");
				  txtID.requestFocus();
			  } else if (txtName.getText().isEmpty()) {
				  JOptionPane.showMessageDialog(null, "Please Enter Customer Name");
				  txtName.requestFocus();
			  }
			  Customer customer = new Customer(txtID.getText(), txtName.getText());
			  customers.add(customer);
//			  createCustomer(1);
		  });
		  
		  btnExit.setOnAction(e -> {
//			  btnExit.set
			// test.hide();	
			  });

	  return custMainPane;
}
  
   
   
}

class NewActualBanker {
	//NewFXBanker fxBanker = new NewFXBanker();


}