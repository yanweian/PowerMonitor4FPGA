import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> usbs = FpgaMonitor.getUsbBlaster();
        System.out.println(usbs);
        for(int i=0;i<usbs.size();i++){
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FpgaMonitor.executeCommand(usbs.get(finalI));
                }
            }).start();
        }
    }
}
