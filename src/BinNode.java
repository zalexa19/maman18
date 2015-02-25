/**
 * Created by Alexa on 15/02/2015.
 */
public class BinNode
{
    private String _value;
    private BinNode _left;
    private BinNode _right;
    private BinNode _parent;
    private Color  _color;

    //Empty constructor
    public BinNode ()
    {
        this(null,null,null,null);
        this._color = Color.BLACK;
    }
    public BinNode (String v)
    {
        this(v,null,null,null);
    }
    public BinNode (String v, BinNode left, BinNode right,BinNode parent)
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

    public String get_value() {
        return _value;
    }

    public void set_value(String _value) {
        this._value = _value;
    }

    public Color get_color() {
        return _color;
    }

    public void set_color(Color _color) {
        this._color = _color;
    }
}
