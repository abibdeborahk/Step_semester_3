public class word_reversal_encoder {

    public static void main(String[] args) {
        System.out.println(reverseEachWord("hello club"));
    }

    static String reverseEachWord(String sentence) {

        String[] word = sentence.split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length; i++) {

            StringBuilder reversedWord = new StringBuilder(word[i]);

            reversedWord.reverse();

            sb.append(reversedWord);

            if (i < word.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}