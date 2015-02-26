
public class Main
{

    public static void main(String[] args) {

        RBTree alexTree = new RBTree();

        String [] words = { "stas", "hat", "cat", "louie", "fruhstuk", "banana", "aadwark", "llz","yoyo","derp", "zzz"};
        for(int i=0; i<words.length; i++)
        {
            alexTree.rbInsert(new BinNode(words[i]));
            System.out.println();
            alexTree.printInOrder();
        }

        for(int i=0; i<words.length; i++)
        {
            alexTree.rbDelete(alexTree.get_root());
            System.out.println();
            alexTree.printInOrder();
        }

    }

}
