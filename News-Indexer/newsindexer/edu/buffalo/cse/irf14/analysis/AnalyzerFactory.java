/**
 * 
 */
package edu.buffalo.cse.irf14.analysis;

import edu.buffalo.cse.irf14.analysis.analyzers.ContentAnalyzer;
import edu.buffalo.cse.irf14.document.FieldNames;

/**
 * @author nikhillo
 * This factory class is responsible for instantiating "chained" {@link Analyzer} instances
 */
public class AnalyzerFactory {
	/**
	 * Static method to return an instance of the factory class.
	 * Usually factory classes are defined as singletons, i.e. 
	 * only one instance of the class exists at any instance.
	 * This is usually achieved by defining a private static instance
	 * that is initialized by the "private" constructor.
	 * On the method being called, you return the static instance.
	 * This allows you to reuse expensive objects that you may create
	 * during instantiation
	 * @return An instance of the factory
	 */
	
	private static AnalyzerFactory factory =null;
	
	
	public static AnalyzerFactory getInstance() {
		if(factory==null){
			factory=new AnalyzerFactory();
		}
		return factory;
		
	}
	
	private AnalyzerFactory(){
		
	}
	
	/**
	 * Returns a fully constructed and chained {@link Analyzer} instance
	 * for a given {@link FieldNames} field
	 * Note again that the singleton factory instance allows you to reuse
	 * {@link TokenFilter} instances if need be
	 * @param name: The {@link FieldNames} for which the {@link Analyzer}
	 * is requested
	 * @param TokenStream : Stream for which the Analyzer is requested
	 * @return The built {@link Analyzer} instance for an indexable {@link FieldNames}
	 * null otherwise
	 * @throws TokenizerException 
	 */
	public Analyzer getAnalyzerForField(FieldNames name, TokenStream stream) throws TokenizerException {
		TokenFilterFactory factory=TokenFilterFactory.getInstance();
		Analyzer analyzer=null;
		
		switch(name){
		case AUTHOR:
			break;
		case AUTHORORG:
			break;
		case CATEGORY:
			break;
			
		case CONTENT:
			analyzer=new ContentAnalyzer(stream);
			break;
			
		case FILEID:
			//analyzer=factory.getFilterByType(TokenFilterType.NUMERIC, stream);
			break;
		case NEWSDATE:
			analyzer=factory.getFilterByType(TokenFilterType.DATE, stream);
			break;
		case PLACE:
//			analyzer=factory.getFilterByType(TokenFilterType.ACCENT, stream);
//			analyzer=factory.getFilterByType(TokenFilterType.CAPITALIZATION, stream);
			break;
		case TITLE:
			break;
		default:
			break;
		
		}
		
		return analyzer;
	}
}
