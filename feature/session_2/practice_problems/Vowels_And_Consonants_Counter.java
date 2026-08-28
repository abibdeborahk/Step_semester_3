public class Vowels_And_Consonants_Counter {
    public static void main (String[] args) {
    countVowelsAndConsonants("Java Programming");
    }
    static void countVowelsAndConsonants(String text) {
        String input = text.toLowerCase();
        int vowels = 0;
        int consonants = 0;
        for(int i=0; i<input.length(); i++)
        {
            char ch = input.charAt(i);
            if(ch == 'a' || ch == 'e' || ch=='i' || ch=='o' || ch=='u')
            {
                vowels ++;
            }
            else if (ch == ' ')
            { 
                continue;
            }
            else consonants++;
        }
        System.out.println("Vowels: "+vowels+" | Consonants: "+consonants);
    }
}