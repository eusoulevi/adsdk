package org.cajuinabits.adsdk.core.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author levi
 */
public class SecurityUtils {

    /**
     * Carrega para a memória o reposit&oacute;rio de certificados (chaveiro) armazenado no arquivo especificado.
     * @param file Arquivo que cont&eacute;m o reposit&oacute;rio de certificados (chaveiro).
     * @param password senha do repositório de certificados (chaveiro).
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws KeyStoreException
     */
    public static KeyStore loadKeyStore(String file, String password) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
        return loadKeyStore(new File(file), password);
    }

    /**
     * Carrega para a memória o reposit&oacute;rio de certificados (chaveiro) armazenado no arquivo especificado.
     * @param file Arquivo que cont&eacute;m o reposit&oacute;rio de certificados (chaveiro).
     * @param password senha do reposit&oacute;rio de certificados (chaveiro).
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws KeyStoreException
     */
    public static KeyStore loadKeyStore(File file, String password) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
        if (password == null) {
            throw new KeyStoreException("Password nulo n\u00e3o \u00e9 permitido");
        }
        FileInputStream inputStream = new FileInputStream(file);
        KeyStore chaveiro = chaveiro = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] passwordArray = password.toCharArray();
        chaveiro.load(inputStream, passwordArray);
        inputStream.close();
        return chaveiro;
    }

    /**
     * Carrega certificado (DER).
     * @param certdata certificado na forma de array de bytes
     * @return X509Certificate
     */
    public static X509Certificate loadX509Certificate(byte[] certdata) {
        ByteArrayInputStream inputStream;
        CertificateFactory cf;
        X509Certificate cert;
        try {
            inputStream = new ByteArrayInputStream(certdata);
            cf = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) cf.generateCertificate(inputStream);
            inputStream.close();
            return cert;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Carrega um objeto X509Certificate de um arquivo especifico.  Se este m&eacute;todo
     * &eacute; capaz de carregar o certificado do arquivo, retorna
     * um objeto do tipo X509Certificate, caso contr&aacute;rio, retorna <code>null</code>.
     * @param file: arquivo que cont&eacute;m o certificado X.509.
     * @return um objeto <code>X509Certificate</code> contendo o certificado, ou <code>null</code>.
     */
    public static X509Certificate loadX509Certificate(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inputStream);
            inputStream.close();
            return cert;
        } catch (Exception anyException) {
            anyException.printStackTrace();
            return null;
        }
    }

    /**
     * Carrega um objeto X509Certificate de um arquivo especifico.  Se este
     * m&eacute;todo &eacute; capaz de carregar o certificado do arquivo, retorna
     * o objeto, caso contr&aacute;rio, retorna <code>null</code>.
     * @param fileName: caminho do arquivo que cont&eacute;m o certificado X.509.
     * @return um objeto <code>X509Certificate</code> contendo o certificado, ou <code>null</code>.
     */
    public static X509Certificate loadX509Certificate(String fileName) {
        return loadX509Certificate(new File(fileName));
    }

    /**
     * Salva um certificado DER codificado em um diret&oacute;rio.
     * @param certBytes Certificado na forma de array de bytes.
     * @param outputDir Diret&oacute;rio onde ser&aacute; salvo o Certificado
     * @throws Exception
     */
    public static void saveCertificateToDisk(byte[] certBytes, String outputDir) throws Exception {
        X509Certificate cert = loadX509Certificate(certBytes);
        FileOutputStream fos = null;
        SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
        String fileName = outputDir + "\\" + cert.getSubjectDN().getName() + "_" + formatador.format(new Date()) + ".cer";
        try {
            fos = new FileOutputStream(fileName);
        } catch (Exception ex) {
            throw new Exception("N\u00e3o foi poss\u00edvel abrir " + fileName + " para salvar o certificado," + " agora use " + (fileName = formatador.format(new Date()) + ".cer"));
        }
        fos.write(certBytes);
        fos.close();
    }
    
}
