package reader.record;

import reader.menu.RdrMenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RdrRecordBackBtnListener implements ActionListener {

    private RdrMenuWindow rdrMenuWindow;
    private RdrRecordWindow recordWindow;

    public RdrRecordBackBtnListener(RdrMenuWindow rdrMenuWindow, RdrRecordWindow recordWindow) {
        this.rdrMenuWindow = rdrMenuWindow;
        this.recordWindow = recordWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        rdrMenuWindow.setLocationRelativeTo(null);
        rdrMenuWindow.setVisible(true);
        recordWindow.dispose();
    }

}
