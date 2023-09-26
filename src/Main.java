import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Print out Hello World
        System.out.println("Hello, world!");

        //Next, create an array of 10 ints.
        int[] newArray = new int[10];
        //Fill it with random values.
        newArray[0] = 5;
        newArray[1] = 10;
        newArray[2] = 15;
        newArray[3] = 20;
        newArray[4] = 25;
        newArray[5] = 30;
        newArray[6] = 35;
        newArray[7] = 40;
        newArray[8] = 45;
        newArray[9] = 50;

        int arraySum = 0;
        //Print out each one.
        for (int i = 0; i <= 9; i++){
            System.out.println(newArray[i]);
            arraySum += newArray[i];
        }

        System.out.println("The sum of the array is: " + arraySum);

        //Asking the user to enter their name and determine their generation
        Scanner s = new Scanner(System.in);

        System.out.println("Please enter your name and age: ");
        String userName = s.nextLine();
        int userAge = s.nextInt();

        if (userAge >= 18){
            System.out.println("You can vote!");
        }
        else {
            System.out.println("You cannot vote!");
        }

        //Greatest Generation
        if (userAge >= 75){
            System.out.println("You are part of the greatest generation!");
        }
        //Baby Boomer
        if (userAge <= 74 && userAge >= 55){
            System.out.println("You are a Baby Boomer!");
        }
        //GenX
        if (userAge <= 56 && userAge >= 41)
        {
            System.out.println("You are a GenX!");
        }
        //Millennial
        if (userAge <= 50 && userAge >= 25)
        {
            System.out.println("You are a Millennial!");
        }
        //iGen
        if (userAge <= 24)
        {
            System.out.println("You are an iGen!");
        }
    }
}