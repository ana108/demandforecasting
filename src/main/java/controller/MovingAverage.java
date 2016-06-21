package controller;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean(name = "MovingAverage", eager = true)
@SessionScoped
public class MovingAverage {
	private int demandInput1;
	private int demandInput2;
	private int demandInput3;
	private int result = 0;
	private Part demandFile;
	
	/*MovingAverage(){
		result = 0;
	}*/
	public int getDemandInput2() {
		return demandInput2;
	}
	public void setDemandInput2(int demandInput2) {
		this.demandInput2 = demandInput2;
	}
	public int getDemandInput1() {
		return demandInput1;
	}
	public void setDemandInput1(int demandInput1) {
		this.demandInput1 = demandInput1;
	}
	public int getDemandInput3() {
		return demandInput3;
	}
	public void setDemandInput3(int demandInput3) {
		this.demandInput3 = demandInput3;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Part getDemandFile() {
		return demandFile;
	}
	public void setDemandFile(Part demandFile) {
		this.demandFile = demandFile;
	}
	/* End of Getters and Setters */
	public String calculateMovingAvg(){
		if(demandFile != null){
			Stack<Integer> muchAdoAboutNothing = readFile();
			if(muchAdoAboutNothing == null)
				System.out.println("Error. Could not open file");
			else{
				/*TODO Check for end of file, or.... check size*/
				demandInput1 = muchAdoAboutNothing.pop();
				demandInput2 = muchAdoAboutNothing.pop();
				demandInput3 = muchAdoAboutNothing.pop();
			}
		}
		result = (demandInput1 + demandInput2 + demandInput3)/3;
		return "index";
	}
	
	public Stack<Integer> readFile(){
		Stack<Integer> s = new Stack<Integer>();

		try {
				Scanner sc = new Scanner(demandFile.getInputStream());
				
				while(sc.hasNext()){
					String line = sc.nextLine();
					for(String x : line.split(","))
						s.push(Integer.parseInt(x));
				}
				
				sc.close();
				
			} catch (IOException e) {
				return null;
			}
		return s;
	}
}
