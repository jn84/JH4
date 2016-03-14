package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet.FontAttribute;
import javax.swing.text.StyleConstants.FontConstants;

import calculator.CalcButtonData;

public class Calculator extends JFrame implements ActionListener
{
	private final Font globalFont = new Font("Serif", Font.BOLD, 64);
	
	private JPanel buttonPanel = null;
	private JTextField numericTextField = null;
	private CalculatorButton[] calcButtons = null;
	private SimpleMath math = new SimpleMath();
	
		
	public Calculator()
	{
		super("Calcuator Program #4");
		this.setSize(800, 800);
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		constructButtons();

		numericTextField = new JTextField();
		numericTextField.setBackground(Color.YELLOW);
		numericTextField.setEditable(false);
		numericTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		numericTextField.setFont(globalFont);
		this.add(numericTextField, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 4, 4));
		for (CalculatorButton button : calcButtons)
			buttonPanel.add(button);
		
		this.add(buttonPanel, BorderLayout.CENTER);
		
		setVisible(true);
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

		calcButtons = new CalculatorButton[buttonData.length];
		
		for (int i = 0; i < buttonData.length; i++)
		{
			calcButtons[i] = new CalculatorButton(buttonData[i]);
			calcButtons[i].setFont(globalFont);
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
		String cmd = ((CalculatorButton)e.getSource()).getText();
		math.commitCommand(cmd);
	}	
	
	private class SimpleMath
	{
		CommandType lastCommandType = null;
		String lastCommand = "";
		String lastResult = "";
		String currentOutput = "0";
		
		int firstNumber = 0;
		int secondNumber = 0;
		String oper = "";
		
		public SimpleMath()	{}
		
		public String commitCommand(String command, int value)
		{
			lastCommand = command;
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
				System.out.println("Entered number: " + command);
				numberCommand();
				lastCommandType = CommandType.NUMBER;
				return currentOutput;
			case "+":
			case "-":
			case "/":
			case "*":
				System.out.println("Entered Operator: " + command);
				operatorCommand();
				lastCommandType = CommandType.OPERATOR;
				return currentOutput;
			case "=":
				System.out.println("Asked for Result ( = )");
				equalityCommand();
				lastCommandType = CommandType.EQUAL;
				return currentOutput;
			case "clear":
				System.out.println("Cleared everything");
				clearCommand();
				return currentOutput;
			default:
				return "0";
			}
		}
		
		private void numberCommand(int value)
		{
			if (lastCommandType == null)
			{
				
			}
			
			if (lastCommandType == CommandType.NUMBER)
			{
				
			}
			
			if ()
		}
		
		private void operatorCommand(int value)
		{
			
		}
		
		private void equalityCommand(int value)
		{
			
		}
		
		private void clearCommand()
		{
			lastCommandType = null;
			lastCommand = "";
			lastResult = "";
			currentOutput = "0";
			
			firstNumber = 0;
			secondNumber = 0;
			oper = null;
		}
		
		
		public String getLastResult()
		{
			return lastResult;
		}
	}
	
	private enum CommandType 
	{
		NUMBER,
		OPERATOR,
		EQUAL,
		CLEAR
	};
} 
