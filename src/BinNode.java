/**
 * Created by Alexa on 15/02/2015.
 */
public class BinNode
{
    private int _value;
    private BinNode _left;
    private BinNode _right;
    private BinNode _parent;
    //Empty constructor
    public BinNode () {}

    public BinNode (int v)
    {
        this(v,null,null,null);
    }

    public BinNode (int v, BinNode left, BinNode right,BinNode parent)
    {
        this._value=v;
        this._left=left;
        this._right=right;
        this._parent=parent;

    }


    public BinNode get_left() {
        return _left;
    }

    public void set_left(BinNode _left) {
        this._left = _left;
    }

    public BinNode get_right() {
        return _right;
    }

    public void set_right(BinNode _right) {
        this._right = _right;
    }

    public BinNode get_parent() {
        return _parent;
    }

    public void set_parent(BinNode _parent) {
        this._parent = _parent;
    }

    public int get_value() {
        return _value;
    }

    public void set_value(int _value) {
        this._value = _value;
    }
}
