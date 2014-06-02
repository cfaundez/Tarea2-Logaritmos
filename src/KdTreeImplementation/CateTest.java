package KdTreeImplementation;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.Test;

public class CateTest {
	private ArrayList<Double> minmax;
	private ArrayList<Point2D> mean;
	private KdTreeBuilder builder;
	private Point2D a,b,c,d,e;
	Node n1,n2,n3,n4,n5;
	@Test
	public void NodeTests() throws NotALeafException {
		n1=new Node(1, 1, false);
		assertFalse(n1.isLeaf());
		assertNull(n1.getLeft());
		assertNull(n1.getRight());
		assertEquals(1, n1.getX(),0.001);
		assertEquals(1, n1.getY(),0.001);
		n2=new Node(2, 2, true);
		n3=new Node(3,3, false);
		n1.setLeft(n3);
		n3.setRight(n2);
		int h=2;
		int s=3;
		n2.setIsLeaf(false);
		assertEquals(h, n1.height());
		assertEquals(s, n1.size());
		n4=new Node(4, 4, true);
		n5=new Node(5, 5, true);
		n2.setLeft(n4);
		n2.setRight(n5);
		int h1=4;
		int s1=5;
		a=new Point2D.Double(5, 5);
		assertEquals(a.getX(),n5.getPoint().x, 0.001);
		assertEquals(a.getY(),n5.getPoint().y, 0.001);
	}
	@Test(expected=NotALeafException.class)
	public void notALeafTest() throws NotALeafException{
		n3=new Node(3,3, false);
		n3.getPoint();
		
	}
	
	@Test
	public void BuilderAuxFunctionsTests() {
		minmax=new ArrayList<Double>();
		minmax.add( 0.56);
		minmax.add( 5.84);
		minmax.add( 0.56);
		minmax.add( 0.99);
		minmax.add( 2.38);
		builder=new KdTreeBuilder(null);
		int size=5;
		float max=(float) 5.84;
		float min=(float) 0.56;
		assertEquals(size,minmax.size());
		assertEquals(min, builder.getMin(minmax), 0.001);
		assertEquals(max, builder.getMax(minmax), 0.001);
	}
	@Test
	public void BuilderFunctionsTests() {
		builder=new KdTreeBuilder(null);
		mean=new ArrayList<Point2D>();
		a=new Point2D.Double(0, 0);
		b=new Point2D.Double(1.5, -2);
		c=new Point2D.Double(-8.44,-16.03);
		d=new Point2D.Double(11.93, 15.12);
		e=new Point2D.Double(-33.8, 2.43);
		mean.add(a);
		mean.add(b);
		mean.add(c);
		mean.add(d);
		mean.add(e);
		double meanX=(-33.8+11.93)/2;
		double meanY=(-16.03+15.12)/2;
		assertEquals(meanX, builder.selectMeanX(mean),0.001);
		assertEquals(meanY, builder.selectMeanY(mean),0.001);
		
		
		
	}

}
