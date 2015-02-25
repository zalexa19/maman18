
public class Main
{

    public static void main(String[] args) {

        RBTree alexTree = new RBTree();

        String [] words = { "stas", "hat", "cat", "louie", "fruhstuk", "banana", "aadwark", "llz","yoyo","derp"};
        for(int i=0; i<words.length; i++)
        {
            alexTree.rbInsert(new BinNode(words[i]));
        }

        alexTree.printInOrder();
        System.out.println();
    }

}
