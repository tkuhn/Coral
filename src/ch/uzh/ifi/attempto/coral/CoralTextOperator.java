// This file is part of Coral.
// Copyright 2011, Tobias Kuhn and Stefan Hoefler.
// 
// Coral is free software: you can redistribute it and/or modify it under the terms of the GNU
// Lesser General Public License as published by the Free Software Foundation, either version 3 of
// the License, or (at your option) any later version.
// 
// Coral is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
// the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
// General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with Coral. If
// not, see http://www.gnu.org/licenses/.

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
