package array;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }

    public static int romanToInt(String s) {
        Map<Character, Integer> d = new HashMap<>();
        d.put('I', 1);
        d.put('V', 5);
        d.put('X', 10);
        d.put('L', 50);
        d.put('C', 100);
        d.put('D', 500);
        d.put('M', 1000);

        int sum = 0;
        int size = s.length();
        for (int i = 0; i < size; i++) {
            if ((size - 1) - i > 0 && d.get(s.charAt(i)) < d.get(s.charAt(i + 1))) {
                sum += d.get(s.charAt(i + 1)) - d.get(s.charAt(i));
                i++;
            } else {
                sum += d.get(s.charAt(i));
            }
        }

        return sum;
    }
}
