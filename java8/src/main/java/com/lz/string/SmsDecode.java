package com.lz.string;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Author: Leowy Zhuang
 * @Date: 2020/11/26 15:03
 * @Description: TODO
 */
public class SmsDecode {

    public static void main(String[] args) throws UnsupportedEncodingException {


        byte[] t = new byte[]{65, 70, 81, 65, 97, 65, 66, 112, 65, 72, 77, 65, 73, 65, 66, 112, 65, 72, 77, 65, 73, 65, 66, 104, 65, 67, 65, 65, 90, 65, 66, 108, 65, 72, 81, 65, 90, 81, 66, 106, 65, 72, 81, 65, 73, 65, 66, 84, 65, 69, 48, 65, 76, 65, 65, 103, 65, 71, 107, 65, 100, 65, 65, 110, 65, 72, 77, 65, 73, 65, 66, 122, 65, 72, 85, 65, 99, 65, 66, 119, 65, 71, 56, 65, 99, 119, 66, 108, 65, 71, 81, 65, 73, 65, 66, 48, 65, 71, 56, 65, 73, 65, 66, 105, 65, 71, 85, 65, 73, 65, 66, 112, 65, 71, 52, 65, 100, 103, 66, 112, 65, 72, 77, 65, 97, 81, 66, 104, 65, 71, 73, 65, 98, 65, 66, 108};

        System.out.println(new String(t, StandardCharsets.US_ASCII));

        String test = "This is a detect SM, it's supposed to be invisiable";
        byte[] bytes = test.getBytes();
//        byte[] bytes = test.getBytes(StandardCharsets.UTF_8);

        System.out.println(Arrays.toString(bytes));
//        System.out.println(new String(bytes));

//        byte[] t1 = new byte[]{82, 7, 82, 7, 82, 7, 82, 7, 0, 96, 82, 7, 82, 7, 81, -60, 81, -60, 82, 7, 82, 7};
//        byte[] t1 = new byte[]{109, 75, -117, -43, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115, 0, 115};
//        byte[] t1 = new byte[]{0, 84, 0, 104, 0, 105, 0, 115, 0, 32, 0, 105, 0, 115, 0, 32, 0, 97, 0, 32, 0, 100, 0, 101, 0, 116, 0, 101, 0, 99, 0, 116, 0, 32, 0, 83, 0, 77, 0, 44, 0, 32, 0, 105, 0, 116, 0, 39, 0, 115, 0, 32, 0, 115, 0, 117, 0, 112, 0, 112, 0, 111, 0, 115, 0, 101, 0, 100, 0, 32, 0, 116, 0, 111, 0, 32, 0, 98, 0, 101, 0, 32, 0, 105, 0, 110, 0, 118, 0, 105, 0, 115, 0, 105, 0, 97, 0, 98, 0, 108, 0, 101};
//        byte[] t1 = new byte[]{-78, -30, -54, -44, -41, -42, -73, -5, -79, -32, -62, -21, -93, -70, 49, 50, 51, 52, 97, 98, 99, 100, -93, -65, -93, -84, -95, -93, -93, -69, -95, -66, -95, -65, -93, -5, -93, -3, 63, 44, 46, 59, 91, 93, 32, 123, 125};
//        byte[] t1 = new byte[]{63, 63, 63, 63, 63, 63, 63, 49, 50, 51, 52, 97, 98, 99, 100, 63, 63, 63, 63, 63, 63, 63, 63, 63, 44, 46, 59, 91, 93, 32, 123, 125};
        byte[] t1 = new byte[]{0, 84, 0, 104, 0, 105, 0, 115, 0, 32, 0, 105, 0, 115, 0, 32, 0, 97, 0, 32, 0, 100, 0, 101, 0, 116, 0, 101, 0, 99, 0, 116, 0, 32, 0, 83, 0, 77, 0, 44, 0, 32, 0, 105, 0, 116, 0, 39, 0, 115, 0, 32, 0, 115, 0, 117, 0, 112, 0, 112, 0, 111, 0, 115, 0, 101, 0, 100, 0, 32, 0, 116, 0, 111, 0, 32, 0, 98, 0, 101, 0, 32, 0, 105, 0, 110, 0, 118, 0, 105, 0, 115, 0, 105, 0, 97, 0, 98, 0, 108, 0, 101};


        System.out.println(new String(t1,"UnicodeBigUnmarked"));

//        String smsText = getSmsText(t1, (byte) 8);
        String smsText = getSmsText(t1, (byte) 8);
        System.out.println(smsText);


    }

