public class NodeAVL<T extends Comparable<T>> {
    private T data;
    private NodeAVL<T> left;
    private NodeAVL<T> right;
    private int height;
    
    public NodeAVL(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public NodeAVL<T> getLeft() {
        return left;
    }
    
    public void setLeft(NodeAVL<T> left) {
        this.left = left;
    }
    
    public NodeAVL<T> getRight() {
        return right;
    }
    
    public void setRight(NodeAVL<T> right) {
        this.right = right;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public String toString() {
        return data.toString();
    }
}