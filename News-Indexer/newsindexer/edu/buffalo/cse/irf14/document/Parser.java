package edu.buffalo.cse.irf14.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.attribute.AclEntry.Builder;
import java.util.Arrays;

/**
 * @author nikhillo Class that parses a given file into a Document
 */

public class Parser {
	/**
	 * Static method to parse the given file into the Document object
	 * 
	 * @param filename
	 *            : The fully qualified filename to be parsed
	 * @return The parsed and fully loaded Document object
	 * @throws ParserException
	 *             In case any error occurs during parsing
	 */
	public static Document parse(String filename) throws ParserException {
		// TODO YOU MUST IMPLEMENT THIS
		if (filename == null || filename == "") {
			throw new ParserException();
		}
		try {
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuilder builder=new StringBuilder();
			Document document = new Document();
			document.setField(FieldNames.FILEID, file.getName());
			document.setField(FieldNames.CATEGORY, file.getParentFile().getName());
			String tmp = null;
			reader.readLine();reader.readLine();		
			if ((tmp = reader.readLine()) != null) {
				document.setField(FieldNames.TITLE, tmp.trim());
			}
			reader.readLine();
			if ((tmp = reader.readLine()) != null) {
				if (tmp.contains("<AUTHOR>")) {
					//System.out.println(filename);
					if(tmp.contains(",")){
						String authorg=tmp.substring(tmp.indexOf(',')+1,tmp.lastIndexOf('<')).trim();
						document.setField(FieldNames.AUTHORORG,authorg);
						tmp=(String) tmp.subSequence(tmp.indexOf('y')+2,tmp.indexOf(','));
					}else{
						tmp=(String) tmp.subSequence(tmp.indexOf('y')+2,tmp.lastIndexOf('<'));
					}
					document.setField(FieldNames.AUTHOR, tmp.trim());
					
					tmp = reader.readLine();
				}

				if (tmp != null) {
					String buf[] = tmp.split(",");
					if (buf.length >= 1) {
						int i = 0;
				     
						while (i < buf.length) {
							if (buf[i] != null && buf[i] != "") {
								if (!buf[i].contains("-")) {
									if(builder.length()>1){
										builder.append(", ");
									}
									builder.append(buf[i].trim());
								} else {
									document.setField(FieldNames.NEWSDATE,buf[i].substring(0,buf[i].indexOf('-')).trim());
									break;
								}
							}
							i++;
						}
						document.setField(FieldNames.PLACE,builder.toString());
						builder.setLength(0);
						builder.append( ( tmp.substring( tmp.indexOf('-')+1,tmp.length() ).trim()));
						builder.append(" ");
					}
					
					
					
					while(reader!=null && (tmp=reader.readLine())!=null){
						builder.append(tmp).append(" ");
					}
					
					document.setField(FieldNames.CONTENT,builder.toString());
				}
			}
			
			
			reader.close();
			file=null;
			return document;
		} catch (IOException e) {
			System.err.println(filename);
			throw new ParserException(e.getMessage());
			// e.printStackTrace();
		}
	}
}