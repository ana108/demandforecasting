package controller;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import view.BestFormula;

public class FormulaTest extends TestCase {

	public void testNaiveMethodBase() {
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(2.0);
		data.add(3.0);
		data.add(4.0);
		data.add(5.0);
		data.add(5.0);
		Formula f = new Formula(data);
		assertEquals("Should return the last value in the data", 5.0, f.naiveMethod());
	}

	/*public void testNaiveMethodSingleValue() {
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		Formula f = new Formula(data);
		assertEquals("Should return the last value in the data", 1.0, f.naiveMethod());
	}*/
	
	public void testNaiveMethodDuplicateValue() {
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(1.0);
		data.add(1.0);
		Formula f = new Formula(data);
		assertEquals("Should return the last value in the data", 1.0, f.naiveMethod());
	}
	
	public void testNaiveMethodRound() {
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(1.0);
		data.add(1.0);
		data.add(3.25);
		Formula f = new Formula(data);
		assertEquals("Should return the last value in the data", 3.25, f.naiveMethod());
	}
	
	/*public void testNaiveMethodRoundUp() {
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(1.0);
		data.add(1.0);
		data.add(3.255);
		Formula f = new Formula(data);
		f.naiveMethod();
		BestFormula bf = new BestFormula();
		bf.setF(f);
		assertEquals("Should correctly round up for the user", 3.26,bf.calculate("naiveMethodFC"));
	}*/
	
	public void testMovingAverageDuplicates() {
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(1.0);
		data.add(1.0);
		data.add(1.0);
		data.add(1.0);
		Formula f = new Formula(data);
		assertEquals("Moving average", 1.0, f.movingAverage(2));
	}
	
	public void testMovingAverageDifferent() {
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(2.0);
		data.add(1.0);
		data.add(2.0);
		data.add(1.0);
		Formula f = new Formula(data);
		assertEquals("Moving average", 1.5, f.movingAverage(2));
	}
	
	/*public void testMovingAverageSingleValue() {
		List<Double> data = new ArrayList<Double>();
		data.add(3.0);
		Formula f = new Formula(data);
		assertEquals("Moving average", 3, f.movingAverage(1));
	}*/
	
	public void testMovingAverageVariety() {
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(2.0);
		data.add(3.0);
		data.add(4.0);
		data.add(10.0);
		data.add(2.0);
		Formula f = new Formula(data);
		assertEquals("Moving average", 5.3333333333333333, f.movingAverage(3));
	}
	
	/*public void testMovingAverageVarietyRound() {
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(2.0);
		data.add(3.0);
		data.add(4.0);
		data.add(5.0);
		data.add(5.555);
		data.add(3.499);
		Formula f = new Formula(data);
		f.movingAverage(3);
		BestFormula bf = new BestFormula();
		bf.setF(f);
		assertEquals("Moving average", 4.69, bf.calculate("movingAverageFC"));
	}*/
	
	public void testWeighedMovingAverageSingleValue() {
		List<Double> data = new ArrayList<Double>();
		data.add(3.0);
		Formula f = new Formula(data);
		assertEquals("Single Value Weighed Moving Average", 3.0, f.weighedMovingAverage(1));
	}

	public void testWeighedMovingAverage() {
		List<Double> data = new ArrayList<Double>();
		data.add(3.0);
		data.add(4.0);
		data.add(2.0);
		data.add(5.0);
		data.add(3.0);
		Formula f = new Formula(data);
		assertEquals("Single Value Weighed Moving Average", 3.5, f.weighedMovingAverage(2));
	}
	
	public void testWeighedMovingDuplicates() {
		List<Double> data = new ArrayList<Double>();
		data.add(3.0);
		data.add(3.0);
		data.add(3.0);
		data.add(3.0);
		data.add(3.0);
		Formula f = new Formula(data);
		assertEquals("Duplicates Values Weighed Moving Average", 3.0, f.weighedMovingAverage(0));
	}
	
	public void testWeighedMovingDuplicatesDecimals() {
		List<Double> data = new ArrayList<Double>();
		data.add(2.55);
		data.add(2.55);
		data.add(2.55);
		data.add(2.55);
		data.add(2.55);
		Formula f = new Formula(data);
		assertEquals("Duplicate Values Weighed Moving Average", 2.55, f.weighedMovingAverage(0));
	}
	
