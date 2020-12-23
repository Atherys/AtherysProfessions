package com.atherys.professions.command;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.PlayerCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import com.atherys.core.command.annotation.Permission;
import com.atherys.professions.AtherysProfessions;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

@Aliases("makeblueprint")
@Permission("atherysprofessions.admin.makeblueprint")
@Description("A command to add the necessary NBT data for an item to be considered a blueprint")
public class MakeBlueprintCommand implements ParameterizedCommand, PlayerCommand {

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[] {
                GenericArguments.string(Text.of("blueprintId")),
                GenericArguments.optional(GenericArguments.bool(Text.of("isOriginal")), false),
                GenericArguments.optional(GenericArguments.doubleNum(Text.of("efficiency")), 0.0d)
        };
    }

    @Override
    public CommandResult execute(Player src, CommandContext args) throws CommandException {
        AtherysProfessions.getInstance().getBlueprintFacade().addBlueprintDataToHeldItem(
                src,
                args.<String>getOne(Text.of("blueprintId")).orElse(null),
                args.<Boolean>getOne(Text.of("isOriginal")).orElse(false),
                args.<Double>getOne(Text.of("efficiency")).orElse(0.0d)
        );
        return CommandResult.success();
    }
}
