package edu.buffalo.cse.irf14.analysis.filters;

import java.text.Normalizer;

import edu.buffalo.cse.irf14.analysis.Token;
import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

public class AccentFilter extends TokenFilter{

	public AccentFilter(TokenStream stream) {
		super(stream);
		// TODO Auto-generated constructor stub
	}

	public String flattenToAscii(String string) {
	    if(string!=null && string!=""){    
		StringBuilder sb = new StringBuilder(string.length());
	        string = Normalizer.normalize(string, Normalizer.Form.NFD);
	        for (char c : string.toCharArray()) {
	            if (c <= '\u007F') sb.append(c);
	        }
	        return sb.toString();
	    }
	    return null;
	}
	
	@Override
	public boolean increment() throws TokenizerException {
		if (!super.stream.hasNext()) {
			return false;
		} else {
			Token token = super.stream.next();
			flattenToAscii(token.getTermText());
			return true;
		}
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return stream;
	}

}
