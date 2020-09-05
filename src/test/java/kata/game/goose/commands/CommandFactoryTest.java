package kata.game.goose.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void shouldCreateMovePlayerCommand() {
        final Command command = commandFactory.createCommand("move pluto 4, 2");
        assertEquals(MovePlayerCommand.class, command.getClass());
        MovePlayerCommand movePlayerCommand = (MovePlayerCommand) command;

        assertEquals("pluto", movePlayerCommand.getName());
        assertEquals("4", movePlayerCommand.getDice1());
        assertEquals("2", movePlayerCommand.getDice2());
    }

    @Test
    void shouldCreateAutoMovePlayerCommand() {
        final Command command = commandFactory.createCommand("move pluto");
        assertEquals(MovePlayerCommand.class, command.getClass());
        MovePlayerCommand movePlayerCommand = (MovePlayerCommand) command;

        assertEquals("pluto", movePlayerCommand.getName());
        assertNotNull(movePlayerCommand.getDice1());
        assertNotNull(movePlayerCommand.getDice2());
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