package ua.arsber.foxminded.anagram;

public class Application {
    public static void main(String[] args) {
        String textInput = "ab/c;d e!fh%gh";
        AnagramBuilder builder = new AnagramBuilder();
        String result = builder.makeAnagram(textInput);
        System.out.println(result);
    }
}
