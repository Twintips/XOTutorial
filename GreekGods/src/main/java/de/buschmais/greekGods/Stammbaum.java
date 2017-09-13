package de.buschmais.greekGods;

import com.buschmais.xo.api.XOManager;
import com.buschmais.xo.api.XOManagerFactory;
import com.buschmais.xo.api.bootstrap.XO;

public class Stammbaum {

	public static void main(String[] args) {
		XOManagerFactory xmf = XO.createXOManagerFactory("Stammbaum");

		XOManager xm = xmf.createXOManager();

		xm.currentTransaction().begin();

		// db löschen
		xm.createQuery("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r").execute();
		System.out.println("Datenbank geleert");

		Gott zeus = xm.create(Gott.class);
		zeus.setName("Zeus");
		zeus.setPersonifiziert("Anführer");
		Gott hera = xm.create(Gott.class);
		hera.setName("Hera");
		hera.setPersonifiziert("Ehe");
		Gott demeter = xm.create(Gott.class);
		demeter.setName("Demeter");
		demeter.setPersonifiziert("Mutter");
		Gott leto = xm.create(Gott.class);
		leto.setName("Leto");
		Kind dinysos = xm.create(Kind.class);
		dinysos.setName("Dinysos");
		dinysos.setPersonifiziert("Ekstase");
		Kind hephaistos = xm.create(Kind.class);
		hephaistos.setName("Hephaistos");
		hephaistos.setPersonifiziert("Schmiedekunst");
		Kind artemis = xm.create(Kind.class);
		artemis.setName("Artemis");
		artemis.setPersonifiziert("Jungfraeulichkeit");
		Kind apollo = xm.create(Kind.class);
		apollo.setName("Apollo");
		apollo.setPersonifiziert("Licht");
		// restliche Kinder anlegen (s. Abbildung1)

		xm.currentTransaction().commit();

		xm.currentTransaction().begin();

		// updates

		hephaistos.setVater(zeus);
		hephaistos.setMutter(hera);
		apollo.setVater(zeus);
		apollo.setMutter(leto);
		artemis.setVater(zeus);
		artemis.setMutter(leto);

		Kind updateDinysos = xm.find(Kind.class, "Dinysos").getSingleResult();
		updateDinysos.setVater(zeus);
		updateDinysos.setMutter(demeter);
		// alle weiteren Beziehungen sind zu ergänzen

		System.out.println(xm.createQuery("match (gott:Gott) where gott.name={name} return gott")
				.withParameter("name", "Artemis").execute().getSingleResult());

		xm.currentTransaction().commit();

		xm.close();
		xmf.close();
		System.out.println("fertig");

	}

}
