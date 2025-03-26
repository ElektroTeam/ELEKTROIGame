package battle.battleGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Principal panel builder with the image of the battle background
 */
public class Scenario extends JPanel {
    private BufferedImage backgroundImage;

    /**
     * public constructor for the panel
     * @param imagePath image we put on the panel
     */
    public Scenario(String imagePath){
        try{
            InputStream inputStream = getClass().getResourceAsStream(imagePath);
            backgroundImage = ImageIO.read(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        setLayout(null);
    }

    /**
     * Overwrite method {@code paintComponent} of {@code JPanel} for
     * paint an image on background in panel.
     * @param g the graphic context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
