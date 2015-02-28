import java.io.UnsupportedEncodingException;

/**
 * Created by Alexa on 27/02/2015.
 */
public class HashTable
{
    private ListNode _hashTable[];
    private static final double PHI = 1.6180339887498948482/2;

    public HashTable(int size)
    {
        _hashTable = new ListNode[size];
    }

    public void insertHash (String key)
    {

        if(searchHash(key)!=null)
        {
            return;
        }

        int index = hashCovert(key);

        if (_hashTable[index] == null)
        {
            _hashTable [index]= new ListNode(key);
        }

        else
        {
            ListNode temp = _hashTable[index];
            while (temp.get_next() != null)
            {
                temp=temp.get_next();
            }
            temp.set_next(new ListNode(key));
        }

    }

    public String searchHash (String key)
    {
        int index = hashCovert(key);
        ListNode temp=_hashTable[index];
        while (temp != null)
        {
            if (temp.get_value().equals(key))
            {
                return temp.get_value();
            }
            else
            {
                temp=temp.get_next();
            }
        }

        return null; //Not found

    }

    private int hashCovert (String value) {

        byte[] ascii;
        try {
            ascii = value.getBytes("US-ASCII");
        }
         catch (UnsupportedEncodingException e) {
            e.printStackTrace();
             return -1;
        }

        long sum=0;
        for (int i=0;i<ascii.length;i++)
        {
            sum += ascii[i] * ascii[i] * (i+1) * (i+1);
        }

        //use division method to calculate index
        return (int)Math.floor(((sum*PHI)%1)*_hashTable.length);
    }

    public void analyzeHash()
    {
        int max=0,sum=0;
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
        System.out.println("longest linked list: "+max);
    }
}
