import java.util.ArrayList;
import java.util.Scanner;

public class main {
	
	public static void main(String args[]) {
		Scanner leer = new Scanner(System.in);
		String abecedario = "abcdefghijklmnopqrstuvwxyz";
		
		System.out.println("Ingrese la longitud de la cadena:");
		String cadenaOriginal = abecedario.substring(0, leer.nextInt());
		System.out.println("Original: " + cadenaOriginal);
		String revuelta = revolver(cadenaOriginal);
		System.out.println("Revuelta: " + revuelta);
		pancake pancake = new pancake(revuelta, id, 0, 0);
		pancakesHechos.add(pancake);
		ArrayList<pancake> aux = new ArrayList<pancake>();
		aux.add(pancake);
		busqueda(cadenaOriginal, revuelta, aux);
		if(id>1) {
			ArrayList<pancake>camino = new ArrayList<pancake>();
			while (pancakesHechos.get(id-1).getIdPadre() > 0) {
				camino.add(pancakesHechos.get(id-1));
				id = pancakesHechos.get(id-1).getIdPadre();
			}
			for(int i = camino.size()-1; i >= 0; i --) {
				System.out.println("Se movió desde la pos " + camino.get(i).getDesplazados() + ": " + camino.get(i).getTexto());
			}
		}else {
			System.out.println("La cadena no requiere ningun cambio: " + cadenaOriginal);
		}
	}
	
	public static boolean ordenado = false;
	public static ArrayList<String> cadenasYaHechas = new ArrayList<String>();
	public static int id = 1;
	public static int ultimoId = 0;
	public static ArrayList<pancake> pancakesHechos = new ArrayList<pancake>();
	
	public static void busqueda(String acomodado, String primerCaso, ArrayList<pancake>pancakes) {
		if(!primerCaso.equals(acomodado)) { //Revisa si el texto está acomodado o no
			ArrayList<pancake> nuevosPancakes = new ArrayList<pancake>();
			for(int i = 0; i < pancakes.size(); i ++) {
				String cadena = pancakes.get(i).getTexto();
				for(int e = 2; e <= acomodado.length(); e ++) {
					if(!ordenado) {
						String movida = mover(cadena, e);
						if(!cadenasYaHechas.contains(movida) && e != pancakes.get(i).getDesplazados()) {
							id++;
							pancake auxiliar = new pancake(movida, id, pancakes.get(i).getId(), e);
							cadenasYaHechas.add(auxiliar.getTexto());
							pancakesHechos.add(auxiliar);
							nuevosPancakes.add(auxiliar);
						}
						if(movida.equals(acomodado)) {
							e = acomodado.length()+1;
							i = pancakes.size()+1;
							ordenado = true;
						}
					}
				}
			}
			if(!ordenado) {
				busqueda(acomodado, primerCaso, nuevosPancakes);
			}
		}
		ultimoId = cadenasYaHechas.indexOf(acomodado);
	}
	
	public static String revolver(String cadena) {
		//Convierte la cadena de texto a un array de caracteres
		char cadenaRevuelta[] = cadena.toCharArray();
		for(int i = 0; i < cadena.length(); i ++) {
			//Genera un numero al azar para determinar la posicion desde que se revuelve
			int random = (int)(Math.random() * cadena.length());
			//Intercambia un caracter aleatorio con el primero de la cadena, luego con el segundo y así sucesivamente
			char temp = cadenaRevuelta[i];
			cadenaRevuelta[i] = cadenaRevuelta[random];
			cadenaRevuelta[random] = temp;
		}
		//Regresa un String con la cadena revuelta
		return String.valueOf(cadenaRevuelta);
	}
	
	public static String mover(String cadena, int pos) {
		char cadenaMovida[] = new char [pos+1];
		char cadenaOrig[] = cadena.toCharArray();
		int e = 0;
		//En un segundo array le paso los caracteres a mover en orden invertido, si debo mover "dcb" le paso "bcd"
		for(int i = pos-1; i >= 0; i --) {
			cadenaMovida[e] = cadenaOrig[i];
			e++;
		}
		//Cambio los caracteres originales de la cadena por los que ya estan invertidos
		for(int i = 0; i < pos; i ++) {
			cadenaOrig[i] = cadenaMovida[i];
		}
		//Regreso la cadena con los caracteres ya movidos
		return String.valueOf(cadenaOrig);
	}
	
}
