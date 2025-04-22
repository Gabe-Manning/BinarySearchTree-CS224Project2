public class Node {
    //Instance variables
    private Node left;
    private Node right;
    private Employee data;

    //Constructor
    public Node(Node left, Node right, Employee data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Node getLeft() {
        return this.left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return this.right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public Employee getEmployee() {
        return this.data;
    }
    public void setEmployee(Employee data) {
        this.data = data;
    }

}
