package pp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jose on 8/01/17.
 */
public class Mochila
{
    ArrayList<BigInteger> s,b;
    BigInteger q, r;
    int inputSize = 640;

    void setClave(int i)
    {
        s = new ArrayList<>();
        BigInteger sum = new BigInteger("0");
        Random ran = new Random();
        BigInteger x = new BigInteger(ran.nextInt(2) + 1 + "");
        for (int j = 0; j < i; j++)
        {
            s.add(x);
            sum = sum.add(x);
            x = new BigInteger(ran.nextInt(2) + 1 + "").add(sum);
        }
        q = x;
        System.out.println(q);
        System.out.println(s);
        do
        {
            r = q.subtract(new BigInteger(ran.nextInt(1000) + ""));
        }
        while ((r.compareTo(new BigInteger("0")) > 0) && (q.gcd(r).intValue() != 1));
        System.out.println(r);
        b=new ArrayList<>();
        for (int j = 0; j < i; j++)
        {
            b.add(s.get(j).multiply(r).mod(q));
        }
        System.out.println(b);
    }

    String cifrar(byte []m)
    {
        setClave(m.length*8);
        BigInteger mc=new BigInteger("0");
        byte mask=0x01;
        System.out.println(mask);
        inputSize = m.length * 8;
        for (int i = 0; i <m.length ; i++)
        {
            for (int j = 0; j <8; j++)
            {
                BigInteger v=b.get(i*8+7-j);
                if((m[i]&(mask<<j))!=0)
                    mc=mc.add(v);
            }
            mask=0x01;
        }
        System.out.println(mc);
        return mc+"";
    }
    String decifrar(String m)
    {
        BigInteger mc=new BigInteger(m);
        BigInteger v=mc.mod(q).multiply(r.modInverse(q)).mod(q);
        System.out.println(v);
        int[] bitMask = new int[inputSize];
        for (int i = inputSize-1; v.compareTo(new BigInteger("0"))!=0 ; i--)
        {
            BigInteger keyValue = s.get(i);
            if(v.compareTo(keyValue)>=0)
            {
                v=v.subtract(keyValue);
                bitMask[i]=1;
            }
        }
        byte[] decrypted = new byte[bitMask.length / 8];
        byte mask = 0x01;

        for (int i = 0; i < bitMask.length; i++)
        {
            if (bitMask[i] == 1)
            {
                decrypted[i / 8] = (byte) (decrypted[i / 8] | (mask << (7 - i % 8)));
            }
            mask = 0x01;
        }

        return new String(decrypted);
    }
}
