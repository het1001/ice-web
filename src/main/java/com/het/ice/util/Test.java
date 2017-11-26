package com.het.ice.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.het.ice.dao.model.CommodityDO;
import com.het.ice.enums.SalesmanTypeEnum;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2017/1/14.
 */
class Test {

    static class TestIn {
        public String getString() {
            return "123";
        }
    }

    public static void saveMinPhoto(String srcURL, String deskURL, double comBase,
                                    double scale) throws Exception {
        File srcFile = new java.io.File(srcURL);
        Image src = ImageIO.read(srcFile);
        int srcHeight = src.getHeight(null);
        int srcWidth = src.getWidth(null);
        int deskHeight = 0;// 缩略图高
        int deskWidth = 0;// 缩略图宽
        double srcScale = (double) srcHeight / srcWidth;
        /**缩略图宽高算法*/
        if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
            if (srcScale >= scale || 1 / srcScale > scale) {
                if (srcScale >= scale) {
                    deskHeight = (int) comBase;
                    deskWidth = srcWidth * deskHeight / srcHeight;
                } else {
                    deskWidth = (int) comBase;
                    deskHeight = srcHeight * deskWidth / srcWidth;
                }
            } else {
                if ((double) srcHeight > comBase) {
                    deskHeight = (int) comBase;
                    deskWidth = srcWidth * deskHeight / srcHeight;
                } else {
                    deskWidth = (int) comBase;
                    deskHeight = srcHeight * deskWidth / srcWidth;
                }
            }
        } else {
            deskHeight = srcHeight;
            deskWidth = srcWidth;
        }
        BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
        tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
        FileOutputStream deskImage = new FileOutputStream(deskURL); //输出到文件流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
        encoder.encode(tag); //近JPEG编码
        deskImage.close();
    }

    public static void main(String[] args) throws Exception {
        /*Random random = new Random(new Date().getTime());
        System.out.print(random.nextInt(1));*/
        /*Map<String, Object> env = new HashMap<>();
        env.put("total", 8.2);

        Double result = (Double) AviatorEvaluator.execute("total+2*(total/5)", env);
        System.out.println(JSONObject.fromObject(env).toString());*/

        /*Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 23);
        calendar1.set(Calendar.MINUTE, 59);
        calendar1.set(Calendar.SECOND, 59);
        Date start = calendar1.getTime();
        System.out.println(start);*/

//        CommodityDO commodityDO = new CommodityDO();

//        commodityDO.setPricePi(2.5d);
/*

        InvokeUtil.get("pricePi", commodityDO);

        PropertyDescriptor descriptor = new PropertyDescriptor("pricePi", commodityDO.getClass());
        Method method = descriptor.getReadMethod();
        Object object = method.invoke(commodityDO);
*/
//        InvokeUtil.set("pricePi", commodityDO, 2.6d);

//        System.out.println(CommonUtil.isWinOS());

    double a= 0.03;
    int b = 15;
        System.out.println(DoubleUtil.multiply(a, b));
    }
}
