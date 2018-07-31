import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: fpga_monitor
 * @description: 相关processBuilder命令
 * @author: yanweian
 * @create: 2018-07-31 15:08
 **/
public class FpgaMonitor {
    final static String parentDirectory="/home/test/fpga_monitor";
    public static String getUsbBlaster(){
        StringBuffer result = new StringBuffer();
        List<String> commend = new ArrayList<>();
        //检查usb-balster,并获取列表
        commend.add("jtagconfig");
        InputStream in = null;
        ProcessBuilder builder = new ProcessBuilder(commend);
        //切换命令文件夹
        builder.directory(new File(parentDirectory));
        //重定向错误流
        builder.redirectErrorStream(true);
        //开始系统进程
        try {
            Process p = builder.start();
            // 读取进程标准输出流
            in = p.getInputStream();
            byte[] re = new byte[1024];
            while (in.read(re) != -1) {
                result = result.append(new String(re));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
