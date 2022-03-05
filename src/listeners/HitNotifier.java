
package listeners;

/**
 * @author Tomer .
 * The interface HitNotifier indicates that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {

    /**
     * The method adds a new HitLinstener to the list of listeners of the notifier object.
     *
     * @param hl - the new HitLinstener to be added to the listeners list.
     */
    void addHitListener(HitListener hl);

    /**
     * The method removes the given HitLinstener from the list of listeners of the notifier object.
     *
     * @param hl - the HitLinstener to be removed from the listeners list.
     */
    void removeHitListener(HitListener hl);

}
