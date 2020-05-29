package tech.gruppone.stalker.app.viewmodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SignupViewModelTest {

  @Test
  public void invalidEmail() {
    SignupViewModel sut = new SignupViewModel();
    assertTrue(sut.invalidEmail("email"));
    assertFalse(sut.invalidEmail("gruppone.swe@gmail.com"));
  }

  @Test
  public void invalidPassword() {
    SignupViewModel sut = new SignupViewModel();
    assertTrue(sut.invalidPassword("s"));
    assertTrue(sut.invalidPassword("sssssssssssssssssssssssssssssssss"));
    assertTrue(sut.invalidPassword("SSSSSSSSSS"));
    assertTrue(sut.invalidPassword("ssssssssss"));
    assertTrue(sut.invalidPassword("sSssssssss"));
    assertFalse(sut.invalidPassword("sSssssss1"));
  }
}
