package reports;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Transactions extends Application{
	
	 public static void main(String[] args) {
		    launch(args);
		  }
	
	public void start(Stage primaryStage) {
	    // Create a scene by calling the method above and place it in the stage
	    Scene scene = new Scene(getPane(), 1300, 300);
	    primaryStage.setTitle("Transactions"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }
	public BorderPane getPane(){
		
	 Label lblAccountNumber = new Label("Account Number");
	 Label lblAmount = new Label("Amount");

	 Button btnAppliedSuccessfully = new Button("Applied Successfully");
	 Button btnApply = new Button("Apply");
	 Button btnExit = new Button("Exit");
	  
	 TextField txtAccountNumber = new TextField();
	 TextField txtAmount = new TextField();
	
	 VBox accountPane = new VBox(2);
	  //bottomPane.setPadding(new Insets(15,15,15,15));
	  accountPane.getChildren().addAll(lblAccountNumber, txtAccountNumber);
	  accountPane.setAlignment(Pos.CENTER);
	  
	  VBox amountPane = new VBox(2);
	  //bottomPane.setPadding(new Insets(15,15,15,15));
	  accountPane.getChildren().addAll(lblAmount, txtAmount);
	  accountPane.setAlignment(Pos.CENTER);
	  
	  HBox topPane = new HBox(4);
	  //bottomPane.setPadding(new Insets(15,15,15,15));
	  topPane.getChildren().addAll(accountPane,amountPane);
	  topPane.setAlignment(Pos.CENTER);
	 
	  
	  
	  HBox centerPane = new HBox(4);
	  //bottomPane.setPadding(new Insets(15,15,15,15));
	 centerPane.getChildren().addAll(btnAppliedSuccessfully);
	 centerPane.setAlignment(Pos.CENTER);
	 
	  
	  HBox bottomPane = new HBox(4);
	  //bottomPane.setPadding(new Insets(15,15,15,15));
	 bottomPane.getChildren().addAll(btnApply, btnExit);
	 bottomPane.setAlignment(Pos.CENTER);
	 
	 BorderPane mainPane = new BorderPane();
	 mainPane.setTop(topPane);
     mainPane.setCenter(centerPane);
     mainPane.setBottom(bottomPane);
     mainPane.setStyle("-fx-border-color: blue");
	  
	  return mainPane;
	
	  
//implement SetOnAction for btnApply & btnExit

	
	
}
}
