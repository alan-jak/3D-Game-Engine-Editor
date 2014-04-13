package com.editor.gui.main;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.editor.wysiwygEngine.base.components.GameComponent;
import com.editor.wysiwygEngine.base.core.CoreEngine;
import com.editor.wysiwygEngine.base.core.GameObject;


public class HierarchyFrame
{
	static JInternalFrame internalFrame = new JInternalFrame("Engine Hierarchy", true, false, false);
	static JTree tree = new JTree();
	static DefaultTreeModel model = new DefaultTreeModel(new DefaultMutableTreeNode("Game") {
															private static final long serialVersionUID = 1L;
															{
															}
															});
	static DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
	
	static ArrayList<ObjectInfoFrame> infoFrames = new ArrayList<ObjectInfoFrame>();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static JInternalFrame createHeirarchyFrame(final JFrame frame)
    {
		BasicFrames.removeDropdown(internalFrame);
		
        internalFrame.setVisible(true);
        internalFrame.setBounds(200, 200, 518, 352);
        internalFrame.setFocusable(true);
        try {
        	
            internalFrame.setSelected(true);

            tree.setModel(new DefaultTreeModel(
                	new DefaultMutableTreeNode("Loading Tree... Wait for Engine To Initialize") {
    					private static final long serialVersionUID = 1L;
    					{
                		}
                	}
                ));
            
            root.removeAllChildren();
            
            model.reload(root);
            
            MouseListener ml = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int selRow = tree.getRowForLocation(e.getX(), e.getY());
                    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                    if(selRow != -1) {
                    	if(e.getClickCount() == 2 && isGameObject((DefaultMutableTreeNode)tree.getLastSelectedPathComponent())) {
                    		ObjectInfoFrame frameTAdd = new ObjectInfoFrame();
                    		synchronized(infoFrames)
                    		{
                    			infoFrames.add(frameTAdd);
                    		}
                        	internalFrame.getParent().add(frameTAdd.createInfoFrame(frame, selPath.getLastPathComponent().toString(), CoreEngine.game.getRootObject()));
                        }
                    }
                }
            };
            tree.addMouseListener(ml);
            
            internalFrame.getContentPane().add(tree, BorderLayout.CENTER);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        internalFrame.invalidate();
        
        new Thread() {
        	public void run() {
        		while (true) {
        			synchronized(infoFrames)
        			{
	                    for(ObjectInfoFrame frome : infoFrames)
	                    {
	                    	frome.update();
	                    }
        			}
                }
        	}
        }.start();
        
        return internalFrame;
    }
	
	public static void updateHeirarchyTree(GameObject rootObj)
	{	
		root.removeAllChildren();

		root.add(addToHeirarchy( new DefaultMutableTreeNode(), rootObj));
		
		System.out.println(root.getChildCount());
		
		model.reload(root);
		tree.setModel(model);
		tree.setCellRenderer(new HierarchyNodeRenderer());
	}
	
	public static DefaultMutableTreeNode addToHeirarchy(DefaultMutableTreeNode currentNode, GameObject obj)
	{
		DefaultMutableTreeNode myNode = new DefaultMutableTreeNode(obj.getName() + " <GameObject>");
		
		for(GameComponent gc : obj.getComponents())
		{
			myNode.add(new DefaultMutableTreeNode(gc.getClass().getSimpleName() + " <GameComponent>"));
		}
		
		for(GameObject object : obj.getChildren())
		{
			myNode.add(addToHeirarchy(myNode, object));
		}
		
		return myNode;
	}
	
	 private static boolean isGameObject(DefaultMutableTreeNode node)
	 {
        String title = (String) node.getUserObject();
        if (title.indexOf("<GameObject>") >= 0) {
            return true;
        }

        return false;
    }
}
