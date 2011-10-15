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
