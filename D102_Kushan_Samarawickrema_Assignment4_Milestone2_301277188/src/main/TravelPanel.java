package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.Timer;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageLoader;
import util.MinimHelper;
import processing.core.PVector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;

import map.*;

import patterns.Terrain;
import patterns.FractalTexture;

import interactions.NavigationPath;

import main.IntroScreen;
import main.EndScreen;

public class TravelPanel extends JPanel implements ActionListener {
	public static int W_WIDTH = 1050;
	public static int W_HEIGHT = 600;
	public Dimension pnlSize = new Dimension(W_WIDTH, W_HEIGHT);

	private Timer timer;
	private java.util.Timer utilTimer;

	private Background bgImg;
	private Background startImg;

	private IntroScreen introScreen;
	private EndScreen endScreen;
	private TitleScreen transition;

	ArrayList<Location> locations = new ArrayList<>();
	ArrayList<Location> addedLocations = new ArrayList<>();

	// starting/ending
	private StartingLocation vancouver;
	private StartingLocation calgary;

	// destinations
	private Destination jasper;
	private Destination banff;
	private Destination lakeLouise;

	// states
	protected final int START_SCREEN = 0;
	protected final int DAY_1_START = 1;
	protected final int DAY_1_END = 2;
	//protected final int DAY_2_INTRO = 3;
	protected final int DAY_2 = 4;
	//protected final int DAY_3_INTRO = 5;
	protected final int DAY_3 = 6;
	//protected final int DAY_4_INTRO = 7;
	protected final int DAY_4 = 8;
	protected final int FINISH_TOUR = 9;
	protected final int END_SCREEN = 8;

	protected int state = START_SCREEN;

	private Location currentDisplayLocation = null; // index the Location that is displaying its Textbox

	private Terrain terrain;
	// private FractalTexture texture;

	private NullSelection nullSelection;

	private NavigationPath path;
	
	private Minim minim;
	private AudioPlayer bgMusic, click, nature;
	

	TravelPanel(JFrame frame) {
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(pnlSize));

		introScreen = new IntroScreen(pnlSize);
		endScreen = new EndScreen(pnlSize);

		bgImg = new Background("assets/map.png");
		startImg = new Background("assets/Rockies.jpg");
		vancouver = new Vancouver(new PVector(285, 505), pnlSize);
		calgary = new Calgary(new PVector(905, 315), pnlSize);
		jasper = new Jasper(new PVector(635, 110), pnlSize);
		banff = new Banff(new PVector(800, 300), pnlSize);
		lakeLouise = new LakeLouise(new PVector(776, 260), pnlSize);

		locations.add(vancouver);
		locations.add(calgary);
		locations.add(jasper);
		locations.add(banff);
		locations.add(lakeLouise);

		terrain = new Terrain(W_WIDTH, W_HEIGHT); // Create the terrain

		// texture = new FractalTexture(W_WIDTH, W_HEIGHT);

		path = new NavigationPath(addedLocations); // update path to include added locations

		utilTimer = new java.util.Timer(); // Initialize utilTimer
		
		System.out.println(new File(".").getAbsolutePath());

		
		minim = new Minim(new MinimHelper());
		
		bgMusic = minim.loadFile("ES_Mother Nature - Feras Charestan.mp3");
		bgMusic.setGain(bgMusic.getGain() - 15);
		bgMusic.loop();
		
		nature = minim.loadFile("ES_Nature Forest Swamp - SFX Producer.mp3");
		nature.setGain(nature.getGain() - 8);
		nature.loop();


		click = minim.loadFile("tap.mp3");
		click.setGain(click.getGain() - 8);


