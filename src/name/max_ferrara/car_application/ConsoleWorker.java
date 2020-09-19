package name.max_ferrara.car_application;

import java.util.*;

public class ConsoleWorker {
    private final CarManager carManager;
    private final Scanner stringScanner = new Scanner(System.in);
    private final Scanner intScanner = new Scanner(System.in);
    private final int currentYear;

    public ConsoleWorker(int currentYear, CarManager carManager) {
        this.carManager = carManager;
        this.currentYear = currentYear;
    }

    public void startApplication() {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            printShortCarInformation();
            System.out.println("command hint: ");
            System.out.println("add - add new car*");
            System.out.println("delete - delete car by Id*");
            System.out.println("show - change car by Id*");
            System.out.println("for close application input exit");
            System.out.println("enter you command: ");
            command = scanner.nextLine();

            switch (command) {
                case "add":
                    createCar();
                    break;
                case "delete":
                    deleteCar();
                    break;
                case "show":
                    showCarById();
                    break;
                default:
                    System.out.println("unknown command");
                    break;
            }
        } while (!command.equals("exit"));
    }

    private void printShortCarInformation() {
        List<Car> cars = carManager.getCars();
        List<Integer> carsId = carManager.getCarsId();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < cars.size(); ++i) {
            stringBuilder.append(carsId.get(i)).append("|").append(cars.get(i).getProvider()).append("|").append(cars.get(i).getModel()).append("\n");
        }

        System.out.println("-------------------------");
        System.out.println("cars: ");
        System.out.print(stringBuilder.toString());
        System.out.println("-------------------------");
    }

    private void createCar() {
        try {
            System.out.println("enter car year: ");
            int year = intScanner.nextInt();

            if (year < 1885 || year > currentYear) {
                System.out.println("invalid car year");
                createCar();
            } else {
                System.out.println("enter car provider");
                String provider = stringScanner.nextLine();

                System.out.println("enter car model");
                String model = stringScanner.nextLine();

                System.out.println("enter car body-type");
                String bodyType = stringScanner.nextLine();

                System.out.println("enter car color");
                String color = stringScanner.nextLine();

                if (provider.equals("") || model.equals("") || bodyType.equals("") || color.equals("")) {
                    System.out.println("one of the input fields is empty");
                    createCar();
                }

                Car car = new Car(year, provider, model, bodyType, color);
                carManager.addCar(car);
            }
        } catch (InputMismatchException exception) {
            System.out.println("you entered not a number!");
            System.out.println("RESTART APPLICATION");
            intScanner.next();
        }
    }

    private void deleteCar() {
        List<Integer> carsId = carManager.getCarsId();
        System.out.println("enter car id: ");

        try {
            int id = intScanner.nextInt();

            if (!carsId.contains(id)) {
                System.out.println("car with this id does not exist");
                deleteCar();
            } else {
                carManager.deleteCar(id);
            }
        } catch (InputMismatchException exception) {
            System.out.println("you entered not a number!");
            System.out.println("RESTART APPLICATION");
            intScanner.next();
        }
    }

    private void showCarById() {
        List<Integer> carsId = carManager.getCarsId();
        System.out.println("enter car id: ");

        try {
            int id = intScanner.nextInt();

            if (!carsId.contains(id)) {
                System.out.println("car with this id does not exist");
                showCarById();
            } else {
                System.out.println(carManager.getCarById(carsId.indexOf(id)));
                changeCarFields(id);
            }
        } catch (InputMismatchException exception) {
            System.out.println("you entered not a number!");
            System.out.println("RESTART APPLICATION");
            intScanner.next();
        }
    }

    private void changeCarYear(int id) {
        List<Integer> carsId = carManager.getCarsId();
        System.out.println("enter car year: ");

        int year = intScanner.nextInt();

        if (year < 1885 || year > currentYear) {
            System.out.println("invalid car year");
            changeCarYear(id);
        } else {
            carManager.setCarYear(id, year, carManager.getCarById(carsId.indexOf(id)));
        }
    }

    private void changeCarProvider(int id) {
        List<Integer> carsId = carManager.getCarsId();
        System.out.println("enter new car provider: ");

        String provider = stringScanner.nextLine();

        if (provider.equals("")) {
            System.out.println("input filed is empty");
            changeCarProvider(id);
        } else {
            carManager.setCarProvider(id, provider, carManager.getCarById(carsId.indexOf(id)));
        }
    }

    private void changeCarModel(int id) {
        List<Integer> carsId = carManager.getCarsId();
        System.out.println("enter new car model: ");

        String model = stringScanner.nextLine();

        if (model.equals("")) {
            System.out.println("input filed is empty");
            changeCarModel(id);
        } else {
            carManager.setCarModel(id, model, carManager.getCarById(carsId.indexOf(id)));
        }
    }

    private void changeCarBodyType(int id) {
        List<Integer> carsId = carManager.getCarsId();
        System.out.println("enter new car body-type: ");

        String bodyType = stringScanner.nextLine();

        if (bodyType.equals("")) {
            System.out.println("input filed is empty");
            changeCarBodyType(id);
        } else {
            carManager.setCarBodyType(id, bodyType, carManager.getCarById(carsId.indexOf(id)));
        }
    }

    private void changeCarColor(int id) {
        List<Integer> carsId = carManager.getCarsId();
        System.out.println("enter new car color: ");

        String color = stringScanner.nextLine();

        if (color.equals("")) {
            System.out.println("input field is empty");
            changeCarColor(id);
        } else {
            carManager.setCarColor(id, color, carManager.getCarById(carsId.indexOf(id)));
        }
    }

    private void changeCarFields(int id) {
        System.out.println("command hint: 1 - edit year, 2 - edit provider, 3 - edit model, 4 - edit body-type, 5 - edit color,");
        System.out.println("0-exit");
        System.out.println("enter you command: ");

        try {
            int command = intScanner.nextInt();

             if (command == 1) {
                changeCarYear(id);
            } else if (command == 2) {
                changeCarProvider(id);
            } else if (command == 3) {
                changeCarModel(id);
            } else if (command == 4) {
                changeCarBodyType(id);
            } else if (command == 5) {
                changeCarColor(id);
            } else if(command == 0) {
                 return;
             } else {
                System.out.println("unknown command");
                changeCarFields(id);
            }
        } catch (InputMismatchException exception) {
            System.out.println("you entered not a number!");
            System.out.println("RESTART APPLICATION");
            intScanner.next();
        }
    }
}
