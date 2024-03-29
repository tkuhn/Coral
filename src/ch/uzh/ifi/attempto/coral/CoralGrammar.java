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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ch.uzh.ifi.attempto.chartparser.Annotation;
import ch.uzh.ifi.attempto.chartparser.Nonterminal;
import ch.uzh.ifi.attempto.chartparser.GrammarRule;
import ch.uzh.ifi.attempto.chartparser.LexicalRule;
import ch.uzh.ifi.attempto.chartparser.StringRef;
import ch.uzh.ifi.attempto.chartparser.Terminal;
import ch.uzh.ifi.attempto.chartparser.Preterminal;
import ch.uzh.ifi.attempto.chartparser.Category;
import ch.uzh.ifi.attempto.chartparser.BackrefCategory;
import ch.uzh.ifi.attempto.chartparser.FeatureMap;

/**
 * This grammar class is automatically generated on the basis of a file in Codeco notation.
 *<p>
 * For more information, see the Codeco package {@link ch.uzh.ifi.attempto.codeco} of the
 * <a href="http://attempto.ifi.uzh.ch/acewiki/" target="_top">AceWiki</a> system and the thesis
 * "<a href="http://attempto.ifi.uzh.ch/site/pubs/papers/doctoral_thesis_kuhn.pdf">Controlled
 * English for Knowledge Representation</a>".
 */
@SuppressWarnings("all")
public class CoralGrammar extends ch.uzh.ifi.attempto.chartparser.Grammar {

	public static final CoralGrammar grammar = new CoralGrammar();
	
