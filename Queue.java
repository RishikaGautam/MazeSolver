/**Rishika Gautam 
 *
 * A first-in-first-out (FIFO) queue of generic items.
 *
 * @param <T> the type of item to store in the queue
 */
public class Queue<T>
{
	//using linked list queue
	//creating the Node class
	private static class Node<T> {
        T data;
        Node<T> next;
        Node(){
            data = null;
            next = null;
            
        }
    }
    
	//initialzing private field
    private Node<T> head; //the head for the first item in queue
    private Node<T> tail; //the tail for adding items to the back of the queue - FIFO
    private int size; //holding the size of the queue
	
    //Queue constructor
    public Queue()
    {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds an item to the queue.
     *
     * @param newItem the item to add
     */
    public void enqueue(T newItem)
    {
    	//checking whether the newItem can be added to the queue - it is of an object type or not 
        if(newItem == null){
            throw new IllegalArgumentException();
        }
        //adding an item to the end of the queue
        Node<T> temp = tail;;
        tail = new Node<T>();
        tail.next = null;
        tail.data = newItem;
        if(isEmpty()){
            head = tail;
        }else{
            temp.next = tail;
        }
        
        this.size++; //incrementing size since an item is being added to the queue
    }

    /**
     * Removes and returns the item on the queue that was least recently added.
     *
     * @return the item on the queue that was least recently added
     */
    public T dequeue()
    {
    	//checking whether the queue is empty or not - if empty there is nothing to dequeue
        if(isEmpty()){
            throw new IllegalStateException();
        }
        //storing the deleted value to return after it is deleted
        T deleteVal = head.data;
        head = head.next;
        this.size--; //decrementing the size of the queue since an item is being removed
        if(isEmpty()) tail = null;
        
        return deleteVal; //returning the deleted item value
        
    }

    /**
     * Returns the item least recently added to the queue.
     *
     * @return the item least recently added to the queue
     */
    public T peek()
    {
    	//
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return head.data; //returning first thing in queue as it is the least recently added to the queue
    }

    /**
     * Returns whether the queue is empty.
     *
     * @return whether the queue is empty
     */
    public boolean isEmpty()
    {
    	//checking whether the queue is empty by checking if size is 0 or not
    	 return (size==0);
    }

    /**
     * Returns the number of items in the queue.
     *
     * @return the number of items in the queue
     */
    public int size()
    {
    	//returning the size of the queue
    	return size;
    }
}
