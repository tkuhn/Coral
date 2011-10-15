package ch.uzh.ifi.attempto.coral;

import java.util.ArrayList;
import java.util.List;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import ch.uzh.ifi.attempto.chartparser.Category;
import ch.uzh.ifi.attempto.chartparser.Nonterminal;
import ch.uzh.ifi.attempto.chartparser.ParseTreeNode;
import ch.uzh.ifi.attempto.chartparser.Preterminal;
import ch.uzh.ifi.attempto.chartparser.Terminal;
import ch.uzh.ifi.attempto.echocomp.HSpace;
import ch.uzh.ifi.attempto.echocomp.SquareButton;

public class StatementItem implements ActionListener {
	
	private static final long serialVersionUID = -2829724933282868264L;
	
	private static long idCount = 0;
	
	private long id;
	private QueryEntry owner;
	private Row row = new Row();
	private boolean selected;
	private List<StatementElement> elements = new ArrayList<StatementElement>();
	private SquareButton editButton = new SquareButton("pen", "edit this item", this);
	private SquareButton deleteButton = new SquareButton("cross", "delete this item", this);
	private SquareButton addButton1 = new SquareButton("plus", "add text here", this);
	private SquareButton addButton2 = new SquareButton("plus", "add text here", this);
	
	public StatementItem(ParseTreeNode node, long id, QueryEntry owner) {
		this.owner = owner;
		if (id == -1) {
			this.id = ++idCount;
		} else {
			this.id = id;
		}
		if (node != null) {
			addElementsWithoutUpdate(node);
		}
		update();
	}
	
	public StatementItem(long id, QueryEntry owner) {
		this(null, id, owner);
	}
	
	public StatementItem(QueryEntry owner) {
		this(null, -1, owner);
	}
	
	public void addElement(StatementElement el) {
		elements.add(el);
	}
	
	public void addElements(ParseTreeNode node) {
		addElementsWithoutUpdate(node);
		update();
	}
	
	private void addElementsWithoutUpdate(ParseTreeNode node) {
		Category h = node.getCategory();
		String n = h.getName();
		if (n.equals("sentence_delimiter")) {
			elements.add(new SimpleElement(";", this));
		} else if (n.equals("el") || n.equals("ref")) {
			elements.add(new AnteAnaElement(node, this));
		} else if (h instanceof Terminal || h instanceof Preterminal) {
			elements.add(new SimpleElement(node, this));
		} else {
			for (ParseTreeNode c : node.getChildren()) {
				addElementsWithoutUpdate(c);
			}
		}
	}
	
	public Component getComponent() {
		update();
		return row;
	}
	
	public long getId() {
		return id;
	}
	
	public String getText() {
		String t = "";
		for (StatementElement se : elements) {
			t += se.getText() + " ";
		}
		return t;
	}
	
	public List<StatementElement> getElements() {
		return elements;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		update();
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void update() {
		row.removeAll();
		if (selected) {
			row.add(addButton1);
		} else {
			row.add(new HSpace(20));
		}
		for (StatementElement se : elements) {
			se.update();
			row.add(se.getComponent());
		}
		if (selected) {
			row.add(editButton);
			row.add(deleteButton);
			row.add(addButton2);
		}
		
		for (StatementElement se : elements) {
			se.setSelected(selected);
		}
	}

	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == deleteButton) {
			owner.deleteItem(this);
		} else if (action.getSource() == editButton) {
			new CoralPreditor(this, 0, owner);
		} else if (action.getSource() == addButton1) {
			new CoralPreditor(this, -1, owner);
		} else if (action.getSource() == addButton2) {
			new CoralPreditor(this, 1, owner);
		} else if (action.getSource() instanceof StatementElement) {
			StatementElement se = (StatementElement) action.getSource();
			if (se.getId() != null) owner.setSelectedId(se.getId());
			owner.selectItem(this);
			update();
		}
	}
	
	boolean isSelected(StatementElement se) {
		if (owner.getSelectedId() == null) return false;
		return owner.getSelectedId().equals(se.getId());
	}
	
	void setSelectedElement(StatementElement se) {
		owner.setSelectedId(se.getId());
		owner.selectItem(this);
		update();
	}
	
	void collectForwardRefs(List<Nonterminal> r) {
		for (StatementElement se : elements) {
			if (se instanceof AnteAnaElement) {
				((AnteAnaElement) se).collectForwardRefs(r);
			}
		}
	}
	
	void collectTokens(List<String> tokens) {
		for (StatementElement e : elements) {
			e.collectTokens(tokens);
		}
	}

}
