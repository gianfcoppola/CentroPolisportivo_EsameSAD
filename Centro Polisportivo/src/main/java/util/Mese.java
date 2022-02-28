package util;

import java.util.ArrayList;
import java.util.List;

public class Mese {

	private String nomeMese;
	private int giorniMese;
	private int giornoPrimoDelMese;

	private List<Integer> lunedi = new ArrayList<Integer>();
	private List<Integer> martedi = new ArrayList<Integer>();
	private List<Integer> mercoledi = new ArrayList<Integer>();
	private List<Integer> giovedi = new ArrayList<Integer>();
	private List<Integer> venerdi = new ArrayList<Integer>();
	private List<Integer> sabato = new ArrayList<Integer>();
	private List<Integer> domenica = new ArrayList<Integer>();
	
	
	public Mese(String nomeMese, int giorniMese, int giornoPrimoDelMese) {
		
		this.nomeMese = nomeMese;
		this.giorniMese = giorniMese;
		
		int i=0;
		int giornoSuccessivoDaSettare=0;
		while(i<giorniMese) {
			
			//per capire il primo del mese che giorno è (lun, mar, mer, ...)
			if(i==0) {
				//se il primo del mese è un lunedi
				if(giornoPrimoDelMese==1) {
					lunedi.add(i+1);
					giornoSuccessivoDaSettare=2;
				}
					
				else if(giornoPrimoDelMese==2) {
					martedi.add(i+1);
					giornoSuccessivoDaSettare=3;
				}
					
				else if(giornoPrimoDelMese==3) {
					mercoledi.add(i+1);
					giornoSuccessivoDaSettare=4;
				}
					
				else if(giornoPrimoDelMese==4) {
					giovedi.add(i+1);
					giornoSuccessivoDaSettare=5;
				}
					
				else if(giornoPrimoDelMese==5) {
					venerdi.add(i+1);
					giornoSuccessivoDaSettare=6;
				}
					
				else if(giornoPrimoDelMese==6) {
					sabato.add(i+1);
					giornoSuccessivoDaSettare=7;
				}
					
				else if(giornoPrimoDelMese==7) {
					domenica.add(i+1);
					giornoSuccessivoDaSettare=1;
				}
			}
			else {
				if(giornoSuccessivoDaSettare==1) {
					lunedi.add(i+1);
					giornoSuccessivoDaSettare = 2;
				}
				else if(giornoSuccessivoDaSettare==2) {
					martedi.add(i+1);
					giornoSuccessivoDaSettare = 3;
				}
				else if(giornoSuccessivoDaSettare==3) {
					mercoledi.add(i+1);
					giornoSuccessivoDaSettare = 4;
				}
				else if(giornoSuccessivoDaSettare==4) {
					giovedi.add(i+1);
					giornoSuccessivoDaSettare = 5;
				}
				else if(giornoSuccessivoDaSettare==5) {
					venerdi.add(i+1);
					giornoSuccessivoDaSettare = 6;
				}
				else if(giornoSuccessivoDaSettare==6) {
					sabato.add(i+1);
					giornoSuccessivoDaSettare = 7;
				}
				else if(giornoSuccessivoDaSettare==7) {
					domenica.add(i+1);
					giornoSuccessivoDaSettare = 1;
				}
			}
			
			i++;
		}
		
	}
	
	
	
	
	public String getNomeMese() {
		return nomeMese;
	}




	public void setNomeMese(String nomeMese) {
		this.nomeMese = nomeMese;
	}




	public int getGiorniMese() {
		return giorniMese;
	}




	public void setGiorniMese(int giorniMese) {
		this.giorniMese = giorniMese;
	}




	public int getGiornoPrimoDelMese() {
		return giornoPrimoDelMese;
	}




	public void setGiornoPrimoDelMese(int giornoPrimoDelMese) {
		this.giornoPrimoDelMese = giornoPrimoDelMese;
	}




	public List<Integer> getLunedi() {
		return lunedi;
	}




	public void setLunedi(List<Integer> lunedi) {
		this.lunedi = lunedi;
	}




	public List<Integer> getMartedi() {
		return martedi;
	}




	public void setMartedi(List<Integer> martedi) {
		this.martedi = martedi;
	}




	public List<Integer> getMercoledi() {
		return mercoledi;
	}




	public void setMercoledi(List<Integer> mercoledi) {
		this.mercoledi = mercoledi;
	}




	public List<Integer> getGiovedi() {
		return giovedi;
	}




	public void setGiovedi(List<Integer> giovedi) {
		this.giovedi = giovedi;
	}




	public List<Integer> getVenerdi() {
		return venerdi;
	}




	public void setVenerdi(List<Integer> venerdi) {
		this.venerdi = venerdi;
	}




	public List<Integer> getSabato() {
		return sabato;
	}




	public void setSabato(List<Integer> sabato) {
		this.sabato = sabato;
	}




	public List<Integer> getDomenica() {
		return domenica;
	}




	public void setDomenica(List<Integer> domenica) {
		this.domenica = domenica;
	}

	public static String numeroToMese (int numeroMese) {
		if(numeroMese == 1)
			return "Gennaio";
		else if (numeroMese == 2)
			return "Febbraio";
		else if (numeroMese == 3)
			return "Marzo";
		else if (numeroMese == 4)
			return "Aprile";
		else if (numeroMese == 5)
			return "Maggio";
		else if (numeroMese == 6)
			return "Giugno";
		else if (numeroMese == 7)
			return "Luglio";
		else if (numeroMese == 8)
			return "Agosto";
		else if (numeroMese == 9)
			return "Settembre";
		else if (numeroMese == 10)
			return "Ottobre";
		else if (numeroMese == 11)
			return "Novembre";
		else
			return "Dicembre";
	}


	public static void main (String[] args) {
		Mese m = new Mese("marzo", 31, 2);
		System.out.println(m.getLunedi().get(0));
		System.out.println(m.getLunedi().get(1));
		System.out.println(m.getLunedi().get(2));
		System.out.println(m.getLunedi().get(3));
		
		System.out.println(m.getGiovedi().get(0));
		System.out.println(m.getGiovedi().get(1));
		System.out.println(m.getGiovedi().get(2));
		System.out.println(m.getGiovedi().get(3));
		System.out.println(m.getGiovedi().get(4));
		
		for(int i=0; i<m.getMercoledi().size(); i++) {
			System.out.println(m.getMercoledi().get(i));
		}
		
	}

}