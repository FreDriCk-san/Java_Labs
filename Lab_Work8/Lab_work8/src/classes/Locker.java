package classes;

public class Locker {

    private int value;
    private boolean locked;

    public Locker (){
        this.locked = false;
        this.value = 0;
    }

    public void set(int value) throws LockerLockedException {
        if (this.locked){
            throw new LockerLockedException();
        }
        this.value = value;
    }

    public int get() {
        return this.value;
    }

    public void lock() {
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }
}
