package ch.uzh.ifi.attempto.coral;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import au.com.bytecode.opencsv.CSVReader;
import ch.uzh.ifi.attempto.chartparser.AbstractOption;
import ch.uzh.ifi.attempto.chartparser.Category;
import ch.uzh.ifi.attempto.chartparser.DynamicLexicon;
import ch.uzh.ifi.attempto.chartparser.LexicalRule;
import ch.uzh.ifi.attempto.chartparser.Preterminal;

public class CoralLexicon implements DynamicLexicon {
	
	private Map<String, List<LexiconEntry>> catMap = new TreeMap<String, List<LexiconEntry>>();
	private Map<String, List<LexiconEntry>> textMap = new HashMap<String, List<LexiconEntry>>();
	
	public CoralLexicon(String lexFiles) {
		// Predefined entries:
		addEntry(new String[] {"element", "type=node|predef=true", "structure", "node"});
		addEntry(new String[] {"element", "type=leaf|predef=true", "token", "tok"});
		if (lexFiles == null || lexFiles.length() == 0) {
			lexFiles = "base-lexicon.csv,example-lexicon.csv";
		}
		for (String lexFile : lexFiles.split(",")) {
			try {
				InputStream in = getClass().getResourceAsStream(lexFile);
				CSVReader reader = new CSVReader(new InputStreamReader(in), ',', '\'');
			    for (String[] s : reader.readAll()) {
			    	if (s.length >= 4) {
			    		addEntry(s);
			    	}
			    }
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public List<LexiconEntry> getEntriesForCategory(String category) {
		List<LexiconEntry> l = catMap.get(category);
		if (l == null) {
			l = new ArrayList<LexiconEntry>();
			catMap.put(category, l);
		}
		return l;
	}
	
	public List<LexiconEntry> getEntriesForText(String text) {
		List<LexiconEntry> l = textMap.get(text);
		if (l == null) {
			l = new ArrayList<LexiconEntry>();
			textMap.put(text, l);
		}
		return l;
	}
	
	private void addEntry(String[] entryData) {
		LexiconEntry entry = new LexiconEntry(entryData);
		getEntriesForCategory(entry.getCategory()).add(entry);
		Collections.sort(getEntriesForCategory(entry.getCategory()));
		getEntriesForText(entry.getText()).add(entry);
	}

	public List<LexicalRule> getLexRules(AbstractOption option) {
		Category cat = option.getCategory();
		String catName = cat.getName();
		List<LexicalRule> lexRules = new ArrayList<LexicalRule>();
		if (catName.equals("v")) {
			for (LexiconEntry le : getEntriesForCategory("relation")) {
				collectRelationRules(le.getText(), lexRules);
			}
		} else if (catName.equals("article_noun")) {
			for (LexiconEntry le : getEntriesForCategory("element")) {
				collectElementRules("a " + le.getText(), lexRules);
			}
		} else if (catName.equals("ref")) {
			for (LexiconEntry le : getEntriesForCategory("element")) {
				String text = le.getText();
				String string = cat.getFeature("string").getString();
				if (!string.equals("")) {
					text += " \"" + string + "\"";
				}
				String num = cat.getFeature("num").getString();
				if (!num.equals("minus")) {
					text += " (" + num + ")";
				} else  {
					text = "the " + text;
				}
				collectRefRules(text, lexRules);
			}
		} else if (catName.equals("property")) {
			for (LexiconEntry le : getEntriesForCategory("property")) {
				collectPropertyRules("the " + le.getText(), lexRules);
				collectPropertyRules("a " + le.getText(), lexRules);
			}
		} else if (catName.equals("role")) {
			for (LexiconEntry le : getEntriesForCategory("role")) {
				collectRoleRules(le.getText(), lexRules);
			}
		} else if (catName.equals("integer")) {
			for (int i = 1 ; i < 16 ; i++) {
				collectIntegerRules(i + "", lexRules);
			}
		} else if (catName.equals("num")) {
			for (int i = 1 ; i < 10 ; i++) {
				collectNumRules("(" + i + ")", lexRules);
			}
		}
		return lexRules;
	}

	public List<LexicalRule> getLexRules(String word) {
		List<LexicalRule> lexRules = new ArrayList<LexicalRule>();
		collectRefRules(word, lexRules);
		collectElementRules(word, lexRules);
		collectPropertyRules(word, lexRules);
		collectRoleRules(word, lexRules);
		collectNumRules(word, lexRules);
		collectRelationRules(word, lexRules);
		collectStringRules(word, lexRules);
		collectIntegerRules(word, lexRules);
		return lexRules;
	}
	
	private void collectRelationRules(String text, List<LexicalRule> lexRules) {
		for (LexiconEntry le : getEntriesForText(text)) {
			if (!le.getCategory().equals("relation")) continue;
			Preterminal p = new Preterminal("v");
			p.setFeature("sem", le.getSemantics());
			setTypes(p, le);
			lexRules.add(new LexicalRule(p, text));
		}
	}
	
	private void collectElementRules(String text, List<LexicalRule> lexRules) {
		if (text.startsWith("a ") || text.startsWith("an ")) {
			text = text.substring(text.indexOf(" ") + 1);
			for (LexiconEntry le : getEntriesForText(text)) {
				if (!le.getCategory().equals("element")) continue;
				Preterminal p = new Preterminal("article_noun");
				p.setFeature("noun", text);
				p.setFeature("sem", le.getSemantics());
				setTypes(p, le);
				if (useIndefiniteArticleAn(text)) {
					lexRules.add(new LexicalRule(p, "an " + text));
				} else {
					lexRules.add(new LexicalRule(p, "a " + text));
				}
			}
		}
	}

	private void collectRefRules(String text, List<LexicalRule> lexRules) {
		String pre = "";
		if (text.startsWith("the ")) {
			pre = "the ";
			text = text.substring(4);
		}
		String num = "minus";
		if (text.matches(".* \\([0-9]+\\)")) {
			num = text.replaceFirst("^.* \\(([0-9]+)\\)$", "$1");
			text = text.replaceFirst("^(.*) \\([0-9]+\\)$", "$1");
		}
		String string = "";
		if (text.matches(".* \"[^\"]*\"")) {
			string = text.replaceFirst("^.* \"([^\"]*)\"$", "$1");
			text = text.replaceFirst("^(.*) \"[^\"]*\"$", "$1");
		}
		for (LexiconEntry le : getEntriesForText(text)) {
			if (!le.getCategory().equals("element")) continue;
			if ("0".equals(num)) num = "minus";
			Preterminal p = new Preterminal("ref");
			p.setFeature("string", string);
			p.setFeature("num", num);
			p.setFeature("noun", text);
			setTypes(p, le);
			String t = pre + text;
			if (!"".equals(string)) {
				t += " \"" + string + "\"";
			}
			if (!"minus".equals(num)) {
				t += " (" + num + ")";
			}
			lexRules.add(new LexicalRule(p, t));
		}
	}
	
	private void collectPropertyRules(String text, List<LexicalRule> lexRules) {
		String general = null;
		if (text.startsWith("a ") || text.startsWith("an ")) {
			text = text.substring(text.indexOf(" ") + 1);
			general = "plus";
		} else if (text.startsWith("the ")) {
			text = text.substring(text.indexOf(" ") + 1);
			general = "minus";
		}
		if (general != null) {
			for (LexiconEntry le : getEntriesForText(text)) {
				if (!le.getCategory().equals("property")) continue;
				Preterminal p = new Preterminal("property");
				p.setFeature("general", general);
				p.setFeature("sem", le.getSemantics());
				setTypes(p, le);
				if (general.equals("minus")) {
					lexRules.add(new LexicalRule(p, "the " + text));
				} else if (useIndefiniteArticleAn(text)) {
					lexRules.add(new LexicalRule(p, "an " + text));
				} else {
					lexRules.add(new LexicalRule(p, "a " + text));
				}
			}
		}
	}
	
	private void collectRoleRules(String text, List<LexicalRule> lexRules) {
		for (LexiconEntry le : getEntriesForText(text)) {
			if (!le.getCategory().equals("role")) continue;
			Preterminal p = new Preterminal("role");
			p.setFeature("sem", le.getSemantics());
			setTypes(p, le);
			lexRules.add(new LexicalRule(p, text));
		}
	}
	
	private void collectNumRules(String text, List<LexicalRule> lexRules) {
		if (text.matches("\\([0-9]+\\)")) {
			Preterminal p = new Preterminal("num");
			p.setFeature("num", text.replaceFirst("^\\(([0-9]+)\\)$", "$1"));
			lexRules.add(new LexicalRule(p, text));
		}
	}
	
	private void collectStringRules(String text, List<LexicalRule> lexRules) {
		if (text.matches("\"[^\"]+\"")) {
			Preterminal p = new Preterminal("string");
			p.setFeature("text", text.replaceFirst("^\"(.*)\"$", "$1"));
			lexRules.add(new LexicalRule(p, text));
		}
	}
	
	private void collectIntegerRules(String text, List<LexicalRule> lexRules) {
		if (text.matches("[0-9]+")) {
			Preterminal p = new Preterminal("integer");
			p.setFeature("text", text);
			lexRules.add(new LexicalRule(p, text));
		}
	}
	
	private void setTypes(Preterminal p, LexiconEntry le) {
		for (String n : le.getParamNames()) {
			p.setFeature(n, le.getParam(n));
		}
	}
	
	public static boolean useIndefiniteArticleAn(String word) {
		if (word == null || word.equals("")) return false;
		boolean an = false;
		word = word.toLowerCase();
		if (word.matches("[aeiou].*")) an = true;
		if (word.matches("[fhlmnrsx]")) an = true;
		if (word.matches("[fhlmnrsx]-.*")) an = true;
		if (word.equals("u")) an = false;
		if (word.matches("u-.*")) an = false;
		if (word.matches("u[rtn]i.*")) an = false;
		if (word.matches("use.*")) an = false;
		if (word.matches("uk.*")) an = false;
		return an;
	}

}
