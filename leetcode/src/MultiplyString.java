/**
 * Created by zhangying on 6/8/18.
 */
public class MultiplyString {

    public String multiply(String num1, String num2) {
        System.out.println("num1: " + num1);
        System.out.println("num2: " + num2);
        int length1 = num1.length();
        int length2 = num2.length();

        String one = num1;
        String two = num2;
        int length = length2;
        if (length2 > length1) {
            one = num2;
            two = num1;
            length = length1;
        }
        String result = addSelfTimes(one, two.charAt(0) - '0');
        for (int i = 1; i < length; i++) {
            result = add(
                    addSelfTimes(result, 10),
                    addSelfTimes(one, (two.charAt(i) - '0')));
        }
        System.out.println("result: " + result);

        return result;
    }

    public String addSelfTimes(String a, int times) {
        if (times == 10) return a + "0";
        if (times == 0) return "0";
        if (times > 10 || times < 0) return "0";
        int length = a.length();
        int add = 0;
        char[] resultC = new char[length];
        for (int i = 0; i < length; i++) {
            char ac = a.charAt(length - 1 - i);
            char temp = (char) ('0' + (ac - '0') * times + add);
            add = 0;
            while (temp > '9') {
                add++;
                temp = (char) (temp - 10);
            }
            resultC[length - 1 - i] = temp;
        }
        String result = new String(resultC);
        if (add != 0) return (char) (add + '0') + result;
        return result;
    }

    public String add(String a, String b) {
        int lengthA = a.length();
        int lengthB = b.length();
        int max = Math.max(lengthA, lengthB);
        int add = 0;
        char[] resultC = new char[max];
        for (int i = 0; i < max; i++) {
            char ac = i < lengthA ? a.charAt(lengthA - 1 - i) : '0';
            char bc = i < lengthB ? b.charAt(lengthB - 1 - i) : '0';
            char temp = (char) (ac + bc - '0' + add);
            if (temp > '9') {
                temp = (char) (temp - 10);
                add = 1;
            } else {
                add = 0;
            }
            resultC[max - 1 - i] = temp;
        }
        String result = new String(resultC);
        if (add == 1) return "1" + result;
        return result;
    }
}
