// Importing packages
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SnackKing {
    public static final int countOfCashiers = 3;
    public static final int stockOfPizza = 100;
    public static int updatedPizzaStock;
    static String[][] cashierQueues = new String[countOfCashiers][];
    static int[] sizeOfCashiers = {2, 3, 5}; // Number of spots for each queue
    static Scanner userInput = new Scanner(System.in);
    public static int pizzaCount;

    public SnackKing() {
        for (int i = 0; i < countOfCashiers; i++) {
            cashierQueues[i] = new String[sizeOfCashiers[i]]; // Initilazing  three queues with specific sizes
            for (int j = 0; j < cashierQueues[i].length; j++) {
                cashierQueues[i][j] = null; // Make all slots empty
            }
        }
    }


    // Method to view all Queueus
    public static void viewQueues() {

        System.out.println("**************************");
        System.out.println("*\tCashiers\t*");
        System.out.println("**************************");
        System.out.println("");

        for (int k = 0; k < countOfCashiers; k++) {
            System.out.println("Queue " + (k + 1));
            for (int j = 0; j < sizeOfCashiers[k]; j++) {
                String customers = cashierQueues[k][j];
                if (customers != null) {
                    System.out.println("O"); // Printing "O" for occupied slots
                } else {
                    System.out.println("X"); // Printing "X" for empty slots
                }
            }
            System.out.println(); // Starting in  new line for other queue
        }
    }

 // Method to view all empty Queues
    public static void viewEmptyQueues() {
        System.out.println("*********Currently Empty Queues*********");
        for (int i = 0; i < countOfCashiers; i++) {
           boolean emptySlot=false;
           System.out.print("Queue "+(i+1) + ": ");
           for (int j=0; j< sizeOfCashiers[i];j++) {
               String slot = cashierQueues[i][j];
               if (slot == null) {
                   System.out.print((j + 1) + " "); //Occupied slots
                   emptySlot = true;
               }
           }
           if (emptySlot) {
               System.out.println("\nEmpty slots in Queue" + (i + 1));
           }else {
               System.out.println("No empty slots in Queue" + (i + 1));

           }
        }
    }

// Method to add Customers
    public static void addingCustomer() {

        Scanner userInput = new Scanner(System.in);
        for (int i = 0; i < countOfCashiers; i++) {
            System.out.print("Enter name:  ");
            String nameOfCustomer = userInput.nextLine();


            System.out.print("Enter the queue: ");
            int cashierNo = userInput.nextInt();

            System.out.print("Enter the slot: ");
            int slotNo = userInput.nextInt();

            System.out.print("Enter the amount of pizza: ");

            pizzaCount = userInput.nextInt();
            if (pizzaCount > 100) {  // Validating the pizza stock
                System.out.println("Current stock available: 100");
            } else {
                updatedPizzaStock = stockOfPizza - pizzaCount; //Updating the stock
            }



            if (updatedPizzaStock==20){ //Warning message when stock reach 20
                System.out.println("Pizza stock reach to the count of 20!!!!");
            }

            int newCashierNo= cashierNo-1;
            int newSlotNo= slotNo-1;

            if (newCashierNo >= 0 && newCashierNo < countOfCashiers &&
                    newSlotNo >= 0 && newSlotNo < sizeOfCashiers[newCashierNo]) {
                if(cashierQueues[newCashierNo][newSlotNo]==null) {
                     cashierQueues[newCashierNo][newSlotNo]= (nameOfCustomer);
                    System.out.println(nameOfCustomer + "  has been successfully added to the " + "slot " + slotNo + " of Queue " + cashierNo +".");

                    break;
                }else {
                    System.out.println("Slot is already occupied");
                }


            } else {
                System.out.println("Invalid queue or slot number entered!!!");

            }
        }


    }
    public void restorePizza(){
       updatedPizzaStock=updatedPizzaStock+ pizzaCount;

    }


// Method to remove Customer from queue
    public void clearSlot() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter a queue number: ");
        int clearQueueNo = userInput.nextInt();

        System.out.print("Enter a slot number: ");
        int clearSlotNo = userInput.nextInt();



        int newClearQueueNo= clearQueueNo-1;
        int newClearSlotNo= clearSlotNo-1;


        if (newClearQueueNo >= 0 && newClearQueueNo < countOfCashiers &&
                newClearSlotNo >= 0 && newClearSlotNo < sizeOfCashiers[newClearQueueNo]) {

          if (cashierQueues[newClearQueueNo][newClearSlotNo] != null)  {

              cashierQueues[newClearQueueNo][newClearSlotNo]=null;
              restorePizza();
          }

            System.out.println("The customer has been removed from the queue!!");
        } else {
            System.out.println("Invalid slot or queue number entered!!!!");
        }

    }

//Method to remove served Customer

    public void clearServedSlot(){
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter a queue number: ");
        int clearQueueNo = userInput.nextInt();

        System.out.print("Enter a slot number: ");
        int clearSlotNo = userInput.nextInt();



        int newClearQueueNo= clearQueueNo-1;
        int newClearSlotNo= clearSlotNo-1;


        if (newClearQueueNo >= 0 && newClearQueueNo < countOfCashiers &&
                newClearSlotNo >= 0 && newClearSlotNo < sizeOfCashiers[newClearQueueNo]) {

            if (cashierQueues[newClearQueueNo][newClearSlotNo] != null)  {

                cashierQueues[newClearQueueNo][newClearSlotNo]=null;

            }

            System.out.println("The customer has been removed from the queue!!");
        } else {
            System.out.println("Invalid slot or queue number entered!!!!");
        }
    }

