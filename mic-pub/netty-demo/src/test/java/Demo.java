/**
 * @author HXS
 * @copyright
 * @since 2019-08-19
 */
public class Demo {
    final byte[] a ;

    public Demo(byte[] a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Demo x = new Demo(new byte[4]);
        x.a[0] = 9;
    }
}
