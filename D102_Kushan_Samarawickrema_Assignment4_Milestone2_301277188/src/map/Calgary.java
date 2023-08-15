package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

import processing.core.PVector;

public class Calgary extends StartingLocation {

	public Calgary(PVector loc, Dimension pnlSize) {
		super(loc, pnlSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Calgary"; // Return the name "Vancouver"
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

		if (this.state == HOVERING) {

		}

	}

	@Override
	protected String Description() {
		// TODO Auto-generated method stub
		return "\n"
				+ "Nestled in Alberta's picturesque landscape, Calgary is a vibrant city that blends modernity with western charm, offering diverse cultural experiences and captivating views of the nearby Rocky Mountains.";
	}

}
