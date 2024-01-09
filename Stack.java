/**Rishika Gautam 
 * 
 * A last-in-first-out (LIFO) stack of generic items.
 *
 * @param <T> the type of item to store in the stack
 */
public class Stack<T>
{
	//using linked list stacks
	//node class
	private static class Node<T> {
        T data;
        Node<T> next;
        
        Node(T value) {
            data = value;
            next = null;
        }
    }
    
	//initializing private field size and the head of the linked list stack
    private Node<T> head;
    private int size;
     
    //Stack constructor
    public Stack()
    {
        head = null; 
    }

    /**
     * Adds an item to the stack.
     *
     * @param newItem the item to add
     */
    public void push(T newItem)
    {
    	//checking whether the newItem can be added to the linked list stack 
        if(newItem == null){
            throw new IllegalArgumentException();
        }
        //adding the newItem to the front of the stack 
        Node<T> temp = new Node<T>(newItem);
        temp.next = head;
        head = temp;
        this.size++; //incrementing size as an item is added to stack
    }

    /**
     * Removes and returns the item on the stack that was most recently added.
     *
     * @return the item on the stack that was most recently added
     */
    public T pop()
    {
    	//checks whether the stack is empty or not - if empty there is nothing to pop
        if(isEmpty()){
            throw new IllegalStateException();
        }
        //stores the value that will be deleted to return later
        T deleteVal = head.data;
        head = head.next;
        this.size--; //decrements size as item is removed
        return deleteVal; //return the item that will be deleted
    }

    /**
     * Returns the item most recently added to the stack.
     *
     * @return the item most recently added to the stack
     */
    public T peek()
    {
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return head.data; //returns first thing in linked list stack which is the most recently added to stack
    }

    /**
     * Returns whether the stack is empty.
     *
     * @return whether the stack is empty
     */
    public boolean isEmpty()
    {
    	//returns whether the stack is empty or not by checking whether size is 0 or not
        return (size() == 0);
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size()
    {
    	//return size of the stack
    	return size;
    }
}
