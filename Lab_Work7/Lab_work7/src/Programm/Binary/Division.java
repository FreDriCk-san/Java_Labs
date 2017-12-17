package Programm.Binary;

import Programm.IExpression;
import Programm.Tools.CacheCalculation;

public class Division extends BinaryExpression implements IExpression{

    public Division(Object left, Object right) {
        super(left, right);

        this.cache = new CacheCalculation(() -> {

            if (this.getRight().calculate() != 0) {
                return this.getLeft().calculate() / this.getRight().calculate();
            }

            return Double.NaN;
        });


    }

}
