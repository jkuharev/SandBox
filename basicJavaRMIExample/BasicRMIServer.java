package basicJavaRMIExample;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.mz.jk.jsix.ui.JTextAreaOutputStream;

/** RMITest, , 21.03.2018*/
/**
 * <h3>{@link BasicRMIServer}</h3>
 * @author kuh1j
 * @version 21.03.2018 12:19:29
 */
public class BasicRMIServer extends JFrame
{
	private Registry reg = null;
	private int srvPort = 1099;
	private String srvHost = "127.0.0.1";
	private String rmiName = "test";
	private RemoteObjectInterface rmiObject = null;

	private JTextArea txt = new JTextArea();
	private JTextAreaOutputStream out = new JTextAreaOutputStream( txt, true, true );

	public BasicRMIServer()
	{
		super();
		initGUI();
		initRMI();
	}

	private void initGUI()
	{
		this.getContentPane().add( new JScrollPane( txt ) );
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
		this.setVisible( true );
		this.setSize( 640, 480 );
		this.toFront();
	}

	public void initRMI()
	{
		System.setProperty( "java.rmi.server.hostname", "localhost" );
		try
		{
			System.out.println( "creating new RMI registry for tcp port " + srvPort + " ..." );
			reg = LocateRegistry.createRegistry( srvPort );
		}
		catch (Exception e)
		{
			System.err.println( "failed to create new RMI registry ... " );
			System.out.println( "testing for existing RMI registry for tcp port " + srvPort + " ..." );
			try
			{
				reg = LocateRegistry.getRegistry( srvPort );
				System.out.println( "existing RMI registry detected." );
			}
			catch (Exception e2)
			{
				System.err.println( "failed to locate existing registry ..." );
			}
		}

		try
		{
			rmiObject = new RemoteObjectImplementation();
			Naming.rebind( rmiName, rmiObject );
			System.out.println(
					"DB-Server is running now.\n" +
							"Use 'rmi://localhost/" + rmiName + "' to connect from local client applications\n" +
							"or 'rmi://[IP-Adress]/" + rmiName + "' to connect from remote applications." );
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println( "failed to register RMI service." );
		}
	}

	public static void main(String[] args)
	{
		BasicRMIServer srvWin = new BasicRMIServer();
	}
}
