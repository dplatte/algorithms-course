package course1.week1;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        String x = "3141592653589793238462643383279502884197169399375105820974944592";
        String y = "2718281828459045235360287471352662497757247093699959574966967627";

        System.out.println(karatsubaMult(x, y));
        System.out.println(new BigInteger(x).multiply(new BigInteger(y)));

    }

    private static String karatsubaMult(String x, String y) {

        //Base Case
        if(x.length() == 1 || y.length() == 1) {
            return String.valueOf(Integer.parseInt(x) * Integer.parseInt(y));
        }

        int n = Math.max(x.length(), y.length());

        //Make sure the length is even
        if (n % 2 != 0) {
            n++;
        }

        //Prepend zeros to make lengths even
        StringBuilder xBuilder = new StringBuilder(x);
        while (xBuilder.length() != n) {
            xBuilder.insert(0, "0");
        }

        StringBuilder yBuilder = new StringBuilder(y);
        while (yBuilder.length() != n) {
            yBuilder.insert(0, "0");
        }

        String a = xBuilder.substring(0, n / 2);
        String b = xBuilder.substring(n / 2);
        String c = yBuilder.substring(0, n / 2);
        String d = yBuilder.substring(n / 2);

        //Use string add function instead
        String p = addStrings(a, b);
        String q = addStrings(c, d);

        //Recursive section
        String ac = karatsubaMult(a, c);
        String bd = karatsubaMult(b, d);
        String pq = karatsubaMult(p, q);


        //Use string subtract function instead
        String adbc = subtractStrings(subtractStrings(pq, ac), bd);

        //Final merging of products
        return addStrings(addStrings(appendZeros(ac, n), appendZeros(adbc, n / 2)), bd);
    }

    private static String appendZeros(String s, int n) {
        StringBuilder sBuilder = new StringBuilder(s);
        for(int i = 1; i <= n; i++) {
            sBuilder.append("0");
        }

        return sBuilder.toString();
    }

    private static String addStrings(String s1, String s2) {
        //Null checking
        if(s1 == null || s1.isEmpty()) {
            return s2;
        } else if(s2 == null || s2.isEmpty()) {
            return s1;
        }

        //Make sure s1 is the longest string
        if(s2.length() > s1.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        StringBuilder result = new StringBuilder();
        int carry = 0;
        for(int i = 0; i < s1.length(); i++) {

            //Apply the previous carry value
            int tempIntResult = carry;

            //Reset carry
            carry = 0;

            int s1Int = Integer.parseInt(String.valueOf(s1.charAt(s1.length() - 1 - i)));
            int s2Int = 0;

            if(i < s2.length()) {
                s2Int = Integer.parseInt(String.valueOf(s2.charAt(s2.length() - 1 - i)));
            }

            tempIntResult += s1Int + s2Int;

            if(tempIntResult >= 10) {
                carry = 1;
                tempIntResult = tempIntResult % 10;
            }

            result.append(tempIntResult);
        }

        //Append the last 1 if needed
        if(carry == 1) {
            result.append(carry);
        }

        //Remove extra zeros
        while(result.length() > 1 && result.lastIndexOf("0") == result.length() - 1) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.reverse().toString();

    }

    private static String subtractStrings(String s1, String s2) {

        //Null checking
        if(s1 == null || s1.isEmpty()) {
            return s2;
        } else if(s2 == null || s2.isEmpty()) {
            return s1;
        }

        //Make sure s1 is the longest string
        if(s2.length() > s1.length()) {
            System.out.println("Swapping strings in subtract.");
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        StringBuilder result = new StringBuilder();
        int carry = 0;
        for(int i = 0; i < s1.length(); i++) {

            //Apply the previous carry value
            int tempIntResult = carry;

            //Reset carry
            carry = 0;

            int s1Int = Integer.parseInt(String.valueOf(s1.charAt(s1.length() - 1 - i)));
            int s2Int = 0;

            if(i < s2.length()) {
                s2Int = Integer.parseInt(String.valueOf(s2.charAt(s2.length() - 1 - i)));
            }

            tempIntResult = s1Int - s2Int - tempIntResult;

            if(tempIntResult < 0) {
                carry = 1;
                tempIntResult = 10 + tempIntResult;
            }

            result.append(tempIntResult);
        }

        return result.reverse().toString();

    }

    /*
        Lessons learned:
        1. Math.pow() doesn't work with 64-bit numbers, have to implement your own
        2. Karatsuba's works with prepending zeros to even out both integer lengths or make them into an even n-digits
        3. long can't store 64-bit integers, need to use BigInteger (but there might be another way)
            a. There is a way to add two strings together as numbers
            b. There should also be a way to subtract two strings as numbers
            c. Instead of 10 ^ n, we could just append zeros to ac n times or to bd n/2 times
     */
}
