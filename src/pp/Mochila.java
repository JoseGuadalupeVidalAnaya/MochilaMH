package pp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Mochila
{
    ArrayList<BigInteger> s, b;
    BigInteger q, r;
    int inputSize = 640;

    void setClave(int i)
    {
        s = new ArrayList<>();
        BigInteger sum = new BigInteger("0");
        Random ran = new Random();
        BigInteger x = new BigInteger(  "1");
        for (int j = 0; j < i; j++)
        {
            s.add(x);
            sum = sum.add(x);
            x = new BigInteger( "1").add(sum);
        }
        System.out.println("Mochila: "+s);
        q = x;
        System.out.println("q: "+q);
        int y=1000;
        do
        {
            r = q.subtract(new BigInteger(y + ""));
            y++;
        }
        while ((r.compareTo(new BigInteger("0")) > 0) && (q.gcd(r).intValue() != 1));
        System.out.println("r: "+r);
        b = new ArrayList<>();
        for (int j = 0; j < i; j++)
        {
            b.add(s.get(j).multiply(r).mod(q));
        }
        System.out.println("b: "+b);
    }

    String cifrar(byte[] m)
    {
        setClave(m.length * 8);
        BigInteger mc = new BigInteger("0");
        byte mask = 0x01;
        inputSize = m.length * 8;
        for (int i = 0; i < m.length; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                BigInteger v = b.get(i * 8 + 7 - j);
                if ((m[i] & (mask << j)) != 0)
                    mc = mc.add(v);
            }
            mask = 0x01;
        }
        return mc + "";
    }

    String decifrar(String m)
    {
        BigInteger mc = new BigInteger(m);
        BigInteger v = mc.mod(q).multiply(r.modInverse(q)).mod(q);
        int[] bitMask = new int[inputSize];
        for (int i = inputSize - 1; v.compareTo(new BigInteger("0")) != 0; i--)
        {
            BigInteger keyValue = s.get(i);
            if (v.compareTo(keyValue) >= 0)
            {
                v = v.subtract(keyValue);
                bitMask[i] = 1;
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
