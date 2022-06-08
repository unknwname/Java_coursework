
package personal.TableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import personal.Objects.DB;
import personal.Objects.Position;

public class PositionTableModel extends AbstractTableModel {
    
    private ArrayList<Position> data = new ArrayList();
    private final DB dataBase;
    
    public PositionTableModel(DB dataBase){
        this.dataBase = dataBase;
        this.getAll();
    }
    
    public final void getAll(){
        for(Position pos: dataBase.getPosition()){
            data.add(pos);
        }
    }
    
    public void Add(Position pos){
        data.add(pos);
        this.fireTableDataChanged();
    }
    
    
    public void Remove(int number){
        this.dataBase.removePosition(data.get(number).getPosition_id());
        data.remove(number);
        this.fireTableDataChanged();
    }
    
    public void Update(int row, Position position){
        this.dataBase.updatePosition(position.getPosition_id(), position.getName());
        data.set(row, position);
        this.fireTableDataChanged();
    }
    
    public Position[] getItems() {
        return data.stream().toArray(Position[]::new);
    }
    
    public Position getItem(int i){
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
                сolumn = "Вид работ";
                break;
        }
        return сolumn;
    }
    
}
