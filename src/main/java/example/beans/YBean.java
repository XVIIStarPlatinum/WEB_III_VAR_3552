package example.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Data
@NoArgsConstructor
public class YBean implements Serializable{
    private Double y = 0.0;

    public void validateYBeanValue(Object o){
        if (o == null){
            FacesMessage message = new FacesMessage("Y should be between -5 and 3.");
            throw new ValidatorException(message);
        }
    }
}
