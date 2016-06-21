package view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="NaiveTrend")
@ViewScoped
public class NaiveTrend extends IndividualFormula{
	public void calculate(){
		this.calculateGeneric("naiveTrendMethodFC");
	}
}
