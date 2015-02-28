import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

/**
 * Spell checker implementation
 *
 * Maman 18
 * Date:        28/02/2015
 * Course:      20407
 * Semester:    2015a
 * By:          Stas Seldin     (311950943)
 *              Alex Zablotsky  (314494964)
 */

public class Main
{

    public static void main(String[] args)
    {
        //Read dictionary
        HashTable dictionary = new HashTable(90000);
        try
        {
            loadDictionary("dictionary.txt", dictionary);
        }
        catch (Exception e)
        {
            System.out.println("Error: Could not open file: "+e.getMessage());
            return;
        }

        //dictionary.analyzeHash();

        //Load user text file input
        RBTree input = new RBTree();
        RBTree story = new RBTree();
        try {
            loadInput("Input.txt",input);
            loadInput("story.txt",story);
        }
        catch (Exception e) {
            System.out.println("Error: Could not open file: " + e.getMessage());
            return;
        }

        //spell check
        spellCheck(dictionary,input);
        spellCheck(dictionary,story);

        //output
        System.out.println("The misspelled words in input.txt are:");
        input.printInOrder();
        System.out.println("\n\nThe misspelled words in story.txt are:");
        story.printInOrder();
    }


    /**
     * Loads a dictionary into a hash table
     * @param filePath the path to the dictionary file
     * @param hashTable the hashtable to store the dictionary in.
     * @throws Exception when there was a problem reading the text file
     */
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


    /**
     * Loads a user input file to a red black tree
     * @param filePath the path to the user input file
     * @param rbTree the red black tree to store the words in
     * @throws Exception when there was a problem reading the text file
     */
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
            String words []= line.split("\\s+");

            for (String word : words)
            {
                BinNode z = new BinNode(word.toLowerCase());
                rbTree.rbInsert(z);
            }
        }
        sc.close();
    }

    /**
     * Performs a spell check on the
     * Runtime Complexity: worst case: O(nlgn) best case: O(n)
     * @param dictionary the dictionary to use
     * @param input the input file to check
     */
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
                predecessor = index;
                index=input.successor(index);
            }
        }
    }
}