    public static String getSmsText(byte[] byteText, byte dataCoding) {
        //stlen取短信内容的实际长度（不含短信头）
        int stlen = byteText.length;
        String cont = "";
        try {
            if ((dataCoding & (0x0C0)) == 0 || (dataCoding & 0x0F0) == 0x0F0) {
                //00XXXXXX                           11110000
                byte alphabetInd = (byte) (dataCoding & 0x0C);
                //00001100
                switch (alphabetInd) {// 0 4 8 C
                    case 0x8: {
                        byte[] b = new byte[stlen];
                        System.arraycopy(byteText, 0, b, 0, stlen);
                        cont = new String(b, "unicodebigunmarked");
                        break;
                    }
                    case 0x4: {
                        byte[] b = new byte[stlen];
                        System.arraycopy(byteText, 0, b, 0, stlen);
                        cont = new String(b, StandardCharsets.UTF_8);
                        break;
                    }
                    case 0x0: {
                        //new!
                        byte[] b = new byte[stlen];
                        System.arraycopy(byteText, 0, b, 0, stlen);
                        cont = gsmDecodeText(b);
                        break;
                    }
                    case 0xC: {//0x0c
                        byte[] b = new byte[stlen];
                        System.arraycopy(byteText, 0, b, 0, stlen);
                        cont = new String(b, "gb18030");
                        break;
                    }
                    default: {
                        byte[] b = new byte[stlen];
                        System.arraycopy(byteText, 0, b, 0, stlen);
//                        cont = CommonUtil.bytestohexstr(b);
                        break;
                    }
                }

            } else {
                //不支持的解码格式
                byte[] b = new byte[stlen];
                System.arraycopy(byteText, 0, b, 0, stlen);
//                cont = CommonUtil.bytestohexstr(b);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cont;
    }

    public static String gsmDecodeText(byte[] pDst) {
        byte[] b = new byte[pDst.length];
        int n = 0;

        for (int i = 0; i < pDst.length; i++) {
            if (pDst[i] != 0X1B) {
                b[n] = (byte) (getSmsChar((char) pDst[i]) & 0xFF);
                n++;
            } else {
                if (i < (pDst.length - 1)) {
                    int tr = ((pDst[i] & 0xFF) << 8) | pDst[i + 1];
                    char c = (char) tr;
                    b[n] = (byte) (getSmsChar(c) & 0xFF);
                    n++;
                    i++;
                } else {
                    b[n] = (byte) (getSmsChar((char) pDst[i]) & 0xFF);
                    n++;

                }
            }
        }
        if (b.length != n) {
            byte[] bb = new byte[n];
            System.arraycopy(b, 0, bb, 0, n);
            b = bb;
        }
        return new String(b);
    }

    public static char getSmsChar(char ch) {
        char c = ' ';
        switch (ch) {
            case 0x00:
                c = 64;
                break;
            case 0x01:
                c = 163;
                break;
            case 0x02:
                c = 36;
                break;
            case 0x03:
                c = 165;
                break;
            case 0x04:
                c = 232;
                break;
            case 0x05:
                c = 233;
                break;
            case 0x06:
                c = 249;
                break;
            case 0x07:
                c = 236;
                break;
            case 0x08:
                c = 242;
                break;
            case 0x09:
                c = 199;
                break;
            case 0x0B:
                c = 216;
                break;
            case 0x0C:
                c = 248;
                break;
            case 0x0E:
                c = 197;
                break;
            case 0x0F:
                c = 229;
                break;
            case 0x10:
                c = 0x394;
                // c = 'Δ';
                break;
            case 0x11:
                c = 95;
                break;
            case 0x12:
                // c = 'Φ';
                c = 0x3A6;
                break;
            case 0x13:
                c = 0x393;
                // c = 'Γ';
                break;
            case 0x14:
                c = 0x39B;
                // c = 'Λ';
                break;
            case 0x15:
                c = 0x3A9;
                break;
            case 0x16:
                // c = 'Π';
                c = 0x3A0;
                break;
            case 0x17:
                // c = 'Ψ';
                c = 0x3A8;
                break;
            case 0x18:
                // c = 'Σ';
                c = 0x3A3;
                break;
            case 0x19:
                // c = 'Θ';
                c = 0x398;
                break;
            case 0x1A:
                // c = 'Ξ';
                c = 0x39E;
                break;
            case 0x1B0A:
                c = 12;
                break;
            case 0x1B14:
                c = 94;
                break;
            case 0x1B28:
                c = 123;
                break;
            case 0x1B29:
                c = 125;
                break;
            case 0x1B2F:
                c = 92;
                break;
            case 0x1B3C:
                c = 91;
                break;
            case 0x1B3D:
                c = 126;
                break;
            case 0x1B3E:
                c = 93;
                break;
            case 0x1B40:
                c = 124;
                break;
            case 0x1B65:
                c = 164;// (ISO-8859-15);
                break;
            case 0x1C:
                c = 198;
                break;
            case 0x1D:
                c = 230;
                break;
            case 0x1E:
                c = 223;
                break;
            case 0x1F:
                c = 201;
                break;
            case 0x24:
                c = 164;
                break;
            case 0x40:
                c = 161;
                break;
            case 0x5B:
                c = 196;
                break;
            case 0x5C:
                c = 214;
                break;
            case 0x5D:
                c = 209;
                break;
            case 0x5E:
                c = 220;
                break;
            case 0x5F:
                c = 167;
                break;
            case 0x60:
                c = 191;
                break;
            case 0x7B:
                c = 228;
                break;
            case 0x7C:
                c = 246;
                break;
            case 0x7D:
                c = 241;
                break;
            case 0x7E:
                c = 252;
                break;
            case 0x7F:
                c = 224;
                break;
            default:
                c = ch;
                break;
        }
        return c;
    }


}