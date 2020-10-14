package jvm;

public class GCTest {

    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[309000*1024];
        allocation2 = new byte[9000*1024];
    }
}
