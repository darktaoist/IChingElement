package kr.co.taoists.struct.stack;

import kr.co.taoists.struct.node.SingleNode;

public class ListStack implements Stack{

    private SingleNode top;
    private int length;

    public ListStack(){
    }

    public boolean isEmpty(){
        return (top == null);
    }

    public void push(Object obj){
        SingleNode newNode = new SingleNode(obj);
        if(isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        length++;
    }

    public Object pop(){
        Object obj = null;
        if(!isEmpty()) {
            obj = top.data;
            top = top.next;
            length--;
        }
        return obj;
    }

    public void remove(){
        if(!isEmpty()) {
            top = top.next;
            length--;
        }
    }

    public Object peek(){
        if(isEmpty()) return null;
        else          return top.data;
    }

    public int size(){
        return length;
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        SingleNode p = top;
        while(p != null){
            buffer.append(p.data);
            p = p.next;
        }
        return buffer.toString();
    }

    public static void main(String[] args){
        ListStack stack = new ListStack();
        stack.push("111");
        stack.push("222");
        stack.push("333");
        stack.push("444");
        stack.push("555");

        System.out.println(stack.toString());
        System.out.println(stack.pop());
        System.out.println(stack.toString());
    }

}
