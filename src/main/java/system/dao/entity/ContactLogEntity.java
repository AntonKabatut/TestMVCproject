package system.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "contact_log", schema = "Contacts")
public class ContactLogEntity {
    private int id;
    private ContactEntity contactByContactId;
    private UsersEntity user;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactLogEntity that = (ContactLogEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    public ContactEntity getContactByContactId() {
        return contactByContactId;
    }

    public void setContactByContactId(ContactEntity contactByContactId) {
        this.contactByContactId = contactByContactId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
