package com.editor.gui.themes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.Painter;
import javax.swing.UIManager;

public class NimbusMetrofier
{	
	public static void metrofy()
	{
		UIManager.getLookAndFeelDefaults().put("nimbusBase", Color.decode("#76608A"));
		UIManager.getLookAndFeelDefaults().put("nimbusBlueGrey", Color.decode("#647687"));
		UIManager.put("control", Color.decode("#FFFFFF"));
		UIManager.getLookAndFeelDefaults().put("InternalFrame:InternalFrameTitlePane[Enabled].textForeground", Color.decode("#F7F7F7"));		
		UIManager.getLookAndFeelDefaults().put("InternalFrame.titleFont", new Font("Segoe UI", Font.PLAIN, 12));
		UIManager.getLookAndFeelDefaults().put("InternalFrame.activeTitleBackground", Color.RED);
		UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Segoe UI", Font.PLAIN, 12));
		
		UIManager.getLookAndFeelDefaults().put("InternalFrame[Enabled+WindowFocused].backgroundPainter", new Painter<Object>() {
			@Override
			public void paint(Graphics2D g, Object c, int w, int h) {
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setStroke(new BasicStroke(2f));
                g.setColor(Color.decode("#6A00FF"));
                g.fillRoundRect(0, 0, w-1, h-1, 8, 8);
                g.setColor(Color.decode("#5200D6"));
                g.drawRoundRect(0, 0, w-1, h-1, 8, 8);
			}});
		
		UIManager.getLookAndFeelDefaults().put("InternalFrame[Enabled].backgroundPainter", new Painter<Object>() {

			@Override
			public void paint(Graphics2D g, Object c, int w, int h) {
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setStroke(new BasicStroke(2f));
                g.setColor(Color.decode("#9047FF"));
                g.fillRoundRect(0, 0, w-1, h-1, 8, 8);
                g.setColor(Color.decode("#7F40E5"));
                g.drawRoundRect(0, 0, w-1, h-1, 8, 8);
			}});
		
		UIManager.getLookAndFeelDefaults().put("InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.closeButton\".contentMargins", new Insets(0, 0, 0, 0));
		UIManager.getLookAndFeelDefaults().put("InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\".contentMargins", new Insets(0, 0, 0, 0));
	}
	
	
}
