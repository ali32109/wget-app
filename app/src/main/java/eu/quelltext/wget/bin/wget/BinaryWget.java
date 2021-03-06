package eu.quelltext.wget.bin.wget;

import androidx.annotation.NonNull;

import java.io.IOException;

import eu.quelltext.wget.bin.Executable;

public class BinaryWget implements IWget {
    private final Executable executable;

    public BinaryWget(Executable executable) {
        this.executable = executable;
    }

    @NonNull
    @Override
    public String version() {
        try {
            Executable.Result result = executable.run(new String[]{"--version"}, new String[]{});
            result.waitFor();
            return result.getOutput();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean isValid() {
        String version = version();
        return !version.equals("");
    }

    @Override
    public Executable.Result run(String[] command, String[] envList) throws IOException {
        return executable.run(command, envList);
    }
}
