public class Result extends Node {
    public Node val;
    public Result(Node val)
    {
        this.val=val;
    }
    public Node getNode(){
        return val;
    }
    @Override
    public String toString() {
        return "Result{" +
                 val +
                '}';
    }
}
