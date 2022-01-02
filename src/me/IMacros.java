package me;

public interface IMacros {

    public void recordMacro()  throws InterruptedException ;
    public void replayMacro(int delay) throws InterruptedException;
}
