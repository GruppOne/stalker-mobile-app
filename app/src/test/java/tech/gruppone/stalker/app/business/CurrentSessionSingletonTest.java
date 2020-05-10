package tech.gruppone.stalker.app.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.stub;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest({CurrentSessionSingleton.class, Organization.class, LiveData.class})
public class CurrentSessionSingletonTest {

  @Rule
  InstantTaskExecutorRule taskExecutorRule = new InstantTaskExecutorRule();

  @Test
  public void setOrganizationList() {
    //Arrange
    final Organization organization = mock(Organization.class);
    final List<Organization> organizationList = new ArrayList<>();
    organizationList.add(organization);
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();

    //Act
    sut.setOrganizationList(organizationList);

    //Assert
    assertNotEquals(sut.getOrganizations(), organizationList);
  }

  @Test
  public void zeroOrganizations_true() {
    //Arrange
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();
    final List<Organization> organizationList = new ArrayList<>();

    //Act
    sut.setOrganizationList(organizationList);

    //Assert
    assertTrue(sut.zeroOrganizations());
  }

  @Test
  public void zeroOrganizations_false() {
    //Arrange
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();
    final Organization organization = mock(Organization.class);
    final List<Organization> organizationList = new ArrayList<>();
    organizationList.add(organization);

    //Act
    sut.setOrganizationList(organizationList);

    //Assert
    assertFalse(sut.zeroOrganizations());
  }

  @Test
  public void getInsidePlaces_EmptyList() {
    //Arrange
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();
    final Point point = new Point(0,0);

    stub(method(CurrentSessionSingleton.class, "zeroOrganizations")).toReturn(true);

    //Act
    List<Integer> result = sut.getInsidePlaces(point);

    //Assert
    assertTrue(result.isEmpty());
  }

  @Test
  public void getInsidePlaces_NotEmptyList() {
    //Arrange
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();
    final Point point = new Point(0,0);
    final Organization organization = mock(Organization.class);
    final List<Organization> organizationList = new ArrayList<>();
    organizationList.add(organization);
    final List<Integer> intList = new ArrayList<>();
    intList.add(1);

    stub(method(CurrentSessionSingleton.class, "zeroOrganizations")).toReturn(false);
    stub(method(LiveData.class, "getValue")).toReturn(organizationList);
    Mockito.when(organization.getInsidePlaces(point)).thenReturn(intList);

    //Act
    List<Integer> result = sut.getInsidePlaces(point);

    //Assert
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
  }

  @Test
  public void getInstance_notNull() {
    // Arrange
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();

    // Act

    // Assert
    assertNotNull(sut);
  }

  @Test
  public void getInstance_sameInstance() {
    // Arrange
    final CurrentSessionSingleton firstInstance = CurrentSessionSingleton.getInstance();
    final CurrentSessionSingleton secondInstance = CurrentSessionSingleton.getInstance();

    // Act

    // Assert
    assertEquals(firstInstance, secondInstance);
  }
}
