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
