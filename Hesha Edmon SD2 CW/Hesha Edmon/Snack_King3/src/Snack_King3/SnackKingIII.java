//Importing packages

package Snack_King3;
import Snack_King3.Exceptions.QueueOutOfBoundsException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class SnackKingIII {

//Main Method
    public static void main(String[] args) throws QueueOutOfBoundsException {
        FoodQueue fq = new FoodQueue();
        Scanner input = new Scanner(System.in);
        String code = "";
        do {
            try {
                System.out.println("\t======Snack King Food Center======");

                System.out.println("100 or VFQ: View all Queues");
                System.out.println("101 or VEQ: View all Empty Queues");
                System.out.println("102 or ACQ: Add customer to a Queue");
                System.out.println("103 or RCQ: Remove a customer from a queue");
                System.out.println("104 or PFQ: Remove a served customer");
                System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
                System.out.println("106 or SPD: Store Program Data into file.");
                System.out.println("107 or LPD: Load Program Data from file");
                System.out.println("108 or STK: View remaining pizza in stock");
                System.out.println("109 or AFS: Add pizza to Stock");
                System.out.println("110 or IFQ: Calculate Income");

                System.out.println("999 or EXT: Exit the Program");
                System.out.println("\t------------------------------------");
                System.out.print("Enter the operation: ");
                code = input.nextLine();

                switch (code) {
                    case "100":
                    case "VFQ":
                        fq.viewQueues(); //Calling the view all queues method
                        break;

                    case "101":
                    case "VEQ":
                        fq.viewEmptyQueues(); //Calling all the empty queue method
                        break;

                    case "102":
                    case "ACQ":
                        System.out.print("Enter customer full name: ");

                        String name = input.nextLine();

                        String nameArr[] = name.split(" ");

                        System.out.print("Enter customer id: ");
                        int customerId = input.nextInt();


                        System.out.print("Enter customer order size: ");
                        int orderSize = input.nextInt();

                        fq.currNoOfPizza= fq.PIZZA_STOCK - orderSize;
                        fq.addingCustomer(new Customer(nameArr[0], nameArr[1], customerId, orderSize)); //Calling add customer method
                        input.nextLine();
                        System.out.println("\t====Waiting Queue====");
                        fq.waitingQueue.displayQueue();
                        break;

                    case "103":
                    case "RCQ":
                        System.out.println("Enter queue number: ");
                        int queueNo = input.nextInt();
                        if (queueNo != 1 && queueNo != 2 && queueNo != 3) {
                            throw new QueueOutOfBoundsException();
                        }
                        input.nextLine();
                        System.out.println("Enter location: ");
                        int loc = input.nextInt();

                        fq.removeCustomer(queueNo, loc); //Calling remove customer method
                        input.nextLine();

                    case "104":
                    case "PCQ":
                        System.out.println("Enter queue number: ");
                        queueNo = input.nextInt();
                        if (queueNo != 1 && queueNo != 2 && queueNo != 3) {
                            throw new QueueOutOfBoundsException();
                        }

                        fq.removeCustomer(queueNo); //Calling remove a served customer method
                        input.nextLine();
                        break;


                    case "105":
                    case "VCS":
                        fq.displaySortedQueue(); //Display the sorted names of customer method
                        break;

                    case "106":
                    case "SPD":


                    case "107":
                    case "LPD":

                    case "108":
                    case "STK":
                        int updatedPizzaCount= fq.getCurrNoOfPizza(); //Calling the method to get the remainig pizza count
                        System.out.println("Remaining pizza in stock : "+ updatedPizzaCount);

                        break;


                    case "109":
                    case "AFS":
                        System.out.print("Enter quantity: ");
                        int qty = input.nextInt();
                        fq.addPizza(qty);
                        input.nextLine();

                        int addedPizzaCount= fq.getCurrNoOfPizza(); //Calling the method to add pizza
                        System.out.println("Remaining pizza in stock : "+addedPizzaCount);

                        break;

                    case "110":
                    case "IFQ":
                        System.out.print("Enter queue number: ");
                        queueNo = input.nextInt();
                        fq.displayIncome(queueNo); //Calling the method to get the income of entered queue
                        input.nextLine();
                        break;

                    case "999":
                    case "EXT":
                        System.out.println("Exiting..."); //Exiting the program
                        break;

                    default:
                        System.out.println("Invalid operation code!");
                        break;


                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!");
                input.nextLine();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } while (!code.equals(
                "999") && !code.equals("EXT"));
    }
}






