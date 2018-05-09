package be.lsinf1225gr12.minipoll.minipoll;

import android.text.TextUtils;

/**
 * String checking utility class.
 */
public class InputValidation {

    public static boolean isNullOrWhitespace(String s) {
        return s == null || isWhitespace(s);

    }

    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int start = 0, middle = length / 2, end = length - 1; start <= middle; start++, end--) {
                if (s.charAt(start) > ' ' || s.charAt(end) > ' ') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidField(String s) {
        return !(TextUtils.isEmpty(s) || s.charAt(0) == ' ' || s.charAt(s.length() - 1) == ' ');
    }
}
