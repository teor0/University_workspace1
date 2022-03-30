package esempi;

public class OccorrenzeMatrice {

	public static void main(String[] args) {
		
		int[][] m= {{1,2,3,4},
					{2,4,2,9},
					{7,1,8,1},
					{2,4,4,4},
					{0,2,4,1}};
		int x=4;
		int y=2;
		OccorrenzeXY[] occ= new OccorrenzeXY[m.length];
		for(int i=0; i<m.length; i++) {
			occ[i]=new OccorrenzeXY(x,y,m[0].length,m[i]);
			occ[i].start();
		}
		int cX=0;
		int cY=0;
		for(int j=0; j<occ.length; j++) {
			cX+=occ[j].getX();
			cY+=occ[j].getY();
		}
		System.out.println(cX>cY);
		if(cX>cY)
			System.out.println("Le occorrenze di x sono: "+ cX+ " e sono maggiori di quelle di y "+ cY);
		else
			System.out.println("Le occorrenze di x: " +cX +" non superano quelle di y: "+ cY);
	}

}
