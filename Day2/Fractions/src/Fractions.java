public class Fractions {

public long numerator, denominator;
    //Default constructor

    public Fractions() {
        numerator = 0;
        denominator = 1;
    }

    //Constructor that sets the value
    public Fractions(long num, long den){
        numerator = num;
        denominator = den;
        if (numerator < 0 && denominator < 0){
            numerator *= -1;
            denominator *= -1;
        }
        else if (denominator < 0){
            numerator *= -1;
            denominator *= -1;
        }
        reduce();
    }

    public Fractions Plus(Fractions rhs) {
        long resultNum, resultDen;
        if (denominator == rhs.denominator) {
            resultDen = denominator;
            resultNum = numerator + rhs.numerator;
        } else {
            resultDen = denominator * rhs.denominator;
            resultNum = numerator * (rhs.denominator) + rhs.numerator * (denominator);
        }
        return new Fractions(resultNum, resultDen);
    }

    public Fractions Minus(Fractions rhs){
        long resultNum, resultDen;
        if (denominator == rhs.denominator) {
            resultDen = denominator;
            resultNum = numerator + rhs.numerator;
        } else {
            resultDen = denominator * rhs.denominator;
            resultNum = numerator * (rhs.denominator) - rhs.numerator * (denominator);
        }
        return new Fractions(resultNum, resultDen);
    }
    public Fractions Times(Fractions rhs){
        long resultNum, resultDen;
        resultNum = numerator * rhs.numerator;
        resultDen = denominator * rhs.denominator;
        return new Fractions(resultNum, resultDen);
    }

    public Fractions DividedBy(Fractions rhs)
    {
        long resultNum, resultDen;
        resultNum = numerator * rhs.denominator;
        resultDen = denominator * rhs.numerator;
        return new Fractions(resultNum, resultDen);
    }

    public Fractions Reciprocal ()
    {
        long newNum, newDen;
        newNum = denominator;
        newDen = numerator;
        return new Fractions(newNum, newDen);
    }

    public String toString () {
        String stringNum = Long.toString(numerator);
        String stringDen = Long.toString(denominator);
        return stringNum + "/" + stringDen;
    }

    public Double toDouble (){
        double resultDouble;
        double num = numerator;
        double den = denominator;
        return resultDouble= num/den;
    }

    private Long findGCD ()
    {
        long gcd = numerator;
        long remainder = denominator;
        while( remainder != 0 ) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        return gcd;
    }
   private void reduce (){
        long _gcd = findGCD();
      numerator = (int) (numerator/_gcd);
      denominator = (int) (denominator/_gcd);
   }
}
