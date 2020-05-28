package tech.gruppone.stalker.app.utility;

import static org.junit.Assert.*;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.Map;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest({})
public class HeadersAddersTest {

  @Test
  public void getHeaders() {
    // Arrange

    // Act
    Map<String, String> result = HeadersAdders.getHeaders();

    // Assert
    assertNotNull(result);
  }

  @Test
  public void buildArrReqWithHeaders() {
    // Arrange

    // Act
    JsonArrayRequest result = HeadersAdders.buildArrReqWithHeaders(1, "test", null, null, null);

    // Assert
    assertNotNull(result);
  }

  @Test
  public void buildObjReqWithHeaders() {
    // Arrange

    // Act
    JsonObjectRequest result = HeadersAdders.buildObjReqWithHeaders(1, "test", null, null, null);

    // Assert
    assertNotNull(result);
  }
}
