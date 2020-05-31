package tech.gruppone.stalker.app.business;


import java.util.List;
import lombok.Data;

@Data
public class HistoryResponse {
    List<UserHistory> userHistoryList;
}
