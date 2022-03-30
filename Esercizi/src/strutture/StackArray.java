package strutture;
import java.util.*;
public class StackArray<T> implements Iterable<T>{
	
	private T[] stack;
	private int size=0; //indica il primo elemento libero
	
	@SuppressWarnings("unchecked")
	public StackArray(int n){
		if(n<1)
			throw new IllegalArgumentException();
		stack=(T[]) new Object[n];
	}
	
	public StackArray(){
		this(10);
	}
	
	@SuppressWarnings("unchecked")
	public void clear(){
		stack=(T[]) new Object[stack.length];
		size=0;
	}
	
	public int size(){
		return size;
	}
	
	public void push(T e){
		if(size==stack.length)
			java.util.Arrays.copyOf(stack, size*2);
		stack[size]=e;
		size++;
	}
	
	public T peek(){
		if(size==0)
			throw new NoSuchElementException();
		return stack[size-1];
	}
	
	public T pop(){
		if(size==0)
			throw new NoSuchElementException();
		T ret=stack[size-1];
		stack[size-1]=null;
		size--;
		return ret;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder(30);
		sb.append("[");
		Iterator<T> it=iterator();
		while(it.hasNext()){
			sb.append(it.next());
			if(it.hasNext())
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public Iterator<T> iterator(){
		return new IteratoreStack();
	}
	
	private class IteratoreStack implements Iterator<T>{
		
		private int cor=size;
		private boolean rem=false;
		
		public boolean hasNext(){
			if(cor==size)
				return size>0;
			return cor>0;
		}
		
		public T next(){
			if(!hasNext())
				throw new NoSuchElementException();
			rem=true;
			cor--;
			return stack[cor];
		}
		
		public void remove(){
			if(!rem)
				throw new IllegalStateException();
			rem=false;
			for(int i=cor+1; i<size; i++)
				stack[i-1]=stack[i];
			stack[size-1]=null;
			size--;
		}
	}//iteratore
	
	public static void main(String[] args){
		StackArray<Integer> sa=new StackArray<>(13);
		sa.push(34);
		sa.push(2);
		sa.push(5);
		System.out.println("size="+sa.size());
		System.out.println("elemento affiorante " +sa.peek());
		System.out.println(sa.toString());
		for(int i=1; i<6; i++){
			int m=(i*7)+1;
			sa.push(m);
		}
		System.out.println(sa.toString());
		System.out.println("elemento affiorante " +sa.peek());
		sa.pop();
		sa.pop();
		sa.pop();
		System.out.println(sa.toString());
		System.out.println("elemento affiorante " +sa.peek());
		System.out.println("size="+sa.size());
	}//main
}

