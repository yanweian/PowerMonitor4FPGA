import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: fpga_monitor
 * @description: 相关processBuilder命令
 * @author: yanweian
 * @create: 2018-07-31 15:08
 **/
public class FpgaMonitor {
    final static String parentDirectory = "/home/test/Desktop/fpga_monitor";
//    static ProcessBuilder builder;

    //获取usb-blaster列表
    public static ArrayList<String> getUsbBlaster() {
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
                if (line.contains(")") && line.contains("USB")) {
                    result.add(line.substring(3));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //获取日志文件夹
    public static String getRootDirectory(String usb) {
        String path = parentDirectory + "/" + usb;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        return path;
    }

    //执行
    public static ProcessBuilder executeCommand(String usb) {
        List<String> command = new ArrayList<>();
        command.add("nios2-terminal");
        command.add("--cable=" + usb);
        ProcessBuilder builder = new ProcessBuilder(command);
        //切换命令文件夹
        builder.directory(new File(parentDirectory));
        //重定向错误流
        builder.redirectErrorStream(true);
        //开始系统进程
        try {
            Process p = builder.start();
            // 读取进程标准输出流
            InputStream in = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                Monitor monitor = new Monitor();
                System.out.println(line);
                if (line.contains("startMonitor")) {
                    monitor = new Monitor();
                    monitor.setUsb(usb);
                    monitor.setTime(new Date().getTime());
                } else if (line.contains("endMonitor")) {
                    System.out.println(monitor.toString());
                    if (DBUtil.insert(monitor) > 0) {
                        System.out.println("插入成功");
                    }
                    monitor = new Monitor();
                } else if (line.contains("core_power")) {
                    monitor.setCore_power(Integer.parseInt(line.split(" ")[2]));
                } else if (line.contains("ddr_power")) {
                    monitor.setDdr_power(Integer.parseInt(line.split(" ")[2]));
                } else if (line.contains("board_power")) {
                    monitor.setBoard_power(Integer.parseInt(line.split(" ")[2]));
                } else if (line.contains("core_temperature")) {
                    monitor.setCore_tem(Integer.parseInt(line.split(" ")[2]));
                } else if (line.contains("board_temperature")) {
                    monitor.setBoard_tem(Integer.parseInt(line.split(" ")[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder;
    }
}
