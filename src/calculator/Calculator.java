package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator extends JFrame implements ActionListener
{
	private final Font FONT = new Font("Serif", Font.BOLD, 64);
	private final Dimension DIMEN = new Dimension(800, 800);
	
	private JPanel buttonPanel = null;
	private JTextField numericTextField = null;
	private JButton[] calcButtons = null;
	private SimpleMath math = new SimpleMath();
	
		
	public Calculator()
	{
		super("Calcuator Program #4");
		this.setSize(DIMEN);
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		constructButtons();

		numericTextField = new JTextField();
		numericTextField.setBackground(Color.YELLOW);
		numericTextField.setEditable(false);
		numericTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		numericTextField.setFont(FONT);
		this.add(numericTextField, BorderLayout.NORTH);
		
		buttonPanel = new JPanel(new GridLayout(4, 4, 4, 4));
		for (JButton button : calcButtons)
			buttonPanel.add(button);
		
		this.add(buttonPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		numericTextField.setText(math.getCurrentOutput());
	}
	
	
	private class CalcButtonData
	{
		String label = null;
		Color color = null;
		
		CalcButtonData(String lbl, Color clr)
		{
			this.label = lbl;
			this.color = clr;
		}
	}
	
	
	private void constructButtons()
	{
		final CalcButtonData[] buttonData = new CalcButtonData[]
				{
						new CalcButtonData("0", Color.CYAN),
						new CalcButtonData("1", Color.CYAN),
						new CalcButtonData("2", Color.CYAN),
						new CalcButtonData("3", Color.CYAN),
						new CalcButtonData("4", Color.CYAN),
						new CalcButtonData("5", Color.CYAN),
						new CalcButtonData("6", Color.CYAN),
						new CalcButtonData("7", Color.CYAN),
						new CalcButtonData("8", Color.CYAN),
						new CalcButtonData("9", Color.CYAN),
						new CalcButtonData("+", Color.RED),
						new CalcButtonData("-", Color.RED),
						new CalcButtonData("*", Color.RED),
						new CalcButtonData("/", Color.RED),
						new CalcButtonData("=", Color.RED),
						new CalcButtonData("clear", Color.RED)
				};

		calcButtons = new JButton[buttonData.length];
		
		for (int i = 0; i < buttonData.length; i++)
		{
			calcButtons[i] = new JButton(buttonData[i].label);
			calcButtons[i].setBackground(buttonData[i].color);
			calcButtons[i].setFont(FONT);
			calcButtons[i].addActionListener(this);
		}
	}
	
	public static void main(String[] args)
	{
		Calculator calculator = new Calculator();
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cmd = ((JButton)e.getSource()).getText();
		math.commitCommand(cmd, numericTextField.getText());
		numericTextField.setText(math.getCurrentOutput());
	}	
	
	private class SimpleMath
	{
		boolean isResult;
		
		CommandType lastCommandType = null;
		
		String currentOutput,
			   firstNumber, 
			   secondNumber, 
			   oper;
		
		public SimpleMath()
		{
			clearCommand();
		}
		
		public String commitCommand(String command, String value)
		{
			if (isResult && command != "clear")
				return currentOutput;
			
			switch (command)
			{
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				numberCommand(command, value);
				return currentOutput;
				
			case "+":
			case "-":
			case "/":
			case "*":
				operatorCommand(command, value);
				return currentOutput;
				
			case "=":
				solveCommand(command, value);
				return currentOutput;
				
			case "clear":
				clearCommand();
				return currentOutput;
				
			// Should never be reached. Added to quiet down compiler errors
			default:
				return "0";
			}
		}
		
		private void numberCommand(String command, String value)
		{
			// First button pressed (first or second number)
			if (lastCommandType == null || lastCommandType == CommandType.OPERATOR)
			{
				currentOutput = command;
				lastCommandType = CommandType.NUMBER;
				return;
			}
			
			// Last command was a number -> Append new command to value and update output
			if (lastCommandType == CommandType.NUMBER)
				currentOutput = value + command;
		}
		
		private void operatorCommand(String command, String value)
		{
			if (lastCommandType == null)
				return;
			
			// Can change operation type if no other commands were issued
			if (lastCommandType == CommandType.OPERATOR)
			{
				oper = command;
				return;
			}
			
			// If at least one number has been entered and we don't already have an operator
			if (lastCommandType == CommandType.NUMBER && oper == "")
			{
				// Commit the first value
				firstNumber = value;
				
				// Commit the operation
				oper = command;
				
				lastCommandType = CommandType.OPERATOR;
				
				// Reset the output to 0
				currentOutput = "0";
				return;
			}
		}
		
		private void solveCommand(String command, String value)
		{
			// No other commands have been entered, or no operator has been set
			if (lastCommandType == null || oper == "")
				return;
			
			// Commit the second value
			secondNumber = value;
			
			// Perform the actual operation
			performOperation();
			
			lastCommandType = CommandType.EQUAL;
		}
		
		// Reset everything
		private void clearCommand()
		{
			isResult = false;
			
			lastCommandType = null;

			currentOutput = "0";
			firstNumber = "0";
			secondNumber = "0";
			oper = "";
		}
		
		private void performOperation()
		{
			BigInteger tempResult = null, 
					   tempFirst = null, 
					   tempSecond = null;
			
			// Shouldn't ever throw under normal use
			try
			{
				
				tempFirst = new BigInteger(firstNumber);
				tempSecond = new BigInteger(secondNumber);
			}
			catch (NumberFormatException e)
			{
				// Debug code
				System.out.println("Error converting String to int (performOperation)");
			}

			switch (oper)
			{
			case "+":
				tempResult = tempFirst.add(tempSecond); 
				break;
			case "-":
				tempResult = tempFirst.subtract(tempSecond);
				break;
			case "*":
				tempResult = tempFirst.multiply(tempSecond);
				break;
			case "/":
				if (tempSecond.compareTo(BigInteger.ZERO) == 0)
				{
					currentOutput = "ERROR: Divide by 0";
					return;
				}
				tempResult = tempFirst.divide(tempSecond);
				break;
			}
			isResult = true;
			currentOutput = tempResult.toString();
		}
		
		public String getCurrentOutput()
		{
			return trimZeros(currentOutput);
		}
		
		// Dumb little trick to clear out any excess leading zeros
		private String trimZeros(String input)
		{
			// Should never throw, but who knows. Keep everything going if it does.
			try	
			{
				return new BigInteger(input).toString();
			}
			catch (Exception e) { return input; }
		}
	} // End SimpleMath
	
	private enum CommandType 
	{
		NUMBER,
		OPERATOR,
		EQUAL,
		CLEAR
	};
} 
