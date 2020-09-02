package Br.edu.opet.controller;
import java.util.ArrayList;
import Br.edu.opet.model.Pessoa;
import Br.edu.opet.viewer.PessoaTela;

public class PessoaController {

	private PessoaTela tp = new PessoaTela();
	private EspController ce = new EspController();
	private Pessoa p;

	public PessoaController(EspController ce){

		this.ce = ce;
		this.tp = new PessoaTela();
		this.p = new Pessoa();
	}

	public void  init() throws Exception {

		int op = tp.menu();

		while(op!=0){

			switch (op){
			case 1: // cadastro
				cadastrar();
				break;
			case 2: // consultar
				consultar();
				break;
			case 3: // alterar
				alterar();
				break;
			case 4: // excluir
				excluir();
				break;
			case 5: // listar
				listar();
				break;
			default:
				tp.popUpErro();
				break;
			}
			op = tp.menu();
		}
	}

	private void excluir() {
		
		Pessoa p = tp.exibirConsultar();
		p = p.consultar();
		if(p != null) {
			p.excluir();
		}else {
			tp.PessoaNaoEncontrada();
		}
	}

	private void alterar() {

		Pessoa p = tp.exibirConsultar();
		p = p.consultar();
		if(p != null) {
			p = tp.exibircadastrarAtualuzar(ce, p);
			p.atualizar();
		}else {
			tp.PessoaNaoEncontrada();
		}
	}

	private void consultar() {

		Pessoa p = tp.exibirConsultar();
		p = p.consultar();
		if(p != null) {
			tp.exibirConsultar(p);
		}else {
			tp.PessoaNaoEncontrada();
		}

	}

	private void cadastrar() throws Exception{

		Pessoa p = tp.exibircadastrarAtualuzar(ce, null);
		p.salvar();
	}

	private void listar(){
		ArrayList<Pessoa> alP = p.listar();
		for (Pessoa pessoa : alP) {
			tp.exibirConsultar(pessoa);
		}
	}

}
