package reader.lending;

import reader.menu.RdrMenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RdrLendingBackBtnListener implements ActionListener {

    private RdrLendingWindow lendingWindow;
    private RdrMenuWindow rdrMenuWindow;

    public RdrLendingBackBtnListener(RdrLendingWindow lendingWindow, RdrMenuWindow rdrMenuWindow) {
        this.lendingWindow = lendingWindow;
        this.rdrMenuWindow = rdrMenuWindow;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        rdrMenuWindow.setLocationRelativeTo(null);
        rdrMenuWindow.setVisible(true);
        lendingWindow.dispose();
    }

}
