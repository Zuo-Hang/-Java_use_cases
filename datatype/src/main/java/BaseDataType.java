/**
 * 基础数据类型与封装类型
 *
 * @Author zuo_h
 * @Date 12:04 2021/5/17
 */


public class BaseDataType {
    /**
     * 8个
     * 1、字符类型：byte，char
     * 2、基本整型：short，int，long
     * 3、浮点型：float，double
     * 4、布尔类型：boolean
     */

    /**
     * 字节 一个字节8个比特位
     */
    void byteTest() {
        byte maxValue = Byte.MAX_VALUE;
        byte minValue = Byte.MIN_VALUE;
//        String
        byte b1 = 9;
        System.out.println(b1 + "\t" + maxValue + "\t" + minValue);
    }

    void charTest() {

    }

    public static void main(String[] args) {
        new BaseDataType().byteTest();
    }
}
