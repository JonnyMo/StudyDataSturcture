public class TestSingleLinkedList1{
	public static void main(String[] args) {
		// 先创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

		SingleLinkedList list = new SingleLinkedList();
		list.add(hero1);
		list.add(hero2);
		list.add(hero3);
		list.add(hero4);
		list.list();
	}
}


/*
1、链表是以节点的方式通过链式存储的方式存储的
2、每个节点包含： data域、 next域(指向下一个节点)
3、发现链表的各个节点不一定是连续存放的
4、链表分： 带头结点的链表 和 不带头节点的链表
 
头节点的特点：
	不存放具体的数据
	作用就是表示单链表的头
 
1、创建一个head头节点
2、后面我们每添加一个节点，就直接加入到链表的最后
	遍历：
		a. 通过一个辅助变量，帮助遍历整个链表
 */
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

	public String toString(){
		return "HeroNode [no=" + no +", name=" + name + ", nickname=" + nickname + "]";
	}
}










SingleLinkedList{
	private HeroNode head;
	public SingleLinkedList (){
		this.head = new HeroNode(-1, "", "");
	}

	
}








