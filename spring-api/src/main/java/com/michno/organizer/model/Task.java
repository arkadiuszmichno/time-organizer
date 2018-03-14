package com.michno.organizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.michno.organizer.model.audit.UserDateAudit;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task extends UserDateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 3)
    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private String priority;


    @Column(name = "description")
    private String description;

    //@JsonDeserialize(using = LocalDateDeserializer.class)
   // @JsonSerialize(using = LocalDateSerializer.class)
    //@Column(name = "start_date")
   // private Instant startDate;

   // @JsonDeserialize(using = LocalDateDeserializer.class)
   // @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "reached")
    private Boolean reached = false;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "list_id")
    private TodoList list;

    public Task() {

    }

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, String description, String priority, Instant startDate, Instant endDate) {
        this.name = name;
        this.description = description;
        this.priority = priority;
       // this.startDate = startDate;
        this.endDate = endDate;
        this.reached = false;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

   // public Instant getStartDate() {
   //     return startDate;
   // }

  //  public void setStartDate(Instant startDate) {
       // this.startDate = startDate;
   // }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public boolean isReached() {
        return reached;
    }

    public void setReached(boolean reached) {
        this.reached = reached;
    }

    @JsonIgnore
    public TodoList getList() {
        return list;
    }

    public void setList(TodoList list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                ", description='" + description + '\'' +
               // ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reached=" + reached +
                '}';
    }
}
