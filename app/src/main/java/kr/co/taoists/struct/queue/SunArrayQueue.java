package kr.co.taoists.struct.queue;

import java.io.Serializable;
import java.util.AbstractList;

public class SunArrayQueue extends AbstractList implements Serializable{

    private int capacity;
    private Object queue[];
    private int head;
    private int tail;

    public SunArrayQueue(int i){
        capacity = i + 1;
        queue = new Object[i + 1];
        head = 0;
        tail = 0;
    }

    public void resize(int i){
        int j = size();
        if(i < j)
            throw new IndexOutOfBoundsException("Resizing would lose data");
        if(++i == capacity)
            return;
        Object aobj[] = new Object[i];
        for(int k = 0; k < j; k++)
            aobj[k] = get(k);

        capacity = i;
        queue = aobj;
        head = 0;
        tail = j;
    }

    public boolean add(Object obj){
        queue[tail] = obj;
        int i = (tail + 1) % capacity;
        if(i == head)
        {
            throw new IndexOutOfBoundsException("Queue full");
        } else
        {
            tail = i;
            return true;
        }
    }

    public Object remove(int i){
        if(i != 0)
            throw new IllegalArgumentException("Can only remove head of queue");
        if(head == tail)
        {
            throw new IndexOutOfBoundsException("Queue empty");
        } else
        {
            Object obj = queue[head];
            queue[head] = null;
            head = (head + 1) % capacity;
            return obj;
        }
    }

    public Object get(int i) {
        int j = size();
        if(i < 0 || i >= j) {
            String s = "Index " + i + ", queue size " + j;
            throw new IndexOutOfBoundsException(s);
        } else
        {
            int k = (head + i) % capacity;
            return queue[k];
        }
    }

    public int size(){
        int i = tail - head;
        if(i < 0)
            i += capacity;
        return i;
    }

}
