package mars_rover;

public interface CommunicationInterpreter {
    NavigationCommand translate(String command);
}
