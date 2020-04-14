import java.util.Scanner;
public class TestCircleQueue{
	public static void main(String[] args) {
		// 创建一个队列
		// 队列中的有效数据长度是 n-1, 因为有个空位来做约定
		CircleQueue arrayQueue = new CircleQueue(5);
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

