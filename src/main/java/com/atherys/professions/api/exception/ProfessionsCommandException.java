package com.atherys.professions.api.exception;

import com.atherys.professions.AtherysProfessions;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.text.Text;

public class ProfessionsCommandException extends CommandException {
    public ProfessionsCommandException(Text message) {
        super(AtherysProfessions.getInstance().getMessagingFacade().formatError(message));
    }

    public ProfessionsCommandException(Object... msg) {
        this(Text.of(msg));
    }
}
