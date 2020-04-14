

	function main() {
		var expression = "1+((2+3)*4)-5";
		var polandExpr = convertToPoland(expression);		
		
		for(var i = 0; i< polandExpr.length; i++) {
			console.log(polandExpr[i]);
		}
	
		var result = calc(polandExpr);
		console.log("result: " + result);

	}


	main();
	
	function convertToPoland(expression) {
			// 数字栈
			var numStack = new Array();

			// 符号栈
			var operStack = new Array();

			// 保存临时数字的变量
			var tmpStr = '';
			while(expression.length > 0){
				var ch = expression[0];

				if('+-*/()'.indexOf(ch) > -1){
					if(tmpStr != ''){
						console.log("tmpStr: ", tmpStr);
						numStack.push(tmpStr);
						tmpStr = '';
					}

					checkOper(ch, numStack, operStack);
				}else{// 是数字
					tmpStr = ch + tmpStr;
				}
				console.log("ch: ", ch);
				expression = expression.substring(1);
			}
			numStack.push(tmpStr);

			while(operStack.length > 0){
				numStack.push(operStack.pop());
			}
			console.log("numStack: ", numStack);
			return numStack;
	}

	function reverseList(src){
		var result = new Array();
		for(var i = src.length - 1; i >= 0; i--) {
			result.push(src[i]);
		}
		return result;
	}

	function checkOper(ch, numStack, operStack){
		var frontCh = '';
		if("+-*/".indexOf(ch) > -1){// 是+-*/运算符
			// a.符号栈为空，运算符直接入栈
			if(operStack.length == 0){
				operStack.push(ch);
				return;
			}

			frontCh = operStack.pop();

			// b.如果符号栈栈顶元素是 '(', 直接入栈
			if('(' == frontCh){
				operStack.push(frontCh);
				operStack.push(ch);
				return;
			}

			// 当前符号的运算优先级 > 栈顶元素的优先级， 将ch 入符号栈
			if(getOperLevl(ch) > getOperLevl(frontCh)){
				operStack.push(frontCh);
				operStack.push(ch);
			}else{// 否则，将栈顶元素入栈到数字栈，继续检查 下一个栈顶元素
				numStack.push(frontCh);
				checkOper(ch, numStack, operStack);
			}
		}else{
			if('(' == ch){
				operStack.push(ch);
				return;
			}else{// ch 是 ')'
				frontCh = operStack.pop();
				if(frontCh == '('){// 直接删掉 与当前 '(' 配对的 ')'
					return;
				}else{
					numStack.push(frontCh);
					checkOper(ch, numStack, operStack);
				}		
			}
		}
	}

	function getOperLevl(oper){
	  return (oper == "+" || oper == "-") ? 0 : 1;
	}

	function calc(polandExpr){
		var resultStack = new Array();
		
		while(polandExpr.length > 0){
			var tmp = polandExpr.shift();
			if(!/\d+/.test(tmp)){
				var rightNum = resultStack.pop(); 
				var leftNum = resultStack.pop();				
				var result = doCalc(leftNum, rightNum, tmp);
				resultStack.push(result);
			}else{
				resultStack.push(parseInt(tmp));
			}
		}

		console.log("计算结果是:", resultStack.pop());
	} 
	
	function doCalc(num1, num2, op){
		if("+" == op) {
			return num1 + num2;
		}else if("-" == op) {
			return num1 - num2;
		}else if("*" == op) {
			return num1 * num2;
		} else if("/" == op) {
			return num1 / num2;
		}else{
			throw new Error("非法的符号： " + op);
		}
	} 
