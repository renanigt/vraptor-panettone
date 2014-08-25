package br.com.caelum.vraptor.panettone.parser.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.vraptor.panettone.parser.Regexes;
import br.com.caelum.vraptor.panettone.parser.SourceCode;
import br.com.caelum.vraptor.panettone.parser.TextChunk;
import br.com.caelum.vraptor.panettone.parser.ast.Node;
import br.com.caelum.vraptor.panettone.parser.ast.VariableDeclarationNode;

public class VariableDeclarationRule implements Rule {

	@Override
	public List<TextChunk> getChunks(SourceCode sc) {
		List<TextChunk> chunks = new ArrayList<TextChunk>();
		
		
		Pattern p = Pattern.compile(
				"\\(@" + 
				Regexes.SPACE + 
				Regexes.CLASS_NAME + 
				Regexes.SPACE + 
				Regexes.GENERICS + 
				Regexes.SPACE + 
				Regexes.CLASS_NAME + 
				Regexes.SPACE + 
				"\\)");
		
		Matcher matcher = p.matcher(sc.getSource());
		
		while(matcher.find()) {
			chunks.add(new TextChunk(matcher.group()));
		}
		
		return chunks;
	}

	@Override
	public Node getNode(TextChunk chunk) {
		String type = chunk.getText().split(" ")[1].trim();
		String name = chunk.getText().split(" ")[2].replace(")", "").trim();
		
		return new VariableDeclarationNode(type, name);
	}

}
