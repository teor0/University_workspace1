package poo.eratostene;
import poo.util.*;
public interface CrivelloOrdinato<T extends Comparable<? super T>> extends CollezioneOrdinata<T>{
	default int size() {
		int ret=0;
		for(T x:this)
			ret++;
		return ret;
	}
	void filtra();
	
}
