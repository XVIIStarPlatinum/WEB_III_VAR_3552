package example.beans;

import example.service.AreaChecker;
import example.repository.DAOFactory;
import example.entity.ResultEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

@SessionScoped
@Getter
@Setter
@Data
public class ResultsControllerBean implements Serializable {
    private XBean xBean;
    private YBean yBean;
    private RBean rBean;

    private ArrayList<ResultEntity> results = new ArrayList<>();

    @PostConstruct
    public void init() {

        var resultsEntities = DAOFactory.getInstance().getResultDAO().getAllResults();
        results = new ArrayList<>(resultsEntities);
    }

    public void addResult(Integer x, Double y, Double r) {
        boolean hitResult = AreaChecker.checkHit(x, y, r);
        ResultEntity entity = ResultEntity.builder().x(x).y(y).r(r).hitResult(hitResult).build();
        results.add(entity);
        DAOFactory.getInstance().getResultDAO().addNewResult(entity);

        String script = String.format(Locale.US, "drawPointForJSF(%n, %d, %,f, %,f, true);", x, y, r, hitResult);
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script);
    }
    public void updateCanvas(double r) {
        for (ResultEntity en : results) {
            boolean result = AreaChecker.checkHit(en.getX(), en.getY(), r);
            en.setR(r);
            en.setHitResult(result);

            String script = String.format(Locale.US, "window.drawPointsForJSF(%n, %d, %f, %b, true);", en.getX(), en.getY(), r, result);
            System.out.println("Script: " + script);
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script);
        }
    }
    public void clearResults() {
        DAOFactory.getInstance().getResultDAO().clearResults();
        results.clear();
    }
}
