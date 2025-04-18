package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {

		ContaController contaController = new ContaController();

		Scanner leia = new Scanner(System.in);

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		while (true) {

			System.out.println(Cores.TEXT_BLUE + "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.print("Entre com a opção desejada: ");

			try {
				opcao = leia.nextInt();

			} catch (InputMismatchException e) {
				System.err.println("\nDigite valores inteiros!\n");
				opcao = 0;

			}

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (opcao) {

			case 1:

				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");

				System.out.print("Digite o Número da Agência: ");
				agencia = leia.nextInt();

				System.out.print("Digite o Nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();

				do {
					System.out.print("Digite o Tipo da Conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();

				} while (tipo < 1 && tipo > 2);

				System.out.print("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();

				switch (tipo) {

					case 1 -> {
						System.out.print("Digite o Limite da Crédito (R$): ");
						limite = leia.nextFloat();
						contaController.cadastrar(
								new ContaCorrente(contaController.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
	
					case 2 -> {
						System.out.print("Digite o dia do Aniversario da Conta: ");
						aniversario = leia.nextInt();
						contaController.cadastrar(new ContaPoupanca(contaController.gerarNumero(), agencia, tipo, titular,
								saldo, aniversario));
					}
				}

				keyPress();
				break;

			case 2:

				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
				contaController.listarTodas();
				keyPress();
				break;

			case 3:

				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");

				System.out.print("Digite o número da Conta: ");
				numero = leia.nextInt();

				contaController.procurarPorNumero(numero);

				keyPress();
				break;

			case 4:

				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");

				System.out.print("Digite o número da conta: ");

				numero = leia.nextInt();

				Conta buscaConta = contaController.buscarNaCollection(numero);

				if (buscaConta != null) {

					tipo = buscaConta.getTipo();

					System.out.print("Digite o Numero da Agência: ");
					agencia = leia.nextInt();

					System.out.print("Digite o Nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();

					System.out.print("Digite o Saldo da Conta (R$): ");
					saldo = leia.nextFloat();

					switch (tipo) {

						case 1 -> {
	
							System.out.print("Digite o Limite de Credito (R$): ");
							limite = leia.nextFloat();
	
							contaController.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
						}
	
						case 2 -> {
	
							System.out.print("Digite o dia do Aniversario da Conta: ");
							aniversario = leia.nextInt();
	
							contaController
									.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
						}
	
						default -> {
	
							System.out.println("Tipo de conta invalido!");
	
						}
					}

				} else {

					System.out.println("A Conta nao foi encontrada!");

				}

				keyPress();
				break;

			case 5:

				System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");

				System.out.print("Digite o número da conta: ");
				numero = leia.nextInt();

				contaController.deletar(numero);

				keyPress();
				break;

			case 6:

				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");

				System.out.print("Digite o Numero da conta: ");
				numero = leia.nextInt();

				do {

					System.out.print("Digite o Valor do Saque (R$): ");
					valor = leia.nextFloat();

				} while (valor <= 0);

				contaController.sacar(numero, valor);

				keyPress();
				break;

			case 7:

				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");

				System.out.print("Digite o Numero da conta: ");
				numero = leia.nextInt();

				do {

					System.out.print("Digite o Valor do Deposito (R$): ");
					valor = leia.nextFloat();

				} while (valor <= 0);

				contaController.depositar(numero, valor);

				keyPress();
				break;

			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");

				System.out.print("Digite o Numero da Conta de Origem: ");
				numero = leia.nextInt();

				System.out.print("Digite o Numero da Conta de Destino: ");
				numeroDestino = leia.nextInt();

				do {

					System.out.print("Digite o Valor da Transferencia (R$): ");
					valor = leia.nextFloat();

				} while (valor <= 0);

				contaController.transferir(numero, numeroDestino, valor);

				keyPress();
				break;

			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				keyPress();
				break;
			}
		}
	}

	public static void keyPress() {

		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar.....");
			System.in.read();

		} catch (IOException e) {
			System.err.println("Você digitou uma tecla diferente de Enter!");

		}
	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Vinícius Cristhian e Generation Brasil");
		System.out.println("Vinícius Cristhian - viniciuscristhian33@gmail.com");
		System.out.println("github.com/ViniCristhian");
		System.out.println("*********************************************************");

	}

}
