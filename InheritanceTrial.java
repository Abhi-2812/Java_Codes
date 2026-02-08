import java.util.*;

class Employee {
    double baseSalary;

    void showSalary() {
        System.out.println("Salary: " + baseSalary);
    }
}

class FullTimeEmployee extends Employee {

    void processSalary(Scanner scanner) {

        System.out.println("Full Time Employee");
        System.out.print("Enter salary: ");
        baseSalary = scanner.nextDouble();

        System.out.print("Salary before hike: ");
        showSalary();

        baseSalary = baseSalary + (baseSalary * 0.50);

        System.out.print("Salary after 50% hike: ");
        showSalary();
        System.out.println();
    }
}

class InternEmployee extends Employee {

    void processSalary(Scanner scanner) {

        System.out.println("Intern Employee");
        System.out.print("Enter salary: ");
        baseSalary = scanner.nextDouble();

        System.out.print("Salary before hike: ");
        showSalary();

        baseSalary = baseSalary + (baseSalary * 0.25);

        System.out.print("Salary after 25% hike: ");
        showSalary();
        System.out.println();
    }
}

public class InheritanceTrial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
        fullTimeEmployee.processSalary(scanner);

        InternEmployee internEmployee = new InternEmployee();
        internEmployee.processSalary(scanner);

        scanner.close();
    }
}
