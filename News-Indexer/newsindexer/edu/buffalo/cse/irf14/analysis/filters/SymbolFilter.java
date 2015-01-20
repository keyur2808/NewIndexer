package edu.buffalo.cse.irf14.analysis.filters;

import edu.buffalo.cse.irf14.analysis.Token;
import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

public class SymbolFilter extends TokenFilter {

	private String endOfSentence="\\b[,<>!.\"'?]\\b";
		
	public SymbolFilter(TokenStream stream) {
		super(stream);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean increment() throws TokenizerException {
		
		// TODO Auto-generated method stub
		if (!super.stream.hasNext()) {
			return false;
		} else {
			Token token = super.stream.next();
			action(token);
		    return true;
		}
	}

	private void action(Token token) {
		String tokenText=token.getTermText();
		if (tokenText!=null && tokenText!="" && tokenText.matches(endOfSentence)){
			stream.remove();
		}
		
	}

	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return null;
	}

}
