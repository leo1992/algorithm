/**
 * Created by zhangying on 5/17/18.
 */
public class Roman {

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int first = num / 1000;
        if (first > 0) makeMulti(sb, first, 'M');
        int devide = num % 1000;
        first = devide / 100;
        if (first > 0 && first < 10) roman(sb, first, 'C', 'D', 'M');
        devide = devide % 100;
        first = devide / 10;
        if (first > 0 && first < 10) roman(sb, first, 'X', 'L', 'C');
        first = devide % 10;
        if (first > 0 && first < 10) roman(sb, first, 'I', 'V', 'X');
        return sb.toString();
    }

    public void roman(StringBuilder sb, int num, char one, char five, char ten) {
        if (num < 4) {
            makeMulti(sb, num, one);
        } else if (num == 4) {
            sb.append(one);
            sb.append(five);
        } else if (num < 9) {
            sb.append(five);
            makeMulti(sb, num - 5, one);
        } else if (num == 9) {
            sb.append(one);
            sb.append(ten);
        }
    }

    public void makeMulti(StringBuilder builder, int n, char c) {
        for (int i = 0; i < n; i++) builder.append(c);
    }

}
