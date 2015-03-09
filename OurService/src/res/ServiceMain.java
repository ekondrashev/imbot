package res;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;


public class ServiceMain implements WrapperListener{

	public ServiceMain() {
	}

	public static void main(String[] args) {
		WrapperManager.start( new ServiceMain(), args );
	
	}

	@Override
	public void controlEvent(int e) {
		if ( ( e == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT )
                && ( WrapperManager.isLaunchedAsService() || WrapperManager.isIgnoreUserLogoffs() ) )
        {
                // Ignore
        }
        else
        {
            WrapperManager.stop( 0 );
        }
		
	}

	@Override
	public Integer start(String[] args) {
		final TrayIcon trayIcon;
		 
        if (SystemTray.isSupported()) {
 
            SystemTray tray = SystemTray.getSystemTray();
            URL url = ServiceMain.class.getResource("style.png");      
            Image image = Toolkit.getDefaultToolkit().getImage(url);
  
            ActionListener exitListener = new ActionListener() {
 
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            };
 
            final JPopupMenu popup = new JPopupMenu();
            JMenuItem defaultItem = new JMenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
 
            trayIcon = new TrayIcon(image, "Our service");
 
            ActionListener actionListener = new ActionListener() {
 
                public void actionPerformed(ActionEvent e) {
                    trayIcon.displayMessage("Our service",
                            "It's our service",
                            TrayIcon.MessageType.INFO);
                }
            };
 
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);
            trayIcon.addMouseListener(new MouseAdapter() {
 
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popup.setLocation(e.getX(), e.getY());
                        popup.setInvoker(popup);
                        popup.setVisible(true);
                    }
                }
            });
 
            try {
            	Thread ourThread=new Thread(new Logger4j());
            	ourThread.start(); 
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
 
        } else {
            //  System Tray is not supported
        }

		return null;
	}

	@Override
	public int stop(int exitCode) {
		WrapperManager.signalStopping( 60000 );
        // Do some cleanup that takes a while
                        
        WrapperManager.signalStopping( 60000 );
        // Do some more cleanup that takes a while
        
        return exitCode;
	}

}
