package com.atherys.professions.facade;

import com.atherys.core.utils.AbstractMessagingFacade;
import com.google.inject.Singleton;

@Singleton
public class  ProfessionsMessagingFacade extends AbstractMessagingFacade {
    public ProfessionsMessagingFacade() {
        super("Professions");
    }
}