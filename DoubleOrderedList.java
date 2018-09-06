//*Armando Minor 09-04-16
//*DoubleOrderedList class implements a
// *doubly linked implementation using explicit iterator
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleOrderedList<T> implements Iterable<T>
{
    private int num;            //number of elements on list
    private DoubleNode before;  // place before first item
    private DoubleNode after;   // place after last item

    public DoubleOrderedList()
    {
        before = new DoubleNode();
        after = new DoubleNode();
        before.next = after;
        after.prev = before;
    }

    // linked list node helper data type
    private class DoubleNode
    {
        private T item;
        private DoubleNode next; //place after item
        private DoubleNode prev; //place before item
    }
    // add the item to the list
     public void add(T item)
     {
        DoubleNode last = after.prev;
        DoubleNode a = new DoubleNode();
        a.item = item;
        a.next = after;
        a.prev = last;
        after.prev = a;
        last.next = a;
        num++;
    }

    //method to remove item from the list
    public void remove(T item)
    {
        // currentNode is the reference to the first node
        DoubleNode currentNode = after.prev;
        // if items equals the first node in the list, first = currentNode.next which will make the first item
        DoubleNode tmp = currentNode;
        while(tmp.next != null){
            tmp = currentNode;
            if(item.equals(currentNode.item)){
                currentNode = currentNode.next;
                tmp.next = currentNode;
                break;
            }else{
                currentNode = currentNode.next;
            }
        }
        num--;
    }

    public ListIterator<T> iterator()
    {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements ListIterator<T>
    {
        private DoubleNode current      = before.next;  //the node that is returned by next()
        private DoubleNode lastAccessed = null;      //the last node to be returned by prev() or next()
        // reset to null upon intervening remove() or add()
        private int index = 0;

        public boolean hasNext()      { return index < num; }
        public boolean hasPrevious()  { return index > 0; }
        public int previousIndex()    { return index - 1; }
        public int nextIndex()        { return index;     }

        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            lastAccessed = current;
            T item = current.item;
            current = current.next; //set new value for current
            index++; //index shift forward
            return item;
        }

        public T previous()
        {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.prev; //set new value for current
            index--; //index shift back
            lastAccessed = current; //set new value for lastAccessed
            return current.item;
        }

        // replace the item of the element that was last accessed
        public void set(T item) {
            if (lastAccessed == null) throw new IllegalStateException();
            lastAccessed.item = item;
        }

        // remove the last element
        public void remove()
        {
            //throw exception if element position doesn't exist
            if (lastAccessed == null)
                throw new IllegalStateException();
            DoubleNode a = lastAccessed.prev;
            DoubleNode b = lastAccessed.next;
            a.next = b;
            b.prev = a;
            num--;
            if (current == lastAccessed)
                current = b;
            else
                index--;
            lastAccessed = null;
        }

        // add method
        public void add(T item)
        {
            DoubleNode a = current.prev;
            DoubleNode b = new DoubleNode();
            DoubleNode c = current;
            b.item = item;
            a.next = b;
            b.next = c;
            c.prev = b;
            b.prev = a;
            num++;
            index++;
            lastAccessed = null;
        }
        //Convert list to string
    }    public String toString()
{
        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item + " ");
        return s.toString();
    }
}