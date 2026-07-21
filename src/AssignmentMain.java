import com.samarth.assignment.employees.Developer;
import com.samarth.assignment.employees.Manager;
import com.samarth.assignment.utilities.Employeedutilities;

public class AssignmentMain {

    public static void main(String[] args) {

        // Creating Manager Object

        Manager manager = new Manager(
                "Rahul",
                101,
                70000,
                "HR");

        // Creating Developer Object

        Developer developer = new Developer(
                "Priya",
                102,
                60000,
                "Java");

        // Utility Object

        Employeedutilities utility = new Employeedutilities();

        System.out.println("Manager Details");

        utility.displayEmployee(manager);

        System.out.println();

        System.out.println("Developer Details");

        utility.displayEmployee(developer);

        System.out.println();

        utility.increaseSalary(manager,5000);

        System.out.println();

        System.out.println("Manager Details After Salary Increment");

        utility.displayEmployee(manager);

    }

}