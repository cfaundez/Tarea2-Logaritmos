package KdTreeImplementation;

import java.awt.geom.Point2D;
import org.apache.commons.math3.random;
import java.util.ArrayList;
import java.util.Random;

public class InstanceGenerator {
	final static private double c=0.7;
	Random r;
	SobolSequenceGenerator sobol;
	public InstanceGenerator(){
		r=new Random();
	}
	public ArrayList<Point2D.Double> randomInstance(int n){
		double max=Math.sqrt(n)*c;
		ArrayList<Point2D.Double> ret=new ArrayList<Point2D.Double>();
		for(int i=0;i<n;i++){
			double x=r.nextDouble()*max;
			double y=r.nextDouble()*max;
			Point2D.Double newPoint=new Point2D.Double(x, y);
			ret.add(newPoint);
		}
		return ret;
	}
	public ArrayList<Point2D.Double> lowDiscrepancyInstance(int n){
		double max=Math.sqrt(n)*c;
		ArrayList<Point2D.Double> ret= new ArrayList<Point2D.Double>();
		for(int i=0;i<n;i++){
			double x=0; //TODO
			double y=0;
			Point2D.Double newPoint=new Point2D.Double(x,y);
			ret.add(newPoint);
		}
		return ret;
	}
}
