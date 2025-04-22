import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeBST_Testing {

    public static void main(String[] args) {
        String fileName = "./company.csv" ;
        List<Employee> employeeList = new ArrayList<>();
        try {
            File inputFile=new File(fileName);
            Scanner scanner =new Scanner(inputFile);
            String header = scanner.nextLine(); //Code breaks if I delete this, don't know why
            while (scanner.hasNext()){
                String employeeRecord =scanner.nextLine();
                String [] array = employeeRecord.split(",");
                Employee employee =new Employee (Integer.parseInt(array[0]), array[1],
                        array[2],Integer.parseInt(array[3]));
                employeeList.add(employee);
            }
            scanner.close();
            System.out.println(employeeList.get(0));

            EmployeeBST operations = new EmployeeBST();
            Node root = operations.constructBalancedBST(employeeList);
            operations.levelOrderTraversal(root);

            Node retiredEmployee = operations.searchRetiree(root);
            JOptionPane.showMessageDialog(null, "The retired employee is: " + retiredEmployee.getEmployee());

            operations.removeRetiree(root, retiredEmployee.getEmployee());
            int retiredID = retiredEmployee.getEmployee().getId();
            if (operations.search(retiredEmployee, retiredID)) {
                JOptionPane.showMessageDialog(null, "You have removed the retired employee!");
            }
            else {
                JOptionPane.showMessageDialog(null, "Retired employee was not removed.");
            }

            Employee newHire = new Employee(100001, "London", "Hodge", 27);
            operations.insertNewHire(root, newHire);

            operations.inOrderTraversal(root);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
    }
}
