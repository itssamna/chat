package server;

import java.sql.*;

public class DB {
    private static Connection connection;
    private static Statement stmt;
    private PreparedStatement getNicknameByLoginAndPasswordStatement;
    private PreparedStatement addNewUserStatement;
    private PreparedStatement updateNicknameStatement;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    public static void disconnect(){
        try{
            stmt.close();
            connection.close();
        }
        catch (SQLException err){
            err.printStackTrace();
        }
    }

    public String getNicknameByLoginAndPassword (String login, String password) {
        ResultSet response;
        String nickname = null;

        try {
            getNicknameByLoginAndPasswordStatement = connection.prepareStatement("SELECT nickname FROM users WHERE login =? and password = ?");
            getNicknameByLoginAndPasswordStatement.setString(1, login);
            getNicknameByLoginAndPasswordStatement.setString(2, password);
            response = getNicknameByLoginAndPasswordStatement.executeQuery();

            nickname = response.getString("nickname");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nickname;
    }

    public boolean addNewUser (String login, String password, String nickname) {
        boolean result = false;

        try {
            addNewUserStatement = connection.prepareStatement("INSERT INTO users (login, password, nickname) values (?, ?, ?)");
            addNewUserStatement.setString(1, login);
            addNewUserStatement.setString(2, password);
            addNewUserStatement.setString(3, nickname);

            addNewUserStatement.executeUpdate();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean updateNickname(String nickname, String newNickname){
        boolean result = false;

        try {
            updateNicknameStatement = connection.prepareStatement("UPDATE users SET nickname = ? WHERE nickname  = ?");
            updateNicknameStatement.setString(1, newNickname);
            updateNicknameStatement.setString(2, nickname);

            updateNicknameStatement.executeUpdate();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
