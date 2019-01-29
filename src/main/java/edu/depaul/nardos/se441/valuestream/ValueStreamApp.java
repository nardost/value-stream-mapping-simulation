package edu.depaul.nardos.se441.valuestream;

import java.util.LinkedHashMap;
import java.util.Set;

public class ValueStreamApp {
	public static void main(String [] args) {
		
		ValueStream valueStream = new ValueStream();
		LinkedHashMap<String,Double> workstations = new LinkedHashMap<String,Double>();
		workstations.put("A", 1.0);
		workstations.put("B", 1.0);
		workstations.put("C", 1.0);
		workstations.put("D", 1.0);
		workstations.put("E", 1.0);
		Set<String> ids = workstations.keySet();
		for(String id : ids) {
			valueStream.addStation(new Station(id, workstations.get(id)));
		}
		for(int i = 0; i < workstations.size(); i++) {
			valueStream.doCycle();
		}
		Utilities.printStateByLaps(valueStream.getState());
		Utilities.printStateByStation(ids, valueStream);
	}
}
