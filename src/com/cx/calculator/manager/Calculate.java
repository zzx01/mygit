



package com.cx.calculator.manager;
/**
 * 业务层
 * 实现计算器的加减乘除
 * @author Administrator
 *
 */
public class Calculate {
	public Double add(Double a,Double b) {//加运算
		return a+b;	
	}

	public Double sub(Double a,Double b) {//减运算
		return a-b;
	}
	
	public Double multi(Double a,Double b) {//乘运算
		return a*b;
	}
	
	public Double dev(Double a,Double b) {//除运算
		return a/b;
	}

}
