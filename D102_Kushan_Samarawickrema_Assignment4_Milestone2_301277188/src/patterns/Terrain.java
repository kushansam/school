package patterns;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import processing.core.PApplet;

public class Terrain {
    private int width, height;
    private BufferedImage terrainImage;
    private PApplet pa;

    public Terrain(int width, int height) {
        this.width = width;
        this.height = height;
        this.pa = new PApplet();
        this.terrainImage = generateTerrainImage();
    }

    private BufferedImage generateTerrainImage() {
        float[][] terrain = new float[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float noiseValue = pa.noise(x * 0.01f, y * 0.01f);
                terrain[x][y] = noiseValue;
            }
        }

        BufferedImage terrainImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = terrainImage.createGraphics();
        int alpha = 255;

        Color waterColor = new Color(0, 0, 200, alpha);
        Color grassColor = new Color(34, 139, 34, alpha);
        Color mountainColor = new Color(139, 69, 19, alpha);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float noiseValue = terrain[x][y];
                Color color;
                if (noiseValue < 0.3) {
                    color = interpolateColors(waterColor, grassColor, noiseValue / 0.3f);
                } else if (noiseValue < 0.6) {
                    color = interpolateColors(grassColor, mountainColor, (noiseValue - 0.3f) / 0.3f);
                } else {
                    color = mountainColor;
                }
                g2.setColor(color);
                g2.fillRect(x, y, 1, 1);
            }
        }

        g2.dispose();
        return terrainImage;
    }

    public void drawTerrain(Graphics2D g2, float transparency) {
        int alpha = (int) (transparency * 255);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha / 255f));
        g2.drawImage(terrainImage, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    private Color interpolateColors(Color c1, Color c2, float ratio) {
        int red = (int) (c1.getRed() * (1 - ratio) + c2.getRed() * ratio);
        int green = (int) (c1.getGreen() * (1 - ratio) + c2.getGreen() * ratio);
        int blue = (int) (c1.getBlue() * (1 - ratio) + c2.getBlue() * ratio);
        int alpha = (int) (c1.getAlpha() * (1 - ratio) + c2.getAlpha() * ratio);
        return new Color(red, green, blue, alpha);
    }
}