	/*public void testExponentialSmoothing(){
		List<Double> data = new ArrayList<Double>();
		data.add(2.0);
		data.add(4.0);
		Formula f = new Formula(data);
		assertEquals("Exponential Smoothing: 2 Values", 3.0, f.exponentialSmoothing(0.5));
	}*/
	
	/*public void testExponentialSingleValue(){
		List<Double> data = new ArrayList<Double>();
		data.add(2.0);
		Formula f = new Formula(data);
		assertEquals("Exponential Smoothing: 1 Value", 2.0, f.exponentialSmoothing(0.5));
	}*/
	
	/*public void testExponentialSmoothingManyValues(){
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(5.0);
		data.add(7.0);
		data.add(3.0);
		data.add(9.0);
		data.add(11.0);
		Formula f = new Formula(data);
		assertEquals("Exponential Smoothing: Many Values", 8.666666666666666, f.exponentialSmoothing(0.5));
	}*/
	
	/*public void testExponentialSmoothingThreeValues(){
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(5.0);
		data.add(9.0);
		Formula f = new Formula(data);
		assertEquals("Exponential Smoothing: Many Values", 6.0, f.exponentialSmoothing(0.5));
	}*/
	/*public void testNaiveTrendMethodSingle(){
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		Formula f = new Formula(data);
		assertEquals("Naive Trend Method Single Value, ", 1.0, f.naiveTrendMethod());
	}*/
	public void testNaiveTrendMethodTwoValues(){
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(3.0);
		Formula f = new Formula(data);
		assertEquals("Naive Trend Method Two Values, ", 5.0, f.naiveTrendMethod());
	}
	public void testNaiveTrendMethod(){
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(3.0);
		data.add(5.0);
		Formula f = new Formula(data);
		assertEquals("Naive Trend Method, ", 7.0, f.naiveTrendMethod());
	}
	public void testNaiveTrendMethodDuplicate(){
		List<Double> data = new ArrayList<Double>();
		data.add(3.0);
		data.add(3.0);
		data.add(3.0);
		Formula f = new Formula(data);
		assertEquals("Naive Trend Method, ", 3.0, f.naiveTrendMethod());
	}
	public void testNaiveTrendMethodDecreasing(){
		List<Double> data = new ArrayList<Double>();
		data.add(32.0);
		data.add(31.0);
		data.add(30.0);
		data.add(29.0);
		data.add(28.0);
		Formula f = new Formula(data);
		assertEquals("Naive Trend Method, ", 27.0, f.naiveTrendMethod());
	}
	
	public void testNaiveDoubleTrendMethod(){
		List<Double> data = new ArrayList<Double>();
		data.add(1.0);
		data.add(2.0);
		data.add(4.0);
		data.add(7.0);
		data.add(11.0);
		data.add(16.0);
		data.add(22.0);
		data.add(29.0);
		Formula f = new Formula(data);
		assertEquals("Naive Double Trend, ", 37.0, f.naiveDoubleTrendMethod());
	}
	/*public void testLinearTrendEquation(){
		List<Double> data = new ArrayList<Double>();
		data.add(700.0);
		data.add(724.0);
		data.add(720.0);
		data.add(728.0);
		data.add(740.0);
		data.add(742.0);
		data.add(758.0);
		data.add(750.0);
		data.add(770.0);
		data.add(775.0);
		Formula f = new Formula(data);
		assertEquals("Linear Trend Equation Result ", 782.005, f.linearTrendEquation());
	}*/
	/*public void testLinearTrendEquationPartB(){
		List<Double> data = new ArrayList<Double>();
		data.add(700.0);
		data.add(724.0);
		data.add(720.0);
		data.add(728.0);
		data.add(740.0);
		data.add(742.0);
		data.add(758.0);
		data.add(750.0);
		data.add(770.0);
		data.add(775.0);
		data.add(782.01);
		Formula f = new Formula(data);
		assertEquals("Linear Trend Equation Result ", 789.515, f.linearTrendEquation());
	}*/
	public void testLinearTrendEquationPartC(){
		List<Double> data = new ArrayList<Double>();
		data.add(100.0);
		data.add(93.0);
		data.add(105.0);
		data.add(107.0);
		data.add(98.0);
		data.add(110.0);
		data.add(115.0);
		Formula f = new Formula(data);
		assertEquals("Linear Trend Equation Result ", 0.00, f.linearTrendEquation());
	}
	/*public void testLinearTrendEquationSingleValue(){
		List<Double> data = new ArrayList<Double>();
		data.add(100.0);
		Formula f = new Formula(data);
		assertEquals("Linear Trend Equation Result ", 100.00, f.linearTrendEquation());
	}*/
	/*public void testExponentialEquation(){
		List<Double> data = new ArrayList<Double>();
		data.add(11.3);
		data.add(13.4);
		data.add(16.1);
		data.add(19.2);
		data.add(22.9);
		Formula f = new Formula(data);
		assertEquals("Non linear Trend Equation Result ", 27.3, f.exponentialEquation());
	}*/
	
