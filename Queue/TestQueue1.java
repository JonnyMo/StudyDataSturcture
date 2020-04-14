package Queue;
import java.util.Scanner;
public class TestQueue1{
	
	public static void main(String[] args) {
		// 创建一个队列
		ArrayQueue arrayQueue = new ArrayQueue(3);
		char key = ' '; // 接收用户输入
		Scanner input = new Scanner(System.in);
		boolean loop = true;
		while(loop){
				System.out.println("s(show): 显示队列");
				System.out.println("e(exit): 退出程序");
				System.out.println("a(add): 添加数据");
				System.out.println("g(get): 取数据");
				System.out.println("h(head): 查看队列头的数据");
				key = input.next().charAt(0);

				switch(key) {
					case 's': 
						arrayQueue.showQueue();
						break;
					case 'e':
						loop = false;
						input.close();
						System.out.println("程序退出");
						break;
					case 'a':
						System.out.println("输入一个数字");
						int value = input.nextInt();
						arrayQueue.addQueue(value);
						break;
					case 'g':
						System.out.println("获取的数据是： " + arrayQueue.getQueue());
						break;
					case 'h':
						System.out.println("队列头的数据是: " + arrayQueue.headQueue());
						break;
				}
		}		
	}

}

class ArrayQueue{
	private int maxSize; // 数组的最大容量
	private int rear; // 尾指针 指向队列尾的数据（即就是队列的最后一个数据）
	private int front; // 头指针 分析出front指向队列头的前一个位置
	private int[] arr; //存放数据的数组

	public ArrayQueue(int size){
		maxSize = size;
		arr = new int[maxSize];
		front = -1;// 指向队列头部, 
		rear = -1; // 指向队列尾部, 
	}

	// 判断队列是否满
	public boolean isFull(){
		return rear == maxSize - 1;
	}

	// 判断队列是否是空的
	public boolean isEmpty(){
		return front == rear;
	}

	/*
	1、将尾指针往后移： rear + 1， 当front == rear 时，表示 result 是空的
	2、若尾指针 rear 小于队列的最大下标 maxSize - 1,则将数据存入rear 所指的数组元素中，否则无法存入数据
		rear == maxSize -1 表示队列满了
	 */
	public boolean addQueue(int n){
		if(isFull()){
			System.out.println("满了");
			return false;
		}
		rear = rear + 1;
		arr[rear] = n;
		return true;
	}

	// 获取队列的数据，从front 头部开始取数据， 出队列
	public int getQueue(){
		// 判断队列是否为空
		if(isEmpty()){
			// 通过抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		front++; // front 后移
		return arr[front];
	}


	// 显示队列的所有数据
	public void showQueue(){
		if(isEmpty()){
			System.out.println("队列为空");
		}
		for(int i = 0; i < arr.length; i++){
			System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}
	}

	// 显示的队列的头数据（注意：不是取出数据）
	public int headQueue(){
		// 判断
		if(isEmpty()){
			throw new RuntimeException("队列空，不能取数据");
		}
		// 注意这里的front 指向的是队列头部的前一个位置，所以是 front+1
		return arr[front + 1];
	}


}