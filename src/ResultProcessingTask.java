public class ResultProcessingTask implements Runnable {

    private int studentId;

    public ResultProcessingTask(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public void run() {
        processResult();
    }

    public synchronized void processResult() {

        System.out.println(
                "Result processing started for Student ID: "
                        + studentId
        );

        int[] marks = MarksDAO.getStudentMarks(studentId);

        if (marks.length == 0) {
            System.out.println(
                    "No marks found for Student ID: "
                            + studentId
            );
            return;
        }

        int total = ResultCalculator.calculateTotal(marks);
        double percentage =
                ResultCalculator.calculatePercentage(marks);
        String grade =
                ResultCalculator.calculateGrade(percentage);
        String result =
                ResultCalculator.calculateResult(marks);

        System.out.println("Student ID: " + studentId);
        System.out.println("Total Marks: " + total);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Grade: " + grade);
        System.out.println("Result: " + result);

        System.out.println(
                "Result processing completed for Student ID: "
                        + studentId
        );

        System.out.println("-----------------------------");
    }
}