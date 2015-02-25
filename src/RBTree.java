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




}

