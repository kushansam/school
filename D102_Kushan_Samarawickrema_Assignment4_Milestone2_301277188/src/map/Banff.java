package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

import processing.core.PVector;

public class Banff extends Destination {

	public Banff(PVector loc, Dimension pnlSize) {
		super(loc,pnlSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Banff";
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

    public void draw(Graphics2D g) {
        super.draw(g); // Call the draw method of the superclass (Location) to draw the circle marker
        g.setColor(Color.BLACK);
        drawName(g);   
    }

	@Override
	protected String Description() {
		// TODO Auto-generated method stub
		return "\n"+
				"Banff is a picturesque mountain town renowned for its stunning natural beauty, encompassing soaring peaks, turquoise lakes, and abundant wildlife, making it a paradise for outdoor enthusiasts and nature lovers";
		
	}

}
