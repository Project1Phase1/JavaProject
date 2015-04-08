/*
	@Author: Awny Alnusair
	@File name: TemplateGUITest.java

	Swings template for GUI objects (buttons, labels, textfields, textareas, etc...)

	Tutorial, Creating a GUI With JFC/Swing: http://download.oracle.com/javase/tutorial/uiswing/TOC.html

*/

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MyFirstSwingProgram
{

    public static void main(String[] args)
    {
        TemplateGUI frame = new TemplateGUI();
        frame.setSize(600, 250); // resize the frame
        frame.setVisible(true);// show the frame
        frame.setResizable(false);
        frame.setTitle("Testing GUI in Java");

    }// end main
}

/**
 	A frame with panels
 */

//Frame is the top level window with a title and a border. It's used as a basis
//for windowing. It is basically a container for other components and user interface elements

class TemplateGUI extends JFrame
{
	//Declare all your graphical user interface objects such as textfields, command buttons,... here:

	private JButton b1, b2, b3, b4;
	private JLabel l1,l2;
	private JTextField t1,t2;
	private JTextArea ta;

   //A panel is a component that can be added to a frame for drawings (graphics and text).
   //It can also serve as a container for other objects such as text boxes, buttons, etc.
   //Panel can also be used for drawings by utilizing the methods in the Graphics class
	private JPanel p1,p2, p3;


    public TemplateGUI()
    {
	  //Exit the program when the user closes the frame (clicking the X)
       setDefaultCloseOperation(EXIT_ON_CLOSE);//Try: HIDE_ON_CLOSE or DO_NOTHING_ON_CLOSE

       /*
       	Container is a component that can contain other GUI components
       	The simplest way of positioning components on the screen is the use of
       	FlowLayout approach (Components are placed on a container from left to write).

       	A GridLayout lays out components in a grid of equal sized rectangles.
       	The constructor for a GridLayout with R rows and C columns takes the
       	form GridLayout(R,C). If you want to leave horizontal gaps of H pixels between
       	rows and vertical gaps of V pixels between columns, use GridLayout(R,C,H,V) instead.

       	The following uses a BorderLayout: Where the container is divided into five regions which are:
       	North, South, Center, West, East:
       */
        Container window = getContentPane(); //returns the container of this frame
        window.setLayout(new BorderLayout(0,10) );
        //Leave 10 pixels of vertical gaps between the components
        //BorderLayout(int hgap, int vgap)

		//Create a sample font:
        Font sansbold12 = new Font("SansSerif", Font.BOLD, 12);

        p1 = new JPanel();
	    p1.setPreferredSize(new Dimension(600, 40));
	    p1.setBackground(Color.LIGHT_GRAY);
        //window.add(p1); if FlowLayout is used

        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(600, 120));
        p2.setBackground(Color.CYAN);

	    p3 = new JPanel();
	    p3.setPreferredSize(new Dimension(600, 40));
	    p3.setLayout(new GridLayout(1,4));
        p3.setBackground(Color.LIGHT_GRAY);

        //Create all the graphical objects you declared above:
		l1 = new JLabel("Enter your name:");
		p1.add(l1);

		//Note: The default layout for panels is FlowLayout, thus, components will be added to the
		//panel in order from left to right. To override the default, you write:
		//p1.setLayout(new BorderLayout()); then you can position the components as follows:
		//p1.add("North", l1); ......

		t1 = new JTextField(15);
		t1.setEditable(true); //Not necessary: the default is editable textfield1
		t1.setToolTipText("Your name!");
		p1.add(t1);

		l2 = new JLabel("Enetr a number:");
		p1.add(l2);

		t2 = new JTextField(5);
		t2.setToolTipText("Numeric value!");
		p1.add(t2);

		ta = new JTextArea(5,35);
		ta.setFont(sansbold12);
        p2.add(ta);

        b1 = new JButton("Display name");
		p3.add(b1);

        b2 = new JButton("Display number");
		p3.add(b2);

        b3 = new JButton("Clear");
		p3.add(b3);

        b4 = new JButton("Exit");
		p3.add(b4);


        window.add("North", p1); // display it north of the center panel
        window.add("Center",p2);
        window.add("South", p3);


        /*
        Components use various Listeners:
        JButton, JMenuItem, JComboBox, and JTextField use ActionListener
        JCheckBox, JRadioButton use ItemListener
        JSlider use ChangeListener
        etc.......
        */
        //Create button actions:
        ClickAction handler = new ClickAction();

        /*
         addActionListener method is used to register the JButton objects.
         When an action event occurs, the object's actionPerformed method is invoked.
        */
        b1.addActionListener(handler);
  		b2.addActionListener(handler);
  		b3.addActionListener(handler);
  		b4.addActionListener(handler);


    }//end TemplateGUI() constructor

/**
 	An action listener 'inner class' that creates event handlers for button clicks:
 */

private class ClickAction implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {
		//Making the GUI components do something:

		int num;
		double sqRoot;
		String numS, name;

		if (event.getSource() == b1)
		{
			name = t1.getText();
			ta.setText("The name you entered is: "+ name);

		}
		else if (event.getSource() == b2)
		{
			try{
					numS = t2.getText();
					num = Integer.parseInt(numS);
					sqRoot = Math.sqrt(num);
					ta.append("\n"+"Square root of your number is: " + sqRoot);
			}
			catch(NumberFormatException a){
					JOptionPane.showMessageDialog(null, "You must enter a number", "Wrong Input", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if (event.getSource() == b3)
		{
			ta.setText("");
		}
		else if (event.getSource() == b4)
		{
			JOptionPane.showMessageDialog(null, "Exiting!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);

			System.exit( 0 );  // terminate application
      		//class System is part of the Java.lang package thats imported automatically
      		//for every java program.
      		//exit: is a method used to terminate any application that has graphical user interface components
		}


    }//end actionPerformed

  }//end ClickAction

}//end TemplateGUI



//For more info, check out this tutorial:
//http://download.oracle.com/javase/tutorial/uiswing/TOC.html
