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
import java.util.Comparator;
import java.util.List;

import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import ch.uzh.ifi.attempto.base.ConcreteOption;
import ch.uzh.ifi.attempto.base.NextTokenOptions;
import ch.uzh.ifi.attempto.chartparser.CPAbstractOption;
import ch.uzh.ifi.attempto.chartparser.CPConcreteOption;
import ch.uzh.ifi.attempto.chartparser.CPNextTokenOptions;
import ch.uzh.ifi.attempto.chartparser.Preterminal;
import ch.uzh.ifi.attempto.preditor.DefaultMenuCreator;
import ch.uzh.ifi.attempto.preditor.DefaultMenuItemComparator;
import ch.uzh.ifi.attempto.preditor.MenuEntry;
import ch.uzh.ifi.attempto.preditor.MenuItem;
import ch.uzh.ifi.attempto.preditor.SpecialMenuItem;

/**
 * This class is the menu creator for the Coral editor.
 * 
 * @author Tobias Kuhn
 */
public class CoralMenuCreator extends DefaultMenuCreator implements ActionListener {

	private static final long serialVersionUID = 4562127796694800104L;
	private static final DefaultMenuItemComparator comparator = new DefaultMenuItemComparator();
	
	private static List<String> menuGroupOrdering;
	
	static {
		menuGroupOrdering = new ArrayList<String>();
		menuGroupOrdering.add("fixed expression");
		menuGroupOrdering.add("token");
		menuGroupOrdering.add("part of speech");
		menuGroupOrdering.add("structure");
		menuGroupOrdering.add("reference");
		menuGroupOrdering.add("specific attribute");
		menuGroupOrdering.add("general attribute");
		menuGroupOrdering.add("role");
		menuGroupOrdering.add("relation");
		menuGroupOrdering.add("preposition");
		menuGroupOrdering.add("number");
		menuGroupOrdering.add("value");
		
		comparator.addPrefix("directly ");
		comparator.addPrefix("indirectly ");
		comparator.addPrefix("is ");
		comparator.addPrefix("is directly ");
		comparator.addPrefix("is indirectly ");
	}
	
	private CoralPreditor rpred;
	
	/**
	 * Creates a new menu creator instance.
	 */
	public CoralMenuCreator(CoralPreditor rpred) {
		this.rpred = rpred;
	}
	
	public List<String> getMenuGroupOrdering() {
		return menuGroupOrdering;
	}
	
	public MenuEntry createMenuEntry(ConcreteOption option) {
		String n = option.getCategoryName();
		Preterminal c = ((CPConcreteOption) option).getCategory();
		
		if (n == null) {
			return new MenuEntry(option, "fixed expression");
		} else if (n.equals("v")) {
			return new MenuEntry(option, "relation");
		} else if (n.equals("article_noun")) {
			String predef = c.getFeature("predef").getString();
			if ("true".equals(predef)) {
				return new MenuEntry(option, "fixed expression");
			}
			String type = c.getFeature("type").getString();
			if ("token".equals(type)) {
				return new MenuEntry(option, "token");
			} else if ("leaf".equals(type)) {
				return new MenuEntry(option, "part of speech");
			} else {
				return new MenuEntry(option, "structure");
			}
		} else if (n.equals("ref")) {
			return new MenuEntry(option, "reference");
		} else if (n.equals("role")) {
			return new MenuEntry(option, "role");
		} else if (n.equals("property")) {
			if ("plus".equals(c.getFeature("general").getString())) {
				return new MenuEntry(option, "general attribute");
			} else {
				return new MenuEntry(option, "specific attribute");
			}
		} else if (n.equals("num")) {
			return new MenuEntry(option, "number");
		} else if (n.equals("integer")) {
			return new MenuEntry(option, "value");
		} else if (n.equals("prep")) {
			return new MenuEntry(option, "preposition");
		} else {
			return new MenuEntry(option, "fixed expression");
		}
	}

	public List<SpecialMenuItem> createSpecialMenuItems(NextTokenOptions options) {
		List<SpecialMenuItem> menuItems = new ArrayList<SpecialMenuItem>();
		for (CPAbstractOption o : ((CPNextTokenOptions) options).getAbstractOptions("string")) {
			String type = o.getCategory().getFeature("type").getString();
			if ("token".equals(type)) {
				menuItems.add(new SpecialMenuItem("\"...\"", "token", "string", this));
			} else if ("propname".equals(type)) {
				menuItems.add(new SpecialMenuItem("\"...\"", "property name", "propname", this));
			} else if ("propval".equals(type)) {
				menuItems.add(new SpecialMenuItem("\"...\"", "property value", "string", this));
			} else if ("relationtype".equals(type)) {
				menuItems.add(new SpecialMenuItem("\"...\"", "relation type", "propname", this));
			}
		}
		return menuItems;
	}

	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		Object src = e.getSource();
		if (src instanceof StringWindow) {
			rpred.getWindow().addText(c + " ");
		} else if (c.matches("string|propname")) {
			rpred.getGUI().showWindow(new StringWindow(rpred.getGUI(), c, this));
		}
	}
	
	public Comparator<MenuItem> getMenuItemComparator() {
		return comparator;
	}

}
