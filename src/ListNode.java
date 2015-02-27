/**
 * Created by Alexa on 27/02/2015.
 */
public class ListNode
{
    private String _value;
    private ListNode _next;

    public ListNode (String _value)
    {
        _value=_value;
        _next=null;
    }

    public String get_value() {
        return _value;
    }

    public void set_value(String _value) {
        this._value = _value;
    }

    public ListNode get_next() {
        return _next;
    }

    public void set_next(ListNode _next) {
        this._next = _next;
    }
}
