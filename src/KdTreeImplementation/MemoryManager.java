package KdTreeImplementation;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class MemoryManager {
	private final int blockSize = 4096;
	private final int numOfBuffers = 10;
	private RandomAccessFile file;
	private LinkedList<Long> priority;//indica que buffer va a ser sobreescrito
	private long position;
	private int numOfElements;
	private byte[] buffer;
	public MemoryManager(int numOfBuffers, int bufferSize, String fileName) throws FileNotFoundException{
		file = new RandomAccessFile(fileName, "rw");
		buffer = new byte[bufferSize];
		priority = new LinkedList<Long>();
		numOfElements = 0;
		position = 0;
	}
	public void writeNode(MemoryNode n){
		//TODO
	}
	public MemoryNode readNode(double pos){
		return null;
		//TODO
	}
	private void improvePriority(long elmt){
		priority.remove(elmt);
		priority.offerFirst(elmt);
	}
	public long getNewPosition(){
		long temp = position;
		position += buffer.length;
		return temp;
	}

}
