package KdTreeImplementation;
import java.awt.geom.Point2D;
public class Node{
	private Node right;
	private Node left;
	private double x;
	private double y;
	private boolean isLeaf;
	public Node(double x, double y, boolean leaf){
		isLeaf=leaf;
		this.x=x;
		this.y=y;
		this.right=null;
		this.left=null;
	}

	public void setRight(Node right){
		this.right=right;
	}


	public Node getRight(){
		return this.right;
	}


	public void setLeft(Node left){
		this.left=left;
	}


	public Node getLeft(){
		return this.left;
	}

	public int height(){
		if(this.right==null && this.left==null)
			return 0;
		else if(this.right==null)
			return 1+left.height();
		else if(this.left==null)
			return 1+right.height();
		return Math.max(right.height(), left.height())+1;
	}
	public int size(){
		if(this.right==null && this.left==null)
			return 1;
		else if(this.right==null)
			return 1+left.size();
		else if(this.left==null)
			return 1+right.size();
		return 1+right.size()+left.size();
	}

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

	public void setX(double x){
		this.x=x;
	}

	public void setY(double y){
		this.y=y;
	}

	public boolean isLeaf(){
		return this.isLeaf;
	}

	public void setIsLeaf(boolean b){
		this.isLeaf=b;
	}

	public Point2D.Double getPoint() throws NotALeafException{
		if(this.isLeaf())
			return new Point2D.Double(this.getX(), this.getY());
		else{
			throw new NotALeafException();
		}
	}

}
