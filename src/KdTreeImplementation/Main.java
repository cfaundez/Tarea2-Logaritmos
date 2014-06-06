package KdTreeImplementation;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		InstanceGenerator gen=new InstanceGenerator();
		System.out.println(gen.lowDiscrepancyInstance(10));
	}

}