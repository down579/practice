package my.hydra.practice.models;

import lombok.Getter;

@Getter
public class LocalCoordinate {
    public String level3;
    public int x;
    public int y;
    public LocalCoordinate(String level3, int x, int y) {
        this.level3 = level3;
        this.x = x;
        this.y = y;
    }
}
