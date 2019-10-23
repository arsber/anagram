package ua.arsber.foxminded.anagram;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AnagramBuilderTest {

    AnagramBuilder builder = new AnagramBuilder();

    @Nested
    class MakeAnagramTest {
        @Test
        @DisplayName("test throw IllegalArgumentException if input string is null")
        void makeAnagramShouldThrowExceptionWhenInputIsNull() {
            assertThrows(IllegalArgumentException.class, () -> builder.makeAnagram(null));
        }

        @Test
        @DisplayName("test return empty string when input is empty string")
        void makeAnagramShouldReturnEmptyStringWhenInputIsEmptyString() {
            String expected = "";
            String actual = builder.makeAnagram("");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test return space when input is space")
        void makeAnagramShouldNotReverseAnySymbolWhenInputIsSpace() {
            String expected = " ";
            String actual = builder.makeAnagram(" ");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test return spaces when input is only spaces")
        void makeAnagramShouldNotReverseAnySymbolWhenInputIsFewSpaces() {
            String expected = "   ";
            String actual = builder.makeAnagram("   ");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test should return same lowercase letter")
        void makeAnagramShouldReturnLowercaseLetterWhenInputIsLowercaseLetter() {
            String expected = "a";
            String actual = builder.makeAnagram("a");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test should return same capital letter")
        void makeAnagramShouldReturnCapitalLetterWhenInputIsCapitalLetter() {
            String expected = "A";
            String actual = builder.makeAnagram("A");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test revers lowercase word")
        void makeAnagramShouldReturnReversedWordWhenInputIsLowercaseWord() {
            String expected = "cba";
            String actual = builder.makeAnagram("abc");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test revers word from capital letters")
        void makeAnagramShouldReturnReversedWordWhenInputIsCapitalWord() {
            String expected = "CBA";
            String actual = builder.makeAnagram("ABC");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test should revers only letters in words")
        void makeAnagramShouldReverseOnlyLettersWhenInputIsWordsContainsLettersAndSymbols() {
            String expected = "c1b#a f1e#d";
            String actual = builder.makeAnagram("a1b#c d1e#f");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test revers space separeted words")
        void makeAnagramShouldReturnReversedWordsWhenInputIsSpaceSeparetedWords() {
            String expected = "abc def";
            String actual = builder.makeAnagram("cba fed");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test revers spaces separeted words")
        void makeAnagramShouldReturnReturnReversedWordsWhenInputIsSpacesSeparetedWords() {
            String expected = "abc   def";
            String actual = builder.makeAnagram("cba   fed");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test revers words when string start with space")
        void makeAnagramShouldReturnReversedWordsWhenInputIsStringStartWithSpace() {
            String expected = " abc def";
            String actual = builder.makeAnagram(" cba fed");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test revers words when string start with spaces")
        void makeAnagramShouldReturnReversedWordsWhenInputIsStringStartWithSpaces() {
            String expected = "  abc def";
            String actual = builder.makeAnagram("  cba fed");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test revers words when string end with space")
        void makeAnagramShouldReturnReversedWordsWhenInputIsStringEndWithSpace() {
            String expected = "  abc  def ";
            String actual = builder.makeAnagram("  cba  fed ");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test revers words when string end with spaces")
        void makeAnagramShouldReturnReversedWordsWhenInputIsStringEndWithSpaces() {
            String expected = "  abc  def  ";
            String actual = builder.makeAnagram("  cba  fed  ");
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("test should not revers any symbols")
        void makeAnagramShouldNotReverseAnySymbolsWhenInputIsOnlyNonLetters() {
            assertAll(() -> assertEquals("123", builder.makeAnagram("123")),
                    () -> assertEquals("1!2 3@4", builder.makeAnagram("1!2 3@4")));
        }
    }

    @Nested
    class FindEndIndexTest {
        @Test
        @DisplayName("test return last character index before space")
        void findEndIndexShouldReturnIndexLastCharacterBeforeSpaceWhenInputIsWordAndSpace() {
            char[] input = ("123abcd ").toCharArray();
            assertEquals(6, builder.findEndIndex(0, input));
        }

        @Test
        @DisplayName("test return last character index in word")
        void findEndIndexShouldReturnIndexLastCharacterInWordWhenInputIsWord() {
            char[] input = ("123abcd").toCharArray();
            assertEquals(6, builder.findEndIndex(0, input));
        }

        @Test
        @DisplayName("test return zero when input is letter")
        void findEndIndexShouldReturnZeroWhenInputIsLetter() {
            assertAll(() -> assertEquals(0, builder.findEndIndex(0, ("a").toCharArray())),
                    () -> assertEquals(0, builder.findEndIndex(0, ("A").toCharArray())));
        }
    }

    @Nested
    class WordToAnagramTest {
        @Test
        @DisplayName("test return letter when input is letter")
        void wordToAnagramShouldReturnLetterWhenInputIsLetter() {
            char[] input = ("a").toCharArray();
            String expected = "a";
            builder.wordToAnagram(0, 0, input);
            assertEquals(expected, String.valueOf(input));
        }

        @Test
        @DisplayName("test return reversed word")
        void wordToAnagramShouldReturnReversedWordWhenInputIsOnlyLetters() {
            char[] input = ("aAaA").toCharArray();
            String expected = "AaAa";
            builder.wordToAnagram(0, 3, input);
            assertEquals(expected, String.valueOf(input));
        }

        @Test
        @DisplayName("test revers only letters")
        void wordToAnagramShouldReverseOnlyLettersWhenInputIsLettersAndSymbols() {
            char[] input = ("a1Aa#A").toCharArray();
            String expected = "A1aA#a";
            builder.wordToAnagram(0, 5, input);
            assertEquals(expected, String.valueOf(input));
        }
    }

    @Nested
    class FindFrontIndexTest {
        @Test
        @DisplayName("test return letter index")
        void findFrontIndexShouldReturnLetterIndexWhenInputContainsLetters() {
            char[] input = ("123abcd").toCharArray();
            assertEquals(3, builder.findFrontIndex(0, input));
        }
    }

    @Nested
    class FindBackIndexTest {
        @Test
        @DisplayName("test return letter index")
        void findBackIndexShouldReturnLetterIndexWhenInputContainsLetters() {
            char[] input = ("abcd123").toCharArray();
            assertEquals(3, builder.findBackIndex(5, input));
        }
    }

    @Nested
    class CountLettersTest {
        @Test
        @DisplayName("test count letters in word")
        void countLettersShouldReturnNumberOfLettersWhenInputIsOnlyLetters() {
            char[] input = ("abcd").toCharArray();
            int expected = 4;
            assertEquals(expected, builder.countLetters(0, 3, input));
        }

        @Test
        @DisplayName("test count only letters in word")
        void countLettersShouldReturnNumberOfLettersWhenInputIsSymbolsAndLetters() {
            char[] input = ("abcd1!3").toCharArray();
            int expected = 4;
            assertEquals(expected, builder.countLetters(0, 6, input));
        }
    }

    @Nested
    class SwapLettersTest {
        @Test
        @DisplayName("test swap characters")
        void swapLettersShouldSwapTwoCharactersWhenInputIsCharacters() {
            char[] input = ("abcD").toCharArray();
            String expected = "Dbca";
            builder.swapLetters(0, 3, input);
            assertEquals(expected, String.valueOf(input));
        }
    }
}
