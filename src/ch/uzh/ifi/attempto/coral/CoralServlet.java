package ch.uzh.ifi.attempto.coral;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Window;
import nextapp.echo2.webcontainer.WebContainerServlet;
import ch.uzh.ifi.attempto.echocomp.Style;

/**
 * This class is the servlet for the Coral editor.
 * 
 * @author Tobias Kuhn
 */
public class CoralServlet extends WebContainerServlet {
	
	private static final long serialVersionUID = -6998969461055356964L;

	/**
	 * Creates a new servlet instance.
	 */
	public CoralServlet() {
	}

	public ApplicationInstance newApplicationInstance() {
		
		return new ApplicationInstance() {
			
			private static final long serialVersionUID = -5640636230574254208L;

			public Window init() {
				setStyleSheet(Style.styleSheet);
				return new CoralGUI(getInitParameters());
			}
			
		};
		
	}
	
	@SuppressWarnings("rawtypes")
	private Map<String, String> getInitParameters() {
		Map<String, String> initParameters = new HashMap<String, String>();
		Enumeration paramEnum = getInitParameterNames();
		while (paramEnum.hasMoreElements()) {
			String paramName = paramEnum.nextElement().toString();
			initParameters.put(paramName, getInitParameter(paramName));
		}
		return initParameters;
	}

}
