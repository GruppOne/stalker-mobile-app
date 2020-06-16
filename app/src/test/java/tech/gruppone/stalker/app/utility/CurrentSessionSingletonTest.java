package tech.gruppone.stalker.app.utility;

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
import java.util.stream.Collectors;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.Point;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CurrentSessionSingleton.class, Organization.class, LiveData.class})
public class CurrentSessionSingletonTest {

  @Rule InstantTaskExecutorRule taskExecutorRule = new InstantTaskExecutorRule();

  @Test
  public void setOrganizationList() {
    // Arrange
    final Organization organization = mock(Organization.class);
    final List<Organization> organizationList = new ArrayList<>();
    organizationList.add(organization);
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();

    // Act
    sut.setOrganizationList(organizationList);

    // Assert
    assertNotEquals(sut.getOrganizations(), organizationList);
  }

  @Test
  public void zeroOrganizations_true() {
    // Arrange
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();
    final List<Organization> organizationList = new ArrayList<>();

    // Act
    sut.setOrganizationList(organizationList);

    // Assert
    assertTrue(sut.zeroOrganizations());
  }

  @Test
  public void zeroOrganizations_false() {
    // Arrange
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();
    final Organization organization = mock(Organization.class);
    final List<Organization> organizationList = new ArrayList<>();
    organizationList.add(organization);

    // Act
    sut.setOrganizationList(organizationList);

    // Assert
    assertFalse(sut.zeroOrganizations());
  }

  @Test
  public void getInsidePlaces_EmptyList() {
    // Arrange
    final CurrentSessionSingleton sut = CurrentSessionSingleton.getInstance();
    final Point point = Point.buildFromDegrees(0, 0);

    stub(method(CurrentSessionSingleton.class, "zeroOrganizations")).toReturn(true);

    // Act
    List<Integer> result =
        sut.getInsidePlaces(point).stream()
            .map(placeWithOrganization -> placeWithOrganization.placeId)
            .collect(Collectors.toList());

    // Assert
    assertTrue(result.isEmpty());
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
