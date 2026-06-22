package TP17.src;

import java.lang.reflect.Method;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		ListeurFabriquesStatiques listeur = new ListeurFabriquesStatiques();

		// Nom complet avec le nouveau package
		List<Method> fabriques = listeur.getMethodes("TP17.src.C");

		System.out.println("Fabriques statiques de C :");
		for (Method m : fabriques) {
			System.out.println("- " + m);
		}
	}
}