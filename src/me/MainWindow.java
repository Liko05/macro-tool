package me;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JButton mouseRecorderButton;
    private JButton keyboardRecorderButton;
    private JPanel panelMain;
    private JButton btnReplay;
    private JButton btnRecord;
    private JSpinner spinner1;
    private JButton button1;
    private static boolean keyboardActive = false;
    private static boolean mouseActive = false;
    private static boolean replaying = false;
    private static boolean recording = false;
    private static KeyboardMacro kM = new KeyboardMacro();
    private static MouseMacro mM = new MouseMacro();


    public MainWindow() {
        panelMain.setBackground(new Color(199, 0, 255));
        mouseRecorderButton.setBackground(new Color(173, 161, 156));
        keyboardRecorderButton.setBackground(new Color(173, 161, 156));
        btnRecord.setBackground(new Color(173, 161, 156));
        btnReplay.setBackground(new Color(173, 161, 156));

        mouseRecorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mouseActive && keyboardActive) {
                    JOptionPane.showMessageDialog(null, "Error choose either mouse or keyboard recorder");
                } else if (!mouseActive) {
                    mouseRecorderButton.setBackground(new Color(0, 255, 47));
                    mouseActive = true;
                } else if (mouseActive && !keyboardActive) {
                    mouseRecorderButton.setBackground(new Color(173, 161, 156));
                    mouseActive = false;
                }


            }
        });

        keyboardRecorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mouseActive && !keyboardActive) {
                    JOptionPane.showMessageDialog(null, "Error choose either mouse or keyboard recorder");
                } else if (!keyboardActive) {
                    keyboardRecorderButton.setBackground(new Color(0, 255, 47));
                    keyboardActive = true;
                } else if (!mouseActive && keyboardActive) {
                    keyboardRecorderButton.setBackground(new Color(173, 161, 156));
                    keyboardActive = false;
                }

            }
        });

        btnReplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keyboardActive || mouseActive) {
                    if (!replaying) {
                        btnReplay.setBackground(new Color(0, 255, 47));
                        replaying = true;
                    } else {
                        btnReplay.setBackground(new Color(173, 161, 156));
                        replaying = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error u have to enable mouse or keyboard recorder first");
                }

                if (mouseActive && replaying) {
                    try {
                        mM.replayMacro((Integer) spinner1.getValue());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else if (keyboardActive && replaying) {
                    try {
                        kM.replayMacro((Integer) spinner1.getValue());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        btnRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keyboardActive || mouseActive) {
                    if (!recording) {
                        btnRecord.setBackground(new Color(0, 255, 47));
                        recording = true;
                    } else {
                        btnRecord.setBackground(new Color(173, 161, 156));
                        recording = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error u have to enable mouse or keyboard recorder first");
                }

                if (recording) {
                    if (keyboardActive) {
                        try {
                            kM.recordMacro();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    } else if (mouseActive) {
                        try {
                            mM.recordMacro();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                    }

                }

            }
        });
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame mainFrame = new JFrame();
        mainFrame.setSize(500, 200);
        mainFrame.setContentPane(new MainWindow().panelMain);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.show();
        mainFrame.setResizable(false);

    }


}
