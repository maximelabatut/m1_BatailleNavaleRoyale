package actions;

import com.opensymphony.xwork2.ActionSupport;
import facade.Facade;
import facade.IFacade;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * The type Environnement.
 */
public class Environnement extends ActionSupport implements ApplicationAware, SessionAware {

    public static String MAFACADE = "facade";
    public static String MONID = "monId";

    // Variables
    private IFacade facade;
    private Map<String, Object> variablesSession;

    @Override
    public void setApplication(Map<String, Object> map) {

        // Récupération de la facade
        facade = (IFacade) map.get(MAFACADE);

        // Test si la facade est vierge
        if (facade == null) {
            // Instanciation d'une nouvelle facade
            facade = new Facade();

            // Intégration de la nouvelle facade
            map.put(MAFACADE, facade);
        }
    }


    // Initialisation des variables de sessions
    @Override
    public void setSession(Map<String, Object> map) {
        this.variablesSession = map;
    }

    /**
     * Gets facade.
     *
     * @return the facade
     */
    public IFacade getFacade() {
        return facade;
    }

    /**
     * Gets variables session.
     *
     * @return the variables session
     */
    public Map<String, Object> getVariablesSession() {
        return variablesSession;
    }

    public int getIdentifiant(){return (Integer)this.variablesSession.get(MONID);}
}
