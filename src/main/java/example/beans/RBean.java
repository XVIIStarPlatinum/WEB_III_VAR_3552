package example.beans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.validator.ValidatorException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.faces.context.FacesContext;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Data
@NoArgsConstructor
public class RBean implements Serializable{
    private Double r = 0.0;

    public void validateRBeanValue(FacesContext facesContext, UIComponent uiComponent, Object o){
        if (o == null){
            FacesMessage message = new FacesMessage("R should be between 1 and 3 and divisible by 0,5.");
            throw new ValidatorException(message);
        }
    }

    @PostConstruct
    public void init(){
        if(r == null || r == 0.0){
            r = 1.0;
        }
    }
}
