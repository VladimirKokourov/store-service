package util;

import exception.InvalidArgumentException;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Arrays;

public class ArgsValidator {

    public static final int NUMBER_OF_ARGS = 3;
    public static final String FILE_FORMAT = ".json";

    public static void validate(String[] args) {
        if (args.length != NUMBER_OF_ARGS) {
            throw new InvalidArgumentException("Неверное число аргументов");
        }
        String inputCommand = args[0].toLowerCase();
        String inputPath = args[1];
        String outputPath = args[2];

        if (Arrays.stream(Command.values()).noneMatch(command -> command.toString().equals(inputCommand))) {
            throw new InvalidArgumentException("Неверный ввод команды: " + inputCommand);
        }

        if (!isValidFilePath(inputPath)) {
            throw new InvalidArgumentException("Неверный ввод пути к файлу: " + inputPath);
        }
        if (!isValidFilePath(outputPath)) {
            throw new InvalidArgumentException("Неверный ввод пути к файлу: " + outputPath);
        }

        if (!isValidFileFormat(inputPath)) {
            throw new InvalidArgumentException("Неверный формат файла: " + inputPath);
        }
        if (!isValidFileFormat(outputPath)) {
            throw new InvalidArgumentException("Неверный формат файла: " + outputPath);
        }
    }

    private static boolean isValidFilePath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ex) {
            return false;
        }
        return true;
    }

    private static boolean isValidFileFormat(String path) {
        return path.endsWith(FILE_FORMAT);
    }
}
