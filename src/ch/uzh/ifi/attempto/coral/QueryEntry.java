package ch.uzh.ifi.attempto.coral;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.RowLayoutData;
import nextapp.echo2.webcontainer.command.BrowserRedirectCommand;
import ch.uzh.ifi.attempto.chartparser.ChartParser;
import ch.uzh.ifi.attempto.chartparser.Grammar;
import ch.uzh.ifi.attempto.chartparser.Nonterminal;
import ch.uzh.ifi.attempto.echocomp.HSpace;
import ch.uzh.ifi.attempto.echocomp.Label;
import ch.uzh.ifi.attempto.echocomp.MessageWindow;
import ch.uzh.ifi.attempto.echocomp.SquareButton;
import ch.uzh.ifi.attempto.echocomp.Style;

public class QueryEntry extends Column implements ActionListener {

	private static final long serialVersionUID = 2475734471952647073L;
	
	private static Color borderColor = new Color(230, 230, 120);
	private static Color inactiveBorderColor = new Color(230, 230, 200);
	
	private CoralGUI owner;
	private StatementItem selectedItem;
	private String selectedId;
	private Button resultButton;
	private Button debugButton;
	private SquareButton deleteButton;
	private Column condCol;
	private Row headRow;
	private Column borderCol;
	private boolean selected;
	private List<StatementItem> items = new ArrayList<StatementItem>();
	private ChartParser parser;
	private CoralGrammar grammar;
	private boolean initialized = false;
	
	public QueryEntry(CoralGUI owner) {
		this.owner = owner;
		setInsets(new Insets(0, 0, 0, 5));
		RowLayoutData layout = new RowLayoutData();
		layout.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		
		grammar = new CoralGrammar();
		parser = new ChartParser(grammar, "query");
		parser.setDynamicLexicon(owner.getLexicon());
		parser.setPositionIdentifierPrefix("");
		
		headRow = new Row();
		headRow.setInsets(new Insets(0, 0, 5, 0));
		headRow.setBackground(borderColor);
		deleteButton = new SquareButton("cross", "delete this query", this);
		headRow.add(deleteButton);
		headRow.add(new HSpace(5));
		resultButton = createButton("submit");
		headRow.add(resultButton);
		debugButton = createButton("debug");
		if ("on".equals(owner.getParameter("debug"))) {
			headRow.add(debugButton);
		}
		
		add(headRow);
		
		borderCol = new Column();
		borderCol.setBackground(borderColor);
		borderCol.setInsets(new Insets(5, 0, 5, 5));
		
		Row mainRow = new Row();
		mainRow.setBackground(Color.WHITE);
		mainRow.setInsets(new Insets(10));
		
		Column ruleCol = new Column();
		ruleCol.setLayoutData(layout);
		Row ruleInitRow = new Row();
		ruleInitRow.add(new TextButton("Find all passages where", this));
		ruleCol.add(ruleInitRow);
		Row r1 = new Row();
		condCol = new Column();
		Row r2 = new Row();
		r2.add(new HSpace(20));
		r2.add(new Label("..."));
		condCol.add(r2);
		r1.add(condCol);
		ruleCol.add(r1);
		mainRow.add(ruleCol);
		
		borderCol.add(mainRow);
		add(borderCol);
		
		setSelected(false);
	}
	
