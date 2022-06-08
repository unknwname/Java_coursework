package personal.Forms;

import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import personal.Objects.DB;
import personal.Main;
import personal.PopupListener;
import personal.PopupMenuListener;
import personal.TableModel.FilialTableModel;

public class Filials extends JFrame {

    public static FilialTableModel filialModel;
    DB dataBase;

    public Filials(DB dataBase) {
        this.dataBase = dataBase;
        filialModel = new FilialTableModel(dataBase);
        initComponents();
        createMenu();
    }

    private void createMenu() {
        JPopupMenu menu = new JPopupMenu();
        MouseListener popupListener = new PopupListener(menu, filialsTable);
        PopupMenuListener menuListen = new PopupMenuListener("Заказы");
        JMenuItem change = new JMenuItem("Изменить");
        JMenuItem remove = new JMenuItem("Удалить");

        change.setActionCommand("change");
        change.addActionListener(menuListen);

        remove.setActionCommand("remove");
        remove.addActionListener(menuListen);

        menu.add(change);
        menu.add(remove);

        filialsTable.addMouseListener(popupListener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        filialsTable = new javax.swing.JTable();
        addFilial = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Заказы");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        filialsTable.setModel(filialModel);
        jScrollPane1.setViewportView(filialsTable);

        addFilial.setText("Добавить");
        addFilial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFilialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addFilial, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(addFilial)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addFilialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFilialActionPerformed
        String result = JOptionPane.showInputDialog("Введите название заказа");

        if ((result == null) || (result.length() == 0)) {
            JOptionPane.showMessageDialog(this, "Заказ не был добавлен!");
        } else {
            filialModel.Add(result);
            JOptionPane.showMessageDialog(this, "Новый заказ добавлен!");
        }
    }//GEN-LAST:event_addFilialActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Main.getInstance().refresh();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFilial;
    public static javax.swing.JTable filialsTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
