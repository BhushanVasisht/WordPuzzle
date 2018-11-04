/**
 * CrossWord WordPuzzle game to search for words in a modern English dictionary 
 * Written by Bhushan S Vasisht, Net-ID-bsv180000, started writing 10/29/2018
 * Implements a user defined grid and randomly populates the grid
 * A Dictionary is used to check for words inside the grid along with the orientation
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	
	//the 2D matrix to store the characters
	public char[][] wordGrid;
	
	//number of cols of the wordgrid
	private int cols;
	
	//number of columns of the wordgrid
	private int rows;
	
	//the alphabet string
	private String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	/**
	 * Constructor of WordPuzzle class
	 * It initializes the wordgrid matrix for 10000 cols and 10000 columns
	 */
	public WordPuzzle()
	{
		this.wordGrid = new char[10000][10000];
	}
	
	public String reverse(String s)
	{
		String r = "";
		for(int i=s.length()-1; i>= 0; i--)
		{
			r += s.charAt(i);
		}
		
		return r;
	}
	
	
	/**
	 * Function to generate the grid using random letters from the alphabet string
	 * @param row
	 * @param col
	 */
	public void generateGrid(int row, int col)
	{
		this.cols = row;
		this.rows = col;
		
		Random r = new Random();
		
		for(int i = 0; i<= row; i++)
		{
			for(int j=0; j<= col; j++)
			{
				int x = r.nextInt(alphabet.length());
				this.wordGrid[i][j] = alphabet.charAt(x);
			}
		}
	}
	
	/**
	 * Function to display the grid with a visual effect.
	 * Reads the matrix and prints it by looping through cols and columns
	 */
	public void printGrid()
	{	
		for(int j=0; j < rows; j++)
		{
			int curr = 0;
			while(curr != cols)
			{
				System.out.print(this.wordGrid[curr][j] + "\t");
				curr++;
			}
			System.out.println("\n");
			
		}
		System.out.println("---------------------------The words found using dictionary are as below-----------------------------------------------");
	}
	
	public void solvePuzzle(int size)
	{
		MyHashTable ht = new MyHashTable();
		
		File fl = new File("dictionary.txt");
		
		try {
			//Initiate a scanner to read the dictionary file
	        Scanner sc = new Scanner(fl);

	        while (sc.hasNextLine()) 
	        {
	            String line = sc.nextLine();
	            
	             boolean addStat = ht.addToTable(line);
	             
	             if(addStat == false)
	            	 System.out.println("Could not add " + line +" to table");   
	        }
	        sc.close();
	        
	        //vertical search
	        int curc1 = 0;
	        while(curc1 != rows)
	        {
	        	String temp = "";
	        	for(int i = 0; i< cols; i++)
	        	{
	        		temp += wordGrid[i][curc1];
	        		
	        		String tableval = ht.getString(temp);
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(temp);
	        		}
	        		
	        		tableval = ht.getString(reverse(temp));
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(reverse(temp));
	        		}
	        		
	        		
	        	}
	        	curc1++;
	        }
	        
	        int curc2 = 0;
	        while(curc2 != rows)
	        {
	        	String temp = "";
	        	for(int i = cols-1; i>= 0; i--)
	        	{
	        		temp += wordGrid[i][curc2];
	        		
	        		String tableval = ht.getString(temp);
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(temp);
	        		}
	        		
	        		tableval = ht.getString(reverse(temp));
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(reverse(temp));
	        		}
	        		
	        	}
	        	curc2++;
	        }
	        
	        
	        //horizontal search
	        int curr1 = 0;
	        while(curr1 != cols)
	        {
	        	String temp = "";
	        	for(int i = 0; i< rows; i++)
	        	{
	        		temp += wordGrid[curr1][i];
	        		
	        		String tableval = ht.getString(temp);
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(temp);
	        		}
	        		
	        		tableval = ht.getString(reverse(temp));
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(reverse(temp));
	        		}
	        		
	        	}
	        	curr1++;
	        }
	        
	        int curr2 = 0;
	        while(curr2 != cols)
	        {
	        	String temp = "";
	        	for(int i = rows-1; i >= 0; i--)
	        	{
	        		temp += wordGrid[curr2][i];
	        		
	        		String tableval = ht.getString(temp);
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(temp);
	        		}
	        		
	        		tableval = ht.getString(reverse(temp));
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(reverse(temp));
	        		}
	        		
	        	}
	        	curr2++;
	        }
	        
	        //diagonally to the left upper portion of the matrix
	        for(int k = cols-1; k>= 0; k--)
	        {
	        	int i = k;
	        	int j = 0;
	        	String temp ="";
	        			
	        	while(i >= 0)
	        	{	
	        		temp += wordGrid[i][j];
	        		String tableval = ht.getString(temp);
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(temp);
	        		}
	        		
	        		tableval = ht.getString(reverse(temp));
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(reverse(temp));
	        		}
	        		
	        		i = i - 1;
		        	j = j + 1;
	        	}
	        	
	        }
	        
	        //diagonally to the left lower portion of the matrix
	        for(int k =1; k <= rows-1; k++)
	        {
	        	int i = rows - 1;
	        	int j = k;
	        	String temp = "";
	        	
	        	while(j <= rows - 1)
	        	{
	        		temp += wordGrid[i][j];
	        		String tableval = ht.getString(temp);
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(temp);
	        		}
	        		
	        		tableval = ht.getString(reverse(temp));
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(reverse(temp));
	        		}
	        		
		        	j = j + 1;
		        	i = i - 1;
	        	}
	        }
	        
	        
	        //Diagonally to the right upper portion of the matrix
	        for(int k=0; k <= cols-1; k++)
	        {
	        	int i=0;
	        	int j=k;
	        	String temp = "";
	        	
	        	while(j <= rows - 1)
	        	{
	        		temp += wordGrid[i][j];
	        		String tableval = ht.getString(temp);
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(temp);
	        		}
	        		
	        		tableval = ht.getString(reverse(temp));
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(reverse(temp));
	        		}
	        		
		        	j = j + 1;
		        	i = i + 1;
	        	}
	        }
	        
	        //diagonally to the right lower portion of the matrix
	        for(int k= 1; k <= cols-1; k++)
	        {
	        	int i = k;
	        	int j = 0;
	        	String temp = "";
	        	
	        	while(i <= rows - 1)
	        	{
	        		temp += wordGrid[i][j];
	        		String tableval = ht.getString(temp);
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(temp);
	        		}
	        		
	        		tableval = ht.getString(reverse(temp));
	        		
	        		if (tableval != "" && temp.length() >= size)
	        		{
	        			System.out.println(reverse(temp));
	        		}
	        		
		        	j = j + 1;
		        	i = i + 1;
	        	}
	        	
	        }
	        
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("Welcome to WordPuzzle");
		
		WordPuzzle wp = new WordPuzzle();
		
		//Initiate a Scanner to read user inputs
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please Enter the number of rows and columns of the puzzle u need");
		
		int m = sc.nextInt();
		int n = sc.nextInt();
		
		//Minimum size of words to search for in the dictionary
		System.out.println("Please enter the minimum length for the search terms");
		int size = sc.nextInt();
		
		wp.generateGrid(m,n);
		
		wp.printGrid();
		
		wp.solvePuzzle(size);
		
		System.out.println("------------------------------End of word puzzle--------------------------------");
	}

}
