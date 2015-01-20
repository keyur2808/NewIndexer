/**
 * 
 */
package edu.buffalo.cse.irf14.index;

/**
 * @author nikhillo
 * Generic wrapper exception class for indexing exceptions
 */
public class IndexerException extends Exception {

	/**
	 * 
	 */
    public IndexerException(){
    	
    }
    
    public IndexerException(String message){
    	super(message);
    }
	
	private static final long serialVersionUID = -3012675871474097239L;

}
