
public class Type<T> {
	
	private T myVariable;
	
	public Type (T ob){
		
		this.myVariable=ob;
	}
	
	public Type (){
		
		this.myVariable=null;
	}
	
	public void setVariable(T setVariable)
	{
		this.myVariable=setVariable;
	}
	
	public T getVariable()
	{
		return this.myVariable;
	}
	
}
