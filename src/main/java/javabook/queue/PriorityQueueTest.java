package javabook.queue;

import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        var pq = new PriorityQueue<Integer>();
        pq.add(12);
        pq.add(5);
        pq.add(7);
        pq.add(23);
        for (Integer integer : pq) {
            System.out.print(integer + " ");
        }
        System.out.println();
        while (!pq.isEmpty())
            System.out.print(pq.remove() + " ");
    }
}
