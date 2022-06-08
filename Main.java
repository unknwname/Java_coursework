package personal;

import java.awt.event.MouseListener;
import personal.Objects.DB;
import personal.Objects.Filial;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import personal.Forms.AddEmployee;
import personal.Forms.Filials;
import personal.Forms.FoundEmployee;
import personal.Forms.Positions;
import personal.Objects.Part;
import personal.TableModel.EmployeeTableModel;

public class Main extends javax.swing.JFrame {

    public static DB dataBase = new DB();
    public static EmployeeTableModel employeeModel;
    private static Main instance;
    ArrayList<Filial> filials = new ArrayList();

    public static Main getInstance() {

        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }


    private Main() {
        employeeModel = new EmployeeTableModel(dataBase);
        employeeModel.getAll();
        initComponents();
        refresh();
        createRBMenu();
    }

    public final void refresh() {
        employeeModel.getAll();
        filials.clear();
        chooseFilial.removeAllItems();
        chooseFilial.addItem("Все");

        for (Filial filial : dataBase.getFilials()) {
            filials.add(filial);
            chooseFilial.addItem(filial.getName());
        }
    }

    private void createRBMenu() {

        JPopupMenu menu = new JPopupMenu();
        MouseListener popup1Listener = new PopupListener(menu, EmployeeTable);
        PopupMenuListener menuListen = new PopupMenuListener("Работы");
        JMenuItem change = new JMenuItem("Изменить");
        JMenuItem remove = new JMenuItem("Удалить");

        change.setActionCommand("change");
        change.addActionListener(menuListen);

        remove.setActionCommand("remove");
        remove.addActionListener(menuListen);

        menu.add(change);
        menu.add(remove);

        EmployeeTable.addMouseListener(popup1Listener);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        EmployeeTable = new javax.swing.JTable();
        chooseFilial = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addEmpl = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFilial = new javax.swing.JMenu();
        filialItem = new javax.swing.JMenuItem();
        position = new javax.swing.JMenu();
        menuPosition = new javax.swing.JMenuItem();
        searchMenu = new javax.swing.JMenu();
        searchItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Автосервис");

        EmployeeTable.setModel(employeeModel);
        jScrollPane1.setViewportView(EmployeeTable);

        chooseFilial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFilialActionPerformed(evt);
            }
        });

        jLabel1.setText("Заказы");

        jLabel2.setText("Вид работ");

        addEmpl.setText("Добавить вид работы");
        addEmpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmplActionPerformed(evt);
            }
        });

        menuFilial.setText("Заказы");

        filialItem.setText("Заказы");
        filialItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filialItemActionPerformed(evt);
            }
        });
        menuFilial.add(filialItem);

        jMenuBar1.add(menuFilial);

        position.setText("Вид работ");

        menuPosition.setText("Вид работ");
        menuPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPositionActionPerformed(evt);
            }
        });
        position.add(menuPosition);

        jMenuBar1.add(position);

        searchMenu.setText("Поиск");

        searchItem.setText("Поиск мастера");
        searchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchItemActionPerformed(evt);
            }
        });
        searchMenu.add(searchItem);

        jMenuBar1.add(searchMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(chooseFilial, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chooseFilial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addEmpl)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void filialItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filialItemActionPerformed
        new Filials(dataBase).setVisible(true);
    }//GEN-LAST:event_filialItemActionPerformed

    private void menuPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPositionActionPerformed
        new Positions(dataBase).setVisible(true);
    }//GEN-LAST:event_menuPositionActionPerformed

    private void chooseFilialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFilialActionPerformed
        if (chooseFilial.getSelectedIndex() != 0){
            addEmpl.setEnabled(true);   
        }else{
            addEmpl.setEnabled(false);
        }
        
        employeeModel.refreshFilials();
        employeeModel.changeFilial(chooseFilial.getSelectedIndex()-1);
        
    }//GEN-LAST:event_chooseFilialActionPerformed

    private void addEmplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmplActionPerformed
        new AddEmployee(chooseFilial.getSelectedIndex()-1, dataBase, "add").setVisible(true);
    }//GEN-LAST:event_addEmplActionPerformed

    private void searchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchItemActionPerformed
        String result = JOptionPane.showInputDialog("Введите ФИО мастера");
        ArrayList<Part> foundPart = new ArrayList();
        if (result !=null && result.length() >0){
            for (Part part : dataBase.getEmployee()){
                if (part.getFio().equalsIgnoreCase(result) || part.getFio().toLowerCase().contains(result.toLowerCase())){
                    foundPart.add(part);
                }
            }
            new FoundEmployee(foundPart).setVisible(true);
        }
    }//GEN-LAST:event_searchItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable EmployeeTable;
    private javax.swing.JButton addEmpl;
    private javax.swing.JComboBox<String> chooseFilial;
    private javax.swing.JMenuItem filialItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuFilial;
    private javax.swing.JMenuItem menuPosition;
    private javax.swing.JMenu position;
    private javax.swing.JMenuItem searchItem;
    private javax.swing.JMenu searchMenu;
    // End of variables declaration//GEN-END:variables





}
