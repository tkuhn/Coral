package ch.uzh.ifi.attempto.coral;

import ch.uzh.ifi.attempto.base.DefaultTextOperator;
import ch.uzh.ifi.attempto.base.TextElement;

public class CoralTextOperator extends DefaultTextOperator {
	
	public TextElement createTextElement(String text) {
		String t = text.toLowerCase();
		if (t.startsWith("a " ) || t.startsWith("an ")) {
			String n = text.substring(text.indexOf(" ") + 1);
			if (CoralLexicon.useIndefiniteArticleAn(n)) {
				text = "an " + n;
			} else {
				text = "a " + n;
			}
		}
		return super.createTextElement(text);
	}

}
