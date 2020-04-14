/*约瑟夫(环)问题：
 * 设编号为1,2,...n 的n个人围坐一圈，约定编号为k(1 <= k <= n) 的人 从1开始报数，数到m的那个人出列，
 * 它的下一位又开始从1 报数，数到m的那个人又出列，依此类推，由此产生一个出队编号的序列
 *
 * n=5 即有5个人
 * k=1 从第一个人开始报数
 * m=2 数2下
 */
import java.util.*;
public class TestSingleCircleLinkedList{
	public static void main(String[] args) {
		SingleCircleLinkedList circleList = new SingleCircleLinkedList();
		// circleList.add(5);
		// circleList.list();		
		//circleList.addChildren(7);
		//int[] result = circleList.circleRound2(7, 2, 4);
		
		circleList.addChildren2(7);
		circleList.list();

		int[] result = circleList.startCount(7, 2, 3);
		for(int element: result){
		    System.out.println(element);
		}
	}
}

/*
构建一个单向的环形链表
	1.先创建一个节点，让first指向该节点，并形成环形
	2.后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中
	3.将新加入的节点的next 指向 first

遍历环形链表
	1.先让一个辅助变量curChild 指向first
	2.然后通过while 循环，遍历该队列，直到 curChild.next == first 停止循环
 */
class SingleCircleLinkedList{
	// 创建一个first节点，当前没有编号
	Child first = new Child(-1);
	
	/*
	 19/04/04 复习完成
	 思路： 
	 1. 定义变量curNode 记录当前要出圈的节点， 默认值 first
	 2. 定义变量beforeCurNode 记录当前出圈的节点的前一个节点， 默认值 通过while循环找到 first 的前一个节点
     3. 定义变量result 用于保存出圈的数据

	 4. 根据startIndex，让 curNode 和 beforeCurNode 一直往下找， 注意这里的次数是 startIndex -1 次
	 5. 循环开始出圈 
	    a. 循环条件 curNode == beforeCurNode ,即队列中只剩余一个节点
	       b. 开始for循环  次数为 countNum-1 次， curNode 和 beforeCurNode 一样往下找
	       c. 将curNode.no 加入 result中
	       d. curNode 出圈
	       e. curNode = curNode.next
	 6. 循环结束，将剩余一个 节点的数据加入result中
	 7. return result
	*/
	public int[] startCount(int length, int startIndex, int countNum){
		Child head = first;
		Child curChild = head;
		Child beforeCurNode = head;

		// 用于存储出圈的数据
		int[] nums = new int[length];
		// 记录当前出圈的数据 在 nums中的位置
		int counts = 0;	

		// 让 beforeCurNode 在head 的前面
		while(beforeCurNode.getNext() != head){
			beforeCurNode = beforeCurNode.getNext();
		}

		for(int i = 0; i < startIndex - 1; i++){
			curChild = curChild.getNext();
			beforeCurNode = beforeCurNode.getNext();
		}

		while(curChild != beforeCurNode){
			for(int i = 0; i < countNum-1; i++){
				curChild = curChild.getNext();
				beforeCurNode = beforeCurNode.getNext();
			}

			nums[counts++] = curChild.getNo();
			// 出圈
			beforeCurNode.setNext(curChild.getNext());
			curChild = curChild.getNext();
		}

		// 还剩一个节点，直接加入即可
		nums[counts] = curChild.getNo();
		return nums;
	}

	// 添加num个小孩,构建成一个环形链表
	public void addChildren2(int num){
		Child lastNode = null;
		int startNo = 1;
		for(int i = 0; i < num; i++){
			Child newNode = new Child(startNo + i);
			// 需要特殊处理，替换掉第一个节点，不然会出现 num + 1 （1是链表中默认的头节点）个节点
			if(i == 0){
				first = newNode;
				lastNode = first;
				first.setNext(lastNode);
				continue;
			}
			lastNode.setNext(newNode);
			newNode.setNext(first);
			lastNode = newNode;
		}
	}



	/**
	 * 自己的思路
	 * 在环形链表中数数
	 * @param  length     [环形链表的长度]
	 * @param  startIndex [从什么位置开始数，最小值为1]
	 * @param  count      [数几下]
	 * @return            [description]
	 */
	public int[] circleRound(int length, int startIndex, int count){
		int[] result = new int[length];

		// 从curChild 开始找next
		Child curChild = null;
		int findCount = 0;
		int i = 1;

		for(; i < startIndex; i ++){
			if(curChild == null){
				curChild = this.first;
			}else{
				curChild = curChild.getNext();
			}
		}

		// 单向链表 要删除节点，必须要找到要删除节点的前一个节点
		Child prev = null;
		while(findCount != length){
			
			for(i = 1; i <= count; i++){
				
				if(curChild == null){
					curChild = this.first;
				}else{
					curChild = curChild.getNext();
				}

				if(i == count -1){
					prev = curChild;
				}
			}

			// 从链表中删除first
			prev.setNext(curChild.getNext());
			result[findCount++] = curChild.getNo();
			System.out.print("no: " + curChild.getNo());
		}

		System.out.println(result);
		return result;
	}


	/**
	 * 设置一个变量helper 值为first 的前一个元素
	 * 让helper 一直跟着 first 移动， 找到目标节点后，通过helper 和 first 将first删掉，再次循环找下一个
	 * @param  length     [description]
	 * @param  startIndex [description]
	 * @param  count      [description]
	 * @return            [description]
	 */
	public int[] circleRound2(int length, int startIndex, int count){
		// helper 为 first的前一个节点
		int[] result = new int[length];
		Child helper = first;
		int removedCount = 0;
		while(true){
			if(helper.getNext() == first){
				break;
			}
			helper = helper.getNext();
		}

		for(int i = 1; i < startIndex; i++){
			helper = helper.getNext();
			first = first.getNext();
		}

		while(true){
			if(helper == first){
				result[removedCount++] = first.getNo();
				break;
			}

			for(int i = 1; i < count; i++){
				helper = helper.getNext();
				first = first.getNext();
			}
			
			result[removedCount++] = first.getNo();
			helper.setNext(first.getNext());
			// first要指向 之前的first的下一个
			first = first.getNext();
		}
		return result;
	}


	// 添加num个小孩,构建成一个环形链表
	public void addChildren(int num){
		Child curChild = null;

		for(int i = 1; i <= num; i++){
			Child newChild = new Child(i);
			if(i == 1){
				first = newChild;
				first.setNext(newChild);
			}else{
				curChild.setNext(newChild);
				newChild.setNext(first);
			}	
			curChild = newChild;
		}
	}

	public void list(){
		Child curChild = first;	
		if(first.getNext() == first){
			System.out.println("列表数据为空");
			return;
		}

		while(true){
			System.out.println(curChild);
			if(curChild.getNext() == first){
				break;
			}
			curChild = curChild.getNext();
		}
	}

	


}

class Child{
	private int no;
	private Child next;

	public Child(int no){
		this.no = no;
	}

	public String toString(){
		return "Child [ no=" + no + "]";
	}

	public void setNext(Child next){
		this.next = next;
	}

	public int getNo(){
		return no;
	}
	public Child getNext(){
		return this.next;
	}
}

	
	