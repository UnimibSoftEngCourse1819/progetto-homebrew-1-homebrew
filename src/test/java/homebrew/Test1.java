package homebrew;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import static org.junit.Assert.*;



import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import org.junit.Test;

public class Test1 {

	@Test
	public void test() {
		String input = "Hello world !";
	    SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
	    byte[] digest = digestSHA3.digest(input.getBytes());

	    System.out.println("SHA3-512 = " + Hex.toHexString(digest));
	}
	
    private static final boolean FOR_ENCRYPTION = true;

    public static void main(String[] args) {

        byte[] keyData = new byte[16];
        System.out.println(keyData);
        CipherParameters key = new KeyParameter(keyData);
        System.out.println(key);

        byte[] ivData = new byte[16];
        CipherParameters keyAndIV = new ParametersWithIV(key, ivData);
        System.out.println(ivData);
        System.out.println(keyAndIV);

        // absorbs the key
        BlockCipher blockCipher = new AESEngine();
        // absorbs the IV (missing in the code of the question)
        BlockCipher cbcBlockCipher = new CBCBlockCipher(blockCipher);
        // simply passes on the key and IV
        PaddedBufferedBlockCipher bufferedBlockCipher =
                new PaddedBufferedBlockCipher(cbcBlockCipher, new ZeroBytePadding());
        // initialization should be on the last wrapper class
        bufferedBlockCipher.init(FOR_ENCRYPTION, keyAndIV);

        // just to test that I didn't make any mistakes
        System.out.println(bufferedBlockCipher.getUpdateOutputSize(100));
    }

}
