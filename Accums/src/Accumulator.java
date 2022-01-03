import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Accumulator {

    public static BigInteger genMod (){
        Random rnd = new Random();
        BigInteger p = BigInteger.probablePrime(1024, rnd);
        BigInteger q = BigInteger.probablePrime(1024, rnd);
        q.multiply(p);
        return q;
    }

    public static BigInteger getProduct(ArrayList<BigInteger> list){
        BigInteger product = new BigInteger("1");
        for (BigInteger i : list){
            product = product.multiply(i);
        }
        return product;
    }

    public static BigInteger getAccumulator (ArrayList<BigInteger> list, BigInteger mod, BigInteger generator){
        return generator.modPow(getProduct(list),mod);
    }

    public static ArrayList<BigInteger> getHPKList (ArrayList<String> list){
        ArrayList<BigInteger> newList = new ArrayList<>();
        for (String i : list){
            newList.add(new BigInteger(i, 16));
        }
        return  newList;
    }

    public static BigInteger getWitness(BigInteger generator, BigInteger product, BigInteger HPKi, BigInteger mod){
        product = product.divide(HPKi);
        return generator.modPow(product,mod);
    }

    public static boolean checkWitness(BigInteger witness, BigInteger HPKi, BigInteger mod, BigInteger accumulator){
        BigInteger i = witness.modPow(HPKi, mod);
        if (i.equals(accumulator))
            return true;
        else
            return false;
    }

    public static void addElementToAccumulator(BigInteger accumulator, BigInteger newValue, BigInteger mod){
        accumulator = accumulator.modPow(newValue, mod);
    }

}
