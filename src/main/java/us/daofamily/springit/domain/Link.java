package us.daofamily.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.ocpsoft.prettytime.PrettyTime;
import us.daofamily.springit.service.BeanUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public  class Link extends Auditable{

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @NotEmpty(message = "Please provide a titlle")
    private String title;

    @NonNull
    @NotEmpty(message = "Please enter a URL")
    @URL(message = "Please enter a valid URL")
    private String url;



    //comments
    @OneToMany(mappedBy = "link")
    private List<Comment> comments = new ArrayList<Comment>();

    public String getDomainName() throws URISyntaxException {
        URI uri = new URI(this.url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getCreationDate()));
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}
