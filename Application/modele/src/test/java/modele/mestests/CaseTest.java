package modele.mestests;

import modele.Case;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CaseTest {

    Case c;

    @Before
    public void preparation(){
        c = new Case(1, 1);
    }

    @Test
    public void testConstructeur(){
        Assert.assertEquals(true, c.isEau());
        Assert.assertEquals("./img/eau.png", c.getImgPath());
    }
}
