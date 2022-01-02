package me;

import java.util.ArrayList;

public class KeyboardMacro implements IMacros{


   private final ArrayList<Integer> strokes = new ArrayList<Integer>();
    private final ArrayList<Integer> combKeys = new ArrayList<Integer>();

//todo catch special characters and capitals using keys like shift

    public KeyboardMacro(){

    }

    public void recordMacro() throws InterruptedException {
        while(true){
            if(Keyboard.isKeyDown(18) && Keyboard.isKeyDown(35)){
                break;
            }
            for (int i = 0; i < 255; i++) {
                if (Keyboard.isKeyDown(i)) {
                    // debug System.out.println("Key pressed: " + i);
                    strokes.add(i);
                    Thread.sleep(120);
                }
            }
        }
    }

    public void replayMacro(int delay) throws InterruptedException{
        for(int i = 0; i < strokes.size();i++){

            Keyboard.pressKey(strokes.get(i));
            Thread.sleep(delay);
        }
    }
}
