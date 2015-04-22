package com.banker;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import com.accounts.Account;
import com.accounts.Checking;
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
public class NewFXBanker extends Application {
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
      Menu files = new Menu("Files");
      MenuItem filesNew = new MenuItem("Ceate New File System");
      MenuItem filesLoad = new MenuItem("Load All Files");
      MenuItem filesSave = new MenuItem("Save All Files");
      MenuItem filesBackup = new MenuItem("Make Backup Copy");
      files.getItems().addAll(filesNew, filesLoad, filesSave, filesBackup);
      menuBar.getMenus().add(files);
      
      Menu accountMenu = new Menu("New");
      MenuItem accountCustomer = new MenuItem("Customers");
      MenuItem accountChecking = new MenuItem("Checking");
      MenuItem accountGold = new MenuItem("Gold");
      MenuItem accountRegular = new MenuItem("Regular");
      //SubMenuItem subM = new SubMenuItem("This is it");
      accountMenu.getItems().addAll(accountCustomer, accountChecking, accountGold, accountRegular);
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

     Menu quit = new Menu("Quit");
     quit.getItems().add(new MenuItem("Exit"));
     menuBar.getMenus().add(quit);
     quit.setOnAction(new EventHandler<ActionEvent>() {
    	 public void handle(ActionEvent e) {
    		 System.exit(0);
    	 }
     });
      accountCustomer.setOnAction(e -> {
 				Scene myScene = new Scene(getCustPane(), 280,175, Color.BEIGE);
    			Stage test = new Stage();
    			test.setTitle("Customer Information");
    			test.setScene(myScene);
    			test.show();
     });
     
