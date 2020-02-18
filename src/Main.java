import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		// classe Random usada para gerar numero aleat�rio.
		Random rand = new Random();

		// variaveis de jogo
		String[] inimigos = { "Esqueleto", "Goblin", "Troll", "Orc", "Arakno", "Espectro Vingativo",
				"Morcego Vamp�rico", "Saqueador de T�mulos", "Rei dos Ratos" };
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

		System.out.println("Bem vindo as Masmorras da Escurid�o!");

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
				System.out.println("\n\tQual a sua decis�o?");
				System.out.println("\t1. Atacar!");
				System.out.println("\t2. Beber Po��o de Vida.");
				System.out.println("\t3. Fugir!");

				String input = in.nextLine();

				if (input.equals("1")) {

					int danoCausado = rand.nextInt(danoAtaque);
					int danoRecebido = rand.nextInt(danoAtaqueInimigo);

					vidaInimigo -= danoCausado;
					vida -= danoRecebido;

					System.out.println("\t> Voc� causou " + danoCausado + " pontos de dano ao " + inimigo + "!");
					System.out.println("\t> Em retalia��o, voc� recebeu " + danoRecebido + " pontos de dano!");

					if (vida < 1) {
						System.out.println("-----------------------------------------------------");
						System.out.println(
								"\t Voc� recebeu muito dano. Sangrando intensamente, voc� come�a a ouvir, chamando o, uma doce voz atrav�s de uma luz p�lida...");
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
						System.out.println("Voc� bebeu uma Po��o de Vida, curando " + pocaoVidaCura + " PV!"
								+ "\n\t> Voc� agora tem " + vida + " PV." + "\n\t> Voc� agora tem " + numPocoesVida
								+ " Po��es de Vida restantes.");

					} else if (numPocoesVida > 0 && vida >= 100) {
						System.out.println("Seu hp j� est� cheio.");
					}

					else
						System.out.println(
								"Voc� n�o tem Po��es de Vida! Derrote um inimigo para ter uma chance de ganhar uma Po��o de Vida. ");

				}

				else if (input.equals("3")) {
					System.out.println("Vergonhosamente, voc� fugiu do " + inimigo + "!");
					continue JOGO; // quebra o loop e retorna para a sess�o JOGO, finalizando a encounter e gerando
									// novo inimigo.

				}

				else {
					System.out.println("\tComando inv�lido!");

				}
			}
			if (vida < 1) {
				System.out.println("\t V O C �  M O R R E U !");
				break;

			}

			System.out.println("-----------------------------------------------------");
			System.out.println("# " + inimigo + " foi derrotado! " + "#");
			System.out.println("# Voc� tem " + vida + " PV restantes. #");

			pontuacao++;

			// roll que determina se a pocao de vida dropa ou nao

			if (rand.nextInt(100) > pocaoVidaDropChance) {
				numPocoesVida++;
				System.out.println("# " + inimigo + " dropou uma Po��o de Vida. #");
				System.out.println("# Voc� agora tem " + numPocoesVida + " Po��es de Vida restantes. #");

			}

			System.out.println("-----------------------------------------------------");
			System.out.println("Agora qual � sua a decis�o?");
			System.out.println("1. Continuar explorando a masmorra.");
			System.out.println("2. Sair da masmorra.");

			String input = in.nextLine();

			while (!input.equals("1") && !input.equals("2")) {
				System.out.println("Comando inv�lido!");
				input = in.nextLine();
			}

			if (input.equals("1")) {
				System.out.println("Voc� continua na sua aventura.");
			}
			if (input.equals("2")) {
				System.out.println("Voc� abandonou a sua jornada.");
				break;
			}
		}
		System.out.println("\t PONTUA��O: " + pontuacao + " inimigos derrotados.");

		System.out.println("\t O B R I G A D O  P O R  J O G A R ! :)");

		in.close();
	}

}
