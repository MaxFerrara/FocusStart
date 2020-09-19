package name.max_ferrara.car_application;

import java.util.Objects;

public class Car {
    private int year;
    private String provider;
    private String model;
    private String bodyType;
    private String color;

    public Car(int year, String provider, String model, String bodyType, String color) {
        this.year = year;
        this.provider = provider;
        this.model = model;
        this.bodyType = bodyType;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("About car: %n year - %s, %n provider - %s, %n model - %s, %n body - %s, %n color - %s",
                 year, provider, model, bodyType, color);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Car car = (Car) object;
        return year == car.year &&
                Objects.equals(provider, car.provider) &&
                Objects.equals(model, car.model) &&
                Objects.equals(bodyType, car.bodyType) &&
                Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int hash = 1;
        hash = prime * hash * year;
        hash = prime * hash * Objects.hash(provider);
        hash = prime * hash * Objects.hash(model);
        hash = prime * hash * Objects.hash(bodyType);
        hash = prime * hash * Objects.hash(color);

        return hash;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
