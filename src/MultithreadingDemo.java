public class MultithreadingDemo {

    public static void main(String[] args) {

        ResultProcessingTask task1 =
                new ResultProcessingTask(101);

        ResultProcessingTask task2 =
                new ResultProcessingTask(102);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        System.out.println(
                "Result processing threads started."
        );
    }
}
