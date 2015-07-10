public class Chamado {
	private int numero;
	private boolean chamadoResolvido; // true = atendido / false = pendente
	private String horaAbertura;
	private String origemChamado;
	private int dificuldadeChamado;

	public Chamado(int numero, String horaAbertura, String origemChamado) {
		this.numero = numero;
		this.horaAbertura = horaAbertura;
		this.origemChamado = origemChamado;
		this.chamadoResolvido = false;
		this.dificuldadeChamado = this.geradorDificuldade();
	}

	public int getNumero() {
		return numero;
	}

	public boolean isStatus() {
		return chamadoResolvido;
	}

	public void setChamadoResolvido(boolean status) {
		this.chamadoResolvido = status;
	}

	public int getDificuldadeChamado() {
		return dificuldadeChamado;
	}

	public String getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(String horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public String getOrigemChamado() {
		return origemChamado;
	}

	public void setOrigemChamado(String origemChamado) {
		this.origemChamado = origemChamado;
	}

	private int geradorDificuldade() {
		return 1 + ((int) (Math.random() * 3));
	}

}
