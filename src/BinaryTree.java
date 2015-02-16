/**
 * Created by Alexa on 15/02/2015.
 */
public class BinaryTree {
    private BinNode _root;

    public BinaryTree(BinNode root) {
        this._root = root;
    }

    public BinNode get_root()
    {
        return _root;
    }

    //Insertion
    public void treeInsert(BinNode node) {
        //Root is null
        if (_root == null) {
            _root = node;
        } else {
            BinNode index = _root;
            BinNode parentI = null;
            while (index != null) {
                parentI = index;

                if (node.get_value() > index.get_value()) {
                    index = index.get_right();
                } else {
                    index = index.get_left();
                }
            }

            node.set_parent(parentI);
            if (node.get_value() > parentI.get_value()) {
                parentI.set_right(node);
            } else {
                parentI.set_left(node);
            }
        }
    }

    public BinNode minimum() {
        if (_root == null) {
            return null;
        }

        BinNode index = _root;
        while (index.get_left() != null) {
            index = index.get_left();
        }

        return index;
    }



    public BinNode maximum ()
    {
        if (_root==null)
        {
            return null;
        }

        BinNode index = _root;
        while (index.get_right() != null)
        {
            index=index.get_right();
        }

        return  index;
    }

    private void printInOrder (BinNode node)
    {
        if( node != null)
        {
            printInOrder(node.get_left());
            System.out.print(" " + node.get_value() + " ");
            printInOrder(node.get_right());
        }

    }

    public void printInOrder ()
    {
        if(_root == null)
        {
            System.out.print("- empty tree -");
        }
        else
        {
            this.printInOrder(_root);
        }
    }

    //Tree Successor
    public BinNode successor (BinNode node)
    {
        if (node.get_right() != null)
        {
            BinaryTree newTree = new BinaryTree(node.get_right());
            return newTree.minimum();
        }

        BinNode parent=node.get_parent(); //Holds the parent
        while((parent != null)&&(parent.get_right() == node))
        {
            node=parent;
            parent=parent.get_parent();
        }
        return parent; //This is the successor.
    }


    public void deleteNode (BinNode node)
    {
        BinNode deleteMe;
        if ((node.get_left() == null) || (node.get_right()==null))
        {
            deleteMe = node;
        }
        else
        {
            deleteMe = this.successor(node);
        }

        BinNode child;
        if (deleteMe.get_left() != null)
        {
            child = deleteMe.get_left();
        }
        else
        {
            child = deleteMe.get_right();
        }

        if(child != null)
        {
            child.set_parent(deleteMe.get_parent());
        }

        if(deleteMe.get_parent()==null)
        {
            this._root = child;
        }
        else
        {
            if (deleteMe == deleteMe.get_parent().get_left())
            {
                //delete me is the left child
                deleteMe.get_parent().set_left(child);
            }
            else
            {
                //delete me is the right child
                deleteMe.get_parent().set_right(child);
            }
        }

        if(deleteMe != node)
        {
            node.set_value(deleteMe.get_value());
        }

        //return deleteMe;
    }
}
