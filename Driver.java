/**
 * DoubleOrderedList testing area.
 *
 * @author (Armando Minor), Acuna
 * @version (version)
 */
import java.util.ListIterator;
public class Driver extends DoubleOrderedList
{
    public static void main(String[] args)
    {
        DoubleOrderedList<Integer> list = new DoubleOrderedList<Integer>();

        //TEST 1

        list.add(23); //add new item
        list.add(24);
        list.add(16);
        list.add(3);
        list.add(7);
        list.add(17);
        list.add(9);
        System.out.println(list + "\n"); //print updated list

        //Test 2

        ListIterator<Integer> iterator = list.iterator();

        while (iterator.hasPrevious())
        {
            int a = iterator.previous();
            iterator.add(a * 10);
            iterator.previous();
        }
        System.out.println(list + "\n");

        //TEST 3

        for (Object item : list) //print each item in the list
        {
            System.out.println(item);
        }

        //TEST 4

        while (iterator.hasNext()) //while there exist an item in the list; execute
        {
            int a = iterator.next();
            iterator.set(a + 1);
        }
        System.out.println(list); //print list
    }
}

