/** RMITest, basicJavaRMIExample, 27.03.2018*/
package basicJavaRMIExample;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * <h3>{@link BasicRMIClient}</h3>
 * @author kuh1j
 * @version 27.03.2018 09:04:40
 */
public class BasicRMIClient
{
	public static void main(String[] args) throws Exception
	{
		Registry reg = LocateRegistry.getRegistry( "localhost" );
		RemoteObjectInterface obj = (RemoteObjectInterface)reg.lookup( "test" );
		String answer = obj.doRemoteAction( "!uoy kcuf" );
		System.out.println( "Client: server told me: '" + answer + "'" );
	}
}
