package Br.edu.opet.viewer;

import java.util.ArrayList;

import com.opet.util.Reader;

import Br.edu.opet.model.Especialidade;
import Br.edu.opet.util.AppUtil;

public class EspTela {

	public static int dropbox(ArrayList<Especialidade> alE){

		int opc = -1;

		System.out.println("====================================");

		for(Especialidade e : alE){

			System.out.println(e.getId() + " - " +  e.getDescrição());
		}
		System.out.print("Escolha sua opção: ");
		opc = AppUtil.readint();
		return opc;
	}

	public int menu(){

		int opc = -1;
		System.out.println("====================================");
		System.out.println("====       1 - Cadastrar        ====");
		System.out.println("====       2 - Consultar        ====");
		System.out.println("====       3 - Editar           ====");
		System.out.println("====       4 - Excluir          ====");
		System.out.println("====       5 - Listar           ====");
		System.out.println("====       0 - Voltar           ====");
		System.out.println("====================================");
		System.out.print("Escolha sua opção: ");
		opc = AppUtil.readint();

		return opc;
	}

	public Especialidade exibircadastrarAlterar(Especialidade esp) {
		System.out.println("====================================");
		System.out.print("Descrição"+
				(esp==null?
						""
						:
						"("+esp.getDescrição()+")")
						+":");

		String desc = Reader.readString();


		if (esp == null) 
			esp = new Especialidade();

		if(!desc.equalsIgnoreCase("")) 
			esp.setDescrição(desc);

		return esp;
	}

	public Especialidade exibiralterar() {

		System.out.println("====================================");
		System.out.print("Descrição: ");
		String desc = Reader.readString();
		Especialidade e = new Especialidade();
		e.setDescrição(desc);
		return e;
	}

	public void exibirconsultar(Especialidade e) {

		System.out.println(e.getId() + " - " + e.getDescrição());
	}

	public Especialidade exibirConsultar() {

		System.out.println("====================================");
		System.out.print("Id: ");
		int id = AppUtil.readint();
		return new Especialidade(id);
	}

	public void popUpErro(){

		System.err.println("Opção inválida no Menu Especialidade!");
	}

	public void exibirEspNaoEncontrada(int id) {

		System.err.println("\nA especialidade com ID " + id + " não foi encontrada!");
	}

	public void exibirNaoAtualizado(Especialidade e) {

		System.err.println("\nA atualização da especialidade " + e.getDescrição() + " não ocorreu!");
	}

}
