package by.javatr.personalfinance.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = -415338188735346077L;

    private long id;
    private String login;
    private String password;
    private boolean signInStatus;
    private int roleID;
    private boolean banStat;
    private List<Long> accountIDList;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSignInStatus() {
        return signInStatus;
    }

    public void setSignInStatus(boolean signInStatus) {
        this.signInStatus = signInStatus;
    }

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public boolean isBanStat() {
        return banStat;
    }

    public void setBanStat(boolean banStat) {
        this.banStat = banStat;
    }

    public List<Long> getAccountIDList() {
        return accountIDList;
    }

    public void setAccountIDList(List<Long> accountIDList) {
        this.accountIDList = accountIDList;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return id == user.id &&
                signInStatus == user.signInStatus &&
                roleID == user.roleID &&
                banStat == user.banStat &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(accountIDList, user.accountIDList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, signInStatus, roleID, banStat, accountIDList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", signInStatus=" + signInStatus +
                ", roleID=" + roleID +
                ", banStat=" + banStat +
                ", accountIDList=" + accountIDList +
                '}';
    }
}
