package com.editor.gui.main;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class test {
	final static JFrame frame = new JFrame("Unitor 3D Game Engine Editor");
	
    public static void main(String[] args) {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setSize(1280, 720);

        JDesktopPane desktop = new JDesktopPane();
        desktop.setVisible(true);

        frame.setContentPane(desktop);

        desktop.add(HierarchyFrame.createHeirarchyFrame(frame));
        desktop.add(BasicFrames.createEngineFrame(frame));
        desktop.add(BasicFrames.createInfoFrame(frame));
    }
    
}