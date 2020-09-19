package name.max_ferrara.car_application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarManager {
    private List<Car> cars;
    private List<Integer> carsId;
    private final Connection connection;

    public CarManager(DataBaseWorker dataBaseWorker) {
        this.connection = dataBaseWorker.getConnection();
    }

    public List<Car> getCars() {
        initCarsList();
        return cars;
    }

    public List<Integer> getCarsId() {
        initCarsId();
        return carsId;
    }

    public Car getCarById(int id) {
        return cars.get(id);
    }

    private void initCarsId() {
        try (Statement statement = this.connection.createStatement()) {
            carsId = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM cars");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                int numberId = Integer.parseInt(id);
                carsId.add(numberId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initCarsList() {
        try (Statement statement = this.connection.createStatement()) {
            cars = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT year, provider, model, bodyType, color FROM cars");

            while (resultSet.next()) {
                cars.add(new Car(resultSet.getInt("year"),
                        resultSet.getString("provider"),
                        resultSet.getString("model"),
                        resultSet.getString("bodyType"),
                        resultSet.getString("color")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCar(Car car) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO cars('year', 'provider', 'model', 'bodyType', 'color') " +
                        "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, car.getYear());
            statement.setObject(2, car.getProvider());
            statement.setObject(3, car.getModel());
            statement.setObject(4, car.getBodyType());
            statement.setObject(5, car.getColor());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(int carId) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM cars WHERE id = ?")) {
            statement.setObject(1, carId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCarYear(int id, int year, Car car) {
        car.setYear(year);

        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE cars SET year = ? WHERE id = ?;")) {
            statement.setInt(1, car.getYear());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setVarCharFields(String fieldName, String input, int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE cars SET " + fieldName + " = ? WHERE id = ?;")) {
            statement.setString(1, input);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCarProvider(int id, String provider, Car car) {
        car.setProvider(provider);
        setVarCharFields("provider", car.getProvider(), id);
    }

    public void setCarModel(int id, String model, Car car) {
        car.setModel(model);
        setVarCharFields("model", car.getModel(), id);
    }

    public void setCarBodyType(int id, String bodyType, Car car) {
        car.setBodyType(bodyType);
        setVarCharFields("bodyType", car.getBodyType(), id);
    }

    public void setCarColor(int id, String color, Car car) {
        car.setColor(color);
        setVarCharFields("color", car.getColor(), id);
    }
}
