package com.banker;


import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.accounts.Account;
import com.accounts.AddAccount;
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
	static boolean noGo = false;

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
    			noGo = true;
 				Scene myScene = new Scene(getCustPane(), 280,175, Color.BEIGE);
    			Stage test = new Stage();
    			test.setTitle("Customer Information");
    			test.setScene(myScene);
    			test.show();
	   			if (!noGo) {
	   				test.hide();
	   			}

    	 }
     });
     
     fileChecking.setOnAction(new EventHandler<ActionEvent>() {
    	 public void handle(ActionEvent e) {
  		    Stage chkAddStage = new Stage();
		    // Create a scene by calling the method above and place it in the stage
		    Scene scene = new Scene(addAccountPane("Checking Account"), 800, 600);
		    chkAddStage.setTitle("Add Account"); // Set the stage title
		    chkAddStage.setScene(scene); // Place the scene in the stage
   		    chkAddStage.show(); // Display the stage
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
			  JOptionPane.showMessageDialog(null,  "Customer Created successfully!");
			  
		  });
		  
		  btnExit.setOnAction(e -> {
			  Stage stage = (Stage) btnExit.getScene().getWindow();
			  stage.close();
			  });
	  return custMainPane;
	  }


	  protected BorderPane addAccountPane(String acctType) {
		  
		  String heading = "25";
		  String txtMessage = "18";
		  String txtLabel = "14";
		  String acctStyleFirst = "-fx-font: ";
		  String acctStyleLast =" arial; -fx-text-fill: blue;";
		  String acctButtonStyle = "-fx-font: 18 arial; -fx-base: ";
		
		  Button btnAdd = new Button("Add");
		  btnAdd.setStyle(acctButtonStyle + "blue;");
		  Button btnExit = new Button("Exit");
		  btnExit.setStyle(acctButtonStyle + "red;");
		  
		  Label lbltxtDisplayBoxAccounts = new Label(acctType);  // heading
		  lbltxtDisplayBoxAccounts.setStyle(acctStyleFirst + heading  + acctStyleLast);
		  Label lbltxtDisplayBoxMessage = new Label(" "); // error reporting
		  
		  
		  Label lblAccountNumber = new Label("Account Number");
		  lblAccountNumber.setStyle(acctStyleFirst + txtLabel + acctStyleLast);
		  Label lblCustomerID = new Label("Customer ID");
		  lblCustomerID.setStyle(acctStyleFirst + txtLabel + acctStyleLast);
		  Label lblOpeningBalance = new Label("Opening Balance");
		  lblOpeningBalance.setStyle(acctStyleFirst + txtLabel + acctStyleLast);
		  
		  TextField txtAccountNumber = new TextField();
		  TextField txtCustomerID = new TextField();
		  TextField txtOpeningBalance = new TextField();
		  
		  VBox accountPane = new VBox(2);
		  //bottomPane.setPadding(new Insets(15,15,15,15));
		  accountPane.getChildren().addAll(lblAccountNumber, txtAccountNumber);
		  accountPane.setAlignment(Pos.CENTER);
		  
		  VBox customerPane = new VBox(2);
		  //bottomPane.setPadding(new Insets(15,15,15,15));
		  customerPane.getChildren().addAll(lblCustomerID,txtCustomerID);
		  customerPane.setAlignment(Pos.CENTER);
		 
		  
		  VBox balancePane = new VBox(2);
		  //bottomPane.setPadding(new Insets(15,15,15,15));
		  balancePane.getChildren().addAll(lblOpeningBalance,txtOpeningBalance);
		  balancePane.setAlignment(Pos.CENTER);
		 
		  
		  HBox accountDisplayPane = new HBox(1);
		  accountDisplayPane.setPadding(new Insets(20,15,150,15));
		  accountDisplayPane.getChildren().addAll(lbltxtDisplayBoxAccounts);
		  accountDisplayPane.setAlignment(Pos.CENTER);
		  
		  HBox topPane = new HBox(3);
		  topPane.setPadding(new Insets(15,15,15,15));
		  topPane.getChildren().addAll(accountPane,customerPane,balancePane);
		  topPane.setAlignment(Pos.CENTER);
		  
		  VBox finaTopPane = new VBox(2);
		  //bottomPane.setPadding(new Insets(15,15,15,15));
		  finaTopPane.getChildren().addAll(accountDisplayPane, topPane);
		  finaTopPane.setAlignment(Pos.CENTER);
		  
		  HBox centerPane = new HBox(1);
		  centerPane.setPadding(new Insets(15,15,150,15));
		  centerPane.getChildren().addAll(lbltxtDisplayBoxMessage);
		  centerPane.setAlignment(Pos.CENTER);
		  
		  
		  HBox bottomPane = new HBox(2);
		  bottomPane.setPadding(new Insets(15,15,15,15));
		  bottomPane.getChildren().addAll(btnAdd, btnExit);
		  bottomPane.setAlignment(Pos.CENTER);
	/*	 
		  HBox finalPane = new HBox(3);
		  //bottomPane.setPadding(new Insets(15,15,15,15));
		  finalPane.getChildren().addAll(accountDisplayPane,topPane);
		 finalPane.setAlignment(Pos.CENTER);
	*/
		  BorderPane mainPane = new BorderPane();
			 mainPane.setTop(finaTopPane);
		     mainPane.setCenter(centerPane);
		     mainPane.setBottom(bottomPane);
		     mainPane.setStyle("-fx-border-color: blue");
		     
		    
		     txtAccountNumber.setOnKeyTyped(e ->{
		    	 lbltxtDisplayBoxMessage.setText(" ");
		     });
		     
		     txtCustomerID.setOnKeyTyped(e -> {
		    	 lbltxtDisplayBoxMessage.setText(" ");
		     });
		     
		     txtOpeningBalance.setOnKeyTyped(e -> {
		    	 lbltxtDisplayBoxMessage.setText(" ");
		     });
		     
		     txtAccountNumber.setOnAction(e -> {
		    	 if (txtAccountNumber.isFocused()) {
		    		 lbltxtDisplayBoxMessage.setText(" ");
		    	}
		     });
		     
		     
		     btnAdd.setOnAction(e -> {
		    	 if (txtAccountNumber.getText().isEmpty()) {
		    		 lbltxtDisplayBoxMessage.setText("You must enter an Accouont Number!");
		    		 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast);
		    		 txtAccountNumber.requestFocus();
		    	 } else if (txtCustomerID.getText().isEmpty()) {
		    		 lbltxtDisplayBoxMessage.setText("You must enter a Customer ID!");
		    		 lbltxtDisplayBoxMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: red;");
		    		 txtCustomerID.requestFocus();
		    	 } else if (txtOpeningBalance.getText().isEmpty()) {
		    		 lbltxtDisplayBoxMessage.setText("You must the Opening Balance!");
		    		 lbltxtDisplayBoxMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: red;");
		    		 txtOpeningBalance.requestFocus();
		    	 } else {
		    		 lbltxtDisplayBoxMessage.setStyle("-fx-font: 18 arial; -fx-text-fill: black;");
		    		 lbltxtDisplayBoxMessage.setText("Account successfully Added!");
		    	 }
		     });
		     btnExit.setOnAction(e -> {
		    	 Stage stage = (Stage) btnExit.getScene().getWindow();
		    	 stage.close();
		    });		     
				  return mainPane;
	  }
}

class NewActualBanker {
	//NewFXBanker fxBanker = new NewFXBanker();


}