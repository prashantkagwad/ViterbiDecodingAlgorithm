package com.vda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author Prashant Kagwad [pdk130030]
 * 
 */
public class ReadFile {

	public ReadFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Data data = new Data();

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void getInputdata(String fileName) {

		FileReader file = null;
		BufferedReader reader = null;
		String line = "";

		try {

			file = new FileReader(fileName);
			reader = new BufferedReader(file);
			int count = 1;
			while ((line = reader.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(line);

				switch (count) {
				case 1:
					if (st.countTokens() == 1) {
						data.setNumberOfStates(Integer.parseInt(st.nextToken()));
						count++;
					}
					break;

				case 2:
					int nos = data.getNumberOfStates();
					double isp[] = new double[nos];
					if (st.countTokens() == nos) {
						for (int i = 0; i < nos; i++) {
							isp[i] = Double.parseDouble(st.nextToken());
						}
						count++;
					}

					data.setiSP(isp);
					break;

				case 3:
					nos = data.getNumberOfStates();
					double tp[][] = new double[nos][nos];
					if (st.countTokens() == (nos * nos)) {
						for (int i = 0; i < nos; i++) {
							for (int j = 0; j < nos; j++) {
								tp[i][j] = Double.parseDouble(st.nextToken());
							}
						}

						data.setTransProbabilities(tp);
						count++;
					}
					break;

				case 4:
					if (st.countTokens() == 1) {
						data.setNumberOfOutputSymbol(Integer.parseInt(st
								.nextToken()));
						count++;
					}
					break;

				case 5:
					int noos = data.getNumberOfOutputSymbol();
					if (st.countTokens() == noos) {

						String outputAlphabet[] = new String[noos];
						for (int i = 0; i < noos; i++) {
							outputAlphabet[i] = st.nextToken();
						}

						data.setOutputAlpha(outputAlphabet);
						count++;
					}
					break;

				case 6:
					int n = data.getNumberOfStates();
					int m = data.getNumberOfOutputSymbol();
					if (st.countTokens() == (n * m)) {

						double outputDistribution[][] = new double[n][m];
						for (int i = 0; i < n; i++) {
							for (int j = 0; j < m; j++) {
								outputDistribution[i][j] = Double
										.parseDouble(st.nextToken());
							}
						}

						data.setOutputDistribution(outputDistribution);
						count++;
					}
					break;

				default:
					System.out.println("Something went wrong in the switch");
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (file != null) {
				try {
					reader.close();
					file.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String[] getTestData(String fileName) {

		FileReader file = null;
		BufferedReader reader = null;
		String line = "";
		String outputString[] = null;

		try {

			file = new FileReader(fileName);
			reader = new BufferedReader(file);
			while ((line = reader.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(line);
				outputString = new String[st.countTokens()];

				for (int i = 0; i < outputString.length; i++) {
					outputString[i] = st.nextToken();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (file != null) {
				try {
					reader.close();
					file.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return outputString;
	}

	public void printData() {

		System.out.println("Printing data read from file : \n");

		System.out.println("Number of States : " + data.getNumberOfStates());

		System.out.println("Initial State Probablilties : ");
		double[] x = data.getiSP();
		System.out.print("[ ");
		for (int i = 0; i < x.length; i++) {

			System.out.print(x[i] + " ");
		}
		System.out.print("]\n");

		System.out.println("Transaction Probablities : ");
		double[][] y = data.getTransProbabilities();
		for (int i = 0; i < y.length; i++) {
			for (int j = 0; j < y[i].length; j++) {
				System.out.print(y[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("Number of Output Symbols : "
				+ data.getNumberOfOutputSymbol());

		System.out.println("Output Chars :");
		String[] z = data.getOutputAlpha();
		System.out.print("[ ");
		for (int i = 0; i < z.length; i++) {
			System.out.print(z[i] + " ");
		}
		System.out.print("]\n");

		System.out.println("Output Distribution :");
		double[][] a = data.getOutputDistribution();
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}
}