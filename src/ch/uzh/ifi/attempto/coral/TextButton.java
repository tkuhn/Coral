package ch.uzh.ifi.attempto.coral;

import ch.uzh.ifi.attempto.echocomp.Style;
import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.layout.RowLayoutData;

public class TextButton extends Button {

	private static final long serialVersionUID = 1978247555875137128L;
	
	public TextButton(String text, ActionListener actionlistener) {
		if (text != null) {
			setText(text);
		}
		if (actionlistener != null) {
			addActionListener(actionlistener);
		}
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, new Extent(13)));
		setInsets(new Insets(3, 1, 3, 2));
		setHeight(new Extent(17));
		setRolloverEnabled(true);
		RowLayoutData layout = new RowLayoutData();
		layout.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		setLayoutData(layout);
		setRolloverBackground(Style.mediumBackground);
		setLineWrap(false);
	}
	
	public TextButton(String text) {
		this(text, null);
	}
	
	public TextButton(ActionListener actionlistener) {
		this(null, actionlistener);
	}
	
	public TextButton() {
		this(null, null);
	}
	
	public void setSelected(boolean selected) {
		if (selected) {
			setForeground(Color.BLUE);
		} else {
			setForeground(Color.BLACK);
		}
	}

}
