package tech.gruppone.stalker.app.model.fragment;

import static java.util.Objects.requireNonNull;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.Place;
import tech.gruppone.stalker.app.model.OrganizationModel;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.excpetions.OrganizationNotFoundException;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class OrganizationsModel {

  OrganizationModel organizationModel = new OrganizationModel();

  public void loadOrganizations() {
    WebSingleton.getInstance()
        .getOrganizationList(
            jsonObject -> {
              List<Organization> organizations = new ArrayList<>();

              try {
                JSONArray array = jsonObject.getJSONArray("organizations");

                for (int i = 0; i < array.length(); ++i) {
                  organizations.add(new Organization(array.getJSONObject(i)));
                }
              } catch (JSONException e) {
                throw new RuntimeException(e);
              }

              CurrentSessionSingleton.getInstance().setOrganizationList(organizations);

              CurrentSessionSingleton.getInstance()
                  .getOrganizations()
                  .observeForever(
                      new Observer<Map<Integer, LiveData<Organization>>>() {
                        @Override
                        public void onChanged(
                            Map<Integer, LiveData<Organization>> integerLiveDataMap) {
                          CurrentSessionSingleton.getInstance()
                              .getOrganizations()
                              .removeObserver(this);
                          OrganizationsModel.this.loadConnectedOrganizations();

                        }
                      });
                for(Organization org : organizations){
                            organizationModel.loadPlaces(org.getId());
                          }
            },
            null);
  }


  public void loadConnectedOrganizations() {
    WebSingleton.getInstance()
        .getConnectedOrganizations(
            requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue())
                .getId(),
            response -> {
              try {
                JSONArray orgsArray = response.getJSONArray("connectedOrganizationsIds");

                for (int i = 0; i < orgsArray.length(); ++i) {
                  int organizationId = orgsArray.getInt(i);

                  LiveData<Organization> organizationLiveData =
                      CurrentSessionSingleton.getInstance().getOrganization(organizationId);

                  organizationLiveData.observeForever(
                      new Observer<Organization>() {
                        @Override
                        public void onChanged(Organization organization) {
                          if (organization.isConnected()) {
                            organizationLiveData.removeObserver(this);
                            OrganizationsModel.this.loadConnectedPlaces(organizationId);
                          }
                        }
                      });

                  CurrentSessionSingleton.getInstance()
                      .setConnectedOrganization(organizationId, true);
                }

                CurrentSessionSingleton.getInstance().doneChanges();
              } catch (JSONException | OrganizationNotFoundException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }

  protected void loadConnectedPlaces(int organizationId) {
    WebSingleton.getInstance()
        .getPlaces(
            organizationId,
            response -> {
              try {
                JSONArray placesArray = response.getJSONArray("places");

                List<Place> places = new ArrayList<>();

                for (int i = 0; i < placesArray.length(); ++i) {
                  places.add(new Place(placesArray.getJSONObject(i)));
                }

                CurrentSessionSingleton.getInstance().updatePlaces(organizationId, places);
                CurrentSessionSingleton.getInstance().doneChanges();
              } catch (JSONException | OrganizationNotFoundException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }

  @NonNull
  public LiveData<Map<Integer, LiveData<Organization>>> getOrgsLiveData() {
    return Transformations.map(
        CurrentSessionSingleton.getInstance().getOrganizations(),
        map -> {
          Map<Integer, LiveData<Organization>> connected = new TreeMap<>();

          for (LiveData<Organization> organizationLiveData : map.values()) {
            Organization organization = requireNonNull(organizationLiveData.getValue());
            if (!organization.isConnected()) {
              connected.put(organization.getId(), organizationLiveData);
            }
          }

          return connected;
        });
  }
}
