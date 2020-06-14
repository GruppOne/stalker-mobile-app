package tech.gruppone.stalker.app.model.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.Place;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.excpetions.OrganizationNotFoundException;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class ReportModel {

  public void getUsersHistory(int id) {
    /*String str =
    "{\n"
        + "\n"
        + "  \"history\": [\n"
        + "\n"
        + "    {\n"
        + "\n"
        + "      \"organizationId\": 1,\n"
        + "\n"
        + "      \"historyPerOrganization\": {\n"
        + "\n"
        + "        \"userId\": 1,\n"
        + "\n"
        + "        \"history\": [\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2020-06-18T20:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 1,\n"
        + "\n"
        + "            \"inside\": false\n"
        + "\n"
        + "          },\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2020-06-15T20:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 2,\n"
        + "\n"
        + "            \"inside\": false\n"
        + "\n"
        + "          },\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2020-06-15T21:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 1,\n"
        + "\n"
        + "            \"inside\": false\n"
        + "\n"
        + "          }\n"
        + "\n"
        + "        ]\n"
        + "\n"
        + "      }\n"
        + "\n"
        + "    },\n"
        + "\n"
        + "    {\n"
        + "\n"
        + "      \"organizationId\": 2,\n"
        + "\n"
        + "      \"historyPerOrganization\": {\n"
        + "\n"
        + "        \"userId\": 1,\n"
        + "\n"
        + "        \"history\": [\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2019-06-15T22:40:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 3,\n"
        + "\n"
        + "            \"inside\": true\n"
        + "\n"
        + "          },\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2016-06-16T20:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 3,\n"
        + "\n"
        + "            \"inside\": true\n"
        + "\n"
        + "          },\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2020-06-19T21:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 3,\n"
        + "\n"
        + "            \"inside\": false\n"
        + "\n"
        + "          },\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2020-06-14T12:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 3,\n"
        + "\n"
        + "            \"inside\": true\n"
        + "\n"
        + "          }\n"
        + "\n"
        + "        ]\n"
        + "\n"
        + "      }\n"
        + "\n"
        + "    },\n"
        + "\n"
        + "    {\n"
        + "\n"
        + "      \"organizationId\": 2,\n"
        + "\n"
        + "      \"historyPerOrganization\": {\n"
        + "\n"
        + "        \"userId\": 1,\n"
        + "\n"
        + "        \"history\": [\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2020-06-13T21:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 3,\n"
        + "\n"
        + "            \"inside\": false\n"
        + "\n"
        + "          }\n"
        + "\n"
        + "        ]\n"
        + "\n"
        + "      }\n"
        + "\n"
        + "    },\n"
        + "\n"
        + "    {\n"
        + "\n"
        + "      \"organizationId\": 2,\n"
        + "\n"
        + "      \"historyPerOrganization\": {\n"
        + "\n"
        + "        \"userId\": 1,\n"
        + "\n"
        + "        \"history\": [\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2019-05-14T22:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 3,\n"
        + "\n"
        + "            \"inside\": true\n"
        + "\n"
        + "          },\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2020-06-17T23:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 3,\n"
        + "\n"
        + "            \"inside\": false\n"
        + "\n"
        + "          },\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2020-06-16T20:35:59.000+00:00\", \n"
        + "\n"
        + "            \"placeId\": 3,\n"
        + "\n"
        + "            \"inside\": false\n"
        + "\n"
        + "          },\n"
        + "\n"
        + "          {\n"
        + "\n"
        + "            \"timestamp\": \"2020-07-15T20:35:59.000+00:00\",\n"
        + "\n"
        + "            \"placeId\": 3,\n"
        + "\n"
        + "            \"inside\": true\n"
        + "\n"
        + "          }\n"
        + "\n"
        + "        ]\n"
        + "\n"
        + "      }\n"
        + "\n"
        + "    }\n"
        + "\n"
        + "  ]\n"
        + "\n"
        + "}";*/
    WebSingleton.getInstance()
      .getUserHistory(
        id,
        jsonObject -> {
          System.out.println(jsonObject);
          // JSONObject jsonObject = new JSONObject(str);
          List<UserOrganizationHistory> userOrganizationHistories = new ArrayList<>();
          try {
            JSONArray jsonOrganizationsHistory = jsonObject.getJSONArray("history");
            for (int i = 0; i < jsonOrganizationsHistory.length(); i++) {

              JSONObject jsonHistoryObject = jsonOrganizationsHistory.getJSONObject(i);
              int organizationId = jsonHistoryObject.getInt("organizationId");
              LiveData<Organization> organization =
                Objects.requireNonNull(
                  CurrentSessionSingleton.getInstance().getOrganization(organizationId));

              JSONObject organizationJson =
                jsonHistoryObject.getJSONObject("historyPerOrganization");

              JSONArray historyArray = organizationJson.getJSONArray("history");

              LiveData<List<Place>> placeList =
                new MutableLiveData<>(
                  Objects.requireNonNull(organization.getValue()).getPlaces());

              placeList.observeForever(
                new Observer<List<Place>>() {
                  @Override
                  public void onChanged(List<Place> dataList) {
                    if (!Objects.requireNonNull(placeList.getValue()).isEmpty()) {
                      placeList.removeObserver(this);
                      try {
                        userOrganizationHistories.addAll(
                          ReportModel.this.getUsersOrganizationHistory(
                            historyArray, organization.getValue()));
                      } catch (JSONException e) {
                        e.printStackTrace();
                      }
                    } else {
                      WebSingleton.getInstance()
                        .getPlaces(
                          organizationId,
                          jsonObject -> {
                            try {
                              JSONArray placesArray = jsonObject.getJSONArray("places");

                              List<Place> places = new ArrayList<>();

                              for (int i = 0; i < placesArray.length(); ++i) {
                                places.add(new Place(placesArray.getJSONObject(i)));
                              }
                              CurrentSessionSingleton.getInstance()
                                .updatePlaces(organizationId, places);
                            } catch (JSONException | OrganizationNotFoundException e) {
                              throw new RuntimeException(e);
                            }
                          },
                          null);
                    }
                  }
                });
            }
            CurrentSessionSingleton.getInstance()
              .setUserOrganizationHistory(userOrganizationHistories);
          } catch (JSONException | OrganizationNotFoundException e) {
            e.printStackTrace();
          }
        },
        null);
  }

  List<UserOrganizationHistory> getUsersOrganizationHistory(
    JSONArray jsonHistoryObject, Organization organization) throws JSONException {
    List<UserOrganizationHistory> userOrganizationHistories = new ArrayList<>();
    for (int j = 0; j < jsonHistoryObject.length(); j++) {
      JSONObject jsonUserOrganizationHistory = jsonHistoryObject.getJSONObject(j);
      UserOrganizationHistory userOrganizationHistory =
        new UserOrganizationHistory(jsonUserOrganizationHistory, organization);
      userOrganizationHistories.add(userOrganizationHistory);
    }
    return userOrganizationHistories;
  }

  @NonNull
  public LiveData<Map<Long, LiveData<UserOrganizationHistory>>> getOrgsHistoryLiveData() {
    return CurrentSessionSingleton.getInstance().getUserOrganizationHistory();
  }
}
