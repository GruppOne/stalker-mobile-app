package tech.gruppone.stalker.app.view;

import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

  @Rule
  public ActivityTestRule<SignUpActivity> activityRule =
      new ActivityTestRule<>(SignUpActivity.class, true, true);

  @Test
  public void onPause() {
    assertTrue(true);
  }
}
