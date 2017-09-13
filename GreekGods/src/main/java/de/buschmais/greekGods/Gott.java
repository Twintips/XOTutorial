package de.buschmais.greekGods;

import com.buschmais.xo.neo4j.api.annotation.Indexed;
import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("Gott")
public interface Gott {
	@Indexed
	String getName();

	void setName(String name);

	String getPersonifiziert();

	void setPersonifiziert(String personifiziert);

}
