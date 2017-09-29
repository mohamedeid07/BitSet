package sets;

import java.util.ArrayList;
import iSets.ISet;

public class Set implements ISet {
	
	private int sizeOfUniverse; 
	final int intBits = 32;
	private int size ;
	int[][] subsets;
	int[] result;
	public ArrayList<String> universe;
	
	public Set(int sizeOfUniverse,int subsetsNumber) {
		this.sizeOfUniverse = sizeOfUniverse;
		this.size = (sizeOfUniverse - 1) / intBits + 1;
		subsets = new int[subsetsNumber][size];
		result = new int[size];
		universe = new ArrayList<String>(sizeOfUniverse);
	}
	
	@Override
	public void setBit(int[] array, int index) {
		array[index / intBits] |= 1 << (index % intBits);
	}

	@Override
	public boolean isElement(int[] array, int index) {
		if ((array[index / intBits] & (1 << (index % intBits))) == 0) {
			return false;
		}
		return true;
	}
	
	@Override
	public void union(int n1, int n2) {
		int[] array1 = new int[size], array2 = new int[size];
		if (n1 == -1) {
			array1 = result;
		} else {
			array1 = this.subsets[n1];
		}
		if (n2 == -1) {
			array2 = result;
		} else {
			array2 = this.subsets[n2];
		}
		for(int i = 0; i < size; i++) {
			result[i] = array1[i] | array2[i];
		}
	}
	
	@Override
	public void intersection(int n1, int n2) {
		int[] array1 = new int[size], array2 = new int[size];
		if (n1 == -1) {
			array1 = result;
		} else {
			array1 = this.subsets[n1];
		}
		if (n2 == -1) {
			array2 = result;
		} else {
			array2 = this.subsets[n2];
		}
		for(int i = 0; i < size; i++) {
			result[i] = array1[i] & array2[i];
		}
	}
	
	@Override
	public void complement(int n) {
		int[] array = new int[size];
		if (n == -1) {
			array = result;
		} else {
			array = this.subsets[n];
		}
		for(int i = 0; i < size; i++) {
			result[i] = ~ array[i];
		}
	}
	
	private boolean isPhi(int[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] != 0) return false;
		}
		return true;
	}
	
	public void printSet(int x) {
		int[] array;
		if (x == -1) {
			array = result;
		} else {
			array = subsets[x];
		}
		
		if (isPhi(array)) {
			System.out.print("subset(" + x + ")" + " = {}");
		} else {
			System.out.print("subset(" + x + ")" + " = {");
			int i = 0;
			while (!isElement(array, i)) {
				i++;
			}
			System.out.print(universe.get(i));
			i++;
			while (i < sizeOfUniverse) {
				if (isElement(array, i)) {
					System.out.print(", " + universe.get(i));
				}
				i++;
			}
			System.out.println("}");
		}
		
	}

}
