package DB;

public interface SQL {
    public boolean Connect(String username, String password);
    public void insert(String sql);
    public void deleteContent();
    public void getCSV();
}
