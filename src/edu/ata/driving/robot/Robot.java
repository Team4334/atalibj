package edu.ata.driving.robot;

import edu.ata.auto.AutonomousSelector;
import edu.ata.driving.modules.Module;
import java.util.Hashtable;

/**
 * Class that represents the robot. The ideal way to implement this class is by
 * adding a whole bunch of functionality to the class, and using its specific
 * methods to manipulate the robot.
 *
 * <p> A bad way to implement this class is by just including everything inside
 * of {@link Robot#autonomous()}, {@link Robot#teleop()} and
 * {@link Robot#disabled()}. This would take away any functionality the robot
 * could have.
 *
 * @author Joel Gallant
 */
public abstract class Robot {

    private static Robot robot;
    private final Hashtable modules;
    private final String name;

    public static Robot getRobot() {
        if (robot == null) {
            robot = new DefaultRobot(new Module[0], "Fake Robot", AutonomousSelector.getInstance()) {
                public void teleopLoop() {
                    System.out.println("Default robot running... Overload me!");
                }
            };
        }
        return robot;
    }

    /**
     * Creates robot with its modules and name. The name of the robot should be
     * identifiable to the user.
     *
     * @param modules its necessary modules
     * @param name the robot configuration's name
     */
    public Robot(Module[] modules, String name) {
        if (modules == null) {
            throw new NullPointerException("Modules were null in robot - " + name);
        }
        this.modules = new Hashtable(modules.length);
        for (int x = 0; x < modules.length; x++) {
            this.modules.put(modules[x].getIdentifier(), modules[x]);
        }
        this.name = name;
    }

    /**
     * Returns the identifiable name of the robot configuration. This should
     * mean something to the user.
     *
     * @return name of robot
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the {@link Hashtable} object used to store modules. This should
     * be used to display to the user, and typically nothing else. Interaction
     * with a {@link Robot} object should happen through its methods, and not
     * this table. (The latter would be very inefficient.)
     *
     * @return the hash table of modules
     */
    public Hashtable getModules() {
        return modules;
    }

    /**
     * This is the initialization method of the robot. It should start all
     * necessary modules and calibrate them correctly.
     *
     * <p> This is run once when the robot is starting up (First turned on).
     */
    public abstract void init();

    /**
     * This is the disabled method of the robot. Typically, this should shut off
     * all motor (just in case).
     *
     * <p> This is run once when the robot is disabled.
     */
    public abstract void disabled();

    /**
     * This is the teleop method of the robot. Typically, this should start user
     * control, and get the robot driving.
     *
     * <p> This is run periodically when the robot is in teleop mode.
     */
    public abstract void teleopLoop();

    /**
     * This is the autonomous method of the robot. Typically, this should start
     * the autonomous mode, and do whatever is involved for that period of time.
     *
     * <p> This is run once when the robot is starting autonomous mode.
     */
    public abstract void autonomous();
}