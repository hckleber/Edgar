/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Suporte;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import static javax.management.Query.lt;

/**
 *
 * @author Milene
 */
public class NetworkInfo {
    
    private static Random rand = new Random();
private static char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

    private static String getMacAddress() throws SocketException, UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String loopBack = ip.substring(0, 3);
        if (loopBack.equals("127")) {
            ip = getFirstIP();
        }
        InetAddress localHost = InetAddress.getByName(ip);
        NetworkInterface netInter = NetworkInterface.getByInetAddress(localHost);
        byte[] macAddressBytes = netInter.getHardwareAddress();
        String macAddress = String.format("%1$02x-%2$02x-%3$02x-%4$02x-%5$02x-%6$02x",
                macAddressBytes[0], macAddressBytes[1],
                macAddressBytes[2], macAddressBytes[3],
                macAddressBytes[4], macAddressBytes[5]).toUpperCase();
        return macAddress;
    }

    static private String getFirstIP() {
        java.util.Enumeration<java.net.NetworkInterface> ifaces = null;
        try {
            ifaces = java.net.NetworkInterface.getNetworkInterfaces();
        } catch (java.net.SocketException e) {
        }
        for (; ifaces.hasMoreElements();) {
            java.util.Enumeration<java.net.InetAddress> addrs = ifaces.nextElement().getInetAddresses();
            for (; addrs.hasMoreElements();) {
                java.net.InetAddress addr = addrs.nextElement();
                if (!addr.isLoopbackAddress() && !(addr instanceof java.net.Inet6Address)) {
                    return addr.getHostName();
                }
            }
        }
        return "127.0.0.1";
    }

public static String criarHash() throws SocketException, UnknownHostException{
    String hash;
    String[] macAdress = getMacAddress().split("-");
    int [] mac = {0,0,0,0,0,0};
    for(int i=0; i<macAdress.length;i++){
        mac[i] = 255 - Integer.parseInt(macAdress[i], 16);
    }
    
    //hash = String.format("%s-%s-%s-%s-%s-%s-%s", charAleatorio(6), charAleatorio(6), charAleatorio(6), charAleatorio(6), charAleatorio(6), charAleatorio(6), charAleatorio(6));
    hash = charAleatorio(6);
    hash += "-" + Integer.toHexString(mac[0]) + charAleatorio(5);
    hash += "-" + charAleatorio(1) + Integer.toHexString(mac[1]) + charAleatorio(4);
    hash += "-" + charAleatorio(2) + Integer.toHexString(mac[2]) + charAleatorio(3);
    hash += "-" + charAleatorio(3) + Integer.toHexString(mac[3]) + charAleatorio(2);
    hash += "-" + charAleatorio(4) + Integer.toHexString(mac[4]) + charAleatorio(1);
    hash += "-" + charAleatorio(5) + Integer.toHexString(mac[5]);
    hash += "-" + charAleatorio(6);
    
    return hash;
}

public static boolean verificarSerial(String [] serial) throws SocketException, UnknownHostException{
    boolean retorno = false;

    try{
    int [] xxx = {48, 17, 167, 39, 22, 116};
    String[] mac = getMacAddress().split("-");
    
    if(Integer.valueOf(serial[0]) - xxx[0] - xxx[5] == Integer.parseInt(mac[0], 16) + Integer.parseInt(mac[5], 16) &&
            Integer.valueOf(serial[1]) - xxx[1] - xxx[4] == Integer.parseInt(mac[1], 16) + Integer.parseInt(mac[4], 16) &&
            Integer.valueOf(serial[2]) - xxx[2] - xxx[3] == Integer.parseInt(mac[2], 16) + Integer.parseInt(mac[3], 16)){
        retorno = true;
    }
}catch (java.lang.NumberFormatException ex){
    retorno = false;
}
    return retorno;
}


private static String charAleatorio (int nCaracteres) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < nCaracteres; i++) {
        int ch = rand.nextInt (letras.length);
        sb.append (letras [ch]);
    }    
    return sb.toString();    
}
    
    /*
     * Main
 */
//public final static void main(String[] args) {
//        try {
//            System.out.println("Network infos");
//            System.out.println("  Operating System: " + System.getProperty("os.name"));
//            System.out.println("  IP/Localhost: " + InetAddress.getLocalHost().getHostAddress());
//            System.out.println("  MAC Address: " + getMacAddress());
//            System.out.println("  HASH: " + criarHash());
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }
}
