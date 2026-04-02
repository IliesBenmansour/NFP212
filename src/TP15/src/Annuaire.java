package TP15.src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Annuaire {

	private Map<String, String> annuaire;

	public Annuaire() {
		this.annuaire = new HashMap();
	}

	public void enregistrerArrivee(String name, String bureau) throws DejaPresentException {
		if (name == null) {
			throw new NullPointerException("name est null");
		}
		if (bureau == null) {
			throw new NullPointerException("bureau est null");
		}
		if (this.annuaire.containsKey(name)) {
			throw new DejaPresentException(name + " est deja present dans l'annuaire");
		}
		this.annuaire.put(name, bureau);
	}

	public String bureau(String name) throws PersonnelInconnuException {
		if (name == null) {
			throw new NullPointerException("name est null");
		}
		if (!(this.annuaire.containsKey(name))) {
			throw new PersonnelInconnuException(name + " n'est pas present dans l'annuaire");
		}
		return this.annuaire.get(name);
	}

	public void modifierBureau(String name, String bureau) throws PersonnelInconnuException {
		if (name == null) {
			throw new NullPointerException("name est null");
		}
		if (bureau == null) {
			throw new NullPointerException("bureau est null");
		}
		if (!(this.annuaire.containsKey(name))) {
			throw new PersonnelInconnuException(name + " n'est pas present dans l'annuaire");
		}
		this.annuaire.replace(name, bureau);
	}

	public List<String> personnels() {
		return new ArrayList<>(this.annuaire.keySet());
	}

	public Collection<String> personnels(String bureau) {
		if (bureau == null) {
			throw new NullPointerException("bureau est null");
		}
		List<String> result = new ArrayList<>();
		for (Map.Entry<String, String> entry : this.annuaire.entrySet()) {
			if (entry.getValue().equals(bureau)) {
				result.add(entry.getKey());
			}
		}
		return result;
	}

	public List<String> bureaux() {
		Set<String> bureauxUniques = new HashSet<>(this.annuaire.values());
		return new ArrayList<>(bureauxUniques);
	}

	public void enregistrerDepart(String name) throws PersonnelInconnuException {
		if (name == null) {
			throw new NullPointerException("name est null");
		}
		if (!(this.annuaire.containsKey(name))) {
			throw new PersonnelInconnuException(name + " n'est pas present dans l'annuaire");
		}
		this.annuaire.remove(name);
	}

	public Map<String, List<String>> occupationBureaux() {
		Map<String, List<String>> result = new HashMap<>();

		for (Map.Entry<String, String> entry : this.annuaire.entrySet()) {
			String nom = entry.getKey();
			String bureau = entry.getValue();

			// Si le bureau n'est pas encore dans la map, on l'ajoute
			result.putIfAbsent(bureau, new ArrayList<>());

			// On ajoute la personne au bureau
			result.get(bureau).add(nom);
		}

		return result;
	}

}
