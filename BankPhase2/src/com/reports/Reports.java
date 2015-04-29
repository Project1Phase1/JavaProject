
package com.reports;


//import com.sun.corba.se.impl.orbutil.graph.Node;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Reports extends Application {
	
	 public static void main(String[] args) {
		    launch(args);
		  }
	 
	public void start(Stage primaryStage) {
	    // Create a scene by calling the method above and place it in the stage
	    Scene scene = new Scene(getPane(), 1300, 500);
	    primaryStage.setTitle("Reports"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }
	
	  protected BorderPane getPane() {
		  Label lblReports = new Label("Reports");
		 
		  
		  Button btnExit = new Button("Exit");
		  btnExit.setStyle("-fx-font: 18 Times New Roman; -fx-base: #ff2222;");
		  Font sansbold12 = Font.font("Times New Roman", FontWeight.BOLD, 13);
		  
		  
		  TextField txtDisplayBox = new TextField();
		  txtDisplayBox.setPrefSize(0,1);
		  txtDisplayBox.setEditable(false);
		  TextArea taOutput = new TextArea();
		  taOutput.setPrefRowCount(15);
		  taOutput.setPrefColumnCount(75);
		  taOutput.setFont(sansbold12);
		  taOutput.setEditable(false);;
		  
		  
		  //FlowPane adds nodes row by row horizontally or col by col vertically
		  VBox topPane = new VBox(2);
		  //Sets the top, right, bottom, and left padding around the region's content
		  topPane.setPadding(new Insets(11,12,13,14));
		  //Set the amount of horizontal space between each node
		  topPane.getChildren().addAll(lblReports,txtDisplayBox);
		  topPane.setAlignment(Pos.CENTER);
		  
		  FlowPane centerPane = new FlowPane();
		  //centerPane.setPadding(new Insets(11,0,5,0));
		  centerPane.setHgap(5);
		  centerPane.setVgap(5);
		  centerPane.getChildren().add(taOutput);
		  centerPane.setAlignment(Pos.CENTER);
		  
		  //HBpx pane lays out its children in a single horizontal row
		  HBox bottomPane = new HBox(4);
		  //bottomPane.setPadding(new Insets(15,15,15,15));
		  bottomPane.getChildren().addAll(btnExit);
		  bottomPane.setAlignment(Pos.CENTER);
		  
		  BorderPane mainPane = new BorderPane();
		  mainPane.setTop(topPane);
		  mainPane.setCenter(centerPane);
		  mainPane.setBottom(bottomPane);
		  mainPane.setStyle("-fx-border-color: blue");
		  
		  btnExit.setOnAction(e -> {
			  ((Window)(e.getSource())).getScene().getWindow().hide();
		});
	   
		  return mainPane;
	  }
}
