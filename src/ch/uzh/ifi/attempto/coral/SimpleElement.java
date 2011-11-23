package ch.uzh.ifi.attempto.coral;

import java.util.List;

import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import ch.uzh.ifi.attempto.chartparser.Category;
import ch.uzh.ifi.attempto.chartparser.ParseTreeNode;
import ch.uzh.ifi.attempto.chartparser.Terminal;
import ch.uzh.ifi.attempto.echocomp.Style;

public class SimpleElement implements StatementElement, ActionListener {
	
	private static final long serialVersionUID = 7318012893173461468L;
	
	private String text;
	private String type;
	private TextButton button;
	private StatementItem owner;
	
	public SimpleElement(ParseTreeNode node, StatementItem owner) {
		this.owner = owner;
		Category c = node.getCategory();
		if (c instanceof Terminal) {
			this.type = null;
			this.text = c.getName();
		} else {
			this.type = c.getName();
			this.text = extractText(node);
		}
		update();
	}

	public SimpleElement(String text, StatementItem owner) {
		this.owner = owner;
		this.type = null;
		this.text = text;
		update();
	}
	
	private String extractText(ParseTreeNode node) {
		Category c = node.getCategory();
		if (c instanceof Terminal) {
			return c.getName();
		} else {
			String s = "";
			for (ParseTreeNode n : node.getChildren()) {
				s += extractText(n) + " ";
			}
			return s.replaceFirst("\\s*$", "");
		}
	}

	public Component getComponent() {
		return button;
	}

	public String getText() {
		return text;
	}

	public String getType() {
		return type;
	}
	
	public void update() {
		if (button == null) {
			button = new TextButton(this);
		}
		button.setText(text);
		button.setSelected(owner.isSelected(this));
	}
	
	public void setSelected(boolean selected) {
		if (selected) {
			button.setBackground(Style.lightBackground);
		} else {
			button.setBackground(Color.WHITE);
		}
	}
	
	public void collectTokens(List<String> tokens) {
		tokens.add(getText());
	}
	
	public String getId() {
		return null;
	}
	
	public void actionPerformed(ActionEvent e) {
		owner.setSelectedElement(this);
	}

}
