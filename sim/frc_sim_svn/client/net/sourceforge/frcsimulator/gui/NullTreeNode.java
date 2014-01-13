/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.gui;

import java.util.Enumeration;
import javax.swing.tree.TreeNode;

/**
 *
 * @author wolf
 */
public class NullTreeNode implements TreeNode {

	@Override
	public TreeNode getChildAt(int i) {
		return null; // Cannot get nonexistent children
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int getIndex(TreeNode tn) {
		return -1;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public Enumeration children() {
		return null; // Cannot enumerate on nonexistent children
	}
}
