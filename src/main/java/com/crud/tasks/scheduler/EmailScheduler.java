
package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT="subject";
    private static final String SUBJECT2="number of tasks";
    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;


//    @Scheduled(cron = "0 0 10 * * *")
//    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail(){
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(),SUBJECT,message(),null));
    }

    public String message(){
        long size = taskRepository.count();
        return "Currently in database you got: "+size+((size!=1)?" tasks":" task");
    }

    @Scheduled(cron = "0 0 14 * * *")
    public void sendEmailWithNumberOfTasks(){
        simpleEmailService.send(simpleEmailService.createMimeMessageNumberOfTasks(
                new Mail(adminConfig.getAdminMail(),SUBJECT2,"",null)));
    }

}
