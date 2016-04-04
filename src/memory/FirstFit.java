package memory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Segment{

	int size;
	Pointer pointer;

	public Segment(int size, Pointer pointer){
		this.size = size;
		this.pointer = pointer;
	}
}

/**
 * This memory model allocates memory cells based on the first-fit method. 
 * 
 * @author "Johan Holmberg, Malmö university"
 * @since 1.0
 */
public class FirstFit extends Memory {

	private LinkedList<Segment> freeList = new LinkedList<>();
	private HashMap<Pointer, Integer> allocatedSegments = new HashMap<>();

	/**
	 * Initializes an instance of a first fit-based memory.
	 * 
	 * @param size The number of cells.
	 */
	public FirstFit(int size) {
		super(size);
		// TODO Implement this!
		Segment baseSegment = new Segment(size, new Pointer(this));
		freeList.push(baseSegment);
	}

	/**
	 * Allocates a number of memory cells.
	 * freeList will shrink for every allocation.
	 * 
	 * @param allocSize the number of cells to allocate.
	 * @return The address of the first cell.
	 */
	@Override
	public Pointer alloc(int allocSize) {
		// TODO Implement this!

		try {

			for (Segment segment : freeList){

				if (segment.size >= allocSize){

					int oldStartSegmentAdress = segment.pointer.pointsAt();
					Pointer allocPointer = new Pointer(oldStartSegmentAdress, this);

					allocatedSegments.put(allocPointer, allocSize);

					segment.pointer.pointAt(oldStartSegmentAdress + allocSize);
					segment.size = segment.size - allocSize;

					return allocPointer; // return pointer pointing at the start of the old free segment.
				}
			}
		}finally {
			removeZeroSegments();
		}

		return null;
	}

	/**
	 * Releases a number of data cells
	 * freeList will increment by one for every deallocation.
	 *
	 * @param p The pointer to release.
	 */
	@Override
	public void release(Pointer p) {
		// TODO Implement this!

		//Push segment to freeList
		int size = allocatedSegments.get(p);
		Segment releaseSegment = new Segment(size, p);
		freeList.push(releaseSegment);

		allocatedSegments.remove(p);

		//overwrite old values in memory with zeros
		int[] zeros = new int[size];
		write(p.pointsAt(), zeros);

	}

	public void removeZeroSegments(){
		for (int i = 0; i < freeList.size(); i++) {
			Segment segment = freeList.get(i);
			if (segment.size <= 0){
				freeList.remove(i);
			}
		}
	}

	/**
	 * Prints a simple model of the memory. Example:
	 * 
	 * |    0 -  110 | Allocated
	 * |  111 -  150 | Free
	 * |  151 -  999 | Allocated
	 * | 1000 - 1024 | Free
	 */
	@Override
	public void printLayout() {
		// TODO Implement this!
		freeList.forEach(segment -> System.out.println(
				"Free Memory: " + segment.pointer.pointsAt() + " - " + (segment.pointer.pointsAt() + segment.size) +
				"\tCells = " + segment.size ));

		//Comes in random order
		allocatedSegments.forEach((pointer, size) -> System.out.println(
				"Allocated Memory: " + pointer.pointsAt() + " - " + (pointer.pointsAt() + size) +
						"\tCells = " + size));

	}

}
