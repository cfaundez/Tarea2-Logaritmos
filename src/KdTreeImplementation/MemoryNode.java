package KdTreeImplementation;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class MemoryNode {
	private double rightPos;
	private double leftPos;
	private double x;
	private double y;
	private boolean isLeaf;
	private double pos;
	MemoryManager manager;
	public MemoryNode(double x, double y, boolean leaf, MemoryManager man){
		isLeaf=leaf;
		this.x=x;
		this.y=y;
		this.manager=man;
		this.pos=manager.getNewPosition();
		//Como el archivo no puede tener posiciones negativas, un numero negativo es el null
		rightPos=-1;
		leftPos=-1;
	}
	public void setRight(double pos) {
		this.rightPos=pos;
		
	}

 
	public MemoryNode getRight() {
		return this.manager.readNode(rightPos);
	}


	public void setLeft(double pos) {
		this.leftPos=pos;
		
	}

 
	public MemoryNode getLeft() {
		return this.manager.readNode(leftPos);
	}

 
	public int height() {
		if(this.leftPos<0 && this.rightPos<0)
			return 0;
		else if(this.rightPos<0)
			return 1+manager.readNode(leftPos).height();
		else if(this.leftPos<0)
			return 1+manager.readNode(rightPos).height();
		return 1+manager.readNode(leftPos).height()+manager.readNode(rightPos).height();
	}

 
	public int size() {
		if(this.leftPos<0 && this.rightPos<0)
			return 1;
		else if(this.rightPos<0)
			return 1+manager.readNode(leftPos).size();
		else if(this.leftPos<0)
			return 1+manager.readNode(rightPos).size();
		return 1+manager.readNode(leftPos).size()+manager.readNode(rightPos).size();
	}

 
	public double getX() {
		return this.x;
	}

 
	public double getY() {
		return this.y;
	}

 
	public void setX(double x) {
		this.x=x;
		
	}

 
	public void setY(double y) {
		this.y=y;
		
	}

 
	public boolean isLeaf() {
		return this.isLeaf;
	}

 
	public void setIsLeaf(boolean b) {
		this.isLeaf=b;
		
	}

 
	public Point2D.Double getPoint() throws NotALeafException {
		if(this.isLeaf())
			return new Point2D.Double(this.getX(), this.getY());
		else{
			throw new NotALeafException();
		}
	}

}
