package reader.menu;

import reader.lending.RdrLendingWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RdrLendingBtnListener implements ActionListener {

    private RdrMenuWindow rdrMenuWindow;

    public RdrLendingBtnListener(RdrMenuWindow rdrMenuWindow) {
        this.rdrMenuWindow = rdrMenuWindow;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        rdrMenuWindow.setVisible(false);
        @SuppressWarnings("unused")
        RdrLendingWindow lendingWindow = new RdrLendingWindow(rdrMenuWindow);
    }

}
