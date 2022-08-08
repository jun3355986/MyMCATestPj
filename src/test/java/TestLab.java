import com.alibaba.fastjson.JSONObject;
import com.jun.arithmetic.*;
import com.jun.arithmetic.Queue;
import com.jun.arithmetic.Stack;
import com.jun.common.innerclass.Outer;
import com.jun.interview.Q3_Min2Square;
import com.jun.patterm.singleton.Mgr08;
import com.jun.patterm.strategy.*;
import com.jun.thread.FiveThreadCreate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


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
    @Test
    public void TestPartitionAndQuickSort() {
        log.info("目标数组：{}", arr);
//        log.info("排序后返回：{}", PartitionAndQuickSort.partition(arr, 0, arr.length - 1));
//        log.info("排序后返回：{}", PartitionAndQuickSort.netherlandsFlag(arr, 0, arr.length - 1));
//        PartitionAndQuickSort.quickSort1(arr);
//        PartitionAndQuickSort.quickSort2(arr);
//        PartitionAndQuickSort.quickSort3(arr);
        PartitionAndQuickSort.quickSort4(arr);
        log.info("排序后：{}", arr);
    }

    @Test
    public void testRandom() {
        log.info("随机数：{}", Math.random());
        log.info("随机数：{}", (int)Math.random() * 11);
        for (int i=0; i< 100; i++) {
            log.info("随机数：{}", (int) (Math.random() * 11));
        }
    }

    @Test
    public void testComparator() {

        Integer[] arr2 = {-1, 0,1,8,-5,2,5,5,5,29,20, 13, 13, 9,12};
        log.info("排序前：{}", (Object)arr2);
//        Arrays.sort(arr2, (a,b) -> a - b);
        Arrays.sort(arr2, (a,b) -> b - a);
        // 【注意】打印时，如果数组是对象类型，要使用(Object)A, 不然不打印数据所有内容，只会打印最后一个

        log.info("排序后：{}", (Object)arr2);

    }

    @Test
    public void testHeap() {
//        log.info("排序前：{}", arr);
//        Heap.printTree(arr);
        Heap.MaxHeap head = new Heap.MaxHeap(50);
        head.push(10);
        head.push(4);
        head.push(19);
        head.push(-8);
        head.push(20);
        head.push(5);
        head.push(2);
        head.push(-2);
        Heap.printTree(head.getHeap());
        log.info("弹出：{}", head.pop());
        Heap.printTree(head.getHeap());
        log.info("弹出：{}", head.pop());
        Heap.printTree(head.getHeap());
        head.push(18);
        Heap.printTree(head.getHeap());
        head.push(6);
        Heap.printTree(head.getHeap());

    }

    @Test
    public void testHeapSort() {
        log.info("排序前：{}", arr);
        HeapSort.sort(arr);
        log.info("排序后：{}", arr);
    }

    @Test
    public void testSortArrDistanceLessK() {
        int[] arr = {1,3,7, 4, 2, 8, 5 ,6};
        SortArrDistanceLessK.sort(arr, 4);
        Heap.printTree(arr);
    }

    @Test
    public void testCoverMax() {
        int[][] lines = CoverMax.generateLines(30, 2, 50);
        log.info("lines: {}", (Object) lines);
//        log.info("最大覆盖数量：{}", CoverMax.maxCover1(lines));
        log.info("最大覆盖数量：{}", CoverMax.maxCover2(lines));
    }

    @Test
    public void testGreaterHeap() {
        GreaterHeap<Integer> greaterHeap = new GreaterHeap<>((a, b) -> a -b);
        greaterHeap.push(10);
        greaterHeap.push(13);
        greaterHeap.push(4);
        greaterHeap.push(2);
        greaterHeap.push(5);
        greaterHeap.push(7);
        greaterHeap.push(23);
        Heap.printTree(greaterHeap.getAllElements());
        greaterHeap.pop();
        greaterHeap.pop();
        Heap.printTree(greaterHeap.getAllElements());
        greaterHeap.push(32);
        greaterHeap.push(70);
        greaterHeap.push(40);
        greaterHeap.push(51);
        greaterHeap.push(78);
        greaterHeap.push(55);
        Heap.printTree(greaterHeap.getAllElements());
        greaterHeap.remove(70);
        Heap.printTree(greaterHeap.getAllElements());

        greaterHeap.remove(78);
        Heap.printTree(greaterHeap.getAllElements());
        greaterHeap.remove(5);
        Heap.printTree(greaterHeap.getAllElements());

    }

    @Test
    public void testListToArr() {
        Integer[] objArr = new  Integer[10];
        objArr[0] = 10;
        objArr[1] = 12;
        objArr[2] = 14;
        objArr[3] = 15;
        objArr[4] = 100;

        int[] bArr = Arrays.stream(objArr).mapToInt(num -> num == null ? 0 : num).toArray();
        log.info("objArr: {}", (Object) objArr);
        log.info("bArr: {}", (Object) bArr);
    }

    @Test
    public void testMacro() {
        String s1 = "abcd";
        String s2 = "ab" + "cd";
        log.info("s1==s2:{}", s1 == s2);

        String s3 = "ab";
        String s4 = "cd";
        String s5 = s3 + s4;
        log.info("s1==s2:{}", s1 == s5);


        String key="doiijwey90780203u98723DWsw2389";
        String timestamp= "1645454234";
        String nonce="oiaduowefuoupfbqer";
        String encrypt="CtJyyv2PRkrwuJAxNc/aD+g5Q437==";
        String[] array = new String[]{key, timestamp, nonce, encrypt};
        // 字符串排序
        Arrays.sort(array);
        String str = "";
        for (String s: array) {
            str += "\"" + s + "\" + ";
        }
        log.info("{}",(Object) array);
        log.info("{}",str);

    }

    @Test
    public void testJuc() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        arr[0] = 100;
        lock.unlock();
    }

    @Test
    public void testThreadLocal() throws InterruptedException {
        ThreadLocal<Integer> t1 = new ThreadLocal<>();
        log.info("{} , before t1: {}", Thread.currentThread(), t1.get());
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                log.info("{} , before t1: {}", Thread.currentThread(), t1.get());
                t1.set(190);
                log.info("{} , after t1: {}", Thread.currentThread(), t1.get());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                log.info("{} , before t1: {}", Thread.currentThread(), t1.get());
                Thread.sleep(1000);
                log.info("{} , after t1: {}", Thread.currentThread(), t1.get());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        log.info("{} , after t1: {}", Thread.currentThread(), t1.get());

        Thread.sleep(7000);
    }

    @Test
    public void testThreadCreate() throws ExecutionException, InterruptedException {
        FiveThreadCreate.type1();
        FiveThreadCreate.type2();
        FiveThreadCreate.type3();
        FiveThreadCreate.type4();
        FiveThreadCreate.type5();

        Thread.sleep(7000);


    }

    @Test
    public void testThreadStop() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("设置线程停止啊");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t1.stop();
        t1.resume();
        t1.suspend();

        Thread.sleep(7000);

    }

    @Test
    public void testP() {

        Date date = getOfMonthOneDay(new Date());
        log.info("date: {}", date);
        String dateStr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
        log.info("dateStr: {}", dateStr);
    }

    public static Date getOfMonthOneDay(Date date){

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MILLISECOND,0);
        cal.add(Calendar.MONTH,1);
        return cal.getTime();
    }

    @Test
    public void testSet() {
        Set<String> set = new HashSet<>();
        log.info("{}-{}-{}", set.add("rr"), set.add("tt"), set.add("tt"));
    }

    @Test
    public void testXX() {
        Date createDate;
        List<Long> projectIdList = new ArrayList<>();
        String param = "2020-05-20,";
        String[] params = param.split(",");
        for (String s : params) {
            if (!StringUtils.isNumeric(s)) {
                log.info("date: [{}]", s);
                createDate = parseDate(s);
            } else {
                log.info("long: [{}]", s);
                projectIdList.add(Long.parseLong(s));
            }
        }
        if (projectIdList.isEmpty()) {
            log.info("empty................");
        } else {
            log.info("no empty");
        }
    }

    private Date parseDate(String param) {
        return Optional.ofNullable(param)
                .filter(StringUtils::isNotBlank)
                .map(source -> {
                    try {
                        return DateUtils.parseDate(source, "yyyy-MM-dd");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .orElseGet(Date::new);
    }




    @Test
    public void testFor() {
        int[][] M = new int[6][8];
        log.info("arr size: {}", M.length);
        for(int i = 0; i < M.length; i++) {
            for(int j = i + 1; j < M.length; j++) {
                log.info("i:{}, j: {}", i , j);
            }
        }
    }

    @Test
    public void testArr01() {
        int[][] positions = new int[5][3];
        int num = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                positions[i][j] = num++;
            }
        }
        int index = 0;
        for (int[] position : positions ) {
            log.info("index: {}, p0: {}, p1: {}", index++, position[0], position[1]);
        }
    }
}
