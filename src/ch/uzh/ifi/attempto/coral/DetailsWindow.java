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

import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;
import nextapp.echo.app.WindowPane;
import ch.uzh.ifi.attempto.chartparser.ChartParser;
import ch.uzh.ifi.attempto.chartparser.ParseTree;
import ch.uzh.ifi.attempto.echocomp.Style;
import echopoint.DirectHtml;

public class DetailsWindow extends WindowPane {

	private static final long serialVersionUID = -2687350184617313339L;
	
	public DetailsWindow(String sem, ChartParser parser) {
		setTitle("Query Details");
		setTitleFont(new Font(Style.fontTypeface, Font.ITALIC, new Extent(13)));
		setTitleBackground(Style.windowTitleBackground);
		setStyleName("Default");
		setModal(true);
		setWidth(new Extent(500));
		setHeight(new Extent(500));
		setResizable(true);
		setMovable(true);
		
		String t = "|";
		for (String s : parser.getTokens()) {
			t +=  " " + s + " |";
		}
		
		ParseTree p = parser.getParseTree();
		
		Column col = new Column();
		col.setInsets(new Insets(10, 10, 10, 10));
		col.add(new DirectHtml(
				"<h3>AQL</h3>" + sem +
				"<h3>Raw Semantics</h3>" + p.getSerializedLambdaSemTree() +
				"<h3>Tokens</h3>" + t +
				"<h3>Syntax</h3><pre>" + p.getAsciiSynTree() + "</pre>"
			));
		add(col);
	}

}
