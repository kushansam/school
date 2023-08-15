package patterns;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FractalTexture {

    private int width, height;
    private BufferedImage texture;

    public FractalTexture(int width, int height) {
        this.width = width;
        this.height = height;
        this.texture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = texture.createGraphics();
        g2.setColor(Color.BLACK);
        generateFractal(g2, 0, 0, width, height, 5); // Adjust the last parameter to change the recursion depth
        g2.dispose();
    }

    private void generateFractal(Graphics2D g2, int x, int y, int w, int h, int depth) {
        if (depth == 0) {
            g2.fillRect(x, y, w, h);
            return;
        }

        int midX = x + w / 2;
        int midY = y + h / 2;
        int quarterW = w / 4;
        int quarterH = h / 4;

        // Recursive calls for sub-regions
        generateFractal(g2, x + quarterW, y + quarterH, quarterW, quarterH, depth - 1);
        generateFractal(g2, midX - quarterW, y + quarterH, quarterW, quarterH, depth - 1);
        generateFractal(g2, x + quarterW, midY - quarterH, quarterW, quarterH, depth - 1);
        generateFractal(g2, midX - quarterW, midY - quarterH, quarterW, quarterH, depth - 1);
    }

    public BufferedImage getTexture() {
        return texture;
    }
}
