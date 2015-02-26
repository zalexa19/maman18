/**
 * Created by Alexa on 25/02/2015.
 */
public class RBTree {
    private static final BinNode Neil = new BinNode();
    private BinNode _root;

    public RBTree() {
        _root = Neil;
    }

    public BinNode get_root() {
        return this._root;
    }

    //Left Rotate
    private void leftRotate(BinNode x) {
        BinNode y = x.get_right();
        x.set_right(y.get_left());

        if (y.get_left() != Neil) {
            y.get_left().set_parent(x);
        }
        y.set_parent(x.get_parent());

        if (x.get_parent() == Neil)
        {
            _root = y;
        }
        else {
            if (x == x.get_parent().get_left()) {
                x.get_parent().set_left(y);
            } else {
                x.get_parent().set_right(y);
            }
        }

        y.set_left(x);
        x.set_parent(y);
    }


    //Right Rotate
    private void rightRotate(BinNode x) {
        BinNode y = x.get_left();
        x.set_left(y.get_right());

        if (y.get_right() != Neil) {
            y.get_right().set_parent(x);
        }

        y.set_parent(x.get_parent());
        if (x.get_parent() == Neil) {
            _root = y;
        } else {
            if (x == x.get_parent().get_right()) {
                x.get_parent().set_right(y);
            } else {
                x.get_parent().set_left(y);
            }
        }

        y.set_right(x);
        x.set_parent(y);
    }

    public void rbInsert(BinNode z) {
        //empty tree
        if (_root == Neil) {
            _root = z;
            z.set_left(Neil);
            z.set_right(Neil);
            z.set_parent(Neil);
            return;
        }


        BinNode y = Neil;
        BinNode x = _root;

        while (x != Neil) {
            y = x;
            if (z.get_value().compareTo(x.get_value()) < 0) {
                x = x.get_left();
            } else {
                x = x.get_right();
            }

        }

        z.set_parent(y);
        if (y == Neil) {
            _root = z;
        } else if (z.get_value().compareTo(y.get_value()) < 0) {
            y.set_left(z);
        } else {
            y.set_right(z);
        }

        z.set_left(Neil);
        z.set_right(Neil);
        z.set_color(Color.RED);
        rbInsertFixup(z);
    }

    private void rbInsertFixup(BinNode z) {
        while (z.get_parent().get_color() == Color.RED) {
            if (z.get_parent() == z.get_parent().get_parent().get_left()) {
                BinNode y = z.get_parent().get_parent().get_right();
                if (y.get_color() == Color.RED) {
                    z.get_parent().set_color(Color.BLACK);
                    y.set_color(Color.BLACK);
                    z.get_parent().get_parent().set_color(Color.RED);
                    z = z.get_parent().get_parent();
                } else {
                    if (z == z.get_parent().get_right()) {
                        z = z.get_parent();
                        leftRotate(z);
                    }


                    z.get_parent().set_color(Color.BLACK);
                    z.get_parent().get_parent().set_color(Color.RED);
                    rightRotate(z.get_parent().get_parent());
                }

            } else {
                BinNode y = z.get_parent().get_parent().get_left();

                if (y.get_color() == Color.RED) {
                    z.get_parent().set_color(Color.BLACK);
                    y.set_color(Color.BLACK);
                    z.get_parent().get_parent().set_color(Color.RED);
                } else {
                    if (z == z.get_parent().get_left()) {
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

    public void printInOrder() {
        if (_root == Neil) {
            System.out.print("- empty tree -");
        } else {
            this.printInOrder(_root);
        }
    }

    private void printInOrder(BinNode node) {
        if (node != Neil) {
            printInOrder(node.get_left());
            if(node == _root) {
                System.out.print(" [" + node.get_value() + "] ");
            }
            else {
                System.out.print(" " + node.get_value() + " ");
            }
            printInOrder(node.get_right());
        }

    }

    //Tree Successor
    public BinNode successor(BinNode node) {
        if (node.get_right() != Neil) {
            //minimum in the sub tree
            BinNode index = node.get_right();
            while (index.get_left() != Neil) {
                index = index.get_left();
            }
            return index;
        }

        BinNode parent = node.get_parent(); //Holds the parent
        while ((parent != Neil) && (parent.get_right() == node)) {
            node = parent;
            parent = parent.get_parent();
        }
        return parent; //This is the successor.

    }


    public BinNode rbDelete (BinNode z)
    {
        BinNode y;
        BinNode x;
        if (z.get_left() == Neil || z.get_right() == Neil)
        {
            y=z;
        }
        else
        {
            y=successor(z);
        }
        if (y.get_left() != Neil)
        {
            x=y.get_left();
        }
        else
        {
            x=y.get_right();
        }
        x.set_parent(y.get_parent());

        if (y.get_parent() == Neil)
        {
            _root=x;
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

    private void rbDeleteFixup(BinNode x)
    {
        BinNode w;
        while (x != _root && x.get_color() == Color.BLACK)
        {
            if (x==x.get_parent().get_left())
            {
                w=x.get_parent().get_right();
                if (w.get_color()== Color.RED)
                {
                    w.set_color(Color.BLACK);
                    x.get_parent().set_color(Color.RED);
                    leftRotate(x.get_parent());
                    w=x.get_parent().get_right();
                }
                if (w.get_left().get_color()==Color.BLACK && w.get_right().get_color()==Color.BLACK)
                {
                    w.set_color(Color.RED);
                    x=x.get_parent();
                }
                else
                {
                    if (w.get_right().get_color()==Color.BLACK)
                    {
                        w.get_left().set_color(Color.BLACK);
                        w.set_color(Color.RED);
                        rightRotate(w);
                        w=x.get_parent().get_right();
                    }
                    w.set_color(x.get_parent().get_color());
                    x.get_parent().set_color(Color.BLACK);
                    w.get_right().set_color(Color.BLACK);
                    leftRotate(x.get_parent());
                    x=_root;
                }

            }
            else
            {
                w=x.get_parent().get_left();
                if (w.get_color()== Color.RED)
                {
                    w.set_color(Color.BLACK);
                    x.get_parent().set_color(Color.RED);
                    rightRotate(x.get_parent());
                    w=x.get_parent().get_left();
                }
                if (w.get_right().get_color()==Color.BLACK && w.get_left().get_color()==Color.BLACK)
                {
                    w.set_color(Color.RED);
                    x=x.get_parent();
                }
                else
                {
                    if (w.get_left().get_color()==Color.BLACK)
                    {
                        w.get_right().set_color(Color.BLACK);
                        w.set_color(Color.RED);
                        leftRotate(w);
                        w=x.get_parent().get_left();
                    }
                    w.set_color(x.get_parent().get_color());
                    x.get_parent().set_color(Color.BLACK);
                    w.get_left().set_color(Color.BLACK);
                    rightRotate(x.get_parent());
                    x=_root;
                }
            }
        }
        x.set_color(Color.BLACK);
    }

    public BinNode treeSearch(String key)
    {
        BinNode x = _root;
        while(x != Neil && key != x.get_value())
        {
            if(key.compareTo(x.get_value())<0) {
                x = x.get_left();
            }
            else {
                x = x.get_right();
            }
        }

        return x;
    }
}

