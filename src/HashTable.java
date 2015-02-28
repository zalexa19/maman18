import java.io.UnsupportedEncodingException;

/**
 * Hash table implementation
 *
 * Maman 18
 * Date:        28/02/2015
 * Course:      20407
 * Semester:    2015a
 * By:          Stas Seldin     (311950943)
 *              Alex Zablotsky  (314494964)
 */
public class HashTable
{
    private ListNode _hashTable[];
    private static final double PHI = 1.6180339887498948482/2;

    /**
     * Creates a new hash table
     * @param size The size of the hashtable's array
     */
    public HashTable(int size)
    {
        _hashTable = new ListNode[size];
    }

    /**
     * Insert a new key to the hash table
     * Runtime complexity: O(1+alpha)
     * @param key the key to insert to the hash table
     */
    public void insertHash (String key)
    {
        int index = hashCovert(key);

        if (_hashTable[index] == null)
        {
            _hashTable [index]= new ListNode(key);
        }

        else
        {
            ListNode current = _hashTable[index];
            while (current.get_next() != null && current.get_value().compareTo(key) < 0)
            {
                current=current.get_next();
            }

            //insert the key in to the list
            ListNode newNode = new ListNode(key);
            ListNode temp = current.get_next();
            newNode.set_next(temp);
            current.set_next(newNode);

        }

    }

    /**
     * Search and return a key from the hash table
     * Runtime complexity: O(1+alpha)
     * @param key the keay to serach for
     * @return the key's value. If the key does not exists in the table, return null
     */
    public String searchHash (String key)
    {
        int index = hashCovert(key);
        ListNode temp=_hashTable[index];
        while (temp != null && temp.get_value().compareTo(key) < 0)
        {
            temp=temp.get_next();
        }

        if(temp != null && temp.get_value().equals(key))
        {
            return temp.get_value();
        }
        return null; //Not found

    }


    /**
     * The hash table function. It converts a string to an index in the array
     * Runtime complexity: O(k) k - number of letters
     * @param value the key we want to convert to index
     * @return the key's index in the array
     */
    private int hashCovert (String value)
    {
        //Get the ascii values from the string
        byte[] ascii;
        try {
            ascii = value.getBytes("US-ASCII");
        }
         catch (UnsupportedEncodingException e) {
            e.printStackTrace();
             return -1;
        }

        //Calculate a unique number for the string
        long sum=0;
        for (int i=0;i<ascii.length;i++)
        {
            sum += ascii[i] * ascii[i] * (i+1) * (i+1);
        }

        //use division method to calculate index for the string
        return (int)Math.floor(((sum*PHI)%1)*_hashTable.length);
    }

    /**
     * Analyzes and prints data about the hash table's efficiency
     * Runtime complexity: O(n)
     */
    public void analyzeHash()
    {
        int max=0,sum=0,maxIndex=0;
        final int COUNTER_SCOPE = 20;
        int counter[] = new int[COUNTER_SCOPE+1];

        for (int i = 0; i < _hashTable.length; i++)
        {
            int listLength = 0;
            ListNode l = _hashTable[i];
            while(l!=null)
            {
                l=l.get_next();
                listLength++;
            }
            sum+=listLength;
            if(listLength>max) {
                max = listLength;
                maxIndex = i;
            }

            if(listLength<COUNTER_SCOPE)
            {
                counter[listLength]++;
            }
            else
            {
                counter[COUNTER_SCOPE]++;
            }
        }

        System.out.println("Analyzing hash:");
        System.out.println("Hash size: "+_hashTable.length);
        System.out.println("Total records: "+sum);
        System.out.println("alpha =  "+(float)sum/_hashTable.length);

        System.out.println("List length\t\tNumber of cells");
        for (int i = 0; i < counter.length-1; i++)
        {
            System.out.println(i + "\t\t\t\t\t"+counter[i]);
        }

        System.out.println( (COUNTER_SCOPE)+ "+\t\t\t\t\t"+counter[COUNTER_SCOPE]);
        System.out.println("longest linked list: "+max+" at index "+maxIndex);
    }
}
