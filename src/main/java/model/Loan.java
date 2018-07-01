package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Domain object represent one loan on Zonky market
 *
 * Note: Object does not contains all attributes from real JSON response,
 *       because application show only short enum values for loan.
 *
 * Created by Jiří Cága
 */
@JsonIgnoreProperties
public class Loan implements Serializable {

    private Long id;
    private String name;
    private String story;
    private BigDecimal amount;
    private String rating;

    private Date publishDate;
    private Date deadLineDate;

    private String userId;
    private String nickName;

    // Constructor
    public Loan() {
    }



    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(Date deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id) &&
                Objects.equals(name, loan.name) &&
                Objects.equals(story, loan.story) &&
                Objects.equals(amount, loan.amount) &&
                Objects.equals(rating, loan.rating) &&
                Objects.equals(publishDate, loan.publishDate) &&
                Objects.equals(deadLineDate, loan.deadLineDate) &&
                Objects.equals(userId, loan.userId) &&
                Objects.equals(nickName, loan.nickName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, story, amount, rating, publishDate, deadLineDate, userId, nickName);
    }

    @Override
    public String toString() {
        return "Loan with id " + id + "\n{" +
                "\n\tname='" + name + '\'' +
                ", \n\tstory='" + story + '\'' +
                ", \n\tamount=" + amount +
                ", \n\trating='" + rating + '\'' +
                ", \n\tpublishDate=" + publishDate +
                ", \n\tdeadLineDate=" + deadLineDate +
                ", \n\tuserId='" + userId + '\'' +
                ", \n\tnickName='" + nickName + '\'' +
                "\n}\n";
    }
}
