package mesTests;

import facade.IFacade;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by YohanBoichut on 30/01/2017.
 */
public class TestDeconnexion extends StrutsJUnit4TestCase{

    private IFacade mockModel;

    @Before
    public void before() {
        this.mockModel = EasyMock.createMock(IFacade.class);
    }

    @Test
    public void testDeconnexionOK() throws Exception {

    }

    @Test
    public void testDeconnexionsKO() throws Exception {

    }
}

