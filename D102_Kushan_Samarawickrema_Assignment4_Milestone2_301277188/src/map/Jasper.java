package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

import processing.core.PVector;

public class Jasper extends Destination {

	public Jasper(PVector loc, Dimension pnlSize) {
		super(loc, pnlSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
        return "Jasper"; // Return the name "Vancouver"

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocations(List<String> locations) {
		// TODO Auto-generated method stub
		
	}

	
    @Override
    public void draw(Graphics2D g) {
        super.draw(g); // Call the draw method of the superclass (Location) to draw the circle marker
        g.setColor(Color.BLACK);
        drawName(g);
        
    }



	@Override
	protected String Description() {
		// TODO Auto-generated method stub
		return "\n"+
				"Jasper, located in the heart of the Canadian Rockies, is a breathtaking destination known for its stunning natural landscapes, abundant wildlife, and outdoor adventure opportunities such as hiking, skiing, and white-water rafting.";
	}


}
