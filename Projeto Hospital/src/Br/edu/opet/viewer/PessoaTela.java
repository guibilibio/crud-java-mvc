package Br.edu.opet.viewer;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.opet.util.Reader;
import Br.edu.opet.controller.EspController;
import Br.edu.opet.model.Adm;
import Br.edu.opet.model.Enf;
import Br.edu.opet.model.Especialidade;
import Br.edu.opet.model.Med;
import Br.edu.opet.model.Pessoa;
import Br.edu.opet.util.AppUtil;

public class PessoaTela {

	public Pessoa exibircadastrarAtualuzar(EspController ce, Pessoa p){

		int opc = -1;

		do{
			opc = menuTipoPessoa(); // DropBox

		}while(!AppUtil.isValid(opc));

		System.out.print("Nome"+(p!=null?"["+p.getNome()+"]":"")+": ");
		String nome = Reader.readString();
		nome = nome.equalsIgnoreCase("")?p.getNome():nome;

		System.out.print("Cpf" +(p!=null?"["+p.getCPF()+"]":"")+": ");
		String cpf = Reader.readString();
		cpf = cpf.equalsIgnoreCase("")?p.getCPF():cpf;

		System.out.print("Sexo"+(p!=null?"["+p.getSexo()+"]":"")+": ");
		String sexo = Reader.readString();
		sexo = sexo.equalsIgnoreCase("")?p.getSexo():sexo;

		System.out.print("Telefone"+(p!=null?"["+p.getTelefone()+"]":"")+": ");
		String telefone = Reader.readString();
		telefone = telefone.equalsIgnoreCase("")?p.getTelefone():telefone;

		System.out.print("Altura"+(p!=null?"["+p.getAltura()+"]":"")+": ");
		double alt = AppUtil.readDouble();
		alt = alt ==-2.0?p.getAltura():alt;

		System.out.print("Data de nascimento"+(p!=null?"["+p.getDtNasc()+"]":"")+": ");
		Date dtNasc = null;


		do{
			dtNasc = AppUtil.readDate();

		}while(dtNasc == null);

		dtNasc = dtNasc.getTime() == 0 ?p.getDtNasc():dtNasc;

		if(opc==1){

			Especialidade e = ce.getEspecialidade(); // Mandatório nova especialidade
			p = new Med(p!=null?p.getId():0,nome, cpf, sexo, telefone, alt, dtNasc, e);
		}
		if(opc==2){

			System.out.print("Carga Horária: ");
			int cargahoraria = AppUtil.readint();
			p = new Enf(nome, cpf, sexo, telefone, alt, dtNasc, cargahoraria);
		}
		if(opc==3){

			System.out.print("Salário: ");
			double salfixo = AppUtil.readDouble();
			p = new Adm(nome, cpf, sexo, telefone, alt, dtNasc, salfixo);
		}
		return p;
	}

	public void exibirConsultar(Pessoa p) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("===================================");
		System.out.println("Nome: " + p.getNome());
		System.out.println("CPF: " + p.getCPF());
		System.out.println("Sexo: " + p.getSexo());
		System.out.println("Telefone: " + p.getTelefone());	
		System.out.println("Salário: " + p.getSalario());
		System.out.println("Altura: " + p.getAltura());
		System.out.println("Data de nascimento: " + sdf.format(p.getDtNasc()));
		System.out.println("Idade: " + p.getIdade() + " anos");
		System.out.println(p.getAtributo());

	}

	public Pessoa exibirConsultar() {

		System.out.println("====================================");
		System.out.print("CPF: ");
		String cpf = Reader.readString();
		return new Pessoa(cpf);
	}

	public int menuTipoPessoa(){

		int op = -1;

		System.out.println("===================================");
		System.out.println("1 - Médico");
		System.out.println("2 - Enfermeiro");
		System.out.println("3 - Administrativo");
		System.out.println("====================================");
		System.out.print("Escolha sua opção: ");
		op = AppUtil.readint();

		return op;
	}

	public int menu() throws Exception{

		int opc = -1;
		System.out.println("====================================");
		System.out.println("====       1 - Cadastrar        ====");
		System.out.println("====       2 - Consultrar       ====");
		System.out.println("====       3 - Alterar          ====");
		System.out.println("====       4 - Excluir          ====");
		System.out.println("====       5 - Listar           ====");
		System.out.println("====       0 - Voltar           ====");
		System.out.println("====================================");
		System.out.print("Escolha sua opção: ");
		opc = AppUtil.readint();

		return opc;
	}

	public void popUpErro(){

		System.err.println("Opção inválida no Menu Pessoa!");
	}

	public void PessoaNaoEncontrada() {
		System.out.println("Pessoa não encontrada!");
	}


}

