package br.com.fiap.loja.bo;

import org.apache.axis2.AxisFault;

import br.com.fiap.loja.to.ProdutoTO;

public class EstoqueBO {
	public static ProdutoTO consultarProduto(int codigo) throws AxisFault {
		if(codigo == 401) {
			return new ProdutoTO(codigo, "Camiseta branca", 4, 2.99);
		} else if(codigo == 402) {
			return new ProdutoTO(codigo, "Camiseta azul", 4, 2.99);
		} else if(codigo == 403) {
			return new ProdutoTO(codigo, "Camiseta rosa", 4, 2.99);
		} else {
			throw new AxisFault("Produto não encontrado!");
		}
	}
}
