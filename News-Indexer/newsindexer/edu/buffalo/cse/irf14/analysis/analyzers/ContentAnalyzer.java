package edu.buffalo.cse.irf14.analysis.analyzers;

import java.util.ArrayList;
import java.util.List;

import edu.buffalo.cse.irf14.analysis.Analyzer;
import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenFilterFactory;
import edu.buffalo.cse.irf14.analysis.TokenFilterType;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

public class ContentAnalyzer implements Analyzer {

	TokenStream stream=null;

	public ContentAnalyzer(TokenStream stream) {
		this.stream = stream;
	}
	
	
	@Override
	public boolean increment() throws TokenizerException {
		
		List<TokenFilterType>filterList=new ArrayList<>();
	    TokenFilterFactory factory=TokenFilterFactory.getInstance();
		TokenFilter filter=null;
		
		//Check order of filters change if needed.
		
	    //filterList.add(TokenFilterType.ACCENT);
	    filterList.add(TokenFilterType.STOPWORD);
	    filterList.add(TokenFilterType.CAPITALIZATION);
	    //filterList.add(TokenFilterType.SPECIALCHARS);
	    //filterList.add(TokenFilterType.SYMBOL);
	    //filterList.add(TokenFilterType.Date);
	    //filterList.add(TokenFilterType.NUMERIC);
	    
	    for(TokenFilterType filterType:filterList){
		filter=factory.getFilterByType(filterType,stream);
		while(filter.increment()){}
	    stream.reset();
	    }
	    return true;
	}

	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return stream;
	}

}
