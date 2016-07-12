package strings;

public class StringTask implements Runnable{
	private String napis;
	private String result;
	private int liczbaPowielen;
	private int index=0;
	private boolean done;
	private Thread t;
	private TaskState state;
	public enum TaskState{ CREATED,RUNNING, ABORTED, READY }
	
	public StringTask(String napis,int liczbaPowielen){
		this.napis=napis;
		this.liczbaPowielen=liczbaPowielen;
		this.state=TaskState.CREATED;
	}
	public StringTask(){
		this.state=TaskState.CREATED;
	}
	public boolean isDone(){
		return done;
	}
	public TaskState getState() {
		return state;
	}
	public String getResult(){
		return result;
	}
	public void start(){
		if(t==null){
			t=new Thread(this,"Watek1");
			t.start();
		}
	}
	public void abort(){
		if(t!=null){
			state=TaskState.ABORTED;
			t.interrupt();
		}
		else{
			return;
		}
	}
	@Override
	public void run() {
		state=TaskState.RUNNING;
		while( index<liczbaPowielen&&state==TaskState.RUNNING){
			result+=napis;
			index++;
		}
		if(index>=liczbaPowielen){
			done=true;
			state=TaskState.READY;
		}
		else{
			done=false;
		}
		
	}
	
}
