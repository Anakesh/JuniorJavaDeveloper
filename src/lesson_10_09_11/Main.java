package lesson_10_09_11;

public class Main {
    public static void main(String[] args) {
        String a = "1112";
        int b = Integer.parseInt(a);
        int d = Integer.valueOf(a);
        System.out.println(b);
        System.out.println(d);
        int c = 3;
        int k = d;

        System.out.println(Integer.sum(c,b));
        System.out.println(Integer.max(c,b));
        System.out.println(Integer.TYPE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println("\n\n");


        String strBool = "false";
        int intFalse = 0;
        boolean bool1;
        boolean bool2;

        bool1 = Boolean.getBoolean(strBool);
        bool2 = Boolean.parseBoolean(strBool);

        System.out.println(bool1);
        System.out.println(bool2);
        System.out.println(Boolean.hashCode(bool1));
        System.out.println("\n\n");

        char cc = 'd';
        System.out.println(Character.isDigit(cc));
        System.out.println(Character.isLetterOrDigit(cc));
        System.out.println(Character.toTitleCase(cc)+'\n');

        short sho =256;
        System.out.println(Short.reverseBytes(sho)+"\n\n\n");

        double doub = 30;
        double doub1 = Double.NaN;

        System.out.println(Double.isNaN(doub));
        System.out.println(Double.isNaN(doub1));
        System.out.println(Double.isNaN(Double.POSITIVE_INFINITY));
        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(Integer.MAX_VALUE);
        System.out.println((Integer.MAX_VALUE+1));



    }
}
