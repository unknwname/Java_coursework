package personal.Forms;

import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import personal.Main;
import personal.Objects.DB;
import personal.Objects.Position;
import personal.PopupListener;
import personal.PopupMenuListener;

import personal.TableModel.PositionTableModel;

public class Positions extends javax.swing.JFrame {

    public static PositionTableModel positionModel;
    DB dataBase;

    public Positions(DB dataBase) {
        this.dataBase = dataBase;
        positionModel = new PositionTableModel(dataBase);
        initComponents();
        createRBMenu();
    }

    private void createRBMenu() {

        JPopupMenu menu = new JPopupMenu();
        MouseListener popupListener = new PopupListener(menu, positionTable);
        PopupMenuListener menuListen = new PopupMenuListener("Вид работ");
        JMenuItem change = new JMenuItem("Изменить");
        JMenuItem remove = new JMenuItem("Удалить");
        change.setActionCommand("change");
        change.addActionListener(menuListen);

        remove.setActionCommand("remove");
        remove.addActionListener(menuListen);

        menu.add(change);
        menu.add(remove);

        positionTable.addMouseListener(popupListener);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addPos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        positionTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Вид работ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        addPos.setText("Добавить");
        addPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPosActionPerformed(evt);
            }
        });

        positionTable.setModel(positionModel);
        jScrollPane1.setViewportView(positionTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addPos, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(addPos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPosActionPerformed
        String result = JOptionPane.showInputDialog("Укажите вид работы");

        if (result == null || result.length() == 0) {
            JOptionPane.showMessageDialog(this, "Новый вид работы не был добавлен!");
        } else {
            int id = this.dataBase.addPosition(result);
            positionModel.Add(new Position(result, id));
            JOptionPane.showMessageDialog(this, "Новый вид работы был добавлен!");
        }
    }//GEN-LAST:event_addPosActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       Main.getInstance().refresh();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPos;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable positionTable;
    // End of variables declaration//GEN-END:variables
}
