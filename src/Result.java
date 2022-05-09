public class Result {
    public ASTNODE tree;
    public Result(){}

    public Result(ASTNODE expr) {
       tree=expr;
    }

    public ASTNODE getNode(ASTNODE node){
        return node;
    }
    public ASTNODE getTree(ASTNODE node){
        tree=node;
        return tree;
    }

    @Override
    public String toString() {
        return "{" + tree + '}';
    }
}
