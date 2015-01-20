package edu.buffalo.cse.irf14.analysis.filters;

import edu.buffalo.cse.irf14.analysis.Token;
import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

public class Capitalization extends TokenFilter {

	private Token previous =null;
	private String endOfSentence="[,!.\"'?]*";
	private boolean isMerged=false;
	
	
	public Capitalization(TokenStream stream) {
		super(stream);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean increment() throws TokenizerException {
		isMerged=false;
		if (!super.stream.hasNext()) {
			return false;
		} else {
			Token token = super.stream.next();
			action(token);
			if(!isMerged){
				previous=token;
			}
			return true;
		}
		
	}
	
	private void action(Token token){
		String ttext=token.getTermText();
		if(previous!=null){
		String prevText=previous.getTermText().trim();
		if(prevText.contains(" ")){
			String tmp[]=prevText.split(" ");
			prevText=tmp[tmp.length-1];
		}
		if(ttext!=null && ttext!=""){
			if( ttext.equals(ttext.toUpperCase()) &&  ttext.equals(ttext.toLowerCase()) ){
				return;
			}else{
				if(ttext.matches("[A-Z][a-z]+") && prevText!=null && prevText!="" && prevText.matches("\\b[A-Z][a-z]+\\b")){
					previous.merge(token);
					stream.remove();
					isMerged=true;
				}
				if(prevText.endsWith(".")){
					token.setTermText(ttext.toLowerCase());
				}
			}
		}
		}
	}
	
	
	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return super.getStream();
	}

}
