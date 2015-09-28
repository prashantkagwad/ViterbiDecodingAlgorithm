package com.vda;

/**
 * @author Prashant Kagwad [pdk130030]
 * 
 */

public class Data {

	private int numberOfStates; // Number of States
	private double[] iSP; // Initial State Probabilities
	private double[][] transProbabilities; // Transition Probabilities
	private int numberOfOutputSymbol; // Number of Output Symbols
	private String[] outputAlpha; // Output Alphabet
	private double[][] outputDistribution;// Output Distributions

	public Data() {
		// TODO Auto-generated constructor stub
	}

	public int getNumberOfStates() {
		return numberOfStates;
	}

	public void setNumberOfStates(int numberOfStates) {
		this.numberOfStates = numberOfStates;
	}

	public double[] getiSP() {
		return iSP;
	}

	public void setiSP(double[] iSP) {
		this.iSP = iSP;
	}

	public double[][] getTransProbabilities() {
		return transProbabilities;
	}

	public void setTransProbabilities(double[][] transProbabilities) {
		this.transProbabilities = transProbabilities;
	}

	public int getNumberOfOutputSymbol() {
		return numberOfOutputSymbol;
	}

	public void setNumberOfOutputSymbol(int numberOfOutputSymbol) {
		this.numberOfOutputSymbol = numberOfOutputSymbol;
	}

	public String[] getOutputAlpha() {
		return outputAlpha;
	}

	public void setOutputAlpha(String[] outputAlpha) {
		this.outputAlpha = outputAlpha;
	}

	public double[][] getOutputDistribution() {
		return outputDistribution;
	}

	public void setOutputDistribution(double[][] outputDistribution) {
		this.outputDistribution = outputDistribution;
	}

}
