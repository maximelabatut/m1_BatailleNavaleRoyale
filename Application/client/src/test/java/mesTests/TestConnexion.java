package mesTests;

import actions.Environnement;
import com.opensymphony.xwork2.ActionProxy;
import facade.IFacade;
import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YohanBoichut on 30/01/2017.
 */
public class TestConnexion extends StrutsJUnit4TestCase{

    private IFacade mockModel;

    @Before
    public void before() {
        this.mockModel = EasyMock.createMock(IFacade.class);
    }

    @Test
    public void testConnexionOK() throws Exception {

    }

    @Test
    public void testConnexionKO() throws Exception {

    }
}

