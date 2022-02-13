package poc.util;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.Random;

public class NumberUtil {
    public Double factorialDouble(int number) {
        return CombinatoricsUtils.factorialDouble(number);
    }

    public Integer randomInt(int maxNumber) {
        return new Random().nextInt(maxNumber);
    }

}
