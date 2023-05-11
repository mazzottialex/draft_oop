package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import data.Calciatore;
import data.Modulo;

public class Formazione extends Base{
	
	public Formazione() {
		JButton btnScegli=new JButton("Scegli Formazione");
		btnScegli.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		btnScegli.setBackground(Color.white);
		btnScegli.setRolloverEnabled(true);
		btnScegli.setForeground(Color.BLUE);
		Modulo moduli[]=Modulo.values();
		List<Modulo> li=Arrays.asList(Modulo.values());
		li=getRandomMod(li);
		
	}
	private List<Modulo> getRandomMod(List<Modulo> li){
		Random rnd = new Random();
		Set<Integer> posizioni = new HashSet<>();
		for(int i=0; i<5; i++) {
			int num;
			do {
				num=rnd.nextInt(li.size());
			}while(posizioni.contains(num));
			posizioni.add(num);
		}
		
		return posizioni.stream()
				.map(p-> li.get(p))
				.toList();
	}
}