	/*public void testExponentialEquationPartB(){
		List<Double> data = new ArrayList<Double>();
		data.add(20.0);
		data.add(23.0);
		data.add(30.42);
		data.add(34.98);
		data.add(40.23);
		data.add(46.26);
		data.add(53.2);
		data.add(61.18);
		data.add(70.36);
		data.add(80.91);
		data.add(93.05);
		data.add(107.01);
		data.add(123.0);
		Formula f = new Formula(data);
		assertEquals("Non linear Trend Equation Result ", 141.445, f.exponentialEquation());
	}*/
	/*public void testExponentialEquationSingleValue(){
		List<Double> data = new ArrayList<Double>();
		data.add(55.6);
		Formula f = new Formula(data);
		assertEquals("Non linear Trend Equation Result - Single Value ", 55.6, f.exponentialEquation());
	}*/
	/*public void testTrendAdjustedExponentialSmoothing(){
		List<Double> data = new ArrayList<Double>();
		data.add(700.0);
		data.add(724.0);
		data.add(720.0);
		data.add(728.0);
		data.add(740.0);
		data.add(742.0);
		data.add(758.0);
		data.add(750.0);
		data.add(770.0);
		data.add(775.0);
		Formula f = new Formula(data);
		assertEquals("Non linear Trend Equation Result ", 786.23, f.trendAdjustedExponentialSmoothing(0.4, 0.3));
	}*/
	/*public void testTrendAdjustedExponentialSmoothingSinlgeValue(){
		List<Double> data = new ArrayList<Double>();
		data.add(222.0);
		Formula f = new Formula(data);
		assertEquals("Non linear Trend Equation Result ", 0.00, f.trendAdjustedExponentialSmoothing(0.4, 0.3));
	}*/
	/*public void testSeasonalForecastLinear(){
		//seasonalForecast(int numSeasons, String model)
		List<Double> data = new ArrayList<Double>();
		data.add(66.0);
		data.add(96.0);
		data.add(91.0);
		data.add(66.0);
		data.add(59.0);
		data.add(91.0);
		data.add(84.0);
		data.add(60.0);
		data.add(55.0);
		data.add(82.0);
		data.add(78.0);
		data.add(45.0);
		data.add(46.0);
		data.add(58.0);
		data.add(63.0);
		Formula f = new Formula(data);
		List<Double> seasonalTrend = new ArrayList<Double>();
		seasonalTrend = f.seasonalForecast(4, "linearTrend");
		assertEquals("Seasonal Linear Trend data (decreasing)", 41.193, seasonalTrend.get(0));
		assertEquals("Seasonal Linear Trend data (decreasing)", 38.681, seasonalTrend.get(1));
		assertEquals("Seasonal Linear Trend data (decreasing)", 56.382, seasonalTrend.get(2));
		assertEquals("Seasonal Linear Trend data (decreasing)", 51.431, seasonalTrend.get(3));
	}*/
	/*public void testSeasonalForecastTrendAdjustedExponentialSmoothing(){
		
	}*/
}
