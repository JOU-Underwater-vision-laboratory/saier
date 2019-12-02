package com.hhit.common.utils;

import com.hhit.common.domain.FileDO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author ray
 */
@Component
public class FileUtil {

	@Value("${hhit.uploadPath}")
	static String uploadPath;

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {

		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
    public static String renameToVersion(String fileName, String version,String webName) {

        fileName = webName+"_"+version+"."+fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileName;
    }
	public static String renameToVersion(String fileName, String name) {
		fileName = fileName.substring(0,fileName.lastIndexOf("."))+"_"+name+"."+fileName.substring(fileName.lastIndexOf(".") + 1);
		return fileName;
	}

	public static String renameToDate(String fileName) {
		fileName = fileName.substring(0,fileName.lastIndexOf("."))+DateUtils.format(new Date())+"."+fileName.substring(fileName.lastIndexOf(".")+1);
		return fileName;
	}
	public static String renameToUUID(String fileName) {
		return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	public static String  cutImage(MultipartFile file, String avatar_data) throws Exception {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		//获取图片后缀
		String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
		String[] str = avatar_data.split(",");
		//获取截取的x坐标
		int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
		//获取截取的y坐标
		int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
		//获取截取的高度
		int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
		//获取截取的宽度
		int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
		//获取旋转的角度
		int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
		try {
			BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
			BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			boolean flag = ImageIO.write(rotateImage, prefix, out);
			//转换后存入数据库
			byte[] b = out.toByteArray();
			FileUtil.uploadFile(b, uploadPath, fileName);
		} catch (Exception e) {
			throw new Exception("图片裁剪错误！！");
		}
		return fileName;
	}

	public static String  cutImage2(MultipartFile file, String avatar_data,String fileName) throws Exception {

		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		//获取图片后缀
		String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
		String[] str = avatar_data.split(",");
		//获取截取的x坐标
		int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
		//获取截取的y坐标
		int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
		//获取截取的高度
		int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
		//获取截取的宽度
		int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
		//获取旋转的角度
		int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
		try {
			BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
			assert cutImage != null;
			BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			boolean flag = ImageIO.write(rotateImage, prefix, out);
			//转换后存入数据库
			byte[] b = out.toByteArray();
			FileUtil.uploadFile(b, uploadPath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("图片裁剪错误！！");
		}
		return fileName;
	}
}
