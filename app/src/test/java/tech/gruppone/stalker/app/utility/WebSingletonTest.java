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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WebSingleton.class})
public class WebSingletonTest {

  @Rule InstantTaskExecutorRule taskExecutorRule = new InstantTaskExecutorRule();

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

    stub(method(WebSingleton.class, "getRequestQueue")).toReturn(queue);

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

  //  @Test
  //  public void getRequestQueue() {
  //    //Arrange
  //    PowerMockito.mockStatic(Volley.class);
  //    final RequestQueue queue = mock(RequestQueue.class);
  //    final Context context = mock(Context.class);
  //    stub(method(App.class, "getAppContext")).toReturn(context);
  //    when(Volley.newRequestQueue(context)).thenReturn(queue);
  //
  //    final WebSingleton sut = WebSingleton.getInstance();
  //
  //    //Act
  //    final RequestQueue result = sut.getRequestQueue();
  //
  //    //Assert
  //    assertEquals(result, queue);
  //  }

}
