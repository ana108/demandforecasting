package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formula {

	private List<Double> data;
	private double defectRate;
	private double naiveMethodDefect;
	private double naiveTrendMethodDefect;
	private double movingAverageDefect;
	private double naiveMethodFC;
	private double naiveTrendMethodFC;
	private double movingAverageFC;
	private double weighedMovingAverageFC;
	private double weighedMovingAverageDefect;
	private double exponentialSmoothingFC;
	private double exponentialSmoothingDefect;
	private double sumAll;

	public Formula(List<Double> d) {
		data = d;
		setSumAll(0); // zero is not needed, but is there to match signature
		/*movingAverage(d.size() / 2);
		naiveTrendMethod();
		naiveMethod();
		weighedMovingAverage(data.size() / 2);
		weighedMovingAverageDefect();*/
	}

	public double getDefectRate() {
		return defectRate;
	}
	public double getNaiveMethodDefect() {

		return naiveMethodDefect;
	}

	public void setNaiveMethodDefect(double naiveMethodDefect) {
		this.naiveMethodDefect = (naiveMethodDefect / sumAll) * 100;
	}
	public void setDefectRate(double d) {
		this.defectRate = d;
	}
	
	public double getNaiveTrendMethodDefect() {
		DecimalFormat df = new DecimalFormat("#.00");
		naiveTrendMethodDefect = Double.valueOf(df
				.format(naiveTrendMethodDefect));
		return naiveTrendMethodDefect;
	}

	public void setNaiveTrendMethodDefect(double naiveTrendMethodDefect) {
		this.naiveTrendMethodDefect = (naiveTrendMethodDefect / sumAll) * 100;
	}
	
	public double getMovingAverageDefect() {
		DecimalFormat df = new DecimalFormat("#.00");
		movingAverageDefect = Double.valueOf(df.format(movingAverageDefect));
		return movingAverageDefect;
	}

	public void setMovingAverageDefect(double movingAverageDefect) {
		this.movingAverageDefect = (movingAverageDefect / sumAll) * 100;
	}
	
	public double getNaiveMethodFC() {
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.valueOf(df.format(naiveMethodFC));
	}

	public void setNaiveMethodFC(double naiveMethodFC) {
		this.naiveMethodFC = naiveMethodFC;
	}
	
	public double getNaiveTrendMethodFC() {
		DecimalFormat df = new DecimalFormat("#.00");
		if(this.naiveTrendMethodFC == 0.0){
			naiveTrendMethod();
		}
		return Double.valueOf(df.format(naiveTrendMethodFC));
	}

	public void setNaiveTrendMethodFC(double naiveTrendMethodFC) {
		this.naiveTrendMethodFC = naiveTrendMethodFC;
	}
	
	public double getMovingAverageFC() {
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.valueOf(df.format(movingAverageFC));
	}

	public void setMovingAverageFC(double movingAverageFC) {
		this.movingAverageFC = movingAverageFC;
	}
	
	public double getWeighedMovingAverageFC() {
		if(weighedMovingAverageFC==0.0){
			weighedMovingAverage(3);
		}
		return weighedMovingAverageFC;
	}

	public void setWeighedMovingAverageFC(double weighedMovingAverageFC) {
		this.weighedMovingAverageFC = weighedMovingAverageFC;
	}
	
	public double getWeighedMovingAverageDefect() {
		return weighedMovingAverageDefect;
	}

	public void setWeighedMovingAverageDefect(double weighedMovingAverageDefect) {
		this.weighedMovingAverageDefect = weighedMovingAverageDefect;
	}
	
	public double getExponentialSmoothingFC() {
		return exponentialSmoothingFC;
	}

	public void setExponentialSmoothingFC(double exponentialSmoothingFC) {
		this.exponentialSmoothingFC = exponentialSmoothingFC;
	}

	public double getExponentialSmoothingDefect() {
		return exponentialSmoothingDefect;
	}

	public void setExponentialSmoothingDefect(double exponentialSmoothingDefect) {
		this.exponentialSmoothingDefect = exponentialSmoothingDefect;
	}
	
	public double getSumAll() {
		return sumAll;
	}

	public void setSumAll(double s) {
		for (int i = 0; i < data.size(); i++) {
			sumAll += data.get(i);
		}
	}

	protected double naiveMethod() {
		if (!data.isEmpty()) {
			double prediction = data.get(0);
			double demand = data.get(0);
			double sum = 0.0;

			for (int i = 0; i < data.size(); i++) {
				demand = data.get(i);
				sum += Math.abs(prediction - demand);
				prediction = demand;
			}// for

			setNaiveMethodFC(data.get(data.size() - 1));
			setNaiveMethodDefect(sum);
		} else
			System.out.println("Data empty");
		
		return naiveMethodFC;
	}
	//another name for exponential smoothing, except without the smoothing constant
	protected double naiveTrendMethod() {
		if(data.size()==0){
			naiveTrendMethodFC = 0.0;
			return naiveTrendMethodFC;
		}
		if(data.size() == 1){
			naiveTrendMethodFC = data.get(0);
			return naiveTrendMethodFC;
		}
		double lastDifference = data.get(data.size()-1)-data.get(data.size()-2);
		naiveTrendMethodFC = data.get(data.size()-1) + lastDifference;
		return naiveTrendMethodFC;
	}
	protected double naiveDoubleTrendMethod(){
		double lastDemand = data.get(data.size()-1);
		double secondLastDemand = data.get(data.size()-2);
		double thirdLastDemand = data.get(data.size()-3);
		double change = lastDemand - secondLastDemand;
		double secondChange = secondLastDemand - thirdLastDemand;
		double predictedChange = change + (change - secondChange);
		double result = lastDemand + predictedChange;
		return result;
	}
	protected double movingAverage(int numPeriods) {
		if (!data.isEmpty()) {
			double[] movingAverageValues = new double[numPeriods];
			double movingAverage = 0.0;
			double demand = 0.0;
			double sum = 0.0;
			if (data.size() > numPeriods) {
				for (int i = 0; i < numPeriods; i++) {
					movingAverageValues[i] = data.get(i);
					movingAverage += movingAverageValues[i];
				}
				movingAverage = movingAverage / numPeriods;
			}// else decrease numPeriods by 1, or just calculate it if its equal
			for (int i = 0; i < data.size(); i++) {
				demand = data.get(i);
				sum += Math.abs(movingAverage - demand);
				for (int s = 1; s < numPeriods; s++) {
					movingAverageValues[s - 1] = movingAverageValues[s];
				}
				movingAverageValues[numPeriods - 1] = demand;
				for (int i1 = 0; i1 < numPeriods; i1++) {
					movingAverage += movingAverageValues[i1];
				}
				movingAverage = movingAverage / numPeriods;
			}
			double nextEvent = 0.0;
			for (int z = 1; z <= numPeriods; z++) {
				nextEvent += data.get(data.size() - z);
			}
			nextEvent = nextEvent / numPeriods;
			setMovingAverageFC(nextEvent);
			setMovingAverageDefect(sum);
		} else
			System.err.println("Data is empty in moving average");
		
		return movingAverageFC;
	}

	protected double weighedMovingAverage(int numPeriods) {
		double result = 0;
		if(data.isEmpty()){
			return 0.0;
		}
		if (data.size() > 3) {
			int numPeriod = 2;
			//get the average of the last numPeriods of values. Exclude the last one.
			// multiply each of the values by 1/2^numPeriod
			for (int i = (data.size() - 2); i >= data.size() - numPeriods; i--) {
				double tempResult = data.get(i);
				double weight = 1 / Math.pow(2, numPeriod);
				tempResult = tempResult * weight;
				result += tempResult;
				numPeriod++; // increase the index of the period
			}
			
			// to make sure all the weights are equal to one, after calculating all the regular cases, make sure to add
			// the remainder weight to the first value. ie 1-whatever
			double fraction = 0.0;
			for(int i = numPeriods; i > 1; i--){
				fraction += 1/Math.pow(2, i);
			}
			
			double firstValue = data.get(data.size()-1);
			double weight = 0.0;
			weight = (1-fraction);
			result += firstValue*weight;
		}//
		if(data.size() == 3){
			if(numPeriods > 3){numPeriods = 3;}
			if(numPeriods < 2){numPeriods = 2;}
			if(numPeriods == 2){
				result = (data.get(0)*0.75 + data.get(1)*0.25);
			}
			else{
				double remainingWeight = 1 - (1/Math.pow(2, 2) + 1/Math.pow(2, 3));
				result = 0.0;
				result += data.get(data.size()-2)*(1/Math.pow(2, 2)); //equivalent of saying data.get(1)
				result += data.get(data.size()-3)*(1/Math.pow(2, 3)); //equivalent of saying data.get(0)
				result += data.get(data.size()-1)*remainingWeight; //last value //equivalent of saying data.get(2)
			}
		}//if data.size() == 2
		if(data.size() == 2){
			result = (data.get(0)*0.75 + data.get(1)*0.25);
		}
		if(data.size() == 1){
			result = data.get(0);
		}
		weighedMovingAverageFC = result;
		return weighedMovingAverageFC;
	}

	private void weighedMovingAverageDefect() {
		//TODO: implement the error checking formulas.
		weighedMovingAverageDefect = 0.00;
	}
	
	protected double exponentialSmoothing(double smoothingConstant){
		return 0.0;
	}
	protected double linearTrendEquation(){
		return 0.0;
	}
	/* exponential is another name for non-linear*/
	protected double exponentialEquation(){
		return 0.0;
	}
	protected double trendAdjustedExponentialSmoothing(double alpha, double beta){
		return 0.0;
	}
	protected List<Double> seasonalForecast(int numSeasons, String model){
		List<Double> seasonList = new ArrayList<Double>();
		return seasonList;
	}
	protected List<Double> centeredMovingAverage(int numSeasons){
		List<Double> seasonList = new ArrayList<Double>();
		return seasonList;
	}
	public String findLowestDefectRate() {
		// TODO: write a comparator to compare two formulas
		movingAverage(3);
		naiveTrendMethod();
		naiveMethod();

		Map<String, Double> m = new HashMap<String, Double>();
		m.put("movingAverage", movingAverageDefect);
		m.put("naiveTrendMethod", naiveTrendMethodDefect);
		m.put("naiveMethod", naiveMethodDefect);

		double smallest = m.get("movingAverage");

		String result = "Moving Average";
		if (m.get("naiveTrendMethod") < smallest) {
			smallest = m.get("naiveTrendMethod");
			result = "Naive Trend Method";
		}
		if (m.get("naiveMethod") < smallest) {
			smallest = m.get("naiveMethod");
			result = "Naive Method";
		}
		setDefectRate(smallest);
		return result;
	}

} // end class
