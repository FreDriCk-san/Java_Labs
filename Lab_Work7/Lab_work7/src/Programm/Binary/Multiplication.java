package Programm.Binary;

import Programm.IExpression;
import Programm.Tools.CacheCalculation;
import Programm.Unary.Value;

public class Multiplication extends BinaryExpression implements IExpression {

    public Multiplication(Object left, Object... right) {

        super(left, new Value(1));

        if (1 == right.length) {
            this.setRight(new Multiplication(right[0], new Object[]{}));

        } else if (1 < right.length) {

            Multiplication root = new Multiplication(right[0], 1);
            this.setRight(root);

            for (int i = 1; i < right.length - 1; i++) {

                Multiplication next = new Multiplication(right[i], 1);
                root.setRight(next);
                root = next;
            }
        }

        this.cache = new CacheCalculation(() -> this.getLeft().calculate() * this.getRight().calculate());
    }
}
