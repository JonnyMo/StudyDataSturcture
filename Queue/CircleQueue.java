class CircleQueue{
	private int maxSize; // 数组的最大容量
	private int front; // 头指针: 指向队列中的第一个元素的位置
	private int rear; // 尾指针: 指向队列中最后一个元素的后一个位置
	private int[] arr; //存放数据的数组

	public CircleQueue(int size){
		maxSize = size;
		arr = new int[maxSize];
		front = 0;// 指向队列头部
		rear = 0; // 指向队列尾部, 指向队列中最后一个元素的后一个位置
	}

	// 判断队列是否满
	public boolean isFull(){
		return (rear + 1) % maxSize == front;
	}

	// 判断队列是否是空的
	public boolean isEmpty(){
		return front == rear;
	}

	/*
		将尾指针往后移： rear + 1 
		rear 需要重新赋值
	 */
	public boolean addQueue(int n){
		if(isFull()){
			System.out.println("满了");
			return false;
		}
		// 将数据直接加入, 因为 rear 本身指向的就是最后一个元素的后一个位置
		arr[rear] = n;

		// 加完后，需要将rear向后移动一个位置，注意必须取模，rear不能一直不停的变大
		rear = (rear + 1) % maxSize;
		return true;
	}

	// 获取队列的数据，从front 头部开始取数据， 出队列
	public int getQueue(){
		// 判断队列是否为空
		if(isEmpty()){
			// 通过抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		// 先将front对应的值存起来
		int val = arr[front];
		// 将front后移一个位置
		front = (front + 1) % maxSize;
		// 返回原先front 位置对应的值
		return val;
	}


	// 显示队列的所有数据
	public void showQueue(){
		if(isEmpty()){
			System.out.println("队列为空");
			return;
		}

		// 思路： 从front 开始往后找，直到找到rear
		int fake = front > rear ? rear + maxSize : rear;
		System.out.println("length: " + length());
		for(int i = front; i < front + length(); i++){
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	// 显示的队列的头数据（注意：不是取出数据）
	public int headQueue(){
		// 判断
		if(isEmpty()){
			throw new RuntimeException("队列空，不能取数据");
		}
		// 注意这里的front 指向的是队列头部的前一个位置，所以是 front+1
		return arr[front];
	}

	// rear 可能会小于 front
	public int length(){
		return (rear - front + maxSize) % maxSize; 
	}
}