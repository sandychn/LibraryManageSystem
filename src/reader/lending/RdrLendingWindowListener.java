package reader.lending;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import reader.menu.RdrMenuWindow;

public class RdrLendingWindowListener implements WindowListener {

	private RdrMenuWindow rdrMenuWindow;

	public RdrLendingWindowListener(RdrMenuWindow rdrMenuWindow) {
		this.rdrMenuWindow = rdrMenuWindow;
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		rdrMenuWindow.setLocationRelativeTo(null);
		rdrMenuWindow.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}

}
