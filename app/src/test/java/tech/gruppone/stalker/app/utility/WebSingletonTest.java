package tech.gruppone.stalker.app.utility;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.method;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.stub;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WebSingleton.class, HeadersAdders.class})
public class WebSingletonTest {

  @Rule
  InstantTaskExecutorRule taskExecutorRule = new InstantTaskExecutorRule();

  @Test
  public void getInstance_notNull() {
    // Arrange
    final RequestQueue queue = mock(RequestQueue.class);

    stub(method(WebSingleton.class, "getRequestQueue")).toReturn(queue);

    final WebSingleton sut = WebSingleton.getInstance();

    // Act

    // Assert
    assertNotNull(sut);
  }

  @Test
  public void getInstance_sameInstance() {
    // Arrange
    final RequestQueue queue = mock(RequestQueue.class);

    PowerMockito.stub(PowerMockito.method(WebSingleton.class, "getRequestQueue")).toReturn(queue);

    final WebSingleton firstInstance = WebSingleton.getInstance();
    final WebSingleton secondInstance = WebSingleton.getInstance();

    // Act

    // Assert
    assertSame(firstInstance, secondInstance);
  }

  @Test
  public void addToRequestQueue() {
    // Arrange
    final RequestQueue queue = mock(RequestQueue.class);
    stub(method(WebSingleton.class, "getRequestQueue")).toReturn(queue);

    final WebSingleton sut = WebSingleton.getInstance();
    final JsonObjectRequest req = mock(JsonObjectRequest.class);

    // Act
    sut.addToRequestQueue(req);

    // Assert
    verify(queue, times(1)).add(req);
  }

 /* @Test
  public void getRequestQueue() throws Exception {
    //Arrange
    final
    whenNew(WebSingleton.class).withNoArguments().thenReturn()
    final RequestQueue queue = mock(RequestQueue.class);
    stub(method(Volley.class, "newRequestQueue")).toReturn(queue);

    final WebSingleton sut = WebSingleton.getInstance();

    //Act
    final RequestQueue result = sut.getRequestQueue();

    //Assert
    assertEquals(result, queue);
  }*/

/*  @Test(expected=JSONException.class)
  public void locationUpdateInside() throws Exception {
    //Arrange
    final RequestQueue queue = mock(RequestQueue.class);
    final JsonObjectRequest request = mock(JsonObjectRequest.class);
    final JSONObject jsonObject = mock(JSONObject.class);

    stub(method(WebSingleton.class, "getRequestQueue")).toReturn(queue);
    stub(method(HeadersAdders.class, "buildObjReqWithHeaders")).toReturn(request);
    //stub(method(JSONObject.class, "put")).toReturn(jsonObject);

    when(jsonObject.put(anyString(), any(Object.class))).thenReturn(jsonObject);
    whenNew(JSONObject.class).withAnyArguments().thenReturn(jsonObject);

    final WebSingleton sut = WebSingleton.getInstance();
    final List<Integer> intList = new ArrayList<>();
    intList.add(1);

    //Act
    sut.locationUpdateInside(1, intList);

    //Assert
    verify(queue, times(1)).add(request);
  }*/

}
