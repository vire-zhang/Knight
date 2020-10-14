package javabook;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.lang.Math.round;

public class FirstSample {

    private static final Logger LOGGER = Logger.getLogger("");

    enum Size {
        S, M, L, X
    };

    public static void main(String[] args) {
        System.out.println("'Hello, World!' again.");
        System.out.println(Size.S);
        System.out.println(1<<35);
        System.out.println(1<<3);
        System.out.println(1<<8);
        System.out.println(-4>>2);
        System.out.println(-4>>>2);
        System.out.println(-4>>8);
        System.out.println(-4>>>8);
        String s = "Java\u2122\uD835\uDD46";
        System.out.println(s);
        System.out.println(s.substring(4,5));
        System.out.println("\u03C0");
        System.out.println(String.join(" / ", "S", "M", "L"));
        System.out.println(s.repeat(3));
        System.out.println("\uD835\uDD46");
        int[] codePoints = s.codePoints().toArray();
        System.out.println();
        for (int i = 0; i < codePoints.length; i++) {
            System.out.println(new String(codePoints, i, 1));
            System.out.println(codePoints[i]);
        }
        System.out.println();
        int[] cp2 = new int[7];
        for (int i = 0; i < s.length(); i++) {
            cp2[i] = s.codePointAt(i);
        }
        for (int i = 0; i < cp2.length; i++) {
            System.out.println(new String(cp2, i, 1));
        }
        System.out.println();
        System.out.println(s.codePointCount(0, s.length()));

//        Scanner in = new Scanner(System.in);
//        System.out.println("Name?");
//        String name = in.nextLine();
//        System.out.println("Age?");
//        int age = in.nextInt();
//        System.out.println(name + " " + age);
//
//        Console cons = System.console();
//        String username = cons.readLine("User name: ");
//        char[] passwd = cons.readPassword("Password: ");

        double d = 10000.0 / 3.0;
        System.out.printf("%.2f\n", d);
        System.out.printf("%,8.2f\n", d);
        System.out.printf("%+,.2f\n", d);
        System.out.printf("%+,.2f\n", -d);

        try {
            Scanner fileIn = new Scanner(Path.of("src/main/resources/myfile.txt"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.info("输入文件读取失败");
        }

        try {
            PrintWriter out = new PrintWriter("outfile.txt", StandardCharsets.UTF_8);
            out.println('c');
            out.append("123");
            out.close();
        } catch (Exception e) {
            LOGGER.info("输出文件读取失败");
        }
        System.out.println('c');


        // 大数
        BigInteger a = BigInteger.valueOf(2);
        System.out.println(a);
        BigInteger realBig = new BigInteger("23364353444400000000000000000000");
        System.out.println(realBig.add(a));
        System.out.println(realBig.multiply(a));
        System.out.println(realBig.subtract(a));
        System.out.println(realBig.divide(a));
        System.out.println(realBig.mod(BigInteger.valueOf(23)));
        System.out.println(realBig.sqrt());
        BigInteger realBig2 = new BigInteger("23364353444400000076800000000000");
        System.out.println(realBig.compareTo(realBig2));


        // 数组
        int[] ints = {1,2,3,};
        System.out.println(ints.length);
        System.out.println(Arrays.toString(ints));
        int[] copy = ints;
        copy[0] = 99;
        System.out.println(Arrays.toString(ints));
        int[] copy2 = Arrays.copyOf(copy, copy.length);
        copy2[0] = 100;
        System.out.println(Arrays.toString(ints));

        // main函数参数
        if (args.length == 0 || args[0].equals("-h"))
            System.out.println("Hello,");
        else if (args[0].equals("-g"))
            System.out.println("Goodbye,");
        for (int i = 1; i < args.length; i++) {
            System.out.print(" " + args[i]);
        }
        System.out.println("!");

        // Date
        System.out.println(new Date());
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.now().plusDays(1000));
        LocalDate newYearsEve = LocalDate.of(1999, 12, 31);
        System.out.println(newYearsEve);
        LocalDate aThousandDaysLater = newYearsEve.plusDays(1000);
        System.out.println(aThousandDaysLater);


        // 静态工厂方法
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        double x = 0.1;
        System.out.println(currencyFormatter.format(x));
        System.out.println(percentFormatter.format(x));

        // 强制类型转换，四舍五入
        double n = 9.97;
        System.out.println((int) n);
        System.out.println((int) round(n));

        // 数组、数组列表
        System.out.println("\nArray:");
        int[] aa = new int[10];
        ArrayList<Integer> bb = new ArrayList<>(10);
        System.out.println(aa.length);
        System.out.println(bb.size());
        var bbNew  = bb.toArray();

        System.out.println(Integer.toString(34, 5));
        int quinary = Integer.parseInt("234", 11);
        System.out.println(quinary);
    }


}
