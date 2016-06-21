package view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="WeighedMovingAverage")
@ViewScoped
public class WeighedMovingAverage extends IndividualFormula{
	public WeighedMovingAverage(){
		
	}
	public void calculate(){
		this.calculateGeneric("weighedMovingAverageFC");
	}
}