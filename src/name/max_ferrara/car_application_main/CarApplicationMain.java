package name.max_ferrara.car_application_main;

import name.max_ferrara.car_application.CarManager;
import name.max_ferrara.car_application.ConsoleWorker;
import name.max_ferrara.car_application.DataBaseWorker;

import java.util.Scanner;

public class CarApplicationMain {
    public static void main(String[] args) {
        System.out.println("Enter the absolute path to database: ");
        System.out.println("For example: " + "C:\\Users\\MAKCOH\\IdeaProjects\\FocusStart\\res\\cars.db");

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter you path to database: ");
            String path = scanner.nextLine();

            DataBaseWorker dataBaseWorker = new DataBaseWorker(path);
            dataBaseWorker.openDataBase();

            CarManager carManager = new CarManager(dataBaseWorker);

            System.out.println("Enter current year: ");

            int currentYear = scanner.nextInt();

            ConsoleWorker consoleWorker = new ConsoleWorker(currentYear, carManager);
            consoleWorker.startApplication();

            dataBaseWorker.closeDataBase();
        } catch (Exception exception) {
            System.out.println("Application do't start for one of the reasons:");
            System.out.println("1) Invalid path to database");
            System.out.println("2) Entered year is not a number or less or equal to zero");
            System.out.println("3) You have problem with JDBC driver. Contact to the application developers for help:)");
        }
    }
}
