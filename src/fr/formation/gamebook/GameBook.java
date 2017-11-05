package fr.formation.gamebook;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import fr.formation.gamebook.loader.DataLoader;
import fr.formation.gamebook.loader.XmlDataLoader;
import fr.formation.gamebook.model.Choice;
import fr.formation.gamebook.model.GameData;
import fr.formation.gamebook.model.Paragraph;

public class GameBook implements Runnable {
	
	public static void main(String[] args) {
		File f = new File(args[1]);
		if (args.length < 2) {
			System.err.println("Usage : GameBook <username>, <xmlfilepath>");
		} else if (!f.exists() ){
			System.err.println("Le fichier n'existe pas.");
		} else if (!args[1].substring(args[1].lastIndexOf(".")).equals(".xml")) {
			System.err.println("Le fichier n'est pas au format XML!");
		} else {
			new GameBook(args[0], args[1]).run();
		}
	}

	private String username;
	private DataLoader dataLoader;
	private Scanner scanner;

	private GameBook(String username, String datapath) {
		this.username = username;
		this.dataLoader = new XmlDataLoader(datapath);
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void run() {
		System.out.println("Lancement du jeu GameBook pour l'utilisateur : "
				+ this.username);
		final GameData data = this.dataLoader.load();
		System.out.println("Nom du livre : " + data.getId());
		System.out.println("Nombre de paragraphes : " 
				+ data.getParagraphs().size());
		System.out.println("-----------------------------------------------");
		Paragraph current = data.getParagraph(0);
		while (current.getChoices().size() > 0) {
			System.out.println(current.getContent());
			System.out.println(current.getQuestion());
			for (Choice c : current.getChoices()) {
				System.out.println("\t" + c.getId() + " - " + c.getContent());
			}
			final Choice choice = this.readChoice(current.getChoices());
			current = data.getParagraph(choice.getParagraphId());
		}
		System.out.println(current.getContent());
		System.out.println("Le jeu est termin�, fermeture du programme.");
		this.scanner.close();
	}

	private Choice readChoice(List<Choice> choices) {
		Choice result = null;
		while (result == null) {
			System.out.println("Votre choix : ");
			final int choiceId = this.scanner.nextInt();
			final Optional<Choice> choice = choices.stream()
					.filter((Choice searchChoice) -> searchChoice.getId() == choiceId)
					.findFirst();
			if (choice.isPresent()) {
				result = choice.get();
			} else {
				System.err.println("Votre num�ro de votre choix n'est pas valide.");
			}
		}
		return result;
	}
}
