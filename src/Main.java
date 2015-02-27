
public class Main
{

    public static void main(String[] args) {

        RBTree alexTree = new RBTree();

        String [] words = { "aa","bb","cc","dd","ee","ff","gg","hh","ii","jj","kk","ll","mm","nn","oo","pp","qq","rr","ss","tt","uu","vv","ww","xx","yy","zz",};

        HashTable hash = new HashTable(100000);
        hash.insertHash("stas");
        hash.insertHash("sats");
        hash.insertHash("alex");
        hash.insertHash("alexa");

        String result = hash.searchHash("stas");
        String result2 = hash.searchHash("sats");
        String result3 = hash.searchHash("tass");
        String result4 = hash.searchHash("aaa");

        for(int i=0; i<words.length; i++)
        {
            alexTree.rbInsert(new BinNode(words[i]));
            System.out.println();
            //alexTree.printInOrder();
        }

        //BinNode banana = alexTree.treeSearch("banana");
        for(int i=0; i<words.length; i++)
        {
            alexTree.rbDelete(alexTree.get_root());
            System.out.println();
            //alexTree.printInOrder();
        }

    }

}
