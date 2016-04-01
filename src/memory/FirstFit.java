package memory;

import java.util.LinkedList;

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
	 * 
	 * @param allocSize the number of cells to allocate.
	 * @return The address of the first cell.
	 */
	@Override
	public Pointer alloc(int allocSize) {
		// TODO Implement this!

		for (Segment segment : freeList){

			if (segment.size > allocSize){

				int oldStartSegmentAdress = segment.pointer.pointsAt();
				Pointer allocPointer = new Pointer(oldStartSegmentAdress, this);

				segment.pointer.pointAt(oldStartSegmentAdress + allocSize);
				segment.size = segment.size - allocSize;

				return allocPointer; // return pointer pointing at old free segment start
			}
		}

		return null;
	}
	
	/**
	 * Releases a number of data cells
	 * 
	 * @param p The pointer to release.
	 */
	@Override
	public void release(Pointer p) {
		// TODO Implement this!
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
		freeList.forEach(segment -> System.out.println("Free Memory: " +
				segment.pointer.pointsAt() + " - " + (segment.pointer.pointsAt() + segment.size)));

	}

}
