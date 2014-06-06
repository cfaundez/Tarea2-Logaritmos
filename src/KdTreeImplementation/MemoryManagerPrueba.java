package KdTreeImplementation;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class MemoryManagerPrueba {

	public static void main(String[] args) {
		InstanceGenerator gen=new InstanceGenerator();
		KdTreeBuilder builder=new KdTreeBuilder(null);
		ArrayList<Point2D.Double> points=new ArrayList<Point2D.Double>();
		Node kDTree;
		Point2D.Double point=new Point2D.Double(0.5,0.5);
		Point2D.Double a= new Point2D.Double(0,0);
		Point2D.Double b=new Point2D.Double(0.45, 0.45);
		Point2D.Double c=new Point2D.Double(0.8,0.8);
		Point2D.Double d=new Point2D.Double(1,0);
		points.add(a);
		points.add(b);
		points.add(c);
		points.add(d);
		builder.setPoints(points);
		kDTree=builder.startToBuildWithMean();
		Point2D.Double masCercano=builder.vecinoMasCercano(kDTree, point);
		System.out.println(masCercano.x);
		System.out.println(masCercano.y);
	}

}