package com.accounts;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddAccount extends Application {

	 public static void main(String[] args) {
		    launch(args);
		  }
	
	public void start(Stage primaryStage) {
	    // Create a scene by calling the method above and place it in the stage
	    Scene scene = new Scene(getPane(), 800, 600);
	    primaryStage.setTitle("Add Account"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }
	
	  protected BorderPane getPane() {
		  
		  Button btnAdd = new Button("Add");
		  Button btnExit = new Button("Exit"); 
		  
		  TextField txtDisplayBoxAccounts = new TextField(" ");
		  txtDisplayBoxAccounts.setPrefSize(0,1);
		  txtDisplayBoxAccounts.setDisable(true);
		  
		  TextField txtDisplayBoxMessage = new TextField(" ");
		  txtDisplayBoxMessage.setPrefSize(0,1);
		  txtDisplayBoxMessage.setDisable(true);
		  
		  Label lblAccountNumber = new Label("Account Number");
		  Label lblCustomerID = new Label("Customer ID");
		  Label lblOpeningBalance = new Label("Opening Balance");
		  
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
		  accountDisplayPane.getChildren().addAll(txtDisplayBoxAccounts);
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
		  centerPane.getChildren().addAll(txtDisplayBoxMessage);
		  centerPane.setAlignment(Pos.CENTER);
		  
		  
		  HBox bottomPane = new HBox(2);
		  //bottomPane.setPadding(new Insets(15,15,15,15));
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
			  
			  return mainPane;
		  	
//implement SetOnAction for btnAdd & btnExit
	
	
}
}
