import com.jun.arithmetic.*;
import com.jun.common.innerclass.Outer;
import com.jun.interview.Q3_Min2Square;
import com.jun.patterm.singleton.Mgr08;
import com.jun.patterm.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;


/**
 * @className: TestLab
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/3/24 08:35
 **/
@Slf4j
public class TestLab {

    int[] arr= {-1, 0,1,8,-5,2,5,5,5,29,20, 13, 13, 9,12};;

    @Test
    public void dichotomy() {
        int[] arr = {1,3, 4, 5,8, 10, 12, 16, 18, 19, 20, 24, 25, 25};
        int num = 17;
        StopWatch stopWatch = new StopWatch("检测时间01");
        stopWatch.start();
        Dichotomy.genericFind(arr, num);
        stopWatch.stop();
        log.info("genericFind时间：{}",stopWatch.getNanoTime());
        stopWatch.reset();
        stopWatch.start();
        Dichotomy.find(arr, num);
        stopWatch.stop();
        log.info("find时间：{}",stopWatch.getNanoTime());

    }

    @Test
    public void testMostLeftNoLessNumIndex() {
        int[] arr = {1,3, 4, 5,8, 10, 12, 16, 18, 19, 20, 24, 25, 25};
        int num = 17;
        Dichotomy.mostLeftNoLessNumIndex(arr, num);
    }

    @Test
    public void testSingleton() {
        for (int i =0 ; i < 100; i++) {
            new Thread(() -> log.info("实例：{}", Mgr08.INSTANCE.hashCode())).start();
        }

    }

    @Test
    public void testStrategy() {
        ZhaZha zhaZha = new ZhaZha(new CannotDoNotThing(), new SpeakChinese());
        zhaZha.iCanDo();
        zhaZha.iCanSpeak();
        Talent Talent = new Talent(new CanDoAllThing(), new SpeakEnglish());
        Talent.iCanDo();
        Talent.iCanSpeak();
    }

    @Test
    public void testOneMinIndex() {
        int maxLen = 30;
        int maxValue = 100;
        int testTime = 1000000;
        log.info("开始测试");
        int mark = 10;
        for(int i = 0; i < testTime; i++ ) {
            int[] arr = Dichotomy.randomArray(maxLen, maxValue);
            int ans = Dichotomy.oneMinIndex(arr);
            if (!Dichotomy.check(arr, ans)) {
                log.info("数组：{}", arr);
                log.info("出问题的地方：{}", ans);
                break;
            }
            if (i == mark) {
                log.info("数组：{}", arr);
                log.info("局部最小位置：{}", ans);
            }
        }
        log.info("开始结束");

    }

    @Test
    public void testXor() {
        int[] arr= {1,3,4,5,6};

        int a = 3;
        int b = 3;
        log.info("a: {}, b: {}", arr[a] , arr[b]);
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
        log.info("a: {}, b: {}", arr[a] , arr[b]);
    }

    @Test
    public void testMin2Square() {
        int n = -19;
        PrintBinary.printBinary(n);
        int r = Q3_Min2Square.tableSizeFor(n);
        PrintBinary.printBinary(r);
    }


    @Test
    public void testSelectSort() {
        int[] arr= {-1, 0,1,8,-5,2,5,5,5,29,20, 13, 13, 9,12};
        SelectSort.sort(arr);
        log.info("arr: {}", arr);
    }

    @Test
    public void testBubblingSort() {
        int[] arr= {-1, 0,1,8,-5,2,5,5,5,29,20, 13, 13, 9,12};
        int[] arr2= {5,5,5,29,20, 13, 13, 9,12};
        BubblingSort.sort(arr);
        log.info("arr: {}", arr);
    }

    @Test
    public void testInsertionSort() {
        CommonSort.insertionSort(arr);
        log.info("arr: {}", arr);
    }

    @Test
    public void testFindOddInArr() {
        int[] arr= {5,5,5, 5, 8, 8, 8, 8, 8};
        log.info("res: {}", Xor.findOddInArr(arr));
    }

    @Test
    public void testFindLastRightOne() {
        int n = -28;
        PrintBinary.printBinary(n);
        PrintBinary.printBinary(Xor.findLastRightOne(n));
    }

    @Test
    public void testFindTwoOdd() {
        int[] arr= {5,5,5, 22,22, 5, 8,22, 8, 8,6,6, 3,3,3, 8,8, 8};
        int[] res = Xor.findTwoOdd(arr);
        log.info("res: {}", res);
    }

    @Test
    public void testFindKM() {
        int[] arr= {-1,-1,8, 8,8, 8};
        log.info("the K: {}", Xor.findKM(arr, 2, 4));
    }

    @Test
    public void testReverseLinkedList() {

        Linked.Node nodeA = new Linked.Node("a", null);
        Linked.Node nodeB = new Linked.Node("b", null);
        nodeA.setNext(nodeB);
        Linked.Node nodeC = new Linked.Node("c", null);
        nodeB.setNext(nodeC);
        Linked.Node nodeD = new Linked.Node("d", null);
        nodeC.setNext(nodeD);
        Linked.Node nodeE = new Linked.Node("e", null);
        nodeD.setNext(nodeE);

        Linked.printLinked(nodeA);

        Linked.Node head = Linked.reverseLinkedList(nodeA);

        Linked.printLinked(head);
    }

