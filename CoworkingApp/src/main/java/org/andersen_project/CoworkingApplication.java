package org.andersen_project;

import org.andersen_project.context.ProgramContext;
import org.andersen_project.context.RepositoryContext;
import org.andersen_project.menu.MainMenu;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CoworkingApplication {
    public static void main(String[] args) {
        RepositoryContext repositoryContext = ProgramContext.atProgramStart();

        new MainMenu(repositoryContext).run();
    }
}
