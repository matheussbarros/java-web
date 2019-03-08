package br.com.fiap;

import java.rmi.RemoteException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.fiap.loja.bo.EstoqueBOStub;
import br.com.fiap.loja.bo.EstoqueBOStub.ConsultarProduto;
import br.com.fiap.loja.bo.EstoqueBOStub.ConsultarProdutoResponse;
import br.com.fiap.loja.bo.EstoqueBOStub.ProdutoTO;

public class TelaSWT {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaSWT window = new TelaSWT();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(10, 26, 55, 15);
		label.setText("Codigo");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(71, 23, 169, 25);
		
		Label label2 = new Label(shell, SWT.NONE);
		label2.setBounds(10, 82, 268, 15);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EstoqueBOStub stub = null;
				try {
					stub = new EstoqueBOStub();
				} catch (RemoteException e1) {
					label2.setText("Produto não encontrado");
				}
				
				
				ConsultarProduto consulta = new ConsultarProduto();
				int codigo = Integer.parseInt(text.getText());
				consulta.setCodigo(codigo);
				ConsultarProdutoResponse response = null;
				
				
				try {
					response = stub.consultarProduto(consulta);
					ProdutoTO produto = response.get_return();
					label2.setText(produto.getDescricao());
				} catch (Exception e2) {
					label2.setText("Sistema fora do ar");
				}
				
				
				
			}
		});
		btnNewButton.setBounds(109, 138, 75, 25);
		btnNewButton.setText("Pesquisar");
		
		

	}
}
