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
public class XBean implements Serializable {
    private Double x = 0.0;
    public void validateXBeanValue(Object o){
        if (o == null){
            FacesMessage message = new FacesMessage("X should be an integer and between -5 and 3.");
            throw new ValidatorException(message);
        }
    }
}
