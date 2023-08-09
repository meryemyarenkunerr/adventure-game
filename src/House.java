public class House extends SafeLocation {
    public House(Player player) {
        super(player, 1, "House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("You are in the House. Your health will be full.");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        this.getPlayer().printfPlayerInfo();
        return true;
    }
}
