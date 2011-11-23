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
import java.util.List;

import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import ch.uzh.ifi.attempto.chartparser.Category;
import ch.uzh.ifi.attempto.chartparser.ChartParser;
import ch.uzh.ifi.attempto.chartparser.Nonterminal;
import ch.uzh.ifi.attempto.chartparser.ParseTree;
import ch.uzh.ifi.attempto.chartparser.ParseTreeNode;
import ch.uzh.ifi.attempto.echocomp.MessageWindow;
import ch.uzh.ifi.attempto.preditor.PreditorWindow;

public class CoralPreditor implements ActionListener {
	
	private static final long serialVersionUID = -8740818073236660213L;
	
	private StatementItem item;
	private long itemId = -1;
	private int mode;
	private QueryEntry ruleEntry;
	private PreditorWindow preditor;
	private ChartParser parser;
	
	public CoralPreditor(StatementItem item, int mode, QueryEntry ruleEntry) {
		this.item = item;
		this.mode = mode;
		this.ruleEntry = ruleEntry;
		
		String title;
		List<Nonterminal> context = null;
		
		if (mode == 0) {
			title = "Edit Text";
			itemId = item.getId();
			context = ruleEntry.getContext(item);
		} else {
			title = "Add Text";
			context = ruleEntry.getContext(null);
		}
		
		parser = new ChartParser(ruleEntry.getGrammar(), "query", context);
		parser.setDynamicLexicon(ruleEntry.getLexicon());
		preditor = new PreditorWindow(title, parser);
		preditor.setMenuCreator(new CoralMenuCreator(this));
		preditor.setTextOperator(new CoralTextOperator());
		if (mode == 0) {
			String text = item.getText();
			text = text.substring(0, text.length()-2);
			preditor.addText(text);
		}
		preditor.addActionListener(this);
		ruleEntry.getOwner().showWindow(preditor);
	}
	
	public PreditorWindow getWindow() {
		return preditor;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().matches("OK|Enter")) {
			if (preditor.getTokenCount() == 0) {
				ruleEntry.getOwner().removeWindow(preditor);
			} else {
				if (preditor.isPossibleNextToken(";")) {
					preditor.addText("; ");
				}
				ParseTree pt = parser.getParseTree();
				if (pt == null) {
					pt = parser.getParseTree("np");
				}
				if (pt != null) {
					if (mode == 0) {
						ruleEntry.editItems(item, createStatementItems(pt.getTopNode()));
					} else if (mode == -1) {
						ruleEntry.addItems(item, 0, createStatementItems(pt.getTopNode()));
					} else if (mode == 1) {
						ruleEntry.addItems(item, 1, createStatementItems(pt.getTopNode()));
					}
					ruleEntry.getOwner().removeWindow(preditor);
				} else if (e.getActionCommand().equals("OK")) {
					ruleEntry.getOwner().showWindow(new MessageWindow(
							"Error",
							"There are unfinished statements.",
							"OK"
						));
				}
			}
		} else {
			ruleEntry.getOwner().removeWindow(preditor);
			ruleEntry.update();
		}
	}
	
	public CoralGUI getGUI() {
		return ruleEntry.getOwner();
	}
	
	private List<StatementItem> createStatementItems(ParseTreeNode n) {
		List<StatementItem> l = new ArrayList<StatementItem>();
		List<ParseTreeNode> sentences = new ArrayList<ParseTreeNode>();
		String topNodeName = n.getCategory().getName();
		if (topNodeName.equals("np")) {
			StatementItem si = new StatementItem(ruleEntry);
			si.addElement(new SimpleElement("there is", si));
			si.addElements(n);
			si.addElement(new SimpleElement(";", si));
			l.add(si);
		} else {
			collectSentences(n, sentences);
			long itemId = this.itemId;
			for (ParseTreeNode c : sentences) {
				l.add(new StatementItem(c, itemId, ruleEntry));
				itemId = -1;
			}
		}
		return l;
	}
	
	private void collectSentences(ParseTreeNode node, List<ParseTreeNode> sentences) {
		Category h = node.getCategory();
		if (h instanceof Nonterminal) {
			if (h.getName().equals("condition")) {
				sentences.add(node);
			} else {
				for (ParseTreeNode c : node.getChildren()) {
					collectSentences(c, sentences);
				}
			}
		}
	}

}
