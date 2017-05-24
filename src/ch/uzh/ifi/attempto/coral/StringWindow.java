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

import nextapp.echo.app.Alignment;
import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.WindowPane;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.layout.GridLayoutData;
import ch.uzh.ifi.attempto.echocomp.GeneralButton;
import ch.uzh.ifi.attempto.echocomp.Label;
import ch.uzh.ifi.attempto.echocomp.MessageWindow;
import ch.uzh.ifi.attempto.echocomp.Style;
import ch.uzh.ifi.attempto.echocomp.TextField;

public class StringWindow extends WindowPane implements ActionListener {
	
	private static final long serialVersionUID = -6704597832001286479L;
	
	private CoralGUI gui;
	private String type;
	private ActionListener actionListener;
	
	private TextField stringField = new TextField(410, this);
	
	public StringWindow(CoralGUI gui, String type, ActionListener actionListener) {
		this.gui = gui;
		this.type = type;
		this.actionListener = actionListener;
		
		setTitle("Add String");
		setTitleFont(new Font(Style.fontTypeface, Font.ITALIC, new Extent(13)));
		setModal(true);
		setWidth(new Extent(500));
		setHeight(new Extent(150));
		setResizable(false);
		setMovable(true);
		setTitleBackground(Style.windowTitleBackground);
		setStyleName("Default");

		Grid mainGrid = new Grid(1);
		mainGrid.setInsets(new Insets(10, 10, 10, 0));
		mainGrid.setColumnWidth(0, new Extent(480));
		mainGrid.setRowHeight(0, new Extent(60));
		
		Column messageColumn = new Column();
		GridLayoutData layout1 = new GridLayoutData();
		layout1.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		messageColumn.setLayoutData(layout1);
		messageColumn.setInsets(new Insets(10, 10, 10, 0));
		Row textFieldRow = new Row();
		textFieldRow.setCellSpacing(new Extent(5));
		textFieldRow.add(new Label("\""));
		textFieldRow.add(stringField);
		textFieldRow.add(new Label("\""));
		messageColumn.add(textFieldRow);
		
		mainGrid.add(messageColumn);

		Row buttonBar = new Row();
		buttonBar.setCellSpacing(new Extent(10));
		buttonBar.setInsets(new Insets(0, 0, 0, 10));
		buttonBar.add(new GeneralButton("OK", this, 80));
		buttonBar.add(new GeneralButton("Cancel", this, 80));
		GridLayoutData layout2 = new GridLayoutData();
		layout2.setAlignment(new Alignment(Alignment.CENTER, Alignment.BOTTOM));
		buttonBar.setLayoutData(layout2);
		mainGrid.add(buttonBar);
		
		add(mainGrid);

		ApplicationInstance.getActive().setFocusedComponent(stringField);
	}
	
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		if ("Cancel".equals(c)) {
			setVisible(false);
		} else {
			String s = stringField.getText();
			if (s.length() == 0) {
				gui.showWindow(new MessageWindow(
						"Error",
						"The string is not allowed to be empty.",
						this,
						"OK"
					));
			} else if (s.matches(".*(\\\"|/).*")) {
				gui.showWindow(new MessageWindow(
						"Error",
						"The string contains illegal characters.",
						this,
						"OK"
					));
			} else if (type.equals("string")) {
				performAction(s);
			} else if (type.equals("propname")) {
				if (s.matches("[a-zA-Z][a-zA-Z0-1\\-_]*")) {
					performAction(s);
				} else {
					gui.showWindow(new MessageWindow(
							"Error",
							"Illegal characters for this type.",
							this,
							"OK"
						));
				}
			}
		}
	}
	
	private void performAction(String s) {
		actionListener.actionPerformed(new ActionEvent(this, "\"" + s + "\""));
		setVisible(false);
	}
	
}
