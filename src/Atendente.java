import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

public class Atendente implements Runnable {
	private String nome;
	private int nivelAtendente;
	private FilaChamados chamadosPendentes; // origem chamados
	private FilaChamados chamadosAtendidos; // chamados solucionados
	private FilaChamados chamadosTransferidos; // chamados n�o solucionados

	public Atendente(String nome, int nivelAtendente,
			FilaChamados chamadosPendentes, FilaChamados chamadosAtendidos,
			FilaChamados chamadosTransferidos) {
		this.nome = nome;
		this.nivelAtendente = nivelAtendente;
		this.chamadosPendentes = chamadosPendentes;
		this.chamadosAtendidos = chamadosAtendidos;
		this.chamadosTransferidos = chamadosTransferidos;
	}

	public void run() {
		int ciclosEspera = 0;

		// espera pela abastecimento de chamados
		while (ciclosEspera < 5) {

			if (!chamadosPendentes.isEmpty()) {
				this.realizaAtendimento();
			} else {
				ciclosEspera++;
				geradorEspera(1000, 500);
			}
		}
		this.exibeEncerramentoDemandas();
	}

	public String getNome() {
		return nome;
	}

	// retira o chamado da fila de pendentes e atende
	public synchronized void realizaAtendimento() {
		try {
			// remove chamado da fila de pendentes e armazena temporariamente
			Chamado chamadoEmProcesso = this.chamadosPendentes.removeChamado();

			// avalia a possibilidade de solucionar o chamado
			if (this.nivelAtendente == chamadoEmProcesso
					.getDificuldadeChamado()) {

				// muda o estado do chamado para true(resolvido)
				chamadoEmProcesso.setChamadoResolvido(true);

				// move para a fila de chamados atendidos
				this.transfereChamado(this.chamadosAtendidos, chamadoEmProcesso);

				// exibe info do chamado fechado
				this.exibeFechamentoChamado(chamadoEmProcesso);

				// realiza espera para o termino do chamado
				this.geradorEspera(350, 1000);

			} else {
				this.transfereChamado(this.chamadosTransferidos,
						chamadoEmProcesso);

				// realiza espera para atender proximo chamado
				this.geradorEspera(150, 300);
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	// realiza a tranferencia de chamados entre filas
	private void transfereChamado(FilaChamados filaDestino, Chamado chamadoAtual) {
		filaDestino.getFilaDeChamados().add(chamadoAtual);
	}

	// realiza um sleep na thread atual
	private void geradorEspera(int esperaMinima, int offsetEspera) {
		try {
			Thread.sleep(esperaMinima + ((long) (Math.random() * offsetEspera)));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// exibe info de um chamado fechado
	private void exibeFechamentoChamado(Chamado chamadoEmProcesso) {
		// exibe o fechamento do chamado
		System.out.println("[" + chamadoEmProcesso.getHoraAbertura() + "] "
				+ this.getNome() + " fechou chamado "
				+ chamadoEmProcesso.getOrigemChamado()
				+ chamadoEmProcesso.getNumero());
	}

	private void exibeEncerramentoDemandas() {

		// exibe o encerramento das demandas do usuario
		System.out.println("["
				+ new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] "
				+ this.nome + " finalizou todas demandas.");

	}
	
}
