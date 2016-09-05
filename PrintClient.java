import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.io.OutputConnection;
import javax.microedition.io.StreamConnection;

class PrintClient
{
  private String printsize;
  private String serverConnectionString;
  private DiscoveryAgent discoveryAgent;
  private RemoteDevice[] remoteDevices;
  public UUID RFCOMM_UUID = new UUID(3L);
  private final byte[] RJUSTIFY = { 27, 97,  2};
  private final byte[] CJUSTIFY = { 27, 97,  1};
  private final byte[] LJUSTIFY = { 27, 97,  0};
  private final byte[] PR = { 27, 64 };
  private final byte[] CPI33 = { 29, 33, 33 };
  private final byte[] CPI00 = { 29, 33, 0 };
  private final byte[] CPI01 = { 29, 33, 1 };
  private final byte[] CPI02 = { 29, 33, 2 };
  private final byte[] CPI10 = { 29, 33, 16 };
  private final byte[] CPI20 = { 29, 33, 32 };
  private final byte[] FTB = { 27, 69, 1 };
  private final byte[] FTS = { 27, 33, 16 };
  private final byte[] FTB1 = { 27, 69, 0 };
  private final byte[] CRLF = { 13, 10 };
  private final byte[] H_TAB = { 27, 68, 8, 8, 0 };
  private final byte[] PO = { 18 };
  private final byte[] POFF = { 18 };
  private final byte[] TO = { 17 };
  private final byte[] END = { 10 };

  PrintClient(String server, String printsize)
  {
    this.serverConnectionString = server;
    this.printsize = printsize;
  }

  public void setServer(String server) {
    this.serverConnectionString = server;
  }

  public void setPrintSize(String printsize) {
    this.printsize = printsize;
  }

  public String getPrintSize() {
    return this.printsize;
  }

  public boolean printReceipt(String text)
  {
    OutputStream os = null;
    StreamConnection con = null;
    boolean result = false;
    try {
      con = (StreamConnection)Connector.open(this.serverConnectionString);
      os = con.openOutputStream();
      os.write(this.PR);
      os.write(this.CJUSTIFY);
      os.write(this.CPI33);
      os.write(text.getBytes());
      os.write(this.CRLF);
      os.flush();
      Thread.sleep(1000L);
      result = true
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      System.out.println("Failed to print data");
      System.out.println("IOException: " + ex.getMessage());
    }
    finally
    {
      os.close();
      con.close();
    }
    return result;
  }
}
