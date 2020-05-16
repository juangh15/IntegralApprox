package com.Integrales.Main;
import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		int i;
		
		//Scan for data
		Scanner entry = new Scanner(System.in);

		double aux=0.0,result=0.0,acumulador=0.0;
		System.out.println("Enter the lower bound of the integral: ");
		double lowerBound = entry.nextDouble();
		
		System.out.println("Enter the upper bound of the integral: ");
		double upperBound = entry.nextDouble();
		
		System.out.println("Enter the number of terms for the accuracy (higher values = higher cpu work and higher value accuracy): ");
		int n = entry.nextInt();
		
		double differential = (upperBound - lowerBound)/n;
		
		System.out.println("Enter the function (using ^ for exponential notation) ");
		String function = entry.next();
		
		//Calculations
		for(i=1;i<=n;i++) {
			acumulador = (Double.parseDouble(parentesis(function,lowerBound+i*differential)))*differential;
			//acumulador = Math.sin(lowerBound+i*differential)*differential;
			aux+=acumulador;
		}
		
		//Prints
		System.out.println("\nIntegral from "+lowerBound+" to "+upperBound+" of "+function+" calculated sucessfully!");
		System.out.println("The value is approximately: "+aux);
		System.out.println("With: "+n+" terms of accuracy");
		
		entry.close();
	}
	
	
	//FUNCTION FOR SEARCH PARENTESIS
	public static int buscaParentesis(String evaluar) {
		int k = -1,i;
		int parentesis_abiertos = 0;
		for(i=0;i<evaluar.length();i++) {
			if(evaluar.substring(i,i+1).equals("(")) { parentesis_abiertos++;}
			else if(evaluar.substring(i,i+1).equals(")")) {
				parentesis_abiertos--;
				if(parentesis_abiertos <= 0) {
					return i;
				}
			}
		}
		return k;
	}
	
	//FUNCTION FOR SEARCH CORCHETES
	public static int buscaCorchetes(String evaluar) {
		int k = -1,i;
		for(i=0;i<evaluar.length();i++) {
			if(evaluar.substring(i,i+1).equals("]")) {
				return i;
			}
		}
		return k;
	}
	
	//FUNCTION ONLY FOR OPERATIONS
	public static String operacion(String operando, String eval1, String eval2) {
		double number1,number2,conversion;
		
		if(eval1.equals("")) {
			return eval2;
		}
		if(eval2.equals("")) {
			return eval1;
		}
		number1 = Double.parseDouble(eval1);
		number2 = Double.parseDouble(eval2);
		System.out.println("operando: "+eval1+" "+operando+" "+eval2+" .");
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
	
	//FUNCTION FOR MANEJO DE PARENTESIS
	public static String parentesis(String evaluar, double x) {
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
			
			//Se cierra el parentesis
			if (actual.equals(")")||actual.equals("]")) {
				final_index = i;
				
				//No habia un operando activo o un termino activo
				if (eval1.equalsIgnoreCase("")) {
					eval1 = anterior;
					anterior = "";
				}
				//Habia un operando activo o un termino activo
				else {
					eval2 = anterior;
					eval1 = operacion(operando, eval1, eval2);
					
					anterior = "";
				}
				break;
			}
			//Se encuentra un operando a activar
			else if (actual.equals("+")||actual.equals("-")||actual.equals("*")||actual.equals("/")) {
				
				//No habia un operando activo o un termino activo
				if (eval1.equalsIgnoreCase("")) {
					eval1 = anterior;
					anterior = "";
				}
				//Habia un operando activo o un termino activo
				else {
					eval2 = anterior;
					eval1 = operacion(operando, eval1, eval2);
					
					anterior = "";
				}
				
				//Cambia el operando anterior por el nuevo
				operando = actual;
				System.out.println("Proximo a operar: "+eval1+"con operador. "+operando+" .");

			}
			//Hay un nuevo parentesis, rellama la funcion
			else if (actual.equals("(")) {
				
				index_parentesis = buscaParentesis(evaluar.substring(i,evaluar.length()))+i;
				System.out.println("**Se ha encontrado entre parentesis: "+evaluar.substring(i+1,index_parentesis+1)+"**");
				eval2 = parentesis(evaluar.substring(i+1,index_parentesis+1), x);
				
				
				if(eval1.equals("")) {eval1 = eval2;}
				else {eval1 = operacion(operando, eval1, eval2);}
				
				anterior = "";
				
				i = index_parentesis;
			}
			//FUNCION SENO
			else if(actual.equals("S")) {
				if(evaluar.substring(i,i+4).equalsIgnoreCase("Sin[")) {
					
					index_parentesis = buscaCorchetes(evaluar.substring(i,evaluar.length()))+i;
					eval2 = parentesis(evaluar.substring(i+4,index_parentesis+1),x);
					System.out.println("Seno de "+eval2);
					eval2 = String.valueOf(Math.sin(Double.parseDouble(eval2)));
					
					if(eval1.equals("")) {eval1 = eval2;}
					else {eval1 = operacion(operando, eval1, eval2);}
					System.out.println("es: "+eval2);
					anterior = "";
					
					i = index_parentesis+1;
				}
				else {
					System.out.println("Error not supported expression");
					break;
				}
			
			}
			//X REEMPLAZO por valor dado al inicio
			else if(actual.equals("x")) {
				anterior += String.valueOf(x);
			}
			
			//concatena
			else {
				anterior += actual;
				//si llego al final de la expresion evalua u obtiene el valor actual
				if(i==evaluar.length()-1) {
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
				}
				  
			}
		}
		System.out.println("**cierre con exito de parentesis: "+eval1+" . **");
		return eval1;
	}
}
