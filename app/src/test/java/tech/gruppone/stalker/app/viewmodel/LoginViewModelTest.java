package tech.gruppone.stalker.app.viewmodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoginViewModelTest {

  @Test
  public void validateEmail() {
    LoginViewModel sut = new LoginViewModel();
    assertTrue(sut.invalidEmail("email"));
    assertFalse(sut.invalidEmail("gruppone.swe@gmail.com"));
  }
}
