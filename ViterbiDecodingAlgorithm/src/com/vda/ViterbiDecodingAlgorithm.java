package com.vda;

public class ViterbiDecodingAlgorithm {

	static int path[][];
	static String finalPath = " ";

	public ViterbiDecodingAlgorithm() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Check for number of arguments.
			if (args.length != 2) {
				System.err.println("You must call ViterbiDecodingAlgorithm as "
						+ "follows:\n\njava ViterbiDecodingAlgorithm "
						+ "<modelFileName> <testFilename> \n");
				System.exit(1);
			}

			// Read the model file.
			ReadFile modelFile = new ReadFile();
			String modelFileName = System.getProperty("user.dir") + "\\Input\\"
					+ args[0];
			modelFile.getInputdata(modelFileName);
			Data data = modelFile.getData();
			// modelFile.printData();

			// Read the test file.
			ReadFile testFile = new ReadFile();
			String testSetFileName = System.getProperty("user.dir")
					+ "\\Input\\" + args[1];
			String x[] = testFile.getTestData(testSetFileName);

			// Variables to store the result from the viterbi forward table
			// calculation.
			path = new int[x.length][data.getNumberOfStates()];
			double massiveData[][] = new double[x.length][data
					.getNumberOfStates()];
			double[] PI = data.getiSP();
			double[][] Beta = data.getOutputDistribution();
			Max m = new Max();

			// Run for the output string pattern (Virterbi Decoding - Forward
			// Calculations)
			for (int rows = 0; rows < x.length; rows++) {
				// Starting at time, t=1 , for number of time instances

				for (int cols = 0; cols < data.getNumberOfStates(); cols++) {
					// for number of states.

					if (rows == 0) {
						// Initialization

						if (x[rows].equalsIgnoreCase("a")) {
							massiveData[rows][cols] = PI[cols] * Beta[cols][0];
							// path[0][cols] = ;
						} else {
							massiveData[rows][cols] = PI[cols] * Beta[cols][1];
							// path[0][cols] = " ";
						}
					} else {
						// Iterative Step calculation in terms of a matrix

						double valFromS1 = 0;
						double valFromS2 = 0;
						double valFromS3 = 0;

						if (x[rows].equalsIgnoreCase("a")) {
							// For symbol a

							// Calculate all the possible values from pervious
							// time stamp
							valFromS1 = massiveData[rows - 1][0]
									* data.getTransProbabilities()[0][cols]
									* Beta[cols][0];
							valFromS2 = massiveData[rows - 1][1]
									* data.getTransProbabilities()[1][cols]
									* Beta[cols][0];
							valFromS3 = massiveData[rows - 1][2]
									* data.getTransProbabilities()[2][cols]
									* Beta[cols][0];

							// find the max vaule and the parent node for
							// backtracking
							m = findMax(valFromS1, valFromS2, valFromS3);
							path[rows][cols] = m.getPath();
							massiveData[rows][cols] = m.getMax();

						} else {
							// For symbol c

							// Calculate all the possible values from pervious
							// time stamp
							valFromS1 = massiveData[rows - 1][0]
									* data.getTransProbabilities()[0][cols]
									* Beta[cols][1];
							valFromS2 = massiveData[rows - 1][1]
									* data.getTransProbabilities()[1][cols]
									* Beta[cols][1];
							valFromS3 = massiveData[rows - 1][2]
									* data.getTransProbabilities()[2][cols]
									* Beta[cols][1];

							// find the max vaule and the parent node for
							// backtracking
							m = findMax(valFromS1, valFromS2, valFromS3);
							path[rows][cols] = m.getPath();
							massiveData[rows][cols] = m.getMax();
						}
					}
				}
			}

			// To print the forward possibilities matrix calculated from
			// viterbis algo.
			// for (int i = 0; i < massiveData.length; i++) {
			// for (int j = 0; j < massiveData[i].length; j++) {
			//
			// System.out.print(massiveData[i][j] + "\t");
			// }
			// System.out.println();
			// }

			int last = massiveData.length;
			Max max = findMax(massiveData[last - 1][0],
					massiveData[last - 1][2], massiveData[last - 1][2]);

			// Backtracking the path
			int h = max.getPath();
			finalPath = "S" + (path[x.length - 1][h] + 1);
			for (int i = x.length - 1; i > 0; i--) {
				finalPath = finalPath + " <- S" + (path[i][h] + 1);
				h = path[i][h];
			}

			// Printing the path
			System.out.println("Path taken : ");
			System.out.println(finalPath);

		} catch (Exception e) {
			System.out.println("Some Exception occured in main.");
			e.printStackTrace();
		}
	}

	/**
	 * @param x
	 *            - Value 1
	 * @param y
	 *            - Value 2
	 * @param z
	 *            - Value 3
	 * @return - Max object - that contains the max value and the state from
	 *         which this value came from.
	 */
	public static Max findMax(double x, double y, double z) {

		Max m = new Max();
		try {

			if (x < y) {
				m.setMax(y);
				m.setPath(1);
			} else {
				m.setMax(x);
				m.setPath(0);
			}

			if (m.getMax() < z) {
				m.setMax(z);
				m.setPath(2);
			}
		} catch (Exception e) {
			System.out.println("Some Exception occured while finding max.");
			e.printStackTrace();
		}
		return m;
	}
}
