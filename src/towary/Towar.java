package towary;

public class Towar {
	private String id;
	private double waga;
	public Towar(String id,double waga){
		this.id=id;
		this.waga=waga;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public double getWaga() {
		return waga;
	}
	public void setWaga(double waga) {
		this.waga = waga;
	}	
}
