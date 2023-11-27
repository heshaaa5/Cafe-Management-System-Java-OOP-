//Importing packages
package Snack_King3;
import Snack_King3.Exceptions.QueueEmptyException;
import Snack_King3.Exceptions.QueueFullException;
import Snack_King3.Exceptions.QueueIndexOutOfBoundsException;
import java.io.Serializable;
import java.util.LinkedList;


public class SKFoodCenter implements Serializable {
    private LinkedList<Customer> queue = new LinkedList<>();

    private int size;
    private int queueId;

    public SKFoodCenter(int size, int queueId) {
        this.size = size;
        this.queueId = queueId;

    }

    public boolean isFull() {
        return queue.size() == this.size;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getSize() {
        return this.size;
    }

    public int getNoOfElements() {
        return this.queue.size();
    }

    public LinkedList getQueue() {
        return this.queue;
    }

    public int getId() {
        return this.queueId;
    }


//Method of adding customer

    public void addingCustomer(Customer cus) throws QueueFullException {

        if (isFull()) {
            throw new QueueFullException(queueId);
        }
        queue.add(cus);
    }

//Method of removing customer in a queue
    public Customer removeCustomer() throws QueueEmptyException {

        if (isEmpty()) {
            throw new QueueEmptyException(queueId);
        }
        Customer cus = queue.remove();
        System.out.println(cus.getFullName() + " removed.");
        return cus;

    }

//Method of removing a served customer


    public Customer removeCustomer(int loc) throws QueueIndexOutOfBoundsException, QueueEmptyException {

        if (isEmpty()) {
            throw new QueueEmptyException(queueId);
        }
        if (loc > queue.size() - 1) {
            throw new QueueIndexOutOfBoundsException();
        }
        Customer cus = queue.remove(loc);
        System.out.println(cus.getFullName() + " removed.");
        return cus;
    }


// Method of viewing all queues

    public void viewQueue() {
        int i = 0;
        for (; i < queue.size(); i++) {
            System.out.println("O"); //Indicates Occupied slots
        }
        for (int j = i; j < this.size; j++) {
            System.out.println("X"); //Indicates Empty slots
        }
    }

//Method of getting income

    public float getIncome() {
        float tot = 0f;
        for (Customer c : queue) {
            tot += c.getNoOfPizza() * FoodQueue.PIZZA_PRICE;
        }
        return tot;
    }

}



