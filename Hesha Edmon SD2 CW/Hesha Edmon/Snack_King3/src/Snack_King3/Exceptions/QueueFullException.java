
package Snack_King3.Exceptions;


public class QueueFullException extends Exception {

    private int queueNo;

    public QueueFullException() {

    }

    public QueueFullException(int queueNo) {
        this.queueNo = queueNo;
    }

    public String toString() {
        return "The queue " + queueNo + " is currently fully occupied.";
    }

}
