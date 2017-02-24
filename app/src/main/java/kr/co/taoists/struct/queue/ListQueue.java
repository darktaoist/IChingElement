package kr.co.taoists.struct.queue;

import kr.co.taoists.struct.node.SingleNode;
 
public class ListQueue implements Queue {

    private SingleNode front;
    private SingleNode rear;
    private int length;

    public ListQueue(){

    }

    public boolean isEmpty(){
        return (length == 0);
    }

    public void enqueue(Object obj){
        SingleNode node = new SingleNode(obj);
        if(isEmpty()){
            front = rear = node;
        } else {
            rear.next = node;
            rear      = node;
        }
        length++;
    }

    public Object dequeue(){
        Object obj = null;
        if(!isEmpty()){
            obj = front.data;
            front = front.next;
            if(front == null) rear = null;
            length--;
        }
        return obj;
    }

    public void remove(){
        if(!isEmpty()){
            front = front.next;
            if(front == null) rear = null;
            length--;
        }
    }

    public Object peek(){
        Object obj = null;
        if(!isEmpty()){
            obj = front.data;
        }
        return obj;
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        if(!isEmpty()){
            SingleNode p = front;
            buffer.append("{");
            while(p != null){
                buffer.append(p.data);
                if(p.next != null) buffer.append(",");
                p = p.next;
            }
            buffer.append("}");
        }
        return buffer.toString();
    }

    public static void main(String[] args){

        ListQueue queue = new ListQueue();
        queue.enqueue("111");
        queue.enqueue("222");
        queue.enqueue("333");
        queue.enqueue("444");
        queue.enqueue("555");
        System.out.println( queue.toString() );

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println( queue.toString() );        

    }

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}

