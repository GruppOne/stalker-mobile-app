package tech.gruppone.stalker.app.utility;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.powermock.api.mockito.PowerMockito.method;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.stub;

import com.android.volley.RequestQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WebSingleton.class})
public class WebSingletonTest {

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

    // Act

    // Assert
  }

  @Test
  public void userEntered() {
    // Arrange

    // Act

    // Assert
  }

  @Test
  public void userExited() {
    // Arrange

    // Act

    // Assert
  }

  @Test
  public void getOrganizationList() {
    // Arrange

    // Act

    // Assert
  }
}
