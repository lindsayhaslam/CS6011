public class Danger {
    public static int answer;

    public static void badSum(int numThreads) {
        answer = 0;
        int maxValue = 100;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int finalI = i;
            threads[i] = new Thread(() -> {
                int start = finalI * maxValue / numThreads;
                int end = Math.min((finalI + 1) * maxValue / numThreads, maxValue);
                int threadSum = 0;
                for (int j = start + 1; j <= end; j++) {
                    threadSum += j;
                }
                synchronized (Danger.class) {
                    answer += threadSum;
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

        public static void main (String[]args){
            badSum(4);
            int correctAnswer = (100 * 101) / 2;
            System.out.println("Computed Answer: " + answer);
            System.out.println("Correct Answer: " + correctAnswer);
        }
    }


//Once you have answered the above questions, try setting maxValue to 100. Run you program multiple times. What do you see? Why?
//Computed Answer and Correct Answer are the same when maxValue is set to a smaller number. But don't match when it
//is set to 40,000. I believe this is because the larger value is more time-consuming and makes it easier for threads to overlap.