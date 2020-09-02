package Br.edu.opet.controller;

import Br.edu.opet.viewer.AppTela;

public class AppController {

	private PessoaController pc;
	private EspController ce;
	private AppTela appTela;

	public AppController(){

		this.appTela = new AppTela();
		this.ce = new EspController();
		this.pc = new PessoaController(this.ce);
	}

	public void executar() throws Exception {

		int op = appTela.menu();

		while(op!=0){

			switch (op){
			case 1: // PESSOA
				pc.init();
				break;
			case 2: // ESPECIALIDADE
				ce.init();
				break;
			default:
				appTela.popUpErro();
				break;
			}

			op = appTela.menu();
		}
	}
}
