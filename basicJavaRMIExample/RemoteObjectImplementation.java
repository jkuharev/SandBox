package basicJavaRMIExample;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/** RMITest, , 21.03.2018*/
/**
 * Implementation of a remote object
 * that we can pass between server and client systems
 */
public class RemoteObjectImplementation extends UnicastRemoteObject implements RemoteObjectInterface
{
	/**
	 * @throws RemoteException
	 */
	protected RemoteObjectImplementation() throws RemoteException
	{
		super();
	}

	@Override public String doRemoteAction(String value) throws RemoteException
	{
		System.out.println( "doing something to the input string ..." );
		String result = new StringBuilder( value ).reverse().toString();
		System.out.println( "request: " + value );
		return result;
	}
}
