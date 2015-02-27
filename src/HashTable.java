import java.io.UnsupportedEncodingException;
import java.security.KeyStore;

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

    public ListNode searchHash (String key)
    {
        int index = hashCovert(key);
        ListNode temp=_hashTable[index];
        while (temp != null)
        {
            if (temp.get_value()==key)
            {
                return temp;
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
        for (i=0;i<ascii.length;i++)
        {
            sum=sum+ascii[i];
        }

        return (int)Math.floor(((sum*PHI)%1)*_hashTable.length);
    }
}
