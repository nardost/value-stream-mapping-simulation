package edu.depaul.nardos.se441.valuestream;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.javatuples.Sextet;

public class Utilities {
	public static void printStateByLaps(ArrayList<Sextet<String,Integer,Integer,Integer,Integer,Double>> state) {
		for(int lap = 1; lap <= Configuration.CYCLES; lap++) {
			Iterator<Sextet<String,Integer,Integer,Integer,Integer,Double>> iterator = state.iterator();
			while(iterator.hasNext()) {
				Sextet<String,Integer,Integer,Integer,Integer,Double> s = iterator.next();
				if(s.getValue1() == lap) {
					String pattern = (s.getValue5() < 0) ? "##0.00" : "\u002b##0.00";
					String output = 
							s.getValue0() + "," + 
							s.getValue1() + "," + 
							s.getValue2() + "," + 
							s.getValue3() + "," + 
							s.getValue4() + "," + 
							formatDouble(pattern, s.getValue5());
					System.out.println(output);
				}
			}
		}
	}
	public static void printStateByStation(Set<String> ids, ValueStream valueStream) {
		for(String id : ids) {
			ArrayList<Sextet<String,Integer,Integer,Integer,Integer,Double>> state = valueStream.getState();
			Iterator<Sextet<String,Integer,Integer,Integer,Integer,Double>> iterator = state.iterator();
			while(iterator.hasNext()) {
				Sextet<String,Integer,Integer,Integer,Integer,Double> s = iterator.next();
				if(s.getValue0() == id) {
					String pattern = (s.getValue5() < 0) ? "##0.00" : "\u002b##0.00";
					String output = 
							s.getValue0() + "," + 
							s.getValue1() + "," + 
							s.getValue2() + "," + 
							s.getValue3() + "," + 
							s.getValue4() + "," + 
							formatDouble(pattern, s.getValue5());
					System.out.println(output);
				}
			}
		}
	}
	private static String formatDouble(String pattern, double value) {
		DecimalFormat formatter = new DecimalFormat(pattern);
		return formatter.format(value);
	}
}
