import java.util.*;
public class TestPolandExpression{

	public static void main(String[] args) {
		String expression = "1+((2+3)*4)-5";
		List<String> polandExpr = convertToPoland(expression);
		polandExpr = reverseList(polandExpr);
		for(int i = 0; i< polandExpr.size(); i++) {
			System.out.println(polandExpr.get(i));
		}
		int result = calc(polandExpr);
		System.out.println("result: " + result);
	}

	public static List<String> reverseList(List<String> src){
		List<String> result = new ArrayList<String>();
		for(int i = src.size() - 1; i >= 0; i--) {
			result.add(src.get(i));
		}
		return result;
	}

	public static int calc(List<String> polandExpr){
		Stack<Integer> calcResultStack = new Stack<Integer>();
		int i = 0;
		while(polandExpr.size() > 0){
			String tmp = polandExpr.get(i);
			if("+-*/".indexOf(tmp) > -1){
				int num2 = calcResultStack.pop();
				int num1 = calcResultStack.pop();
				calcResultStack.push(doCalc(num1, num2, tmp));
			}else{
				calcResultStack.push(Integer.parseInt(tmp));
			}
			polandExpr.remove(i);
		}
		
		return calcResultStack.pop(); 
	} 
	
	public static int doCalc(int num1, int num2, String op){
		if("+".equals(op)) {
			return num1 + num2;
		}else if("-".equals(op)) {
			return num1 - num2;
		}else if("*".equals(op)) {
			return num1 * num2;
		} else if("/".equals(op)) {
			return num1 / num2;
		}else{
			throw new RuntimeException("非法的符号： " + op);
		}
	} 

	public static List convertToPoland(String expression) {
		List<String> result = new ArrayList<String>();
		Stack<String> numStack = new Stack<String>();
		Stack<String> operStack = new Stack<String>();
		String numStr = "";
		while(expression.length() > 0){
			String ch = "" + expression.charAt(0);
			if(ch.matches("\\d+")){
				numStr = numStr + ch;
			}else{
				if(!"".equals(numStr)){// 有效的数字才能加入
					numStack.push(numStr);
				}
				numStr = "";
				checkOper(ch, numStack, operStack);
			}

			expression = expression.substring(1);
		}
		// ！！！这一步总是容易忘掉
		numStack.push(numStr);
	
		while(!operStack.empty()){
			numStack.push(operStack.pop());
		}
		
		while(!numStack.empty()){
			System.out.println(numStack.peek());
			result.add(numStack.pop());
		}

		return result;
	}

	public static void checkOper(String ch,Stack<String> numStack,Stack<String> operStack){
		if("+-*/".indexOf(ch) > -1){// 是运算符号
			if(operStack.empty()) {
				operStack.push(ch);
				return;
			}

			String front = operStack.peek();
			if(front.equals("(")) {
				operStack.push(ch);
				return;
			}

			if(getOperLevl(ch) > getOperLevl(front)){
				operStack.push(ch);
			}else{
				numStack.push(operStack.pop());
				checkOper(ch, numStack, operStack);
			}

		}else{// 是括号
			if("(".equals(ch)){
				operStack.push(ch);
			}else{// ch是 ")"
				String tmp = operStack.pop();
				if(!"(".equals(tmp)){
					numStack.push(tmp);
					// 继续从符号栈中弹元素
					checkOper(ch, numStack, operStack);
				}
			}
		}

	}

	public static int getOperLevl(String oper){
	  return (oper == "+" || oper == "-") ? 0 : 1;
	}

}