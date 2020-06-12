package tech.gruppone.stalker.app.model.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class ReportModel {


  public void getUsersHistory(int id) {
    WebSingleton.getInstance()
        .getUserHistory(
            id,
            jsonObject -> {
              try {
                List<UserOrganizationHistory> userOrganizationHistories = new ArrayList<>();
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
                  for (int j = 0; j < historyArray.length(); j++) {
                    JSONObject jsonUserOrganizationHistory = historyArray.getJSONObject(j);
                    /*UserOrganizationHistory userOrganizationHistory =
                        new UserOrganizationHistory(
                            jsonUserOrganizationHistory,
                            Objects.requireNonNull(organization.getValue()));
                    userOrganizationHistories.add(userOrganizationHistory);*/
                  }
                  CurrentSessionSingleton.getInstance()
                      .setUserOrganizationHistory(userOrganizationHistories);
                }
              } catch (JSONException | OrganizationNotFoundException e) {
                e.printStackTrace();
              }
            },
            error -> {
              String str =
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
                      + "            \"timestamp\": 129751040203886,\n"
                      + "\n"
                      + "            \"placeId\": 1,\n"
                      + "\n"
                      + "            \"inside\": false\n"
                      + "\n"
                      + "          },\n"
                      + "\n"
                      + "          {\n"
                      + "\n"
                      + "            \"timestamp\": 2119099905936,\n"
                      + "\n"
                      + "            \"placeId\": 2,\n"
                      + "\n"
                      + "            \"inside\": false\n"
                      + "\n"
                      + "          },\n"
                      + "\n"
                      + "          {\n"
                      + "\n"
                      + "            \"timestamp\": 5470813408029,\n"
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
                      + "            \"timestamp\": 85485162295885,\n"
                      + "\n"
                      + "            \"placeId\": 3,\n"
                      + "\n"
                      + "            \"inside\": true\n"
                      + "\n"
                      + "          },\n"
                      + "\n"
                      + "          {\n"
                      + "\n"
                      + "            \"timestamp\": 8648516422588,\n"
                      + "\n"
                      + "            \"placeId\": 3,\n"
                      + "\n"
                      + "            \"inside\": true\n"
                      + "\n"
                      + "          },\n"
                      + "\n"
                      + "          {\n"
                      + "\n"
                      + "            \"timestamp\": 8548518229588,\n"
                      + "\n"
                      + "            \"placeId\": 3,\n"
                      + "\n"
                      + "            \"inside\": false\n"
                      + "\n"
                      + "          },\n"
                      + "\n"
                      + "          {\n"
                      + "\n"
                      + "            \"timestamp\": 548510622957,\n"
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
                      + "            \"timestamp\": 854858622958,\n"
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
                      + "            \"timestamp\": 8548519229588,\n"
                      + "\n"
                      + "            \"placeId\": 3,\n"
                      + "\n"
                      + "            \"inside\": true\n"
                      + "\n"
                      + "          },\n"
                      + "\n"
                      + "          {\n"
                      + "\n"
                      + "            \"timestamp\": 804851622958,\n"
                      + "\n"
                      + "            \"placeId\": 3,\n"
                      + "\n"
                      + "            \"inside\": false\n"
                      + "\n"
                      + "          },\n"
                      + "\n"
                      + "          {\n"
                      + "\n"
                      + "            \"timestamp\": 85485160229, \n"
                      + "\n"
                      + "            \"placeId\": 3,\n"
                      + "\n"
                      + "            \"inside\": false\n"
                      + "\n"
                      + "          },\n"
                      + "\n"
                      + "          {\n"
                      + "\n"
                      + "            \"timestamp\": 8948516209,\n"
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
                      + "}";

              try {
                JSONObject jsonObject = new JSONObject(str);
                List<UserOrganizationHistory> userOrganizationHistories = new ArrayList<>();
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
                  for (int j = 0; j < historyArray.length(); j++) {
                    JSONObject jsonUserOrganizationHistory = historyArray.getJSONObject(j);
                    UserOrganizationHistory userOrganizationHistory =
                        new UserOrganizationHistory(
                            jsonUserOrganizationHistory,
                            Objects.requireNonNull(organization.getValue()));
                    userOrganizationHistories.add(userOrganizationHistory);
                  }
                  CurrentSessionSingleton.getInstance()
                      .setUserOrganizationHistory(userOrganizationHistories);
                }
              } catch (JSONException | OrganizationNotFoundException e) {
                e.printStackTrace();
              }
            });
  }

  @NonNull
  public LiveData<Map<Long, LiveData<UserOrganizationHistory>>> getOrgsHistoryLiveData() {
    return CurrentSessionSingleton.getInstance().getUserOrganizationHistory();
  }
}
