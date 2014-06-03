package KdTreeImplementation;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.IOException;

public class MemoryNode {
	private final int bufferSize=42;
	private long rightPos;
	private long leftPos;
	private double x;
	private double y;
	private char isLeaf;
	private long pos;
	MemoryManager manager;
	public MemoryNode(double x, double y, char leaf, MemoryManager man) throws IOException{
		isLeaf=leaf;
		this.x=x;
		this.y=y;
		this.manager=man;
		this.pos=manager.getNewPosition();
		//Como el archivo no puede tener posiciones negativas, un numero negativo es el null
		rightPos=-1;
		leftPos=-1;
		this.manager.writeBuff(this.toBuff(), this.pos);
	}
	public void setRight(long pos) throws IOException {
		this.rightPos=pos;
		this.manager.writeBuff(this.toBuff(), this.pos);
		
	}

 
	private static byte[] toBuff() {
		// TODO Auto-generated method stub
		return null;
	}
	public MemoryNode getRight() throws IOException {
		byte[]b= this.manager.readBuff(rightPos);
		return this.getNodeFromBuff(b);
	}


	private static MemoryNode getNodeFromBuff(byte[] b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setLeft(long pos) throws IOException {
		this.leftPos=pos;
		this.manager.writeBuff(this.toBuff(), this.pos);
		
	}

 
	public MemoryNode getLeft() throws IOException {
		byte[]b= this.manager.readBuff(leftPos);
		return this.getNodeFromBuff(b);
	}

 
	public int height() throws IOException {
		if(this.leftPos<0 && this.rightPos<0)
			return 0;
		else if(this.rightPos<0)
			return 1+this.getNodeFromBuff(manager.readBuff(leftPos)).height();
		else if(this.leftPos<0)
			return 1+this.getNodeFromBuff(manager.readBuff(rightPos)).height();
		return 1+this.getNodeFromBuff(manager.readBuff(leftPos)).height()+this.getNodeFromBuff(manager.readBuff(rightPos)).height();
	}

 
	public int size() throws IOException {
		if(this.leftPos<0 && this.rightPos<0)
			return 1;
		else if(this.rightPos<0)
			return 1+this.getNodeFromBuff(manager.readBuff(leftPos)).size();
		else if(this.leftPos<0)
			return 1+this.getNodeFromBuff(manager.readBuff(rightPos)).size();
		return 1+this.getNodeFromBuff(manager.readBuff(leftPos)).size()+this.getNodeFromBuff(manager.readBuff(rightPos)).size();
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

 
	public char isLeaf() {
		return this.isLeaf;
	}

 
	public void setIsLeaf(char b) {
		this.isLeaf=b;
		
	}

 
	public Point2D.Double getPoint() throws NotALeafException {
		if(this.isLeaf()==1)
			return new Point2D.Double(this.getX(), this.getY());
		else{
			throw new NotALeafException();
		}
	}

}
