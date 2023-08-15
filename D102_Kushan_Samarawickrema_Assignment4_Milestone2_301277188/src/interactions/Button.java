package interactions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button {
    private int x, y, width, height;
    private String label;
    private Color backgroundColor;
    private Color textColor;

    public Button(int x, int y, int width, int height, String label) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.backgroundColor = new Color(200, 200, 200);
        this.textColor = Color.BLACK;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(backgroundColor);
        g2.fillRect(x, y, width, height);

        g2.setColor(textColor);
        int labelWidth = g2.getFontMetrics().stringWidth(label);
        int labelHeight = g2.getFontMetrics().getHeight();
        g2.drawString(label, x + (width - labelWidth) / 2, y + (height + labelHeight) / 2);
    }

    public boolean contains(int x, int y) {
        Rectangle bounds = new Rectangle(this.x, this.y, width, height);
        return bounds.contains(x, y);
    }
    
    // Optional setters for customizing button colors
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    
    
}
