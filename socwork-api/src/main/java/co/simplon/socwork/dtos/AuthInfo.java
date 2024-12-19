package co.simplon.socwork.dtos;

import java.util.List;

public record AuthInfo(String token, List<String> roles) {

    @Override
    public String toString() {
        return "AuthInfo{" +
                "token='" + "TOKEN" + '\'' +
                ", roles=" + roles +
                '}';
    }
}
