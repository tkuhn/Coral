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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.app.Window;
import nextapp.echo.webcontainer.WebContainerServlet;
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
