/**
 * Ridham Patel,000831171,
 * Date: MArch 28, 2021
 */
public class Customer {
    private int item;            // to store item
    private int time ;           // to store time

    /**
     * constructor of class
     * @param item
     */
    public Customer(int item) {
        this.item = item;
        time = 45 + (item*5);
    }

    /**
     * to set time
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * to get time
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     * to get item
     * @return
     */
    public int getItem() {
        return item;
    }

    /**
     * to print customer details
     * @return
     */
    @Override
    public String toString() {
        return "[" + item +"(" +  time +"s)]" ;
    }
}
