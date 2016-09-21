
package utils;
import java.util.Locale;

public class StringUtils {
    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;
    
    /**
     * <p>
     * Checks if the CharSequence contains only lowercase characters.</p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty CharSequence
     * (length()=0) will return {@code false}.</p>
     *
     * <pre>
     * StringUtils.isAllLowerCase(null)   = false
     * StringUtils.isAllLowerCase("")     = false
     * StringUtils.isAllLowerCase("  ")   = false
     * StringUtils.isAllLowerCase("abc")  = true
     * StringUtils.isAllLowerCase("abC")  = false
     * StringUtils.isAllLowerCase("ab c") = false
     * StringUtils.isAllLowerCase("ab1c") = false
     * StringUtils.isAllLowerCase("ab/c") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains lowercase characters, and is
     * non-null
     * @since 2.5
     * @since 3.0 Changed signature from isAllLowerCase(String) to
     * isAllLowerCase(CharSequence)
     */
    public static boolean isAllLowerCase(final CharSequence cs) {
        if (cs == null || isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLowerCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the CharSequence contains only uppercase characters.</p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty String (length()=0) will
     * return {@code false}.</p>
     *
     * <pre>
     * StringUtils.isAllUpperCase(null)   = false
     * StringUtils.isAllUpperCase("")     = false
     * StringUtils.isAllUpperCase("  ")   = false
     * StringUtils.isAllUpperCase("ABC")  = true
     * StringUtils.isAllUpperCase("aBC")  = false
     * StringUtils.isAllUpperCase("A C")  = false
     * StringUtils.isAllUpperCase("A1C")  = false
     * StringUtils.isAllUpperCase("A/C")  = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains uppercase characters, and is
     * non-null
     * @since 2.5
     * @since 3.0 Changed signature from isAllUpperCase(String) to
     * isAllUpperCase(CharSequence)
     */
    public static boolean isAllUpperCase(final CharSequence cs) {
        if (cs == null || isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isUpperCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    // Defaults
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Returns either the passed in String, or if the String is {@code null}, an
     * empty String ("").</p>
     *
     * <pre>
     * StringUtils.defaultString(null)  = ""
     * StringUtils.defaultString("")    = ""
     * StringUtils.defaultString("bat") = "bat"
     * </pre>
     *
     * @see ObjectUtils#toString(Object)
     * @see String#valueOf(Object)
     * @param str the String to check, may be null
     * @return the passed in String, or the empty String if it was {@code null}
     */
    public static String defaultString(final String str) {
        return str == null ? EMPTY : str;
    }

    /**
     * <p>
     * Returns either the passed in String, or if the String is {@code null},
     * the value of {@code defaultStr}.</p>
     *
     * <pre>
     * StringUtils.defaultString(null, "NULL")  = "NULL"
     * StringUtils.defaultString("", "NULL")    = ""
     * StringUtils.defaultString("bat", "NULL") = "bat"
     * </pre>
     *
     * @see ObjectUtils#toString(Object,String)
     * @see String#valueOf(Object)
     * @param str the String to check, may be null
     * @param defaultStr the default String to return if the input is
     * {@code null}, may be null
     * @return the passed in String, or the default if it was {@code null}
     */
    public static String defaultString(final String str, final String defaultStr) {
        return str == null ? defaultStr : str;
    }

    /**
     * <p>
     * Returns either the passed in CharSequence, or if the CharSequence is
     * whitespace, empty ("") or {@code null}, the value of
     * {@code defaultStr}.</p>
     *
     * <pre>
     * StringUtils.defaultIfBlank(null, "NULL")  = "NULL"
     * StringUtils.defaultIfBlank("", "NULL")    = "NULL"
     * StringUtils.defaultIfBlank(" ", "NULL")   = "NULL"
     * StringUtils.defaultIfBlank("bat", "NULL") = "bat"
     * StringUtils.defaultIfBlank("", null)      = null
     * </pre>
     *
     * @param <T> the specific kind of CharSequence
     * @param str the CharSequence to check, may be null
     * @param defaultStr the default CharSequence to return if the input is
     * whitespace, empty ("") or {@code null}, may be null
     * @return the passed in CharSequence, or the default
     * @see StringUtils#defaultString(String, String)
     */
    public static <T extends CharSequence> T defaultIfBlank(final T str, final T defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    /**
     * <p>
     * Returns either the passed in CharSequence, or if the CharSequence is
     * empty or {@code null}, the value of {@code defaultStr}.</p>
     *
     * <pre>
     * StringUtils.defaultIfEmpty(null, "NULL")  = "NULL"
     * StringUtils.defaultIfEmpty("", "NULL")    = "NULL"
     * StringUtils.defaultIfEmpty(" ", "NULL")   = " "
     * StringUtils.defaultIfEmpty("bat", "NULL") = "bat"
     * StringUtils.defaultIfEmpty("", null)      = null
     * </pre>
     *
     * @param <T> the specific kind of CharSequence
     * @param str the CharSequence to check, may be null
     * @param defaultStr the default CharSequence to return if the input is
     * empty ("") or {@code null}, may be null
     * @return the passed in CharSequence, or the default
     * @see StringUtils#defaultString(String, String)
     */
    public static <T extends CharSequence> T defaultIfEmpty(final T str, final T defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    // Reversing
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Reverses a String as per {@link StringBuilder#reverse()}.</p>
     *
     * <p>
     * A {@code null} String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.reverse(null)  = null
     * StringUtils.reverse("")    = ""
     * StringUtils.reverse("bat") = "tab"
     * </pre>
     *
     * @param str the String to reverse, may be null
     * @return the reversed String, {@code null} if null String input
     */
    public static String reverse(final String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }
    // Character Tests
    //-----------------------------------------------------------------------

    /**
     * <p>
     * Checks if the CharSequence contains only Unicode letters.</p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty CharSequence
     * (length()=0) will return {@code false}.</p>
     *
     * <pre>
     * StringUtils.isAlpha(null)   = false
     * StringUtils.isAlpha("")     = false
     * StringUtils.isAlpha("  ")   = false
     * StringUtils.isAlpha("abc")  = true
     * StringUtils.isAlpha("ab2c") = false
     * StringUtils.isAlpha("ab-c") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains letters, and is non-null
     * @since 3.0 Changed signature from isAlpha(String) to
     * isAlpha(CharSequence)
     * @since 3.0 Changed "" to return false and not true
     */
    public static boolean isAlpha(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetter(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the CharSequence contains only Unicode letters and space ('
     * ').</p>
     *
     * <p>
     * {@code null} will return {@code false} An empty CharSequence (length()=0)
     * will return {@code true}.</p>
     *
     * <pre>
     * StringUtils.isAlphaSpace(null)   = false
     * StringUtils.isAlphaSpace("")     = true
     * StringUtils.isAlphaSpace("  ")   = true
     * StringUtils.isAlphaSpace("abc")  = true
     * StringUtils.isAlphaSpace("ab c") = true
     * StringUtils.isAlphaSpace("ab2c") = false
     * StringUtils.isAlphaSpace("ab-c") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains letters and space, and is non-null
     * @since 3.0 Changed signature from isAlphaSpace(String) to
     * isAlphaSpace(CharSequence)
     */
    public static boolean isAlphaSpace(final CharSequence cs) {
        if (cs == null) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetter(cs.charAt(i)) == false && cs.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the CharSequence contains only Unicode letters or digits.</p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty CharSequence
     * (length()=0) will return {@code false}.</p>
     *
     * <pre>
     * StringUtils.isAlphanumeric(null)   = false
     * StringUtils.isAlphanumeric("")     = false
     * StringUtils.isAlphanumeric("  ")   = false
     * StringUtils.isAlphanumeric("abc")  = true
     * StringUtils.isAlphanumeric("ab c") = false
     * StringUtils.isAlphanumeric("ab2c") = true
     * StringUtils.isAlphanumeric("ab-c") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains letters or digits, and is non-null
     * @since 3.0 Changed signature from isAlphanumeric(String) to
     * isAlphanumeric(CharSequence)
     * @since 3.0 Changed "" to return false and not true
     */
    public static boolean isAlphanumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetterOrDigit(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the CharSequence contains only Unicode letters, digits or space
     * ({@code ' '}).</p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty CharSequence
     * (length()=0) will return {@code true}.</p>
     *
     * <pre>
     * StringUtils.isAlphanumericSpace(null)   = false
     * StringUtils.isAlphanumericSpace("")     = true
     * StringUtils.isAlphanumericSpace("  ")   = true
     * StringUtils.isAlphanumericSpace("abc")  = true
     * StringUtils.isAlphanumericSpace("ab c") = true
     * StringUtils.isAlphanumericSpace("ab2c") = true
     * StringUtils.isAlphanumericSpace("ab-c") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains letters, digits or space, and is
     * non-null
     * @since 3.0 Changed signature from isAlphanumericSpace(String) to
     * isAlphanumericSpace(CharSequence)
     */
    public static boolean isAlphanumericSpace(final CharSequence cs) {
        if (cs == null) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetterOrDigit(cs.charAt(i)) == false && cs.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if a CharSequence is not empty ("") and not null.</p>
     *
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is not empty and not null
     * @since 3.0 Changed signature from isNotEmpty(String) to
     * isNotEmpty(CharSequence)
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    

    /**
     * <p>
     * Checks if the CharSequence contains only Unicode digits. A decimal point
     * is not a Unicode digit and returns false.</p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty CharSequence
     * (length()=0) will return {@code false}.</p>
     *
     * <p>
     * Note that the method does not allow for a leading sign, either positive
     * or negative. Also, if a String passes the numeric test, it may still
     * generate a NumberFormatException when parsed by Integer.parseInt or
     * Long.parseLong, e.g. if the value is outside the range for int or long
     * respectively.</p>
     *
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = false
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("\u0967\u0968\u0969")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = false
     * StringUtils.isNumeric("-123") = false
     * StringUtils.isNumeric("+123") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains digits, and is non-null
     * @since 3.0 Changed signature from isNumeric(String) to
     * isNumeric(CharSequence)
     * @since 3.0 Changed "" to return false and not true
     */
    public static boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the CharSequence contains only Unicode digits or space
     * ({@code ' '}). A decimal point is not a Unicode digit and returns
     * false.</p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty CharSequence
     * (length()=0) will return {@code true}.</p>
     *
     * <pre>
     * StringUtils.isNumericSpace(null)   = false
     * StringUtils.isNumericSpace("")     = true
     * StringUtils.isNumericSpace("  ")   = true
     * StringUtils.isNumericSpace("123")  = true
     * StringUtils.isNumericSpace("12 3") = true
     * StringUtils.isNumeric("\u0967\u0968\u0969")  = true
     * StringUtils.isNumeric("\u0967\u0968 \u0969")  = true
     * StringUtils.isNumericSpace("ab2c") = false
     * StringUtils.isNumericSpace("12-3") = false
     * StringUtils.isNumericSpace("12.3") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains digits or space, and is non-null
     * @since 3.0 Changed signature from isNumericSpace(String) to
     * isNumericSpace(CharSequence)
     */
    public static boolean isNumericSpace(final CharSequence cs) {
        if (cs == null) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i)) == false && cs.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the CharSequence contains only whitespace.</p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty CharSequence
     * (length()=0) will return {@code true}.</p>
     *
     * <pre>
     * StringUtils.isWhitespace(null)   = false
     * StringUtils.isWhitespace("")     = true
     * StringUtils.isWhitespace("  ")   = true
     * StringUtils.isWhitespace("abc")  = false
     * StringUtils.isWhitespace("ab2c") = false
     * StringUtils.isWhitespace("ab-c") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains whitespace, and is non-null
     * @since 2.0
     * @since 3.0 Changed signature from isWhitespace(String) to
     * isWhitespace(CharSequence)
     */
    public static boolean isWhitespace(final CharSequence cs) {
        if (cs == null) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Uncapitalizes a String, changing the first letter to lower case as per
     * {@link Character#toLowerCase(char)}. No other letters are changed.</p>
     *
     * <p>
     * For a word based algorithm, see
     * {@link org.apache.commons.lang3.text.WordUtils#uncapitalize(String)}. A
     * {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.uncapitalize(null)  = null
     * StringUtils.uncapitalize("")    = ""
     * StringUtils.uncapitalize("cat") = "cat"
     * StringUtils.uncapitalize("Cat") = "cat"
     * StringUtils.uncapitalize("CAT") = "cAT"
     * </pre>
     *
     * @param str the String to uncapitalize, may be null
     * @return the uncapitalized String, {@code null} if null String input
     * @see org.apache.commons.lang3.text.WordUtils#uncapitalize(String)
     * @see #capitalize(String)
     * @since 2.0
     */
    public static String uncapitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        final char firstChar = str.charAt(0);
        final char newChar = Character.toLowerCase(firstChar);
        if (firstChar == newChar) {
            // already uncapitalized
            return str;
        }
        char[] newChars = new char[strLen];
        newChars[0] = newChar;
        str.getChars(1, strLen, newChars, 1);
        return String.valueOf(newChars);
    }

    /**
     * <p>
     * Swaps the case of a String changing upper and title case to lower case,
     * and lower case to upper case.</p>
     *
     * <ul>
     * <li>Upper case character converts to Lower case</li>
     * <li>Title case character converts to Lower case</li>
     * <li>Lower case character converts to Upper case</li>
     * </ul>
     *
     * <p>
     * For a word based algorithm, see
     * {@link org.apache.commons.lang3.text.WordUtils#swapCase(String)}. A
     * {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.swapCase(null)                 = null
     * StringUtils.swapCase("")                   = ""
     * StringUtils.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     *
     * <p>
     * NOTE: This method changed in Lang version 2.0. It no longer performs a
     * word based algorithm. If you only use ASCII, you will notice no change.
     * That functionality is available in
     * org.apache.commons.lang3.text.WordUtils.</p>
     *
     * @param str the String to swap case, may be null
     * @return the changed String, {@code null} if null String input
     */
    public static String swapCase(final String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        final char[] buffer = str.toCharArray();
        for (int i = 0; i < buffer.length; i++) {
            final char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                buffer[i] = Character.toUpperCase(ch);
            }
        }
        return new String(buffer);
    }

    // Case conversion
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Converts a String to upper case as per {@link String#toUpperCase()}.</p>
     *
     * <p>
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.upperCase(null)  = null
     * StringUtils.upperCase("")    = ""
     * StringUtils.upperCase("aBc") = "ABC"
     * </pre>
     *
     * <p>
     * <strong>Note:</strong> As described in the documentation for
     * {@link String#toUpperCase()}, the result of this method is affected by
     * the current locale. For platform-independent case transformations, the
     * method {@link #lowerCase(String, Locale)} should be used with a specific
     * locale (e.g. {@link Locale#ENGLISH}).</p>
     *
     * @param str the String to upper case, may be null
     * @return the upper cased String, {@code null} if null String input
     */
    public static String upperCase(final String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * <p>
     * Converts a String to upper case as per
     * {@link String#toUpperCase(Locale)}.</p>
     *
     * <p>
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.upperCase(null, Locale.ENGLISH)  = null
     * StringUtils.upperCase("", Locale.ENGLISH)    = ""
     * StringUtils.upperCase("aBc", Locale.ENGLISH) = "ABC"
     * </pre>
     *
     * @param str the String to upper case, may be null
     * @param locale the locale that defines the case transformation rules, must
     * not be null
     * @return the upper cased String, {@code null} if null String input
     * @since 2.5
     */
    public static String upperCase(final String str, final Locale locale) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase(locale);
    }

    /**
     * <p>
     * Converts a String to lower case as per {@link String#toLowerCase()}.</p>
     *
     * <p>
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.lowerCase(null)  = null
     * StringUtils.lowerCase("")    = ""
     * StringUtils.lowerCase("aBc") = "abc"
     * </pre>
     *
     * <p>
     * <strong>Note:</strong> As described in the documentation for
     * {@link String#toLowerCase()}, the result of this method is affected by
     * the current locale. For platform-independent case transformations, the
     * method {@link #lowerCase(String, Locale)} should be used with a specific
     * locale (e.g. {@link Locale#ENGLISH}).</p>
     *
     * @param str the String to lower case, may be null
     * @return the lower cased String, {@code null} if null String input
     */
    public static String lowerCase(final String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * <p>
     * Converts a String to lower case as per
     * {@link String#toLowerCase(Locale)}.</p>
     *
     * <p>
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.lowerCase(null, Locale.ENGLISH)  = null
     * StringUtils.lowerCase("", Locale.ENGLISH)    = ""
     * StringUtils.lowerCase("aBc", Locale.ENGLISH) = "abc"
     * </pre>
     *
     * @param str the String to lower case, may be null
     * @param locale the locale that defines the case transformation rules, must
     * not be null
     * @return the lower cased String, {@code null} if null String input
     * @since 2.5
     */
    public static String lowerCase(final String str, final Locale locale) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase(locale);
    }

    /**
     * <p>
     * Capitalizes a String changing the first letter to title case as per
     * {@link Character#toTitleCase(char)}. No other letters are changed.</p>
     *
     * <p>
     * For a word based algorithm, see
     * {@link org.apache.commons.lang3.text.WordUtils#capitalize(String)}. A
     * {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.capitalize(null)  = null
     * StringUtils.capitalize("")    = ""
     * StringUtils.capitalize("cat") = "Cat"
     * StringUtils.capitalize("cAt") = "CAt"
     * </pre>
     *
     * @param str the String to capitalize, may be null
     * @return the capitalized String, {@code null} if null String input
     * @see org.apache.commons.lang3.text.WordUtils#capitalize(String)
     * @see #uncapitalize(String)
     * @since 2.0
     */
    public static String capitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        final char firstChar = str.charAt(0);
        final char newChar = Character.toUpperCase(firstChar);
        if (firstChar == newChar) {
            // already capitalized
            return str;
        }
        char[] newChars = new char[strLen];
        newChars[0] = newChar;
        str.getChars(1, strLen, newChars, 1);
        return String.valueOf(newChars);
    }
    
    /**
 * <p>
 * Checks if a CharSequence is whitespace, empty ("") or null.</p>
 *
 * <pre>
 * StringUtils.isBlank(null)      = true
 * StringUtils.isBlank("")        = true
 * StringUtils.isBlank(" ")       = true
 * StringUtils.isBlank("bob")     = false
 * StringUtils.isBlank("  bob  ") = false
 * </pre>
 *
 * @param cs the CharSequence to check, may be null
 * @return {@code true} if the CharSequence is null, empty or whitespace
 * @since 2.0
 * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
 */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    
    // Empty checks
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Checks if a CharSequence is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>
     * NOTE: This method changed in Lang version 2.0. It no longer trims the
     * CharSequence. That functionality is available in isBlank().</p>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is empty or null
     * @since 3.0 Changed signature from isEmpty(String) to
     * isEmpty(CharSequence)
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    
    

    // Trim
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Removes control characters (char &lt;= 32) from both ends of this String,
     * handling {@code null} by returning {@code null}.</p>
     *
     * <p>
     * The String is trimmed using {@link String#trim()}. Trim removes start and
     * end characters &lt;= 32. To strip whitespace use
     * {@link #strip(String)}.</p>
     *
     * <p>
     * To trim your choice of characters, use the {@link #strip(String, String)}
     * methods.</p>
     *
     * <pre>
     * StringUtils.trim(null)          = null
     * StringUtils.trim("")            = ""
     * StringUtils.trim("     ")       = ""
     * StringUtils.trim("abc")         = "abc"
     * StringUtils.trim("    abc    ") = "abc"
     * </pre>
     *
     * @param str the String to be trimmed, may be null
     * @return the trimmed string, {@code null} if null String input
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }
    
    

    /**
     * <p>
     * Checks if a CharSequence is not empty (""), not null and not whitespace
     * only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is not empty and not null and
     * not whitespace
     * @since 2.0
     * @since 3.0 Changed signature from isNotBlank(String) to
     * isNotBlank(CharSequence)
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    

    

    /**
     * <p>
     * Removes control characters (char &lt;= 32) from both ends of this String
     * returning {@code null} if the String is empty ("") after the trim or if
     * it is {@code null}.
     *
     * <p>
     * The String is trimmed using {@link String#trim()}. Trim removes start and
     * end characters &lt;= 32. To strip whitespace use
     * {@link #stripToNull(String)}.</p>
     *
     * <pre>
     * StringUtils.trimToNull(null)          = null
     * StringUtils.trimToNull("")            = null
     * StringUtils.trimToNull("     ")       = null
     * StringUtils.trimToNull("abc")         = "abc"
     * StringUtils.trimToNull("    abc    ") = "abc"
     * </pre>
     *
     * @param str the String to be trimmed, may be null
     * @return the trimmed String, {@code null} if only chars &lt;= 32, empty or
     * null String input
     * @since 2.0
     */
    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * <p>
     * Removes control characters (char &lt;= 32) from both ends of this String
     * returning an empty String ("") if the String is empty ("") after the trim
     * or if it is {@code null}.
     *
     * <p>
     * The String is trimmed using {@link String#trim()}. Trim removes start and
     * end characters &lt;= 32. To strip whitespace use
     * {@link #stripToEmpty(String)}.</p>
     *
     * <pre>
     * StringUtils.trimToEmpty(null)          = ""
     * StringUtils.trimToEmpty("")            = ""
     * StringUtils.trimToEmpty("     ")       = ""
     * StringUtils.trimToEmpty("abc")         = "abc"
     * StringUtils.trimToEmpty("    abc    ") = "abc"
     * </pre>
     *
     * @param str the String to be trimmed, may be null
     * @return the trimmed String, or an empty String if {@code null} input
     * @since 2.0
     */
    public static String trimToEmpty(final String str) {
        return str == null ? EMPTY : str.trim();
    }

    // Stripping
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Strips whitespace from the start and end of a String.</p>
     *
     * <p>
     * This is similar to {@link #trim(String)} but removes whitespace.
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <p>
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.strip(null)     = null
     * StringUtils.strip("")       = ""
     * StringUtils.strip("   ")    = ""
     * StringUtils.strip("abc")    = "abc"
     * StringUtils.strip("  abc")  = "abc"
     * StringUtils.strip("abc  ")  = "abc"
     * StringUtils.strip(" abc ")  = "abc"
     * StringUtils.strip(" ab c ") = "ab c"
     * </pre>
     *
     * @param str the String to remove whitespace from, may be null
     * @return the stripped String, {@code null} if null String input
     */
    public static String strip(final String str) {
        return strip(str, null);
    }

    /**
     * <p>
     * Strips whitespace from the start and end of a String returning
     * {@code null} if the String is empty ("") after the strip.</p>
     *
     * <p>
     * This is similar to {@link #trimToNull(String)} but removes whitespace.
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripToNull(null)     = null
     * StringUtils.stripToNull("")       = null
     * StringUtils.stripToNull("   ")    = null
     * StringUtils.stripToNull("abc")    = "abc"
     * StringUtils.stripToNull("  abc")  = "abc"
     * StringUtils.stripToNull("abc  ")  = "abc"
     * StringUtils.stripToNull(" abc ")  = "abc"
     * StringUtils.stripToNull(" ab c ") = "ab c"
     * </pre>
     *
     * @param str the String to be stripped, may be null
     * @return the stripped String, {@code null} if whitespace, empty or null
     * String input
     * @since 2.0
     */
    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        str = strip(str, null);
        return str.isEmpty() ? null : str;
    }

    /**
     * <p>
     * Strips whitespace from the start and end of a String returning an empty
     * String if {@code null} input.</p>
     *
     * <p>
     * This is similar to {@link #trimToEmpty(String)} but removes whitespace.
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripToEmpty(null)     = ""
     * StringUtils.stripToEmpty("")       = ""
     * StringUtils.stripToEmpty("   ")    = ""
     * StringUtils.stripToEmpty("abc")    = "abc"
     * StringUtils.stripToEmpty("  abc")  = "abc"
     * StringUtils.stripToEmpty("abc  ")  = "abc"
     * StringUtils.stripToEmpty(" abc ")  = "abc"
     * StringUtils.stripToEmpty(" ab c ") = "ab c"
     * </pre>
     *
     * @param str the String to be stripped, may be null
     * @return the trimmed String, or an empty String if {@code null} input
     * @since 2.0
     */
    public static String stripToEmpty(final String str) {
        return str == null ? EMPTY : strip(str, null);
    }

    /**
     * <p>
     * Strips any of a set of characters from the start and end of a String.
     * This is similar to {@link String#trim()} but allows the characters to be
     * stripped to be controlled.</p>
     *
     * <p>
     * A {@code null} input String returns {@code null}. An empty string ("")
     * input returns the empty string.</p>
     *
     * <p>
     * If the stripChars String is {@code null}, whitespace is stripped as
     * defined by {@link Character#isWhitespace(char)}. Alternatively use
     * {@link #strip(String)}.</p>
     *
     * <pre>
     * StringUtils.strip(null, *)          = null
     * StringUtils.strip("", *)            = ""
     * StringUtils.strip("abc", null)      = "abc"
     * StringUtils.strip("  abc", null)    = "abc"
     * StringUtils.strip("abc  ", null)    = "abc"
     * StringUtils.strip(" abc ", null)    = "abc"
     * StringUtils.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str the String to remove characters from, may be null
     * @param stripChars the characters to remove, null treated as whitespace
     * @return the stripped String, {@code null} if null String input
     */
    public static String strip(String str, final String stripChars) {
        if (isEmpty(str)) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }

    /**
     * <p>
     * Strips any of a set of characters from the start of a String.</p>
     *
     * <p>
     * A {@code null} input String returns {@code null}. An empty string ("")
     * input returns the empty string.</p>
     *
     * <p>
     * If the stripChars String is {@code null}, whitespace is stripped as
     * defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripStart(null, *)          = null
     * StringUtils.stripStart("", *)            = ""
     * StringUtils.stripStart("abc", "")        = "abc"
     * StringUtils.stripStart("abc", null)      = "abc"
     * StringUtils.stripStart("  abc", null)    = "abc"
     * StringUtils.stripStart("abc  ", null)    = "abc  "
     * StringUtils.stripStart(" abc ", null)    = "abc "
     * StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str the String to remove characters from, may be null
     * @param stripChars the characters to remove, null treated as whitespace
     * @return the stripped String, {@code null} if null String input
     */
    public static String stripStart(final String str, final String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while (start != strLen && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (start != strLen && stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND) {
                start++;
            }
        }
        return str.substring(start);
    }

    /**
     * <p>
     * Strips any of a set of characters from the end of a String.</p>
     *
     * <p>
     * A {@code null} input String returns {@code null}. An empty string ("")
     * input returns the empty string.</p>
     *
     * <p>
     * If the stripChars String is {@code null}, whitespace is stripped as
     * defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripEnd(null, *)          = null
     * StringUtils.stripEnd("", *)            = ""
     * StringUtils.stripEnd("abc", "")        = "abc"
     * StringUtils.stripEnd("abc", null)      = "abc"
     * StringUtils.stripEnd("  abc", null)    = "  abc"
     * StringUtils.stripEnd("abc  ", null)    = "abc"
     * StringUtils.stripEnd(" abc ", null)    = " abc"
     * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
     * StringUtils.stripEnd("120.00", ".0")   = "12"
     * </pre>
     *
     * @param str the String to remove characters from, may be null
     * @param stripChars the set of characters to remove, null treated as
     * whitespace
     * @return the stripped String, {@code null} if null String input
     */
    public static String stripEnd(final String str, final String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }
        if (stripChars == null) {
            while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    // StripAll
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Strips whitespace from the start and end of every String in an array.
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <p>
     * A new array is returned each time, except for length zero. A {@code null}
     * array will return {@code null}. An empty array will return itself. A
     * {@code null} array entry will be ignored.</p>
     *
     * <pre>
     * StringUtils.stripAll(null)             = null
     * StringUtils.stripAll([])               = []
     * StringUtils.stripAll(["abc", "  abc"]) = ["abc", "abc"]
     * StringUtils.stripAll(["abc  ", null])  = ["abc", null]
     * </pre>
     *
     * @param strs the array to remove whitespace from, may be null
     * @return the stripped Strings, {@code null} if null array input
     */
    public static String[] stripAll(final String... strs) {
        return stripAll(strs, null);
    }

    /**
     * <p>
     * Strips any of a set of characters from the start and end of every String
     * in an array.</p>
     * <p>
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <p>
     * A new array is returned each time, except for length zero. A {@code null}
     * array will return {@code null}. An empty array will return itself. A
     * {@code null} array entry will be ignored. A {@code null} stripChars will
     * strip whitespace as defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripAll(null, *)                = null
     * StringUtils.stripAll([], *)                  = []
     * StringUtils.stripAll(["abc", "  abc"], null) = ["abc", "abc"]
     * StringUtils.stripAll(["abc  ", null], null)  = ["abc", null]
     * StringUtils.stripAll(["abc  ", null], "yz")  = ["abc  ", null]
     * StringUtils.stripAll(["yabcz", null], "yz")  = ["abc", null]
     * </pre>
     *
     * @param strs the array to remove characters from, may be null
     * @param stripChars the characters to remove, null treated as whitespace
     * @return the stripped Strings, {@code null} if null array input
     */
    public static String[] stripAll(final String[] strs, final String stripChars) {
        int strsLen;
        if (strs == null || (strsLen = strs.length) == 0) {
            return strs;
        }
        final String[] newArr = new String[strsLen];
        for (int i = 0; i < strsLen; i++) {
            newArr[i] = strip(strs[i], stripChars);
        }
        return newArr;
    }
}



