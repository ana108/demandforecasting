package view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import controller.Formula;

@ManagedBean(name="BestFormula")
@RequestScoped
public class BestFormula {
	private Part dataFile;
	private double defectRate;
	private String bestFormula;
	private List<Double> data = new ArrayList<Double>();
	private Formula f;
	private boolean visible = false;
	private String delimiter = " ";
	private String description;

	private String weighedMovingAverageData;
	
	public Part getDataFile() {
		return dataFile;
	}

	public void setDataFile(Part dataFile) {
		this.dataFile = dataFile;
	}

	public String getWeighedMovingAverageData() {
		return weighedMovingAverageData;
	}

	public void setWeighedMovingAverageData(String weighedMovingAverageData) {
		this.weighedMovingAverageData = weighedMovingAverageData;
	}

	public double getDefectRate() {
		return defectRate;
	}

	public void setDefectRate(double defectRate) {
		this.defectRate = defectRate;
	}
	
	public String getBestFormula() {
		return this.bestFormula;
	}

	public void setBestFormula(String bestFormula) {
		this.bestFormula = bestFormula;
	}
	
	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}
	
	public Formula getF() {
		return f;
	}

	public void setF(Formula f) {
		this.f = f;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public String getDescription() {
		if (description == null) {
			this.description = "This tool will help you figure out which formula will give the best results based on previous demand."
					+ " Please select a comma separated csv or txt file with historical data. Coma and space separated";
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void readFile() {
		try {
			Scanner sc = new Scanner(dataFile.getInputStream());
			String sampleText;
			while (sc.hasNext()) {

				sampleText = sc.nextLine();

				if (sampleText.contains(","))
					delimiter = ",";
				else
					delimiter = " ";

				String[] line = sampleText.split(delimiter);
				for (String x : line) {
					if (!Double.isNaN(Double.parseDouble(x)))
						data.add((double) (Double.parseDouble(x)));
				}// for
			}// while
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String calculateFormula() {
		readFile();
		if (data.isEmpty())
			System.err.print(" no data ");

		f = new Formula(data);
		bestFormula = f.findLowestDefectRate();
		defectRate = f.getDefectRate();
		DecimalFormat df = new DecimalFormat("#.00");
		defectRate = Double.valueOf(df.format(defectRate));
		visible = true;
		return "bestformula";
	}
	
	public void localeChanged(ValueChangeEvent e){
		 dataFile = (Part)e.getNewValue();
	     calculateFormula();
		}
	private List<Double> convert(String l){
		List<Double> data = new ArrayList<Double>();
		String plainText = l;
		String[] line;
		if (plainText.contains(",")) {
			line = plainText.trim().split(",");
			delimiter = ",";
		} else {
			delimiter = " ";
			line = plainText.split(delimiter);
		}

		for (String x : line) {
			try {
				Double.parseDouble(x.trim()); // TODO: multiple spaces
				data.add((double)(Double.parseDouble(x)));
			} catch (NumberFormatException e) {
				throw new ValidatorException(
						new FacesMessage("Invalid input",
								"Invalid character in file. Please include only numbers"));
			}
		}// for
		return data;
	}
	public void validateFile(FacesContext context, UIComponent component,
			Object value) {
		try {
			if (value == null)
				System.err.print("didn't get name of file properly");// TODO
																		// throw
																		// err
																		// here

			Part f = (Part) value;
			Scanner sc = new Scanner(f.getInputStream());

			while (sc.hasNext()) {
				String plainText = sc.nextLine();
				String[] line;
				if (plainText.contains(",")) {
					line = plainText.trim().split(",");
					delimiter = ",";
				} else {
					delimiter = " ";
					line = plainText.split(delimiter);
				}

				for (String x : line) {
					try {
						Double.parseDouble(x.trim()); // TODO: multiple spaces
						// data.add((double)(Double.parseDouble(x)));
					} catch (NumberFormatException e) {
						sc.close();
						throw new ValidatorException(
								new FacesMessage("Invalid input",
										"Invalid character in file. Please include only numbers"));
					}

				}// for
			}// while
			sc.close();
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage(
					"could not open file", "couldn't open file"));
		}
	}// validateFile

	public ValidatorException getValidateFile() {
		return new ValidatorException(new FacesMessage("could not open file",
				"couldn't open file"));
	}
	
	public double calculateMe(String name) {
		DecimalFormat df = new DecimalFormat("#.00");
		double result = 0.00;
		f = new Formula(convert(weighedMovingAverageData));
		if (name.equals("movingAverageDefect")) {
			result = Double.valueOf(df.format(f.getMovingAverageDefect()));
		}
		if (name.equals("movingAverageFC")) {
			result = Double.valueOf(df.format(f.getMovingAverageFC()));
		}
		if (name.equals("naiveMethodDefect")) {
			result = Double.valueOf(df.format(f.getNaiveMethodDefect()));
		}
		if (name.equals("naiveMethodFC")) {
			result = Double.valueOf(df.format(f.getNaiveMethodFC()));
			}
		if (name.equals("naiveTrendMethodDefect")) {
			result = Double.valueOf(df.format(f.getNaiveTrendMethodDefect()));
			}
		if (name.equals("naiveTrendMethodFC")) {
			result = Double.valueOf(df.format(f.getNaiveTrendMethodFC()));
			}
		if(name.equals("weighedMovingAverageDefect")){
			result = Double.valueOf(df.format(f.getWeighedMovingAverageDefect()));
		}
		if (name.equals("weighedMovingAverageFC")) {
			System.out.println(this.weighedMovingAverageData);
			//result = weighedMovingAverageData;
			//result = Double.valueOf(df.format(f.getWeighedMovingAverageFC()));
		}
		return result;
	}
	public void calculateWeighedMovingAverage(){
		System.out.println("Calculate Weighed Moving Average");
		DecimalFormat df = new DecimalFormat("#.00");
		double result = 0.00;
		System.out.println("Starting value");
		System.out.println("User Input: " + this.weighedMovingAverageData);
		f = new Formula(convert(weighedMovingAverageData));
		System.out.println("User Input: " + this.weighedMovingAverageData);
		result = Double.valueOf(df.format(f.getMovingAverageDefect()));
		System.out.println("User Input: " + this.weighedMovingAverageData);
		System.out.println("Result: " + result);
		this.weighedMovingAverageData = String.valueOf(result);
	}
}
