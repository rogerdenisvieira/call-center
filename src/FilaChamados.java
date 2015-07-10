import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class FilaChamados {
	public String nomeFila;
	private Queue<Chamado> filaDeChamados;

	public FilaChamados(String nomeFila) {
		this.nomeFila = nomeFila;
		this.filaDeChamados = new LinkedBlockingQueue<Chamado>();
	}

	public boolean insereChamado(Chamado chamado) {
		return this.filaDeChamados.add(chamado);
	}

	public Chamado removeChamado() {
		return this.filaDeChamados.remove();
	}

	public int tamanhoFila() {
		return this.filaDeChamados.size();
	}

	public void exibeFila() {
		for (Chamado chamado : this.filaDeChamados) {
			System.out.println("[" + chamado.getHoraAbertura() + "] "
					+ chamado.getOrigemChamado() + chamado.getNumero());
		}
	}

	public boolean isEmpty() {
		if (this.filaDeChamados.size() == 0)
			return true;
		return false;
	}

	public Queue<Chamado> getFilaDeChamados() {
		return filaDeChamados;
	}

}
