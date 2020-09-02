package Br.edu.opet.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.opet.util.Reader;



public class AppUtil{

	public static boolean isValid(int prof){

		while(true){

			switch (prof){

			case 1: //med
				return true;
			case 2: //enf
				return true;
			case 3: // adm
				return true;
			default:
				return false;
			}
		}
	}

	public static Date toDate(String dt) throws ParseException{

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.parse(dt);
	}

	public static int readint(){

		int opc =-1;
		try{
			opc = Reader.readInt();

		}catch (Exception e){
			System.out.println("------------------------------------");
			System.out.println("Escolha uma opção novamente!");	
			return -1;
		}

		return opc;
	}
	
	public static double readDouble(){

		double opc = -1.0;
		
		try{
			String str = Reader.readString();
			if(str.equalsIgnoreCase("")){
				return -2.0;
			}
			opc = Double.parseDouble(str);
		}catch (Exception e){
			System.out.println("------------------------------------");
			System.out.println("Informe uma altura válida!");	
			return -1.0;
		}
		return opc;
	}
	
	public static Date readDate(){
		Date dt = null;
		try{
			String sdt = Reader.readString();
			if (sdt.equalsIgnoreCase("")) {
				return new Date(0); // 01-01-1970  00:00:00.0000
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dt = sdf.parse(sdt);
			
		}catch (ParseException e){
			System.out.println("Informe uma data válida!");
			System.out.println("------------------------------------");
			System.out.print("Data de nascimento: ");
			return null;
		}
		return dt;
	}

}
