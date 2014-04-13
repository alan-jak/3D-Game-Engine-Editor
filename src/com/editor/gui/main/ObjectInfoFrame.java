package com.editor.gui.main;

import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import com.editor.wysiwygEngine.base.core.GameObject;

import java.awt.GridLayout;

public class ObjectInfoFrame
{
	public ObjectInfoFrame()
	{}
	
	JLabel lblXpos;
	JLabel lblYpos;
	JLabel lblZpos;
	JLabel lblYrot;
	JLabel lblXrot;
	JLabel lblZrot;
	GameObject objForInfo = new GameObject("");
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JInternalFrame createInfoFrame(final JFrame frame, String objName, GameObject rootObj)
    {
		
		String name = "";
		
		if(objName.endsWith(" <GameObject>"))
		{
			objName = objName.substring(0, objName.length() - 13);
		}
		
		int index = 0;
		boolean instantClose = false;
		synchronized(rootObj.getAllAttached())
		{
			while(!name.equals(objName) && index < rootObj.getAllAttached().size())
			{
				objForInfo = rootObj.getAllAttached().get(index);
				name = objForInfo.getName();
				index++;
			}
		}
		if(!objName.equals(name))
		{
			System.out.println("Name not found");
			instantClose = true;
		}
		else
		{
			index--;
		}
		
    	final JInternalFrame internalFrame = new JInternalFrame("Info: " + objName, false, true, false);

    	BasicFrames.removeDropdown(internalFrame);
    	BasicFrames.metroStyleTitlebar(internalFrame);
    	
        internalFrame.setVisible(!instantClose);
        internalFrame.setBounds(200, 200, 490, 210);
        internalFrame.setFocusable(true);
        try {
            internalFrame.setSelected(true);
            internalFrame.getContentPane().setLayout(new GridLayout(0, 4, 0, 0));
            
            JLabel label = new JLabel("Object X:");
            internalFrame.getContentPane().add(label);
            
            lblXpos = new JLabel("XPOS");
            internalFrame.getContentPane().add(lblXpos);
            
            JLabel lblObjectXRotation = new JLabel("Object X Rotation:");
            internalFrame.getContentPane().add(lblObjectXRotation);
            
            lblXrot = new JLabel("XROT");
            internalFrame.getContentPane().add(lblXrot);
            
            JLabel lblObjectX = new JLabel("Object Y:");
            internalFrame.getContentPane().add(lblObjectX);
            
            lblYpos = new JLabel("YPOS");
            internalFrame.getContentPane().add(lblYpos);
            
            JLabel lblObjectYRotation = new JLabel("Object Y Rotation:");
            internalFrame.getContentPane().add(lblObjectYRotation);
            
            lblYrot = new JLabel("YROT");
            internalFrame.getContentPane().add(lblYrot);
            
            JLabel lblObjectZ = new JLabel("Object Z:");
            internalFrame.getContentPane().add(lblObjectZ);
            
            lblZpos = new JLabel("ZPOS");
            internalFrame.getContentPane().add(lblZpos);
            
            JLabel lblObjectZRotation = new JLabel("Object Z Rotation:");
            internalFrame.getContentPane().add(lblObjectZRotation);
            
            lblZrot = new JLabel("ZROT");
            internalFrame.getContentPane().add(lblZrot);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        internalFrame.invalidate();
        
        return internalFrame;
    }
	
	public void update()
	{
		if(lblXpos != null)
			lblXpos.setText(Float.toString(objForInfo.getTransform().getPosition().getX()));
		if(lblYpos != null)
			lblYpos.setText(Float.toString(objForInfo.getTransform().getPosition().getY()));
		if(lblZpos != null)
			lblZpos.setText(Float.toString(objForInfo.getTransform().getPosition().getZ()));
		if(lblXrot != null)
			lblXrot.setText(Float.toString(objForInfo.getTransform().getRotation().getX()));
		if(lblYrot != null)
			lblYrot.setText(Float.toString(objForInfo.getTransform().getRotation().getY()));
		if(lblZrot != null)
			lblZrot.setText(Float.toString(objForInfo.getTransform().getRotation().getZ()));
	}
}
