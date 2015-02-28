import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {

        HashTable hash = new HashTable(100000);
        try
        {
            loadDictionary("dictionary.txt", hash);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        hash.analyzeHash();

    }

    public static void loadDictionary(String filePath, HashTable hashTable) throws Exception
    {
        URL path = ClassLoader.getSystemResource(filePath);
        if(path==null) {
            throw new FileNotFoundException(filePath);
        }

        File file = new File(path.toURI());
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
        {
            String word = sc.nextLine();
            hashTable.insertHash(word.toLowerCase());
        }
        sc.close();
    }

}
