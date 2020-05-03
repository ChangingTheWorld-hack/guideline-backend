
package virushack.changing.guideline.backend.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;


/**
 * Node
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "nodes",
    "edges"
})
@Data
@Entity
public class Guideline {
    @Id
    @JsonIgnore
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nodes")
    @OneToMany(mappedBy = "guideline", cascade = CascadeType.ALL)
    private List<Node> nodes = new ArrayList<>();

    @JsonProperty("edges")
    @OneToMany(mappedBy = "guideline", cascade = CascadeType.ALL)
    private List<Edge> edges = new ArrayList<>();

    @JsonIgnore
    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="additional_properties", joinColumns=@JoinColumn(name="guideline"))
    private Map<String, String> additionalProperties = new HashMap<String, String>();

}