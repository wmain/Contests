/*
ID: arjvik1
LANG: JAVA
TASK: Permutations
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Permutations {
	

	public static void main(String[] args) throws IOException {
		
		
		@SuppressWarnings("serial")
		List<Integer> vals = new ArrayList<Integer>(){{
			add(1);
			add(2);
			add(3);
			add(4);
		}};
		permExactlytN(vals,2)
				.stream()
				.forEachOrdered(System.out::println);
	}
	public static <T> Set<Set<T>> getAllSubsets(List<T> vals){
		if(vals.size()==1){
			Set<Set<T>> s = new HashSet<>();
			s.add(new HashSet<>(vals));
			s.add(new HashSet<>());
			return s;
		} else if(vals.size()==0){
			Set<Set<T>> s = new HashSet<>();
			s.add(new HashSet<>());
			return s;
		}
		
		Set<Set<T>> r = new HashSet<>();
		T t = vals.get(0);
		List<T> vals2 = new ArrayList<>(vals);
		vals2.remove(0);
		Set<Set<T>> withoutT = getAllSubsets(vals2);
		for (Set<T> set : withoutT) {
			r.add(set);
			Set<T> withT = new HashSet<>(set);
			withT.add(t);
			r.add(withT);
		}
		return r;
	}
	public static <T> Set<Set<T>> chooseAtMostN(List<T> vals, int n){
		return getAllSubsets(vals).stream()
								  .filter(s -> s.size() <= n)
								  .collect(Collectors.toSet());
	}
	public static <T> Set<Set<T>> chooseAtLeastN(List<T> vals, int n){
		return getAllSubsets(vals).stream()
								  .filter(s -> s.size() >= n)
								  .collect(Collectors.toSet());
	}
	public static <T> Set<Set<T>> chooseExactlytN(List<T> vals, int n){
		return getAllSubsets(vals).stream()
								  .filter(s -> s.size() == n)
								  .collect(Collectors.toSet());
	}
	
	//https://stackoverflow.com/a/10305419/2600184
	public static <E> Set<List<E>> getAllPermutations(List<E> original) {
		if (original.size() == 0) {
			Set<List<E>> result = new HashSet<List<E>>(); 
			result.add(new ArrayList<E>()); 
			return result; 
		}
		E firstElement = original.remove(0);
		Set<List<E>> returnValue = new HashSet<List<E>>();
		Set<List<E>> permutations = getAllPermutations(original);
		for (List<E> smallerPermutated : permutations) {
			for (int index=0; index <= smallerPermutated.size(); index++) {
				List<E> temp = new ArrayList<E>(smallerPermutated);
				temp.add(index, firstElement);
				returnValue.add(temp);
			}
		}
		return returnValue;
	}
	public static <T> Set<List<T>> permExactlytN(List<T> vals, int n){
		return getAllPermutations(vals).stream()
									   .sequential()
									   .map(s -> s.subList(0, n))
									   .collect(Collectors.toSet());
	}
	
	//use myint for when dupes are needed in subsets
		static class MyInt{
			int i;
			int hashCode;
			static int hashMapCounter = 1;
			public MyInt(int j) {
				i = j;
				hashCode = hashMapCounter++;
			}
			@Override
			public int hashCode() {
				return hashCode;
			}
			public String toString() {
				return Integer.toString(i);
			}
		}
}