package game.cutscene;

import utilities.Prompt;

/**
 * Public class to cutscene related actions.
 */
public class Cutscene {
    private String text;
    private String code;
    /**
     * Public constructor without parameters.
     */
    public Cutscene(){
        this.text = "";
        this.code = "";
    }
    /**
     * Public constructor with parameters.
     * @param text the text that will be displayed in the cutscene.
     * @param code the code to identify the cutscene (doesn't need to be unique).
     */
    public Cutscene(String text, String code){
        this.text = text;
        this.code = code;
    }
    /**
     * Display the cutscene.
     * The player must click ENTER in order to continue.
     */
    public void displayCutscene(){
        System.out.println(text);
        Prompt.promptEnterToContinue();
    }
    /**
     * Get the text that will be displayed in the cutscene.
     * @return the text that will be displayed.
     */
    public String getText() {return text;}
    /**
     * Set the text that will be displayed in the cutscene.
     * @param text the text that will be displayed in the cutscene.
     */
    public void setText(String text) {this.text = text;}
    /**
     * Get the code that identifies the cutscene.
     * @return the code that identifies the cutscene.
     */
    public String getCode() {return code;}
    /**
     * Set the code that identifies the cutscene (doesn't need to be unique).
     * @param code the code to identify the cutscene.
     */
    public void setCode(String code) {this.code = code;}
}
