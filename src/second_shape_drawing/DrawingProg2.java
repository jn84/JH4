package second_shape_drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DrawingProg2 extends JFrame implements ActionListener
{
	private final Dimension DIMEN = new Dimension(800,  400);
	private final Color defaultColor = Color.red;
	private final DrawType defaultShape = DrawType.rectangle;

	DrawingPanel drawingPanel = new DrawingPanel();
	JPanel shapePanel = null;
	JPanel colorPanel = null;
	JCheckBox filled = new JCheckBox("filled");

	DrawingProg2()
	{
		super("My Drawing Program");

		String[] colors = {"Red", "Green", "Blue"};
		String[] shapes1 = {"Rectangle", "Oval", "Line", "Scribble", "Polygon"};

		setSize(DIMEN);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		layout(shapes1, colors);

		setVisible(true);
	}

	private void layout(String[] shapes, String[] colors)
	{
		// set defaults
		// How to preselect the associated radio buttons in a simple way?
		//		Without extensive code modification.
		drawingPanel.drawing.setColor(defaultColor);
		drawingPanel.drawing.setDrawType(defaultShape);

		setLayout(new BorderLayout());

		shapePanel = new JPanel(new FlowLayout());
		filled.addActionListener(this);
		shapePanel.add(filled);
		buildPanel(shapePanel, shapes);

		colorPanel =  new JPanel(new GridLayout(0, 1));
		buildPanel(colorPanel, colors);

		this.add(drawingPanel, BorderLayout.CENTER);
		this.add(shapePanel, BorderLayout.NORTH);
		this.add(colorPanel, BorderLayout.WEST);
	}

	// Simple method to reduce repetition
	private void buildPanel(JPanel jp, String[] buttonList)
	{
		ButtonGroup buttonGroup = new ButtonGroup();
		for (String elem : buttonList)
		{
			JRadioButton tempRadio = new JRadioButton(elem);
			tempRadio.addActionListener(this);
			buttonGroup.add(tempRadio);
			jp.add(tempRadio);
		}
	}

	public static void main(String[] args) 
	{
		DrawingProg2 dp = new DrawingProg2();
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) 
	{
		// I wanted my labels capitalized, so compensate with toLowerCase()
		// 	Not really necessary in this case, but this way I don't have to worry
		// about casing issues.
		String action = actionEvent.getActionCommand().toLowerCase();

		switch(action)
		{
		case "red":
			drawingPanel.drawing.setColor(Color.red);
			break;
		case "green":
			drawingPanel.drawing.setColor(Color.green);
			break;
		case "blue":
			drawingPanel.drawing.setColor(Color.blue);
			break;
		case "filled":
			drawingPanel.drawing.setFilled(filled.isSelected());
			break;
		case "rectangle":
			drawingPanel.drawing.setDrawType(DrawType.rectangle);
			filled.setVisible(true);
			break;
		case "oval":
			drawingPanel.drawing.setDrawType(DrawType.oval);
			filled.setVisible(true);
			break;
		case "line":
			drawingPanel.drawing.setDrawType(DrawType.line);
			filled.setVisible(false);
			break;
		case "scribble":
			drawingPanel.drawing.setDrawType(DrawType.scribble);
			filled.setVisible(false);
			break;
		case "polygon":
			drawingPanel.drawing.setDrawType(DrawType.polygon);
			filled.setVisible(true);
			break;
		}
	}
}
