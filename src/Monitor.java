/**
 * @program: fpga_monitor
 * @description: monitor实体
 * @author: yanweian
 * @create: 2018-08-01 12:38
 **/
public class Monitor {
    private String usb;
    private long time;
    private int core_power;
    private int ddr_power;
    private int board_power;
    private int core_tem;
    private int board_tem;

    public Monitor(String usb, long time, int core_power, int ddr_power, int board_power, int core_tem, int board_tem) {
        this.usb = usb;
        this.time = time;
        this.core_power = core_power;
        this.ddr_power = ddr_power;
        this.board_power = board_power;
        this.core_tem = core_tem;
        this.board_tem = board_tem;
    }

    public Monitor(){}

    @Override
    public String toString() {
        return "Monitor{" +
                "usb='" + usb + '\'' +
                ", time=" + time +
                ", core_power=" + core_power +
                ", ddr_power=" + ddr_power +
                ", board_power=" + board_power +
                ", core_tem=" + core_tem +
                ", board_tem=" + board_tem +
                '}';
    }

    public String getUsb() {
        return usb;
    }

    public void setUsb(String usb) {
        this.usb = usb;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCore_power() {
        return core_power;
    }

    public void setCore_power(int core_power) {
        this.core_power = core_power;
    }

    public int getDdr_power() {
        return ddr_power;
    }

    public void setDdr_power(int ddr_power) {
        this.ddr_power = ddr_power;
    }

    public int getBoard_power() {
        return board_power;
    }

    public void setBoard_power(int board_power) {
        this.board_power = board_power;
    }

    public int getCore_tem() {
        return core_tem;
    }

    public void setCore_tem(int core_tem) {
        this.core_tem = core_tem;
    }

    public int getBoard_tem() {
        return board_tem;
    }

    public void setBoard_tem(int board_tem) {
        this.board_tem = board_tem;
    }
}
