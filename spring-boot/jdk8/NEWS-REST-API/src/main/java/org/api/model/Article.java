
package org.api.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "title",
    "url",
    "imageUrl",
    "newsSite",
    "summary",
    "publishedAt",
    "updatedAt",
    "featured",
    "launches",
    "events"
})
@Generated("jsonschema2pojo")
public class Article {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("newsSite")
    private String newsSite;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("featured")
    private Boolean featured;
    @JsonProperty("launches")
    private List<Launch> launches = new ArrayList<Launch>();
    @JsonProperty("events")
    private List<Object> events = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Article withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Article withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Article withUrl(String url) {
        this.url = url;
        return this;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Article withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @JsonProperty("newsSite")
    public String getNewsSite() {
        return newsSite;
    }

    @JsonProperty("newsSite")
    public void setNewsSite(String newsSite) {
        this.newsSite = newsSite;
    }

    public Article withNewsSite(String newsSite) {
        this.newsSite = newsSite;
        return this;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Article withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Article withPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Article withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @JsonProperty("featured")
    public Boolean getFeatured() {
        return featured;
    }

    @JsonProperty("featured")
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Article withFeatured(Boolean featured) {
        this.featured = featured;
        return this;
    }

    @JsonProperty("launches")
    public List<Launch> getLaunches() {
        return launches;
    }

    @JsonProperty("launches")
    public void setLaunches(List<Launch> launches) {
        this.launches = launches;
    }

    public Article withLaunches(List<Launch> launches) {
        this.launches = launches;
        return this;
    }

    @JsonProperty("events")
    public List<Object> getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(List<Object> events) {
        this.events = events;
    }

    public Article withEvents(List<Object> events) {
        this.events = events;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Article withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Article.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("imageUrl");
        sb.append('=');
        sb.append(((this.imageUrl == null)?"<null>":this.imageUrl));
        sb.append(',');
        sb.append("newsSite");
        sb.append('=');
        sb.append(((this.newsSite == null)?"<null>":this.newsSite));
        sb.append(',');
        sb.append("summary");
        sb.append('=');
        sb.append(((this.summary == null)?"<null>":this.summary));
        sb.append(',');
        sb.append("publishedAt");
        sb.append('=');
        sb.append(((this.publishedAt == null)?"<null>":this.publishedAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("featured");
        sb.append('=');
        sb.append(((this.featured == null)?"<null>":this.featured));
        sb.append(',');
        sb.append("launches");
        sb.append('=');
        sb.append(((this.launches == null)?"<null>":this.launches));
        sb.append(',');
        sb.append("events");
        sb.append('=');
        sb.append(((this.events == null)?"<null>":this.events));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.summary == null)? 0 :this.summary.hashCode()));
        result = ((result* 31)+((this.featured == null)? 0 :this.featured.hashCode()));
        result = ((result* 31)+((this.publishedAt == null)? 0 :this.publishedAt.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.imageUrl == null)? 0 :this.imageUrl.hashCode()));
        result = ((result* 31)+((this.newsSite == null)? 0 :this.newsSite.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.launches == null)? 0 :this.launches.hashCode()));
        result = ((result* 31)+((this.events == null)? 0 :this.events.hashCode()));
        result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Article) == false) {
            return false;
        }
        Article rhs = ((Article) other);
        return (((((((((((((this.summary == rhs.summary)||((this.summary!= null)&&this.summary.equals(rhs.summary)))&&((this.featured == rhs.featured)||((this.featured!= null)&&this.featured.equals(rhs.featured))))&&((this.publishedAt == rhs.publishedAt)||((this.publishedAt!= null)&&this.publishedAt.equals(rhs.publishedAt))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.imageUrl == rhs.imageUrl)||((this.imageUrl!= null)&&this.imageUrl.equals(rhs.imageUrl))))&&((this.newsSite == rhs.newsSite)||((this.newsSite!= null)&&this.newsSite.equals(rhs.newsSite))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.launches == rhs.launches)||((this.launches!= null)&&this.launches.equals(rhs.launches))))&&((this.events == rhs.events)||((this.events!= null)&&this.events.equals(rhs.events))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))));
    }

}
