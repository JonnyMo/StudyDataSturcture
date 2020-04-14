import java.util.Stack;

public class TestSingleLinkedList4{
	public static void main(String[] args) {
		// 先创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(3, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(8, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(9, "林冲", "豹子头");
		HeroNode hero5 = new HeroNode(11, "李逵", "黑旋风");

		SingleLinkedList list = new SingleLinkedList();
		list.add(hero1);
		list.add(hero2);
		list.add(hero3);
		list.add(hero4);
		list.add(hero5);

		list.list();

		HeroNode hero6 = new HeroNode(2, "宋江", "及时雨");
		HeroNode hero7 = new HeroNode(4, "卢俊义", "玉麒麟");
		HeroNode hero8 = new HeroNode(7, "吴用", "智多星");
		HeroNode hero9 = new HeroNode(10, "林冲", "豹子头");
		HeroNode hero10 = new HeroNode(14, "李逵", "黑旋风");

		SingleLinkedList otherList = new SingleLinkedList();
		otherList.add(hero6);
		otherList.add(hero7);
		otherList.add(hero8);
		hero3 = new HeroNode(8, "吴用", "智多星");
		otherList.add(hero3);
		otherList.add(hero9);
		otherList.add(hero10);
		System.out.println("xxxxx");
		otherList.list();

		// list.addByOrder(hero1);
		// list.addByOrder(hero4);
		// list.addByOrder(hero2);
		// list.addByOrder(hero3);
		// list.addByOrder(hero5);


		
		// System.out.println("修改后的值：");
		// HeroNode newNode = new HeroNode(2, "小卢", "玉麒麟ii");
		// list.update(newNode);
		// list.list();
		// list.delete(1);
		// list.delete(4);
		// list.delete(2);
		// list.delete(3);
		// System.out.println("修改后的值：");
		// list.list();
		// HeroNode result = list.getByReverseOrder(2);
		// System.out.println("2: " +result);
		// list.list();
		// System.out.println("........");
		// list.delete(3);
		// list.list();
		// System.out.println("........");
		//result = list.getByReverseOrder(3);
		//System.out.print("3: " +result);
		// list.list();


		// 新增面试题： 反转队列
		//list.reverseList();
		//list.list();
		//System.out.println(".........");
		
		// 新增面试题： 逆序打印队列
		// 思路1： 先反转队列，再打印
		// 思路2：可以用栈这种数据结构来操作
		//list.reversePrint(list.getHead());	
		
		//新增面试题： 合并链表
		// 第一次实现
		// list.combineOther(otherList);
		
		// 第一遍复习重新编写
		list.combineOtherList(otherList);
		list.list();
	}
}


class SingleLinkedList{
	// 先初始化一个头节, 头结点不要动, 不存放具体的数据
	private HeroNode head = null;

	public SingleLinkedList(){
		head = new HeroNode(0, "", "");
	}
	// 添加节点到单项链表, 直接添加到链表的最后
	// 1. 找到链表的最后一个节点
	// 2. 将最后这个节点next 指向新的节点
	public void add(HeroNode heroNode){
		// 因为head 节点不能动，因此我们需要一个辅助变量 tmp
		HeroNode tmp = head;
		while(true){
			if(tmp.next == null){
				//tmp 就是最后一个节点
				break;
			}
			tmp = tmp.next;
		}
		//退出while 循环，tmp就是链表的最后一个元素
		tmp.next = heroNode;
	}

	/*
		19/04/03 第一遍复习： 重新思考后完成
	*/
	public void combineOtherList(SingleLinkedList otherList){
		HeroNode newHead = new HeroNode(-1, "", "");
		HeroNode newListLastNode = newHead; 
		HeroNode otherHead = otherList.head;

		HeroNode target = null;
		while(head.next != null || otherHead.next != null){

			if(head.next == null || (head.next.no > otherHead.next.no)){
				// 摘掉target
				target = otherHead.next;
				otherHead.next = target.next;
			}else if(otherHead.next == null || (head.next.no < otherHead.next.no)){
				// 摘掉target
				target = head.next;
				head.next = target.next;
			}else if(head.next.no == otherHead.next.no){
				System.out.println("有序号相同的数据存在, no: " + head.next.no + "--" + otherHead.next.no);
				// 删除某个链表中的节点
				otherHead.next = otherHead.next.next;

				target = head.next;
				head.next = target.next;
			}
			newListLastNode.next = target;
			newListLastNode = target;
		}
		head.next = newHead.next;
	}

	
	// 逆序打印面试题
	public void reversePrint(HeroNode head){
		Stack<HeroNode> stack = new Stack<HeroNode>();
		if(head.next == null){
			return;
		}
		HeroNode node = head.next;
		while(node != null){
			stack.push(node);
			node = node.next;	
		}
		HeroNode tmp = null;
		while(stack.size() > 0){
			System.out.println(stack.pop());
		}
	}

