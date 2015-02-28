/**
 * Red-Black Tree implementation
 *
 * Maman 18
 * Date:        28/02/2015
 * Course:      20407
 * Semester:    2015a
 * By:          Stas Seldin     (311950943)
 *              Alex Zablotsky  (314494964)
 */
public class RBTree
{
    public static final BinNode Neil = new BinNode();
    private BinNode _root;

    public RBTree()
    {
        _root = Neil;
        Neil.set_parent(Neil);
        Neil.set_left(Neil);
        Neil.set_right(Neil);
    }

    public BinNode get_root()
    {
        return this._root;
    }


    /**
     * This method performs a left rotation on the Red-Black tree
     * Runtime Complexity: O(1)
     * @param x The tree node to rotate around
     */
    private void leftRotate(BinNode x)
    {
        BinNode y = x.get_right();
        x.set_right(y.get_left());

        if (y.get_left() != Neil)
        {
            y.get_left().set_parent(x);
        }
        y.set_parent(x.get_parent());

        if (x.get_parent() == Neil)
        {
            _root = y;
        }
        else
        {
            if (x == x.get_parent().get_left())
            {
                x.get_parent().set_left(y);
            }
            else
            {
                x.get_parent().set_right(y);
            }
        }

        y.set_left(x);
        x.set_parent(y);
    }


    /**
     * This method performs a right rotation on the Red-Black tree
     * Runtime Complexity: O(1)
     * @param x The tree node to rotate around
     */
    private void rightRotate(BinNode x)
    {
        BinNode y = x.get_left();
        x.set_left(y.get_right());

        if (y.get_right() != Neil)
        {
            y.get_right().set_parent(x);
        }

        y.set_parent(x.get_parent());
        if (x.get_parent() == Neil)
        {
            _root = y;
        }
        else
        {
            if (x == x.get_parent().get_right())
            {
                x.get_parent().set_right(y);
            }
            else
            {
                x.get_parent().set_left(y);
            }
        }

        y.set_right(x);
        x.set_parent(y);
    }

    /**
     * Insert a new node in the red black tree
     * Runtime Complexity: O(lgn)
     * @param z Tree node to insert
     */
    public void rbInsert(BinNode z)
    {
        //empty tree
        if (_root == Neil)
        {
            _root = z;
            z.set_color(Color.BLACK);
            z.set_left(Neil);
            z.set_right(Neil);
            z.set_parent(Neil);
            return;
        }


        BinNode y = Neil;
        BinNode x = _root;

        while (x != Neil)
        {
            y = x;
            if (z.get_value().compareTo(x.get_value()) < 0)
            {
                x = x.get_left();
            }
            else
            {
                x = x.get_right();
            }

        }

        z.set_parent(y);
        if (y == Neil)
        {
            _root = z;
        }
        else
        {
            if (z.get_value().compareTo(y.get_value()) < 0)
            {
                y.set_left(z);
            }
            else
            {
                y.set_right(z);
            }
        }

        z.set_left(Neil);
        z.set_right(Neil);
        z.set_color(Color.RED);
        rbInsertFixup(z);
    }

    /**
     * Fix any red black tree violations after insert
     * Runtime Complexity: O(lgn)
     * @param z Tree node to perform the fix on
     */
    private void rbInsertFixup(BinNode z)
    {
        while (z.get_parent().get_color() == Color.RED)
        {
            if (z.get_parent() == z.get_parent().get_parent().get_left())
            {
                BinNode y = z.get_parent().get_parent().get_right();
                if (y.get_color() == Color.RED)
                {
                    z.get_parent().set_color(Color.BLACK);
                    y.set_color(Color.BLACK);
                    z.get_parent().get_parent().set_color(Color.RED);
                    z = z.get_parent().get_parent();
                }
                else
                {
                    if (z == z.get_parent().get_right())
                    {
                        z = z.get_parent();
                        leftRotate(z);
                    }


                    z.get_parent().set_color(Color.BLACK);
                    z.get_parent().get_parent().set_color(Color.RED);
                    rightRotate(z.get_parent().get_parent());
                }

            }
            else
            {
                BinNode y = z.get_parent().get_parent().get_left();

                if (y.get_color() == Color.RED)
                {
                    z.get_parent().set_color(Color.BLACK);
                    y.set_color(Color.BLACK);
                    z.get_parent().get_parent().set_color(Color.RED);
                    z = z.get_parent().get_parent();
                }
                else
                {
                    if (z == z.get_parent().get_left())
                    {
                        z = z.get_parent();
                        rightRotate(z);
                    }

                    z.get_parent().set_color(Color.BLACK);
                    z.get_parent().get_parent().set_color(Color.RED);
                    leftRotate(z.get_parent().get_parent());
                }
            }

        }

        _root.set_color(Color.BLACK);
    }

    /**
     * Print the tree in a sorted order (run function)
     * Runtime Complexity: O(n)
     */
    public void printInOrder()
    {
        if (_root == Neil)
        {
            System.out.print("- empty tree -");
        }
        else
        {
            this.printInOrder(_root);
        }
    }

