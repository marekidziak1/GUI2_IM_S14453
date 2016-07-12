package towary;

public class B extends Thread{
	private double suma;
	private int numerOb;
	public B(){
		this.suma=0;
		this.numerOb=0;
	}
	public double getSuma() {
		return suma;
	}
	public void setSuma(double suma) {
		this.suma = suma;
	}
	public int getNumerOb() {
		return numerOb;
	}
	public void setNumerOb(int numerOb) {
		this.numerOb = numerOb;
	}
	@Override
	public void run(){
		synchronized(Main.KONSOLA){
		while(Main.WatekA.isCzyJeszczeCzyta()){
				while(Main.WatekA.getLiczbaObiektow()<=numerOb){
					Main.KONSOLA.notifyAll();
					try{
						Main.KONSOLA.wait();
					}catch(InterruptedException ert){
						System.out.println("Problem z waitowaniem");
						return;
					}
				}
				suma+=Main.WatekA.getTowary().get(numerOb).getWaga();
				if((numerOb+1)%100==0){
					System.out.println("policzono wage "+(numerOb+1)+"towarow");
				}
				numerOb++;
				Main.KONSOLA.notifyAll();
				try{
					Main.KONSOLA.wait();
				}catch(InterruptedException ert){
					System.out.println("b³ad przy waitowaniu");
					return;
				}
				/*
				if(Main.WatekA.isCzyJeszczeCzyta()==false){
					System.out.println("Waga wszystkich towarow wynosi"+suma);
					return;
				}
				*/
		}
		System.out.println("Waga wszystkich towarow wynosi: "+suma);
		Main.KONSOLA.notifyAll();
		}
		return;
	}
//===================
}
