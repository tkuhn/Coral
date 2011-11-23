package ch.uzh.ifi.attempto.coral;

import java.util.List;

import nextapp.echo.app.Component;

public interface StatementElement {
	
	public String getText();
	
	public String getType();
	
	public Component getComponent();
	
	public void update();
	
	public void setSelected(boolean selected);
	
	public void collectTokens(List<String> tokens);
	
	public String getId();

}
