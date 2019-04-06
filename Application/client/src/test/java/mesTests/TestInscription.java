package mesTests;

import actions.Environnement;
import actions.Inscription;
import com.opensymphony.xwork2.ActionProxy;
import exceptions.identification.PseudoDejaPrisException;
import facade.IFacade;
import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YohanBoichut on 30/01/2017.
 */
public class TestInscription extends StrutsJUnit4TestCase{

    private IFacade mockModel;

    @Before
    public void before() {
        this.mockModel = EasyMock.createMock(IFacade.class);
    }

    @Test
    public void testInscriptionOK() throws Exception {
        request.addParameter("pseudoInscription","bob");
        request.addParameter("mdpInscription","bob");
        Map<String,Object> varApp = new HashedMap();
        Map<String,Object> varSession = new HashedMap();

        varApp.put(Environnement.MAFACADE,mockModel);
        ActionProxy proxy = getActionProxy("/inscription");
        proxy.getInvocation().getInvocationContext().setApplication(varApp);
        proxy.getInvocation().getInvocationContext().setSession(varSession);

        EasyMock.expect(mockModel.inscription("bob", "bob")).andReturn(816);
        EasyMock.replay(mockModel);
        String result = proxy.execute();
        EasyMock.verify(mockModel);

        Assert.assertEquals("soucis au niveau de la clé","success",result);
        Assert.assertTrue("variable de session manquante",varSession.containsKey(Environnement.MONID));
        Assert.assertEquals("l'id doit être celui reoturnée par le modèle", 816, varSession.get(Environnement.MONID));
    }

    @Test
    public void testInscriptionPseudoDejaPris() throws Exception {
        request.addParameter("pseudoInscription","bob");
        request.addParameter("mdpInscription","bob");
        Map<String,Object> varApp = new HashedMap();
        Map<String,Object> varSession = new HashedMap();

        varApp.put(Environnement.MAFACADE,mockModel);
        ActionProxy proxy = getActionProxy("/inscription");
        proxy.getInvocation().getInvocationContext().setApplication(varApp);
        proxy.getInvocation().getInvocationContext().setSession(varSession);

        EasyMock.expect(mockModel.inscription("bob", "bob")).andThrow(new PseudoDejaPrisException());
        EasyMock.replay(mockModel);
        String result = proxy.execute();
        EasyMock.verify(mockModel);

        Inscription action = (Inscription) proxy.getAction();

        Assert.assertEquals("soucis au niveau de la clé","input", result);
        Assert.assertEquals("Il n'y a qu'un champ qui a des erreurs",1, action.getFieldErrors().size());
        Assert.assertTrue("Il doit y avoir un message d'erreur pour le champs \"pseudoInscription\"", action.getFieldErrors().containsKey("pseudoInscription"));
        Assert.assertEquals("Il doit y avoir un message d'erreur pour le champs \"pseudoInscription\"", 1, action.getFieldErrors().get("pseudoInscription").size());
        Assert.assertEquals("Le message d'erreur doit être le bon",action.getText("errors.pseudo.alreadyExist"), action.getFieldErrors().get("pseudoInscription").get(0));

    }




}

