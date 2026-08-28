public class MovieReviewWordLengthProfiler {

    public static void classifyWordLengths(String review) {

        String[] words = review.trim().split("\\s+");

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        for (String word : words) {

            int wordLength = word.length();

            if (wordLength >= 1 && wordLength <= 4) {

                shortWords++;

            } else if (wordLength >= 5 && wordLength <= 8) {

                mediumWords++;

            } else if (wordLength >= 9) {

                longWords++;
            }
        }

        System.out.println(
            "Short: "
            + shortWords
            + " | Medium: "
            + mediumWords
            + " | Long: "
            + longWords
        );
    }

    public static void main(String[] args) {

        String review =
            "This movie was absolutely fantastic and thrilling";

        classifyWordLengths(review);
    }
}