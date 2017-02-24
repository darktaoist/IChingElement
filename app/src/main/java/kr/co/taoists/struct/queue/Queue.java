package kr.co.taoists.struct.queue;

import java.io.Serializable;

public interface Queue extends Serializable, Cloneable{
    boolean isEmpty();
    int size();
    void enqueue(Object obj);
    Object dequeue();
    void remove();
    Object peek();
}
