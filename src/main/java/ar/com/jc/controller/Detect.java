package ar.com.jc.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Detect {

	private static Pattern dnaPatter = Pattern.compile("([ATCG])\\1{3}");
	private static Detect instance;
	
	private Detect() {
	} 
	
	public static final Detect instance() {
		if (instance == null) {
			instance = new Detect();
		}
		return instance;
	} 
	  
	public boolean isMutant(String[] adn) {
		char[][] matrix = convertToMatrix(adn);
		return	horizontal(matrix) || vertical(matrix) || oblicua(matrix);
	}

	private char[][] convertToMatrix(String[] patters) {
		char[][] matrix = new char[patters.length][];
		int index = 0;
		for (String secuence : patters) {
			matrix[(index++)] = secuence.toCharArray();
		}
		return matrix;
	}

	private boolean horizontal(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			if (verifyPatter(String.valueOf(matrix[i]))) {
				return true;
			}
		}
		return false;
	}

	private boolean vertical(char[][] matrix) {
		return horizontal(trasposeMatrix(matrix));
	}

	public static char[][] trasposeMatrix(char[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		char[][] trasposedMatrix = new char[n][m];
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				trasposedMatrix[x][y] = matrix[y][x];
			}
		}
		return trasposedMatrix;
	}

	private boolean verifyPatter(String patter) {
		Matcher matcher = dnaPatter.matcher(patter);
		return matcher.find();
	}

	private boolean oblicua(char[][] matrix) {
		if (matrix.length >= 4){
			for (int i = 0; i < matrix.length; i++) {
				
				String patters = ""; 
				for (int l = 0; l <= i; l++) {
					patters += matrix[l][i - l];
				}
				
				if (verifyPatter(patters)) {
					return true;
				}
				 
				patters = "";
				for (int l = 0; l + i < matrix[i].length; l++) {
					patters += matrix[i + l][l];
				}
				
				if (verifyPatter(patters)) {
					return true;
				}
				
				if (i != 0) {
					 
					int j = matrix[i].length - 1;
					patters = "";
					for (int l = 0; i + l < matrix[i].length; l++) {
						patters += matrix[i + l][j - l];
					}
					 
					if (verifyPatter(patters)) {
						return true;
					}
					 
					j -= i;
					patters = "";
					for (int l = 0; j + l < matrix[i].length; l++) {
						patters += matrix[l][j + l];
					}
					
					if (verifyPatter(patters)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
