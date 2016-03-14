package calculator;

import java.awt.Color;

public class CalcButtonData
{
	String label = null;
	Color color = null;
	
	private CalcButtonData() {}
	
	public CalcButtonData(String lbl, Color clr)
	{
		this.label = lbl;
		this.color = clr;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public Color getColor()
	{
		return color;
	}
}