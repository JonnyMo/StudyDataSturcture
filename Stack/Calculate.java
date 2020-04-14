
public class Calculate{

  public static void main(String[] args) {
    System.out.println("123".substring(0, 1));
    splitBySymbol("5-2*3+1");
  }

  public static void splitBySymbol(String expressionStr){
    MyStack numStack = new MyStack(10);
    MyStack operStack = new MyStack(10);

    int symbolIndex = -1;
    String numStr = "";
    
    while(expressionStr.length() > 0){
      char tmp = expressionStr.charAt(0);

      symbolIndex = "+-*/".indexOf(tmp);

      // 1.如果 当前的字符是符号, 输出 符号:
      if(symbolIndex > -1){
        System.out.println("数字: " + numStr);
        System.out.println("符号: " + tmp);

        numStack.push(Integer.parseInt(numStr + ""));
        // 校验是否可以加入
        checkOperator(tmp, operStack, numStack);
        numStr = "";
      }else if(symbolIndex == -1){// 当前的字符是数字
        numStr = numStr + tmp;
      }

      expressionStr = expressionStr.substring(1, expressionStr.length());
      System.out.println("expressionStr: " + expressionStr);      
    }
    numStack.push(Integer.parseInt(numStr));

    while(!operStack.isEmpty()){
      char op = (char)operStack.pop();
      int rightNum = (int)numStack.pop();
      int leftNum = (int)numStack.pop();
      int result = operStack.doOperate(leftNum, rightNum, op);
      numStack.push(result);
    }

    System.out.println(numStack.pop());
  }


  public static void checkOperator(char tmp, MyStack operStack, MyStack numStack){
    //1. 如果操作符栈中是空的，直接加入操作符栈
    //2. 如果操作符栈中存在元素，则比较运算符的优先级
    //   如果当前运算符优先级较低
    //    从数栈中先后取出rightNum, leftNum, 
    //    leftNum rightNum 进行运算， 使用
    //   当前运算符优先级高，则加入到符号栈
    if(operStack.isEmpty()){
      operStack.push((int)tmp);
      return;
    }

    char oper = (char)operStack.peek();
    // leftNum tmp rightNum
    if(operStack.getOperLevl(tmp) <= operStack.getOperLevl(oper)){
      int rightNum = (int)numStack.pop();
      int leftNum = (int)numStack.pop();

      leftNum = operStack.doOperate(leftNum, rightNum, oper);
      //计算后将结果入数栈
      numStack.push(leftNum);

      // 该符号运算过了，就出栈
      operStack.pop();
      // 操作符入符号栈
      operStack.push((int)tmp);
    }else{
      // 直接入符号栈
      operStack.push((int)tmp);
    }
  }
}



class MyStack{
  // 指向最后一个元素的下一个元素
  private int rear = 0; 

  private int maxSize = 0;
  private int[] stack = null;
  public MyStack(int maxSize){
    this.maxSize = maxSize;
    stack = new int[maxSize];
  }

  public boolean isFull(){
    return rear == maxSize;
  }

  public boolean isEmpty(){
    return rear == 0;
  }

  public int peek(){
    return stack[rear-1];
  }

  public void push(int data){
    if(isFull()){
      throw new RuntimeException("满了，添加失败~~~");
    }
    stack[rear++] = data;
  }

  public int pop(){
    if(isEmpty()){
      throw new RuntimeException("空了，获取失败");
    }
    return stack[--rear];
  }

  public int getOperLevl(char oper){
    return (oper == '+' || oper == '-') ? 0 : 1;
  }

  public int doOperate(int leftNum, int rightNum, char op){
    // leftNum = Integer.parseInt(leftNum);
    // rightNum = Integer.parseInt(rightNum);
    if(op == '*'){
      return leftNum * rightNum;
    }else if(op == '/'){
      return leftNum / rightNum;
    }else if(op == '+'){
      return leftNum + rightNum;
    }else{
      return leftNum - rightNum;
    }
  }
}