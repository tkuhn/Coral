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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LexiconEntry implements Comparable<LexiconEntry> {
	
	private String cat;
	private String text[];
	private String sem;
	private Map<String, String> params;
	
	public LexiconEntry(String[] s) {
		this.cat = s[0];
		this.text = s[2].split("\\|");
		this.sem = s[3];
		params = new HashMap<String, String>();
		for (String t : s[1].split("\\|")) {
			String[] nv = t.split("=");
			if (nv.length != 2) continue;
			params.put(nv[0], nv[1]);
		}
		for (int i = 0 ; i < 10 ; i++) {
			if (text.length > i) {
				params.put("text" + i, text[i]);
			} else {
				params.put("text" + i, "");
			}
		}
	}
	
	public String getCategory() {
		return cat;
	}
	
	public String getText() {
		return getText(0);
	}
	
	public String getText(int i) {
		return text[i];
	}
	
	public String getSemantics() {
		return sem;
	}
	
	public String getParam(String n) {
		return params.get(n);
	}
	
	public Set<String> getParamNames() {
		return new HashSet<String>(params.keySet());
	}
	
	public int compareTo(LexiconEntry o) {
		return getText().compareTo(o.getText());
	}

}
