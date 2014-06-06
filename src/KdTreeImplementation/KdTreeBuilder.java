package KdTreeImplementation;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

public class KdTreeBuilder {
	private ArrayList<Point2D.Double> points;
	final static int X=0;
	final static int Y=1;
	private int splitAxis;
	public KdTreeBuilder(ArrayList<Point2D.Double> points){
		this.points=points;
		this.splitAxis=this.X;
	}
	public Node startToBuildWithMean(){
		return this.buildWithMean(points);
	}
	public Node startToBuildWithMedian(){
		return this.buildWithMedian(points);
	}
	public Node buildWithMean(ArrayList<Point2D.Double> thePoints){
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
			Node ret=new Node(mean, -1, false);
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
			Node ret=new Node(-1, mean, false);
			ret.setLeft(this.buildWithMean(lessThanMean));
			ret.setRight(this.buildWithMean(moreThanMean));
			return ret;
		}


	}
	public Node buildWithMedian(ArrayList<Point2D.Double> thePoints){
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
			Node ret=new Node(median, -1, false);
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
			Node ret=new Node(-1, median, false);
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

	public static double selectMeanX(ArrayList<Point2D.Double> points){
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
		double max=getMax(possibleMaxs);
		double min=getMin(possibleMins);
		//consideracion del ultimo elemento en caso de que el tamaño sea impar
		if(s%2==1)
			if(points.get(s-1).getX()<min)
				min=points.get(s-1).getX();
			else if(points.get(s-1).getX()>max)
				max=points.get(s-1).getX();
		return (max+min)/2;	
	}
	//Cami: listo
	public static double selectMedianX(ArrayList<Point2D.Double> points){
		ArrayList<Double> numbers = new ArrayList<Double>();

		for (Point2D.Double point : points)
			numbers.add(point.getX());

		int k=(int) Math.floor(points.size() / 2); //posicion de la mediana

		return selectKth(numbers, k);
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
	//Cami: listo
	public static double selectMedianY(ArrayList<Point2D> points, int axis){
		ArrayList<Double> numbers = new ArrayList<Double>();

		for (Point2D point : points)
			numbers.add(point.getY());

		int k=(int) Math.floor(points.size() / 2); //posicion de la mediana

		return selectKth(numbers, k);
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
	//Cami: listo
	public static double selectKth(List<Double> numbers, int k) {
		int s = numbers.size();
		List<Double> list = new ArrayList<Double>(numbers);
		if (s <= 5){
			//Insertsort
			double aux;
			for(int i=1; i<s; i++){
				aux = list.get(i);
				for(int j=i; j>=0; j--){
					if (j==0 || list.get(j-1) <= aux){
						list.remove(i);
						list.add(j, aux);//inserta en posicion j
						break;
					}
				}
			}
			return list.get(k); //Return kth element
		}

		//obtengo las medianas
		double mh; //median helper, contiene la media de un subconjunto
		List<Double> medians = new ArrayList<Double>();
		List<Double> aux = new ArrayList<Double>();
		for (int i=0; i<s; i+=5){
			if (i+5 < s){
				aux = numbers.subList(i, i+5);
				mh = selectKth(aux, 2);
			}
			else{
				aux = numbers.subList(i, s);
				mh = selectKth(aux, (int) Math.floor(aux.size()/2) );
			}
			medians.add( mh );
		}

		int mediansK = (int) Math.floor(medians.size()/2);// medians.size()/2 = piso(techo(n/5)/2)
		double median = selectKth(medians, mediansK); //mediana de medianas

		//particiono los elementos segun la mediana
		ArrayList<Double> lesser = new ArrayList<Double>();
		ArrayList<Double> equal = new ArrayList<Double>();
		ArrayList<Double> greater = new ArrayList<Double>();
		for (double number : numbers){
			if (number < median)
				lesser.add(number);
			else if (number == median)
				equal.add(number);
			else
				greater.add(number);
		}
		//paso recursivo o retorno
		if (k< lesser.size())
			return selectKth(lesser, k);
		else if (k>= lesser.size() + equal.size())
			return selectKth(greater, k-lesser.size()-equal.size());
		else
			return median;
	}
	
	Point2D.Double vecinoMasCercano (Node nodo, Point2D.Double point){
		Actual actual = new Actual();
		iterador(nodo, point, actual);

		return actual.mejorActual;
	}
	
	void iterador(Node nodo, Point2D.Double point, Actual actual){
		//Aun no se llega a la region de point => no se tiene actual
		if (nodo.isLeaf()){ //Llega a la region
			try {
				actual.mejorActual = nodo.getPoint();
				actual.distActual = nodo.getPoint().distance(point);
			} catch (NotALeafException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Si no es hoja
		else {
			if(nodo.getX() != -1) // si la linea es vertical
				if (point.getX() <= nodo.getX()){ // si point.X < nodo.X
					iterador(nodo.getLeft(), point, actual);
					//Ya tengo un Actual, intento mejorar
					//Reviso hijo que falta, se que point.X < nodo.X
					if (point.getX() + actual.distActual > nodo.getX())
						buscarMejor(nodo.getRight(), point, actual);
				}
				else{
					iterador(nodo.getRight(), point, actual);
					//Ya tengo un Actual, intento mejorar
					//Reviso hijo que falta, se que nodo.X < point.X
					if (point.getX() - actual.distActual < nodo.getX())
						buscarMejor(nodo.getLeft(), point, actual);
				}
			else // si la linea es horizontal
				if (point.getY() <= nodo.getY()){
					iterador(nodo.getLeft(), point, actual);
					//Ya tengo un Actual, intento mejorar
					//Reviso hijo que falta, se que point.Y < nodo.Y
					if (point.getY() + actual.distActual > nodo.getY())
						buscarMejor(nodo.getRight(), point, actual);
				}
				else{
					iterador(nodo.getRight(), point, actual);
					//Ya tengo un Actual, intento mejorar
					//Reviso hijo que falta, se que nodo.Y < point.Y
					if (point.getY() - actual.distActual < nodo.getY())
						buscarMejor(nodo.getLeft(), point, actual);
				}
			
		}
	}
	
	void buscarMejor(Node nodo, Point2D.Double point, Actual actual){
		double pointCoord;
		double axis;
		
		if (nodo.isLeaf()){
			double newDist;
			try {
				newDist = nodo.getPoint().distance(point);
				if (newDist < actual.distActual){
					actual.mejorActual = nodo.getPoint();
					actual.distActual = newDist;
				}
			} catch (NotALeafException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(nodo.getX() != -1){ // si se compara con X
			pointCoord = point.getX();
			axis = nodo.getX();
		}
		else{ // si se compara con Y
			pointCoord = point.getY();
			axis = nodo.getY();
		}
		// si intersecta linea
		if (pointCoord-actual.distActual < axis && axis <= pointCoord+actual.distActual){
			buscarMejor(nodo.getLeft(), point, actual);
			buscarMejor(nodo.getRight(), point, actual);//aqui
		}
		// si no intersecta y pointCoord < axis
		else if (pointCoord <= axis){
			buscarMejor(nodo.getLeft(), point, actual);
		}
		// si no intersecta y pointCoord > axis
		else {
			buscarMejor(nodo.getRight(), point, actual);
		}
	}
	public void setPoints(ArrayList<Double> newPoints) {
		this.points=newPoints;
		
	}


}

