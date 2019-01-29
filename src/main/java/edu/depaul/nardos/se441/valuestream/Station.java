package edu.depaul.nardos.se441.valuestream;

public class Station {

	private String id;
	private Station upstream;
	private Station downstream;
	private double pCA;
	private int moved;
	private int wip;
	private double deviation;
	
	public Station(String id) {
		this.id = id;
		this.pCA = 1.0;
		this.moved = 0;
		this.wip = 0;
		this.deviation = 0.0;
	}
	public Station(String id, double pca) {
		this.id = id;
		this.pCA = pca;
		this.moved = 0;
		this.wip = 0;
		this.deviation = 0.0;
	}
	
	public String getId() {
		return id;
	}
	public Station getUpstream() {
		return this.upstream;
	}
	public void setUpstream(Station upstream) {
		this.upstream = upstream;
	}
	public Station getDownstream() {
		return this.downstream;
	}
	public void setDownstream(Station downstream) {
		this.downstream = downstream;
	}
	public int getMoved() {
		return moved;
	}
	public void setMoved(int moved) {
		this.moved = moved;
	}
	public void setFirstStation(int wip) {
		this.wip = wip;
	}
	
	public double getpCA() {
		return pCA;
	}
	public int getWip() {
		return wip;
	}
	public double getDeviation() {
		return deviation;
	}
	public int move(int roll) {
		double multiplyingFactor;
		if(this.isFirstStation()) {
			this.wip = roll;
			multiplyingFactor = 1.0;
		} else {
			multiplyingFactor = this.getUpstream().getpCA();
		}
		this.moved = (int) (min(roll, this.wip) * multiplyingFactor);
		this.wip = this.wip - this.moved;
		this.deviation += (this.moved - Configuration.MEAN_EXPECTATION);
		return this.moved;
	}
	public void receive(int moved) {
		this.wip += moved;
	}
	public void reset() {
		this.moved = 0;
		this.wip = 0;
		this.deviation = 0.0;
	}
	private int min(int x, int y) {
		return (x <= y) ? x : y;
	}
	public boolean isFirstStation() {
		return (this.upstream == null);
	}
	public boolean isLastStation() {
		return (this.downstream == null);
	}
}
