package modele.mestests;

import modele.ia.Noob;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NoobTest {

    Noob monGrosNoob;

    @Before
    public void setApplication(){
        monGrosNoob = new Noob();
    }

    @Test
    public void testNoob(){
        Assert.assertEquals("Le pseudo n'est pas correct","Bot_n00b", monGrosNoob.getPseudo());
        Assert.assertEquals("Le mdp n'est pas correct", "", monGrosNoob.getMdp());
    }

}
