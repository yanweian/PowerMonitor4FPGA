import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> usbs=FpgaMonitor.getUsbBlaster();
        System.out.println(usbs);
        FpgaMonitor.executeCommand(usbs.get(0));
    }
}
