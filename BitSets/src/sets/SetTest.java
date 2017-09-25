package sets;

import java.util.Scanner;

public class SetTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the size of the universe: ");
		int sizeOfUniverse;
		sizeOfUniverse = scan.nextInt();
		scan.nextLine();
		System.out.println();
		System.out.print("Enter the number of sets: ");
		int subsetsNumber;
		subsetsNumber = scan.nextInt();
		scan.nextLine();
		System.out.println();
		Set set = new Set(sizeOfUniverse,subsetsNumber);
		System.out.println("Scanning universe ...\nEnter each element line by line:");
		for(int i = 0; i < sizeOfUniverse;) {
			String tempElement = scan.nextLine();
			if (!set.universe.contains(tempElement)) {
				set.universe.add(tempElement);
				i++;
			}
		}
		System.out.println("Scanning universe is done.");
		
		for (int i = 0; i < subsetsNumber; i++) {
			System.out.println("Scanning subset ("+i+")...");
			System.out.println("Enter the indexes of the elements in the universe from");
			System.out.println("1 to " + sizeOfUniverse);
			System.out.println("Enter -1 to end.");
			while (true) {
				int tempIndex = scan.nextInt();
				if (tempIndex == -1) {
					break;
				}
				if (tempIndex > 0 && tempIndex <= sizeOfUniverse) {
					set.setBit(set.subsets[i], tempIndex - 1);
				} else {
					System.out.println(tempIndex + " is out of bounds !");
				}
				scan.nextLine();
			}
			System.out.println("Scanning set ("+i+") is done.");
			System.out.println("/t/t------------------------------------------");
		}
		
		int option;
		int set1, set2;
		boolean flag = true;
		while (flag) {
			for (int i = 0; i < subsetsNumber; i++) {
				set.printSet(i);
			}
			set.printSet(-1);
			System.out.println("/t/t------------------------------------------");
			System.out.println("Choose your option:\n1- Union.\n2- Intersection.\n3- Complement.\n4- Exit.");
			option = scan.nextInt();
			scan.nextLine();
			
			switch(option) {
				case 1:
					System.out.println("Choose two sets by index (ps: result is '-1') ");
					set1 = scan.nextInt();
					set2 = scan.nextInt();
					scan.nextLine();
					set.union(set1, set2);
					break;
				case 2:
					System.out.println("Choose two sets by index (ps: result is '-1') ");
					set1 = scan.nextInt();
					set2 = scan.nextInt();
					scan.nextLine();
					set.intersection(set1, set2);
					break;
				case 3:
					System.out.println("Choose one set by index (ps: result is '-1') ");
					set1 = scan.nextInt();
					scan.nextLine();
					set.complement(set1);
					break;
				case 4:
					flag = false;
					break;
				default:
					continue;
			}
		}
		scan.close();
	}
}
