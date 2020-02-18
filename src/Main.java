import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		// classe Random usada para gerar numero aleatório.
		Random rand = new Random();

		// variaveis de jogo
		String[] inimigos = { "Esqueleto", "Goblin", "Troll", "Orc", "Arakno", "Espectro Vingativo",
				"Morcego Vampírico", "Saqueador de Túmulos", "Rei dos Ratos" };
		int vidaMaximaInimigo = 75;
		int danoAtaqueInimigo = 25;

		int pontuacao = 0;

		// variaveis do jogador
		int vida = 100;
		int danoAtaque = 50;
		int numPocoesVida = 3;
		int pocaoVidaCura = 30; // HP
		int pocaoVidaDropChance = 20; // percentual de chance de drop

		boolean rodando = true;

		System.out.println("Bem vindo as Masmorras da Escuridão!");

		System.out.println("intro TODO");

		JOGO: // main loop
		while (rodando) {
			System.out.println("-----------------------------------------------------");

			int vidaInimigo = rand.nextInt(vidaMaximaInimigo);
			String inimigo = inimigos[rand.nextInt(inimigos.length)];
			System.out.println("\t# Um " + inimigo + " apareceu! #\n");

			// encounter loop
			while (vidaInimigo > 0) {
				System.out.println("\tSeus PV: " + vida);
				System.out.println("\tPV do " + inimigo + ": " + vidaInimigo);
				System.out.println("\n\tQual a sua decisão?");
				System.out.println("\t1. Atacar!");
				System.out.println("\t2. Beber Poção de Vida.");
				System.out.println("\t3. Fugir!");

				String input = in.nextLine();

				if (input.equals("1")) {

					int danoCausado = rand.nextInt(danoAtaque);
					int danoRecebido = rand.nextInt(danoAtaqueInimigo);

					vidaInimigo -= danoCausado;
					vida -= danoRecebido;

					System.out.println("\t> Você causou " + danoCausado + " pontos de dano ao " + inimigo + "!");
					System.out.println("\t> Em retaliação, você recebeu " + danoRecebido + " pontos de dano!");

					if (vida < 1) {
						System.out.println("-----------------------------------------------------");
						System.out.println(
								"\t Você recebeu muito dano. Sangrando intensamente, você começa a ouvir, chamando o, uma doce voz através de uma luz pálida...");
						break; // o break leva novamente ao loop da encounter
					}
				}

				else if (input.equals("2")) {
					if (numPocoesVida > 0 && vida < 100) {

						if (vida + pocaoVidaCura > 100) {
							vida = 100;
						} else {
							vida += pocaoVidaCura;
						}
						numPocoesVida--;
						System.out.println("Você bebeu uma Poção de Vida, curando " + pocaoVidaCura + " PV!"
								+ "\n\t> Você agora tem " + vida + " PV." + "\n\t> Você agora tem " + numPocoesVida
								+ " Poções de Vida restantes.");

					} else if (numPocoesVida > 0 && vida >= 100) {
						System.out.println("Seu hp já está cheio.");
					}

					else
						System.out.println(
								"Você não tem Poções de Vida! Derrote um inimigo para ter uma chance de ganhar uma Poção de Vida. ");

				}

				else if (input.equals("3")) {
					System.out.println("Vergonhosamente, você fugiu do " + inimigo + "!");
					continue JOGO; // quebra o loop e retorna para a sessão JOGO, finalizando a encounter e gerando
									// novo inimigo.

				}

				else {
					System.out.println("\tComando inválido!");

				}
			}
			if (vida < 1) {
				System.out.println("\t V O C Ê  M O R R E U !");
				break;

			}

			System.out.println("-----------------------------------------------------");
			System.out.println("# " + inimigo + " foi derrotado! " + "#");
			System.out.println("# Você tem " + vida + " PV restantes. #");

			pontuacao++;

			// roll que determina se a pocao de vida dropa ou nao

			if (rand.nextInt(100) > pocaoVidaDropChance) {
				numPocoesVida++;
				System.out.println("# " + inimigo + " dropou uma Poção de Vida. #");
				System.out.println("# Você agora tem " + numPocoesVida + " Poções de Vida restantes. #");

			}

			System.out.println("-----------------------------------------------------");
			System.out.println("Agora qual é sua a decisão?");
			System.out.println("1. Continuar explorando a masmorra.");
			System.out.println("2. Sair da masmorra.");

			String input = in.nextLine();

			while (!input.equals("1") && !input.equals("2")) {
				System.out.println("Comando inválido!");
				input = in.nextLine();
			}

			if (input.equals("1")) {
				System.out.println("Você continua na sua aventura.");
			}
			if (input.equals("2")) {
				System.out.println("Você abandonou a sua jornada.");
				break;
			}
		}
		System.out.println("\t PONTUAÇÃO: " + pontuacao + " inimigos derrotados.");

		System.out.println("\t O B R I G A D O  P O R  J O G A R ! :)");

		in.close();
	}

}
