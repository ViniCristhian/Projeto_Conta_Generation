package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {

	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {

		Conta conta = buscarNaCollection(numero);

		if (conta != null) {
			conta.visualizar();

		} else {
			System.out.println("A Conta número: " + numero + " não foi encontrada!");

		}
	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("A conta número: " + conta.getNumero() + " foi cadastrada com sucesso!");

	}

	@Override
	public void atualizar(Conta conta) {

		Conta buscaConta = buscarNaCollection(conta.getNumero());

		if (buscaConta != null) {

			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("Conta número: " + conta.getNumero() + " atualizado com sucesso!");

		} else {

			System.out.println("Conta número: " + conta.getNumero() + " não foi encontrada!");
		}

	}

	@Override
	public void deletar(int numero) {
		Conta conta = buscarNaCollection(numero);

		if (conta != null) {
			if (listaContas.remove(conta) == true)
				System.out.println("Conta número: " + numero + " deletada com sucesso!");

		} else {
			System.out.println("Conta número: " + numero + " não foi encontrada!");
		}

	}

	@Override
	public void sacar(int numero, float valor) {

		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (conta.sacar(valor) == true)
				System.out.println("\nO Saque na Conta numero: " + numero + " foi efetuado com sucesso!");

		} else {
			System.out.println("\nA Conta numero: " + numero + " nao foi encontrada!");
		}
	}

	@Override
	public void depositar(int numero, float valor) {

		var conta = buscarNaCollection(numero);

		if (conta != null) {
			conta.depositar(valor);
			System.out.println("\nO Deposito na Conta numero: " + numero + " foi efetuado com sucesso!");

		} else {
			System.out.println(
					"\nA Conta numero: " + numero + " nao foi encontrada ou a Conta destino nao e uma Conta Corrente!");
		}

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {

		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);

		if (contaOrigem != null && contaDestino != null) {
			if (contaOrigem.sacar(valor) == true)
				contaDestino.depositar(valor);
				System.out.println("\nA Transferencia foi efetuada com sucesso!");

		} else {
			System.out.println("\nA Conta de Origem e/ou Destino nao foram encontradas!");
		}
	}

	public int gerarNumero() {
		return ++numero;
	}

	public Conta buscarNaCollection(int numero) {

		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}

		return null;
	}

}
