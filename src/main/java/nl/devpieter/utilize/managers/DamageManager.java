package nl.devpieter.utilize.managers;

import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.player.PlayerDamagedEvent;
import nl.devpieter.utilize.events.player.PlayerHealedEvent;

public final class DamageManager {

    private static final DamageManager INSTANCE = new DamageManager();

    private final Sees sees = Sees.getSharedInstance();

    private double currentCurrentHealth = -1;

    private DamageManager() {
    }

    public static DamageManager getInstance() {
        return INSTANCE;
    }

    public void tick(double currentHealth) {
        if (currentCurrentHealth == -1) {
            currentCurrentHealth = currentHealth;
            return;
        }

        double difference = currentCurrentHealth - currentHealth;
        currentCurrentHealth = currentHealth;

        if (difference == 0) return;

        if (difference > 0) sees.dispatch(new PlayerDamagedEvent(currentHealth, difference));
        else if (difference < 0) sees.dispatch(new PlayerHealedEvent(currentHealth, -difference));
    }

    public double getCurrentHealth() {
        return currentCurrentHealth;
    }
}
