package KdTreeImplementation;
import java.awt.geom.Point2D;
public class Node implements INode {
	private INode right;
	private INode left;
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
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#setRight(KdTreeImplementation.INode)
	 */
	@Override
	public void setRight(INode right){
		this.right=right;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#getRight()
	 */
	@Override
	public INode getRight(){
		return this.right;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#setLeft(KdTreeImplementation.INode)
	 */
	@Override
	public void setLeft(INode left){
		this.left=left;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#getLeft()
	 */
	@Override
	public INode getLeft(){
		return this.left;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#height()
	 */
	@Override
	public int height(){
		if(this.right==null && this.left==null)
			return 0;
		else if(this.right==null)
			return 1+left.height();
		else if(this.left==null)
			return 1+right.height();
		return Math.max(right.height(), left.height())+1;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#size()
	 */
	@Override
	public int size(){
		if(this.right==null && this.left==null)
			return 1;
		else if(this.right==null)
			return 1+left.size();
		else if(this.left==null)
			return 1+right.size();
		return 1+right.size()+left.size();
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#getX()
	 */
	@Override
	public double getX(){
		return this.x;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#getY()
	 */
	@Override
	public double getY(){
		return this.y;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#setX(double)
	 */
	@Override
	public void setX(double x){
		this.x=x;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#setY(double)
	 */
	@Override
	public void setY(double y){
		this.y=y;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#isLeaf()
	 */
	@Override
	public boolean isLeaf(){
		return this.isLeaf;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#setIsLeaf(boolean)
	 */
	@Override
	public void setIsLeaf(boolean b){
		this.isLeaf=b;
	}
	/* (non-Javadoc)
	 * @see KdTreeImplementation.INode#getPoint()
	 */
	@Override
	public Point2D.Double getPoint() throws NotALeafException{
		if(this.isLeaf())
			return new Point2D.Double(this.getX(), this.getY());
		else{
			throw new NotALeafException();
		}
	}

}
