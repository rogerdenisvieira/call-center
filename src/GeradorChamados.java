import java.text.SimpleDateFormat;
import java.util.*;

public class GeradorChamados implements Runnable {
	private int numeroSerie;
	private FilaChamados filaDestinoChamados;
	private String origemChamados;
	private int quantidadeChamados;

	public GeradorChamados(FilaChamados filaOrigemChamados,
			String origemChamado, int quantidadeChamados) {
		this.numeroSerie = 0;
		this.filaDestinoChamados = filaOrigemChamados;
		this.origemChamados = origemChamado;
		this.quantidadeChamados = quantidadeChamados;
	}

	public void run() {

		for (int indice = 0; indice < this.quantidadeChamados; indice++) {

			// captura a hora do sistema
			String horaAbertura = new SimpleDateFormat("HH:mm:ss")
					.format(new Date());

			// insere um novo chamado na fila
			this.filaDestinoChamados.insereChamado(new Chamado(
					this.numeroSerie, horaAbertura, this.origemChamados));

			System.out.println("[" + horaAbertura + "] " + "aberto chamado "
					+ this.origemChamados + this.numeroSerie);

			this.numeroSerie++;

			// executa uma espera para a próxima iteração
			this.geradorEspera(100, 200);
		}

	}

	public void geradorEspera(int esperaMinima, int offsetEspera) {
		try {
			Thread.sleep(esperaMinima + ((long) (Math.random() * offsetEspera)));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