	/**
	 * Creates a new grammar object.
	 */
	public CoralGrammar() {
		List<Category> l = new ArrayList<Category>();
		Terminal term;
		Nonterminal nonterm;
		Preterminal preterm;
		BackrefCategory brefcat;
		FeatureMap fm;
		HashMap<Integer, StringRef> featureHash = new HashMap<Integer, StringRef>();
		Annotation ann;
		
		// {sem:lam(A, A)}:query~>conditions
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), getStringRef(0, featureHash)});
		nonterm = new Nonterminal("query");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("conditions");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, true));
		
		// {sem:lam(A, A)}:conditions=>condition
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), getStringRef(0, featureHash)});
		nonterm = new Nonterminal("conditions");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("condition");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, lam(B, A+' & '+B))}:conditions=>condition, conditions
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"lam", getStringRef(1, featureHash), new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), " & "}, getStringRef(1, featureHash)}}});
		nonterm = new Nonterminal("conditions");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("condition");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("conditions");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, A)}:condition=>['there is'], n, $sentence_delimiter
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), getStringRef(0, featureHash)});
		nonterm = new Nonterminal("condition");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("there is");
		l.add(term);
		nonterm = new Nonterminal("n");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("sentence_delimiter");
		fm = new FeatureMap();
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, lam(B, A+B))}:condition=>np(id:C, type:D), vp_coord(id:C, domain:D), $sentence_delimiter
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"lam", getStringRef(1, featureHash), new Object[] {"+", getStringRef(0, featureHash), getStringRef(1, featureHash)}}});
		nonterm = new Nonterminal("condition");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("np");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "type", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("vp_coord");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "domain", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("sentence_delimiter");
		fm = new FeatureMap();
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, A+' & ')}:np(id:B, type:C, rel:D, def:minus)=>n(id:B, type:C, rel:D)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", getStringRef(0, featureHash), " & "}});
		nonterm = new Nonterminal("np");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "type", 2, featureHash);
		setFeature(fm, "rel", 3, featureHash);
		fm.setFeature("def", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("n");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "type", 2, featureHash);
		setFeature(fm, "rel", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:''}:np(id:A, type:B, rel:minus, def:plus)=> $ref(noun:C, string:D, num:E, id:A, type:B), <(noun:C, string:D, num:E, id:A)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", "");
		nonterm = new Nonterminal("np");
		fm = new FeatureMap();
		setFeature(fm, "id", 0, featureHash);
		setFeature(fm, "type", 1, featureHash);
		fm.setFeature("rel", new StringRef("minus"));
		fm.setFeature("def", new StringRef("plus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("ref");
		fm = new FeatureMap();
		setFeature(fm, "noun", 2, featureHash);
		setFeature(fm, "string", 3, featureHash);
		setFeature(fm, "num", 4, featureHash);
		setFeature(fm, "id", 0, featureHash);
		setFeature(fm, "type", 1, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		brefcat = new BackrefCategory();
		fm = new FeatureMap();
		setFeature(fm, "noun", 2, featureHash);
		setFeature(fm, "string", 3, featureHash);
		setFeature(fm, "num", 4, featureHash);
		setFeature(fm, "id", 0, featureHash);
		brefcat.addPosFeatureMap(fm);
		l.add(brefcat);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, lam(B, lam(C, lam(D, A+B+C+D))))}:n(id:E, type:F, rel:G)=>el(id:E, string:H, type:F), opt_bare_value(id:E, string:H, type:F), opt_properties(id:E, domain:F), rel(id:E, domain:F, rel:G)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"lam", getStringRef(1, featureHash), new Object[] {"lam", getStringRef(2, featureHash), new Object[] {"lam", getStringRef(3, featureHash), new Object[] {"+", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), getStringRef(1, featureHash)}, getStringRef(2, featureHash)}, getStringRef(3, featureHash)}}}}});
		nonterm = new Nonterminal("n");
		fm = new FeatureMap();
		setFeature(fm, "id", 4, featureHash);
		setFeature(fm, "type", 5, featureHash);
		setFeature(fm, "rel", 6, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("el");
		fm = new FeatureMap();
		setFeature(fm, "id", 4, featureHash);
		setFeature(fm, "string", 7, featureHash);
		setFeature(fm, "type", 5, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("opt_bare_value");
		fm = new FeatureMap();
		setFeature(fm, "id", 4, featureHash);
		setFeature(fm, "string", 7, featureHash);
		setFeature(fm, "type", 5, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("opt_properties");
		fm = new FeatureMap();
		setFeature(fm, "id", 4, featureHash);
		setFeature(fm, "domain", 5, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("rel");
		fm = new FeatureMap();
		setFeature(fm, "id", 4, featureHash);
		setFeature(fm, "domain", 5, featureHash);
		setFeature(fm, "rel", 6, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem: @ + A+' '+B}:el(id:A, type:C, noun:D, string:'', num:E)=> #A, $article_noun(noun:D, id:A, sem:B, type:C), opt_num(num:E), >(noun:D, string:'', num:E, id:A)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", "@", getStringRef(0, featureHash)}, " "}, getStringRef(1, featureHash)});
		nonterm = new Nonterminal("el");
		fm = new FeatureMap();
		setFeature(fm, "id", 0, featureHash);
		setFeature(fm, "type", 2, featureHash);
		setFeature(fm, "noun", 3, featureHash);
		fm.setFeature("string", new StringRef(""));
		setFeature(fm, "num", 4, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("#");
		fm = new FeatureMap();
		setFeature(fm, "pos", 0, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("article_noun");
		fm = new FeatureMap();
		setFeature(fm, "noun", 3, featureHash);
		setFeature(fm, "id", 0, featureHash);
		setFeature(fm, "sem", 1, featureHash);
		setFeature(fm, "type", 2, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		nonterm = new Nonterminal("opt_num");
		fm = new FeatureMap();
		setFeature(fm, "num", 4, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal(">");
		fm = new FeatureMap();
		setFeature(fm, "noun", 3, featureHash);
		fm.setFeature("string", new StringRef(""));
		setFeature(fm, "num", 4, featureHash);
		setFeature(fm, "id", 0, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, @ + B+' '+C+' & '+A+' & #'+B+' _=_ #'+D)}:el(id:B, type:leaf, noun:E, string:F, num:G)=> #B, $article_noun(noun:E, id:B, sem:C, type:leaf), token(text:F, id:D), opt_num(num:G), >(noun:E, string:F, num:G, id:B)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", "@", getStringRef(1, featureHash)}, " "}, getStringRef(2, featureHash)}, " & "}, getStringRef(0, featureHash)}, " & #"}, getStringRef(1, featureHash)}, " _=_ #"}, getStringRef(3, featureHash)}});
		nonterm = new Nonterminal("el");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		fm.setFeature("type", new StringRef("leaf"));
		setFeature(fm, "noun", 4, featureHash);
		setFeature(fm, "string", 5, featureHash);
		setFeature(fm, "num", 6, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("#");
		fm = new FeatureMap();
		setFeature(fm, "pos", 1, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("article_noun");
		fm = new FeatureMap();
		setFeature(fm, "noun", 4, featureHash);
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "sem", 2, featureHash);
		fm.setFeature("type", new StringRef("leaf"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		nonterm = new Nonterminal("token");
		fm = new FeatureMap();
		setFeature(fm, "text", 5, featureHash);
		setFeature(fm, "id", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("opt_num");
		fm = new FeatureMap();
		setFeature(fm, "num", 6, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal(">");
		fm = new FeatureMap();
		setFeature(fm, "noun", 4, featureHash);
		setFeature(fm, "string", 5, featureHash);
		setFeature(fm, "num", 6, featureHash);
		setFeature(fm, "id", 1, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, A)}:el(id:B, type:leaf, noun:'', string:C, num:D)=>token(text:C, id:B), opt_num(num:D), >(noun:token, string:C, num:minus, id:B)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), getStringRef(0, featureHash)});
		nonterm = new Nonterminal("el");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		fm.setFeature("type", new StringRef("leaf"));
		fm.setFeature("noun", new StringRef(""));
		setFeature(fm, "string", 2, featureHash);
		setFeature(fm, "num", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("token");
		fm = new FeatureMap();
		setFeature(fm, "text", 2, featureHash);
		setFeature(fm, "id", 1, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("opt_num");
		fm = new FeatureMap();
		setFeature(fm, "num", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal(">");
		fm = new FeatureMap();
		fm.setFeature("noun", new StringRef("token"));
		setFeature(fm, "string", 2, featureHash);
		fm.setFeature("num", new StringRef("minus"));
		setFeature(fm, "id", 1, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem: @ + A+' "'+B+'"'}:token(text:B, id:A)=> #A, $string(text:B, type:token)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", "@", getStringRef(0, featureHash)}, " \""}, getStringRef(1, featureHash)}, "\""});
		nonterm = new Nonterminal("token");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		setFeature(fm, "id", 0, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("#");
		fm = new FeatureMap();
		setFeature(fm, "pos", 0, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		fm.setFeature("type", new StringRef("token"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:''}:opt_properties=>[]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", "");
		nonterm = new Nonterminal("opt_properties");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, A)}:opt_properties(id:B, domain:C)=>[with], properties(id:B, domain:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), getStringRef(0, featureHash)});
		nonterm = new Nonterminal("opt_properties");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("with");
		l.add(term);
		nonterm = new Nonterminal("properties");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, ' & '+A)}:properties(id:B, domain:C)=>property(id:B, domain:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", " & ", getStringRef(0, featureHash)}});
		nonterm = new Nonterminal("properties");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, lam(B, ' & '+A+B))}:properties(id:C, domain:D)=>property(id:C, domain:D), ['and with'], properties(id:C, domain:D)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"lam", getStringRef(1, featureHash), new Object[] {"+", new Object[] {"+", " & ", getStringRef(0, featureHash)}, getStringRef(1, featureHash)}}});
		nonterm = new Nonterminal("properties");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "domain", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "domain", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("and with");
		l.add(term);
		nonterm = new Nonterminal("properties");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "domain", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem: # + A+':arity='+B}:property(id:A, domain:node)=>[arity], $integer(text:B)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", "#", getStringRef(0, featureHash)}, ":arity="}, getStringRef(1, featureHash)});
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 0, featureHash);
		fm.setFeature("domain", new StringRef("node"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("arity");
		l.add(term);
		preterm = new Preterminal("integer");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem: # + A+':tokenarity='+B}:property(id:A, domain:node)=>[length], $integer(text:B)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", "#", getStringRef(0, featureHash)}, ":tokenarity="}, getStringRef(1, featureHash)});
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 0, featureHash);
		fm.setFeature("domain", new StringRef("node"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("length");
		l.add(term);
		preterm = new Preterminal("integer");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem: @ + A+' tok="'+B+'" & #'+C+' _=_ #'+A}:property(id:C, domain:leaf)=> #A, ['the text'], $string(text:B, type:propval)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", "@", getStringRef(0, featureHash)}, " tok=\""}, getStringRef(1, featureHash)}, "\" & #"}, getStringRef(2, featureHash)}, " _=_ #"}, getStringRef(0, featureHash)});
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		fm.setFeature("domain", new StringRef("leaf"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("#");
		fm = new FeatureMap();
		setFeature(fm, "pos", 0, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("the text");
		l.add(term);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		fm.setFeature("type", new StringRef("propval"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, @ + B+' '+A+' & #'+C+' _=_ #'+B)}:property(id:C, domain:leaf)=>['a text'], #B, value(prop:tok, type:propval)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", "@", getStringRef(1, featureHash)}, " "}, getStringRef(0, featureHash)}, " & #"}, getStringRef(2, featureHash)}, " _=_ #"}, getStringRef(1, featureHash)}});
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		fm.setFeature("domain", new StringRef("leaf"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("a text");
		l.add(term);
		nonterm = new Nonterminal("#");
		fm = new FeatureMap();
		setFeature(fm, "pos", 1, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		fm.setFeature("prop", new StringRef("tok"));
		fm.setFeature("type", new StringRef("propval"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem: @ + A+' '+B+'="'+C+'" & #'+D+' _=_ #'+A}:property(id:D, domain:E)=> #A, $property(sem:B, domain:E, general:minus), $string(text:C, type:propval)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", "@", getStringRef(0, featureHash)}, " "}, getStringRef(1, featureHash)}, "=\""}, getStringRef(2, featureHash)}, "\" & #"}, getStringRef(3, featureHash)}, " _=_ #"}, getStringRef(0, featureHash)});
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 3, featureHash);
		setFeature(fm, "domain", 4, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("#");
		fm = new FeatureMap();
		setFeature(fm, "pos", 0, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "sem", 1, featureHash);
		setFeature(fm, "domain", 4, featureHash);
		fm.setFeature("general", new StringRef("minus"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 2, featureHash);
		fm.setFeature("type", new StringRef("propval"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, @ + B+' '+A+' & #'+C+' _=_ #'+B)}:property(id:C, domain:D)=> $property(sem:E, domain:D, general:plus), #B, value(prop:E, type:propval)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", "@", getStringRef(1, featureHash)}, " "}, getStringRef(0, featureHash)}, " & #"}, getStringRef(2, featureHash)}, " _=_ #"}, getStringRef(1, featureHash)}});
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "domain", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "sem", 4, featureHash);
		setFeature(fm, "domain", 3, featureHash);
		fm.setFeature("general", new StringRef("plus"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		nonterm = new Nonterminal("#");
		fm = new FeatureMap();
		setFeature(fm, "pos", 1, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		setFeature(fm, "prop", 4, featureHash);
		fm.setFeature("type", new StringRef("propval"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, @ + B+' '+A+' & #'+C+' _=_ #'+B)}:property(id:C)=>['an attribute'], $string(text:D, type:propname), #B, value(prop:D, type:propval)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", "@", getStringRef(1, featureHash)}, " "}, getStringRef(0, featureHash)}, " & #"}, getStringRef(2, featureHash)}, " _=_ #"}, getStringRef(1, featureHash)}});
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("an attribute");
		l.add(term);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 3, featureHash);
		fm.setFeature("type", new StringRef("propname"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		nonterm = new Nonterminal("#");
		fm = new FeatureMap();
		setFeature(fm, "pos", 1, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		setFeature(fm, "prop", 3, featureHash);
		fm.setFeature("type", new StringRef("propval"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:''}:opt_bare_value=>[]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", "");
		nonterm = new Nonterminal("opt_bare_value");
		fm = new FeatureMap();
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, ' & @'+B+' '+A+' & #'+C+' _=_ #'+B)}:opt_bare_value(id:C, string:'', type:leaf)=> #B, value(prop:tok, type:token)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", " & @", getStringRef(1, featureHash)}, " "}, getStringRef(0, featureHash)}, " & #"}, getStringRef(2, featureHash)}, " _=_ #"}, getStringRef(1, featureHash)}});
		nonterm = new Nonterminal("opt_bare_value");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		fm.setFeature("string", new StringRef(""));
		fm.setFeature("type", new StringRef("leaf"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("#");
		fm = new FeatureMap();
		setFeature(fm, "pos", 1, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		fm.setFeature("prop", new StringRef("tok"));
		fm.setFeature("type", new StringRef("token"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A+'="'+B+'"'}:value(prop:A, type:C)=>['of value'], $string(text:B, type:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), "=\""}, getStringRef(1, featureHash)}, "\""});
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		setFeature(fm, "prop", 0, featureHash);
		setFeature(fm, "type", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("of value");
		l.add(term);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		setFeature(fm, "type", 2, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A+'="'+B+'"'}:value(prop:A, type:C)=>[that, 'has the value'], $string(text:B, type:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), "=\""}, getStringRef(1, featureHash)}, "\""});
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		setFeature(fm, "prop", 0, featureHash);
		setFeature(fm, "type", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("that");
		l.add(term);
		term = new Terminal("has the value");
		l.add(term);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		setFeature(fm, "type", 2, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A+'!="'+B+'"'}:value(prop:A, type:C)=>[that, 'does not have the value'], $string(text:B, type:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), "!=\""}, getStringRef(1, featureHash)}, "\""});
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		setFeature(fm, "prop", 0, featureHash);
		setFeature(fm, "type", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("that");
		l.add(term);
		term = new Terminal("does not have the value");
		l.add(term);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		setFeature(fm, "type", 2, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A+ =/ + B+ /}:value(prop:A, type:C)=>[that, matches], $string(text:B, type:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), "=/"}, getStringRef(1, featureHash)}, "/"});
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		setFeature(fm, "prop", 0, featureHash);
		setFeature(fm, "type", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("that");
		l.add(term);
		term = new Terminal("matches");
		l.add(term);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		setFeature(fm, "type", 2, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A+'!=/'+B+ /}:value(prop:A, type:C)=>[that, 'does not match'], $string(text:B, type:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), "!=/"}, getStringRef(1, featureHash)}, "/"});
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		setFeature(fm, "prop", 0, featureHash);
		setFeature(fm, "type", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("that");
		l.add(term);
		term = new Terminal("does not match");
		l.add(term);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		setFeature(fm, "type", 2, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:''}:rel(rel:minus)=>[]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", "");
		nonterm = new Nonterminal("rel");
		fm = new FeatureMap();
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, ' & '+A)}:rel(id:B, domain:C, rel:plus)=>[that], vp(id:B, domain:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", " & ", getStringRef(0, featureHash)}});
		nonterm = new Nonterminal("rel");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		fm.setFeature("rel", new StringRef("plus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("that");
		l.add(term);
		nonterm = new Nonterminal("vp");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, A)}:vp_coord(id:B, domain:C)=>vp(id:B, domain:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), getStringRef(0, featureHash)});
		nonterm = new Nonterminal("vp_coord");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("vp");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, lam(B, A+' & '+B))}:vp_coord(id:C, domain:D)=>vp(id:C, domain:D), [and], vp_coord(id:C, domain:D)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"lam", getStringRef(1, featureHash), new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), " & "}, getStringRef(1, featureHash)}}});
		nonterm = new Nonterminal("vp_coord");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "domain", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("vp");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "domain", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("and");
		l.add(term);
		nonterm = new Nonterminal("vp_coord");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "domain", 3, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, A)}:vp(id:B, domain:C)=>[has], property(id:B, domain:C)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), getStringRef(0, featureHash)});
		nonterm = new Nonterminal("vp");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("has");
		l.add(term);
		nonterm = new Nonterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "id", 1, featureHash);
		setFeature(fm, "domain", 2, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, lam(B, A+' #'+C+' '+D+B+' #'+E))}:vp(id:C, domain:F)=> $v(type:G, dir:forward, sem:D, domain:F, range:H), np(id:E, type:H, rel:I), modifier(type:G, rel:I)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"lam", getStringRef(1, featureHash), new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), " #"}, getStringRef(2, featureHash)}, " "}, getStringRef(3, featureHash)}, getStringRef(1, featureHash)}, " #"}, getStringRef(4, featureHash)}}});
		nonterm = new Nonterminal("vp");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "domain", 5, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("v");
		fm = new FeatureMap();
		setFeature(fm, "type", 6, featureHash);
		fm.setFeature("dir", new StringRef("forward"));
		setFeature(fm, "sem", 3, featureHash);
		setFeature(fm, "domain", 5, featureHash);
		setFeature(fm, "range", 7, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		nonterm = new Nonterminal("np");
		fm = new FeatureMap();
		setFeature(fm, "id", 4, featureHash);
		setFeature(fm, "type", 7, featureHash);
		setFeature(fm, "rel", 8, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		setFeature(fm, "type", 6, featureHash);
		setFeature(fm, "rel", 8, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, lam(B, A+' #'+C+' '+D+B+' #'+E))}:vp(id:E, domain:F)=> $v(type:G, dir:backward, sem:D, domain:F, range:H), np(id:C, type:H, rel:I), modifier(type:G, rel:I)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"lam", getStringRef(1, featureHash), new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), " #"}, getStringRef(2, featureHash)}, " "}, getStringRef(3, featureHash)}, getStringRef(1, featureHash)}, " #"}, getStringRef(4, featureHash)}}});
		nonterm = new Nonterminal("vp");
		fm = new FeatureMap();
		setFeature(fm, "id", 4, featureHash);
		setFeature(fm, "domain", 5, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("v");
		fm = new FeatureMap();
		setFeature(fm, "type", 6, featureHash);
		fm.setFeature("dir", new StringRef("backward"));
		setFeature(fm, "sem", 3, featureHash);
		setFeature(fm, "domain", 5, featureHash);
		setFeature(fm, "range", 7, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		nonterm = new Nonterminal("np");
		fm = new FeatureMap();
		setFeature(fm, "id", 2, featureHash);
		setFeature(fm, "type", 7, featureHash);
		setFeature(fm, "rel", 8, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		setFeature(fm, "type", 6, featureHash);
		setFeature(fm, "rel", 8, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:''}:modifier(type:normal)=>[]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", "");
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("normal"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A}:modifier(type:hasrole, rel:minus)=>[as], $role(sem:A)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", getStringRef(0, featureHash));
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("hasrole"));
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("as");
		l.add(term);
		preterm = new Preterminal("role");
		fm = new FeatureMap();
		setFeature(fm, "sem", 0, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A+ (',')+A}:modifier(type:hasdist, rel:minus)=>['at a distance of'], $integer(text:A), [tokens]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), ","}, getStringRef(0, featureHash)});
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("hasdist"));
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("at a distance of");
		l.add(term);
		preterm = new Preterminal("integer");
		fm = new FeatureMap();
		setFeature(fm, "text", 0, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		term = new Terminal("tokens");
		l.add(term);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A+ (',')+B}:modifier(type:hasdist, rel:minus)=>['at a distance of'], $integer(text:A), [to], $integer(text:B), [tokens]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), ","}, getStringRef(1, featureHash)});
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("hasdist"));
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("at a distance of");
		l.add(term);
		preterm = new Preterminal("integer");
		fm = new FeatureMap();
		setFeature(fm, "text", 0, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		term = new Terminal("to");
		l.add(term);
		preterm = new Preterminal("integer");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		term = new Terminal("tokens");
		l.add(term);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A+ (',')+A}:modifier(type:hasdepth, rel:minus)=>['at a depth of'], $integer(text:A), [structures]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), ","}, getStringRef(0, featureHash)});
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("hasdepth"));
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("at a depth of");
		l.add(term);
		preterm = new Preterminal("integer");
		fm = new FeatureMap();
		setFeature(fm, "text", 0, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		term = new Terminal("structures");
		l.add(term);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A+ (',')+B}:modifier(type:hasdepth, rel:minus)=>['at a depth of'], $integer(text:A), [to], $integer(text:B), [structures]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", getStringRef(0, featureHash), ","}, getStringRef(1, featureHash)});
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("hasdepth"));
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("at a depth of");
		l.add(term);
		preterm = new Preterminal("integer");
		fm = new FeatureMap();
		setFeature(fm, "text", 0, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		term = new Terminal("to");
		l.add(term);
		preterm = new Preterminal("integer");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		term = new Terminal("structures");
		l.add(term);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:A}:modifier(type:hasprop, rel:minus)=>[via], ['the relation type'], $string(text:A, type:relationtype)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", getStringRef(0, featureHash));
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("hasprop"));
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("via");
		l.add(term);
		term = new Terminal("the relation type");
		l.add(term);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 0, featureHash);
		fm.setFeature("type", new StringRef("relationtype"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:'['+A+'="'+B+'"'+']'}:modifier(type:hasprop, rel:minus)=>[via], $property(sem:A, domain:relation, general:minus), $string(text:B, type:propval)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", new Object[] {"+", "[", getStringRef(0, featureHash)}, "=\""}, getStringRef(1, featureHash)}, "\""}, "]"});
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("hasprop"));
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("via");
		l.add(term);
		preterm = new Preterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "sem", 0, featureHash);
		fm.setFeature("domain", new StringRef("relation"));
		fm.setFeature("general", new StringRef("minus"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		fm.setFeature("type", new StringRef("propval"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, '['+A+']')}:modifier(type:hasprop, rel:minus)=>[via], $property(sem:B, domain:relation, general:plus), value(prop:B, type:propval)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", new Object[] {"+", "[", getStringRef(0, featureHash)}, "]"}});
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("hasprop"));
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("via");
		l.add(term);
		preterm = new Preterminal("property");
		fm = new FeatureMap();
		setFeature(fm, "sem", 1, featureHash);
		fm.setFeature("domain", new StringRef("relation"));
		fm.setFeature("general", new StringRef("plus"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		setFeature(fm, "prop", 1, featureHash);
		fm.setFeature("type", new StringRef("propval"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// {sem:lam(A, '['+A+']')}:modifier(type:hasprop, rel:minus)=>[via], ['an attribute'], $string(text:B, type:propname), value(prop:B, type:propval)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		ann.setItem("sem", new Object[] {"lam", getStringRef(0, featureHash), new Object[] {"+", new Object[] {"+", "[", getStringRef(0, featureHash)}, "]"}});
		nonterm = new Nonterminal("modifier");
		fm = new FeatureMap();
		fm.setFeature("type", new StringRef("hasprop"));
		fm.setFeature("rel", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		term = new Terminal("via");
		l.add(term);
		term = new Terminal("an attribute");
		l.add(term);
		preterm = new Preterminal("string");
		fm = new FeatureMap();
		setFeature(fm, "text", 1, featureHash);
		fm.setFeature("type", new StringRef("propname"));
		preterm.setFeatureMap(fm);
		l.add(preterm);
		nonterm = new Nonterminal("value");
		fm = new FeatureMap();
		setFeature(fm, "prop", 1, featureHash);
		fm.setFeature("type", new StringRef("propval"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// opt_num(num:minus)=>[]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		nonterm = new Nonterminal("opt_num");
		fm = new FeatureMap();
		fm.setFeature("num", new StringRef("minus"));
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// opt_num(num:A)=> $num(num:A), /<(num:A)
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		nonterm = new Nonterminal("opt_num");
		fm = new FeatureMap();
		setFeature(fm, "num", 0, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		preterm = new Preterminal("num");
		fm = new FeatureMap();
		setFeature(fm, "num", 0, featureHash);
		preterm.setFeatureMap(fm);
		l.add(preterm);
		nonterm = new Nonterminal("/<");
		fm = new FeatureMap();
		setFeature(fm, "num", 0, featureHash);
		nonterm.setFeatureMap(fm);
		l.add(nonterm);
		addGrammarRule(new GrammarRule(ann, l, false));
		
		// $sentence_delimiter=>[;]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		preterm = new Preterminal("sentence_delimiter");
		fm = new FeatureMap();
		preterm.setFeatureMap(fm);
		l.add(preterm);
		term = new Terminal(";");
		l.add(term);
		addLexicalRule(new LexicalRule(ann, l));
		
		// $sentence_delimiter=>[and]
		l.clear();
		featureHash.clear();
		ann = new Annotation();
		preterm = new Preterminal("sentence_delimiter");
		fm = new FeatureMap();
		preterm.setFeatureMap(fm);
		l.add(preterm);
		term = new Terminal("and");
		l.add(term);
		addLexicalRule(new LexicalRule(ann, l));

	}
}
