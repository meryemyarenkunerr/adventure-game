public abstract class SafeLocation extends Location {
    public SafeLocation(Player player, int id, String name) {
        super(player, id, name);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}
