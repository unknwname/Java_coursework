package personal.Objects;
import lombok.*;


@AllArgsConstructor
@Data
public class Part {
    
    private String fio;
    private String email;
    private String mobile;
    private int position_id;
    private String position_name;
    private int filial_id;
    private int employee_id;
    
    
    public Part(int filial_id, int position_id, String position_name, String fio, String email, String mobile){
        this.filial_id = filial_id;
        this.position_id = position_id;
        this.position_name = position_name;
        this.fio = fio;
        this.email = email;
        this.mobile = mobile;
    }
}
