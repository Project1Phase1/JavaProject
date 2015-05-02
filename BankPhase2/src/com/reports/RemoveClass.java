package com.reports;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RemoveClass extends Application {

	public static void main(String[] args) {
	    launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
	    // Create a scene by calling the method above and place it in the stage
	    Scene myScene = new Scene(removeTestScreen(1), 800, 450);
	    primaryStage.setTitle("Transactions"); // Set the stage title
	    primaryStage.setScene(myScene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	}

	
	public BorderPane removeTestScreen(int remNum){
		String txtTitle = "", txtLabel = "";
		Font[] arialFont = new Font[4];
		arialFont[0] = Font.font("Arial", 14);
		arialFont[1] = Font.font("Arial", 18);
		arialFont[2] = Font.font("Arial", FontWeight.BOLD, 18);
		arialFont[3] = Font.font("Arial", 25);
		String[] txtFillColor = new String[7];
		txtFillColor[0] = "-fx-text-fill: blue;";
		txtFillColor[1] = "-fx-text-fill: red;";
		txtFillColor[2] = "-fx-text-fill: black;";
		txtFillColor[3] = "-fx-text-fill: white;";
		txtFillColor[4] = "-fx-text-fill: green;";
		txtFillColor[5] = "-fx-base: blue;";
		txtFillColor[6] = "-fx-base: red;";
		switch (remNum) {
		case 0:
			txtTitle = "Remove Customer";
			txtLabel = "Customer #";
			break;
		case 1:
			txtTitle = "Remove Account";
			txtLabel = "Account #";
			break;
		}
		Label lblTitle = new Label(txtTitle);
		lblTitle.setFont(arialFont[3]);
		lblTitle.setStyle(txtFillColor[0]);
		
		Label lblCustomerNum = new Label(txtLabel);
		lblCustomerNum.setFont(arialFont[1]);
		lblCustomerNum.setStyle(txtFillColor[0]);
		
		Label lblMessage = new Label(" ");
		lblMessage.setFont(arialFont[1]);
		lblMessage.setStyle(txtFillColor[1]);
		
		TextField txtCustomerNum = new TextField();
		txtCustomerNum.setFont(arialFont[1]);
		txtCustomerNum.setStyle(txtFillColor[0]);
		txtCustomerNum.setMaxWidth(200);
		
		TextArea txtOutput = new TextArea();
		txtOutput.setPrefRowCount(1);
		txtOutput.setPrefColumnCount(35);
		txtOutput.setFont(arialFont[1]);
		txtOutput.setStyle(txtFillColor[1]);
		txtOutput.setMaxWidth(600);
		
		Button btnRemove = new Button("Remove");
		btnRemove.setFont(arialFont[2]);
		btnRemove.setStyle(txtFillColor[5]);
		
		Button btnExit = new Button("Exit");
		btnExit.setPadding(new Insets(5, 35, 5, 35));
		btnExit.setFont(arialFont[2]);
		btnExit.setStyle(txtFillColor[6]);
		
		VBox titlePane = new VBox(8);
		titlePane.setPadding(new Insets(15, 15, 25, 15));
		titlePane.getChildren().add(lblTitle);
		titlePane.setAlignment(Pos.CENTER);
		
		VBox custNumPane = new VBox(8);
		custNumPane.getChildren().addAll(lblCustomerNum, txtCustomerNum);
		custNumPane.setPadding(new Insets(25, 15, 25, 15));
		custNumPane.setAlignment(Pos.CENTER);
		
		VBox outputPane = new VBox(8);
		outputPane.getChildren().add(txtOutput);
		outputPane.setAlignment(Pos.CENTER);
		
		VBox topPane = new VBox(8);
		topPane.getChildren().addAll(titlePane, custNumPane);
		topPane.setAlignment(Pos.CENTER);
		
		HBox buttonPane = new HBox(8);
		buttonPane.setPadding(new Insets(25, 15, 25, 15));
		buttonPane.getChildren().addAll(btnRemove, btnExit);
		buttonPane.setAlignment(Pos.CENTER);
		
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(topPane);
		mainPane.setCenter(outputPane);
		mainPane.setBottom(buttonPane);
		
		btnExit.setOnAction(e -> {
			btnExit.getParent().getScene().getWindow().hide();
		});
		
		return mainPane;
	}
}
