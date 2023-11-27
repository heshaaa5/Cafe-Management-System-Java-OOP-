//Importing packages
package Snack_King3;
import Snack_King3.WaitingQueueStructure.CircularQueue;
import Snack_King3.Exceptions.*;
import java.io.Serializable;
import java.util.ArrayList;

public class FoodQueue  implements Serializable {
    private SKFoodCenter numOfQueues[] = new SKFoodCenter[3];
    public CircularQueue<Customer> waitingQueue = new CircularQueue<>();
    private final int sizeofQueues[] = {2, 3, 5};
    public static final int NO_QUEUES = 3;
    public final int PIZZA_STOCK = 100;

    public static final float PIZZA_PRICE = 1350f;
    public int currNoOfPizza;


    public FoodQueue() {

        numOfQueues[0] = new SKFoodCenter(2, 1);
        numOfQueues[1] = new SKFoodCenter(3, 2);
        numOfQueues[2] = new SKFoodCenter(5, 3);
        this.currNoOfPizza = PIZZA_STOCK;

    }
//Method to add customer
    public void addingCustomer(Customer cus) {

        try {

            SKFoodCenter min = numOfQueues[0];
            float maxEmptySizeRatio = (float) (numOfQueues[0].getSize() - numOfQueues[0].getNoOfElements()) / numOfQueues[0].getSize();
            for (int i = 1; i < NO_QUEUES; i++) {

                float emptySizeRatio = (float) (numOfQueues[i].getSize() - numOfQueues[i].getNoOfElements()) / numOfQueues[i].getSize();
                if (emptySizeRatio > maxEmptySizeRatio) {
                    maxEmptySizeRatio = emptySizeRatio;
                    min = numOfQueues[i];
                }
            }

            if (min.isFull()) {
                throw new QueueFullException(min.getId());
            }




            System.out.println(cus.getFullName() + " successfully added.");
            if (currNoOfPizza <= 20) {
                throw new LowPizzaException();
            }
        } catch (QueueFullException |  LowPizzaException ex) {
            System.out.println(ex);
        }
    }

//Method to remove customer

    public Customer removeCustomer(int queueNo) {

        try {

            Customer name = numOfQueues[queueNo - 1].removeCustomer();

            return name;
        } catch (QueueEmptyException ex) {
            System.out.println(ex);
            return null;
        }

    }

//Method to remove a served customer

    public Customer removeCustomer(int queueNo, int loc) {

        try {

            Customer cus = numOfQueues[queueNo - 1].removeCustomer(loc);

            return cus;
        } catch (QueueEmptyException | QueueIndexOutOfBoundsException ex) {
            System.out.println(ex);
            return null;
        }

    }

//Method to view all queues

    public void viewQueues() {

        System.out.println("**************************");
        System.out.println("*\tCashiers\t*");
        System.out.println("**************************");
        System.out.println("");

        for (int k = 0; k < NO_QUEUES; k++) {
            System.out.println("Queue " + (k + 1));
            numOfQueues[k].viewQueue();
        }
        System.out.println("\t====Waiting Queue====");
        waitingQueue.displayQueue();
    }

//Method to display sorted names of customers

    public void displaySortedQueue() {

        Customer temp;
        ArrayList<Customer> tempArr = new ArrayList<>();
        for (int k = 0; k < NO_QUEUES; k++) {
            tempArr.addAll(numOfQueues[k].getQueue());
        }
        System.out.println("\tAll customers in dictionary order");
        System.out.println("");
        for (int i = 0; i < tempArr.size(); i++) {
            for (int j = 1; j < (tempArr.size() - i); j++) {
                if (tempArr.get(j - 1).getFullName().compareTo(tempArr.get(j).getFullName()) > 0) {
                    //swap elements

                    temp = tempArr.get(j - 1);
                    tempArr.set(j - 1, tempArr.get(j));
                    tempArr.set(j, temp);
                }

            }
        }
        for (Customer cus : tempArr) {
            System.out.println(cus.getFullName());
        }
    }
//Method to view empty queues
    public void viewEmptyQueues() {
        System.out.println("Currently Empty Queues");
        for (int i = 0; i < NO_QUEUES; i++) {
            if (numOfQueues[i].isEmpty()) {
                System.out.println("Queue " + (i + 1));
            }
        }
    }

//Method to get current number of pizzas

    public int getCurrNoOfPizza() {
        return this.currNoOfPizza;
    }
//Method to add pizza
    public void addPizza(int qty) {
        currNoOfPizza += qty;
        System.out.println("Stock updated successfully!");
    }

//Method to diplay the income of queues

    public void displayIncome(int queueNo) {
        System.out.printf("Income of %d: %.2f\n", queueNo, numOfQueues[queueNo - 1].getIncome());
    }


}
