package batches;

import memory.BestFit;
import memory.FirstFit;
import memory.Memory;
import memory.Pointer;

public class TestBatch {

    public static void main(String[] args) {

        TestBatch batch = new TestBatch();
        batch.run();

    }

    public int[] range(int start, int stop) {
        int[] range = new int[stop - start + 1];

        for (int i = 0; i < range.length; i++) {
            range[i] = start + i;
        }

        return range;
    }

    public void run() {
        Memory m = new BestFit(20); // Swap this for  your own implementation
        Pointer p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;

        m.printLayout();
        System.out.println("Raw Memory: " + m.toString() + "\n");

        p1 = m.alloc(7);
        p1.write(range(1, 7));
        System.out.println("After allocation");
        m.printLayout();
        System.out.println("Raw Memory: " + m.toString() + "\n");

        p2 = m.alloc(5);
        p2.write(range(1, 5));
        System.out.println("After allocation");
        m.printLayout();
        System.out.println("Raw Memory: " + m.toString() + "\n");

        p3 = m.alloc(4);
        p3.write(range(1, 4));
        System.out.println("After allocation");
        m.printLayout();
        System.out.println("Raw Memory: " + m.toString() + "\n");

        p4 = m.alloc(3);
        p4.write(range(1, 3));
        System.out.println("After allocation");
        m.printLayout();
        System.out.println("Raw Memory: " + m.toString() + "\n");

        //Release

        m.release(p1);
        m.release(p3);
        System.out.println("After Release:");
        m.printLayout();
        System.out.println("Raw Memory: " + m.toString() + "\n");

        //Alloc again to test BestFit
        p5 = m.alloc(1);
        p5.write(range(1, 1));
        System.out.println("After allocation");
        m.printLayout();
        System.out.println("Raw Memory: " + m.toString() + "\n");

        p6 = m.alloc(2);
        p6.write(range(1, 2));
        System.out.println("After allocation");
        m.printLayout();
        System.out.println("Raw Memory: " + m.toString() + "\n");

        p7 = m.alloc(4);
        p7.write(range(1, 4));
        System.out.println("After allocation");
        m.printLayout();
        System.out.println("Raw Memory: " + m.toString() + "\n");

        m.printLayout();

    }
}
