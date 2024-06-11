package startMenu;

/**
 * Public class for actions related to the loading the screen when opening the app.
 */
public class LoadingScreen {
    private String text;

    /**
     * Public constructor that doesn't request any parameters.
     */
    public LoadingScreen() {
        this.text = "";
    }

    /**
     * Public constructor with text parameter.
     * @param text the text that will be displayed.
     */
    public LoadingScreen(String text) {
        this.text = text;
    }

    /**
     * Display the window with the text attribute.
     * Since it's full terminal, we use a simple animation that prints each character every 75 milliseconds.
     */
    public void displayWindow() {
        try{
            // This is to display the loading screen like an animation.
            for(int i = 0; i < text.length(); i++) {
                System.out.print(text.charAt(i));
                Thread.sleep(75);
            }
            System.out.println("");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
}
