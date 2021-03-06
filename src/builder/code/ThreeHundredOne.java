package builder.code;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class ThreeHundredOne extends Code {
  public byte[] buildHeader(File routeFile, String responseCodeMessage, int bodyContentLength) throws IOException, ParseException {
    byte[] originalHeader = new ResponseHeader().build(routeFile, responseCodeMessage, bodyContentLength);
    byte[] location = ("Location: " + toURL(routeFile) + "\r\n").getBytes();
    return concatenate(new byte[][] {originalHeader, location});
  }

  public byte[] buildBody(File routeFile) {
    return "".getBytes();
  }

  public String responseCodeMessage() {
    return "301 Moved Permanently";
  }

  private String toURL(File routeFile) {
    return "http://" + request.get("Host") + "/" + routeFile.getName();
  }
}