package study2.mapping;

public class Test4Calc {
	public int getCalc(int su1, int su2, String opt) {// void는 외부로보내지못함
		int res = 0;
		if(opt.equals("+")) res = su1 + su2;
		else if(opt.equals("-")) res = su1 - su2;
		else if(opt.equals("*")) res = su1 * su2;
		else if(opt.equals("/")) res = su1 / su2;
		else res = su1 % su2;
		
		return res; //res를 다시 넘겨받고, 컨트롤러로 간다.,
	}
}
