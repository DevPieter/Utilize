package nl.devpieter.utilize.managers;

import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.player.PlayerDamagedEvent;
import nl.devpieter.utilize.events.player.PlayerHealedEvent;

public class DamageManager {

    private static DamageManager INSTANCE;

    private final Sees sees = Sees.getInstance();

    private double currentHealth = -1;

    private DamageManager() {
    }

    public static DamageManager getInstance() {
        if (INSTANCE == null) INSTANCE = new DamageManager();
        return INSTANCE;
    }

    public void tick(double currentHealth) {
        if (this.currentHealth == -1) {
            this.currentHealth = currentHealth;
            return;
        }

        double difference = this.currentHealth - currentHealth;
        this.currentHealth = currentHealth;

        if (difference == 0) return;

        if (difference > 0) sees.call(new PlayerDamagedEvent(currentHealth, difference));
        else if (difference < 0) sees.call(new PlayerHealedEvent(currentHealth, -difference));
    }

    public double getCurrentHealth() {
        return this.currentHealth;
    }
}
