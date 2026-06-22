package TP17.src;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ListeurFabriquesStatiques implements Listeur {

	@Override
	public List<Method> getMethodes(String nomClasse) throws Exception {
		List<Method> liste = new ArrayList<>();
		Class<?> c = Class.forName(nomClasse);
		Method[] methodes = c.getDeclaredMethods();
		for (Method m : methodes) {
			if (Modifier.isStatic(m.getModifiers())) {
				if (m.getReturnType() == c) {
					Class<?>[] params = m.getParameterTypes();
					boolean ok = true;
					for (Class<?> p : params) {
						if (p == c) {
							ok = false;
						}
					}
					if (ok) {
						liste.add(m);
					}
				}
			}
		}
		return liste;
	}

}
