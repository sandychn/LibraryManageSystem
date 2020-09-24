package reader.record;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import reader.menu.RdrMenuWindow;

public class RdrRecordWindowListener implements WindowListener {

	private RdrMenuWindow rdrMenuWindow;
	private RdrRecordWindow recordWindow;

	public RdrRecordWindowListener(RdrMenuWindow rdrMenuWindow, RdrRecordWindow recordWindow) {
		this.rdrMenuWindow = rdrMenuWindow;
		this.recordWindow = recordWindow;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		rdrMenuWindow.setLocationRelativeTo(null);
		rdrMenuWindow.setVisible(true);
		recordWindow.dispose();
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

}
