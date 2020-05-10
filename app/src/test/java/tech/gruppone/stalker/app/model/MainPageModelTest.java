package tech.gruppone.stalker.app.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.stub;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import tech.gruppone.stalker.app.business.CurrentSessionSingleton;
import tech.gruppone.stalker.app.business.Organization;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CurrentSessionSingleton.class})
public class MainPageModelTest {

/*  @Test
  public void loadOrganizations() {
    //Arrange
    final MainPageModel sut = new MainPageModel();

    //Act
    sut.loadOrganizations();

    //Assert

  }*/

  @Test
  public void getOrgsLiveData() {
    //Arrange
    final MainPageModel sut = new MainPageModel();
    final CurrentSessionSingleton currentSessionSingleton = mock(CurrentSessionSingleton.class);
    final LiveData<List<Organization>> orgData = new MutableLiveData<>();
    stub(method(CurrentSessionSingleton.class, "getInstance")).toReturn(currentSessionSingleton);
    when(currentSessionSingleton.getOrganizations()).thenReturn(orgData);

    //Act
    final LiveData<List<Organization>> result = sut.getOrgsLiveData();

    //Assert
    Assert.assertNotEquals(null, result);
  }
}
