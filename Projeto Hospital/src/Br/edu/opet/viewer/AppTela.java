package Br.edu.opet.viewer;


import Br.edu.opet.util.AppUtil;

public class AppTela {

	public int menu(){

		int opc = -1;

		System.out.println("=============== Menu ===============");
		System.out.println("=====                          =====");
		System.out.println("=====     1 - Pessoa           =====");
		System.out.println("=====     2 - Especialidade    =====");
		System.out.println("=====     0 - Sair             =====");
		System.out.println("====================================");
		System.out.print("Escolha sua op��o: ");
		opc = AppUtil.readint();

		
		return opc;
	}

	public void popUpErro(){

		System.err.println("Op��o inv�lida em Menu Principal!");

	}
}
