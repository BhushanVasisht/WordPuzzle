import java.util.HashMap;
import java.util.Map;

/**
 * MyHashTable class implementation for the project WordPuzzle using default HashMap implementation of map interface
 * @author bhushanvasisht
 *
 * @param <AnyType>
 */

public class MyHashTable
{
	private static int DEFAULT_TABLE_SIZE = 1010101;

    private Map<Integer, String> arrayTable; // The array of elements
    private int theSize;      // Current size
	
	//basic constructor
    MyHashTable()
	{
		this(DEFAULT_TABLE_SIZE);
	}
	
	//Overridden constructor
	MyHashTable(int i)
	{
		this.arrayTable = new HashMap<>();
		DEFAULT_TABLE_SIZE = i;
	}
	
	/**
	 * getting hash value and truncating the hash value according to the table size
	 * @param x
	 * @return
	 */
	public int getHashValue(String x)
	{
		int hashVal = x.hashCode( );

        hashVal %= DEFAULT_TABLE_SIZE;
        if( hashVal < 0 )
            hashVal += DEFAULT_TABLE_SIZE;

        return hashVal;
	        
	}
	
	//adding strings to the table with linear probing implemented
	public boolean addToTable(String ls)
	{
		int hs = getHashValue(ls);
		int i = 0;
		while(this.arrayTable.containsKey(hs))
		{
			hs = hs + i;
			i++;
		}
		arrayTable.put(hs,ls);
		return true;
	}
	
	//get back the string from the hash table
	public String getString(String l)
	{
		if(this.arrayTable.containsValue(l))
		{
			return l;
		}
		return "";
	}
	
	//redundant method to print out the whole hash table
	public void printTable()
	{
		System.out.println(this.arrayTable);
	}
	
}
