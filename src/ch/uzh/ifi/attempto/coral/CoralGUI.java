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

import java.util.Map;

import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;
import nextapp.echo.app.ResourceImageReference;
import nextapp.echo.app.Row;
import nextapp.echo.app.Window;
import nextapp.echo.app.WindowPane;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import ch.uzh.ifi.attempto.echocomp.HSpace;
import ch.uzh.ifi.attempto.echocomp.Label;
import ch.uzh.ifi.attempto.echocomp.SquareButton;
import ch.uzh.ifi.attempto.echocomp.Style;

public class CoralGUI extends Window implements ActionListener {

	private static final long serialVersionUID = 2729851862529116604L;
	
	private Map<String, String> parameters;
	
	private Column mainColumn;
	private Column ruleEntriesColumn;
	private SquareButton addRuleButton;
	private QueryEntry selectedEntry;
	private CoralLexicon lexicon;

	public CoralGUI(Map<String, String> parameters) {
		this.parameters = parameters;
		setTitle("Coral");
		lexicon = new CoralLexicon(getParameter("lexicons"));
		mainColumn = new Column();
		Row titleRow = new Row();
		titleRow.setBackground(Style.mediumBackground);
		titleRow.setInsets(new Insets(20, 10, 0, 0));
		titleRow.add(new Label(new ResourceImageReference("ch/uzh/ifi/attempto/coral/coral.png")));
		titleRow.add(new HSpace(25));
		Column titleColumn = new Column();
		Label titleLabel = new Label("Coral");
		titleLabel.setLineWrap(false);
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, new Extent(40)));
		titleLabel.setForeground(Color.WHITE);
		titleColumn.add(titleLabel);
		Label subtitleLabel = new Label("Corpus Access in Controlled Language");
		subtitleLabel.setLineWrap(false);
		subtitleLabel.setFont(new Font(Font.VERDANA, Font.BOLD, new Extent(15)));
		subtitleLabel.setForeground(Color.WHITE);
		titleColumn.add(subtitleLabel);
		titleRow.add(titleColumn);
		mainColumn.add(titleRow);
		Column rulesColumn = new Column();
		rulesColumn.setInsets(new Insets(5, 15, 5, 5));
		ruleEntriesColumn = new Column();
		ruleEntriesColumn.setCellSpacing(new Extent(5));
		rulesColumn.add(ruleEntriesColumn);
		addRuleButton = new SquareButton("plus", "create a new query", this);
		Row r = new Row();
		r.add(addRuleButton);
		rulesColumn.add(r);
		mainColumn.add(rulesColumn);
		getContent().add(mainColumn);
	}
	
	public String getParameter(String name) {
		return parameters.get(name);
	}
	
	public CoralLexicon getLexicon() {
		return lexicon;
	}
	
	void selectRule(QueryEntry entry) {
		if (selectedEntry != null) {
			selectedEntry.setSelected(false);
		}
		entry.setSelected(true);
		selectedEntry = entry;
	}
	
	void deleteRule(QueryEntry entry) {
		if (selectedEntry == entry) {
			selectedEntry = null;
		}
		ruleEntriesColumn.remove(entry);
	}
	
	void showWindow(WindowPane window) {
		cleanWindows();
		getContent().add(window);
	}
	
	void removeWindow(WindowPane window) {
		window.setVisible(false);
		window.dispose();
		cleanWindows();
	}
	
	private void cleanWindows() {
		for (Component c : getContent().getComponents()) {
			if (!c.isVisible()) {
				getContent().remove(c);
			}
		}
	}
	
	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == addRuleButton) {
			QueryEntry newRule = new QueryEntry(this);
			ruleEntriesColumn.add(newRule);
			selectRule(newRule);
			newRule.initRule();
		}
	}

}
