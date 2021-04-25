package data;

import java.io.Serializable;
import java.util.Objects;


public class Person implements Comparable<Person>, Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Значение этого поля должно быть уникальным, Поле может быть null
    private Color hairColor; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person() {
    }

    ;

    public Person(String name, String passportID, Color hairColor, Location location) {
        this.name = name;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int compareTo(Person o) {
        if (getPassportID().length() > o.getPassportID().length()) return 1;
        if (getPassportID().length() < o.getPassportID().length()) return -1;
        return getPassportID().compareTo(o.getPassportID());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", passportID='" + passportID + '\'' +
                ", hairColor=" + hairColor +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(passportID, person.passportID) &&
                hairColor == person.hairColor &&
                Objects.equals(location, person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passportID, hairColor, location);
    }
}
