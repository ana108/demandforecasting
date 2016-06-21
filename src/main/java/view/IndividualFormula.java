package view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

import controller.Formula;

public abstract class IndividualFormula {
	protected String userData;
	protected String result;
	protected Formula f;
	protected boolean visible;
	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isVisible() {
		if(result == null){
			return false;
		}
		else{
			return true;
		}
		//return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	protected List<Double> convert(String l){
		String delimiter;
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
				Double.parseDouble(x.trim()); // TODO: multiple spaces --> use regex
				data.add((double)(Double.parseDouble(x)));
			} catch (NumberFormatException e) {
				throw new ValidatorException(
						new FacesMessage("Invalid input",
								"Invalid character in file. Please include only numbers"));
			}
		}// for
		return data;
	}
	protected void calculateGeneric(String name) {
		DecimalFormat df = new DecimalFormat("#.00");
		double prediction = 0.00;
		if(userData.length() == 0){
			System.out.println("could not retrieve data.");
		}
		f = new Formula(convert(userData));
		if (name.equals("movingAverageDefect")) {
			prediction = Double.valueOf(df.format(f.getMovingAverageDefect()));
		}
		if (name.equals("movingAverageFC")) {
			prediction = Double.valueOf(df.format(f.getMovingAverageFC()));
		}
		if (name.equals("naiveMethodDefect")) {
			prediction = Double.valueOf(df.format(f.getNaiveMethodDefect()));
		}
		if (name.equals("naiveMethodFC")) {
			prediction = Double.valueOf(df.format(f.getNaiveMethodFC()));
			}
		if (name.equals("naiveTrendMethodDefect")) {
			prediction = Double.valueOf(df.format(f.getNaiveTrendMethodDefect()));
			}
		if (name.equals("naiveTrendMethodFC")) {
			prediction = Double.valueOf(df.format(f.getNaiveTrendMethodFC()));
			}
		if(name.equals("weighedMovingAverageDefect")){
			prediction = Double.valueOf(df.format(f.getWeighedMovingAverageDefect()));
		}
		if (name.equals("weighedMovingAverageFC")) {
			prediction = Double.valueOf(df.format(f.getWeighedMovingAverageFC()));
		}
		result = String.valueOf(prediction);
		visible = true;
	}
}
