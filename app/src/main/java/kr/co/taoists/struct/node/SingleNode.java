package kr.co.taoists.struct.node;

public class SingleNode implements Cloneable, java.io.Serializable {

    public Object data;
    public SingleNode next;

    public SingleNode(){
    }

    public SingleNode(Object obj){
        this.data = obj;
    }


}
