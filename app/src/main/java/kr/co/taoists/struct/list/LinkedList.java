package kr.co.taoists.struct.list;

import kr.co.taoists.struct.node.SingleNode;

import java.util.ArrayList;


public class LinkedList implements java.io.Serializable{

    private SingleNode head;
    private int length;

    public LinkedList(){
    }

    public void addFirst(Object obj){
        SingleNode node = new SingleNode(obj);
        if(isEmpty()){
            head = node;
        } else {
            node.next = head;
            head      = node;
        }
        length++;
    }

    public void addLast(Object obj){
        SingleNode node = new SingleNode(obj);
        if(isEmpty()){
            head = node;
        } else {
            SingleNode p = head;
            while( p.next != null ){
                p = p.next;
            }
            p.next = node;
        }
        length++;
    }

    public void addNext(Object obj, SingleNode p){
        SingleNode node = new SingleNode(obj);
        if(isEmpty()) {
            head = node;
            length++;
        } else if(p == null){
            addFirst(obj);
        } else {
            node.next = p.next;
            p.next    = node;
            length++;
        }
    }

    public void removeFirst(){
        if( isEmpty() ) return;
        if(head.next == null) {
            head = null;
        } else {
            head = head.next;
        }
        length--;
    }

    public void removeLast(){
        if( isEmpty()) return;
        if(head.next == null) {
            head = null;
        } else {
            SingleNode preNode = head;
            SingleNode curNode = head.next;
            while( curNode.next != null ){
                preNode = curNode;
                curNode = curNode.next;
            }
            preNode.next = null;
        }
        length--;
    }

    public void deleteNext(SingleNode p){
        if(isEmpty()) return;
        if(p == null){
            removeFirst();
        } else {
            SingleNode q = p.next;
            if(q == null){
                return;
            } else {
                p.next = q.next;
                length--;
            }
        }
    }

    public int size(){
        return length;
    }

    public boolean isEmpty(){
        return (length == 0);
    }

    public void clear(){
        head   = null;
        length = 0;
    }

    public void reverse(){
        if( isEmpty() ) return;
        if(head.next == null) return;
        SingleNode p = head;
        SingleNode q = null;
        SingleNode r = null;
        while(p != null){
            r = q;
            q = p;
            p = p.next;
            q.next = r;
        }
        head = q;
    }

    public ArrayList toList(){
        ArrayList list = new ArrayList();
        if(head == null) return list;
        SingleNode p = head;
        while(p != null){
            list.add(p.data);
            p = p.next;
        }
        return list;
    }

    public String toString(){
        if(head == null) return null;
        StringBuffer buffer = new StringBuffer();
        SingleNode p = head;
        while( p != null){
            buffer.append(p.data+"    ");
            p = p.next;
        }
        return buffer.toString();
    }

}
