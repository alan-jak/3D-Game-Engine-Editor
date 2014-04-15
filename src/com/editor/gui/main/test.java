package com.editor.gui.main;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

import com.editor.gui.themes.NimbusMetrofier;

public class test {
	final static JFrame frame = new JFrame("Unitor 3D Game Engine Editor");
	public static String filename = "./res/game/gameFile.planet";
	
    public static void main(String[] args) {
    	
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
        
        NimbusMetrofier.metrofy();
        
        showFileChooser();
    }
    
    	public static void showFileChooser()
    	{
    		final JFrame chooseFrame = new JFrame("Select a Scene to Open");
            chooseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
            JFrame.setDefaultLookAndFeelDecorated(true);

            chooseFrame.setSize(808, 486);
            chooseFrame.getContentPane().setLayout(null);
            
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setBounds(0, 0, 792, 408);
            chooseFrame.getContentPane().add(fileChooser);
            fileChooser.setControlButtonsAreShown(false);
            fileChooser.setFileFilter(new PlanetFileFilter( "planet", "Planet Scene File"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            disableUglyButtons(fileChooser.getParent());
            disableUglyButtons(fileChooser.getParent());
            
            JButton btnOpenScene = new JButton("Open Scene");
            btnOpenScene.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) {
            		if(fileChooser.getSelectedFile() != null)
            			filename = fileChooser.getSelectedFile().getAbsolutePath();
            		beginEditor();
            		chooseFrame.setVisible(false);
            	}
            });
            btnOpenScene.setBounds(664, 414, 122, 28);
            chooseFrame.getContentPane().add(btnOpenScene);
            chooseFrame.setVisible(true);
    	}
    
    public static void beginEditor()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame.setSize(1280, 720);

        JDesktopPane desktop = new JDesktopPane();
        desktop.setVisible(true);

        frame.setContentPane(desktop);

        desktop.add(HierarchyFrame.createHeirarchyFrame(frame));
        desktop.add(BasicFrames.createEngineFrame(frame));
        desktop.add(BasicFrames.createInfoFrame(frame));
    }
    
    
    public static void disableUglyButtons(Container c) { 
        for (int i = 0; i < c.getComponentCount(); i++) {  
          Component comp = c.getComponent(i);  
          if (comp instanceof JToggleButton) {  
        	  JToggleButton b = (JToggleButton) comp;  
              c.remove(b);  
          } else if (comp instanceof Container) {  
        	  disableUglyButtons((Container) comp);  
          }  
        }  
        
         
    } 
}