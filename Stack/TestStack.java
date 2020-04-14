public class TestStack{
  public static void main(String[] args) {
  	MyStack myStack = new MyStack(5);
  	myStack.push(1);
  	myStack.push(2);
  	myStack.push(3);
  	myStack.push(4);
  	myStack.push(5);

  	myStack.pop();
  	myStack.pop();
  	myStack.pop();
  	myStack.pop();
  	myStack.pop();
  	myStack.pop();
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