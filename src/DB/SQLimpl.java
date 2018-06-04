package DB;
import com.opencsv.CSVWriter;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class SQLimpl implements SQL {
    Connection c = null;
    Statement statement = null;
    String sql = null;
    ArrayList<String> output = null;
    static String DB_URL = "jdbc:postgresql://localhost:5432/proyectoFinal";
    boolean debug = false;
    Writer writer;
    CSVWriter csvWriter;

    @Override
    public boolean Connect(String username, String password) {
        try {
            if (password == null)
                password = "";
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(DB_URL,
                            username, password);
            writer = Files.newBufferedWriter(Paths.get("output.csv"));
            output = new ArrayList<>();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("connection successful");
        return true;
    }
    @Override
    public void insert(String sql) {
        try {
            statement = c.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            output.add(sql);
            System.out.println("Query Successful");
        } catch (SQLException e) {
            System.err.println("ERROR: inserting data:\n" + sql);
        }
    }
    @Override
    public void deleteContent(){
        try {
            statement = c.createStatement();
            statement.executeUpdate("TRUNCATE \"Album\", \"Artist\", \"Customer\", \"Employee\", \"Genre\", \"Invoice\",\"InvoiceLine\",\"MediaType\",\"Playlist\",\"PlaylistTrack\",\"Track\";");
            statement.close();
        } catch (SQLException e) {
            System.out.println("ERROR: inserting data:\n" + sql);
        }
    }

    public void getCSV(){
        csvWriter = new CSVWriter(writer,
                ',',
                '"',
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        for (String s: output
             ) {
            csvWriter.writeNext(new String[] {s.replace("INSERT INTO public.\"Genre\"(\"GenreId\", \"Name\")VALUES (", "Genre,")
                                                .replace("):","")});
            System.out.println("CSV Sucessfully write: " + s);

        }

    }
}
