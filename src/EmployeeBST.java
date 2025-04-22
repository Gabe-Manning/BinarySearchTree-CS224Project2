import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class EmployeeBST {

    public Node constructBalancedBST(List<Employee> employeeList) {
        int lowIndex = 0;
        int highIndex = employeeList.size()-1;
        return bstHelper(employeeList, lowIndex, highIndex);
    }

    private Node bstHelper(List<Employee> employeeList, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            return null;
        }
        if (lowIndex == highIndex) {
            return new Node(null, null, employeeList.get(lowIndex));
        }
        int mid = lowIndex + (highIndex - lowIndex)/2;
        Node root = new Node(null, null, employeeList.get(mid));
        root.setLeft(bstHelper(employeeList, lowIndex, mid-1));
        root.setRight(bstHelper(employeeList, mid+1, highIndex));
        return root;
    }

    public void levelOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.println("Level " + level + " has " + levelSize + " employees.");
            for (int i = 0; i < levelSize; i++) {
                Node temp = queue.poll();
                System.out.println(temp.getEmployee());
                if (temp.getLeft() != null) {
                    queue.add(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.add(temp.getRight());
                }
            }
            level++;
        }
    }

    public Node searchRetiree(Node root) {
        if (root == null) {
            return null;
        }
        if (root.getEmployee().getAge() > 65) {
            return root;
        }
        Node retiredEmployeeLeft = searchRetiree(root.getLeft());
        if (retiredEmployeeLeft != null) {
            return retiredEmployeeLeft;
        }
        Node retiredEmployeeRight = searchRetiree(root.getRight());
        return retiredEmployeeRight;
    }

    public Node removeRetiree(Node root, Employee employee) {
        if (root == null) {
            return root;
        }
        if (employee.getId() < root.getEmployee().getId()) {
            root.setLeft(removeRetiree(root.getLeft(), employee));
        }
        else if (employee.getId() > root.getEmployee().getId()) {
            root.setRight(removeRetiree(root.getRight(), employee));
        }
        else {
            if (root.getLeft() == null) {
                return root.getRight();
            }
            else if (root.getRight() == null) {
                return root.getLeft();
            }
            else {
                Node minNode = minNode(root.getRight());
                root.setEmployee(minNode.getEmployee());
                root.setRight(removeRetiree(root.getRight(), minNode.getEmployee()));
            }
        }
        return root;
    }

    private Node minNode(Node root) {
        if (root.getLeft() == null) {
            return root;
        }
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    public boolean search(Node root, int id) {
        if (root == null) {
            return false;
        }
        if (root.getEmployee().getId() == id) {
            return true;
        }
        else if (id < root.getEmployee().getId()) {
            return search(root.getLeft(), id);
        }
        else {
            return search(root.getRight(), id);
        }
    }

    public Node insertNewHire(Node root, Employee employee) {
        if (root == null) {
            return new Node(null, null, employee);
        }
        if (employee.getId() < root.getEmployee().getId()) {
            root.setLeft(insertNewHire(root.getLeft(), employee));
        }
        else if (employee.getId() > root.getEmployee().getId()) {
            root.setRight(insertNewHire(root.getRight(), employee));
        }
        return root;
    }

    public void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.getLeft());
            System.out.println(root.getEmployee().toString() + " ");
            inOrderTraversal(root.getRight());
        }
    }
}
