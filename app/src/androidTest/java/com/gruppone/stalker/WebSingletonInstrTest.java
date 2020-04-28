package com.gruppone.stalker;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import com.android.volley.RequestQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WebSingleton.class})
public class WebSingletonInstrTest {

  @Test
  public void getRequestQueue_notNull() {
    //Arrange
    WebSingleton sut = WebSingleton.getInstance();

    //Act
    RequestQueue queue = sut.getRequestQueue();

    //Assert
    assertNotNull(queue);
  }

  @Test
  public void getRequestQueue_sameQueue() {
    //Arrange
    WebSingleton sut = WebSingleton.getInstance();

    //Act
    RequestQueue queue1 = sut.getRequestQueue();
    RequestQueue queue2 = sut.getRequestQueue();

    //Assert
    assertSame(queue1, queue2);
  }

  @Test
  public void addToRequestQueue() {
    //Arrange

    //Act

    //Assert
  }
}
