package util;

public enum Command {
    SEARCH,
    STAT;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
