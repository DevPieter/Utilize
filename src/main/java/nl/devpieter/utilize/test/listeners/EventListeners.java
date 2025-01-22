package nl.devpieter.utilize.test.listeners;

import nl.devpieter.sees.Annotations.EventListener;
import nl.devpieter.sees.Listener.Listener;
import nl.devpieter.utilize.events.SleepStateChangedEvent;
import nl.devpieter.utilize.events.interaction.*;

public class EventListeners implements Listener {

    @EventListener
    public void onSleepStateChanged(SleepStateChangedEvent event) {
        System.out.println("SleepStateChangedEvent " + event.previous() + " -> " + event.current());
    }

    @EventListener
    public void onUpdateBlockBreakingProgress(UpdateBlockBreakingProgressEvent event) {
        System.out.println("UpdateBlockBreakingProgressEvent");
//        event.cancel();
    }

    @EventListener
    public void onAttackBlock(AttackBlockEvent event) {
        System.out.println("AttackBlockEvent");
//        event.cancel();
    }

    @EventListener
    public void onAttackEntity(AttackEntityEvent event) {
        System.out.println("AttackEntityEvent");
//        event.cancel();
    }

    @EventListener
    public void onBreakBlock(BreakBlockEvent event) {
        System.out.println("BreakBlockEvent");
//        event.cancel();
    }

    @EventListener
    public void onInteractBlock(InteractBlockEvent event) {
        System.out.println("InteractBlockEvent");
//        event.cancel();
    }

    @EventListener
    public void onInteractEntity(InteractEntityEvent event) {
        System.out.println("InteractEntityEvent");
//        event.cancel();
    }

    @EventListener
    public void onInteractItem(InteractItemEvent event) {
        System.out.println("InteractItemEvent");
//        event.cancel();
    }
}
