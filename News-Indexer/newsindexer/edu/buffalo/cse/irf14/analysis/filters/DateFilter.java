package edu.buffalo.cse.irf14.analysis.filters;

import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

public class DateFilter extends TokenFilter{

	public DateFilter(TokenStream stream) {
		super(stream);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean increment() throws TokenizerException {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		 return super.getStream();
	}
	
	

}
