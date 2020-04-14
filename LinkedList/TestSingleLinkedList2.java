public class TestSingleLinkedList2{
	public static void main(String[] args) {
		// 先创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

		SingleLinkedList list = new SingleLinkedList();
		// list.add(hero1);
		// list.add(hero2);
		// list.add(hero3);
		// list.add(hero4);
		
		list.addByOrder(hero1);
		list.addByOrder(hero4);
		list.addByOrder(hero2);
		list.addByOrder(hero3);
		list.addByOrder(hero3);
		list.list();
	}
}



class SingleLinkedList{
	// 先初始化一个头节, 头结点不要动, 不存放具体的数据
	private HeroNode head = new HeroNode(0, "", "");

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

	// 显示链表
	public void list(){
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