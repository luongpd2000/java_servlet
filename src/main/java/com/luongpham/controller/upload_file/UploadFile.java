package com.luongpham.controller.upload_file;

import com.luongpham.model.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UploadFile", value = "/upload-file/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxRequestSize = 1024 * 1024 * 50,
        maxFileSize = 1024 * 1024 * 50)
//fileSizeThreshold : neu kich thuoc cua file upload len lon hon dinh nghia
// thi he thong tu ghi file vao truc tiep o cung khog thong qua bo dem
// maxRequestSize : kich thuoc toi da cua 1 request
// maxFileSize kích thước tối đa của 1 file được upload

public class UploadFile extends HttpServlet {

    private JsonResult jsonResult = new JsonResult();

    private static final String SAVE_DICRECTORY = "file-upload";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = "";
        //chua cac file ma minh upload len;

        List<String> list = new ArrayList<>();
        try {
            Collection<Part> partCollection = request.getParts();
            long time = new Date().getTime();

            for (Part part : partCollection) {
                String fileName = getFileName(part);
                if (fileName != null) {
                    String filePath = getFolderUpload(time).getAbsolutePath() + File.separator + fileName;
                    // trả về đường dẫn

                    System.out.println("Write" + filePath);
                    part.write(filePath);       // ghi file vào đường dẫn
                    list.add(SAVE_DICRECTORY + "/" + time + "/" + fileName);
                    //lay ra duong dan thu muc upload file
                }
            }
            rs = jsonResult.jsonSuccess(list);

        } catch (Exception e) {
            e.printStackTrace();
            rs = jsonResult.jsonFail("upload fail");
        }

        response.getWriter().write(rs);
    }


    private File getFolderUpload(long time) {
        String appPath = "C:\\Server\\apache-tomcat-9.0.37\\webapps\\" + SAVE_DICRECTORY + "\\" + time;
        //C:\Server\apache-tomcat-8.5.51\webapps\
        //C:\Server\apache-tomcat-9.0.37\webapps\
        File folderUpload = new File(appPath);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }


    private String getFileName(Part part) {
        //String fileNameRS = "";
        //thuoc tinh header cua doi tuong part tuong ung voi key content-disposition
        //thi se chua mot mot chuoi co dinh dang tuong tu

        //form-data ; name = "file" ; filename = "C:\file.zip"
        //tu chuoi nay minh se lay a ten file va phan mo rong cua file
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String fileNameRS = s.substring(s.indexOf("=") + 2, s.length() - 1);
                fileNameRS = fileNameRS.replace("\\", "/");
                int i = fileNameRS.lastIndexOf("/");
                return fileNameRS.substring(i + 1);
            }
        }
        return null;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
