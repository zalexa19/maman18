/**
 * Representation of a node in a linked list
 *
 * Maman 18
 * Date:        28/02/2015
 * Course:      20407
 * Semester:    2015a
 * By:          Stas Seldin     (311950943)
 *              Alex Zablotsky  (314494964)
 */
public class ListNode
{
    private String _value;
    private ListNode _next;

    public ListNode (String value)
    {
        _value=value;
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
