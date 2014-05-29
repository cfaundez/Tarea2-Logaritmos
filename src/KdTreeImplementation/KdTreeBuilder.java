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
	public static int selectMean(ArrayList<Point2D> points, int axis){
		return 0;
	}
	public static int selectMedian(ArrayList<Point2D> points, int axis){
		return 0;
	}

}
