package KdTreeImplementation;

import java.awt.geom.Point2D.Double;

public class MemoryNode implements INode {
	private INode right;
	private INode left;
	private double x;
	private double y;
	private boolean isLeaf;
	private double pos;

	@Override
	public void setRight(INode right) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public INode getRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLeft(INode left) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public INode getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsLeaf(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double getPoint() throws NotALeafException {
		// TODO Auto-generated method stub
		return null;
	}

}
