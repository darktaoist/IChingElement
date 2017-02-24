package kr.co.taoists.struct.node;

public class DoubleNode implements Cloneable, java.io.Serializable {

    public Object data;
    public DoubleNode right;
    public DoubleNode left;

    public DoubleNode(){

    }

    public DoubleNode(Object obj){
        this.data = obj;
    }
}
