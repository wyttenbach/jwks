package org.wyttenbach.dale.jwks;

import java.io.FileReader;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

public class JWKSCreator {

	private static final String PRIVATE_KEY_PEM = "privateKey.pem";
	private static final String PUBLIC_KEY_PEM = "publicKey.pem";

	private static Object getPEMObject(String path) throws IOException {
        FileReader reader = new FileReader(path);
        try (PEMParser pemParser = new PEMParser(reader)) {
			Object pemObject = pemParser.readObject();
			return pemObject;
		}
	}
   
	public static void main(String[] args) throws Exception {

        // Read the PEM files
        Object privatePemObject = getPEMObject(PRIVATE_KEY_PEM);
        Object publicPemObject = getPEMObject(PUBLIC_KEY_PEM);
     
        // Get the RSA keys
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        RSAPrivateKey privateKey = (RSAPrivateKey) converter.getPrivateKey((org.bouncycastle.asn1.pkcs.PrivateKeyInfo) privatePemObject);
        RSAPublicKey publicKey = (RSAPublicKey) converter.getPublicKey((SubjectPublicKeyInfo) publicPemObject);

        // Generate the JWK
        RSAKey jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID("myKeyId")
                .build();

        // Create a JWKS containing the JWK
        JWKSet jwkSet = new JWKSet(jwk);

        // Print the JWKS
        System.out.println(jwkSet.toJSONObject());
    }
}