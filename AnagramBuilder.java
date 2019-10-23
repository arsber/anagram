package ua.arsber.foxminded.anagram;

public class AnagramBuilder {
    public String makeAnagram(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Argument should not be null");
        }
        char[] arrayFromText = text.toCharArray();
        int currentIndex = 0;
        while (currentIndex < arrayFromText.length) {
            if (!Character.isLetter(arrayFromText[currentIndex])) {
                currentIndex++;
            } else {
                int wordEndIndex = findEndIndex(currentIndex, arrayFromText);
                wordToAnagram(currentIndex, wordEndIndex, arrayFromText);
                currentIndex = wordEndIndex + 1;
            }
        }
        return String.valueOf(arrayFromText);
    }

    int findEndIndex(int index, char[] text) {
        int result = 0;
        for (int i = index + 1; i < text.length; i++) {
            if (text[i] == ' ') {
                return i - 1;
            }
            result = i;
        }
        return result;
    }

    void wordToAnagram(int start, int end, char[] text) {
        int frontIndex = start;
        int backIndex = end;
        int steps = (countLetters(start, backIndex, text)) / 2;
        while (steps > 0) {
            frontIndex = findFrontIndex(frontIndex, text);
            backIndex = findBackIndex(backIndex, text);
            swapLetters(frontIndex, backIndex, text);
            frontIndex++;
            backIndex--;
            steps--;
        }
    }

    int findFrontIndex(int index, char[] word) {
        while (!Character.isLetter(word[index])) {
            index++;
        }
        return index;
    }

    int findBackIndex(int index, char[] word) {
        while (!Character.isLetter(word[index])) {
            index--;
        }
        return index;
    }

    int countLetters(int startIndex, int endIndex, char[] text) {
        int result = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            if (Character.isLetter(text[i])) {
                result++;
            }
        }
        return result;
    }

    void swapLetters(int frontIndex, int backIndex, char[] word) {
        char tmp = word[frontIndex];
        word[frontIndex] = word[backIndex];
        word[backIndex] = tmp;
    }
}
