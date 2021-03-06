package builder.httpMethod;

import builder.code.Code;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class Get implements HttpMethod {
  private HashMap partialContentMap;

  public Get() {
    partialContentMap = new PartialContentMapBuilder().build();
  }

  public byte[] get(File routeFile, HashMap request) throws IOException, ParseException {
    boolean answer = rangeRequestHeaderExists(request);
    Code code = (Code) partialContentMap.get(answer);
    return code.build(routeFile, request);
  }

  private boolean rangeRequestHeaderExists(HashMap request) throws IOException {
    return request.get("Range") != null;
  }
}