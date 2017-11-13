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
	
	//private static final String DATA_PATH = "/Users/jose/formation-ensup/superhistoire.xml";

	public static void main(String[] args) {
		if (args.length >= 2) {
			File file = new File(args[1]);

			if (file.exists() && !file.isDirectory()) {
				String extension = "";
				int i = args[1].lastIndexOf('.');

				if (i > 0) {
					extension = args[1].substring(i+1);
				}

				if (extension.equals("xml")) {
					if (file.canRead()) {
						new GameBook(args[0], args[1]).run();
					} else {
						System.err.println("Le fichier " + args[1] + " ne peut pas être lu.");
					}
				} else {
					System.err.println("Le fichier doit être un xml.");
				}
			} else {
				System.err.println(args[1] + " n'est pas un fichier");
			}
		} else {
			System.err.println("Usage : GameBook <username> <xmlfile>");
		}
	}

	private String username;
	private DataLoader dataLoader;
	private Scanner scanner;

	private GameBook(String username, String data_path) {
		this.username = username;
		this.dataLoader = new XmlDataLoader(data_path);
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
		System.out.println("Le jeu est termin?, fermeture du programme.");
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
				System.err.println("Votre num?ro de votre choix n'est pas valide.");
			}
		}
		return result;
	}
}
