/**
 * 
 */
package edu.buffalo.cse.irf14.index;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
//import java.util.*;







import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import edu.buffalo.cse.irf14.analysis.Analyzer;
import edu.buffalo.cse.irf14.analysis.AnalyzerFactory;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.Tokenizer;
import edu.buffalo.cse.irf14.document.Document;
import edu.buffalo.cse.irf14.document.FieldNames;

/**
 * @author nikhillo
 * Class responsible for writing indexes to disk
 */
public class IndexWriter {
	/**
	 * Default constructor
	 * @param indexDir : The root directory to be sued for indexing
	 */
	long count=0;
	String indexDir=null;
	String dictDir=null;
	Map<Long,String>catDict=new TreeMap<>();
	Map<Long,LinkedList<String>>catIndex=new TreeMap<>();
	
	public IndexWriter(String indexDir) {
		//TODO : YOU MUST IMPLEMENT THIS
		this.indexDir=indexDir+File.separator;
		this.dictDir=indexDir+File.separator+"dictionaries"+File.separator;
				
	}
	
	/**
	 * Method to add the given Document to the index
	 * This method should take care of reading the filed values, passing
	 * them through corresponding analyzers and then indexing the results
	 * for each indexable field within the document. 
	 * @param d : The Document to be added
	 * @throws IndexerException : In case any error occurs
	 */
	public void addDocument(Document d,long count,long catCount) throws IndexerException {
		
        try {
        	if(d==null){
        		throw new IndexerException();
        	}
            	//System.out.println(catCount+1);
        	if(!catDict.containsKey(catCount+1)){
        		catDict.put(catCount+1,d.getField(FieldNames.CATEGORY)[0]);
        	}
        	LinkedList<String>l=null;
        	if(!catIndex.containsKey(catCount+1)){
        		l=new LinkedList<String>();
        		l.add(d.getField(FieldNames.FILEID)[0]);
        		catIndex.put(catCount+1,l);
        	}else{
        		l=catIndex.get(catCount+1);
        		l.add(d.getField(FieldNames.FILEID)[0]);
        	}
        	
        	Tokenizer tok=null;
        	for(FieldNames field:FieldNames.values()){
        	TokenStream stream=tok.consume(d.getField(field)[0]);
        	
        
        	AnalyzerFactory factory=AnalyzerFactory.getInstance();
        	Analyzer analyzer=factory.getAnalyzerForField(field, stream);
        	analyzer.increment();
        	stream=analyzer.getStream();
        	stream.reset();
        	}
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	

	}
//	
//	RandomAccessFile memoryMappedFile = new RandomAccessFile("CategoryIndex.txt", "rw");
//	MappedByteBuffer out = memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_WRITE,0, count);
//	out.clear();
//	memoryMappedFile.close();
	
	/**
	 * Method that indicates that all open resources must be closed
	 * and cleaned and that the entire indexing operation has been completed.
	 * @throws IndexerException : In case any error occurs
	 */
	public void close() throws IndexerException {
		try {
			BufferedWriter writer =new BufferedWriter(new FileWriter(dictDir+"CategoryDict.txt"));
			for(Map.Entry<Long,String>entry:catDict.entrySet()){
				writer.write(new StringBuilder().append(entry.getKey()).append(" ").append(entry.getValue()).append("\n").toString());
			}
			writer.close();
			writer =new BufferedWriter(new FileWriter(indexDir+"CategoryIndex.txt"));
			for(Entry<Long, LinkedList<String>> entry:catIndex.entrySet()){
				writer.write(new StringBuilder().append(entry.getKey()).append(" ").append(entry.getValue()).append("\n").toString());
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IndexerException(e.getMessage());
			//e.printStackTrace();
		}
	}
}
