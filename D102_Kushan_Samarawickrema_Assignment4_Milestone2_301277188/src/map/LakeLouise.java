package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;

import processing.core.PVector;

public class LakeLouise extends Destination {

	public LakeLouise(PVector loc, Dimension pnlSize) {
		super(loc,pnlSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Lake Louise";
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
				"A beautiful glacial lake in Banff National Park, known for its turquoise waters and scenic mountain views.";
	}



}
