package towary;
import java.util.Vector;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class A extends Thread{
	private Vector<Towar> towary;
	private int liczbaObiektow;
	private boolean czyJeszczeCzyta;
	public A(){
		towary=new Vector<Towar>();
		liczbaObiektow=0;
		czyJeszczeCzyta=true;
	}
	public boolean isCzyJeszczeCzyta() {
		return czyJeszczeCzyta;
	}
	public void setCzyJeszczeCzyta(boolean czyJeszczeCzyta) {
		this.czyJeszczeCzyta = czyJeszczeCzyta;
	}
	public Vector<Towar> getTowary() {
		return towary;
	}
	public int getLiczbaObiektow(){
		return liczbaObiektow;
	}
	public void setLiczbaObiektow(int liczbaObiektow){
		this.liczbaObiektow=liczbaObiektow;
	}
	@Override
	public void run(){
		FileReader fr=null;
		try{
			fr=new FileReader("../Towary.txt");
		}catch(FileNotFoundException e){
			System.out.println("nie mozna bylo znalezc pliku");
			return;
		}
		
		BufferedReader in=new BufferedReader(fr);
		String linia="";
		try{
		synchronized(Main.KONSOLA){
			while((linia=in.readLine())!=null){
			
				Pattern pattern=Pattern.compile("(\\d)+(\\s)+(\\d)+");
				Matcher matcher=pattern.matcher(linia);
				
				while(matcher.find()){
					while(Main.WatekB.getNumerOb()<liczbaObiektow){
						Main.KONSOLA.notifyAll();
						try{
							Main.KONSOLA.wait();
						}catch(InterruptedException ert){
							System.out.println("problem z waitowaniem");
							return;
						}
					}
					String[] tablica=matcher.group().split(" ");
					towary.add(new Towar(tablica[0],Double.parseDouble(tablica[1])));
					if((liczbaObiektow+1)%200==0){
						System.out.println("utworzono "+(liczbaObiektow+1)+" obiektów");
					}
					liczbaObiektow++;
					Main.KONSOLA.notifyAll();
					try{
						Main.KONSOLA.wait();
					}catch(InterruptedException ert){
						System.out.println("b³ad przy waitowaniu");
						return;
					}
				}	
			}
		czyJeszczeCzyta=false;
		Main.KONSOLA.notifyAll();
		}
		}catch(IOException e){
			System.out.println("b³ad przy odczycie");
			return;
		}
		try{
			in.close();
		}catch(IOException erty){
			System.out.println("b³¹d przy zamykaniu pliku");
			return;
		}
		
		return;
	
	}
}
