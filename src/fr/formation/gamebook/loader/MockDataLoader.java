package fr.formation.gamebook.loader;

import fr.formation.gamebook.model.Choice;
import fr.formation.gamebook.model.GameData;
import fr.formation.gamebook.model.Paragraph;

public class MockDataLoader implements DataLoader {

	@Override
	public GameData load() {
		final GameData gameData = new GameData("Mon premier mock");
		gameData.getParagraphs().add(this.buildParagraph1());
		gameData.getParagraphs().add(this.buildParagraph2());
		gameData.getParagraphs().add(this.buildParagraph3());
		return gameData;
	}

	private Paragraph buildParagraph3() {
		final Paragraph p = new Paragraph(1);
		p.setContent("Ce chemin mène au trésor, bravo vous avez gagné !");
		return p;
	}

	private Paragraph buildParagraph2() {
		final Paragraph p = new Paragraph(2);
		p.setContent("Ce chemin vous ramène à la lisière de la forêt");
		p.getChoices().add(new Choice(0, "Revenir au début", 0));
		return p;
	}

	private Paragraph buildParagraph1() {
		final Paragraph p = new Paragraph(0);
		p.setContent(
				"Il était une fois un aventurier du dimanche qui voulait explorer "
				+ "les bois perdus. Arrivé à l'entrée de la forêt, deux chemins "
				+ "s'offrent à lui.");
		p.getChoices().add(new Choice(0, "Chemin de gauche", 1));
		p.getChoices().add(new Choice(1, "Chemin de droite", 2));
		return p;
	}

}
