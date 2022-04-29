package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 链表的小等大问题
 * @date: 2022/4/29 11:32
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // ----------------------------------数组实现

    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head ;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        cur = head;
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i < nodeArr.length ;i++) {
            nodeArr[i - 1 ].next = nodeArr[i];
        }
        nodeArr[i - 1 ].next = null;
        return  nodeArr[0];

    }

    public static void arrPartition(Node[] nodes, int pivot) {
        int small = -1;
        int big = nodes.length;
        int index = 0;
        while (index != big) {
            if (nodes[index].value < pivot) {
                swap(nodes, ++small, index++);
            } else if(nodes[index].value == pivot) {
                index++;
            } else {
                swap(nodes, --big, index++);
            }
        }
    }

    public static void swap(Node[] nodes, int a, int b) {
        Node cur = nodes[a];
        nodes[a] = nodes[b];
        nodes[b] = cur;
    }





    // --------------------------- 纯链表实现
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = null;
            if( head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot){
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }

        // 一步步确认前面的尾部
        if (sH != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }

        if (eT != null) {
            eT.next = mH;
        }

        return sH != null ? sH : (eH != null ? eH : mH);

    }


    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//         head1 = listPartition1(head1, 5);
        log.info("----------------------------link1");
        head1 = listPartition2(head1, 4);
        printLinkedList(head1);

    }
}
