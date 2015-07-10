public class Teste {
	public static void main(String[] args) {

		// cria filas de chamados
		FilaChamados filaChamadosN1 = new FilaChamados("ChamadosN1");
		FilaChamados filaChamadosN2 = new FilaChamados("ChamadosN2");
		FilaChamados filaChamadosN3 = new FilaChamados("ChamadosN3");
		FilaChamados chamadosAtendidos = new FilaChamados("Atendidos");

		// cria array atendentes N1
		Atendente[] atendentesN1 = new Atendente[8];
		for (int i = 0; i < atendentesN1.length; i++)
			atendentesN1[i] = new Atendente("N1 - 0" + i, 1, filaChamadosN1,
					chamadosAtendidos, filaChamadosN2);

		// cria array atendentes N2
		Atendente[] atendentesN2 = new Atendente[6];
		for (int i = 0; i < atendentesN2.length; i++)
			atendentesN2[i] = new Atendente("N2 - 0" + i, 2, filaChamadosN2,
					chamadosAtendidos, filaChamadosN3);

		// cria array atendentes N3
		Atendente[] atendentesN3 = new Atendente[4];
		for (int i = 0; i < atendentesN3.length; i++)
			atendentesN3[i] = new Atendente("N3 - 0" + i, 3, filaChamadosN3,
					chamadosAtendidos, null);

		// cria geradores de chamados
		GeradorChamados geradorWeb = new GeradorChamados(filaChamadosN1, "W",
				1000);
		GeradorChamados geradorFone = new GeradorChamados(filaChamadosN1, "F",
				1000);

		// cria e dispara as threads de cada gerador
		Thread threadGeradorWeb = new Thread(geradorWeb);
		threadGeradorWeb.start();

		Thread threadGeradorFone = new Thread(geradorFone);
		threadGeradorFone.start();

		// cria e dispara as threads dos atendentes N1
		Thread[] threadAtendentesN1 = new Thread[8];
		for (int i = 0; i < atendentesN1.length; i++) {
			threadAtendentesN1[i] = new Thread(atendentesN1[i]);
			threadAtendentesN1[i].start();
		}

		// cria e dispara as threads dos atendentes N2
		Thread[] threadAtendentesN2 = new Thread[6];
		for (int i = 0; i < atendentesN2.length; i++) {
			threadAtendentesN2[i] = new Thread(atendentesN2[i]);
			threadAtendentesN2[i].start();
			;
		}

		// cria e dispara as threads dos atendentes N3
		Thread[] threadAtendentesN3 = new Thread[4];
		for (int i = 0; i < atendentesN3.length; i++) {
			threadAtendentesN3[i] = new Thread(atendentesN3[i]);
			threadAtendentesN3[i].start();
		}

		// tenta evocar o retorno da thread
		try {
			threadGeradorFone.join();
			threadGeradorWeb.join();
			for (int i = 0; i < atendentesN1.length; i++)
				threadAtendentesN1[i].join();
			for (int i = 0; i < atendentesN2.length; i++)
				threadAtendentesN2[i].join();
			for (int i = 0; i < atendentesN3.length; i++)
				threadAtendentesN3[i].join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("--------------FIM--------------");

	}
}
