
package com.reports;


//import com.sun.corba.se.impl.orbutil.graph.Node;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Reports extends Application {
	
	 public static void main(String[] args) {
		    launch(args);
		  }
	 
	public void start(Stage primaryStage) {
	    // Create a scene by calling the method above and place it in the stage
	    Scene scene = new Scene(getPane(), 500, 500);
	    primaryStage.setTitle("Reports"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }
	
	  protected BorderPane getPane() {
		  
		  
		  Label lblDisplayText = new Label();
		  lblDisplayText.setText("Customer View");
		  
		 Font arial18Bold = Font.font("Arial", FontWeight.BOLD, 18);
		 Font arial12 = Font.font("Arial", 12);
		  Button btnExit = new Button("Exit");
		  btnExit.setFont(arial18Bold);
		  btnExit.setStyle("-fx-base: red;");
		  
		  TextArea taOutput = new TextArea();
		  taOutput.setPrefRowCount(15);
		  taOutput.setPrefColumnCount(30);
		  taOutput.setFont(arial12);
		  taOutput.setEditable(false);
		  
		  HBox displayPane = new HBox();
		  displayPane.setPadding(new Insets(25,12,12,12));
		  displayPane.getChildren().addAll(lblDisplayText);
		  displayPane.setAlignment(Pos.CENTER);
		  
		  FlowPane centerPane = new FlowPane();
		  centerPane.setHgap(5);
		  centerPane.setVgap(5);
		  centerPane.getChildren().addAll(taOutput);
		  centerPane.setAlignment(Pos.CENTER);
		  
		  HBox bottomPane = new HBox(1);
		  bottomPane.setPadding(new Insets(0,0,25,0));
		  bottomPane.getChildren().addAll(btnExit);
		  bottomPane.setAlignment(Pos.CENTER);
		  
		  BorderPane mainPane = new BorderPane();
		  mainPane.setTop(displayPane);
		  mainPane.setCenter(centerPane);
		  mainPane.setBottom(bottomPane);
		  mainPane.setStyle("-fx-border-color: blue");
		  
		  btnExit.setOnAction(e -> {
			  btnExit.getScene().getWindow().hide();
		});
	   
		  return mainPane;
	  }
}
