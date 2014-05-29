package KdTreeImplementation;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class KdTreeBuilder {
	private ArrayList<Point2D> points;
	public KdTreeBuilder(ArrayList<Point2D> points){
		this.points=points;
	}
	public Node buildWithMean(){
		//TODO
		return null;
		
	}
	public Node buildWithMedian(){
		//TODO
		return null;
		
	}
	public static int selectMeanX(ArrayList<Point2D> points){
		//TODO
		return 0;
	}
	public static int selectMedianX(ArrayList<Point2D> points, int axis){
		//TODO
		return 0;
	}
	public static int selectMeanY(ArrayList<Point2D> points){
		//TODO
		return 0;
	}
	public static int selectMedianY(ArrayList<Point2D> points, int axis){
		//TODO
		return 0;
	}

}
