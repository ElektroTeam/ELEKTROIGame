package utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * This class handles loading a custom font from a file and applying specified size and style.
 */

public class arcadeFont {
    private String fontFilePath;
    private float fontSize;
    private int fontStyle;
    /**
     * Constructs an arcadeFont with the specified file path, font size, and font style.
     *
     * @param fontFilePath the path to the font file
     * @param fontSize the size of the font
     * @param fontStyle the style of the font
     */
    public arcadeFont(String fontFilePath, float fontSize, int fontStyle) {
        this.fontFilePath = fontFilePath;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
    }
    /**
     * Creates and returns a Font object based on the specified file path, size, and style.
     *
     * @return the custom Font object, or null if there was an error loading the font
     */
    public Font getFont() {
        Font customFont = null;
        try {
            File fontFile = new File(fontFilePath);
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            customFont = customFont.deriveFont(fontSize);
            customFont = customFont.deriveFont(fontStyle);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont;
    }
}
