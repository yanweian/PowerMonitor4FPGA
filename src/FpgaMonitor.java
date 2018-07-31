import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: fpga_monitor
 * @description: 相关processBuilder命令
 * @author: yanweian
 * @create: 2018-07-31 15:08
 **/
public class FpgaMonitor {
    final static String parentDirectory="/home/test/Desktop/fpga_monitor";
    public static ArrayList<String> getUsbBlaster(){
        ArrayList<String> result = new ArrayList<>();
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
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains(")")&&line.contains("USB")){
                    result.add(line.substring(3));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
