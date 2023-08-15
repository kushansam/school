package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import data.DataEntity;

import interactions.Textbox;

import processing.core.PVector;

public abstract class Location implements DataEntity {

	protected PVector loc;
	protected float scale;

	protected BufferedImage img;

	// protected ArrayList<Activity> actvities;

	protected Ellipse2D.Double marker;

	protected Area bbox;

	private float size = 10;

	public final int NEUTRAL = 1;
	public final int HOVERING = 2;
	public final int CLICKED = 3;
	public final int ADDED = 4;

	public int state = NEUTRAL;

	private Textbox textbox;

	private Dimension dim;

	public Location(PVector loc, Dimension pnlSize) {
		this.loc = loc;
		this.scale = 2.0f; // Set an initial value for scale (you can change this as needed)
		dim = pnlSize;
		bbox = new Area(); // Initialize bbox before adding the area

		textbox = new Textbox(loc, Description(), 125, dim);

		// this.scale = scale;

		setShapeAttributes();

	}

	public void draw(Graphics2D g) {
		AffineTransform af = g.getTransform();

		// Translate to the creature's center
		g.translate(loc.x, loc.y);

		if (state == ADDED) {
			// scale
			g.scale(scale / 2, scale / 2);

			// marker
			g.setColor(Color.BLUE);
			g.fill(marker);

		}

		else {

			// scale
			g.scale(scale, scale);

			// marker
			g.setColor(Color.RED);
			g.fill(marker);
		}

		g.setTransform(af);
		
		
	}

	public void update() {

	}

	protected void setShapeAttributes() {
		marker = new Ellipse2D.Double(0, 0, size, size);

	}

	protected Shape getShapeOutline() {
		bbox.add(new Area(marker));

		AffineTransform at = new AffineTransform();

		at.translate(loc.x, loc.y);

		// scale
		at.scale(scale, scale);

		return at.createTransformedShape(bbox);
	}

	public void setScale(float newScale) {
		scale = newScale;
	}

	// Change the return type to boolean
	public void mouseHover(MouseEvent e) {
		// Get the outline shape for the location
		Shape outlineShape = getShapeOutline();

		// Check if the mouse position (e.getPoint()) is contained within the outline
		// shape

		if (outlineShape.contains(e.getPoint())) {
			scale = 4;
			state = HOVERING;
		}

		else {

			if (state ==HOVERING) {
					scale = 2;
					state = NEUTRAL;
				}
				
				
		}

	}

	public void mouseClicked(MouseEvent e) {
		state = CLICKED;
	}

	public boolean mouseClickedOnButton(MouseEvent e) {
		if (textbox.buttonClicked(e.getPoint())) {			
			return true;
		
		} else			
			return false;

	}

	protected void drawName(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 16));

		if (state == NEUTRAL)
			g.drawString(getName(), loc.x, loc.y);

		else if (state == HOVERING)
			g.drawString(getName(), loc.x, loc.y);

		else if (state == CLICKED) {
			g.setFont(new Font("Arial", Font.PLAIN, 12));
			textbox.draw(g);
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.drawString(getName(), loc.x, loc.y - textbox.titleTranslate());
		}

		else if (state == ADDED) {
			g.drawString(getName(), loc.x, loc.y);

		}

	}

	// for checking if mouse is within bounds when clicking
	public boolean isWithinBounds(Point p) {
		return getShapeOutline().contains(p) || (state == CLICKED && textbox.isWithinBounds(p));
	}

	public void resetState() {
		state = NEUTRAL;
		scale = 2;
	}
	
	public void addedToItinerary() {
		state = ADDED;
	}
	
	public PVector getPosition() {
		return loc;
		
	}

	protected abstract String Description();

}
