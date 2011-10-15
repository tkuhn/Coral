package ch.uzh.ifi.attempto.coral;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.GridLayoutData;
import ch.uzh.ifi.attempto.echocomp.GeneralButton;
import ch.uzh.ifi.attempto.echocomp.Label;
import ch.uzh.ifi.attempto.echocomp.MessageWindow;
import ch.uzh.ifi.attempto.echocomp.Style;
import ch.uzh.ifi.attempto.echocomp.TextField;
import ch.uzh.ifi.attempto.echocomp.WindowPane;

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
		buttonBar.add(new GeneralButton("OK", 80, this));
		buttonBar.add(new GeneralButton("Cancel", 80, this));
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