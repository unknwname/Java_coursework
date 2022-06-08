package personal.TableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import personal.Objects.DB;
import personal.Objects.Part;
import personal.Objects.Filial;

public class EmployeeTableModel extends AbstractTableModel {
    
    private ArrayList<Part> data = new ArrayList();
    private ArrayList<Part> filteredData = new ArrayList();
    private ArrayList<Filial> filials = new ArrayList();
    private final DB dataBase;
    
    public EmployeeTableModel(DB dataBase) {
        this.dataBase = dataBase;
        getAll();
    }
    
    public final void getAll() {
        
        filials.clear();
        data.clear();
        
        for (Filial filial : dataBase.getFilials()) {
            filials.add(filial);
        }
        for (Part part : dataBase.getEmployee()) {
            data.add(part);
            filteredData.add(part);
        }
    }
    
    public void refreshFilials(){
        filials.clear();
        for (Filial filial : dataBase.getFilials()) {
            filials.add(filial);
        }
    }
    
    public Filial getFilial(int filial_num) {
        return filials.get(filial_num);
    }
    
    public void changeFilial(int filial_num) {
        filteredData.clear();
        if (filial_num <= -1) {
            for (Part part : data) {
                filteredData.add(part);
            }
            
        } else {
            for (Part part : data) {
                if (part.getFilial_id() == getFilial(filial_num).getFilial_id()) {
                    filteredData.add(part);
                }
            }
        }
        
        this.fireTableDataChanged();
    }
    
    public void Add(Part empl, int filial_num) {
        Part part = empl;
        int id = this.dataBase.addEmployee(part.getFilial_id(), part.getPosition_id(), part.getFio(), part.getMobile(), part.getEmail());
        part.setEmployee_id(id);
        data.add(part);
        changeFilial(filial_num);
        this.fireTableDataChanged();
        
    }
    

    public void Remove(int number){
        for (Part empl: data){
            if (empl == filteredData.get(number)){
                data.remove(empl);
                break;
            }
        }
        this.dataBase.removeEmployee(filteredData.get(number).getEmployee_id());
        filteredData.remove(number);
        this.fireTableDataChanged();
    }
    
    public void Update(int row, Part oldEmpl, Part newEmpl){
        this.dataBase.updateEmployee(newEmpl);
        filteredData.set(row, newEmpl);
        for (Part empl: data){
            if (empl == oldEmpl){
                data.remove(empl);
                data.add(newEmpl);
                break;
            }
        }
        this.fireTableDataChanged();
    }
    
    public Part[] getItems() {
        return filteredData.stream().toArray(Part[]::new);
    }
    
    public Part getItem(int i) {
        return filteredData.get(i);
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
                сolumn = "Вид работ";
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
