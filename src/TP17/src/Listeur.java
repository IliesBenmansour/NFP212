package TP17.src;

import java.lang.reflect.Method;

interface Listeur {
	java.util.List<Method> getMethodes(String nomClasse) throws Exception;
}
