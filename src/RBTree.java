/**
 * Created by Alexa on 25/02/2015.
 */
public class RBTree extends BinaryTree
{

    private static BinNode Neil = new BinNode();


    public RBTree(BinNode root)
    {
        super(root);
    }

//Left Rotate
    private void leftRotate(BinNode x)
    {
        BinNode y=x.get_right();
        x.set_right(y.get_left());

        if(y.get_left() != Neil)
        {
            y.get_left().set_parent(x);
        }
        y.set_parent(x.get_parent());

        if(x.get_parent()==Neil)
        {
            _root=y;
        }

        else if (x==x.get_parent().get_left())
        {
            x.get_parent().set_left(y);
        }
        else
        {
            x.get_parent().set_right(y);
        }

        y.set_left(x);
        x.set_parent(y);
    }


    //Right Rotate
    private void rightRotate (BinNode x)
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
            _root=y;
        }

        else if (x==x.get_parent().get_right())
        {
            x.get_parent().set_right(y);
        }

        else
        {
            x.get_parent().set_left(y);
        }

        y.set_right(x);
        x.set_parent(y);
    }

    private void rbInsert (BinNode z)
    {
        BinNode y = Neil;
        BinNode x = _root;

        while  (x != Neil)
        {
            y=x;
            if (z.get_value().compareTo(x.get_value())<0)
            {
                x=x.get_left();
            }
            else
            {
                x=x.get_right();
            }

        }

        z.set_parent(y);
        if (y==Neil)
        {
            _root=z;
        }
        else if (z.get_value().compareTo(y.get_value())<0)
        {
            y.set_left(z);
        }
        else
        {
            y.set_right(z);
        }

        z.set_left(Neil);
        z.set_right(Neil);
        z.set_color(Color.RED);
        rbInsertFixup(z);
    }

    private void rbInsertFixup(BinNode z)
    {
        while (z.get_parent().get_color() == Color.RED)
        {
            if (z.get_parent()==z.get_parent().get_parent().get_left()) {
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

            }
            else
            {
                BinNode y=z.get_parent().get_parent().get_left();

                if (y.get_color()==Color.RED)
                {
                    z.get_parent().set_color(Color.BLACK);
                    y.set_color(Color.BLACK);
                    z.get_parent().get_parent().set_color(Color.RED);
                }

                else
                {
                    if (z==z.get_parent().get_left())
                    {
                        z=z.get_parent();
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


}

