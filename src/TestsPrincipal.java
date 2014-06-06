import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import KdTreeImplementation.*;


public class TestsPrincipal {
	
	public static void main(String[] arg){
		Random r=new Random();
		InstanceGenerator gen = new InstanceGenerator();
		
		for(int n=10; n<=20; n++){
		
			ArrayList<Point2D.Double> puntosAleatorios = gen.randomInstance(n);
			ArrayList<Point2D.Double> puntosBajaDisc = gen.lowDiscrepancyInstance(n);
			
			KdTreeBuilder builder=new KdTreeBuilder(puntosAleatorios);
			
			long t1 = System.nanoTime();
			Node n1=builder.startToBuildWithMean();
			long t2 = System.nanoTime();
			System.out.println("KD-1, puntos aleatorios, mean, n=" + n + ", t=" + (t2-t1));
			
			t1 = System.nanoTime();
			Node n2=builder.startToBuildWithMedian();
			t2 = System.nanoTime();
			System.out.println("KD-2, puntos aleatorios, median, n=" + n + ", t=" + (t2-t1));
			
			builder.setPoints(puntosBajaDisc);
			
			t1 = System.nanoTime();
			Node n3=builder.startToBuildWithMean();
			t2 = System.nanoTime();
			System.out.println("KD-3, puntos baja disc, mean, n=" + n + ", t=" + (t2-t1));
			
			t1 = System.nanoTime();
			Node n4=builder.startToBuildWithMedian();
			t2 = System.nanoTime();
			System.out.println("KD-4, puntos baja disc, median, n=" + n + ", t=" + (t2-t1));
			
			//Medir tamaño y altura de n1,n2,n3,n4
			System.out.println("KD-1, altura =" + n1.height() + ", tamaño =" + n1.size());
			System.out.println("KD-2, altura =" + n2.height() + ", tamaño =" + n2.size());
			System.out.println("KD-3, altura =" + n3.height() + ", tamaño =" + n3.size());
			System.out.println("KD-4, altura =" + n4.height() + ", tamaño =" + n4.size());
			
			double max=Math.sqrt(10)*0.4;
			double x1=r.nextDouble()*max;
			double y1=r.nextDouble()*max;
			Point2D.Double p=new Point2D.Double(x1, y1);
			
			//mas cercano
			t1 = System.nanoTime();
			Point2D.Double a=builder.vecinoMasCercano(n1, p);
			t2 = System.nanoTime();
			System.out.println("KD-1, punto buscado=" + p + ", pto encontrado=" + a + ", tiempo=" + (t2-t1));
			
			t1 = System.nanoTime();
			Point2D.Double b=builder.vecinoMasCercano(n2, p);
			t2 = System.nanoTime();
			System.out.println("KD-2, punto buscado=" + p + ", pto encontrado=" + b + ", tiempo=" + (t2-t1));
			
			t1 = System.nanoTime();
			Point2D.Double c=builder.vecinoMasCercano(n3, p);
			t2 = System.nanoTime();
			System.out.println("KD-3, punto buscado=" + p + ", pto encontrado=" + c + ", tiempo=" + (t2-t1));
			
			t1 = System.nanoTime();
			Point2D.Double d=builder.vecinoMasCercano(n4, p);
			t2 = System.nanoTime();
			System.out.println("KD-4, punto buscado=" + p + ", pto encontrado=" + d + ", tiempo=" + (t2-t1));
			
			System.out.println();
		}
	}
		

}
