package JunitTest;

import org.junit.Test;

import util.ValidateCodeUtil;

public class JunitValidCode {

	@Test
	public void test() {
		String code = ValidateCodeUtil.productCode();
		
		System.out.println(code);
	}

}