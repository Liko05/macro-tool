package me;

import java.awt.*;
import java.util.ArrayList;

public class MouseMacro implements IMacros {

    private ArrayList<Vector2> locations;
    private ArrayList<Integer> mouseActions;

    //todo add mouse click actions
    public MouseMacro(){
        locations = new ArrayList<Vector2>();
        mouseActions = new ArrayList<>();
    }


    public void recordMacro() throws InterruptedException {
        while (true) {
            if (Keyboard.isKeyDown(18) && Keyboard.isKeyDown(35)) {
                break;
            }
            Point p = MouseInfo.getPointerInfo().getLocation();
            int x = p.x;
            int y = p.y;
            locations.add(new Vector2(x, y));
            if(Keyboard.isKeyDown(1)){
                mouseActions.add(5);
            }else if(Keyboard.isKeyDown(2)){
                mouseActions.add(7);
            }else if(Keyboard.isKeyDown(4)){
                mouseActions.add(9);
            }else
                mouseActions.add(0);

          Thread.sleep(10);

        }
    }

    public void replayMacro(int delay) throws InterruptedException, IndexOutOfBoundsException {
        int currentlyPressed = 0;
        for (int i = 0; i < locations.size(); i++) {

            //current position of mouse
            Point p = MouseInfo.getPointerInfo().getLocation();
            int xC = p.x;
            int yC = p.y;
            //target position, we need to move by: target location - current location
            int xT = locations.get(i).getX();
            int yT = locations.get(i).getY();
            Mouse.doMouseAction(xT - xC, yT - yC, 1);
            if(currentlyPressed != 0 && currentlyPressed != mouseActions.get(i)){
                //releases previously held button
                try {
                    Mouse.doMouseAction(0, 0,currentlyPressed + 1);
                    currentlyPressed = mouseActions.get(i);
                }catch(ArrayIndexOutOfBoundsException o){

                }
                Mouse.doMouseAction(0,0, mouseActions.get(i));
            }else{
                Mouse.doMouseAction(0,0,mouseActions.get(i));
            }

            Thread.sleep(delay);
        }
    }

}