	/*
	按照编号顺序添加
	1.首先找到新添加节点的位置， 通过辅助变量， 通过遍历来
	2.新的节点的next域 = tmp.next
	3.tmp.next = 新节点
	 */
	public void addByOrder(HeroNode heroNode){
		HeroNode tmp = head;
		boolean flag = false; // 标志添加的编号是否存在

		while(true){
			if(tmp.next == null){// 说明tmp 已经在链表的最后了
				break;
			}
			if(tmp.next.no > heroNode.no) {// 位置找到了
				break;
			}else if (tmp.next.no == heroNode.no) {// 说明编号已经存在了
				flag = true;
				break;
			}
			tmp = tmp.next;
		}

		if(flag){
			System.out.println("该编号的英雄已经存在了： " + heroNode.no);
		}else{
			heroNode.next = tmp.next;
			tmp.next = heroNode;
		}
	}

	//新增修改节点信息, 根据编号来修改即no 编号不能改
	public void update(HeroNode newHeroNode){
		HeroNode tmp = head;
		boolean flag = false; // 标识是否能找到
		if(tmp.next == null){
			return;
		}
		while(true){
			if(tmp == null){
				break;
			}

			if(tmp.next.no == newHeroNode.no){
				flag = true;
				break;	
			}else{
				tmp = tmp.next;
			}
		}	

		if(flag){ // 根据flag 来判断是否找到要修改的节点
			tmp.next.name = newHeroNode.name;
			tmp.next.nickname = newHeroNode.nickname;
		}else{
			System.out.printf("没有找到编号为: %d 的节点", newHeroNode.no);
		}
	}

	public void delete(int no){
		HeroNode tmp = head;
		boolean flag = false; // 标识是否能找到
		if(tmp.next == null){
			return;
		}
		while(true){
			if(tmp == null){
				break;
			}

			if(tmp.next.no == no){
				flag = true;
				break;	
			}else{
				tmp = tmp.next;
			}
		}

		if(flag) {// tmp.next 就是找到的节点
			tmp.next = tmp.next.next;
		} else {
			System.out.printf("没有找到编号为: %d 的节点", no);
		}
	}

	/*
	自己实现的反转方法
	 */
	public void reverseList2(){
		HeroNode head = getHead();
		HeroNode head2 = new HeroNode(-1, "", "");

		HeroNode curNode = null;
		HeroNode afterHead2 = null;

		while(head.next != null){
			curNode = head.next;
			afterHead2 = head2.next;

			head2.next = curNode;
			head.next = curNode.next;
			curNode.next = afterHead2;
		}

		head.next = head2.next;
	}

	/*
	
	public void reverseList(){
		int length = length();
		// 反转队列的头节点
		HeroNode reversedHead = new HeroNode(0, "", "");
		HeroNode latestAdd = null;
		HeroNode tmp = null;
			
		HeroNode sourceHead = getHead();
		HeroNode sourceNode = sourceHead.next;
		if( sourceNode == null){
			return;
		}

		for(int i = 0; i < length; i++) {
			// 1.原始队列的头指向 当前操作节点的下一个节点
			// 2.存储反转节点的下一个节点 为 tmp
			// 3.反转队列的头结点的下一个节点赋值为当前操作节点
			// 4.反转队列头节点的下一个节点 赋值为tmp
			latestAdd = sourceHead.next;
			tmp = reversedHead.next;
			sourceHead.next = latestAdd.next;
			reversedHead.next  = latestAdd;
			latestAdd.next = tmp;
		}

		sourceHead.next = reversedHead.next;
	}
	*/


	// 新增笔试题：获取链表的倒数第n个元素
	public HeroNode getByReverseOrder(int n){
		int size = length();
		if(n < 0  || n > size){
			System.out.println("数据无效，总长度为： " + size + " 倒数第" + n + "个元素不存在");
			return null;
		}

		HeroNode head = getHead();
		int rearIndex = size - n;
		HeroNode tmp = head.next;
		for(int i = 0; i < rearIndex; i++){
			tmp = tmp.next;
		}	 
		return tmp;
	}

	public int length(){
		HeroNode head = getHead();
		HeroNode tmp = head.next;
		if(tmp == null){
			return 0;
		}
		int count = 0; 
		while(tmp != null){
			count++;
			tmp = tmp.next;
		}
		return count;
	}

	// 显示链表
	public void list(){
		HeroNode head = getHead();
		// 判断链表是否为空
		if(head.next == null){
			System.out.println("链表为空");
			return;
		}

		HeroNode tmp = head.next;
		while(true){
			if(tmp == null){
				break;
			}
			System.out.println(tmp.toString());
			tmp = tmp.next;
		}
	}

	public HeroNode getHead(){
		return head;
	}
}

// 定义一个节点的类，每个HeroNode对象就是 链表中的一个节点
class HeroNode{
	int no;
	String name;
	String nickname;
	// 当next为null 表示是链表的最后一个元素
	HeroNode next;

	public HeroNode(int hNo, String hName, String hNickName){
		this.no = hNo;
		this.name = hName;
		this.nickname = hNickName;
	}

	@Override
	public String toString(){
		return "HeroNode [no=" + no +", name=" + name + ", nickname=" + nickname + "]";
	}
}



