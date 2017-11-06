package fr.formation.gamebook.loader;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import fr.formation.gamebook.model.GameData;

public class XmlDataLoader implements DataLoader {

	private final String dataPath;

	public XmlDataLoader(final String dataPath) {
		this.dataPath = dataPath;
	}

	@Override
	public GameData load() {
		GameData data = null;
		try {
			JAXBContext context = JAXBContext.newInstance(GameData.class);
			Unmarshaller transform = context.createUnmarshaller();
			data = (GameData) transform.unmarshal(new File(this.dataPath));
		} catch (final JAXBException e) {
			System.err.println("Le fichier XML de donn?es comporte des erreurs :");
			e.printStackTrace();
			// Valeur : 0 si arr?t normal, > ? 0 si erreur g?r?e, < ? 0 si
			// erreur technique non pr?vue.
			System.exit(1);
		}
		return data;
	}

}
