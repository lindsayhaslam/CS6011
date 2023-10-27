//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

class FractionsTest {
    @Test
    public void runAllTests(){
        tryCatch();
        testReduce();
        testPlus();
        testMinus();
        testTimes();
        testDividedBy();
        testReciprocal();
        testToDouble();
        testCompareTo();
    }

    @Test
    void testCompareTo(){
        ArrayList<Fraction> a1=new ArrayList<Fraction>();

        a1.add(new Fraction(2, 4));
        a1.add(new Fraction (1, 4));
        a1.add(new Fraction (3, 4));
        a1.add(new Fraction (0, 4));

        Collections.sort(a1);

        for (int i=0; i<a1.size(); i++){
            System.out.println(a1.get(i));
        }
    }

    @Test
    void tryCatch(){
        Fraction f1= new Fraction (0,4);
        Fraction f2= new Fraction(1,5);
        try {
            Fraction f3 = new Fraction(4, 0);
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
        try {
            f1.Reciprocal();
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());

        }
        try {
            f2.DividedBy(f1);
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    void testReduce() {
        Fraction f1=new Fraction(12,30);
        Fraction f2=new Fraction(9,12);
        Fraction f3=new Fraction(-6,-18);
        Fraction f4=new Fraction(24, -108);

        Assertions.assertEquals(f1.toString(), "2/5");
        Assertions.assertEquals(f2.toString(), "3/4");
        Assertions.assertEquals(f3.toString(), "1/3");
        Assertions.assertEquals(f4.toString(), "-2/9");
    }


    @Test
    public void testThrowException(){
        try{
            Fraction testException = new Fraction (3, 0);
        }catch(IllegalArgumentException e){
            System.out.println("testThrowException!");
        }
    }
    @Test
    public void testDefaultConstructor(){
        Fraction test1 = new Fraction(3,4);
        Assertions.assertEquals(3, test1.numerator);
        Assertions.assertEquals(4, test1.denominator);

        //Test for two negative numbers
        Fraction test2 = new Fraction(-6,-7);
        Assertions.assertEquals(6, test2.numerator);
        Assertions.assertEquals(7, test2.denominator);

        //Test for negative denominator
        Fraction test3 = new Fraction(6,-7);
        Assertions.assertEquals(-6, test3.numerator);
        Assertions.assertEquals(7, test3.denominator);

    }

    @Test
    public void testPlus(){
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = f1.Plus(f2);

        Assertions.assertEquals(f3.toString(), "5/6");
    }

    @Test
    public void testMinus() {
        Fraction f1 = new Fraction(1, 4);
        Fraction f2 = new Fraction(1, 5);
        Fraction f3 = f1.Minus(f2);

        Assertions.assertEquals(f3.toString(), "1/20");
    }

    @Test
    public void testTimes() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = f1.Times(f2);

        Assertions.assertEquals(f3.toString(), "1/6");
    }

    @Test
    public void testDividedBy(){
        Fraction f1 = new Fraction(3, 6);
        Fraction f2 = new Fraction(3, 4);
        Fraction f3 = f1.DividedBy(f2);

        Assertions.assertEquals(f3.toString(), "2/3");
    }

    @Test
    public void testReciprocal(){
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = f1.Reciprocal();
        Assertions.assertEquals(f2.toString(), "3/2");

    }

    @Test
    public void testToDouble(){
        Fraction f1 = new Fraction(3, 4);
        Assertions.assertEquals(f1.toDouble(), 0.75);
    }

}