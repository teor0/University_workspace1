package Algo;
import java.util.*;
public class AlberoBinLF<T> extends AlberoLF<T> implements AlberoBin<T>{

	public AlberoBinLF() {
		super(2);
	}
	
	

	public AlberoBinLF(T val) {
		super(2, val);
	}



	@SuppressWarnings("unchecked")
	@Override
	public AlberoBin<T> padreBin() {
		// TODO Auto-generated method stub
		return (AlberoBin<T>) padre;
	}

	@Override
	public AlberoBin<T> sin() {
		// TODO Auto-generated method stub
		return (AlberoBin<T>) figlio(0);
	}

	@Override
	public AlberoBin<T> des() {
		// TODO Auto-generated method stub
		return (AlberoBin<T>) figlio(1);
	}

	public void setDes(AlberoBinLF<T> a){
		try {
			setFiglio(a,1);
		} catch (AlberiDiversiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setSin(AlberoBinLF<T> a){
		try {
			setFiglio(a,0);
		} catch (AlberiDiversiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<T> visitaInfissa() {
		List<T> l = new LinkedList<T>();
		visitaInfissa(l);
		return l;
	}
	
	private void visitaInfissa(List<T> l){
		AlberoBinLF<T> s = (AlberoBinLF<T>) sin();
		if (s!=null) s.visitaInfissa(l);
		l.add(val());
		AlberoBinLF<T> d = (AlberoBinLF<T>) des();
		if (d!=null) d.visitaInfissa(l);
	}

	@Override
	public Iterator<T> iteratorVI() {
		
		return new IteratorVP(this);
	}
	
	protected class IteratorVI implements Iterator<T>{
		
		protected static final int SIN = 0;
		protected static final int DES = 1;
		protected static final int SU = 2;
		protected static final int STOP = 3;
		
		protected AlberoBin<T> curr;
		protected boolean hasNext;
		
		public IteratorVI(AlberoBin<T> a){
			curr=a;
			//while(curr.sin()!=null) curr= curr.sin();
			hasNext=true;
		}


		private void succ() {
			int dir = SIN;
			while(dir!=STOP){
				switch (dir){
				case DES:
					if(curr.des()==null){
						dir=SU;
					}else{
						curr=curr.des();
						dir=SIN;
					}
					break;
				case SIN:
					if(curr.sin()==null){
						hasNext=true;
						dir=STOP;
					}else{
						curr= curr.sin();
						dir=SIN;
					}
					break;
				case SU:
					if(curr.padre()==null){
						hasNext=false;
						dir=STOP;
					}else{
						if(((AlberoBin<T>) curr.padre()).sin() ==curr){
							curr=(AlberoBin<T>) curr.padre();
							hasNext=true;
							dir=STOP;
						}else{
							curr=(AlberoBin<T>) curr.padre();
							dir = SU;
						}
					}
					break;
				}
			}
			
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return hasNext;
		}

		@Override
		public T next() {
			if(!hasNext()) return null;
			T tmp = curr.val();
			succ();
			return tmp;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
	}
	
	public String toString() {
		return this.val.toString();
	}
	
protected class IteratorVP implements Iterator<T>{
		
		protected static final int SIN = 0;
		protected static final int DES = 1;
		protected static final int SU = 2;
		protected static final int STOP = 3;
		
		protected AlberoBin<T> curr;
		protected boolean hasNext;
		
		public IteratorVP(AlberoBin<T> a){
			curr=a;
			//while(curr.sin()!=null) curr= curr.sin();
			hasNext=true;
		}
		
		/*private void succ() {
			int dir=SIN;
			while(dir!=STOP){
				switch (dir){
				case DES:
					if(curr.des()==null){
						dir=SU;
					}else{
						curr=curr.des();
						hasNext=true;
						dir=SIN;
					}
					break;
				case SIN:
					if(curr.sin()==null){
						dir=STOP;
					}else{
						curr= curr.sin();
						hasNext=true;
						dir=SIN;
					}
					break;
				case SU:
					if(curr.padre()==null){
						hasNext=false;
						dir=STOP;
					}else{
						if(((AlberoBin<T>) curr.padre()).sin() ==curr){
							curr=(AlberoBin<T>) curr.padre();
							dir=DES;
						}else{
							curr=(AlberoBin<T>) curr.padre();
							hasNext=true;
							dir = SU;
						}
					}
					break;
				}
			}
		}//succ*/

		private void succ() {
			int dir=SIN;
			while(dir!=STOP){
				switch (dir){
				case DES:
					if(curr.des()==null){
						hasNext=true;
						dir=SU;
					}else{
						curr=curr.des();
						dir=SIN;
					}
					break;
				case SIN:
					if(curr.sin()==null){
						dir=STOP;
					}else{
						curr= curr.sin();
						dir=SIN;
					}
					break;
				case SU:
					if(curr.padre()==null){
						hasNext=false;
						dir=STOP;
					}else{
						if(((AlberoBin<T>) curr.padre()).sin() ==curr){
							curr=(AlberoBin<T>) curr.padre();
							dir=DES;
						}else{
							curr=(AlberoBin<T>) curr.padre();
							hasNext=true;
							dir = STOP;
						}
					}
					break;
				}
			}
		}//succ
		
		

		@Override
		public boolean hasNext() {
			return hasNext;
		}

		@Override
		public T next() {
			if(!hasNext()) return null;
			succ();
			T tmp = curr.val();
			return tmp;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
	}

	
	
	public static void main(String[] args) throws AlberiDiversiException {
		AlberoBinLF<Integer> a=new AlberoBinLF<>(7);
		AlberoBinLF<Integer> fa=new AlberoBinLF<>(2);
		AlberoBinLF<Integer> fb=new AlberoBinLF<>(2);
		fa.val=3;
		fb.val=10;
		a.setSin(fa);
		a.setDes(fb);
		Iterator<Integer> it=a.iteratorVI();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
}