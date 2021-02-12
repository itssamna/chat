package server;

public class DBAuthService implements AuthService {
    DB db = new DB();

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return db.getNicknameByLoginAndPassword(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return db.addNewUser(login, password, nickname);
    }

    public boolean updateNickname(String nickname, String newNickname){
        return db.updateNickname(nickname, newNickname);
    }
}
