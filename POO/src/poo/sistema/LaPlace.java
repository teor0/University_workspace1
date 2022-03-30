package poo.sistema;


public class LaPlace {
	
	public static int soluzioneLaplace(Integer m[][]) {
		if((m.length !=m[0].length)|| m.length==0)
			throw new IllegalArgumentException();
		if(m.length==1)
			return m[0][0];
		return calcolo(m,0,0,0);	
		}
	
	private static int calcolo(Integer m[][], int i, int j, int det) {
		if(i==m.length)
			return det;
		det+=calcoloMinori(m,i,j);
		return calcolo(m,i+1,j,det);
	}
	
	private static int calcoloMinori(Integer m[][], int i, int j) {
		int n= m.length;
		Integer copia[][]=new Integer[n][n];
		for(int c=0; c<n; c++)
			for(int r=0; r<n; r++)
				copia[c][r]=m[c][r];
		Integer k[][]= new Integer[n-1][n-1];
		int ele=m[i][j];
		double p= (double)i+j;
		for(int r=0; r<n; r++)//eliminazione riga
			copia[i][r]=null;
		for(int c=0; c<n; c++)
			copia[c][j]=null;
		int ik=0;
		int jk=0;
		for(int c=0; c<n; c++)
			for(int r=0; r<n; r++)
				if(copia[c][r]!=null) {
					k[ik][jk]=copia[c][r];
					if(jk==k.length-1) {
						ik++; 
						jk=0;
					}
					else
						jk++;
				}
		if(k.length>2)
			return calcoloMinori(k,0,0);
		return ele* (int) Math.pow(-1.00,p)*determinante(k);
	}
	
	
	private static int determinante(Integer m [][]) {
		int ret=0;
		if(m.length!=2)
			throw new IllegalArgumentException();
		ret=m[0][0]*m[1][1]-(m[0][1]*m[1][0]);		
		return ret;
	}
	public static void main(String[] args){
		Integer matrix[][]=
			{{1,0,5},
			 {2,-1,0},
			 {7,-2,0}};
		int s=soluzioneLaplace(matrix);
		System.out.println(s);
	}

}
