package KdTreeImplementation;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class KdTreeBuilder {
	private ArrayList<Point2D.Double> points;
	final static int X=0;
	final static int Y=1;
	private int splitAxis;
	public KdTreeBuilder(ArrayList<Point2D.Double> points){
		this.points=points;
		this.splitAxis=this.X;
	}
	public INode startToBuildWithMean(){
		return this.buildWithMean(points);
	}
	public INode startToBuildWithMedian(){
		return this.buildWithMedian(points);
	}
	public INode buildWithMean(ArrayList<Point2D.Double> thePoints){
		//caso base, se recibe 1 punto
		if(thePoints.size()==1){
			Point2D.Double thePoint=thePoints.get(0);
			//este nodo estara en una hoja
			return (new Node(thePoint.getX(), thePoint.getY(), true));
		}
		//si es un conjunto, se particionara en 2 de acuerdo al valor de median
		double mean;
		ArrayList<Point2D.Double> lessThanMean = new ArrayList<Point2D.Double>();
		ArrayList<Point2D.Double> moreThanMean = new ArrayList<Point2D.Double>();
		//casos segun eje
		if(this.splitAxis==this.X){
			//seleccionar el promedio de la menor y mayor coordenada X
			mean=this.selectMeanX(thePoints);
			//particionar en 2 conjuntos
			for(Point2D.Double p: thePoints)
				if(p.getX()<=mean)
					lessThanMean.add(p);
				else
					moreThanMean.add(p);
			//cambiar de eje
			this.switchAxis();
			//el nodo creado tiene el valor mean en la coordenada indicada por splitAxis
			INode ret=new Node(mean, 0, false);
			//llamada recursiva: left son los menores a la raiz y right los mayores
			ret.setLeft(this.buildWithMean(lessThanMean));
			ret.setRight(this.buildWithMean(moreThanMean));
			return ret;
		}
		//analogo para el eje Y
		else{
			mean=this.selectMeanY(thePoints);
			for(Point2D.Double p: thePoints)
				if(p.getY()<=mean)
					lessThanMean.add(p);
				else
					moreThanMean.add(p);
			this.switchAxis();
			INode ret=new Node(0, mean, false);
			ret.setLeft(this.buildWithMean(lessThanMean));
			ret.setRight(this.buildWithMean(moreThanMean));
			return ret;
		}
		
	}
	public INode buildWithMedian(ArrayList<Point2D.Double> thePoints){
		// Analogo a buildWithMean
		if(thePoints.size()==1){
			Point2D.Double thePoint=thePoints.get(0);
			return (new Node(thePoint.getX(), thePoint.getY(), true));
		}
		double median;
		ArrayList<Point2D.Double> lessThanMedian = new ArrayList<Point2D.Double>();
		ArrayList<Point2D.Double> moreThanMedian = new ArrayList<Point2D.Double>();
		if(this.splitAxis==this.X){
			median=this.selectMedianX(thePoints);
			for(Point2D.Double p: thePoints)
				if(p.getX()<=median)
					lessThanMedian.add(p);
				else
					moreThanMedian.add(p);
			this.switchAxis();
			INode ret=new Node(median, 0, false);
			ret.setLeft(this.buildWithMean(lessThanMedian));
			ret.setRight(this.buildWithMean(moreThanMedian));
			return ret;
		}
		else{
			median=this.selectMeanY(thePoints);
			for(Point2D.Double p: thePoints)
				if(p.getY()<=median)
					lessThanMedian.add(p);
				else
					moreThanMedian.add(p);
			this.switchAxis();
			INode ret=new Node(0, median, false);
			ret.setLeft(this.buildWithMean(lessThanMedian));
			ret.setRight(this.buildWithMean(moreThanMedian));
			return ret;
		}
	}
	/**selecciona el valor promedio entre la minima y la maxima coordenada x en el conjunto  
	 * de puntos points
	 * @param mean
	 * @return mean X
	 */

	public static double selectMeanX(ArrayList<Point2D.Double> mean){
		int s=mean.size();
		//notar que si el tamaño es impar, se deja afuera al ultimo elemento
		int halfSize=(int) Math.floor(s/2);
		ArrayList<Double> possibleMaxs=new ArrayList<Double>();
		ArrayList<Double> possibleMins=new ArrayList<Double>();
		//compara pares de elementos consecutivos y los clasifica como
		//posible maximo o posible minimo
		for(int i=0;i<halfSize;i++){
			if(mean.get(2*i).getX()<=mean.get(2*i+1).getX()){
				possibleMaxs.add(mean.get(2*i+1).getX());
				possibleMins.add(mean.get(2*i).getX());
			}
			else{
				possibleMaxs.add(mean.get(2*i).getX());
				possibleMins.add(mean.get(2*i+1).getX());
			}	
		}
		//obtiene el maximo y el minimo de los potenciales conjuntos
		double max=getMax(possibleMaxs);
		double min=getMin(possibleMins);
		//consideracion del ultimo elemento en caso de que el tamaño sea impar
		if(s%2==1)
			if(mean.get(s-1).getX()<min)
				min=mean.get(s-1).getX();
			else if(mean.get(s-1).getX()>max)
				max=mean.get(s-1).getX();
		return (max+min)/2;	
	}

	public static double selectMedianX(ArrayList<Point2D.Double> points){
		//TODO
		return 0;
	}
	/**
	 * todo analogo a selectMeanX
	 * @param points
	 * @return mean
	 */
	public static double selectMeanY(ArrayList<Point2D.Double> points){
		int s=points.size();
		int halfSize=(int) Math.floor(s/2);
		ArrayList<Double> possibleMaxs=new ArrayList<Double>();
		ArrayList<Double> possibleMins=new ArrayList<Double>();
		for(int i=0;i<halfSize;i++){
			if(points.get(2*i).getY()<=points.get(2*i+1).getY()){
				possibleMaxs.add(points.get(2*i+1).getY());
				possibleMins.add(points.get(2*i).getY());
			}
			else{
				possibleMaxs.add(points.get(2*i).getY());
				possibleMins.add(points.get(2*i+1).getY());
			}	
		}
		double max=getMax(possibleMaxs);
		double min=getMin(possibleMins);
		if(s%2==1)
			if(points.get(s-1).getY()<min)
				min=points.get(s-1).getY();
			else if(points.get(s-1).getY()>max)
				max= points.get(s-1).getY();
		return (max+min)/2;	
	}
	public static double selectMedianY(ArrayList<Point2D.Double> points, int axis){
		//TODO
		return 0;
	}
	
	public static double getMax(ArrayList<Double> possibleMaxs) {
		Double candidate=possibleMaxs.get(0);
		for(Double d: possibleMaxs)
			if(d>candidate)
				candidate=d;
		return candidate.doubleValue();
	}
	public static double getMin(ArrayList<Double> possibleMins) {
		Double candidate=possibleMins.get(0);
		for(Double d: possibleMins)
			if(d<candidate)
				candidate=d;
		return candidate.doubleValue();
	}
	public void switchAxis(){
		this.splitAxis=(splitAxis+1)%2;
	}
	public int getAxis(){
		return this.splitAxis;
	}

}
