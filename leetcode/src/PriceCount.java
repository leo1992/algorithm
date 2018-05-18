package leetcode;

public class PriceCount {

	public void run() {
		System.out.println(divide(-2147483648, -1));

	}
	
	 private static int divide(int dividend, int divisor) {
	        if (divisor == 0) return 0;
	        if (dividend == 0) return 0;
	        boolean isMinus = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
	    
	        int i = 0;
	        int start = dividend;
	        if (dividend > 0)  {
	            while (start >= 0) {
	                if (isMinus) start -= divisor;
	                else start += divisor;
	                i++;
	            }
	        } else {
	            while (start <= 0) {
	                if (isMinus) start -= divisor;
	                else start += divisor;
	                i++;
	            }
	        }
			System.out.println("i: " + i);

	        return isMinus ? (i-1) : (1-i);
	    }
	

}
