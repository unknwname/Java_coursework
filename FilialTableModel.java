
package personal.TableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import personal.Objects.DB;
import personal.Objects.Filial;


public class FilialTableModel extends AbstractTableModel {
    
    private ArrayList<Filial> data = new ArrayList();
    private final DB dataBase;
    
    public FilialTableModel(DB dataBase){
        this.dataBase = dataBase;
        this.getAll();
    }
    
    public final void getAll(){
        for(Filial filial: dataBase.getFilials()){
            data.add(filial);
        }
    }
    
    public void Add(String filial){
        int id = this.dataBase.addFilial(filial);
        data.add(new Filial(filial, id));
        this.fireTableDataChanged();
    }
    
    public void Remove(int number){
        this.dataBase.removeFilial(data.get(number).getFilial_id());
        data.remove(number);
        this.fireTableDataChanged();
    }
    
    public void Update(int row, Filial filial){
        this.dataBase.updateFilial(filial.getFilial_id(), filial.getName());
        data.set(row, filial);
        this.fireTableDataChanged();
    }
    
    public Filial[] getItems() {
        return data.stream().toArray(Filial[]::new);
    }
    
    public Filial getItem(int i){
        return data.get(i);
    }

    @Override
    public int getRowCount() {
        return getItems().length;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return this.getItems()[r].getName();
        }
        return "";
    }
    
    @Override
    public String getColumnName(int c) {
        String сolumn = "";
        switch (c) {
            case 0:
                сolumn = "Заказ";
                break;
        }
        return сolumn;
    }
    
}
