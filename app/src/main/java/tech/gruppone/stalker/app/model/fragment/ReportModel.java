package tech.gruppone.stalker.app.model.fragment;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.OrganizationHistory;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.WebSingleton;

public class ReportModel {


    public void getUsersHistory(int id) {
      WebSingleton.getInstance()
          .getUserHistory(
            id,
            jsonObject -> {
              try {
                Map<Integer, OrganizationHistory> organizationHistoryMap = new TreeMap<>();
                JSONArray jsonOrganizationsHistory = jsonObject.getJSONArray("history");
                for (int i=0; i<jsonOrganizationsHistory.length(); i++){
                  JSONObject jsonHistoryObject = jsonOrganizationsHistory.getJSONObject(i);
                  int organizationId = jsonHistoryObject.getInt("organizationId");
                  OrganizationHistory organizationJson = new OrganizationHistory(jsonHistoryObject.getJSONObject("historyPerOrganization"));
                  organizationHistoryMap.put(organizationId, organizationJson);
               }
                CurrentSessionSingleton.getInstance().setUserHistory(organizationHistoryMap);
              } catch (JSONException e) {
                e.printStackTrace();
              }
            },
            error -> {
              String str = "{\n"
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
                + "            \"timestamp\": 9102,\n"
                + "\n"
                + "            \"placeId\": 3,\n"
                + "\n"
                + "            \"inside\": false\n"
                + "\n"
                + "          },\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": -3723,\n"
                + "\n"
                + "            \"placeId\": 4,\n"
                + "\n"
                + "            \"inside\": false\n"
                + "\n"
                + "          },\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": 1135,\n"
                + "\n"
                + "            \"placeId\": 5,\n"
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
                + "            \"timestamp\": -65926,\n"
                + "\n"
                + "            \"placeId\": 1,\n"
                + "\n"
                + "            \"inside\": true\n"
                + "\n"
                + "          },\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": 50493,\n"
                + "\n"
                + "            \"placeId\": 2,\n"
                + "\n"
                + "            \"inside\": true\n"
                + "\n"
                + "          },\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": -215127,\n"
                + "\n"
                + "            \"placeId\": 3,\n"
                + "\n"
                + "            \"inside\": false\n"
                + "\n"
                + "          },\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": 7785,\n"
                + "\n"
                + "            \"placeId\": 4,\n"
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
                + "      \"organizationId\": 3,\n"
                + "\n"
                + "      \"historyPerOrganization\": {\n"
                + "\n"
                + "        \"userId\": 1,\n"
                + "\n"
                + "        \"history\": [\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": 7331499,\n"
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
                + "      \"organizationId\": 4,\n"
                + "\n"
                + "      \"historyPerOrganization\": {\n"
                + "\n"
                + "        \"userId\": 1,\n"
                + "\n"
                + "        \"history\": [\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": -59015,\n"
                + "\n"
                + "            \"placeId\": 1,\n"
                + "\n"
                + "            \"inside\": true\n"
                + "\n"
                + "          },\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": -73928,\n"
                + "\n"
                + "            \"placeId\": 2,\n"
                + "\n"
                + "            \"inside\": false\n"
                + "\n"
                + "          },\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": -7966,\n"
                + "\n"
                + "            \"placeId\": 3,\n"
                + "\n"
                + "            \"inside\": false\n"
                + "\n"
                + "          },\n"
                + "\n"
                + "          {\n"
                + "\n"
                + "            \"timestamp\": -6441,\n"
                + "\n"
                + "            \"placeId\": 4,\n"
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
                Map<Integer, OrganizationHistory> organizationHistoryMap = new TreeMap<>();
                JSONArray jsonOrganizationsHistory = jsonObject.getJSONArray("history");
                for (int i=0; i<jsonOrganizationsHistory.length(); i++){
                  JSONObject jsonHistoryObject = jsonOrganizationsHistory.getJSONObject(i);
                  int organizationId = jsonHistoryObject.getInt("organizationId");
                  OrganizationHistory organizationJson = new OrganizationHistory(jsonHistoryObject.getJSONObject("historyPerOrganization"));
                  organizationHistoryMap.put(organizationId, organizationJson);;
                }
                CurrentSessionSingleton.getInstance().setUserHistory(organizationHistoryMap);
              } catch (JSONException e) {
                e.printStackTrace();
              }
            });
    }


  @NonNull
  public LiveData< Map<Integer, OrganizationHistory>> getOrgsLiveData() {
    return CurrentSessionSingleton.getInstance().getUserHistory();
  }


}
