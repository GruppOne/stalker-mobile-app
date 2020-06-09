package tech.gruppone.stalker.app.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.business.LdapCredentials;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.Place;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.excpetions.OrganizationNotFoundException;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class OrganizationModel {

  @NonNull
  public LiveData<Organization> getOrganization(int organizationId) {
    try {
      return CurrentSessionSingleton.getInstance().getOrganization(organizationId);
    } catch (OrganizationNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public void loadPlaces(int organizationId) {
    WebSingleton.getInstance()
        .getPlaces(
            organizationId,
            response -> {
              try {
                JSONArray jsonPlaces = response.getJSONArray("places");
                List<Place> places = new ArrayList<>();

                for (int i = 0; i < jsonPlaces.length(); ++i) {
                  places.add(new Place(jsonPlaces.getJSONObject(i)));
                }

                CurrentSessionSingleton.getInstance().updatePlaces(organizationId, places);
              } catch (JSONException | OrganizationNotFoundException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }

  public void connect(int organizationId, @Nullable LdapCredentials ldapCredentials) {
    WebSingleton.getInstance()
        .connect(
            Objects.requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue())
                .getId(),
            organizationId,
            ldapCredentials,
            jsonObject -> {
              try {
                CurrentSessionSingleton.getInstance()
                    .setConnectedOrganization(organizationId, true);
              } catch (OrganizationNotFoundException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }

  public void disconnect(int organizationId) {
    WebSingleton.getInstance()
        .disconnect(
            Objects.requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue())
                .getId(),
            organizationId,
            jsonObject -> {
              try {
                CurrentSessionSingleton.getInstance()
                    .setConnectedOrganization(organizationId, false);
              } catch (OrganizationNotFoundException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }
}
