package classes;

import java.rmi.AccessException;

public class LockerLockedException extends AccessException {

    public LockerLockedException () {
        super("Container is locked. You can't set value.");
    }
}
