import java.util.Random;

public class Main {

    public static void main(String[] args) {

        BinNode root = new BinNode(500);

        BinaryTree alexTree = new BinaryTree(root);

        for (int i = 0; i < 20; i++)
        {
            int r = randInt(0,1000);
            BinNode newNode = new BinNode(r);
            alexTree.treeInsert(newNode);
            alexTree.printInOrder();
            System.out.println();
        }

        for (int i = 0; i < 21; i++) {
            alexTree.deleteNode(alexTree.get_root());
            alexTree.printInOrder();
            System.out.println();
        }

    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive

        return rand.nextInt((max - min) + 1) + min;
    }
}
