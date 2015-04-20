package com.utilities;
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
/*
@Author: Awny Alnusair
@File name: MyFirstJavaFXProgram.java

JavaFX template for creating GUI programs with UI Controls such as buttons, labels, textfields, textareas, etc.

Textbook: Chapters: 14, 15, and 16
Tutorial: http://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm

*/

public class MyFirstJavaFXProgram extends Application {

  public static void main(String[] args) {
    launch(args);
  }
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a scene by calling the method above and place it in the stage
    Scene scene = new Scene(getPane(), 650, 250);
    primaryStage.setTitle("My First JavaFX Program"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  protected BorderPane getPane() {

	  //Declare some UI Controls (Labels, Buttons, Textfields, etc.)
	  Label lblName = new Label("Enter your name");
	  Label lblNumber = new Label("Enter a number");

	  Button btnName = new Button("Display Name");
	  Button btnNumber = new Button("Display Number");
	  Button btnClear = new Button("Clear");
	  Button btnExit = new Button("Exit");
	  btnExit.setStyle("-fx-font: 18 arial; -fx-base: #ff2222;");

	  Font sansbold12 = Font.font("SansSerif", FontWeight.BOLD, 13);

	  TextField txtName = new TextField();
	  TextField txtNumber = new TextField();
	  TextArea taOutput = new TextArea();
	  taOutput.setPrefRowCount(5);
	  taOutput.setPrefColumnCount(35);
	  taOutput.setFont(sansbold12);

	  //Create some Panes:

	  //FlowPane adds nodes row by row horizontally or col by col vertically
	  FlowPane topPane = new FlowPane();
	  //Sets the top, right, bottom, and left padding around the region's content
	  topPane.setPadding(new Insets(11,12,13,14));
	  //Set the amount of horizontal space between each node
	  topPane.setHgap(5);
	  //Set the amount of vertical space between each node
	  topPane.setVgap(5);
	  topPane.getChildren().addAll(lblName, txtName, lblNumber, txtNumber);
	  topPane.setAlignment(Pos.CENTER_LEFT);


	  FlowPane centerPane = new FlowPane();
	  //centerPane.setPadding(new Insets(11,0,5,0));
	  centerPane.setHgap(5);
	  centerPane.setVgap(5);
	  centerPane.getChildren().add(taOutput);
	  centerPane.setAlignment(Pos.CENTER);

	  //HBpx pane lays out its children in a single horizontal row
	  HBox bottomPane = new HBox(4);
	  //bottomPane.setPadding(new Insets(15,15,15,15));
	  bottomPane.getChildren().addAll(btnName, btnNumber, btnClear, btnExit);
	  bottomPane.setAlignment(Pos.CENTER);

	  //The following uses a BorderPane: Where nodes are placed in five regions:
	  //top, bottom, left, right, center
	  BorderPane mainPane = new BorderPane();
	  mainPane.setTop(topPane);
	  mainPane.setCenter(centerPane);
	  mainPane.setBottom(bottomPane);
	  mainPane.setStyle("-fx-border-color: red");

	  //Handling button click events:
	  //Buttons have an onAction Property which sets a handler for handling button actions:
	  btnName.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
				String name = txtName.getText();
				taOutput.setText("The name you entered is: " + name);
		    }
		});

	  btnNumber.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
				try{
					double num = Double.parseDouble(txtNumber.getText());
					taOutput.appendText("\nSquare root of your number is: " + Math.sqrt(num));
			}
			catch(NumberFormatException a){
					JOptionPane.showMessageDialog(null, "You must enter a number", "Wrong Input", JOptionPane.INFORMATION_MESSAGE);
			}
		    }
		});


	  btnClear.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
			  	taOutput.clear();
		    }
		});
	  
	  //You can also use the new Java 8 Lambda expression feature as follows:
	  //A lambda expression can be viewed as an anonymous class with precise syntax
	  btnExit.setOnAction(e -> {//code for processing event e
			  	JOptionPane.showMessageDialog(null, "Exiting!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
				System.exit( 0 ); 
				//class System is part of the Java.lang package thats imported automatically for every java program.
				//exit: is a method that terminate any application that has graphical user interface components
		});
	  
    return mainPane;
  }

}
