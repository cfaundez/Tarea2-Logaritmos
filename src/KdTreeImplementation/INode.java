package KdTreeImplementation;

import java.awt.geom.Point2D;

public interface INode {

	public abstract void setRight(INode right);

	public abstract INode getRight();

	public abstract void setLeft(INode left);

	public abstract INode getLeft();

	public abstract int height();

	public abstract int size();

	public abstract double getX();

	public abstract double getY();

	public abstract void setX(double x);

	public abstract void setY(double y);

	public abstract boolean isLeaf();

	public abstract void setIsLeaf(boolean b);

	public abstract Point2D.Double getPoint() throws NotALeafException;

}