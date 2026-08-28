public class TeamBmiCalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25.0) {
            return "Normal";
        } else if (bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        if (heights.length != weights.length) {
            System.out.println("Height and weight arrays must have the same length.");
            return;
        }

        System.out.printf(
            "%-8s %-14s %-14s %-10s %-15s%n",
            "Person", "Height (m)", "Weight (kg)", "BMI", "Status"
        );
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);

            System.out.printf(
                "%-8d %-14.2f %-14.2f %-10.2f %-15s%n",
                i + 1,
                heights[i],
                weights[i],
                bmi,
                getBmiStatus(bmi)
            );
        }
    }

    public static void main(String[] args) {
        double[] heights = {
            1.75, 1.60, 1.68, 1.82, 1.55,
            1.70, 1.65, 1.78, 1.62, 1.73
        };

        double[] weights = {
            70, 90, 72, 85, 48,
            76, 60, 95, 68, 80
        };

        printWellnessReport(heights, weights);
    }
}
