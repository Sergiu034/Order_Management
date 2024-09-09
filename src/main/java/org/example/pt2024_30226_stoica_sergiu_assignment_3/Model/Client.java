package org.example.pt2024_30226_stoica_sergiu_assignment_3.Model;

/**
 * Client class represents a client with an ID, name, surname, and age.
 */

public class Client {

    private int ID;
    private String Name;
    private String Surname;
    private int Age;

    /**
     * Constructs a new Client with the specified ID, name, surname, and age.
     *
     * @param ID      the client's ID
     * @param name    the client's name
     * @param surname the client's surname
     * @param age     the client's age
     */

    public Client(int ID, String name, String surname, int age) {
        this.ID = ID;
        Name = name;
        Surname = surname;
        Age = age;
    }

    /**
     * Gets the client's ID.
     *
     * @return the client's ID
     */

    public int getID() {
        return this.ID;
    }

    /**
     * Sets the client's ID.
     *
     * @param ID the client's ID
     */

    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the client's name.
     *
     * @return the client's name
     */

    public String getName() {
        return Name;
    }

    /**
     * Sets the client's name.
     *
     * @param name the client's name
     */

    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Gets the client's surname.
     *
     * @return the client's surname
     */

    public String getSurname() {
        return Surname;
    }

    /**
     * Sets the client's surname.
     *
     * @param surname the client's surname
     */

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    /**
     * Gets the client's age.
     *
     * @return the client's age
     */

    public int getAge() {
        return Age;
    }

    /**
     * Sets the client's age.
     *
     * @param age the client's age
     */

    public void setAge(int age) {
        this.Age = age;
    }
}
