package kr.co.taoists.struct.stack;

public interface Stack extends Cloneable, java.io.Serializable {
    boolean isEmpty();
    void push(Object obj);
    Object pop();
    void remove();
    Object peek();
}
