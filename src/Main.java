import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        //Read dictionary
        HashTable dictionary = new HashTable(100000);
        try
        {
            loadDictionary("dictionary.txt", dictionary);
        }
        catch (Exception e)
        {
            System.out.println("Error: Could not open file: "+e.getMessage());
        }

        //Load user text file input
        RBTree input = new RBTree();
        try {
            loadInput("Input.txt",input);
        }
        catch (Exception e) {
            System.out.println("Error: Could not open file: " + e.getMessage());
        }

        //spell check
        spellCheck(dictionary,input);
        System.out.println("The misspelled words are:");
        input.printInOrder();
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

    public static void loadInput (String filePath, RBTree rbTree)  throws Exception
    {
        URL path = ClassLoader.getSystemResource(filePath);
        if(path==null) {
            throw new FileNotFoundException(filePath);
        }

        File file = new File(path.toURI());
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            String words []= line.split(" ");

            for (int i=0;i<words.length;i++)
            {
                BinNode z = new BinNode(words[i]);
                rbTree.rbInsert(z);
            }
        }
        sc.close();
    }

    public static void spellCheck (HashTable dictionary,RBTree input)
    {
        BinNode index =input.minimum();
        BinNode predecessor=null;

        while  (index != RBTree.Neil)
        {
            if (dictionary.searchHash(index.get_value())!=null)
            {

                input.rbDelete(index);
                if(predecessor == null)
                {
                    index=input.minimum();
                }
                else
                {
                    index = input.successor(predecessor);
                }

            }
            else
            {
                index=input.successor(index);
            }
        }
    }
}
