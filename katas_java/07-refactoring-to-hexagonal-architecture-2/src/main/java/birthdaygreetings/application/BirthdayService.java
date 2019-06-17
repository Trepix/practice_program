package birthdaygreetings.application;

import birthdaygreetings.core.Employee;
import birthdaygreetings.core.EmployeesRepository;
import birthdaygreetings.core.GreetingMessage;
import birthdaygreetings.core.OurDate;
import birthdaygreetings.infrastructure.senders.GreetingsSender;

import java.util.List;

public class BirthdayService {

    private final GreetingsSender greetingsSender;
    private EmployeesRepository employeesRepository;

    public BirthdayService(GreetingsSender greetingsSender, EmployeesRepository employeesRepository) {
        this.greetingsSender = greetingsSender;
        this.employeesRepository = employeesRepository;
    }

    public void sendGreetings(OurDate date) {
        List<Employee> employeesWhoseIsBirthday = employeesHavingBirthday(date);
        List<GreetingMessage> messages = greetingMessagesFor(employeesWhoseIsBirthday);
        send(messages);
    }

    private List<GreetingMessage> greetingMessagesFor(List<Employee> employees) {
        return GreetingMessage.generateForSome(employees);
    }

    private List<Employee> employeesHavingBirthday(OurDate today) {
        return employeesRepository.whoseBirthdayIs(today);
    }

    private void send(List<GreetingMessage> messages) {
        for (GreetingMessage message : messages) {
            greetingsSender.sendMessage(message);
        }
    }


}