	private Button createButton(String text) {
		Button b = new Button(text);
		
		b.setActionCommand(text);
		b.addActionListener(this);
		
		b.setHeight(new Extent(14));
		b.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, new Extent(10)));
		b.setBackground(null);
		b.setForeground(Color.BLACK);
		b.setRolloverEnabled(true);
		b.setRolloverBackground(Style.lightBackground);
		b.setRolloverForeground(Color.BLACK);
		
		b.setInsets(new Insets(5, 3));
		b.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		b.setTextAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		
		b.setLineWrap(false);
		
		return b;
	}
	
	void initRule() {
		new CoralPreditor(null, -1, this);
		owner.selectRule(this);
		initialized = true;
	}
	
	public Grammar getGrammar() {
		return grammar;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		if (!selected) selectedId = null;
		if (!selected && selectedItem != null) {
			selectedItem.setSelected(false);
		}
		update();
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public CoralLexicon getLexicon() {
		return owner.getLexicon();
	}
	
	void update() {
		if (!initialized) return;
		if (items.size() == 0) {
			owner.deleteRule(this);
		} else {
			condCol.removeAll();
			for (StatementItem si : items) {
				condCol.add(si.getComponent());
			}
			resultButton.setVisible(selected);
			debugButton.setVisible(selected);
		}
		if (selected) {
			headRow.setBackground(borderColor);
			borderCol.setBackground(borderColor);
		} else {
			headRow.setBackground(inactiveBorderColor);
			borderCol.setBackground(inactiveBorderColor);
		}
	}

	void selectItem(StatementItem item) {
		if (selectedItem != null) {
			selectedItem.setSelected(false);
		}
		if (item != null) {
			item.setSelected(true);
		}
		selectedItem = item;
		update();
		if (!selected) {
			owner.selectRule(this);
		}
	}
	
	void deleteItem(StatementItem item) {
		if (selectedItem == item)  {
			selectItem(null);
		}
		items.remove(item);
		selectedId = null;
		checkReferences();
		update();
	}
	
	void addItems(StatementItem item, int offset, List<StatementItem> newItems) {
		if (item == null || items.indexOf(item) < 0) {
			items.addAll(0, newItems);
		} else {
			items.addAll(items.indexOf(item) + offset, newItems);
		}
		if (newItems.isEmpty()) {
			selectItem(null);
		} else {
			selectItem(newItems.get(0));
		}
		selectedId = null;
		checkReferences();
		update();
	}
	
	void editItems(StatementItem item, List<StatementItem> newItems) {
		items.addAll(items.indexOf(item), newItems);
		items.remove(item);
		if (newItems.isEmpty()) {
			selectItem(null);
		} else {
			selectItem(newItems.get(0));
		}
		selectedId = null;
		checkReferences();
		update();
	}
	
	private void checkReferences() {
		ReferenceChecker.check(items);
	}
	
	public void actionPerformed(ActionEvent action) {
		Object src = action.getSource();
		if (src == deleteButton) {
			owner.deleteRule(this);
		} else if (src == resultButton || src == debugButton) {
			List<String> tokens = new ArrayList<String>();
			for (StatementItem si : items) {
				si.collectTokens(tokens);
			}
			parser.setTokens(tokens);
			String sem = postprocessSemantics(parser.getParseTree().getSerializedLambdaSemTree());
			sem = sem.replaceFirst("^'", "").replaceFirst("'$", "");
			if (src == resultButton) {
				String annisURL = owner.getParameter("annisurl");
				if (annisURL == null || annisURL.length() == 0) {
					owner.showWindow(new MessageWindow("Error", "No URL defined to show the results.", "OK"));
				} else {
					try {
						sem = URLEncoder.encode(sem, "UTF-8");
					} catch (UnsupportedEncodingException ex) {
						ex.printStackTrace();
					}
					sem = sem.replaceAll("\\+", "%20");
					String url = annisURL + "Cite/AQL(" + sem + "),CIDS(6230),CLEFT(5),CRIGHT(5)";
					ApplicationInstance.getActive().enqueueCommand(new BrowserRedirectCommand(url));
				}
			} else {
				owner.showWindow(new DetailsWindow(sem, parser));
			}
		} else {
			selectItem(null);
			owner.selectRule(this);
		}
	}
	
	List<Nonterminal> getContext(StatementItem si) {
		List<Nonterminal> context = new ArrayList<Nonterminal>();
		for (StatementItem i : items) {
			if (si != i) {
				i.collectForwardRefs(context);
			}
		}
		return context;
	}
	
	String getSelectedId() {
		return selectedId;
	}
	
	void setSelectedId(String id) {
		selectedId = id;
	}
	
	public CoralGUI getOwner() {
		return owner;
	}
	
	public static String postprocessSemantics(String sem) {
		sem = sem.replaceAll("#", "##");
		while (sem.matches(".*@[0-9]+.*")) {
			String oldNum = sem.replaceFirst("^.*@([0-9]+).*$", "$1");
			String newNum = sem.replaceFirst("^(.*)@[0-9]+.*$", "$1").split("@").length + "";
			sem = sem.replaceAll("##" + oldNum, "#" + newNum);
			sem = sem.replaceAll("@" + oldNum, "@");
		}
		sem = sem.replaceAll("@\\s+", "");
		sem = sem.replaceFirst("^'", "").replaceFirst("'$", "");
		return sem;
	}

}
