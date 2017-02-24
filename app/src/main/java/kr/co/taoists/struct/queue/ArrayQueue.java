package kr.co.taoists.struct.queue;

public class ArrayQueue implements Queue{

    private int front;
    private int rear;
    private int capacity;
    private int increment;
    private Object[] array;

    public ArrayQueue(){
        this(10);
    }

    public ArrayQueue(int capacity){
        front         = 0;
        rear          = 0;
        increment     = 10;
        array         = new Object[capacity + 1];
        this.capacity = capacity + 1;
    }

    public boolean isEmpty(){
        return (front == rear);
    }

    public int size(){
        return (rear - front);
    }

    public void enqueue(Object obj){
        if(rear  == capacity - 1) incrementQueue();
        array[rear] = obj;
        rear = (rear+1) % capacity;
    }

    public Object dequeue(){
        Object obj = null;
        if(!isEmpty()){
            obj          = array[front];
            front        = (front+1) % capacity;
        }
        return obj;
    }

    /*
    public Object get(int i){
        if(isEmpty())   throw new IndexOutOfBoundsException("Queue empty");
        if( i < 0 || i >= length ){
            String str = "Index " + i + ", queue size " + length;
            throw new IndexOutOfBoundsException(str);
        }
        Object obj = null;
        if(!isEmpty()){
            obj   = array[front];
            front = (front+1) % capacity;
        }
        return obj;
    }
    */

    public void remove(){
        if(isEmpty()) {
            throw new IndexOutOfBoundsException("Queue empty");
        } else if (!isEmpty()) {
            front = (front+1) % capacity;
        }
    }

    public Object peek(){
        Object obj = null;
        if(!isEmpty()){
            obj = array[front];
        }
        return obj;
    }

    private void incrementQueue(){
        int length = size();
        int old    = capacity;
        capacity  += increment;

        Object[] newArray = new Object[capacity];
        for( int i = 0 ; i < length ; i++){
            newArray[i] = array[front];
            front = (front+1) % old;
        }
        array = newArray;
        front = 0;
        rear  = length;
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        if(!isEmpty()) {
            buffer.append("{");
            for( int i = front ; i < rear ; i++){
                buffer.append(array[i]);
                if(i != rear - 1 ) buffer.append(",");
            }
            buffer.append("}");
         }
        return buffer.toString();
    }

    public static void main(String[] args){
        ArrayQueue queue = new ArrayQueue();
        for( int i = 1 ; i <= 100 ; i++){
            queue.enqueue(""+i);
        }
        System.out.println( queue.toString() );

        for( int i = 1 ; i <= 99 ; i++){
            System.out.println(queue.dequeue());
        }
        System.out.println( "==>"+queue.toString() );


    }
}

