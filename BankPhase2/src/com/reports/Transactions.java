package com.reports;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Transactions extends Application {
	
	 public static void main(String[] args) {
		    launch(args);
		  }
	
	public void start(Stage primaryStage) {
	    // Create a scene by calling the method above and place it in the stage
	    Scene myScene = new Scene(processTransactionsPane(), 800, 450);
	    primaryStage.setTitle("Transactions"); // Set the stage title
	    primaryStage.setScene(myScene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }
	
	public BorderPane processTransactionsPane(){
		Font arial18 = Font.font("Arial", 18);
		Font arial25 = Font.font("Arial", 25);
		Font arial18Bold = Font.font("Arial", FontWeight.BOLD, 18);
		String txtFillBlue = "-fx-text-fill: blue;", txtFillRed = "-fx-text-fill: red;",
				baseFillBlue = "-fx-base: blue;", baseFillRed = "-fx-base: red;", 
				txtFillWhite = "-fx-text-fill: white;";
		
		Label lblPageTitle = new Label("Transactions");
		lblPageTitle.setFont(arial25);
		lblPageTitle.setStyle(txtFillBlue);
		
		Label lblAccountNum = new Label("Account #");
		lblAccountNum.setFont(arial18);
		lblAccountNum.setStyle(txtFillBlue);
		
		Label lblAmount = new Label("Amount");
		lblAmount.setFont(arial18);
		lblAmount.setStyle(txtFillBlue);
		
		Label lblSuccess = new Label("Applied Successfully!");
		lblSuccess.setFont(arial18);
		lblSuccess.setStyle(txtFillRed);
		
		TextField txtAccountNum = new TextField();
		txtAccountNum.setFont(arial18);
		txtAccountNum.setStyle(txtFillBlue);
		
		TextField txtAmount = new TextField();
		txtAmount.setFont(arial18);
		txtAmount.setStyle(txtFillBlue);
		
		TextArea lstAccount = new TextArea();
		lstAccount.setPrefRowCount(5);
		lstAccount.setEditable(false);
		lstAccount.setFont(arial18);
		lstAccount.setStyle(txtFillBlue);
		
		Button btnAdd = new Button("Add");
		btnAdd.setFont(arial18Bold);
		btnAdd.setStyle(baseFillBlue + txtFillWhite);
		
		Button btnExit = new Button("Exit");
		btnExit.setFont(arial18Bold);
		btnExit.setStyle(baseFillRed + txtFillWhite);
		
		HBox topPane = new HBox(4);
		topPane.setPadding(new Insets(35, 5, 35,5));
		topPane.getChildren().add(lblPageTitle);
		topPane.setAlignment(Pos.TOP_CENTER);
		
		VBox accountPane = new VBox(5);
		accountPane.getChildren().addAll(lblAccountNum, txtAccountNum);
		accountPane.setAlignment(Pos.CENTER);
		
		VBox amountPane = new VBox(5);
		amountPane.getChildren().addAll(lblAmount, txtAmount);
		amountPane.setAlignment(Pos.CENTER);
		
		HBox groupPane = new HBox(5);
		groupPane.getChildren().addAll(accountPane, amountPane);
		groupPane.setAlignment(Pos.CENTER);
		
		VBox listPane = new VBox(5);
		listPane.getChildren().add(lstAccount);
		listPane.setMaxWidth(300);
		listPane.setAlignment(Pos.CENTER);
		
		VBox successPane = new VBox(5);
		successPane.setPadding(new Insets(35,5,35,5));
		successPane.getChildren().add(lblSuccess);
		successPane.setAlignment(Pos.CENTER);
		
		
		VBox centerPane = new VBox(5);
		centerPane.getChildren().addAll(groupPane, successPane, listPane);
		centerPane.setAlignment(Pos.CENTER);
		
		HBox bottomPane = new HBox(5);
		bottomPane.setPadding(new Insets(15,5,15,5));
		bottomPane.getChildren().addAll(btnAdd, btnExit);
		bottomPane.setAlignment(Pos.BOTTOM_CENTER);
		
	 
		 BorderPane mainPane = new BorderPane();
		 mainPane.setTop(topPane);
		 mainPane.setCenter(centerPane);
		 mainPane.setBottom(bottomPane);
	     mainPane.setStyle("-fx-border-color: blue");
	
	     return mainPane;
	}
	
}
