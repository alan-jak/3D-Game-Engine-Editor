package com.editor.gui.main;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class HierarchyNodeRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = 1L;

    public HierarchyNodeRenderer()
    {
    }

    public Component getTreeCellRendererComponent( JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (leaf && isGameObject(value)) {
            setIcon(UIManager.getIcon("FileView.computerIcon"));
        }
        if(isGameComponent(value))
        {
        	setIcon(UIManager.getIcon("FileView.hardDriveIcon"));
        }
        if(isFolder(value))
        {
        	setIcon(UIManager.getIcon("FileChooser.upFolderIcon"));
        } 

        return this;
    }

    protected boolean isGameObject(Object value) {
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode)value;
        String title = (String) node.getUserObject();
        if (title.indexOf("<GameObject>") >= 0) {
            return true;
        }

        return false;
    }
    
    protected boolean isGameComponent(Object value) {
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode)value;
        String title = (String) node.getUserObject();
        if (title.indexOf("<GameComponent>") >= 0) {
            return true;
        }

        return false;
    }
    
    protected boolean isFolder(Object value) {
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode)value;
        if (node.getChildCount() > 0) {
            return true;
        }

        return false;
    }
}