    /**
     * Print the tree in a sorted order (recursive method)
     * Runtime Complexity: O(n)
     */
    private void printInOrder(BinNode node)
    {
        if (node != Neil)
        {
            printInOrder(node.get_left());
            System.out.print(node.get_value() + " ");
            printInOrder(node.get_right());
        }

    }

    /**
     * Find the lowest value of the tree
     * Runtime Complexity: O(lgn)
     * @return The node with the lowest value. (The first item in an ordered list)
     */
    public BinNode minimum()
    {
        BinNode index = _root;
        while (index.get_left() != Neil)
        {
            index = index.get_left();
        }
        return index;
    }

    /**
     * Returns the smallest node who has bigger value than the given node
     * Runtime Complexity: O(lgn)
     * @param node The node we would like to find the successor
     * @return The given node's successor
     */
    public BinNode successor(BinNode node)
    {
        if (node.get_right() != Neil)
        {
            //minimum in the sub tree
            BinNode index = node.get_right();
            while (index.get_left() != Neil)
            {
                index = index.get_left();
            }
            return index;
        }

        BinNode parent = node.get_parent(); //Holds the parent
        while ((parent != Neil) && (parent.get_right() == node))
        {
            node = parent;
            parent = parent.get_parent();
        }
        return parent; //This is the successor.

    }


    /**
     * Removes a node from a red black tree.
     * @param z The node to remove
     * Runtime Complexity: O(lgn)
     * @return The removed node
     *
     */
    public BinNode rbDelete(BinNode z)
    {
        BinNode y;
        BinNode x;
        if (z.get_left() == Neil || z.get_right() == Neil)
        {
            y = z;
        }
        else
        {
            y = successor(z);
        }
        if (y.get_left() != Neil)
        {
            x = y.get_left();
        }
        else
        {
            x = y.get_right();
        }
        x.set_parent(y.get_parent());

        if (y.get_parent() == Neil)
        {
            _root = x;
        }
        else
        {
            if (y == y.get_parent().get_left())
            {
                y.get_parent().set_left(x);
            }
            else
            {
                y.get_parent().set_right(x);
            }

        }

        if (y != z)
        {
            z.set_value(y.get_value());
        }
        if (y.get_color() == Color.BLACK)
        {
            rbDeleteFixup(x);
        }
        return y;
    }

    /**
     * Fix any red black tree violations after delete
     * Runtime Complexity: O(lgn)
     * @param x Tree node to perform the fix on
     */
    private void rbDeleteFixup(BinNode x)
    {
        BinNode w;
        while (x != _root && x.get_color() == Color.BLACK)
        {
            if (x == x.get_parent().get_left())
            {
                w = x.get_parent().get_right();
                if (w.get_color() == Color.RED)
                {
                    w.set_color(Color.BLACK);
                    x.get_parent().set_color(Color.RED);
                    leftRotate(x.get_parent());
                    w = x.get_parent().get_right();
                }
                if (w.get_left().get_color() == Color.BLACK && w.get_right().get_color() == Color.BLACK)
                {
                    w.set_color(Color.RED);
                    x = x.get_parent();
                }
                else
                {
                    if (w.get_right().get_color() == Color.BLACK)
                    {
                        w.get_left().set_color(Color.BLACK);
                        w.set_color(Color.RED);
                        rightRotate(w);
                        w = x.get_parent().get_right();
                    }
                    w.set_color(x.get_parent().get_color());
                    x.get_parent().set_color(Color.BLACK);
                    w.get_right().set_color(Color.BLACK);
                    leftRotate(x.get_parent());
                    x = _root;
                }

            }
            else
            {
                w = x.get_parent().get_left();
                if (w.get_color() == Color.RED)
                {
                    w.set_color(Color.BLACK);
                    x.get_parent().set_color(Color.RED);
                    rightRotate(x.get_parent());
                    w = x.get_parent().get_left();
                }
                if (w.get_right().get_color() == Color.BLACK && w.get_left().get_color() == Color.BLACK)
                {
                    w.set_color(Color.RED);
                    x = x.get_parent();
                }
                else
                {
                    if (w.get_left().get_color() == Color.BLACK)
                    {
                        w.get_right().set_color(Color.BLACK);
                        w.set_color(Color.RED);
                        leftRotate(w);
                        w = x.get_parent().get_left();
                    }
                    w.set_color(x.get_parent().get_color());
                    x.get_parent().set_color(Color.BLACK);
                    w.get_left().set_color(Color.BLACK);
                    rightRotate(x.get_parent());
                    x = _root;
                }
            }
        }
        x.set_color(Color.BLACK);
    }


    /**
     * Find a key in a red black tree
     * Runtime Complexity: O(lgn)
     * @param key the key value to search for
     * @return the node with the given key. If the is not in the tree it will return Tree's Nil value
     */
    public BinNode treeSearch(String key)
    {
        BinNode x = _root;
        while (x != Neil && !key.equals(x.get_value()))
        {
            if (key.compareTo(x.get_value()) < 0)
            {
                x = x.get_left();
            }
            else
            {
                x = x.get_right();
            }
        }

        return x;
    }
}

