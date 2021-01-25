package numeric_lab_2;

public class SecantMethod {

	/*
	 * Метод хорд з пошуком ділянки локалізації x є [-4; -2,5]
	 * 
	 * x^3 + 3x^2 + 1 = 0
	 */

	public static interface Function {
		double calculateValue(double x);
	}

	public static double start = -4;
	public static double end = -2.5;
	public static double step = 0.1;
	public static double accuracyInPercents = 0.0001;
	public static Function function = (x) -> {
		return Math.pow(x, 3) + 3 * Math.pow(x, 2) + 1;
	};

	public static void main(String[] args) {

		findLocalisation(start, end, step, function);
		
		double variable = start;
		double errorInPercents;
		do {
			
			double variableOld = variable;
			double functionStart = function.calculateValue(start);
			double functionEnd = function.calculateValue(end);
			variable = start - functionStart * ((end - start) / (functionEnd - functionStart));
			double functionValue = function.calculateValue(variable);
			
			if (functionValue * functionStart > 0) {
				start = variable;
			} else {
				end = variable;
			}
			
			errorInPercents = Math.abs((variable - variableOld) / variable) * 100;
			
		} while (errorInPercents >= accuracyInPercents);
		
		System.out.println("x = " + variable);
		System.out.println("value = " + function.calculateValue(variable));
	}

	public static void findLocalisation(double start, double end, double step, Function function) {
		end = start + step;
		double functionStart = function.calculateValue(start);
		double functionEnd = function.calculateValue(end);

		if ((Math.abs(functionEnd) > Math.abs(functionStart)) && (functionStart * functionEnd > 0)) {
			step = step * (-1);
		}

		end = start + step;
		functionEnd = function.calculateValue(end);
		while (functionStart * functionEnd > 0) {
			start = end;
			end = start + step;
			functionStart = function.calculateValue(start);
			functionEnd = function.calculateValue(end);
		}
	}

}
