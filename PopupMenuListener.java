package personal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import personal.Forms.AddEmployee;
import personal.Forms.Filials;
import static personal.Forms.Filials.filialModel;
import personal.Forms.Positions;
import personal.Objects.Filial;
import personal.Objects.Position;

public class PopupMenuListener implements ActionListener {

    private final String button;

    public PopupMenuListener(String text) {
        this.button = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (button) {
            case "Работы":
                switch (e.getActionCommand()) {
                    case "change":
                        
                        new AddEmployee(Main.employeeModel.getItem(Main.EmployeeTable.getSelectedRow()), Main.dataBase, "update").setVisible(true);
                        
                        
                        break;
                    case "remove":
                        Main.employeeModel.Remove( Main.EmployeeTable.getSelectedRow());
                        JOptionPane.showMessageDialog(Positions.positionTable, "Вид работы удален");
                        Main.getInstance().refresh();
                        break;
                    default:
                        System.out.println("Неизвесная команда");
                }
                break;
            case "Вид работ":
                switch (e.getActionCommand()) {
                    case "change":
                        
                        int row = Positions.positionTable.getSelectedRow();

                        String result = JOptionPane.showInputDialog("Измените название вида работ", Positions.positionTable.getValueAt(row, 0));
                        if ((result == null) || (result.length() == 0)) {
                            JOptionPane.showMessageDialog(Positions.positionTable, "Название вида работ не было изменено");
                        } else {
                            Positions.positionModel.Update(row, new Position(result, Positions.positionModel.getItem(row).getPosition_id()));
                            JOptionPane.showMessageDialog(Positions.positionTable, "Название вида работ изменено");
                        }
                        
                        
                        break;
                    case "remove":
                        Positions.positionModel.Remove(Positions.positionTable.getSelectedRow());
                        JOptionPane.showMessageDialog(Positions.positionTable, "Вид работы удален");
                        break;
                    default:
                        System.out.println("Неизвесная команда");
                }
                break;
            case "Заказы":
                switch (e.getActionCommand()) {
                    case "change":
                        int row = Filials.filialsTable.getSelectedRow();

                        String result = JOptionPane.showInputDialog("Измените название заказа", Filials.filialsTable.getValueAt(row, 0));
                        if ((result == null) || (result.length() == 0)) {
                            JOptionPane.showMessageDialog(Filials.filialsTable, "Заказ не был изменен");
                        } else {
                            filialModel.Update(row, new Filial(result, Filials.filialModel.getItem(row).getFilial_id()));
                            JOptionPane.showMessageDialog(Filials.filialsTable, "Заказ изменен");
                        }

                        break;
                    case "remove":
                        Filials.filialModel.Remove(Filials.filialsTable.getSelectedRow());
                        JOptionPane.showMessageDialog(Filials.filialsTable, "Заказ был удален");
                        break;
                    default:
                        System.out.println("Неизвесная команда");
                }
                break;
        }

    }
}
