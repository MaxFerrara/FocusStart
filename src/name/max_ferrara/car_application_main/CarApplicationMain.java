package name.max_ferrara.car_application_main;

import name.max_ferrara.car_application.CarManager;
import name.max_ferrara.car_application.ConsoleWorker;
import name.max_ferrara.car_application.DataBaseWorker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CarApplicationMain {
    public static void main(String[] args) {
        System.out.println("Укажите прямой путь до файла базы данных (не забудьте про экранирование ковычек): ");
        System.out.println("Например так: " + "C:\\Users\\MAKCOH\\IdeaProjects\\FocusStart\\res\\cars.db");

        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        DataBaseWorker dataBaseWorker = new DataBaseWorker(path);
        dataBaseWorker.openDataBase();

        CarManager carManager = new CarManager(dataBaseWorker);

        System.out.println("Укажите текущий год: ");

        try {
            int currentYear = scanner.nextInt();

            ConsoleWorker consoleWorker = new ConsoleWorker(currentYear, carManager);
            consoleWorker.startApplication();

            dataBaseWorker.closeDataBase();
        } catch (InputMismatchException exception) {
            System.out.println("вы ввели не число, запустите программу еще раз");
        }
    }
}
