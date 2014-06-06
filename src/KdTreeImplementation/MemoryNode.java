package KdTreeImplementation;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MemoryNode {
	private final static int bufferSize=42;
	private long rightPos;
	private long leftPos;
	private double x;
	private double y;
	private char isLeaf;
	private long pos;
	private static MemoryManager manager;
	public MemoryNode(double x, double y, char leaf) throws IOException{
		isLeaf=leaf;
		this.x=x;
		this.y=y;
		this.pos=manager.getNewPosition();
		//Como el archivo no puede tener posiciones negativas, un numero negativo es el null
		rightPos=-1;
		leftPos=-1;
		this.manager.writeBuff(this.toBuff(), this.pos);
	}
	public MemoryNode(double x, double y, char leaf, long pos) throws IOException{
		isLeaf=leaf;
		this.x=x;
		this.y=y;
		this.pos=pos;
		//Como el archivo no puede tener posiciones negativas, un numero negativo es el null
		rightPos=-1;
		leftPos=-1;
		this.manager.writeBuff(this.toBuff(), this.pos);
	}
	
	//esto hay que hacerlo apenas est� creado el memory manager
	public static void setMemoryManager(MemoryManager man){
		manager=man;
	}
	public void setRight(long pos) throws IOException {
		this.rightPos=pos;
		this.manager.writeBuff(this.toBuff(), this.pos);
		
	}

 
	public byte[] toBuff() {
		//orden de nodo: posLeft, pos, posRight, x, y, isleaf
		byte[] b=new byte[bufferSize];
		ByteBuffer buff = ByteBuffer.allocate(42);
		buff=ByteBuffer.wrap(b);
		buff.position(0);
		buff.putLong(this.leftPos);
		buff.putLong(this.pos);
		buff.putLong(this.rightPos);
		buff.putDouble(this.x);
		buff.putDouble(this.y);
		buff.putChar(this.isLeaf);
		buff.position(0);
		buff.get(b);
		return b;
	}
	
	public static MemoryNode getNodeFromBuff(byte[] b) throws IOException {
		//orden de nodo: posLeft, pos, posRight, x, y, isleaf
		ByteBuffer buff = ByteBuffer.allocate(42);
		buff=ByteBuffer.wrap(b);
		buff.position(0);
		long posLeft, posRight, pos;
		double x,y;
		char isLeaf;
		posLeft=buff.getLong();
		pos=buff.getLong();
		posRight=buff.getLong();
		x=buff.getDouble();
		y=buff.getDouble();
		isLeaf=buff.getChar();
		MemoryNode n= new MemoryNode(x, y, isLeaf,pos);
		n.leftPos = posLeft;
		n.rightPos = posRight;
		return n;
	}
	
	public MemoryNode getRight() throws IOException {
		byte[]b= this.manager.readBuff(rightPos);
		return this.getNodeFromBuff(b);
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
		if(this.rightPos<0&&this.leftPos<0)
			return 0;
		else if(this.rightPos<0)
			return (1+this.getNodeFromBuff(manager.readBuff(leftPos)).height());
		else if(this.leftPos<0)
			return (1+this.getNodeFromBuff(manager.readBuff(rightPos)).height());
		return (1+Math.max(this.getLeft().height(),this.getRight().height()));
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

 
	public boolean isLeaf() {
		return (this.leftPos<0 && this.rightPos<0);
	}

 
	public void setIsLeaf(char b) {
		this.isLeaf=b;
		
	}
	public long getPos(){
		return this.pos;
	}

 
	public Point2D.Double getPoint() throws NotALeafException {
		if(this.isLeaf())
			return new Point2D.Double(this.getX(), this.getY());
		else{
			throw new NotALeafException();
		}
	}
	public boolean equals(MemoryNode n){
		return (this.x==n.x && this.y==n.y && this.pos==n.pos && this.rightPos==n.rightPos
				&& this.leftPos==n.leftPos && this.isLeaf==n.isLeaf);
	}
	public long getRightPos(){
		return this.rightPos;
	}
	public long getLeftPos(){
		return this.leftPos;
	}

}
