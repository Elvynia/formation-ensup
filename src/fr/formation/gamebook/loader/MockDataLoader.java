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
		p.setContent("Ce chemin m?ne au tr?sor, bravo vous avez gagn? !");
		return p;
	}

	private Paragraph buildParagraph2() {
		final Paragraph p = new Paragraph(2);
		p.setContent("Ce chemin vous ram?ne ? la lisi?re de la for?t");
		p.getChoices().add(new Choice(0, "Revenir au d?but", 0));
		return p;
	}

	private Paragraph buildParagraph1() {
		final Paragraph p = new Paragraph(0);
		p.setContent(
				"Il ?tait une fois un aventurier du dimanche qui voulait explorer "
				+ "les bois perdus. Arriv? ? l'entr?e de la for?t, deux chemins "
				+ "s'offrent ? lui.");
		p.getChoices().add(new Choice(0, "Chemin de gauche", 1));
		p.getChoices().add(new Choice(1, "Chemin de droite", 2));
		return p;
	}

}
