// Handles all input and passes it to the neccessary controller

package second_shape_drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel
{
    Drawing drawing = new Drawing();
    Image offScreenImage = null;
    Dimension screenDimension = null;
    
    DrawingPanel()
    {
        super();
    }
    
    public void paint(Graphics screen)
    {
    	Dimension dimen = getSize();

    	// If the buffer image doesn't exist or the screen size changed
        if (offScreenImage == null || !dimen.equals(screenDimension))
        {
        	// Create a new image of the needed size
            screenDimension = dimen;
            offScreenImage = createImage(dimen.width, dimen.height);
        }
        
        // Get a graphics object representing the buffer image
        Graphics g = offScreenImage.getGraphics();
        
    	// Fill background
    	g.setColor(Color.white);
    	g.fillRect(0, 0, dimen.width, dimen.height);
    	
    	// Draw shapes
    	drawing.draw(g);
    	
    	// Draw the buffer image to the screen
        screen.drawImage(offScreenImage, 0, 0, this);
    }
    
    // Process commands
    public boolean commandHandler(char cmd)
    {
		switch(cmd)
		{
		case 'r':
			this.drawing.setDrawType(DrawType.rectangle);
			break;
		case 'o':
			this.drawing.setDrawType(DrawType.oval);
			break;
		case 'l':
			this.drawing.setDrawType(DrawType.line);
			break;
		case 's':
			this.drawing.setDrawType(DrawType.scribble);
			break;
		case 'p':
		case 'a':
			this.drawing.setDrawType(DrawType.polygon);
			break;
		case 'q':
			return false;
		case 'f':
			this.drawing.setFilled(true);
			break;
		case 'd':
			this.drawing.setFilled(false);
			break;
		case 'b':
			this.drawing.setColor(Color.blue);
			break;
		case 'm':
			this.drawing.setColor(Color.magenta);
			break;
		case 'g':
			this.drawing.setColor(Color.green);
			break;
		default: // '?' comes here
			break;
		}
		this.repaint();
		return true;
    }
}

