package personal;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JTable;


public class PopupListener extends MouseAdapter {

    private final JPopupMenu popup;
    private final JTable table;

    public PopupListener(JPopupMenu popupMenu, JTable jtable) {
        popup = popupMenu;
        table = jtable;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (table.getSelectedRow() != -1) {
            ShowPopup(e);
        }
    }
    
    private void ShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
  
}


