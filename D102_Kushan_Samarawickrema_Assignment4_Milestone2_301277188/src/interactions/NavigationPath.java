package interactions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import map.Location;

public class NavigationPath {
    private List<Location> locations;

    public NavigationPath(List<Location> locations) {
        this.locations = locations;
    }

    public void draw(Graphics2D g2) {
        // Define the path's appearance
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(3)); // thickness of the path

        // Iterate through the locations in the order they are to be visited
        for (int i = 0; i < locations.size() - 1; i++) {
            Location start = locations.get(i);
            Location end = locations.get(i + 1);

            // Get the coordinates of the start and end points
            int x1 = (int) start.getPosition().x+6;
            int y1 = (int) start.getPosition().y+12;
            int x2 = (int) end.getPosition().x+6;
            int y2 = (int) end.getPosition().y+12;

            // Draw a line between the points
            g2.drawLine(x1, y1, x2, y2);
        }
    }
}
