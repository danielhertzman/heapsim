package memory;

import java.util.LinkedList;

/**
 * This memory model allocates memory cells based on the first-fit method. 
 * 
 * @author "Johan Holmberg, Malmö university"
 * @since 1.0
 */
public class FirstFit extends Memory {

	private LinkedList<Pointer> freeList = new LinkedList<>();
	/**
	 * Initializes an instance of a first fit-based memory.
	 * 
	 * @param size The number of cells.
	 */
	public FirstFit(int size) {
		super(size);
		// TODO Implement this!
		Pointer startPointer = new Pointer(this);
		freeList.push(startPointer);

	}

	/**
	 * Allocates a number of memory cells. 
	 * 
	 * @param size the number of cells to allocate.
	 * @return The address of the first cell.
	 */
	@Override
	public Pointer alloc(int size) {
		// TODO Implement this!

		int currentAdress = 0;

		for (Pointer p : freeList) {
			if ((p.pointsAt() - currentAdress) > size) {
				return newPointer(currentAdress, size);
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
		freeList.forEach(pointer -> System.out.println(pointer.read(pointer.pointsAt())));
	}

	/**
	 * Method that creats a new pointer
	 *
	 */

	public Pointer newPointer(int currentPointer, int size) {
		return null;
	}

}
