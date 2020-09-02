package Br.edu.opet.controller;

import java.util.ArrayList;

import Br.edu.opet.model.Especialidade;
import Br.edu.opet.viewer.EspTela;

public class EspController {

	EspTela et;
	Especialidade espec;

	public EspController(){

		this.et = new EspTela();
		this.espec = new Especialidade();
	}

	public void  init() throws Exception {

		int op = et.menu();

		while(op!=0){

			switch (op){
			case 1: // cadastro
				cadastrar();
				break;
			case 2: // consulta
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
				et.popUpErro();
				break;
			}
			op = et.menu();
		}
	}

	private void excluir() {

		Especialidade e = et.exibirConsultar();
		if(e.consultar()) {
			if (!e.excluir()) {
				et.exibirNaoAtualizado(e);
			}
		}else{
			et.exibirEspNaoEncontrada(e.getId());
		}
	}

	public Especialidade getEspecialidade(){

		int opcEsp = -1;
		ArrayList<Especialidade> alE = this.espec.listar();
		
		do{
			opcEsp = EspTela.dropbox(alE);
			this.espec.setId(opcEsp);
		}while(!this.espec.consultar());

		return this.espec; // recuperando o objeto da lista
	}

	private void cadastrar(){

		Especialidade e = et.exibircadastrarAlterar(null);
		e.salvar();
	}

	private void alterar(){

		Especialidade e = et.exibirConsultar();
		if(e.consultar()) {
			e = et.exibircadastrarAlterar(e);
			if (!e.atualizar()) 
				et.exibirNaoAtualizado(e);
		}else{
			et.exibirEspNaoEncontrada(e.getId());
		}
	}

	private void listar(){
		
		ArrayList<Especialidade> alE = this.espec.listar();
		for (Especialidade e : alE) {
			et.exibirconsultar(e);
		}
	}

	private void consultar() {

		Especialidade e = et.exibirConsultar();
		if(e.consultar()) {
			et.exibirconsultar(e);
		}else{
			et.exibirEspNaoEncontrada(e.getId());
		}
	}

}
