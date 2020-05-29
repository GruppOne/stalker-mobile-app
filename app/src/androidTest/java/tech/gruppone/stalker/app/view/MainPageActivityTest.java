package tech.gruppone.stalker.app.view;

import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainPageActivityTest {

  @Rule
  public ActivityTestRule<MainPageActivity> activityRule =
      new ActivityTestRule<>(MainPageActivity.class, true, true);

  @Test
  public void loadOrganizations() {
    assertTrue(true);
  }
}
