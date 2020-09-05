package kata.game.goose.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CommandFactoryTest {

    private CommandFactory commandFactory = new CommandFactory();

    @Test
    void shouldCreateExitCommand() {
        final Command command = commandFactory.createCommand("exit");
        assertEquals(ExitCommand.class, command.getClass());
    }

    @Test
    void shouldCreateAddPlayerCommand() {
        final Command command = commandFactory.createCommand("add player pluto");
        assertEquals(AddPlayerCommand.class, command.getClass());
        assertEquals("pluto", ((AddPlayerCommand) command).getName());
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        try {
            final Command command = commandFactory.createCommand("unrecognized");
            fail("Exception not thrown");
        } catch (IllegalArgumentException ignored) {
        }
    }
}