import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.hna.framework.core.Listener.HNAServletContextListener;
import com.hna.framework.core.datasource.DesEncrypt;


@Controller
@RequestMapping("/upload.do")
public class uploadAction {
    @RequestMapping(params = "method=index", method = RequestMethod.GET)
    public String index() {
        return "upload/index";
    }
    @SuppressWarnings("unused")
    @RequestMapping(params = "method=upload", method = RequestMethod.POST)
    public @ResponseBody String upload(HttpServletRequest request, HttpServletResponse response) {
//        request = new MulpartRequestWrapper(request);
        
        String responseStr="";  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        //获取前台传值  
        String[] userNames = multipartRequest.getParameterValues("userName");  
        String[] contents = multipartRequest.getParameterValues("content");  
        String userName="";  
        String content="";  
        if(userNames!=null){  
            userName=userNames[0];  
        }  
        if(contents!=null){  
            content=contents[0];  
        }  
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
            //String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "\\" + "images\\";    
//            String ctxPath=request.getSession().getServletContext().getRealPath("/")+"uploads\\"; 
            String ctxPath= HNAServletContextListener.getSYS_UPLOADPATH_PATH();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
        String ymd = sdf.format(new Date());  
        ctxPath += ymd + "/";  
        //创建文件夹  
            File file = new File(ctxPath);    
            if (!file.exists()) {    
                file.mkdirs();    
            }    
            String fileName = null;
            String path=null;
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
                // 上传文件名    
                // System.out.println("key: " + entity.getKey());    
                MultipartFile mf = entity.getValue();    
                fileName = mf.getOriginalFilename();  
               //String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();      
               //SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
               // String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;  
                
                String strEnc = DesEncrypt.aircrewhealthGetEncString(fileName);// 加密字符串,返回String的密文
                String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
                String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;

                String newFileName = strEnc + "-" + uuid + (suffix!=null?suffix:"");// 构成新文件名。
                
                
                File uploadFile = new File(ctxPath + newFileName);    
                try {  
                    FileCopyUtils.copy(mf.getBytes(), uploadFile); 
                    path =ctxPath+newFileName;
                responseStr="上传成功";  
            } catch (IOException e) {  
                responseStr="上传失败";  
                e.printStackTrace();  
            }    
            }   
           
            return path;  
    }
}
