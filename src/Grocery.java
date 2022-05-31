/**
 * Ridham Patel,000831171
 * Date:Match 28 2021.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * main class of project
 */
public class Grocery {

    /**
     * main method of class
     * @param args
     */
    public static void main(String[] args) {
        final String filename = "src/CustomerData.txt";
        final int expressLine;                   // to store number of express line
        final int normalLine;                    // to store number of normal line
        final int minItemInCart;                 // to store min item of cart
        final int totalCustomer;                 // number of customer
        int a = 34 ;
        int[] item = new int[a];                // to store data from txt file
        try {
            Scanner fin = new Scanner(new File(filename));
            for (int i=0; i<a; i++)
                item[i] = Integer.parseInt(fin.next());
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.exit(-1);
        }

        expressLine = item[0];
        normalLine =item[1];
        minItemInCart = item[2];
        totalCustomer =item[3];
        int totalline = expressLine+normalLine ;

        //array of Customer to store details
        Customer[] customer =new Customer[totalCustomer];

        // to store wait time
        int[] currentTimeEx =new int[totalline] ;
        int[] currentTimeNo =new int[totalline] ;

        // create current time
        for(int i=0;i<normalLine;i++){
             currentTimeEx[i] = 0;
        }
        for(int i=0;i<expressLine;i++){
            currentTimeNo[i] = 0;
        }


        // to store linkedQueue
        LinkedQueue<Customer>[] queuesEx=new LinkedQueue[expressLine] ;
        for(int i=0;i<expressLine;i++){
            queuesEx[i] =new LinkedQueue<>();
        }
        LinkedQueue<Customer>[] queuesNo=new LinkedQueue[normalLine] ;
        for(int i=0;i<normalLine;i++){
            queuesNo[i] =new LinkedQueue<>();
        }

        // to find min and max waiting time
        int temp = 0;
        //int tempNo = 0 ;
        int tempNo =0;
        int tempEx = 0;
        int temp1 = 0;
       // int tempEx = 0;


        // to add customer in queue
        for(int i=0;i<totalCustomer;i++){
            customer[i] =new Customer(item[i+4]);
            if(customer[i].getItem() <= minItemInCart && (currentTimeNo[temp] >= currentTimeEx[temp1] )){
                queuesEx[temp1].enqueue(customer[i]);
                currentTimeEx[temp1] += customer[i].getTime();
            }else {
                    queuesNo[temp].enqueue(customer[i]);
                    currentTimeNo[temp] += customer[i].getTime();
            }

            for(int j = 0; j<normalLine; j++ ){
                if(currentTimeNo[j]<currentTimeNo[temp]){
                    temp = j;
                }
                if(currentTimeNo[j]>currentTimeNo[tempNo]){
                    tempNo=j;
                }
            }
            for(int j = 0; j<expressLine; j++ ){
                if(currentTimeEx[j]<currentTimeEx[temp1]){
                     temp1 = j;
                }
                if(currentTimeEx[j]>currentTimeEx[tempEx]){
                    tempEx=j;
                }
            }
        }




        // to find total time to compelet customer service
        int maxTime =0 ;
        if(currentTimeNo[tempNo] > currentTimeEx[tempEx]){
            maxTime = currentTimeNo[tempNo];
        }else{
            maxTime = currentTimeEx[tempEx];
        }

        /////PART- A solution

        System.out.println("PART A - Checkout lines and time estimates for each line \n");
        for(int i=0;i<expressLine;i++) {
            System.out.println("CheckOut(Express) #1 (EST Time = " + currentTimeEx[i] + "s )  =" + queuesEx[i]);
        }
        for(int i=0;i<normalLine;i++) {
            System.out.println("CheckOut(Express) # "+ (i+1) +" (EST Time = " + currentTimeNo[i] + "s )  =" + queuesNo[i]);
        }
        System.out.println("Time to clear store of all customers = " + maxTime +"s\n");


        //////PART-B
        System.out.println("PART B - Number of customers in line after each minute (30s)");
        System.out.println("t(s) Line 1  Line 2  Line 3  Line4    Line5   Line6   Line7   Line8   Line9   Line10");


        //// Deque customer with time
        int timeSpan=30;
        for(int i=0;i<=maxTime;i++){
            for(int j=0;j<expressLine;j++) {
                if (!queuesEx[j].isEmpty()) {
                    queuesEx[j].peek().setTime(queuesEx[j].peek().getTime() - 1);
                    if (queuesEx[j].peek().getTime() <= 0) {
                        queuesEx[j].dequeue();
                    }
                }
            }
            for(int j=0;j<normalLine;j++) {
                if (!queuesNo[j].isEmpty()) {
                    queuesNo[j].peek().setTime(queuesNo[j].peek().getTime() - 1);
                    if (queuesNo[j].peek().getTime() <= 0) {
                        queuesNo[j].dequeue();
                    }
                }
            }
            if(i%timeSpan == 0 && i != 0) {
                System.out.println(i + "\t\t" + queuesEx[0].size() + "\t\t" + queuesEx[1].size() +
                                       "\t\t" + queuesNo[0].size() + "\t\t" + queuesNo[1].size() +
                                       "\t\t" + queuesNo[2].size() + "\t\t" + queuesNo[3].size() +
                                       "\t\t" + queuesNo[4].size() + "\t\t" + queuesNo[5].size() +
                                       "\t\t" + queuesNo[6].size() + "\t\t" + queuesNo[7].size() );
            }
        }

    }
}
