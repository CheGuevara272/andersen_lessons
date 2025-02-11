package org.andersen_project;

import org.andersen_project.config.AppConfig;
import org.andersen_project.context.ProgramContext;
import org.andersen_project.context.RepositoryContext;
import org.andersen_project.menu.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoworkingApplication {
    @Autowired
    private MainMenu mainMenu;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //RepositoryContext repositoryContext = ProgramContext.atProgramStart();

        CoworkingApplication app = context.getBean(CoworkingApplication.class);
        app.start();

        context.close();
    }

    public void start(){
        mainMenu.run();
    }
}
