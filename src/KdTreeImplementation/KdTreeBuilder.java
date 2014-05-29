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
	/**selecciona el valor promedio entre la minima y la maxima coordenada x en el conjunto  
	 * de puntos points
	 * @param points
	 * @return mean X
	 */

	public static float selectMeanX(ArrayList<Point2D> points){
		int s=points.size();
		//notar que si el tamaño es impar, se deja afuera al ultimo elemento
		int halfSize=(int) Math.floor(s/2);
		ArrayList<Double> possibleMaxs=new ArrayList<Double>();
		ArrayList<Double> possibleMins=new ArrayList<Double>();
		//compara pares de elementos consecutivos y los clasifica como
		//posible maximo o posible minimo
		for(int i=0;i<halfSize;i++){
			if(points.get(2*i).getX()<=points.get(2*i+1).getX()){
				possibleMaxs.add(points.get(2*i+1).getX());
				possibleMins.add(points.get(2*i).getX());
			}
			else{
				possibleMaxs.add(points.get(2*i).getX());
				possibleMins.add(points.get(2*i+1).getX());
			}	
		}
		//obtiene el maximo y el minimo de los potenciales conjuntos
		float max=getMax(possibleMaxs);
		float min=getMin(possibleMins);
		//consideracion del ultimo elemento en caso de que el tamaño sea impar
		if(s%2==1)
			if(points.get(s-1).getX()<min)
				min=(float) points.get(s-1).getX();
			else if(points.get(s-1).getX()>max)
				max=(float) points.get(s-1).getX();
		return (max+min)/2;	
	}

	public static float selectMedianX(ArrayList<Point2D> points, int axis){
		//TODO
		return 0;
	}
	/**
	 * todo analogo a selectMeanX
	 * @param points
	 * @return mean
	 */
	public static float selectMeanY(ArrayList<Point2D> points){
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
		float max=getMax(possibleMaxs);
		float min=getMin(possibleMins);
		if(s%2==1)
			if(points.get(s-1).getY()<min)
				min=(float) points.get(s-1).getY();
			else if(points.get(s-1).getY()>max)
				max=(float) points.get(s-1).getY();
		return (max+min)/2;	
	}
	public static float selectMedianY(ArrayList<Point2D> points, int axis){
		//TODO
		return 0;
	}
	
	private static Float getMax(ArrayList<Double> possibleMaxs) {
		Double candidate=possibleMaxs.get(0);
		for(Double d: possibleMaxs)
			if(d>candidate)
				candidate=d;
		return candidate.floatValue();
	}
	private static float getMin(ArrayList<Double> possibleMins) {
		Double candidate=possibleMins.get(0);
		for(Double d: possibleMins)
			if(d<candidate)
				candidate=d;
		return candidate.floatValue();
	}

}
