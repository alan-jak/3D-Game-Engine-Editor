package com.editor.gui.main;
import java.awt.Canvas;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;

import com.editor.wysiwygEngine.base.core.CoreEngine;
import com.editor.wysiwygEngine.game.TestGame;

import javax.swing.JLabel;

import java.awt.Desktop;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class BasicFrames
{
	public static JInternalFrame createEngineFrame(final JFrame frame)
    {
    	final JInternalFrame internalFrame = new JInternalFrame("WYSIWYG Engine View", true, false, true);

        

        internalFrame.setVisible(true);
        internalFrame.setLocation(30,30);
        internalFrame.setBounds(40, 40, 200, 150);
        internalFrame.setFocusable(true);
        try {
            internalFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        final Canvas canvas = makeCanvas();

        internalFrame.getContentPane().add(canvas);
        internalFrame.invalidate();

        Thread invalidator = new Thread() {
            @Override
            public void run() {
                while (true) {
                    frame.invalidate();
                }
            }
        };
        invalidator.setDaemon(true);
        
        
        frame.setVisible(true);
        new Thread() {
        	public void run() {
        		CoreEngine engine = new CoreEngine(frame.getWidth(), frame.getHeight(), 60, true, new TestGame());
        		engine.createWindow("Earthengine tech demo", canvas);
        		engine.start();
        	}
        }.start();
        
        return internalFrame;
    }
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static JInternalFrame createInfoFrame(final JFrame frame)
    {
    	final JInternalFrame internalFrame = new JInternalFrame("About Unitor", false, true, false);

        

        internalFrame.setVisible(true);
        internalFrame.setBounds(200, 200, 518, 352);
        internalFrame.setFocusable(true);
        try {
            internalFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        internalFrame.getContentPane().setLayout(null);
        
        JLabel lblWelcomeToUnitor = new JLabel("Welcome to Unitor!");
        lblWelcomeToUnitor.setBounds(0, 0, 502, 43);
        lblWelcomeToUnitor.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblWelcomeToUnitor.setHorizontalAlignment( SwingConstants.CENTER );
        internalFrame.getContentPane().add(lblWelcomeToUnitor);
        
        JEditorPane txtpnUnitorIsA = new JEditorPane();
        txtpnUnitorIsA.setContentType("text/html");
        txtpnUnitorIsA.setEditable(false);
        txtpnUnitorIsA.setBorder(null);  
        txtpnUnitorIsA.setText("<style>\r\n\tbody\r\n\t\t{\r\n\t\t\tfont-family:Tahoma, Geneva, sans-serif;\r\n\t\t\tbackground-color:#d6d9df;\r\n\t\t\tborder:0px solid #FFFFFF\r\n\t\t}\r\n</style>\r\n<body>\r\nUnitor is a powerful 3D editor interface for the open source 3D Game Engine series by thebennybox/BennyQBD available from <a href='http://github.com/BennyQBD/3DGameEngine'>github.com/BennyQBD/3DGameEngine</a> <br/>\r\nDid you know this editor is also open source? Get the source at <a href='http://github.com/Kiwuser/3D-Game-Engine-Editor'>github.com/Kiwuser/3D-Game-Engine-Editor</a><br/>\r\nThis project is maintained by Kiwuser<br/>\r\n<a rel=\"license\" href=\"http://creativecommons.org/licenses/by/4.0/\"><img alt=\"Creative Commons License\" style=\"border-width:0\" src=\"http://i.creativecommons.org/l/by/4.0/88x31.png\" /></a><br /><span xmlns:dct=\"http://purl.org/dc/terms/\" href=\"http://purl.org/dc/dcmitype/InteractiveResource\" property=\"dct:title\" rel=\"dct:type\">Unitor</span> is licensed under a <a rel=\"license\" href=\"http://creativecommons.org/licenses/by/4.0/\">Creative Commons Attribution 4.0 International License</a>.<br />Based on a work at <a xmlns:dct=\"http://purl.org/dc/terms/\" href=\"http://github.com/Kiwuser/3D-Game-Engine-Editor\" rel=\"dct:source\">http://github.com/Kiwuser/3D-Game-Engine-Editor</a>.\r\n</body>");
        txtpnUnitorIsA.setBounds(10, 54, 482, 227);
        txtpnUnitorIsA.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent hle) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                    System.out.println(hle.getURL());
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(hle.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        internalFrame.getContentPane().add(txtpnUnitorIsA);
        
        JLabel lblYouAreUsing = new JLabel("This is version 0.1a of Unitor");
        lblYouAreUsing.setBounds(10, 298, 302, 14);
        internalFrame.getContentPane().add(lblYouAreUsing);
        internalFrame.invalidate();
        
        return internalFrame;
    }
	
	public static Canvas makeCanvas() {
        Canvas canvas = new Canvas();
        canvas.setFocusable(true);
        canvas.setSize(200,150);
        canvas.setVisible(true);
        return canvas;
    }
}
