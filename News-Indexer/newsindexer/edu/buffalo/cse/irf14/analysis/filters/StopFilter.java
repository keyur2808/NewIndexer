package edu.buffalo.cse.irf14.analysis.filters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import edu.buffalo.cse.irf14.analysis.Token;
import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

public class StopFilter extends TokenFilter {

	protected static Set<String> stopWords = null;

	public StopFilter(TokenStream stream) {
		super(stream);
		getStopWordSetInstance();
		// TODO Auto-generated constructor stub
	}

	protected static Set<String> getStopWordSetInstance() {
		try {
			if (stopWords == null) {
				stopWords = new HashSet<String>();
				System.out.println(System.getProperty("user.dir"));
				BufferedReader reader = new BufferedReader(new FileReader(
						"newsindexer//edu//buffalo//cse//irf14//analysis//filters//StopWordsList.txt"));
				String tmp = null;
				while ((tmp = reader.readLine()) != null) {
					stopWords.add(tmp);
				}
				reader.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stopWords;
	}

	@Override
	public boolean increment() throws TokenizerException {

		if (!super.stream.hasNext()) {
			return false;
		} else {
			Token token = super.stream.next();
			action(token);
			return true;
		}
		// return false;

	}

	private void action(Token token) {
		String ttext=token.getTermText();
		if(ttext!=null && ttext!="" && stopWords.contains(ttext.toLowerCase())){
			stream.remove();
		}
	}

	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return super.getStream();
	}

}
