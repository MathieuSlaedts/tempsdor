package be.tempsdor.tempsdor.reports;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportWithDetail extends Report {
    String detail;

    @Builder(builderMethodName = "childBuilder")
    public ReportWithDetail(int status, String path, String message, String detail) {
        super(status, path, message);
        this.detail = detail;
    }
}
