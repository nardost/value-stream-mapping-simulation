package edu.depaul.nardos.se441.valuestream;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

import org.javatuples.Sextet;

public class ValueStream {

	private ArrayDeque<Station> stations;
	private ArrayList<Sextet<String,Integer,Integer,Integer,Integer,Double>> state;
	private int output;
	private int currentCycle;
	
	public ValueStream() {
		this.stations = new ArrayDeque<Station>();
		this.state = new ArrayList<Sextet<String,Integer,Integer,Integer,Integer,Double>>();
		this.output = 0;
		this.currentCycle = 0;
	}
	
	public int getOutput() {
		return output;
	}
	
	public int getNumberOfCycles() {
		return currentCycle;
	}

	public ArrayList<Sextet<String,Integer,Integer,Integer,Integer,Double>> getState() {
		return state;
	}

	public void addStation(Station station) {
		if(!this.stations.isEmpty()) {
			station.setUpstream(this.stations.getLast());
			this.stations.getLast().setDownstream(station);
		}
		this.stations.add(station);
		System.out.println(station.getId() + " added.");
	}
	public int doCycle() {
		Iterator<Station> iterator = stations.iterator();
		int i = 0;
		int moved = 0;
		this.currentCycle++;
		while(iterator.hasNext()) {
			Station station = iterator.next();
			station.receive(moved);
			int roll = Configuration.RANDOMS[Configuration.CYCLES*(this.currentCycle - 1) + i++];
			moved = station.move(roll);
			Sextet<String,Integer,Integer,Integer,Integer,Double> s = new Sextet<String,Integer,Integer,Integer,Integer,Double>(
					station.getId(),
					this.getNumberOfCycles(),
					roll,
					moved,
					station.getWip(),
					station.getDeviation());
			this.state.add(s);
		}
		this.output += moved;
		return moved;
	}
	public void reset() {
		Iterator<Station> iterator = this.stations.iterator();
		while(iterator.hasNext()) {
			iterator.next().reset();
		}
	}
}
