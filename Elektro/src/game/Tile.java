package game;
import java.awt.image.BufferedImage;

/**
 * The Tile class represents a tile in the game.
 * Each tile can have an image and may or may not have collision.
 */
public class Tile {
    private BufferedImage image;
    private boolean colission = false;

    /**
     * Default constructor for the Tile class.
     */
    public Tile() {
    }

    /**
     * Gets the image of the tile.
     *
     * @return The image of the tile.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets the image of the tile.
     *
     * @param image The new image of the tile.
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Checks if the tile have collision.
     *
     * @return true if the tile is collidable, false otherwise.
     */
    public boolean isColission() {
        return colission;
    }

    /**
     * Sets the collidable status of the tile.
     *
     * @param colission true to make the tile collidable, false otherwise.
     */
    public void setColission(boolean colission) {
        this.colission = colission;
    }
}
