package personal.TableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import personal.Objects.Part;


public class FoundEmployeeTableModel extends AbstractTableModel {
    
    
    private ArrayList<Part> data = new ArrayList();

    
    public FoundEmployeeTableModel(ArrayList<Part> part){
        this.data = part;
    }
    
    public Part[] getItems() {
        return data.stream().toArray(Part[]::new);
    }
    
    public Part getItem(int i){
        return data.get(i);
    }

    @Override
    public int getRowCount() {
        return getItems().length;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return this.getItems()[r].getFio();
            case 1:
                return this.getItems()[r].getPosition_name();
            case 2:
                return this.getItems()[r].getMobile();
            case 3:
                return this.getItems()[r].getEmail();
        }
        return "";
    }
    
    @Override
    public String getColumnName(int c) {
        String сolumn = "";
        switch (c) {
            case 0:
                сolumn = "ФИО мастера";
                break;
            case 1:
                сolumn = "Вид работы";
                break;
            case 2:
                сolumn = "Стоимость работы";
                break;
            case 3:
                сolumn = "Дата работы";
                break;
        }
        return сolumn;
    }
    
}
