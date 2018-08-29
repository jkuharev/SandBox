package basicJavaRMIExample;
import java.rmi.Remote;
import java.rmi.RemoteException;

/** RMITest, , 21.03.2018*/
/**
 * <h3>{@link RemoteObjectInterface}</h3>
 * @author kuh1j
 * @version 21.03.2018 12:21:06
 */
public interface RemoteObjectInterface extends Remote
{
	/** will do a remote action that still needs to be implemented */
	public String doRemoteAction(String path) throws RemoteException;
}
