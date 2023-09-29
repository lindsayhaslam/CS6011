public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public class shopDemo{
        String itemName;
        static int counter = 0;

        public shopDemo(String itemName){
            this.itemName = itemName;
            counter++;
        }

    }

    //NOTES
    //throws FileNotFoundException?

//    //public static void main
//    //System.out.println("Enter a number: ");
//    Scanner sc = new Scanner(System.in);
//    int num = sc.nextInt();
//    try{
//        int x = getElement(num);
//        System.out.println("Result: " + x);
//    } catch (IndexOutOfBoundsException e){
//        System.out.println("Exception happened"+abc.getMessage());
//    }
//    print();
// }