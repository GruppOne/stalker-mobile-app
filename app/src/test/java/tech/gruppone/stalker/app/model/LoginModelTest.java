package tech.gruppone.stalker.app.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginModelTest {

  @Test
  public void login() {
    //Arrange
    final LoginModel sut = new LoginModel();
    final String username = "test";
    final String password = "test";

    //Act
    final boolean result = sut.login(username, password);

    //Assert
    assertTrue(result);
  }
}
