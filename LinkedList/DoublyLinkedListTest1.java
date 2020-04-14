public class DoublyLinkedListTest1{
	public static void main(String[] args) {
		// 先创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(3, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(8, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(9, "林冲", "豹子头");
		HeroNode hero5 = new HeroNode(11, "李逵", "黑旋风");

		DoublyLinkedList list = new DoublyLinkedList();
		list.addByOrder2(hero4);
		list.list();
		
		System.out.println(".......");

		list.addByOrder2(hero2);
		list.addByOrder2(hero5);
		list.addByOrder2(hero1);
		list.list();
		list.addByOrder2(hero3);
		list.list();

		// System.out.println(".......");
		// list.update(new HeroNode(9, "kkk", "mm"));
		// list.list();
		// System.out.println(".......");

		list.del2(8);
		list.list();

	}
}


/**
 * 双向链表
 * 分析 双向链表的 遍历、 添加、修改、删除
 *
 * 遍历的方式 和 单链表一样，只是可以向前 也可以向后 查找
 * 添加: (默认添加到双向链表的最后)
 *    a. 先找到双向链表的最后
 *    b. tmp.next = newNode
 *    c. newNode.prev = tmp;
 * 修改的思路和原来的单向链表的思路一样
 * 删除：
 * 		a.因为是双向链表，因此可以实现自我删除某个节点tmp
 * 		b.tmp.prev.next = tmp.next;
 * 		c.tmp.next.prev = tmp.prev
 */
class DoublyLinkedList{
	private HeroNode head = null;

	public DoublyLinkedList(){
		head = new HeroNode(0, "", "");
	}

	/*
	第一次复习 重新写的 19/04/03 
	 */
	public boolean del2(int no){
		HeroNode curNode = getByNo2(no);
		if(curNode == null){
			System.out.println("查找失败...");
			return false;
		}

		curNode.prev.next = curNode.next;
		if(curNode.next != null){
			curNode.next.prev = curNode.prev;
		}
		return true;
	}

	/*
	第一次复习 重新写的 19/04/03 
	 */
	public HeroNode findNext(int no){
		HeroNode curNode = head;
		while(curNode.next != null){
			if(curNode.no > no){
				break;
			}
			curNode = curNode.next;
		}
		return curNode;
	}

	/*
	第一次复习 重新写的 19/04/03
	 */
	public boolean addByOrder2(HeroNode newNode){
		HeroNode latest = findNext(newNode.no);
		if(latest.no > newNode.no){
			newNode.prev = latest.prev;
			newNode.next = latest;
			latest.prev.next = newNode;
			latest.prev = newNode;	
		}else if(latest.no == newNode.no){
			System.out.println("添加失败, 该序号元素已存在: no: " + newNode.no);
			return false;
		}else{
			latest.next = newNode;
			newNode.prev = latest;
		}
		return true;
	}

	/*
	第一次复习 重新写的 19/04/03
	 */
	public HeroNode getByNo2(int no){
		HeroNode curNode = head;
		while(curNode.next != null){
			if(curNode.no == no){
				break;
			}
			curNode = curNode.next;
		}
		return curNode.no == no ? curNode : null;
	}
	
	/*
	按顺序加入:
		1.找到链表中 大于 newNOde.no 的或是 链表中的最后一个(如果链表中不存在 > newNode.no 的节点)
		2.判断是否找到的节点的no ，和 newNode.no 值相同
		3.处理 大于newNode.no 的情况
			a.nextNode.prev.next = newNode; 将nextNode的前一个节点的next 设置为 newNode
			b.newNode.prev = nextNode.prev; 将newNode 的前一个节点的prev 设置为 newNode的前一个节点
			c.newNode.next = nextNode;  将newNode.next 设置为nextNode
			d.nextNode.prev = newNode;  将nextNode的前一个节点设置为 newNode
		4.判断nextNode 是 头节点或是最后一个节点的情况（其实头结点和最后一个节点的处理是一样的，头节点也是最后一个节点）
		   如果满足条件，将newNode 加到nextNode后面即可 nextNode.next = newNode; newNode.prev = nextNode;
	 */
	public boolean addByOrder(HeroNode newNode){
		// 查找第一个比 newNode 的no值 大的节点 或是 最后一个节点（如果不存在比NewNode.no值大的节点)
		HeroNode nextNode = findNextOrLast(newNode.no);
		// 节点已经存在了
		if(nextNode.no == newNode.no){
			System.out.println("相同编号的节点 已经存在了， 无法加入");
			return false;
		}

		if(nextNode.no > newNode.no){// 先处理 > newNode.no 的情况，因为
			nextNode.prev.next = newNode;
			newNode.prev = nextNode.prev;
			newNode.next = nextNode;
			nextNode.prev = newNode;

		}else{
			boolean isAppend = false;

			// 只有一个头结点的情况: 把newNode 加到末尾
			if(nextNode.prev == null){
				isAppend = true;
			}

			// 最后一个节点了，但不是> newNode.no 的: 把newNode加到末尾
			if(nextNode.next == null){
				isAppend = true;
			}

			if(isAppend){
				nextNode.next = newNode;
				newNode.prev = nextNode;
			}
		}

		return true;
	}

	public HeroNode findNextOrLast(int no){
		HeroNode curNode = getHead();
		while(true) {
			if(curNode.next == null){
				break;
			}else{
				if(curNode.no >= no){
					// 找到了
					break;
				}
			}
			curNode = curNode.next;
		}
		return curNode;
	}


	/**
	 * [根据no 获取队列中符合条件的第一个HeroNode元素]
	 * @param  no        [description]
	 * @param  condition [1: > no, 0: =, 2: < no]
	 * @return HeroNode [description]
	 */
	public HeroNode getByNo(int no){
		HeroNode curNode = getHead();
		if(curNode.next == null) {
			System.out.println("链表为空");
			return null;			
		}

		while(true) {
			curNode = curNode.next;
			if(curNode == null){
				break;
			}else{
				if(curNode.no == no){
					break;
				}	
			}
		}

		return curNode;
	}

	public boolean del(int no){
		HeroNode target = getByNo(no);

		if(target == null){
			System.out.println("没找到数据");
			return false;
		}
		// 当前节点的前一个节点的next 为 当前节点的 next
		target.prev.next = target.next;
		// 当前节点的后一个节点的prev 为 当前节点的prev
		if(target.next != null){// 如果target 是最后一个元素，target.next 是null
			target.next.prev = target.prev;
		}
		return true;
	}

	public boolean update(HeroNode modifiedNode){
		HeroNode target = getByNo(modifiedNode.no);
		if(target != null){
			target.name = modifiedNode.name;
			target.nickname = modifiedNode.nickname;
			return true;
		}
		return false;
	}


	public void list(){
		HeroNode curNode = getHead();
		if(curNode.next == null) {
			System.out.println("链表为空");
			return;			
		}

		while(true) {
			curNode = curNode.next;
			if(curNode == null){
				break;
			}
			System.out.println(curNode);
		}
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
		heroNode.prev = tmp;
	}

	public HeroNode getHead(){
		return head;
	}
}

class HeroNode{
	int no;
	String name;
	String nickname;
	// 当next为null 表示是链表的最后一个元素
	HeroNode next;
	HeroNode prev;

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

