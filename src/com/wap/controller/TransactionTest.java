package com.wap.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;

import com.kxf.test.UploadImages;
import com.wap.service.OrderTranSer;

@Controller
@RequestMapping(value = "/TransactionTest")
public class TransactionTest {
	private static Logger logger = Logger.getLogger(TransactionTest.class);
	@Autowired
	private OrderTranSer ser;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody
	String update() {
		ser.addDetailMapper();
		logger.error("--------");
		return "ssdsds";
	}

	// String path = request.getSession().getServletContext()
	// .getRealPath("upload");
	@RequestMapping(value = "/upimg", method = RequestMethod.POST)
	public @ResponseBody
	String UploadPohto(String img) {
		String backmsg = "";
		logger.error("-----UploadPohto-------");
		String path = System.getProperty("evan.webapp");
		String time = String.valueOf(System.currentTimeMillis());
		path = path + "/img/" + time + ".png";
		System.out.println("===---------------" + path);
		UploadImages uploadImages = new UploadImages();
		// 上传的路径
		String path2 = "UploadFiles";// 保存的文件夹
		logger.error("-----UploadPohto-------" + img);
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] bitmapArray = decoder.decodeBuffer(img);

			for (int i = 0; i < bitmapArray.length; ++i) {
				if (bitmapArray[i] < 0) {// 调整异常数据
					bitmapArray[i] += 256;
				}
			}
			OutputStream outputStream = new FileOutputStream(new java.io.File(
					path));
			outputStream.write(bitmapArray);
			outputStream.flush();
			outputStream.close();
			backmsg = "正确";

		} catch (IOException e1) {
			backmsg = "错误";
			// e1.printStackTrace();
		}
		// String bigImg = uploadImages.upLoadImage(img, "", path2);
		// System.out.println(bigImg);
		// banner.setBigImg(bigImg);
		// try {
		// bannerService.add(banner);
		// return "success";
		// } catch (Exception e) {
		// e.printStackTrace();
		// return (e.getMessage());
		//
		// }
		return backmsg;
	}

}
