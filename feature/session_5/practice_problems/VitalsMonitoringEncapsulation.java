class PatientVitals {

    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {

        readings = new double[500];
        count = 0;

        for (int i = 0; i < initialReadings.length; i++) {
            recordReading(initialReadings[i]);
        }
    }

    public void recordReading(double reading) {

        if (reading <= 0 || reading > 45) {
            return;
        }

        if (count < readings.length) {
            readings[count] = reading;
            count++;
        }
    }

    public double getAverage() {

        if (count == 0) {
            return 0.0;
        }

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }

        return sum / count;
    }

    public double[] getAllReadings() {

        double[] result = new double[count];

        for (int i = 0; i < count; i++) {
            result[i] = readings[i];
        }

        return result;
    }
}


public class VitalsMonitoringEncapsulation {

    public static void main(String[] args) {

        double[] initial = {
            36.5,
            -2,
            37.1
        };

        PatientVitals v =
            new PatientVitals(initial);

        double[] result = v.getAllReadings();

        for (double value : result) {
            System.out.print(value + " ");
        }

        System.out.println();

        // Test defensive copy
        result[0] = 999;

        double[] result2 = v.getAllReadings();

        for (double value : result2) {
            System.out.print(value + " ");
        }

        System.out.println();

        System.out.println(
            "Average = " + v.getAverage());
    }
}