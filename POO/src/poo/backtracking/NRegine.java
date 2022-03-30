package poo.backtracking;
import java.util.*;
public class NRegine extends Backtracking<Integer,Integer>{
	private boolean [][]board;
	private int n, nrSol=0;
	//punto di scelta una riga, una scelta è una colonna
	public NRegine(int n) {
		if( n<=3 ) 
			throw new IllegalArgumentException();
		board=new boolean[n][n];
		this.n=n;
	}
	
	@Override
	public List<Integer> puntiDiScelta(){ //sono gli indici di riga che posso scegliere.
		//quindi sono da 0 ad n
		ArrayList<Integer> ps=new ArrayList<>();
		for( int i=0; i<n; ++i ) 
			ps.add(i);
		return ps;
	}
	@Override
	public Collection<Integer> scelte( Integer p ){
		//le scelte in questo caso sono le colonne. in questo caso da 0 a n
		List<Integer> s=new ArrayList<>();
		for( int i=0; i<n; ++i ) s.add(i);
		return s;		
	}
	
	@Override
	public void risolvi() {
		tentativo( puntiDiScelta(), 0 );
	}//risolvi
	
	
	@Override
	protected boolean esisteSoluzione( Integer p ) {
		return p==board.length-1;
	}//esisteSoluzione
	
	
	
	protected boolean ultimaSoluzione(Integer p) {
		return nrSol==4;
		//cosi si vogliono solo 4 soluzioni 
	}
	
	@Override
	protected boolean assegnabile( Integer r, Integer c ) {
		//e assegnabile una regina sulla colonna c della riga r?
		//verifica i vincoli di essere sotto attacco
		//verifica attacco a NORD
		for( int i=r-1; i>=0; --i )
			if( board[i][c] ) 
				return false;
		//verifica attacco a NORD-OVEST
		for( int i=r-1,j=c-1; i>=0 && j>=0; --i,--j )
			if( board[i][j] ) 
				return false;
		//verifica attacco a NORD-EST
		for( int i=r-1,j=c+1; i>=0 && j<=n-1; --i,++j )
			if( board[i][j] ) 
				return false;
		return true;
	}//assegnabile
	@Override
	protected void assegna( Integer r, Integer c ) {
		board[r][c]=true;
	}//assegna
	@Override
	protected void deassegna( Integer r, Integer c ) {
		board[r][c]=false;
	}//deassegna
	@Override
	protected void scriviSoluzione( Integer p ) {
		nrSol++;
		System.out.print(nrSol+": ");
		for( int i=0; i<n; ++i ) {
			for( int j=0; j<n; ++j )
				if( board[i][j] ) {
					System.out.print("<"+i+","+j+">");
					break; //interrompe il ciclo di for interno
				}		
		}
		System.out.println();
	}//scriviSoluzione
	
	public static void main( String[] args ) {
		new NRegine(8).risolvi();
	}//main
	
}//NRegine
