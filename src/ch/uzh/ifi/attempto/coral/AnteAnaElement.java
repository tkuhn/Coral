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

import java.util.List;

import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import ch.uzh.ifi.attempto.chartparser.Category;
import ch.uzh.ifi.attempto.chartparser.Nonterminal;
import ch.uzh.ifi.attempto.chartparser.ParseTreeNode;
import ch.uzh.ifi.attempto.echocomp.Style;

public class AnteAnaElement implements StatementElement, ActionListener {
	
	private static final long serialVersionUID = -497882346630452794L;
	
	private boolean isAnaphor;
	private String coreText;
	private String id;
	private String num;
	private String string;
	private TextButton button;
	private StatementItem owner;
	
	public AnteAnaElement(ParseTreeNode node, StatementItem owner) {
		this.owner = owner;
		Category h = node.getCategory();
		coreText = h.getFeature("noun").getString();
		string = h.getFeature("string").getString();
		if (string.length() == 0) string = null;
		num = h.getFeature("num").getString();
		if (num.equals("minus")) num = null;
		isAnaphor = h.getName().equals("ref");
		id = h.getFeature("id").getString();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNum() {
		return num;
	}
	
	void setNum(String num) {
		this.num = num;
		update();
	}
	
	public String getString() {
		return string;
	}
	
	public String getType() {
		if (isAnaphor) {
			return "ref";
		} else {
			return "el";
		}
	}
	
	private String getPre() {
		if (coreText.length() == 0) {
			return "";
		} else if (isAnaphor) {
			if (num == null) {
				return "the";
			} else {
				return "";
			}
		} else if (CoralLexicon.useIndefiniteArticleAn(coreText)) {
			return "an";
		} else {
			return "a";
		}
	}
	
	public String getText() {
		String text = getPre() + " " + coreText;
		if (string != null) {
			text += " \"" + string + "\"";
		}
		if (num != null) {
			text += " (" + num + ")";
		}
		return trim(text);
	}
	
	public String getCoreText() {
		return coreText;
	}
	
	public Component getComponent() {
		return button;
	}
	
	public StatementItem getOwner() {
		return owner;
	}
	
	public boolean isAnaphor() {
		return isAnaphor;
	}
	
	public void morphToAnaphor() {
		isAnaphor = true;
		update();
	}
	
	public void morphToAntecedent() {
		isAnaphor = false;
		update();
	}
	
	public void update() {
		if (button == null) {
			button = new TextButton(this);
		}
		button.setText(getText());
		button.setSelected(owner.isSelected(this));
	}
	
	public void collectTokens(List<String> tokens) {
		if (isAnaphor) {
			tokens.add(getText());
		} else {
			if (coreText.length() > 0) {
				tokens.add(trim(getPre() + " " + coreText));
			}
			if (string != null) {
				tokens.add("\"" + string + "\"");
			}
			if (num != null) {
				tokens.add("(" + num + ")");
			}
		}
	}
	
	void collectForwardRefs(List<Nonterminal> r) {
		if (!isAnaphor) {
			Nonterminal fw = new Nonterminal(">");
			if ("".equals(coreText)) {
				fw.setFeature("noun", "token");
			} else {
				fw.setFeature("noun", coreText);
			}
			if (num == null) {
				fw.setFeature("num", "minus");
			} else {
				fw.setFeature("num", num);
			}
			if (string == null) {
				fw.setFeature("string", "");
			} else {
				fw.setFeature("string", string);
			}
			fw.setFeature("id", id);
			r.add(fw);
		}
	}
	
	public void setSelected(boolean selected) {
		if (selected) {
			button.setBackground(Style.lightBackground);
		} else {
			button.setBackground(Color.WHITE);
		}
	}

	public void actionPerformed(ActionEvent e) {
		owner.setSelectedElement(this);
	}
	
	private static String trim(String s) {
		return s.replaceAll("\\s+", " ").replaceFirst("^ ", "").replaceFirst(" $", "");
	}

}
