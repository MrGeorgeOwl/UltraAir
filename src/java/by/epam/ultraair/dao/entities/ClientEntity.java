package by.epam.ultraair.dao.entities;

public class ClientEntity {
    private String username;
    private boolean isAdmin;

    public ClientEntity(){
        this.username = null;
        this.isAdmin = false;
    }

    public ClientEntity(String username, boolean isAdmin){
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"username\" : \"").append(username).append("\",\n");
        sb.append("\"isAdmin\" : ").append(isAdmin);
        sb.append('\n');
        return sb.toString();
    }
}
