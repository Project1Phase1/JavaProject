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

	

}
