package second_shape_drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class DrawingProg2 extends JFrame implements ActionListener{

	DrawingPanel drawingPanel = new DrawingPanel();
	JCheckBox filled = new JCheckBox("filled");
	

	DrawingProg2()
	{
		super("My Drawing Program");

		String[] colors = {"Red", "Green", "Blue"};
		String[] shapes1 = {"Rectangle", "Oval", "Line", "Scribble"};

		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		layout(shapes1, colors);

		setVisible(true);
	}

	private void layout(String[] shapes, String[] colors )
	{
		// set defaults
		drawingPanel.drawing.setColor(Color.red);
		drawingPanel.drawing.setDrawType(DrawType.rectangle);

		setLayout(new BorderLayout());

		JPanel stylePanel = new JPanel(new FlowLayout());
		stylePanel.add(filled);
		for (String elem : shapes)
			stylePanel.add(new JRadioButton(elem));
		
		JPanel colorPanel =  new JPanel(new GridLayout(0, 1));
		for (String elem : colors)
			colorPanel.add(new JRadioButton(elem));
		
		this.add(drawingPanel, BorderLayout.CENTER);
		this.add(stylePanel, BorderLayout.NORTH);
		this.add(colorPanel, BorderLayout.WEST);
		/*
	        drawingPanel goes in the CENTER
	        Create a JPanel with a FlowLayout for the NORTH.
	        This JPanel will get the filled JCheckBox and all of the necessary radio buttons

	        Create a JPanel with a GridLayout(0,1) or GridLayout(3,1) for the WEST.
	        This JPanel will get the radio buttons for the colors.

	        All RadioButtons and the CheckBox will have ActionListeners associated with them.
		 */


	}

	public static void main(String[] args) 
	{
		DrawingProg2 dp = new DrawingProg2();
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) 
	{
		String action = actionEvent.getActionCommand();
		System.out.println(action);

		switch(action)
		{
		case "red":
			drawingPanel.drawing.setColor(Color.red);
			break;
			/*
	  Handle all of the other actions, and make sure you set the visibility of 
	  the filled CheckBox appropriately.
	  The other methods to be called are:
	         drawingPanel.drawing.setDrawType(....);
	         drawingPanel.drawing.setFilled(.....);
			 */

		}


	}

}
