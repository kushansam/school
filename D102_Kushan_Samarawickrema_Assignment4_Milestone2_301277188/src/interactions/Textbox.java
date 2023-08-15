package interactions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import processing.core.PVector;

public class Textbox {

	private PVector loc;
	private String description;
	private int width;
	private float paragraphHeight;
	private Dimension pnlSize;
	private Button addButton;

	private int buttonHeight = 40; // Height of the button

	public Textbox(PVector loc, String description, int width, Dimension pnlSize) {

		this.loc = loc;
		this.description = description;
		this.width = width;
		this.pnlSize = pnlSize;

		// addButton.addActionListener(this);

	}

	public void draw(Graphics2D g2) {

		// Save the current transform
		AffineTransform old = g2.getTransform();

		// Translate to the location where you want to draw the text box
		g2.translate(loc.x, loc.y - distanceOverEdgeY(pnlSize, buttonHeight));

		drawTextBox(g2, description, width);

		g2.setColor(Color.BLACK);

		// split string by line breaks
		String wrappedText = wrapText(g2, description, width);
		String[] lines = wrappedText.split("\n");

		int y = 0; // start y-coordinate
		for (String line : lines) {
			g2.drawString(line, 0, y);
			y += g2.getFontMetrics().getHeight(); // Increment y-coordinate by the height of one line of text
		}

		// Initialize and draw the button at a specific position relative to the text
		// box
		Button addButton = new Button(10, (int) paragraphHeight + 30, width - 20, 40, "Add");
		addButton.setBackgroundColor(Color.WHITE);
		addButton.setTextColor(Color.BLACK);
		addButton.draw(g2);

		// Restore the original transform
		g2.setTransform(old);
	}

	// method adding "\n" to the string based on width preference also changes
	// height based on the size of paragraph
	private String wrapText(Graphics2D g, String text, int width) {
		paragraphHeight = 0; // Reset the height
		StringBuilder wrappedText = new StringBuilder();
		String[] words = text.split(" ");
		int currentWidth = 0;
		float textHeight = g.getFontMetrics().getHeight();

		for (String word : words) {
			int wordWidth = g.getFontMetrics().stringWidth(word + " ");
			if (currentWidth + wordWidth <= width) {
				wrappedText.append(word).append(" ");
				currentWidth += wordWidth;
			} else {
				wrappedText.append("\n").append(word).append(" ");
				currentWidth = wordWidth;
				paragraphHeight += textHeight; // Increment the height for each new line
			}
		}

		return wrappedText.toString();
	}

	// method for intaking the width and height and making a rectangle box
	private void drawTextBox(Graphics2D g, String text, int width) {
		float textBoxHeight = paragraphHeight + 20; // Add extra padding of 20 pixels

		g.setColor(new Color(255, 255, 255)); // Light yellow color with transparency
		g.fillRoundRect(-10, 0, width + 20, (int) textBoxHeight, 10, 10);
	}

	private float distanceOverEdgeY(Dimension dim, int buttonHeight) {
		// bottom detection
		if (loc.y + paragraphHeight + 20 + buttonHeight > dim.height) { // Add extra padding and button height
			return (loc.y + paragraphHeight + 20 + (buttonHeight + 30)) - dim.height;
		}

		return 0;
	}

	// method for the the JPanel to adjust y position of the TITLE string
	public float titleTranslate() {
		return distanceOverEdgeY(pnlSize, buttonHeight) + 10;
	}

	public boolean isWithinBounds(Point p) {
		return p.x >= loc.x && p.x <= loc.x + width && p.y >= loc.y
				&& p.y <= loc.y + paragraphHeight + 50 + distanceOverEdgeY(pnlSize, buttonHeight);
	}

	public boolean buttonClicked(Point p) {
		Button addButton = new Button(
			10 + (int) loc.x,
			(int) paragraphHeight + 30 + (int) loc.y - (int) distanceOverEdgeY(pnlSize, buttonHeight),
			width - 20,
			40,
			"Add"
		);
		if (addButton.contains(p.x, p.y)) {
			return true;
		}
		return false;
	}


}
