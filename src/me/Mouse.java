package me;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinUser.INPUT;

public abstract class Mouse {

    //events
    private static final int Move = 1;
    private static final int LeftDown = 2;
    private static final int LeftUp = 4;
    private static final int RightDown = 8;
    private static final int RightUp = 16;
    private static final int MiddleDown = 32;
    private static final int MiddleUp = 64;

    public static void doMouseAction(int x, int y,int actionChoice) {

        //1 move, 2 leftclick , 3 rightclick, 4 middleclick

        switch(actionChoice){
            default:
               //nothing System.out.println("nothing");
                break;
            case 1:
                doAction(x, y, Move);
                break;
            case 2:
                doAction(x, y, LeftDown);
                doAction(x, y, LeftUp);
                break;
            case 3:
                doAction(x, y, RightDown);
                doAction(x, y, RightUp);
                break;
            case 4:
                doAction(x, y, MiddleDown);
                doAction(x, y, MiddleUp);
                break;
            case 5:
                doAction(x,y,LeftDown);
                break;
            case 6:
                doAction(x,y,LeftUp);
                break;
            case 7:
                doAction(x,y,RightDown);
                break;
            case 8:
                doAction(x,y,RightUp);
                break;
            case 9:
                doAction(x,y,MiddleDown);
                break;
            case 10:
                doAction(x,y,MiddleUp);
                break;
        }
    }

    public static void doOtherMouseAction(int x , int y, int event){
        doAction(x,y,event);
    }


    public static void doAction(int x, int y, int flags) {
        INPUT in = new INPUT();
        in.type = new DWORD(0L);
        in.input.setType("mi");
        if (x != -1) {
            in.input.mi.dx = new LONG(x);
        }

        if (y != -1) {
            in.input.mi.dy = new LONG(y);
        }

        in.input.mi.time = new DWORD(0L);
        in.input.mi.dwExtraInfo = new ULONG_PTR(0L);
        in.input.mi.dwFlags = new DWORD(flags);
        User32.INSTANCE.SendInput(new DWORD(1L), new INPUT[]{in}, in.size());
    }
}