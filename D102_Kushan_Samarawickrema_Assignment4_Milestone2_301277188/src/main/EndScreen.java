package main;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

import interactions.Button;




public class EndScreen {
	
    private Button endButton;
    private Dimension pnlSize;
    
    private int buttonWidth = 100;
    private int buttonHeight = 50;

    public EndScreen(Dimension pnlSize) {
        // Initialize the start button and other elements
    	this.pnlSize=pnlSize;
    	initializeEndButton();
    }

    public void draw(Graphics2D g2) {
        // Draw the intro screen background or any other elements if needed

        // Set the cursive font
        Font titleFont = new Font("DialogInput", Font.BOLD, 48); // 24 is the font size
        g2.setFont(titleFont);
        

        // Draw the placeholder text
        String text = introTitle();
        
        FontMetrics metrics = g2.getFontMetrics();
        
        // Calculate the X position of the text to center it
        int textWidth = metrics.stringWidth(text);
        int x = (pnlSize.width - textWidth) / 2; 
        int y = pnlSize.height/2; // Y position of the text
        
        g2.setColor(Color.BLACK);
        g2.drawString(text, x, y);

        // Draw the start button or other elements
        Font buttonFont = new Font("DialogInput", Font.PLAIN, 24); // 24 is the font size
        g2.setFont(buttonFont);
        endButton.setBackgroundColor(Color.BLACK);
        endButton.setTextColor(Color.WHITE);
        endButton.draw(g2);
        
    }


    public boolean endButtonClicked(Point p) {
        // Check if the start button was clicked
        return endButton.contains(p.x, p.y);
    }
    
    private String introTitle(){
		return "End of Day 1... to be Continued";
    }
    
    private void initializeEndButton() {
        int buttonX = (pnlSize.width - buttonWidth) / 2;
        int buttonY = (pnlSize.height / 2) + (buttonHeight/2);
        this.endButton = new Button(buttonX, buttonY, buttonWidth, buttonHeight, "Start");
    }
    
    
    
    
    
}

