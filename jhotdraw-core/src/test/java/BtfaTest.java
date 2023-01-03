import org.jhotdraw.draw.ArrangeLayer;
import org.jhotdraw.draw.action.BringToFrontAction;
import org.testng.annotations.Test;

public class BtfaTest {
    @Test
    public void test() {
        BringToFrontAction btfa = new BringToFrontAction(null);
        assert (btfa.ID == "edit.bringToFront");
        assert (btfa.direction == ArrangeLayer.FRONT);
    }


}
