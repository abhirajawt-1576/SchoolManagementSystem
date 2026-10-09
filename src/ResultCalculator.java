/**
 * ResultCalculator is responsible for calculating
 * total marks, percentage, grade, and final result
 * of a student.
 */
public class ResultCalculator {

    /**
     * Calculates the total marks obtained by a student.
     *
     * @param marks array containing subject marks
     * @return total marks
     */
    public static int calculateTotal(int[] marks) {

        int total = 0;

        for (int mark : marks) {
            total = total + mark;
        }

        return total;
    }

    /**
     * Calculates the percentage based on the marks.
     *
     * @param marks array containing subject marks
     * @return percentage
     */
    public static double calculatePercentage(int[] marks) {

        int total = calculateTotal(marks);

        int maximumMarks = marks.length * 100;

        return (total * 100.0) / maximumMarks;
    }

    /**
     * Determines the grade according to the percentage.
     *
     * @param percentage student's percentage
     * @return grade
     */
    public static String calculateGrade(double percentage) {

        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else if (percentage >= 40) {
            return "E";
        } else {
            return "F";
        }
    }

    /**
     * Determines whether the student has passed or failed.
     *
     * @param marks array containing subject marks
     * @return PASS or FAIL
     */
    public static String calculateResult(int[] marks) {

        for (int mark : marks) {

            if (mark < 40) {
                return "FAIL";
            }
        }

        return "PASS";
    }

    /**
     * Main method used to test the result calculation.
     */
    public static void main(String[] args) {

        int[] marks = {
                85,
                78,
                92,
                80
        };

        int total = calculateTotal(marks);

        double percentage =
                calculatePercentage(marks);

        String grade =
                calculateGrade(percentage);

        String result =
                calculateResult(marks);

        System.out.println("----- STUDENT RESULT -----");

        System.out.println(
                "Total Marks: " + total
        );

        System.out.println(
                "Percentage: " + percentage + "%"
        );

        System.out.println(
                "Grade: " + grade
        );

        System.out.println(
                "Result: " + result
        );
    }
}