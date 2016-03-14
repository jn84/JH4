package calculator;

import java.awt.Color;

import javax.swing.JButton;

public class CalculatorButton extends JButton
{
	public CalculatorButton(CalcButtonData cbd)
	{
		super(cbd.getLabel());
		this.setBackground(cbd.getColor());
	}
}
