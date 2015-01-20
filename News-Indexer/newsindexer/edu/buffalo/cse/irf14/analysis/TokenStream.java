/**
 * 
 */
package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author nikhillo
 * Class that represents a stream of Tokens. All {@link Analyzer} and
 * {@link TokenFilter} instances operate on this to implement their
 * behavior
 */
public class TokenStream implements Iterator<Token>{
	
	private List<Token> tokens=null;
	//private int size=this.tokens.size();
	private int currentIndex=-1;
	private int nextIndex=0;
	
	public TokenStream(){
		this.tokens=new ArrayList<Token>(500);
		
	}
	
	
	/**
	 * Method that checks if there is any Token left in the stream
	 * with regards to the current pointer.
	 * DOES NOT ADVANCE THE POINTER
	 * @param token 
	 * @return true if at least one Token exists, false otherwise
	 */
	
	protected void addToken(Token token){
		this.tokens.add(token);
	}
	
	@Override
	public boolean hasNext() {
		
		return (nextIndex>=0 && (nextIndex < tokens.size()) );
				
	}

	/**
	 * Method to return the next Token in the stream. If a previous
	 * hasNext() call returned true, this method must return a non-null
	 * Token.
	 * If for any reason, it is called at the end of the stream, when all
	 * tokens have already been iterated, return null
	 */
	@Override
	public Token next() {
		Token token=null;
		if(this.hasNext()){
			token=tokens.get(++currentIndex);
			nextIndex++;
			return token;
		}
		return null;
		
	}
	
	/**
	 * Method to remove the current Token from the stream.
	 * Note that "current" token refers to the Token just returned
	 * by the next method. 
	 * Must thus be NO-OP when at the beginning of the stream or at the end
	 */
	@Override
	public void remove() {
		if(currentIndex>=0 && currentIndex<tokens.size()){
			tokens.remove(currentIndex--);
			nextIndex--;
		}
		
	}
	
	/**
	 * Method to reset the stream to bring the iterator back to the beginning
	 * of the stream. Unless the stream has no tokens, hasNext() after calling
	 * reset() must always return true.
	 */
	public void reset() {
		currentIndex=-1;
		nextIndex=0;
		
	}
	
	/**
	 * Method to append the given TokenStream to the end of the current stream
	 * The append must always occur at the end irrespective of where the iterator
	 * currently stands. After appending, the iterator position must be unchanged
	 * Of course this means if the iterator was at the end of the stream and a 
	 * new stream was appended, the iterator hasn't moved but that is no longer
	 * the end of the stream.
	 * @param stream : The stream to be appended
	 */
	public void append(TokenStream stream) {
		if(stream!=null){
			stream.reset();
			Token token=null;
		while(stream.hasNext()){
			token=stream.next();
			if(token!=null){
				this.addToken(token);
			}
		}
		}
	}


	public Token getCurrent() {
		// TODO Auto-generated method stub
		if(currentIndex>-1 && currentIndex<tokens.size()){
			return tokens.get(currentIndex);
		}
		return null;
	}
	
}
