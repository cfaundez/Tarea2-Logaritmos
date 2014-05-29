package KdTreeImplementation;
import java.awt.geom.Point2D;
public class Node {
	private Node right;
	private Node left;
	private float x;
	private float y;
	public Node(int x, int y){
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
	public void setLeft(Node right){
		this.left=left;
	}
	public Node getLeft(){
		return this.left;
	}
	public int height(){
		if(this.right==null && this.left==null)
			return 0;
		return Math.max(right.height(), left.height())+1;
	}
	public int size(){
		if(this.right==null && this.left==null)
			return 1;
		return 1+right.size()+left.size();
	}
	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}
	public void setX(float x){
		this.x=x;
	}
	public void setY(float y){
		this.y=y;
	}

}