// Method to view sorted names of Customers
    public void viewSortedNames() {
        int totalCustomers = 0;


        for (int i = 0; i < countOfCashiers; i++) { //Counting total customers
            for (int j = 0; j <sizeOfCashiers[i]; j++) {
                if (cashierQueues[i][j] != null) {
                    totalCustomers++;
                }
            }
        }

        String[] allCustomerNames = new String[totalCustomers];
        int index = 0;


        for (int i = 0; i < countOfCashiers; i++) {  //Collecting customer names in all queues
            for (int j = 0; j < sizeOfCashiers[i]; j++) {
                String customer = cashierQueues[i][j];
                if (customer != null) {
                    allCustomerNames[index++] = customer;
                }
            }
        }


        Arrays.sort(allCustomerNames); //Sort and displaying the names

        System.out.println("Customer names in alphabetical order:");
        for (String name : allCustomerNames) {
            System.out.println(name);
        }
    }

//Method to save program data into Files

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("===========================Snack King Food Center===========================\n");
            writer.write("\n");
            writer.write("Remaining Pizza Stock is : "+updatedPizzaStock+"\n");
            writer.write("\n");
            writer.write("Customer Details\n");


            for (int i = 0; i < countOfCashiers; i++) {
                ;
                for (int j = 0; j < sizeOfCashiers[i]; j++) {
                    String customer = cashierQueues[i][j];
                    if (customer != null) {

                        writer.write("Queue " + (i + 1) + ", Slot " + (j + 1) + ": " + customer + "\n");


                    }
                }

            }
            System.out.println("Queue data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }
// Method to load the data saved in the file

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String[] queueSlot = parts[0].split(", Slot ");
                    if (queueSlot.length == 2) {
                        int queueNumber = Integer.parseInt(queueSlot[0].replace("Queue ", "")) - 1;
                        int slotNumber = Integer.parseInt(queueSlot[1]);
                        String customer = parts[1];
                        cashierQueues[queueNumber][slotNumber - 1] = customer;
                    }
                }
            }

            System.out.println("Queue data loaded from " + filename);
            viewQueues();// To display the loaded data
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }





// Method to view remaining Pizza
    public void remainingAmountOfPizza() {

        if (updatedPizzaStock == 0) {
            System.out.println("Remaining pizza Count is " + stockOfPizza);
        } else {
            System.out.println("Remaining Pizza Count is " + updatedPizzaStock);
        }
    }
// Method to add Pizza

    public void addingDesiredAmountOfPizza() {
        System.out.print("Enter the amount to add: ");
        int newAmountOfPizza = userInput.nextInt(); //Getting the user input


        int addPizzaStock;
        if (updatedPizzaStock == 0) {
            addPizzaStock = stockOfPizza + newAmountOfPizza; //Validating the output

        } else {
            addPizzaStock = updatedPizzaStock + newAmountOfPizza; //Validating the output
        }
        System.out.println("Currently available pizza count :" + addPizzaStock);


    }

//Method to exit the program

    public void exitSnackKing() {
        System.out.print("======End of the Program======");
        System.exit(0);

    }


//Main method of the program
    public static void main(String[] args) {
        SnackKing snackKing = new SnackKing();
        String code = ""; //Initilaize a value for a code

        while (!code.equals("0")) {
            System.out.println("\t======Snack King Food Center======");
            System.out.println("100 or VFQ: View all Queues");
            System.out.println("101 or VEQ: View all Empty Queues");
            System.out.println("102 or ACQ: Add customer to a Queue");
            System.out.println("103 or RCQ: Remove a customer from a queue");
            System.out.println("104 or PFQ: Remove a served customer");
            System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
            System.out.println("106 or SPD: Store Program Data into file.");
            System.out.println("107 or LPD: Load Program Data from file");
            System.out.println("108 or STK: View remaining pizza stock");
            System.out.println("109 or AFS: Add Pizza to Stock");
            System.out.println("999 or EXT: Exit the Program");
            System.out.println("\t------------------------------------");

            System.out.print("Enter the code: ");

            code = userInput.nextLine();

            switch (code) {
                case "100", "VFQ" -> {
                    snackKing.viewQueues(); // Calling the method to view all Queues
                }
                case "101", "VEQ" -> {
                    snackKing.viewEmptyQueues(); // Calling the method to view empty Queues
                }
                case "102", "ACQ" -> {
                    snackKing.addingCustomer(); // Calling the method to add Customers
                }
                case "103", "RCQ" -> {
                    snackKing.clearSlot(); // Calling the method to remove a customer from a queue
                }
                case "104", "PFQ" -> {
                    snackKing.clearServedSlot(); // Calling the method to remove a serves customer

                }
                case "105", "VCS" -> {
                    snackKing.viewSortedNames(); // Calling the method to view sorted names of Customers

                }

                case "106", "SPD" -> {
                    snackKing.saveToFile("Snack_King.txt"); // Calling the method to save data to a text file

                }
                case "107", "LPD" -> {
                    snackKing.loadFromFile("Snack_King.txt"); // Calling the method to load data form the text file

                }
                case "108", "STK" -> {
                    snackKing.remainingAmountOfPizza(); // Calling the method to view remaining pizza
                }
                case "109", "AFS" -> {
                    snackKing.addingDesiredAmountOfPizza(); // Calling the method to add pizza

                }
                case "999", "EXT" -> {
                    snackKing.exitSnackKing(); // Calling the method to exit the program
                }
                default -> {
                    System.out.println("Invalid option ");
                }


            }
        }


    }
}



