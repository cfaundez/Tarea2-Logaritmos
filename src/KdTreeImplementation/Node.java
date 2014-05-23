package KdTreeImplementation;
import java.awt.geom.Point2D;
public class Node {
	private Node right;
	private Node left;
	private Point2D point;
	public Node(Point2D point){
		this.point=point;
		this.right=null;
		this.left=null;
	}
	public void setRight(Node right){
		this.right=right;
	}
	public Node getRight(){
		return this.right;
	}
	public void setLeft(Node right){
		this.left=left;
	}
	public Node getLeft(){
		return this.left;
	}
	public int height(){
		if(this.right==null && this.left==null)
			return 0;
		return Math.max(right.height(), left.height());
	}
	public int size(){
		if(this.right==null && this.left==null)
			return 1;
		return 1+right.size()+left.size();
	}
	

}
