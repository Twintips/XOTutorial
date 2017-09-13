package de.buschmais.greekGods;

import com.buschmais.xo.neo4j.api.annotation.Label;
import com.buschmais.xo.neo4j.api.annotation.Relation;
import com.buschmais.xo.neo4j.api.annotation.Relation.Incoming;

@Label(value = "Kind", usingIndexedPropertyOf = Gott.class)
public interface Kind extends Gott {
	@Relation("VATER_VON")
	@Incoming
	Gott getVater();

	void setVater(Gott vater);

	@Relation("MUTTER_VON")
	@Incoming
	Gott getMutter();

	void setMutter(Gott mutter);
}
