//Importing packages
package Snack_King3;
import java.io.Serializable;


public class Customer implements Serializable {

    private String firstName;
    private String secondName;
    private int countOfPizza;

    public Customer(String firstName, String secondName, int customerId, int orderSize) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.countOfPizza = orderSize;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public String getFullName() {
        return this.firstName + " " + this.secondName;
    }

    public int getNoOfPizza() {
        return this.countOfPizza;
    }
}