     accountChecking.setOnAction(e -> {
    	 String acctType = "Checking Account";
    	 if (customers.isEmpty()) {
    		 JOptionPane.showMessageDialog(null, "There are no customers available to select!\n\nUnable to add " + acctType + "!!");
    	 } else {
  		    Stage chkAddStage = new Stage();
		    Scene scene = new Scene(addAccountPane(acctType), 800, 300);
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

	  public BorderPane getCustPane(){
		  
		  
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
		  
		  btnAdd.setOnKeyPressed(e -> {
			  boolean cc = false;
			  Stage myStage = (Stage) txtID.getScene().getWindow();
			  cc = createCustomer(txtID, txtName);
			  if (!cc) {
				  myStage.requestFocus();
				  txtID.requestFocus();
			  } else {
				  txtID.setText("");
				  txtName.setText("");
				  //myStage.requestFocus();
				  txtID.requestFocus();
			  }
		  });
		  
		  btnAdd.setOnAction(e -> {
			  boolean cc = false;
			  Stage myStage = (Stage) txtID.getScene().getWindow();
			  cc = createCustomer(txtID, txtName);
			  if (!cc) {
				  myStage.requestFocus();
				  txtID.requestFocus();
			  } else {
				  txtID.setText("");
				  txtName.setText("");
				  myStage.requestFocus();
				  txtID.requestFocus();
			  }
		  });
		  
		  btnExit.setOnAction(e -> {
			  btnExit.getScene().getWindow().hide();
			  });
	  return custMainPane;
	  }

	  public boolean createCustomer(TextField txtID, TextField txtName) {
		  int element = customers.size();
		  //JOptionPane.showMessageDialog(null, element);
		  if(txtID.getText().isEmpty()) {
			  JOptionPane.showMessageDialog(null,  "Please Enter Customer ID");
		  } else if (txtName.getText().isEmpty()) {
			  JOptionPane.showMessageDialog(null, "Please Enter Customer Name");
		  } else {
			  Customer customer = new Customer(txtID.getText(), txtName.getText());
			  customers.add(customer);
			  if (element < customers.size()) {
				  JOptionPane.showMessageDialog(null,  "Customer Created successfully!");
				  return true;
			  } else {
				  if (element == customers.size()) {
					  JOptionPane.showMessageDialog(null, "Customer Creation Unsessful!!");
				  }
			  }
		  }
		  return false;
	  }

	  public BorderPane addAccountPane(String acctType) {
		  // heading font size
		  String heading = "25";
		  // message box font size
		  String txtMessage = "18";
		  // label font size
		  String txtLabel = "14";
		  // the beginning of the style string
		  String acctStyleFirst = "-fx-font: ";
		  // the last pert of the style string before the color
		  String acctStyleLast =" arial; -fx-text-fill: ";
		  // the blue color
		  String acctStyleBlue = "blue;";
		  // the red color
		  String acctStyleRed = "red;";
		  // the black color
		  String acctStyleBlack = "black;";
		  // all buttons styles not including the color
		  String acctButtonStyle = "-fx-font: 18 arial; -fx-base: ";
		
		  // declare buttons and set their styles
		  Button btnAdd = new Button("Add");
		  btnAdd.setStyle(acctButtonStyle + "blue;");
		  Button btnExit = new Button("Exit");
		  btnExit.setStyle(acctButtonStyle + "red;");
		  
		  // set up the account type heading
		  Label lbltxtDisplayBoxAccounts = new Label(acctType);  // heading
		  lbltxtDisplayBoxAccounts.setStyle(acctStyleFirst + heading  + acctStyleLast + acctStyleBlue);
		  // set up the error display message area
		  Label lbltxtDisplayBoxMessage = new Label(" "); // error reporting
		  
		  // declare the heading and text boxes for 
		  Label lblAccountNumber = new Label("Account Number");
		  lblAccountNumber.setStyle(acctStyleFirst + txtLabel + acctStyleLast + acctStyleBlue);
		  Label lblCustomerID = new Label("Customer ID");
		  lblCustomerID.setStyle(acctStyleFirst + txtLabel + acctStyleLast + acctStyleBlue);
		  Label lblOpeningBalance = new Label("Opening Balance");
		  lblOpeningBalance.setStyle(acctStyleFirst + txtLabel + acctStyleLast + acctStyleBlue);
		  
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
		  accountDisplayPane.setPadding(new Insets(20,15,25,15));
		  accountDisplayPane.getChildren().addAll(lbltxtDisplayBoxAccounts);
		  accountDisplayPane.setAlignment(Pos.CENTER);
		  
		  HBox topPane = new HBox(3);
		  topPane.setPadding(new Insets(15,15,15,15));
		  topPane.getChildren().addAll(customerPane,accountPane,balancePane);
		  topPane.setAlignment(Pos.CENTER);
		  
		  VBox finaTopPane = new VBox(2);
		  //bottomPane.setPadding(new Insets(15,15,15,15));
		  finaTopPane.getChildren().addAll(accountDisplayPane, topPane);
		  finaTopPane.setAlignment(Pos.CENTER);
		  
		  HBox centerPane = new HBox(1);
		  centerPane.setPadding(new Insets(15,15,25,15));
		  centerPane.getChildren().addAll(lbltxtDisplayBoxMessage);
		  centerPane.setAlignment(Pos.CENTER);
		  
		  
		  HBox bottomPane = new HBox(2);
		  bottomPane.setPadding(new Insets(15,15,15,15));
		  bottomPane.getChildren().addAll(btnAdd, btnExit);
		  bottomPane.setAlignment(Pos.CENTER);
		  BorderPane mainPane = new BorderPane();
			  mainPane.setTop(finaTopPane);
			  mainPane.setCenter(centerPane);
			  mainPane.setBottom(bottomPane);
			  mainPane.setStyle("-fx-border-color: blue");
		     
		  txtAccountNumber.setOnAction(e -> {
			  txtCustomerID.requestFocus();
		  });
		  txtCustomerID.setOnAction(e -> {
			  txtOpeningBalance.requestFocus();
		  });
		  txtAccountNumber.setOnKeyTyped(e ->{
			  lbltxtDisplayBoxMessage.setText(" ");
	     });
	     
	     txtCustomerID.setOnKeyTyped(e -> {
	    	 lbltxtDisplayBoxMessage.setText(" ");
	     });
	     
	     txtOpeningBalance.setOnKeyTyped(e -> {
	    	 lbltxtDisplayBoxMessage.setText(" ");
	     });
	     
	     txtOpeningBalance.setOnAction(e -> {
	    	double amount = -1;
	    	try {
		    	amount = Double.parseDouble(txtOpeningBalance.getText());
		    	if (amount < 0.0) {
    				 lbltxtDisplayBoxMessage.setText("Negative Opening Balances are not allow!\n\nPlease enter a positive Opening Balance!");
    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    				 Stage myStage = (Stage) txtOpeningBalance.getScene().getWindow();
    				 myStage.requestFocus();
	    			 txtOpeningBalance.requestFocus();
		    	}
    		 } catch (NumberFormatException h) {
    			 lbltxtDisplayBoxMessage.setText("I was expecting a Account Opening Balance, Please re-enter");
				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    			 txtOpeningBalance.requestFocus();
    		 } catch (InputMismatchException  g) {
    			 lbltxtDisplayBoxMessage.setText("You must enter the Opening Balance to the Account");
				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    			 txtOpeningBalance.requestFocus();
    		 } catch (NoSuchElementException f) {
    			 lbltxtDisplayBoxMessage.setText("Out of Range, Please re-enter the Opening Balance Ammount!");
				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
    			 txtOpeningBalance.requestFocus();
    		 }
	     });
	     
	     btnAdd.setOnAction(e -> {
	    	 // check to see if the text boxes are empty and notify user
	    	 if (txtAccountNumber.getText().isEmpty()) {
	    		 lbltxtDisplayBoxMessage.setText("You must enter an Accouont Number!");
	    		 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
	    		 txtAccountNumber.requestFocus();
	    	 } else if (txtCustomerID.getText().isEmpty()) {
	    		 lbltxtDisplayBoxMessage.setText("You must enter a Customer ID!");
	    		 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
	    		 txtCustomerID.requestFocus();
	    	 } else if (txtOpeningBalance.getText().isEmpty()) {
	    		 lbltxtDisplayBoxMessage.setText("You must the Opening Balance!");
	    		 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
	    		 txtOpeningBalance.requestFocus();
	    	 } else {
	    		 int element = accounts.size();
	    		 double amount = -1.0;
	    		 boolean isOk = false;
		    		 try {
		    			 amount = Double.parseDouble(txtOpeningBalance.getText());
		    			 if (amount < 0.0) {
		    				 lbltxtDisplayBoxMessage.setText("Negative Opening Balances are not allow!\n\nPlease enter a positive Opening Balance!");
		    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
		    				 txtOpeningBalance.requestFocus();
		    			
		    			 } else {
				    		 JOptionPane.showMessageDialog(null, amount);
				    		 isOk = true;
		    			 }
		    		 } catch (NumberFormatException h) {
		    			 lbltxtDisplayBoxMessage.setText("I was expecting a Account Opening Balance, Please re-enter");
	    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
		    			 txtOpeningBalance.requestFocus();
		    		
		    		 } catch (InputMismatchException  g) {
		    			 lbltxtDisplayBoxMessage.setText("You must enter the Opening Balance to the Account");
	    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
		    			 txtOpeningBalance.requestFocus();
		    			
		    		 } catch (NoSuchElementException f) {
	    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
		    			 JOptionPane.showMessageDialog(null, "Out of Range, Please re-enter the Opening Balance Ammount!");
		    		 }
	    		 if (isOk) {
	    			 Customer customer = customers.get(0);
	    			 for (int x = 0; x < customers.size(); x++) {
		    			 if (customers.get(x).getCustomerID() == txtCustomerID.getText().trim()) {
		    				 JOptionPane.showMessageDialog(null, customers.get(x));
		    				 customer = customers.get(x);
		    			 } else {
		    				 lbltxtDisplayBoxMessage.setText("Customer not fount!\n\nPlease re-enter Customer ID");
		    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
		    				 txtCustomerID.requestFocus();
		    			 }
	    			 }
	    			 // notify the user
	    			 accounts.add(new Checking(txtAccountNumber.getText(), amount, customer));
		    		 if (element< accounts.size()) {
	    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleBlack);
	    	 			 lbltxtDisplayBoxMessage.setText("Account successfully Added!");
		    		 } else {
	    				 lbltxtDisplayBoxMessage.setStyle(acctStyleFirst + txtMessage + acctStyleLast + acctStyleRed);
		    			 lbltxtDisplayBoxMessage.setText("Adding Account was Unsuccessful!");
		    		 }
	    		 } else {
	    			 txtOpeningBalance.requestFocus();
	    			 
	    		 }
	    	 }
	     });
	     
	     btnExit.setOnAction(e -> {
	    	 btnExit.getScene().getWindow().hide();
	    });		     
		return mainPane;
	  }
	  
	  public boolean createAccount() {
		  
		  
		  
		  return false;
	  }
}

class NewActualBanker {
	//NewFXBanker fxBanker = new NewFXBanker();
	
	public void addCustomer() {
		// Customer customer = fxBanker.customers.get(0);
		// Account account = new Checking("", 0.0, customer);
		
	}


}