		timer = new Timer(30, this);
		timer.start();
		addMouseListener(new MyMouseAdapter());
		addMouseMotionListener(new MyMouseMotionAdapter());
	}

	public void paintComponent(Graphics g) {
		System.out.println(state);

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		bgImg.drawBg(g2);
		terrain.drawTerrain(g2, 0.1f); // Adjust transparency as needed

		float alpha = 0.5f; // Adjust alpha as needed

		// g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		// g2.drawImage(texture.getTexture(), 0, 0, null);
		// g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

		path.draw(g2); // draw navigation path

		if (state == START_SCREEN) {
			// startImg.drawBg(g2);

			introScreen.draw(g2);

		}

		else {

			if (state == DAY_1_START) {
				for (Location location : locations) {
					if (location instanceof StartingLocation) {
						location.draw(g2);
					}
				}

			}

			else if (state == DAY_1_END) {
				for (Location location : locations) {
					location.draw(g2);

				}
			}

			else if (state == DAY_2) {
				TitleScreen transition = new TitleScreen(pnlSize, "Day 2");
				transition.draw(g2);
				for (Location location : locations) {
					location.draw(g2);

				}

			}


			else if (state == DAY_3) {
				TitleScreen transition = new TitleScreen(pnlSize, "Day 3");
				transition.draw(g2);
				for (Location location : locations) {
					location.draw(g2);

				}
			}

		

			else if (state == DAY_4) {
				TitleScreen transition = new TitleScreen(pnlSize, "Day 4");
				transition.draw(g2);
				for (Location location : locations) {
					location.draw(g2);

				}

			}

			else if (state == FINISH_TOUR) {
				for (Location location : locations) {
					location.draw(g2);

				}

			}

			else {
				endScreen.draw(g2);
			}

		}

		if (nullSelection != null) {
			nullSelection.draw(g2);

		}

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// System.out.println("Current state: " + state); // Print current state

		repaint();
	}

	private class Background {

		private BufferedImage img;

		private Background(String file) {
			img = ImageLoader.loadImage(file);
		}

		public void drawBg(Graphics2D g2) {
			int imgWidth = img.getWidth();
			int imgHeight = img.getHeight();
			double scaleX = (double) W_WIDTH / imgWidth;
			double scaleY = (double) W_HEIGHT / imgHeight;
			double scale = Math.min(scaleX, scaleY);

			int drawWidth = (int) (scale * imgWidth);
			int drawHeight = (int) (scale * imgHeight);
			int x = (W_WIDTH - drawWidth) / 2;
			int y = (W_HEIGHT - drawHeight) / 2;

			g2.drawImage(img, x, y, drawWidth, drawHeight, null);
		}

	}

	private class MyMouseAdapter extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			click.play(0);
			
			

			if (state == START_SCREEN) {
				if (introScreen.startButtonClicked(e.getPoint())) {
					click.play();
					state = DAY_1_START;
				}
			}

			else {
				

				// Process locations to check if one was clicked to open the textbox
				for (Location location : locations) {
					if (location.state == location.HOVERING) {
						currentDisplayLocation = location;
						location.mouseClicked(e);
						click.play();
						break; // Exit loop after finding the clicked location
					}
				}

				// for handling if user clicks once textbox has been opened
				if (currentDisplayLocation != null) {

					// If user mouse is not within bounds of location, then location will reset
					// state to Neutral
					if (!currentDisplayLocation.isWithinBounds(e.getPoint())) {
						// if current location is in clicked state
						if (currentDisplayLocation.state == currentDisplayLocation.CLICKED) {
							currentDisplayLocation.resetState(); // reset to neutral
							currentDisplayLocation = null;
							return;
						}

						/*
						 * else if (currentDisplayLocation.state == currentDisplayLocation.ADDED) {
						 * currentDisplayLocation.addedToItinerary(); // state = ADDED
						 * 
						 * return; }
						 */

					}

				}

				// for checking mouseClicked
				// Check if the button inside the textbox was clicked
				try {
					if (currentDisplayLocation.mouseClickedOnButton(e)) {
						

						// change state to DAY_1_END
						currentDisplayLocation.addedToItinerary();// method for changing state of location
						addedLocations.add(currentDisplayLocation); // add to list
						System.out.println(addedLocations);

						if (currentDisplayLocation instanceof StartingLocation) {
							currentDisplayLocation = null;
							state = DAY_1_END;
							return; // Exit after handling the button click

							// During Day_1_END state, if add button clicked
						}

						else {
							currentDisplayLocation = null;

							if (state == DAY_1_END) {
								state = DAY_2;
								return;

							}

							else if (state == DAY_2) {
								state = DAY_3;
								return;
							}

							else if (state == DAY_3) {
								state = DAY_4;
								return;
							}

							else if (state == DAY_4) {
								state = FINISH_TOUR;
								return;
							}

							else
								state = END_SCREEN;
						}

					}

				}

				catch (NullPointerException ex) {
					nullSelection = new NullSelection("Click on a location");

				}

			}

		}

	}

	private class MyMouseMotionAdapter extends MouseMotionAdapter {

		public void mouseMoved(MouseEvent e) {
			scaleOnHover(e); // scale if hovering over Location Object
		}

		public void scaleOnHover(MouseEvent e) {

			if (currentDisplayLocation != null) {
				return;
			}

			if (state == DAY_1_START) {

				for (Location location : locations) {
					if (location instanceof StartingLocation) {
						location.mouseHover(e);
					}
				}
			}

			else if (state == DAY_4) {
				for (Location location : locations) {
					if (location instanceof StartingLocation) {
						location.resetState();
						location.mouseHover(e);
					}
				}

			}

			else {
				for (Location location : locations) {
					if (location instanceof Destination) {
						if (location.state == location.NEUTRAL || location.state == location.HOVERING) {
							location.mouseHover(e);

						}
					}

				}

			}

		}

	}

	private class NullSelection {
		String string;
		private int width;
		private int height;

		private int stringWidth;
		private int stringHeight;

		private boolean shouldDraw = true; // Start with true since we initiate the timer in the constructor

		public NullSelection(String string) {
			this.string = string;

			// Create a TimerTask that will set shouldDraw to false after 2 seconds
			TimerTask task = new TimerTask() {
				public void run() {
					shouldDraw = false;
				}
			};

			utilTimer.schedule(task, 1500); // Schedule the task to run after 2 seconds
		}

		public void draw(Graphics2D g) {
			if (!shouldDraw)
				return; // Don't draw if shouldDraw is false

			g.setFont(new Font("Arial", Font.BOLD, 24));
			stringWidth = g.getFontMetrics().stringWidth(string);
			stringHeight = g.getFontMetrics().getHeight();

			width = stringWidth + 10;
			height = stringHeight / 3;

			g.setColor(Color.BLACK);
			g.fillRect(pnlSize.width / 2 - width / 2, pnlSize.height / 2 - 2 * height, width, height);

			g.drawString(string, pnlSize.width / 2 - stringWidth / 2, pnlSize.height / 2 - stringHeight);
		}
	}

}
