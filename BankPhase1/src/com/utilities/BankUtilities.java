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
package com.utilities;

import java.util.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import com.customers.Customer;

public class BankUtilities {
	private static Random randGen = new Random();

	// generate unique info
	
	public int generateUniqueTransNumber() {
		return Math.abs((int) Calendar.getInstance().getTimeInMillis());
	}

	public java.util.Date generateDate() {
		return new java.util.Date();
	}
	
	public String generateUniqueAcctNumber() {
		String output = "";
		output += (char) genRandomNumber(65, 90, 100);
		output += (char) genRandomNumber(65, 90, 100);
		output += genRandomNumber(1000, 10000, 10000);
		return output;
	}
	
	// generates a random number based upon the criteria that the user specifies by digits
	// makes the random number generator very portable between this and other methods
	public static int genRandomNumber(int minNumber, int maxNumber, int digits) {
		boolean chkExit = true;
		int isValueOk = randGen.nextInt(digits+1);
		while (chkExit) {
			if (isValueOk < minNumber || isValueOk > maxNumber) {
				isValueOk = randGen.nextInt(digits+1);
			} else {
				chkExit = false;
			}
		}
		return isValueOk;
	}

	
	public Customer createCustomer(int condition) {
	      String customerID = "", customerName = "";
		if (condition == 0) {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("Enter customer ID ");
			customerID = input.nextLine();
			System.out.print("Enter customer Name ");
			customerName = input.nextLine();
		} else if (condition == 1) {  // FXBanker
			Scene myScene = new Scene(getCustPane(), 280,175, Color.BEIGE);
			Stage test = new Stage();
			test.setTitle(" LLC");
			test.setScene(myScene);
			test.show();

//			customerID = JOptionPane.showInputDialog("Enter customer ID");
//			customerName = JOptionPane.showInputDialog("Enter customer Name");
		}
		Customer customer = new Customer(customerID, customerName);
		return customer;
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
		  
		  //bottomPane.setPadding(new Insets(15,15,15,15));
		  //bottomPane.getChildren().addAll(btnName, btnNumber, btnClear, btnExit);
		  //bottomPane.setAlignment(Pos.CENTER);

		  BorderPane custMainPane = new BorderPane();
		  custMainPane.setTop(lblPane);
		 // custMainPane.setTop(txtPane);
		  custMainPane.setCenter(txtPane);
		  custMainPane.setBottom(bottomPane);
		  custMainPane.setStyle("-fx-border-color: red");
		  
//		  btnAdd.setOnAction(e -> {
//			  ActualFXBanker banker = new ActualFXBanker();
//			  banker.createCustomer(txtID.getText(), txtName.getText());
//		  });

	  return custMainPane;
  }

}
