
public class Main
{

    public static void main(String[] args) {

        RBTree alexTree = new RBTree();

        String [] words = { "aa","bb","cc","dd","ee","ff","gg","hh","ii","jj","kk","ll","mm","nn","oo","pp","qq","rr","ss","tt","uu","vv","ww","xx","yy","zz"};
        for(int i=0; i<words.length; i++)
        {
            alexTree.rbInsert(new BinNode(words[i]));
            System.out.println();
            alexTree.printInOrder();
        }

        //BinNode banana = alexTree.treeSearch("banana");
        for(int i=0; i<words.length; i++)
        {
            alexTree.rbDelete(alexTree.get_root());
            System.out.println();
            alexTree.printInOrder();
        }

    }

}
