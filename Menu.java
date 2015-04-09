package project1;

import java.util.Scanner;

public class Menu
{

	public static void main(String[] args)
	{

			boolean finished = false;

			while (finished == false)
			{
				// Menu Display and Get user input
				int inputInt = 0;
				while (inputInt == 0)
				{
					inputInt = displayMenuAndGetInput();

					// if the input is out of range
					if ((inputInt < 1) || (inputInt > 10))
					{
						System.out.println("\nThe input is out of range. Please enter 1-10!");
						System.out.println();
						inputInt = 0;
					}
				} //end while

				// switch to correspondence function
				switch (inputInt)
				{
					case 1:
						//do something
						break;
					case 2:
						//do something
						break;
					case 3:
						//do something
						break;
					case 4:
						//do something
						break;
					case 5:
						//do something
						break;
					case 6:
						//do something
						break;
					case 7:
						//do something
						break;
					case 8:
						//do something
						break;
					case 9:
						//do something
						break;
					case 10:
						// exit
						finished = true;
						break;
					default:
						System.out.println("Invalid Input!");
						System.out.println("");
						break;
				} // end switch
			} // end while

	}
//--------------------------------------------------------------------------------------------

	public static int displayMenuAndGetInput()
	{
		int inputInt ;

		// Menu Display
		System.out.println("");
		System.out.println("     Welcome To Your Banking Center ");
		System.out.println("         =====================");
		System.out.println(" 1.  Create a Checking Account ");
		System.out.println(" 2.  Create a Gold Account ");
		System.out.println(" 3.  Create a Regular Account");
		System.out.println(" 4.  Make a Deposit ");
		System.out.println(" 5.  Make a Withdraw ");
		System.out.println(" 6.  Display Account Information ");
		System.out.println(" 7.  Remove an Account ");
		System.out.println(" 8.  Apply End of Month Updates ");
		System.out.println(" 9.  Display Bank Statistics ");
		System.out.println(" 10. Exit ");
		System.out.println("");

		// Get the input from the user
		System.out.print("Please input your choice (1-10): ");

		Scanner input = new Scanner( System.in );

		inputInt = input.nextInt();

		return inputInt;
	}

}
