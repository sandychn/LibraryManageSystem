package reader.menu;

import reader.record.RdrRecordWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RdrRecordBtnListener implements ActionListener {

    private RdrMenuWindow rdrMenuWindow;

    public RdrRecordBtnListener(RdrMenuWindow rdrMenuWindow) {
        this.rdrMenuWindow = rdrMenuWindow;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        rdrMenuWindow.setVisible(false);
        @SuppressWarnings("unused")
        RdrRecordWindow recordWindow = new RdrRecordWindow(rdrMenuWindow);
    }

}
