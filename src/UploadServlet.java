
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 文件上传
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_PATH = "/Users/liuyanzhao/Desktop/ArcSofthotel-prj1/WebContent/uploads";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8"); // 设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
        factory.setSizeThreshold(1024 * 1024);

        // 高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            String filename = "";
            List<FileItem> list = upload.parseRequest(request);
            // 获取上传的文件
            for (FileItem item : list) {
                // 获取文件名
                filename = System.currentTimeMillis() + ".png";
                File file = new File(UPLOAD_PATH, filename);
                file.createNewFile();
                // 真正写到磁盘上
                item.write(file);
            }

            PrintWriter writer = response.getWriter();

            writer.print(filename);

            writer.close();

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

 