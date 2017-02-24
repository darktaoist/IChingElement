package kr.co.taoists.struct.stack;

import java.util.EmptyStackException;

public class ArrayStack implements Stack {

    private int capacity;
    private int increment;
    private Object[] array;
    private int top;

    public  ArrayStack(){
        this(10,0);
    }

    public ArrayStack(int capacity){
        this(capacity,0);
    }

    public ArrayStack(int capacity, int increment){
        this.capacity  = capacity;
        this.increment = increment;
        array          = new Object[capacity];
        top            = -1;
    }

    public boolean isEmpty(){
        return (top == -1);
    }

    public void push(Object obj){
        if(top == capacity -1) incrementStack();
        array[++top] = obj;
    }

    public Object pop(){
        Object obj = peek();
        remove();
        return obj;
    }

    public void remove(){
        if(isEmpty()) throw new EmptyStackException();
        top--;
    }

    public Object peek(){
        if(isEmpty()) throw new EmptyStackException();
        return array[top];
    }

    public void incrementStack(){
        int oldCapacity = capacity;
        capacity = increment>0 ? (increment+oldCapacity) : (2*oldCapacity);
        Object[] newArray = new Object[capacity];
        System.arraycopy(array,0,newArray,0,oldCapacity);
        array = newArray;
    }

    public String toString(){
        if(isEmpty()) return null;
        StringBuffer buffer = new StringBuffer();
        buffer.append("ArrayStack {");
        for( int i = 0 ; i <= top ; i++){
            buffer.append(array[top - i]);
            if(i != top) buffer.append(",");
        }
        buffer.append("}");
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception{
        ArrayStack stack = new ArrayStack();
        stack.push("111");
        stack.push("222");
        stack.push("333");
        stack.push("444");
        stack.push("555");

//        System.out.println(stack.pop());
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());

    }

}
