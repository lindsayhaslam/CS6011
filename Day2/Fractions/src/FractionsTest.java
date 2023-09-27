//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FractionsTest {
    @Test
    public void runAllTests(){
    }

    @Test
    public void testDefaultConstructor(){
        Fractions test1 = new Fractions(3,4);
        Assertions.assertEquals(3, test1.numerator);
        Assertions.assertEquals(4, test1.denominator);

        //Test for two negative numbers
        Fractions test2 = new Fractions(-6,-7);
        Assertions.assertEquals(6, test2.numerator);
        Assertions.assertEquals(7, test2.denominator);

        //Test for negative denominator
        Fractions test3 = new Fractions(6,-7);
        Assertions.assertEquals(-6, test3.numerator);
        Assertions.assertEquals(7, test3.denominator);

    }

    @Test
    public void testPlus(){
        Fractions f1 = new Fractions(1, 2);
        Fractions f2 = new Fractions(1, 3);
        Fractions f3 = f1.Plus(f2);

        Assertions.assertEquals(f3.toString(), "5/6");
    }

    @Test
    public void testMinus() {
        Fractions f1 = new Fractions(1, 4);
        Fractions f2 = new Fractions(1, 5);
        Fractions f3 = f1.Minus(f2);

        Assertions.assertEquals(f3.toString(), "1/20");
    }

    @Test
    public void testTimes() {
        Fractions f1 = new Fractions(1, 2);
        Fractions f2 = new Fractions(1, 3);
        Fractions f3 = f1.Times(f2);

        Assertions.assertEquals(f3.toString(), "1/6");
    }

    @Test
    public void testDividedBy(){
        Fractions f1 = new Fractions(3, 6);
        Fractions f2 = new Fractions(3, 4);
        Fractions f3 = f1.DividedBy(f2);

        Assertions.assertEquals(f3.toString(), "2/3");
    }

    @Test
    public void testReciprocal(){
        Fractions f1 = new Fractions(2, 3);
        Fractions f2 = f1.Reciprocal();
        Assertions.assertEquals(f2.toString(), "3/2");

    }

    @Test
    public void testToDouble(){
        Fractions f1 = new Fractions(3, 4);
        Assertions.assertEquals(f1.toDouble(), 0.75);
    }

}