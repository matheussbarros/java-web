package br.com.fiap.loja.teste;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.fiap.loja.bo.EstoqueBOStub;
import br.com.fiap.loja.bo.EstoqueBOStub.ConsultarProduto;
import br.com.fiap.loja.bo.EstoqueBOStub.ConsultarProdutoResponse;
import br.com.fiap.loja.bo.EstoqueBOStub.ProdutoTO;

public class TerminalConsulta {

	public static void main(String[] args) throws RemoteException {
		String nome = "Loja FIAP Run";
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatador = 	DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println(nome + " " + hoje.format(formatador));
		System.out.println("Digite o código do produto desejado: ");
		Scanner scanner = new Scanner(System.in);
		int codigo = scanner.nextInt();
		scanner.close();
		EstoqueBOStub stub = new EstoqueBOStub();
		ConsultarProduto consulta = new ConsultarProduto();
		consulta.setCodigo(codigo);
		ConsultarProdutoResponse response = stub.consultarProduto(consulta);
		
		ProdutoTO produto = response.get_return();
		System.out.println(produto.getDescricao());
		
		
		
	}
}
