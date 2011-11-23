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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReferenceChecker {
	
	private List<StatementItem> items;
	private List<AnteAnaElement> refElements;
	private Collection<AnteAnaElement> antecedents;
	private Set<String> multiantes = new HashSet<String>();
	private Set<String> ids = new HashSet<String>();
	private Map<String, String> idNumMap = new HashMap<String, String>();
	private Map<String, String> idAdjustMap = new HashMap<String, String>();
	
	public static void check(List<StatementItem> items) {
		ReferenceChecker rc = new ReferenceChecker(items);
		rc.runCheck();
	}
	
	private ReferenceChecker(List<StatementItem> items) {
		this.items = items;
	}
	
	private void runCheck() {
		adjustIds();
		collectRefElements();
		collectMultiAntes();
		checkReferences();
	}
	
	private void adjustIds() {
		for (StatementItem si : items) {
			Map<String, Integer> nounCounter = new HashMap<String, Integer>();
			for (StatementElement se : si.getElements()) {
				if (se instanceof AnteAnaElement) {
					AnteAnaElement ae = (AnteAnaElement) se;
					String n = getFootprint(ae);
					int i = 1;
					if (nounCounter.get(n) != null) {
						i = nounCounter.get(n) + 1;
					}
					nounCounter.put(n, i);
					adjustId(ae, n + "/" + i);
				}
			}
		}
	}
	
	private void adjustId(AnteAnaElement ae, String footprint) {
		String id = ae.getId();
		String freshId = ae.getOwner().getId() + "/" + footprint;
		if (id == null) {
			ae.setId(freshId);
			ids.add(freshId);
		} else if (idAdjustMap.containsKey(id)) {
			ae.setId(idAdjustMap.get(id));
		} else if (id.indexOf("/") < 0) {
			ae.setId(freshId);
			ids.add(freshId);
			idAdjustMap.put(id, freshId);
		} else if (!ids.contains(id) && !id.equals(freshId)) {
			ae.setId(freshId);
			ids.add(freshId);
			idAdjustMap.put(id, freshId);
		} else {
			ids.add(id);
		}
	}
	
	private String getFootprint(AnteAnaElement ae) {
		String fp = ae.getCoreText();
		if (ae.getString() != null) {
			fp += "/" + ae.getString();
		}
		return fp;
	}
	
	private void collectRefElements() {
		refElements = new ArrayList<AnteAnaElement>();
		Map<String, AnteAnaElement> anteMap = new HashMap<String, AnteAnaElement>();
		for (StatementItem si : items) {
			for (StatementElement se : si.getElements()) {
				if (se instanceof AnteAnaElement) {
					AnteAnaElement ae = (AnteAnaElement) se;
					refElements.add(ae);
					if (!anteMap.containsKey(ae.getId())) {
						anteMap.put(ae.getId(), ae);
					}
				}
			}
		}
		antecedents = anteMap.values();
	}
	
	private void collectMultiAntes() {
		List<String> nouns = new ArrayList<String>();
		List<String> nounsWithStrings = new ArrayList<String>();
		for (AnteAnaElement se : antecedents) {
			String s = se.getString();
			String t = se.getCoreText();
			if (s == null) {
				nouns.add(t);
			} else if (t.equals("")) {
				nounsWithStrings.add("token/" + s);
			} else {
				nounsWithStrings.add(t + "/" + s);
			}
		}
		
		List<String> nounsCopy = new ArrayList<String>(nouns);
		while (!nounsCopy.isEmpty()) {
			String n = nounsCopy.remove(0);
			if (nounsCopy.contains(n)) {
				multiantes.add(n);
			}
		}
		while (!nounsWithStrings.isEmpty()) {
			String n = nounsWithStrings.remove(0);
			String[] p = n.split("/");
			if (nounsWithStrings.contains(n)) {
				multiantes.add(n);
			}
			if (nouns.contains(p[0])) {
				multiantes.add(n);
				multiantes.add(p[0]);
			}
		}
	}
	
	private void checkReferences() {
		for (StatementItem si : items) {
			for (StatementElement se : si.getElements()) {
				if (se instanceof AnteAnaElement) {
					checkReference((AnteAnaElement) se);
				}
			}
			si.update();
		}
	}
	
	private void checkReference(AnteAnaElement a) {
		if (!a.isAnaphor() && idNumMap.containsKey(a.getId())) {
			a.morphToAnaphor();
		} else if (a.isAnaphor() && !idNumMap.containsKey(a.getId())) {
			a.morphToAntecedent();
		}
		
		if (!a.isAnaphor()) {
			boolean isMulti;
			if (a.getString() == null) {
				isMulti = multiantes.contains(a.getCoreText());
			} else {
				isMulti = multiantes.contains(a.getCoreText() + "/" + a.getString());
			}
			boolean newnum = false;
			String num = a.getNum();
			if (num != null && idNumMap.containsValue(num)) newnum = true;
			if (num == null && isMulti) newnum = true;
			if (newnum) {
				int i = 0;
				while (true) {
					i++;
					if (!idNumMap.containsValue(i + "")) break;
				}
				a.setNum(i + "");
			}
			
			if (num != null && !isMulti) {
				a.setNum(null);
			}
			
			idNumMap.put(a.getId(), a.getNum());
		} else {
			a.setNum(idNumMap.get(a.getId()));
		}
		
		a.update();
	}

}
