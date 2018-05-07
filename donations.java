/*LaToya Anderson
 * CISC 1115
 * May 2, 2018
 * 
 * Donations is a programs that reads in a file of ID numbers and their
 * donations, sorts them by ID number, re-sorts them by donation
 * amounts and prints the unsorted, sorted-by-ID, and sorted-by-donations
 * lists in tabular form
 */

import java.io.*;
import java.util.Scanner;


public class donations {
	
	//method readIn
	/*Input - idNumbers - integer array of ID's
	 * 		  donations - double array of donations
	 * Process - Reads in input file of ID's and their
	 * 				donations into two parallel arrays
	 * Output - Returns total number of data sets read in
	 * 			to main
	 * 
	 */
	public static int readIn(int[] idNumbers, double[] donations)
		throws IOException{
		
		//counter for total number of data sets read in
		int index = 0;
		
		//Instantiates File class to read in input file
		File donorFile = new File("/home/latoya/workspace/Donations" +
				"/DonationInfo.txt");
		
		//Instantiate Scanner object to read file
		Scanner inputFile = new Scanner(donorFile);
		
		//Read in file into arrays until end of file
		while(inputFile.hasNext()){
			
			//array of id numbers
			idNumbers[index] = inputFile.nextInt();
			
			//Array donations
			donations[index] = inputFile.nextDouble();
			
			//Tracks number of data sets read in
			index++;
		}
		
		inputFile.close();
		
		//Returns total data set to main
		return index;
	}
	
	//method printArrays
	/* Input - idNumbers - array of ID's
	 * 		   donations - array of donations
	 * Process - Prints parallel arrays in
	 * 			 tabular form
	 * Output - Void return - returns nothing
	 * 
	 */
	public static void printArrays(int[] idNumbers, double[] donations, 
			int donorCount, PrintWriter outPutInfo) throws IOException{
		
		
		 //Title of data set
		//outPutInfo.println("Donations\n");
		//Headers for ID's and donations
		outPutInfo.println("ID Numbers\tDonations"); 
		
		//Prints formatted ID numbers and donations
		for(int index = 0; index < donorCount; index++){
			outPutInfo.printf("%6d", idNumbers[index]);
			outPutInfo.printf("%12s",'$');
			outPutInfo.printf("%.2f\n",donations[index]);
		}
		
		outPutInfo.println("\n");
		System.out.println("End of File\n");
		
		
		/*Title of data set
		System.out.println("Donations\n");
		//Headers for ID's and donations
		System.out.println("ID Numbers\tDonations"); 
				
		//Prints formatted ID numbers and donations
		for(int index = 0; index < donorCount; index++){
			System.out.printf("%6d", idNumbers[index]);
			System.out.printf("%12s",'$');
			System.out.printf("%.2f\n",donations[index]);
		}
				
		System.out.println("\n");*/
		
		outPutInfo.flush();
		//outPutInfo.close();
	}

//Method for selection sort by ID
	/*Input: idNumbers - array of ID's
	 * 		 donations - array of donations
	 * 		 donorCounts - integer of total data set	
	 * Process: Sorts data by ID using selection sort 
	 * Output: Void return
	 */
	public static void selectionSortID(int[] idNumbers,
			double[] donations, int donorCounts){
		//temp variable for IDs
		int tempID;
		
		//temp variable for donations
		double tempDonations;
		
		//sorts by ID in ascending order
		for(int pos = 0; pos < donorCounts; pos++)
			for(int cand = (pos+1); cand < donorCounts; cand++)
				if(idNumbers[pos]>idNumbers[cand])
				{
					tempID = idNumbers[pos];
					tempDonations = donations[pos];
					
					idNumbers[pos] = idNumbers[cand];
					donations[pos] = donations[cand];
					
					idNumbers[cand] = tempID;
					donations[cand] = tempDonations;
				}
	}
	
	//Method for bubble sort by donation
		/*Input: idNumbers - array of ID's
		 * 		 donations - array of donations
		 * 		 donorCounts - integer of total data set	
		 * Process: Sorts data by donation using linear sort 
		 * Output: Void return
		 */
	public static void bubbleSortDonation(int[] IDnumbers, 
			double[] donations, int donorCount){
		
		//Control variable to fall out of while loop
		boolean swapped;
		
		//temporary variable for IDs
		int tempID;
		
		//temporary variable for donations
		double tempDonations;
		
		//Sorts by donation amount in ascending order
		do{
			swapped = false;
			
			for(int pos = 0; pos < (donorCount-1); pos++)
				if(donations[pos] > donations[pos+1])
				{
					tempDonations = donations[pos];
					tempID = IDnumbers[pos];
					
					donations[pos] = donations[pos+1];
					IDnumbers[pos] = IDnumbers[pos+1];
					
					donations[pos+1] = tempDonations;
					IDnumbers[pos+1] = tempID;
					
					swapped = true;
				}
		}while(swapped);
		
	}
	public static void main(String[] args) throws IOException {
		
		//Holds total data set
		int donorCount;
		
		final int MAX = 100;
		
		//array of id's
		int[] idNumbers = new int[MAX];
		
		//Array of donations
		double[] donations = new double[MAX];
		
		
		//Instantiates PrintWriter and creates new text file
		PrintWriter outPutInfo = new PrintWriter("/home/latoya/workspace/" +
			"Donations/src/SortedDonationInfo.txt");
		
		//Method reads in file and returns last array index
		donorCount = readIn(idNumbers,donations);
		
		//Title of data set
		outPutInfo.println("Unsorted Donations\n");
		//Method prints array
		printArrays(idNumbers,donations,donorCount,outPutInfo);
		
		//Method sorts data by ID using selection sort
		selectionSortID(idNumbers, donations, donorCount);
		
		//Title of data set
		outPutInfo.println("Donations Sorted by ID\n");
		//Method prints array after sorted by ID
		printArrays(idNumbers,donations,donorCount,outPutInfo);
		
		//Method sorts data by donations using bubble sort
		bubbleSortDonation(idNumbers, donations, donorCount);
		
		//Title of data set
		outPutInfo.println("Donations Sorted by Amounts\n");
		//Method prints array after sorted by donations
		printArrays(idNumbers,donations,donorCount,outPutInfo);
		
		outPutInfo.close();
	}

}
