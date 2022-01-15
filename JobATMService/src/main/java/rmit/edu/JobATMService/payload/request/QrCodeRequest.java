package rmit.edu.JobATMService.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class QrCodeRequest {
    @NotBlank
    private String urlLink;
}
