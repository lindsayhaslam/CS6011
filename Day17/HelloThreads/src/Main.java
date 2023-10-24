public class Main {
        public static void main(String[] args) {
            sayHello(10);
        }

        public static void sayHello(int numThreads) {
            Thread[] threads = new Thread[numThreads];

            for (int i = 0; i < numThreads; i++) {
                final int threadNumber = i;
                threads[i] = new Thread(() -> {
                    for (int j = 1; j <= 100; j++) {
                        if (j % 10 == 0) {
                            System.out.println("Hello number " + j + " from thread number " + threadNumber);
                        }
                    }
                });
            }

            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //QUESTIONS
//What happens? Do all the threads run in order?
//No, the threads do not run in order.

//What happens after switching from "println" to "print"?
//It gets printed all on one line, back to back.

//Does the same thread always print the 1st hello? The last hello?
//Nope!
