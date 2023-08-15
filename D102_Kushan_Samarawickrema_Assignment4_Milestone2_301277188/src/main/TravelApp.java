package main;

import javax.swing.JFrame;

public class TravelApp extends JFrame {

	public TravelApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TravelPanel bpnl = new TravelPanel(this);
		this.add(bpnl);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new TravelApp("Build your Itinerary");

	}
}