    @Test
    public void testRemovedLinked() {
        Linked.Node nodeA = new Linked.Node("a", null);
        Linked.Node nodeA2 = new Linked.Node("a", null);
        nodeA.setNext(nodeA2);
        Linked.Node nodeB = new Linked.Node("b", null);
        nodeA2.setNext(nodeB);
        Linked.Node nodeC = new Linked.Node("c", null);
        nodeB.setNext(nodeC);
        Linked.Node nodeD = new Linked.Node("d", null);
        nodeC.setNext(nodeD);
        Linked.Node nodeE = new Linked.Node("e", null);
        nodeD.setNext(nodeE);

        Linked.printLinked(nodeA);

        Linked.Node head = Linked.removedNode(nodeA, "d");

        Linked.printLinked(head);

    }

    @Test
    public void testDoubleLinkedQueue() {
        Queue queue = new Queue();
        queue.addFromHead("a");
        queue.addFromHead("b");
        queue.addFromHead("c");
        queue.addFromHead("d");
        queue.addFromHead("e");
        queue.addFromHead("f");
        queue.addFromHead("g");

        Queue.printLinked(queue);

        queue.removeFromBottom();
        Queue.printLinked(queue);

        queue.removeFromTop();
        Queue.printLinked(queue);

        queue.addFromBottom("ooo");
        Queue.printLinked(queue);

        queue.removeFromTop();
        Queue.printLinked(queue);


    }

    @Test
    public void testDoubleLinkedStack() {
        Stack stack = new Stack(5);
        stack.push("1");
        stack.push("2");
        stack.push("5");
        stack.push("19");
//        stack.push("0");
//        stack.push("10");

        Stack.printStack(stack);

        stack.pop();
        stack.pop();
//        stack.pop();
//        stack.pop();
        stack.push("80");
        stack.push("60");
        stack.pop();
        Stack.printStack(stack);

    }

    @Test
    public void testStackArr() {
        Stack stack = new Stack(5);
        stack.pushArr("1");
        stack.pushArr("2");
        stack.pushArr("5");
        stack.pushArr("19");
        stack.pushArr("0");
        stack.push("10");

        Stack.printStackArr(stack);

        stack.popArr();
        stack.popArr();
        stack.popArr();
        stack.popArr();
//        stack.pushArr("80");
//        stack.pushArr("60");
        stack.popArr();
        Stack.printStackArr(stack);

    }

    @Test
    public void testQueueArr() throws Exception {
        Queue queue = new Queue(5);
        queue.add("1");
        queue.add("2");
        queue.add("5");
        queue.add("19");
        queue.add("20");


        queue.remove();
        queue.remove();
        queue.remove();
        queue.add("22");
        queue.add("5");
        queue.remove();
        queue.remove();
        queue.remove();
    }

    @Test
    public void testMinStack() throws Exception {
        GetMinStack minStack = new GetMinStack();
        minStack.push(10);
        minStack.push(4);
        minStack.push(5);
        minStack.push(2);
        minStack.push(20);

        log.info("min: {}", minStack.getMin());

        minStack.pop();
        log.info("min: {}", minStack.getMin());
        minStack.pop();
        log.info("min: {}", minStack.getMin());
        minStack.pop();
        log.info("min: {}", minStack.getMin());
        minStack.pop();
        log.info("min: {}", minStack.getMin());

    }

    @Test
    public void testStackQueue() {
        QueueImpByTwoStack queue = new QueueImpByTwoStack(5);
        queue.add(10);
        queue.add(11);
        queue.add(20);
        queue.remove();
        queue.remove();
        queue.add(8);
        queue.add(9);
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
    }

    @Test
    public void testStackImpByTwoQueue() {
        StackImpByTwoQueue stack = new StackImpByTwoQueue();
        stack.push("8");
        stack.push("40");
        stack.push("30");
        stack.push("24");
        stack.push("29");

        stack.pop();
        stack.pop();
        stack.pop();
        stack.push("23");
        stack.push("16");
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }

    @Test
    public void testGetMax() {
        log.info("arr: {}", arr);
        log.info("max: {}", GetMax.getMax(arr));
    }


    @Test
    public void testMergeSort() {
        log.info("目标数组：{}", arr);
//        MergeSort.sort1Rec(arr);
        MergeSort.sort2NoRec(arr);
        log.info("归并排除结果：{}", arr);
    }

    @Test
    public void testSmallSum() {
        log.info("目标数组：{}", arr);
        log.info("SmallSum结果：{}", SmallSum.sum(arr));
//        log.info("SmallSum结果：{}", SmallSum.comparator(arr));
    }

    @Test
    public void testArr() {
        int[] help = new int[10];
        help[1] = 10;
        help[2] = 10;
        help[3] = 10;
        help[0] = 10;

        log.info("数组大小：{}", help.length);
    }

    @Test
    public void testReversePair() {
        log.info("目标数组：{}", arr);
        log.info("ReversePair结果：{}", ReversePair.count(arr));
    }

    @Test
    public void testBiggerThanRightTwice() {
        log.info("目标数组：{}", arr);
//        log.info("ReversePair结果：{}", BiggerThanRightTwice.count(arr));
        log.info("BiggerThanRightTwice结果：{}", BiggerThanRightTwice.comparator(arr));
    }

    @Test
    public void testCountOfRangeSum() {
        log.info("目标数组：{}", arr);
        log.info("在范围内的子数组数量：{}", CountOfRangeSum.count(arr, 5, 15));
//        log.info("在范围内的子数组数量：{}", CountOfRangeSum.Comparator.countRangeSum(arr, 5, 15));
    }

    @Test
    public void testInnerClass() {
        Outer outer = new Outer();
        outer.accessInP();
    }

    // 第7种单例模式 静态内部类



}
