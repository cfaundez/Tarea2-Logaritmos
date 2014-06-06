package KdTreeImplementation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.LinkedList;

public class MemoryManager {
	private final int blockSize = 4096;
	private final int numOfBuffers = 10;
	//3 longs, 2 double y 1 char
	private final int bufferSize=42;
	private RandomAccessFile file;
	private LinkedList<Long> priority;//indica que buffer va a ser sobreescrito
	private long position;
	private int numOfElements;
	private HashMap<Long, byte[]> inMemoryNodes;
	//orden de nodo: posLeft, pos, posRight, x, y, isleaf
	private byte[] buffer;
	public MemoryManager(int numOfBuffers, String fileName) throws FileNotFoundException{
		file = new RandomAccessFile(fileName, "rw");
		buffer = new byte[bufferSize];
		priority = new LinkedList<Long>();
		numOfElements = 0;
		position = 0;
	}
	public void writeBuff(byte[] b, long pos) throws IOException{
		if(inMemoryNodes.containsKey(pos)){
			inMemoryNodes.put(pos, b);
			return;
		}
		file.seek(pos);
		file.write(b);
	}
	public byte[] readBuff(long pos) throws IOException{
		if(inMemoryNodes.containsKey(pos)){
			this.improvePriority(pos);
			return inMemoryNodes.get(pos);
		}
		if(numOfElements>=numOfBuffers){
			long byeNode=priority.pollLast();
			buffer=inMemoryNodes.get(byeNode);
			inMemoryNodes.remove(byeNode);
			this.writeBuff(buffer,byeNode);
			numOfElements--;
		}
		file.seek(pos);
		file.read(buffer);
		inMemoryNodes.put(pos, buffer);
		this.improvePriority(pos);
		numOfElements++;
		return buffer;
		
		
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
