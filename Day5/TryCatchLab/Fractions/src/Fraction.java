public class Fraction {

public long numerator, denominator;
    //Default constructor

    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    //Constructor that sets the value
    public Fraction(long num, long den){
        numerator = num;
        denominator = den;
        if (denominator == 0){
            throw new IllegalArgumentException("Divide by zero error.");
        }
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

    public Fraction Plus(Fraction rhs) {
        long resultNum, resultDen;
        if (denominator == rhs.denominator) {
            resultDen = denominator;
            resultNum = numerator + rhs.numerator;
        } else {
            resultDen = denominator * rhs.denominator;
            resultNum = numerator * (rhs.denominator) + rhs.numerator * (denominator);
        }
        return new Fraction(resultNum, resultDen);
    }

    public Fraction Minus(Fraction rhs){
        long resultNum, resultDen;
        if (denominator == rhs.denominator) {
            resultDen = denominator;
            resultNum = numerator - rhs.numerator;
        } else {
            resultDen = denominator * rhs.denominator;
            resultNum = numerator * (rhs.denominator) - rhs.numerator * (denominator);
        }
        return new Fraction(resultNum, resultDen);
    }
    public Fraction Times(Fraction rhs){
        long resultNum, resultDen;
        resultNum = numerator * rhs.numerator;
        resultDen = denominator * rhs.denominator;
        return new Fraction(resultNum, resultDen);
    }

    public Fraction DividedBy(Fraction rhs)
    {
        long resultNum, resultDen;
        resultNum = numerator * rhs.denominator;
        resultDen = denominator * rhs.numerator;
        if (resultDen == 0){
            throw new IllegalArgumentException("Denominator of 0 error.");
        }
        return new Fraction(resultNum, resultDen);
    }

    public Fraction Reciprocal ()
    {
        long newNum, newDen;
        newNum = denominator;
        newDen = numerator;
        if (newDen == 0){
            throw new IllegalArgumentException("Denominator of 0 error.");
        }
        return new Fraction(newNum, newDen);
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
      numerator = numerator/_gcd;
      denominator = denominator/_gcd;
   }
}
