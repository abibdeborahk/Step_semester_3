class Canteen {

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode,
                   String canteenName,
                   int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode,
                   String canteenName) {

        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {

        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        int codeResult =
                this.canteenCode.compareToIgnoreCase(
                        other.canteenCode);

        if (codeResult != 0) {
            return codeResult;
        }

        return this.canteenName.length()
                - other.canteenName.length();
    }

    public static Canteen[] rankCanteens(
            Canteen[] canteens) {

        for (int i = 1; i < canteens.length; i++) {

            Canteen current = canteens[i];

            int j = i - 1;

            while (j >= 0 &&
                    canteens[j].compareTo(current) > 0) {

                canteens[j + 1] = canteens[j];
                j--;
            }

            canteens[j + 1] = current;
        }

        return canteens;
    }

    public String getCanteenCode() {
        return canteenCode;
    }
}

public class CanteenTrustScoreRankingEngine {

    public static void main(String[] args) {

        Canteen[] canteens = {

            new Canteen(
                    "HB3-C",
                    "Spice Junction",
                    3),

            new Canteen(
                    "hb1-c",
                    "Grand Mess",
                    5),

            new Canteen(
                    "HB2-C",
                    "Southern Treats")
        };

        Canteen.rankCanteens(canteens);

        for (Canteen c : canteens) {
            System.out.println(c.getCanteenCode());
        }
    }
}