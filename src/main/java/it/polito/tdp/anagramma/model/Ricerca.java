package it.polito.tdp.anagramma.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	private List <String> soluzione;

	/**
	 * Genera tutti gli anagrammi della parola specificata in ingresso
	 * @param string parola da anagrammare
	 * @return elenco di tutti gli anagrammi della parola data
	 */
	public List<String> anagrammi(String parola) {
		//caso iniziale
		this.soluzione= new ArrayList<String>();
		//devo preparare tutte le variabili di cui ha bisogno la funzione ricorsiva
		parola=parola.toUpperCase();
		
		List <Character> disponibili = new ArrayList <>();
		for (int i=0; i<parola.length(); i++) {
			disponibili.add(parola.charAt(i));//estraggo il carattere in posizione i-esima nella stringa e lo aggiungo ai caratteri disponibili
		}
		
		//avvio la ricorsione
		cerca("",0,disponibili); 
		
		return this.soluzione;
	}
	
	//funzione ricorsiva che sfruttiamo per costruirla
	/**
	 * Procedura ricorsiva oer il calcolo dell'anagramma 
	 * 
	 * @param parziale parte iniziale del'anagramma costruito fin'ora
	 * @param livello livello della ricorsione, sempre uguale a parziale.lenght()
	 * @param disponibili insieme delle lettere non ancora utilizzate (rappresentato come una lista perche l'insieme puo contenere dei duplicati)
	 */
	private void cerca (String parziale, int livello, List<Character> disponibili) {
		if (disponibili.size()==0) { //livello==parola.lenght()
			//caso terminale
			
			//if (parziale è nel dizionario) la aggiungo. Ho comunque calcolato tutto, ma non stampo tutto
			//if (parziale non è presente nella soluzione) adesso lo aggiungo
			this.soluzione.add(parziale);
			
		}
		
		//caso normale
		//provare ad aggiungere alla soluzione parziale tutti i caratteri presenti tra i disponibili
		for (Character ch: disponibili) {
			String tentativo = parziale + ch; //non posso andare a modificare la stringa parziale, quindi ne devo creare una temporanea. 
												// non ho più bisogno del backtracking
			
			//if (nel dizionario esistono delle parole che iniziano con 'tentativo'?)
			//se si continuo con la ricorsione, altrimenti mi fermo prima
			
			List <Character> rimanenti = new ArrayList <>(disponibili); //ne devo creare un'altra perche lo sto iterano in questo momento
			rimanenti.remove(ch); //però questa cosa rallenta molto il programma
			cerca(tentativo, livello+1, rimanenti);
		}
	}
	
}

/* 
 * Dato di partenza: parola da angrammare, di lunghezza N
 * SOLUZIONE PARZIALE:parte dell'anagramma gia costruito (i primi caratteri della parola)
 * Livello: numero di lettere di cui è composta la soluzione parziale
 * Soluzione finale: soluzione di lunghezza N (qui capisco che sono in un caso terminale)
 * Caso terminale: salvare le soluzioni trovate
 * 
 * Generazione delle nuove soluzioni: provare ad aggiungere una lettera scegliendola tra quelle che non sono ancora state utilizzate
 * nella soluzione parziale
 */
 
