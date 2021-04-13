package be.tempsdor.tempsdor.reports;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportWithDetails extends Report{
    List<String> details;

    @Builder(builderMethodName = "childBuilder")
    public ReportWithDetails(int status, String path, String message, List<String> details) {
        super(status, path, message);
        this.details = details;
    }
}
