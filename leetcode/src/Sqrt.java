package leetcode;

public class Sqrt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(mySqrt(45234));
	}
	
	public static int mySqrt(int x) {
		if (x < 0) return -1;
		if (x <= 1) return x;
		int start = 1;
		int end = x;
		while (start < end) {
			int mid = start + (end - start) / 2 ;
			if (mid == x / mid) {
				return mid;
			} else if (mid < x / mid) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
        return end;
    }

}
