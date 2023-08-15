package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.List;

import processing.core.PVector;

public class Vancouver extends StartingLocation {

	public Vancouver(PVector loc, Dimension pnlSize) {
		super(loc, pnlSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Vancouver"; // Return the name "Vancouver"

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g); // Call the draw method of the superclass (Location) to draw the circle marker
		g.setColor(Color.BLACK);
		drawName(g);

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
	protected String Description() {
		// TODO Auto-generated method stub
		return "\n"
				+ "Bordered by the Pacific Ocean and the majestic North Shore Mountains, this metropolis offers a perfect blend of urban sophistication and natural beauty, boasting a thriving arts scene, diverse culinary delights, and opportunities for outdoor adventures.";
	}

}
