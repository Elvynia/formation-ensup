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

	// private static final String DATA_PATH =
	// "C://code//formation-ensup/superhistoire.xml";

	public static void main(String[] args) {
		if (args.length >= 2) {
			File f = new File(args[1]);
			//si le fichier existe bien
			if (f.exists() && !f.isDirectory()) {
				String ext = f.getName().substring(f.getName().lastIndexOf(".") + 1);
				String xml = "xml";
				// si le fichier est bien un xml
				if (ext.equals(xml)) {
					//si le fichier est lisible
					if (f.canRead()) {
						new GameBook(args[0], args[1]).run();
					} else {
						System.err.println("Ce fichier " + args[1] + " n'est pas lisible");
					}
				} else {
					System.err.println("Ce fichier " + args[1] + " n'est pas de type XML");
				}

			} else {
				System.err.println("Le fichier " + args[1] + " n'existe pas");
			}

		} else {
			System.err.println("Usage : GameBook <username>, Fchier : <xmlfilepath>");
		}
	}

	private String username;
	private DataLoader dataLoader;
	private Scanner scanner;

	private GameBook(String username, String DATA_PATH) {
		this.username = username;
		this.dataLoader = new XmlDataLoader(DATA_PATH);
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void run() {
		System.out.println("Lancement du jeu GameBook pour l'utilisateur : " + this.username);
		final GameData data = this.dataLoader.load();
		System.out.println("Nom du livre : " + data.getId());
		System.out.println("Nombre de paragraphes : " + data.getParagraphs().size());
		System.out.println("-----------------------------------------------");
		Paragraph current = data.getParagraph(0);
		while (current.getChoices().size() > 0) {
			System.out.println(current.getContent());
			//afiche la question du fichier xml
			System.out.println(current.getQuestion());
			// System.out.println("Veuillez faire un choix :");
			for (Choice c : current.getChoices()) {
				System.out.println("\t" + c.getId() + " - " + c.getContent());
			}
			final Choice choice = this.readChoice(current.getChoices());
			current = data.getParagraph(choice.getParagraphId());
		}
		System.out.println(current.getContent());
		System.out.println("Le jeu est terminé, fermeture du programme.");
		this.scanner.close();
	}

	private Choice readChoice(List<Choice> choices) {
		Choice result = null;
		while (result == null) {
			System.out.println("Votre choix : ");
			final int choiceId = this.scanner.nextInt();
			final Optional<Choice> choice = choices.stream()
					.filter((Choice searchChoice) -> searchChoice.getId() == choiceId).findFirst();
			if (choice.isPresent()) {
				result = choice.get();
			} else {
				System.err.println("Votre numéro de votre choix n'est pas valide.");
			}
		}
		return result;
	}
}
