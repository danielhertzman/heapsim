package batches;

import memory.FirstFit;
import memory.Memory;
import memory.Pointer;

public class ComplexBatch {

	public static void main(String[] args) {
		ComplexBatch batch = new ComplexBatch();
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
		Memory m = new FirstFit(1000); // Swap this for  your own implementation
		Pointer[] ps = new Pointer[20];

		m.printLayout();

		ps[0] = m.alloc(100);
		ps[0].write(range(1, 100));
		ps[1] = m.alloc(200);
		ps[1].write(range(1001, 1200));
		ps[2] = m.alloc(500);
		ps[2].write(range(101, 600));
		System.out.println("should  on 800");m.printLayout();
		ps[3] = m.alloc(200);
		ps[3].write(range(1, 200));
		System.out.println("should  zero");m.printLayout();
		m.release(ps[2]);							//All fine to here
		System.out.println("ONE");m.printLayout();


		ps[4] = m.alloc(50);
		ps[4].write(range(51, 100));
		ps[5] = m.alloc(5);
		ps[5].write(range(42, 46));					//355-800
		ps[6] = m.alloc(90);
		ps[6].write(range(5000, 5089));				//free 445-800
		m.release(ps[0]);
		System.out.println("TWO");m.printLayout();  // 0-100, 445-800

		ps[7] = m.alloc(60);					    // 60-100, 445-800
		ps[7].write(range(10, 69));
		m.release(ps[4]);
		System.out.println("THREE");m.printLayout(); //  300-350, 60-100, 445-800

		ps[8] = m.alloc(45);
		ps[8].write(range(1, 45));				// 345-350, 60-100, 445-800
		ps[9] = m.alloc(10);
		ps[9].write(range(16, 25));				// 345-350, 70-100, 445-800

		m.release(ps[5]);						// 350-355, 345-350, 70-100, 445-800
		m.release(ps[6]);						// 355-445, 350-355, 345-350, 70-100, 445-800

		System.out.println("FOUR");m.printLayout();

		ps[10] = m.alloc(40);
		ps[10].write(range(301, 340));		// 395-445, 350-355, 345-350, 70-100, 445-800
		System.out.println("FIVE");m.printLayout();
		ps[11] = m.alloc(200);
		ps[11].write(range(1, 200));		// 395-445, 350-355, 345-350, 70-100, 645-800
		System.out.println("SIX");m.printLayout();
		ps[12] = m.alloc(35);
		ps[12].write(range(501, 535));		// 430-445, 350-355, 345-350, 70-100, 645-800
		System.out.println("SIX");m.printLayout();
		ps[13] = m.alloc(25);
		ps[13].write(range(1, 25));			// 430-445, 350-355, 345-350, 95-100, 645-800
		System.out.println("SEVEN");m.printLayout();
		ps[14] = m.alloc(60);
		ps[14].write(range(1, 60));			// 430-445, 350-355, 345-350, 95-100, 705-800
		System.out.println("EIGHT");m.printLayout();
		ps[15] = m.alloc(60);
		ps[15].write(range(1, 60));			// 430-445, 350-355, 345-350, 95-100, 765-800
		System.out.println("NINE");m.printLayout();

		m.release(ps[3]);	// 800-1000, 430-445, 350-355, 345-350, 95-100, 765-800
		System.out.println("TEN");m.printLayout();
		m.release(ps[13]);
		System.out.println("ELEVEN");m.printLayout();//70-95, 800-1000, 430-445, 350-355, 345-350, 95-100, 765-800
		m.release(ps[12]);
		System.out.println("TWELV");m.printLayout();//395-430, 70-95, 800-1000, 430-445, 350-355, 345-350, 95-100, 765-800

		ps[16] = m.alloc(170);
		System.out.println("THIRDTEEN");m.printLayout();
		ps[16].write(range(10001, 10170));//395-430, 70-95, 970-1000, 430-445, 350-355, 345-350, 95-100, 765-800
		System.out.println("");m.printLayout();
		/*try {
			ps[17] = m.alloc(105);
			ps[17].write(range(40, 65));
		}catch (NullPointerException ex){
			System.out.println("No More Memory");
		}*/
		/*
		ps[18] = m.alloc(40);
		ps[18].write(range(1, 40));
		ps[19] = m.alloc(5);
		ps[19].write(range(11, 15));
		*/

		//m.printLayout();



		// After these last releases, the memory table should  empty
		m.release(ps[1]);
		m.release(ps[7]);
		m.release(ps[8]);
		m.release(ps[9]);
		m.release(ps[10]);
		m.release(ps[11]);
		m.release(ps[14]);
		m.release(ps[15]);
		m.release(ps[16]);/*
		m.release(ps[17]);
		m.release(ps[18]);
		m.release(ps[19]);*/

		System.out.println("All FREE");
		m.printLayout();

	}
}
