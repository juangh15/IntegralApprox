package com.Integrales.Main;
import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		int i;
		Scanner entry = new Scanner(System.in);
		
		String expresion = entry.next();
		String aux;
		parentesis(expresion);
//		for(i=0;i<expresion.length();i++) {
//			aux = expresion.substring(i,i+1);
//			System.out.println(aux);
//			if(aux.equalsIgnoreCase("S")) {
//				if(expresion.substring(i,i+4).equalsIgnoreCase("Sin(")) {
//					System.out.println("Seno de ");
//					i = i+3;
//				}
//				else {
//					System.out.println("Error not supported expression");
//					break;
//				}
//			}
//			
//			
//			
//		}
		

		

//		double aux=0.0,result=0.0;
//		System.out.println("Enter the lower bound of the integral: ");
//		double lowerBound = entry.nextDouble();
//		
//		System.out.println("Enter the upper bound of the integral: ");
//		double upperBound = entry.nextDouble();
//		
//		System.out.println("Enter the number of terms for the accuracy (higher values = higher cpu work and higher value accuracy): ");
//		int n = entry.nextInt();
//		
//		double differential = (upperBound - lowerBound)/n;
//		
//		System.out.println("Enter the function (using ^ for exponential notation) ");
//		String function = entry.next();
//		
//		
//
//		
//		for(i=1;i<=n;i++) {
//			aux+=(Math.cos(lowerBound+i*differential))*differential;
//		}
//		System.out.println("The value is approximately: "+aux);
//		
		entry.close();
	}
	
	public static int buscaParentesis(String evaluar) {
		int k = -1,i;
		for(i=0;i<evaluar.length();i++) {
			if(evaluar.substring(i,i+1).equals(")")) {
				return i;
			}
		}
		return k;
	}
	
	public static String operacion(String operando, String eval1, String eval2) {
		
		int number1 = Integer.parseInt(eval1);
		int number2 = Integer.parseInt(eval2);
		int conversion;
		String result="";
		if (operando.equals("+")) {
			conversion =  number1 + number2;
			result = String.valueOf(conversion);
		}
		else if (operando.equals("-")) {
			conversion =  number1 - number2;
			result = String.valueOf(conversion);
		}
		else if (operando.equals("*")) {
			conversion =  number1 * number2;
			result = String.valueOf(conversion);
		}
		else if (operando.equals("/")) {
			conversion =  number1 / number2;
			result = String.valueOf(conversion);
		}
		return result;
	}
	
	public static String parentesis(String evaluar) {
		int final_index = 0;
		int index_parentesis = 0;
		int i;
		int conversion;
		int number1, number2;
		String eval1 = "", eval2 = "";
		String anterior = "";
		String actual = "";
		String operando = "";
		for (i = 0; i < evaluar.length(); i++) {
			actual = evaluar.substring(i, i + 1);
			if (actual.equals(")")) {
				final_index = i;

				if (eval1.equalsIgnoreCase("")) {
					eval1 = anterior;
					anterior = "";
				} else {
					eval2 = anterior;
					eval1 = operacion(operando, eval1, eval2);
					
					anterior = "";
				}

				eval2 = anterior;
				break;
			} else if (actual.equals("+")||actual.equals("-")||actual.equals("*")||actual.equals("/")) {

				if (eval1.equalsIgnoreCase("")) {
					eval1 = anterior;
					anterior = "";
				} else {
					eval2 = anterior;
					eval1 = operacion(operando, eval1, eval2);
					
					anterior = "";
				}

				operando = actual;

			} else if (actual.equals("(")) {
				
				index_parentesis = buscaParentesis(evaluar.substring(i,evaluar.length()))+i;
				eval2 = parentesis(evaluar.substring(i+1,index_parentesis+1));
				eval1 = operacion(operando, eval1, eval2);
				anterior = "";
				
				i = index_parentesis+1;
				
				
				
			} else {
				anterior += actual;
			}
		}
		System.out.println(eval1);
		return eval1;
	}
}
